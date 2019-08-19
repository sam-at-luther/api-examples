// Get Version
//
// https://www.purestake.com and https://developer.purestake.io
// 
// Code borrowed and extended from https://developer.algorand.org/docs/javascript-sdk
//
// Example using PureStake token object, replacing the token as a string
//

const algosdk = require('algosdk');
const baseServer = "https://testnet-algorand.api.purestake.io/ps1"
const port = "";
const token = {
    'X-API-Key': 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab'
}

const algodclient = new algosdk.Algod(token, baseServer, port);

(async () => {
    console.log(await algodclient.versions());
})().catch(e => {
    console.log(e);
});
