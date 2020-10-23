package com.purestake.algosdk.example;

import com.algorand.algosdk.v2.client.common.IndexerClient;

public class IndexerAssetSearch {

    public static void main(String[] args) throws Exception {
        final String ALGOD_API_ADDR = "https://testnet-algorand.api.purestake.io/idx2";
        final int  ALGOD_PORT = 443;
        final String ALGOD_API_TOKEN = "";

        String[] headers = {"X-API-Key"};
        String[] values = {"B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab"};

        IndexerClient client = new IndexerClient(ALGOD_API_ADDR, ALGOD_PORT, ALGOD_API_TOKEN);
        
        String name = "test";
        Long limit = 1L;
        String response = client.searchForAssets().name(name).limit(limit).execute(headers, values).toString();
        System.out.println(response);
    }
}
