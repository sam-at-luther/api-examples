// https://www.purestake.com and https://developer.purestake.io
// 
// Code borrowed and extended from https://developer.algorand.org/docs/javascript-sdk
//
// Requires header 'content-type' for the binary transaction submission
// Both algodclient objects are necessary, as the params call is application/json and the SDK does not allow by request header assignment
// The TestNet account in this example is U2VHSZL3LNGATL3IBCXFCPBTYSXYZBW2J4OGMPLTA4NA2CB4PR7AW7C77E - PureStake cannot guarantee any funds will exist to execute this example
// To add Algo's - https://bank.testnet.algorand.network

const algosdk = require('algosdk');
const baseServer = "https://testnet-algorand.api.purestake.io/ps1"
const port = "";

// Create client for transaction POST
const postToken = {
    'X-API-key' : 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab',
    'Content-Type' : 'application/x-binary'
}

const postAlgodclient = new algosdk.Algod(postToken, baseServer, port); // Binary content type

//Create client for GET of Transaction parameters 
const token = {
    'X-API-key' : 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab',
}


const algodclient = new algosdk.Algod(token, baseServer, port); 

var mnemonic = "code thrive mouse code badge example pride stereo sell viable adjust planet text close erupt embrace nature upon february weekend humble surprise shrug absorb faint"; 
var recoveredAccount = algosdk.mnemonicToSecretKey(mnemonic); 
console.log(recoveredAccount.addr);


(async() => {

    let params = await algodclient.getTransactionParams();
    let endRound = params.lastRound + parseInt(1000);

    let txn = {
        "from": recoveredAccount.addr,
        "to": "AEC4WDHXCDF4B5LBNXXRTB3IJTVJSWUZ4VJ4THPU2QGRJGTA3MIDFN3CQA",
        "fee": 10,
        "amount": 2,
        "firstRound": params.lastRound,
        "lastRound": endRound,
        "genesisID": params.genesisID,
        "genesisHash": params.genesishashb64,
        "note": new Uint8Array(0),
    };

    let signedTxn = algosdk.signTransaction(txn, recoveredAccount.sk);
    let tx = (await postAlgodclient.sendRawTransaction(signedTxn.blob));
    console.log("Transaction : " + tx.txId);
})().catch(e => {
    console.log(e);
});