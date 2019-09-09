from algosdk import encoding
from algosdk import transaction
from algosdk import kmd
from algosdk import algod
from algosdk import account
from algosdk import mnemonic
import json

# Initalize throw-away account for this example - check that is has funds before running script
mnemonic_phrase = "code thrive mouse code badge example pride stereo sell viable adjust planet text close erupt embrace nature upon february weekend humble surprise shrug absorb faint"; 
account_private_key = mnemonic.to_private_key(mnemonic_phrase)
account_public_key = mnemonic.to_public_key(mnemonic_phrase)

# Setup HTTP client w/guest key provided by PureStake
algod_token = 'B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab'
algod_address = 'https://testnet-algorand.api.purestake.io/ps1'
purestake_token = {'X-Api-key': algod_token}
acl = algod.AlgodClient(algod_token, algod_address, headers=purestake_token)

# get suggested parameters from Algod
params = acl.suggested_params()
gen = params["genesisID"]
gh = params["genesishashb64"]
first_valid_round = params["lastRound"]
last_valid_round = first_valid_round + 1000
fee = params["fee"]
send_amount = 1

existing_account = account_public_key
send_to_address = 'AEC4WDHXCDF4B5LBNXXRTB3IJTVJSWUZ4VJ4THPU2QGRJGTA3MIDFN3CQA'

# Create and sign transaction
tx = transaction.PaymentTxn(existing_account, fee, first_valid_round, last_valid_round, gh, send_to_address, send_amount)
signed_tx = tx.sign(account_private_key)


try:
    tx_confirm = acl.send_transaction(signed_tx, headers={'content-type': 'application/x-binary'})
    print(tx_confirm)
except Exception as e:
    print(e)

