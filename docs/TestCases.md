**Initialized Test Data**

***User***

| Id(username) | Nickname  | Plain passowrd | Encrypted password               |
| ------------ | --------- | -------------- | -------------------------------- |
| LiLei        | LiLei     | 123456         | 87d9bb400c0634691f0e3baaf1e2fd0d |
| HanMeimei    | HanMeimei | 123456         | 87d9bb400c0634691f0e3baaf1e2fd0d |

***Car***

| Id    | modelId     | NumberPlate |
| ----- | ----------- | ----------- |
| ar_0  | ar_model_0  | HK0000      |
| ar_1  | car_model_0 | HK0001      |
| car_2 | car_model_1 | HK0002      |
| ar_3  | car_model_1 | HK0003      |

***CarModel***

| Id          | Name         |
| ----------- | ------------ |
| car_model_0 | Toyota Camry |
| ar_model_1  | BMW 650      |



**Test Cases**


| Case Number | Case Name                                                    | Test Background      | Preconditions                                       | Importance | Priority | Version | Test Env              | Test Type | Test Steps                                                   | Expected Result                                              |
| ----------- | ------------------------------------------------------------ | -------------------- | --------------------------------------------------- | ---------- | -------- | ------- | --------------------- | --------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1           | User sign in with correct input                              |                      |                                                     | High       | High     | v1      | Google Chrome Browser | Manual    | 1.Go to the login page;2.Input the correct username and password;3.Click the `Sign in` button | Sign in successfully and jump to car models page automatically |
| 2           | User sign in with incorrect input                            |                      |                                                     | High       | High     | v1      | Google Chrome Browser | Manual    | 1.Go to the login page;2.Input some incorrect username and password;3.Click the `Sign in` button | Sign in failed, stay in the current page and there are tips with message shown |
| 3           | Book an available car with correct input                     | Sign in successfully | There are available cars                            | High       | High     | v1      | Google Chrome Browser | Manual    | 1.Go to the 'Cars' tab, choose a car model and click the `Book` button inside the table; 2. Select the correct begin time and end time of this order;3. Click the `Submit` button | Lease order created successfully, the stock of the selected car model decreased 1, and there is a new record added in the `Orders` tab |
| 4           | Book an available car with incorrect input                   | Sign in successfully |                                                     | High       | High    | v1      | Google Chrome Browser | Manual    | 1.Go to the 'Cars' tab, choose a car model and click the `Book` button inside the table; 2. Select the incorrect begin time and end time of this order;3. Click the `Submit` button | 418 status code returned, lease order created failed, there are tips with error message shown and no record added in the `Orders` tab |
| 5           | Book a car without sign in                                   |                      |                                                     | High       | High     | v1      | Postman               | Manual    | 1.Input the correct api address in the Postman;2. Input the correct body parameters and leave the token header empty;3. send the request to server | 401 status code returned and no lease order created          |
| 6           | Book an unavailable car                                      | Sign in successfully | The selected car model is not available             | High       | High     | v1      | Postman               | Manual    | 1.Input the correct api address in the Postman;2. Input the body parameters while the `carModelId` indicates an unavailable car model, input the correct token header 3. send the request to server | 540 status code returned and no lease order created          |
| 7           | Simulation of car booking in the high concurrency environment | Sign in successfully | The selected car model is initialized as 2 in stock | High       | High     | v1      | Postman               | Automatic | 1.Input the correct api address and correct parameters (both token header and body) in the Postman; 2. Set `Iterations` as 10 or larger and `Delay` as 0;3. choose the api and start test. | Only 2  requests succeeded, the others failed with status code 540, and there are 2 lease order created, exactly related to the  2 cars of the selected car model |

