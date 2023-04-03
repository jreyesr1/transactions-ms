# Transactions Microservice

## Table of contents
* [Stack Tech](#Stack)
* [Setup](#setup)
* [API Specs](#api-specs)

The objective of this app is to create and query the transaction status of an account

## Stack
* Java: 17
* Spring Boot: 3.0.5
* H2 in memory DataBase
* Cucumber: 7.8.1
* openapi-generator : 6.3.0
* Maven
* Liquibase : 4.20.0

## Setup
To run this project use Maven:

```
 mvn clean install verify
 mvn spring-boot:run
```

The app runs at the port 3000 `http://localhost:3000`

An account is created at source code level to make the test easier, the IBAN is `ES56321478963214523658974` , and it contains an ammount of 1000


## API Specs

### Create Transaction

- Request:
```
curl --location --request POST 'http://localhost:3000/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
  "reference": "12345ABC",
  "account_iban": "ES56321478963214523658974",
  "date": "2023-04-02T21:55:42.002Z",
  "amount": 100,
  "fee": 2,
  "description": "Restaurant payment"
}'
```

- Response

```
HTTP 200

{
    "reference": "12345ABC",
    "date": "2023-04-02T21:55:42.002Z",
    "amount": 100,
    "fee": 2,
    "description": "Restaurant payment",
    "id": 1,
    "account": {
        "account_iban": "ES56321478963214523658974",
        "balance": 1098,
        "id": 1
    }
}
```

### Query Transactions

- Request

```
curl --location --request GET 'http://localhost:3000/transaction?account_iban=ES56321478963214523658974&sort_by_amount=desc'
```

- Response

```
HTTP 200
[
    {
        "reference": "12345ABC",
        "date": "2023-04-02T16:55:42.002-05:00",
        "amount": 100,
        "fee": 2,
        "description": "Restaurant payment",
        "id": 1,
        "account": {
            "account_iban": "ES56321478963214523658974",
            "balance": 1098,
            "id": 1
        }
    }
]
```

### Query Transaction Status

Note: Due the requirments, I had to create it with a Post method, but querying 
data is not recomended to do with a POST request, it should be a GET, 
but regarding the requirmentes that we need a body request, I did it 
as per the requirements

- Request

```
curl --location --request POST 'http://localhost:3000/transaction/status' \
--header 'Content-Type: application/json' \
--data-raw '{
"reference":"12345ABC",
"channel":"INTERNAL"
}

'
```

- Response

```
{
    "reference": "12345ABC",
    "amount": 100,
    "fee": 2,
    "status": "SETTLED"
}
```
