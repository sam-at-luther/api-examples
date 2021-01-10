package com.purestake.algosdk.example;

import java.math.BigInteger;

import com.algorand.algosdk.algod.client.AlgodClient;
import com.algorand.algosdk.algod.client.api.AlgodApi;
import com.algorand.algosdk.algod.client.model.Block;
import com.algorand.algosdk.algod.client.model.NodeStatus;

public class CustomHeaderExample {

    public static void main(String[] args) {
        // DEPRECATED 12/30/20 with Algod v1 API Shutdown

        final String ALGOD_API_ADDR = "https://testnet-algorand.api.purestake.io/ps1";
                
        AlgodClient client = new AlgodClient();
        
        client.addDefaultHeader("X-API-Key", "......");
        
        client.setBasePath(ALGOD_API_ADDR);

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
