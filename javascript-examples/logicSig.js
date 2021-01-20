// LogicSig submission

// Using a program that is equal to '1' - always passing - Do Not Use in MainNet

// https://www.purestake.com and https://developer.purestake.io
// 
// Code borrowed and extended from https://developer.algorand.org/docs/asc1-sdk-usage
//
// Requires header 'content-type' for the binary transaction submission
// Both algodclient objects are necessary, as the params call is application/json and the SDK does not allow by request header assignment
// The TestNet account in this example is U2VHSZL3LNGATL3IBCXFCPBTYSXYZBW2J4OGMPLTA4NA2CB4PR7AW7C77E - PureStake cannot guarantee any funds will exist to execute this example
// To add Algo's - https://bank.testnet.algorand.network

// Updated for v2 1/20/21 


const algosdk = require('algosdk');
const baseServer = "https://testnet-algorand.api.purestake.io/ps2"
const port = "";
const token = {
    'X-API-key': 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab',
}


const algodclient = new algosdk.Algodv2(token, baseServer, port);

var mnemonic = "code thrive mouse code badge example pride stereo sell viable adjust planet text close erupt embrace nature upon february weekend humble surprise shrug absorb faint";
var recoveredAccount = algosdk.mnemonicToSecretKey(mnemonic);
console.log(recoveredAccount.addr);

(async () => {
    //Get the relevant params from the algod
    let params = await algodclient.getTransactionParams().do();

    // move the TEAL  program into Uint8Array
    let program = new Uint8Array(Buffer.from("ASABASI=", "base64"));
    let lsig = algosdk.makeLogicSig(program);
    lsig.sign(recoveredAccount.sk);


    //create a transaction
    let txn = {
        "from": recoveredAccount.addr,
        "to": "SOEI4UA72A7ZL5P25GNISSVWW724YABSGZ7GHW5ERV4QKK2XSXLXGXPG5Y",
        "fee": params.fee,
        "amount": 100000,
        "firstRound": params.firstRound,
        "lastRound": params.lastRound,
        "genesisID": params.genesisID,
        "genesisHash": params.genesisHash,
    };


    let rawSignedTxn = algosdk.signLogicSigTransaction(txn, lsig);

    //Submit the lsig signed transaction
    let tx = await algodclient.sendRawTransaction(rawSignedTxn.blob).do();
    console.log("Transaction : " + tx.txId);

})().catch(e => {
    console.log(e);
});