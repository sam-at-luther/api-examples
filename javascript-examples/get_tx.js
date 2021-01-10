// GET Transaction 
//
// https://www.purestake.com and https://developer.purestake.io
// 
// Code borrowed and extended from https://developer.algorand.org/docs/javascript-sdk
//
// Example using PureStake token object 
//

// DEPRECATED 12/30/20 with Algod v1 API Shutdown

const algosdk = require('algosdk');
const baseServer = "https://testnet-algorand.api.purestake.io/ps1"
const port = "";
const token = {
    'X-API-Key': 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab'
}

const algodclient = new algosdk.Algod(token, baseServer, port);

(async () => {
    console.log(await algodclient.transactionById('3J7Z46VKGJ6VATCHOE3I2JZXVDXKAWOKO3JJCR6R7EEX5VBKEATQ'));
})().catch(e => {
    console.log(e);
});
