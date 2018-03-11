# Fun with Events

This project explores event-driven architecture with Spring Cloud Stream, RabbitMQ, and Spring Cloud Contracts.

It uses Java 9, Kotlin, Spring Boot 2, and Junit 5.


## Contracts

Right now, the `creator` service is the only one that publishes contracts. You can find these at:
`/src/test/resources/contracts/`.

To create a jar with the contracts to be published:

```
$ ./gradlew clean copyContracts verifierStubsJar
```

This will create a Jar: `build/libs/creator-0.0.1-SNAPSHOT-stubs.jar`.

Currently, we are making this jar available to the `highScores` service via the classpath. So,
just copy this jar to `highScores/stubs` and `stats/stubs`.


## Running Tests

Go to each directory and `./gradlew clean test`

When you run the tests for the `creator` service, tests will automatically be generated to verify
that the `creator` service issues a message conforming to the contract. These tests are currently
based on `com.bw.events.contracts.TestBase`. Note that this file apparently must be written in Java.
`TestBase` contains a method that emits the event we want to verify.


## Running Locally

#### Start a RabbitMQ

Use Docker to start a RabbitMQ with a management interface on port 15672. It will
listen for messages on the default Rabbit port of 5672.

```
$ docker run -d --hostname my-rabbit \
    --name some-rabbit \
    -p 5672:5672 \
    -p 15672:15672 \
    --rm \
    rabbitmq:3-management
```

#### Start the Microservices

Simply run `foreman start` in the root directory.

Alternatively, go to each directory and `./gradlew clean bootRun`

You can exercise the system by sending a POST request to the `creator` service:

```
$ curl -i -d "{}" http://localhost:8080/games
```

You should see a `201` response with a url in the `Location` header. This will tell you
the UUID of the game. In the logs of the `stats` and `highScores` services, you should see a message that
indicates a game with that UUID was recorded as starting.
