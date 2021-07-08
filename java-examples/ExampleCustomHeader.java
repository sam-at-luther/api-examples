package com.purestake.algosdk.example;

import com.algorand.algosdk.v2.client.common.AlgodClient;
import com.algorand.algosdk.v2.client.model.NodeStatusResponse;
import com.algorand.algosdk.v2.client.model.BlockResponse;

public class CustomHeaderExample {

    public static void main(String[] args) throws Exception {
        final String ALGOD_API_ADDR = "https://testnet-algorand.api.purestake.io/ps2";
        final int  ALGOD_PORT = 443;
        final String ALGOD_API_TOKEN_KEY = "X-API-Key";
        final String ALGOD_API_TOKEN = "B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab";

        AlgodClient client = new AlgodClient(ALGOD_API_ADDR, ALGOD_PORT, ALGOD_API_TOKEN, ALGOD_API_TOKEN_KEY);
        
        NodeStatusResponse status = null;
        
        try {
            status = client.GetStatus().execute().body();
        } catch (Exception e) {
            System.err.print("Failed to get algod status: " + e.getMessage());
        }
        
        if(status!=null) {
            System.out.println("algod last round: " + status.lastRound);
            System.out.println("algod time since last round: " + status.timeSinceLastRound);
            System.out.println("algod catchup: " + status.catchupTime);
            System.out.println("algod latest version: " + status.lastVersion);
            
            Long lastRound = status.lastRound;
            try {
                BlockResponse block = client.GetBlock(lastRound).execute().body();
                System.out.println("Block info: " + block.block);
            } catch (Exception e) {
                System.err.print("Failed to get block info: " + e.getMessage());
            }
        }
    }
}
