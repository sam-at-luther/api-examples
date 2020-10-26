# api-examples
Code samples for PureStake's API-As-A-Service

PureStake provides access to the Algorand TestNet and MainNet Ledgers over REST API. Signups are free.

The ledgers available are complete, indexed, and in a redundant, load balanced configuration under close monitoring.  

There are some requirements for using the PureStake API, and only the JavaScript, Go and Python SDKs are fully supported today. 


## Example using Algorand JavaScript SDK

* [GET ledger version](javascript-examples/get_versions.js)
* [POST transaction](javascript-examples/submit_tx.js)
* [GET Transaction By ID](javascript-examples/get_tx.js)
* [GET v2 Indexer Block](javascript-examples/v2/indexer_block.js)
* [POST v2 Transaction](javascript-examples/v2/algod_submit_tx.js)

## Example using HTML and Algodsdk.min.js

* [Algod v1, v2 and Indexer clients](javascript-examples/html-client-declaration.html)

## Example using Algorand Python SDK

* [Example GET](python-examples/example_custom_header.py)
* [Example POST transaction](python-examples/complete_example.py)
* [Example v2 POST transaction](python-examples/complete_example_v2.py)
* [algo_vanity.py](python-examples/algo_vanity.py) - Vanity address generator for Algorand.

## Example using Algorand Go SDK

* [GET](go-examples/example_custom_header.go)
* [POST transaction](go-examples/submit_tx.go)
* [POST transaction with KMD](go-examples/signsubmit.go)
* [GET v2](go-examples/v2/example_custom_header.go)
* [GET v2 Indexer](go-examples/v2/indexer_search_asset.go)
* [POST v2 Transaction](go-examples/v2/submit_tx.go)

## Example using Algorand Java SDK

* [GET](java-examples/ExampleCustomHeader.java)
* [POST transaction](java-examples/SubmitTx.java)
* [GET v2](java-examples/v2/ExampleCustomHeader.java)
* [GET v2 Indexer](java-examples/v2/IndexerAssetSearch.java)
* [POST v2 Transaction](java-examples/v2/SubmitTx.java)

## Other Resources

* [PureStake developer site](https://developer.purestake.io)
* [Algorand developer site](https://developer.algorand.com)
* [Algorand v2 migration guide](https://developer.algorand.org/docs/reference/sdks/migration/)


