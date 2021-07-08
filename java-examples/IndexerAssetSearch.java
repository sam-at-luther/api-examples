package com.purestake.algosdk.example;

import com.algorand.algosdk.v2.client.common.IndexerClient;

public class IndexerAssetSearch {

    public static void main(String[] args) throws Exception {
        final String IDX_API_ADDR = "https://testnet-algorand.api.purestake.io/idx2";
        final int  IDX_PORT = 443;
        final String IDX_API_TOKEN_KEY = "X-API-Key";
        final String IDX_API_TOKEN = "B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab";

        IndexerClient client = new IndexerClient(IDX_API_ADDR, IDX_PORT, IDX_API_TOKEN, IDX_API_TOKEN_KEY);
        
        String name = "test";
        Long limit = 1L;
        String response = client.searchForAssets().name(name).limit(limit).execute().toString();
        System.out.println(response);
    }
}
