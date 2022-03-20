## Running services in Docker container

- In base package run this command from terminal:
````
    docker compose up -d
````
 This will run Database, Product and User services.

## Using API's from Swagger

- Open web browser and go api's swagger page

For Product service:
``
localhost:8080/swagger-ui.html
``

For User service:
``
localhost:8081/swagger-ui.html
``

- "create user" **Before** "creating product"  

## Running tests

Make comment line "verifyUsername" places in **ProductService.java** before running tests
