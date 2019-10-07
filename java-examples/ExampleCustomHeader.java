package com.purestake.algosdk.example;

import java.math.BigInteger;

import com.algorand.algosdk.algo3d.client.AlgodClient;
import com.algorand.algosdk.algod.client.api.AlgodApi;
import com.algorand.algosdk.algod.client.auth.ApiKeyAuth;
import com.algorand.algosdk.algod.client.model.Block;
import com.algorand.algosdk.algod.client.model.NodeStatus;

public class CustomHeaderExample {

    public static void main(String[] args) {
        final String ALGOD_API_ADDR = "https://testnet-algorand.api.purestake.io/ps1";
        final String ALGOD_API_TOKEN = "";
                
        AlgodClient client = new AlgodClient();
        
        client.addDefaultHeader("X-API-Key", "......");
        
        client.setBasePath(ALGOD_API_ADDR);
        // Configure API key authorization: api_key
        ApiKeyAuth api_key = (ApiKeyAuth) client.getAuthentication("api_key");
        api_key.setApiKey(ALGOD_API_TOKEN);

        AlgodApi algodApiInstance = new AlgodApi(client);
        
        NodeStatus status = null;
        
        try {
            status = algodApiInstance.getStatus();
        } catch (Exception e) {
            System.err.print("Failed to get algod status: " + e.getMessage());
        }
        
        if(status!=null) {
            System.out.println("algod last round: " + status.getLastRound());
            System.out.println("algod time since last round: " + status.getTimeSinceLastRound());
            System.out.println("algod catchup: " + status.getCatchupTime());
            System.out.println("algod latest version: " + status.getLastConsensusVersion());
            
            BigInteger lastRound = status.getLastRound();
            try {
                Block block = algodApiInstance.getBlock(lastRound);
                System.out.println("Block info: " + block.toString());
            } catch (Exception e) {
                System.err.print("Failed to get block info: " + e.getMessage());
            }
        }
    }
}
