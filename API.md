# Directory Backend API

Serves data to the telephone directory front end.

Note: Unless specified all requests use Accepts: 'application/json', Content-Type: 'application/json'

### Summary

For further reference, please consider the following sections:

### PhoneNumber

- Add phone number: `POST /phoneNumber`
- Get all phone numbers: `GET /phoneNumbers`

### Customer

- Get all phone numbers of a single customer: `GET /customer/{customer_id}/phoneNumbers`
- Activate a number: `POST /customer/{customer_id}`
- Add customer: `POST /customer`

See below for more details.

---

## PHONE NUMBER

### Add phone number

`POST /phoneNumber`

```text
POST /phoneNumber => returns 201 OK and returns 

{
    "id": 1,
    "phone": "0469345892"
}
```

### Get all phone numbers

`GET /phoneNumbers`

```text
GET /phoneNumbers => returns 200 OK and returns 

[
    {
        "id": 1,
        "phone": "0469345892"
    },
    {
        "id": 2,
        "phone": "0444333222"
    }
]
```

## CUSTOMER

### Add Customer

`POST /phoneNumber`

```text
POST /customer => returns 200 OK and returns 

{
    "id": 2,
    "name": "John Cave",
    "emailId": "johncase@gmail.com",
    "phoneNumbers": []
}
```

### Activate number (Allocate number to customer)

`PATCH /customer/{customer_id}`

```text
PATCH /customer/2 => returns 200 OK and returns 

{
    "id": 2,
    "name": "John Cave",
    "emailId": "johncave@gmail.com",
    "phoneNumbers": [
        {
            "id": 1,
            "phone": "0469345892"
        }
    ]
}
```

### Customer's phone numbers (All phone numbers of a customer)

`GET /customer/{customer_id}/phoneNumbers`

```text
PATCH /customer/2/phoneNumbers => returns 200 OK and returns 

[
    {
        "id": 1,
        "phone": "0469345892"
    }
]
```

### All customers

`GET /customers`

```text
GET /customers => returns 200 OK and returns 

[
    {
        "id": 2,
        "name": "John Cave",
        "emailId": "johncave@gmail.com",
        "phoneNumbers": [
            {
                "id": 1,
                "phone": "0469345892"
            }
        ]
    }
]
```