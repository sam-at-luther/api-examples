# api-examples
Code samples for PureStake's API-As-A-Service

PureStake provides access to the Algorand TestNet and MainNet Ledgers over REST API. Signups are free.

The ledgers available are complete, indexed, and in a redundant, load balanced configuration under close monitoring.  

There are some requirements for using the PureStake API, and only the JavaScript, Go and Python SDKs are fully supported today. 


## Example using Algorand JavaScript SDK

* [GET ledger version](javascript-examples/get_versions.js)
* [POST transaction](javascript-examples/submit_tx.js)
* [GET Transaction By ID](javascript-examples/get_tx.js)

## Example using Algorand Python SDK

* [Example GET](python-examples/example_custom_header.py)
* [Example POST transaction](python-examples/complete_example.py)
* [algo_vanity.py](python-examples/algo_vanity.py) - Vanity address generator for Algorand.

## Example using Algorand Go SDK

* [Example GET](go-examples/example_custom_header.go)
* [Example POST transaction](go-examples/submit_tx.go)
* [Example POST transaction with KMD](go-examples/signsubmit.go)

## Example using Algorand Java SDK

* [Example GET](java-examples/ExampleCustomHeader.java)

## Other Resources

* [PureStake developer site](https://deverloper.purestake.io)
* [Algorand developer site](https://developer.algorand.com)


