## Environment:
- Java version: 17
- Maven version: 3.*
- Selenium HtmlUnitDriver: 2.52.0

## Read-Only Files:
- src/test/*
- website/*
- src/main/java/com/hackerrank/selenium/server/*

## Form Data
Fill the data entry form with the following data:
- *date of birth*: July 11, 1990
- *favorite programming language*: Java, Python
- *favorite operating system*: Linux, Mac OSX
- *favorite IDEA*: IntelliJ IDEA

## Requirements:
In this challenge, you are going to use the Selenium WebDriver, the HtmlUnitDriver, which uses the HtmlUnit headless browser. This means you don't need to set up the browser (like Firefox or Chrome) nor a web driver executable (like FirefoxDriver or ChromeDriver). Every web page has web elements (aka DOM objects) with unique names or ids.

There is a class `DataEntryAutomation` that has 2 methods:

1. `fillDateOfBirth`: Fills the date of birth (month, day, and year) according to the given data.
2. `answerQuestions`: Chooses options for each of the questions according to the given data.

These methods have 2 parameters, one web driver and one web page URL. The page that will be supplied is website/home.html, which will look like the following:

![web page](webPage.png)

Your task is to complete the implementation of those 2 methods so that the unit tests pass while running the tests.

## Commands
- run: 
```bash
mvn clean package && java -jar target/selenium-java-data-entry-automation-1.0.jar
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```
