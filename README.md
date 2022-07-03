# Blackjack - solution

## Prerequisites

This program was made using kotlin1.7, java11 and maven
You will need Java and maven installed

## How to test

Tests are run when you run `mvn clean install`, but can also be triggered by running the command

```shell
mvn clean verify
```

## How to compile

Run the command in the root of the project to create an executable jar

````shell
mvn clean install
````

## How to run

To run the program, execute the jar file with dependencies with java

```shell
java -jar target/blackjack-1.0-SNAPSHOT-jar-with-dependencies.jar
```

This will run the blackjack program, where it will create and shuffle a deck of cards
that the players (sam, dealer) will use

To provide a file of cards the program should use, provide the file location as
an argument as the example below

```shell
java -jar target/blackjack-1.0-SNAPSHOT-jar-with-dependencies.jar src/test/resources/cards.txt
```