# csv-exercise

The goal of this exercise is to produce a production ready web service that allows a user to fetch all the entries in a CSV that match their criterias. This exercise is constrained to 3 hours of time.

## Assumptions

- For now the CSV will fit nicely in memory if loaded
- The CSV could have different column names at a later date
- The data loaded in the webapp is public and https is not required
- A user could want to load multiple CSV at the same time

## Exercise parameters

- I will try to finish this exercise in 3 hours or less starting at the time I commit this file
- I will use vertx to create the webapp as I am used to work with it. This library is not like springboot or quarkus as in it doesn't come out of the box with thousands of nice-to-have such as automated deployment to docker, metrics, api documentation, configuration already done, etc. I will have to create more boilerplate by hand and abandon some of those nice-to-have entirely. I believe this is acceptable since this is only an exercise.

## Features I want to implement in any order (before I start)

- A GET HTTP endpoint should allow a user to query the CSV for elements within matching what is passed as path params.
- I want large tests to test my API
- The http port and csv file location should be configurable
- Ideally, we would be able to configure many CSV and have the endpoint be flexible enough to switch between CSVs
- I would like the application to be flexible enough to work with any CSV without having to hardcode column names.
- I would like to deliver the app as a fat jar
- I would like to dockerize the application
- I would like to have an endpoint that allows us to check the domain value of columns within the CSV

## Ordered TODO (for me internally)

- POM including dependencies and fat jar code
- Hello world application + test
- Start to code basic web server
- Large test
- Work on CSV
- Complete first feature
- Configuration
- Docker
- Second endpoint to check domain value of columns
- Support many CSVs

## DONE