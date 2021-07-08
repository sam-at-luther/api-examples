import json
from algosdk.v2client import indexer

algod_address = "https://testnet-algorand.api.purestake.io/idx2"
algod_token = ""
headers = {
   "X-API-Key": "B3SU4KcVKi94Jap2VXkK83xx38bsv95K5UZm2lab",
}

indexer_client = indexer.IndexerClient(algod_token, algod_address, headers)

name = 'test'
limit = 1
response = indexer_client.search_assets(name=name, limit=limit)

print("Asset search: " + json.dumps(response, indent=2, sort_keys=True))
