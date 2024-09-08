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

## How to run

The application can be built and launch using the `startApp.sh` script. The application requires java 21 to be run. Specifically, it was built using Java 21.0.4-temurin. 

The webserver doesn't take any configuration for now. It listens for GET requests on port 8080 at route /v1/data-filter. The webserver is built so that it takes a csv named input.csv in the conf folder as an input. The user can query the csv for specific fields using query params. For example, this request fetches all elements that contain restrooms, 4 trashcans but no fishing spots `http://localhost:8080/v1/data-filter?RESTROOMS=Yes&FISHING=No&TrashCans=4`. The query params are all case sensitive and will match exactly with what is found in the csv so be careful.

The webserver works with any csv file and not only the one provided. However, CSVs provided must always have the first row dedicated to label the columns.

## Tradeoffs

- The application was done with the idea in mind that the CSV could change in the future and that it shouldn't require any code change to accomodate a specific CSV. This means that the structures manipulated in the code are not strongly typed. Instead of manipulating pojos, we manipulate a list of maps of strings.
- This project was labeled as a csv exercise but there is an interface in the project that could be implemented by other data provider such as a database or an external ressource.
- Because we assume that the CSVs this server works with will be small, we simply load them in memory at the application start. If the CSVs were bigger in nature, the strategy would probably be to load them to a database and query that instead. The Dao interface allows for this change although the CSV lifecycle would have to be disconnected.
- The use of vertx means that the application is asynchronous in nature. It should allow for a bigger load then a naive implementation of a server.

## Thoughts after exercise

- I only got the base feature implemented
- Boiler plate took me a long time to setup. By the time I was done with the basis of the server, I had about an hour left. Using springboot or quarkus, I could probably have saved 1h30 here. On the flip side, the application is future development would be more flexible because it would be done without a rigid structure that these big frameworks sometime enforce
- I am annoyed I didn't get the time to have a configuration online.
- I believe other features wouldn't take too long to get out now that the whole infrastructure (tests, injection, server, read the csv, etc) is here.
- The application is almost entirely tested which is good considering the time frame of the exercise.
- I should have put a try-catch block in my server component so that I could return 500 errors if problems occur. I was pressed for time when I did this part so I didn't think it through.
- I should have made the Dao interface return a future since it would allow me to asynchronously work with other data source that aren't already loaded in a data structure like an external database.


