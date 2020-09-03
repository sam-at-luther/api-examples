# 1.4.0 update to add flat-fee=True to avoid bug in the SDK

import json
import time
import base64
from algosdk import algod
from algosdk import mnemonic
from algosdk import transaction

# Setup HTTP client w/guest key provided by PureStake
algod_token = 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab'
algod_address = 'https://testnet-algorand.api.purestake.io/ps1'
purestake_token = {'X-Api-key': algod_token}

# Initalize throw-away account for this example - check that is has funds before running script
mnemonic_phrase = "code thrive mouse code badge example pride stereo sell viable adjust planet text close erupt embrace nature upon february weekend humble surprise shrug absorb faint"; 
account_private_key = mnemonic.to_private_key(mnemonic_phrase)
account_public_key = mnemonic.to_public_key(mnemonic_phrase)

algodclient = algod.AlgodClient(algod_token, algod_address, headers=purestake_token)

# get suggested parameters from Algod
params = algodclient.suggested_params()
gen = params["genesisID"]
gh = params["genesishashb64"]
first_valid_round = params["lastRound"]
last_valid_round = first_valid_round + 1000
fee = params["minFee"]
send_amount = 1
print(params)
existing_account = account_public_key
send_to_address = 'AEC4WDHXCDF4B5LBNXXRTB3IJTVJSWUZ4VJ4THPU2QGRJGTA3MIDFN3CQA'

# Create and sign transaction
tx = transaction.PaymentTxn(existing_account, fee, first_valid_round, last_valid_round, gh, send_to_address, send_amount, flat_fee=True)
signed_tx = tx.sign(account_private_key)

# Function from Algorand Inc. - utility for waiting on a transaction confirmation
def wait_for_confirmation( algodclient, txid ):
    last_round = algodclient.status().get('lastRound')
    while True:
        txinfo = algodclient.pending_transaction_info(txid)
        if txinfo.get('round') and txinfo.get('round') > 0:
           print("Transaction {} confirmed in round {}.".format(txid, txinfo.get('round')))
           break
        else:
           print("Waiting for confirmation...")
           last_round += 1
           algodclient.status_after_block(last_round)

try:
    tx_confirm = algodclient.send_transaction(signed_tx, headers={'content-type': 'application/x-binary'})
    wait_for_confirmation(algodclient, txid = signed_tx.transaction.get_txid())
except Exception as e:
    print(e)

