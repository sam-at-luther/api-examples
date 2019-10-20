package com.purestake.algosdk.example;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

import com.algorand.algosdk.account.Account;
import com.algorand.algosdk.algod.client.AlgodClient;
import com.algorand.algosdk.algod.client.ApiException;
import com.algorand.algosdk.algod.client.api.AlgodApi;
import com.algorand.algosdk.algod.client.auth.ApiKeyAuth;
import com.algorand.algosdk.algod.client.model.NodeStatus;
import com.algorand.algosdk.algod.client.model.TransactionID;
import com.algorand.algosdk.algod.client.model.TransactionParams;
import com.algorand.algosdk.crypto.Address;
import com.algorand.algosdk.crypto.Digest;
import com.algorand.algosdk.transaction.SignedTransaction;
import com.algorand.algosdk.transaction.Transaction;
import com.algorand.algosdk.util.Encoder;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SubmitTx {

	public static void main(String[] args) throws GeneralSecurityException {
		final String ALGOD_API_ADDR = "https://testnet-algorand.api.purestake.io/ps1";
        final String ALGOD_API_TOKEN = "YOUR API KEY";
        
        final String SRC_ACCOUNT = "code thrive mouse code badge example pride stereo sell viable adjust planet text close erupt embrace nature upon february weekend humble surprise shrug absorb faint"; 
        final String DEST_ADDR = "AEC4WDHXCDF4B5LBNXXRTB3IJTVJSWUZ4VJ4THPU2QGRJGTA3MIDFN3CQA";
        
        //Setup HTTP client w/guest key provided by PureStake
        AlgodClient client = new AlgodClient();
		
		client.addDefaultHeader("X-API-Key",ALGOD_API_TOKEN);
		
		client.setBasePath(ALGOD_API_ADDR);
		// Configure API key authorization: api_key
		ApiKeyAuth api_key = (ApiKeyAuth) client.getAuthentication("api_key");
		api_key.setApiKey(ALGOD_API_TOKEN);
		
		AlgodApi algodApiInstance = new AlgodApi(client);
		
		Account src = new Account(SRC_ACCOUNT);
        System.out.println("My Address: " + src.getAddress());

        BigInteger feePerByte;
        String genesisID;
        Digest genesisHash;
        long firstRound = 0L;
        try {
            TransactionParams params = algodApiInstance.transactionParams();
            feePerByte = params.getFee();
            genesisHash = new Digest(params.getGenesishashb64());
            genesisID = params.getGenesisID();
            System.out.println("Suggested Fee: " + feePerByte);
            NodeStatus s = algodApiInstance.getStatus();
            firstRound = s.getLastRound().longValue();
            System.out.println("Current Round: " + firstRound);
        } catch (ApiException e) {
            throw new RuntimeException("Could not get params", e);
        }

        long amount = 1L;
        long lastRound = firstRound + 1000; // 1000 is the max tx window
        Transaction tx = new Transaction(src.getAddress(), new Address(DEST_ADDR), amount, firstRound, lastRound, genesisID, genesisHash);
        SignedTransaction signedTx = src.signTransactionWithFeePerByte(tx, feePerByte);
        System.out.println("Signed transaction with txid: " + signedTx.transactionID);

        // send the transaction to the network
        try {
            byte[] encodedTxBytes = Encoder.encodeToMsgPack(signedTx);
            TransactionID id = algodApiInstance.rawTransaction(encodedTxBytes);
            System.out.println("Successfully sent tx with id: " + id);
        } catch (ApiException e) {
            // This is generally expected, but should give us an informative error message.
            System.err.println("Exception when calling algod#rawTransaction: " + e.getResponseBody());
        } catch (JsonProcessingException e) {
        	System.err.println("Exception when calling algod#rawTransaction: " + e);
		}
	}
}
