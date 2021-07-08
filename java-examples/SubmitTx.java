package com.purestake.algosdk.example;

import com.algorand.algosdk.account.Account;
import com.algorand.algosdk.v2.client.common.AlgodClient;
import com.algorand.algosdk.v2.client.model.PendingTransactionResponse;
import com.algorand.algosdk.v2.client.model.TransactionParametersResponse;
import com.algorand.algosdk.v2.client.model.PostTransactionsResponse;
import com.algorand.algosdk.transaction.SignedTransaction;
import com.algorand.algosdk.transaction.Transaction;
import com.algorand.algosdk.util.Encoder;

public class SubmitTx {
    // Function from Algorand Inc. - Utility function to wait on a transaction to be confirmed
    public static void waitForConfirmation(AlgodClient client, String txID, String[] headers, String[] values) throws Exception {
        Long lastRound = client.GetStatus().execute(headers, values).body().lastRound;
        while (true) {
            try {
                // Check the pending tranactions
                PendingTransactionResponse pendingInfo = client.PendingTransactionInformation(txID).execute(headers, values).body();
                if (pendingInfo.confirmedRound != null && pendingInfo.confirmedRound > 0) {
                    System.out.println("Transaction confirmed in round " + pendingInfo.confirmedRound);
                    break;
                }
                lastRound++;
                client.WaitForBlock(lastRound).execute(headers, values);
            } catch (Exception e) {
                throw (e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final String ALGOD_API_ADDR = "https://testnet-algorand.api.purestake.io/ps2";
        final int  ALGOD_PORT = 443;
        final String ALGOD_API_TOKEN_KEY = "X-API-Key";
        final String ALGOD_API_TOKEN = "B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab";

        AlgodClient client = new AlgodClient(ALGOD_API_ADDR, ALGOD_PORT, ALGOD_API_TOKEN, ALGOD_API_TOKEN_KEY);

        final String SRC_ACCOUNT = "cool online brush identify bean nuclear elder soft fashion mind inside drama camp excess captain window spare oxygen tonight kingdom sustain pigeon predict ability rail"; 
        Account src = new Account(SRC_ACCOUNT);

        final String DEST_ADDR = "ZHGZZQ2PIWYRK6MIK44GKO3VGQUC7NS2V3UQ63M3DIMFUFGI4BRWK7WDBU";

        TransactionParametersResponse params = client.TransactionParams().execute().body();
        Long amount = 10L;

        Transaction tx = Transaction.PaymentTransactionBuilder().sender(src.getAddress()).receiver(DEST_ADDR).amount(amount).suggestedParams(params).build();
        SignedTransaction signedTx = src.signTransaction(tx);

        // send the transaction to the network
        try {
            String[] txHeaders = {"Content-Type"};
            String[] txValues = {"application/x-binary"};
            byte[] encodedTxBytes = Encoder.encodeToMsgPack(signedTx);
            PostTransactionsResponse txResponse = client.RawTransaction().rawtxn(encodedTxBytes).execute(txHeaders, txValues).body();
            System.out.println("Successfully sent tx with ID " + txResponse.txId);
            waitForConfirmation(client, txResponse.txId, headers, values);
        } catch (Exception e) {
            System.err.println("Exception when calling algod#rawTransaction: " + e);
        }
    }
}
