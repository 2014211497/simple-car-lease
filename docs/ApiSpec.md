## Book a car for a period

**Api** `/simple_car_lease/api/v1/lease_orders`

**Completed Api For Test** `http://localhost:8080/simple_car_lease/v1/lease_orders` 

**Method** `POST`

**Consumes** `["application/json"]`

**Produces** `["application/json"]`

**Description** `Create a car lease order with the selected car model, the begin time and end time`

**Request Parameters**

| Name        | Description    |     Position |  Required      |  Type  |
| ------------ | -------------------------------- |-----------|--------|----|
| param         |   param in json format, see `CreateLeaseOrderParam` below   |     body        |      true      | CreateLeaseOrderParam   |
| token         |      access token   |     header        |      true      | String   |

**CreateLeaseOrderParam**

| Name      | escription     |  Required     |  Type  |
| ------------ | -------------------------------- |--------|----|
| carModelId  | The model id of the car to book |   true   | String  |
| beginTime  | The beginTime of this order(Timestamp in second) |   true   |Long  |
| endTime  | The endTime of this order(Timestamp in second) |   true   |Long  |

**Response Parameters**

| Name     | Description                  | Type |
| ------------ | -------------------|-------|
| code     |status code for this request, see `Response Code` below     |   Integer   |
| data     | the id of the created order | String |
| message     | error of extra information |   String   |
| took | the time that this request took(Timestamp in millisecond) |   Long   |

**Response Code**

| Code | Description                                       |
| ---- | ------------------------------------------------- |
| 200  | OK                                                |
| 401  | Invalid access token, required login              |
| 428  | Parameter error, missing fields or invalid values |
| 500  | Internal error of the server                      |
| 540  | No cars available for this request                |

**Request Parameter Example**


```json
{
    "carModelId": "car_model_0",
    "beginTime": 1634587479,
    "endTime": 1634687479
}
```

**Response Parameter Example**

```json
{
    "code": 200,
    "data": "68c5558ca7644c44bc88aac8dd9d7777",
    "took": 21
}
```

```json
{
    "code": 401,
    "message": "Invalid access token",
    "took": 21
}
```

```json
{
    "code": 418,
    "message": "parameter carModelId cannot be null or empty",
    "took": 21
}
```

```json
{
    "code": 500,
    "message": "something wrong with the server, please try again later please",
    "took": 21
}
```

```json
{
    "code": 540,
    "message": "No cars available",
    "took": 21
}
```

