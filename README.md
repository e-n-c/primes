Created as response to a test. 

# Primes

## Aim

To provide API that returns list of prime numbers.

## Implementation

API is implemented in Java using Spring Boot. https://github.com/e-n-c/primes/blob/master/java_primes_service
E2E test implemented in Groovy with Spock. https://github.com/e-n-c/primes/blob/master/groovy_api_tests

## Build and deploy

Docker is used manage the build, test and runtime environments

Compile and unit test with: `docker-compose run dev`

Run e2e tests with: `docker-compose run test`

Run instance of application with: `docker-compose run api`

## Usage

Get some primes `curl http://localhost:8080/primes/lessthan/{value}`
