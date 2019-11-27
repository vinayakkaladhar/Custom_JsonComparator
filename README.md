Project Name : JSON Comparator

## General info
This project is to Compare two json responses and validate if the responses are same, and if not, print in the console the api's that are not equal in response.

##Pre-requisite
Increment JAVA heap size to handle million requests
Set -Xmx=64M
To handle requests in parallel mark data-provider-thread-count="20" in test.xml

## Installation
Install JAVA
Install Eclipse
Install TestNG source: https://testng.org/doc/download.html

##Setup

Import the Maven project
Navigate to folder : JsonComparator
Open comfortable IDE (eclipse,netbeans)
Import the maven project - pom.xml
Navigate to folder : resources/testng
Right Click on the project and RunAs -> maven install (installs the dependencies)
Right Click on test.xml -> runs as testng test
Run as testNG Suite

##Functionalities supported
1.Read RestAssured endpoint from basefile.text and newfile.txt (navigate to resources -> testdata -> files)
2.Compare responses and print the results as :
if pass:
enpoint 1 (basefile.txt) equals enpoint 1 (newfile.txt)
if fail:
enpoint 1 (basefile.txt) not equals enpoint 1 (newfile.txt)
3.Compares inner/nested json based on corresponding key and value
4.Compares unsorted array jsons and compares the result
5.Access the report from : test-output -> index.html
6.Support for parallel execution


##Built With
Maven - Dependency Management

##Screenshot
https://snipboard.io/rJnUzT.jpg

## Usage
TODO: Build the Maven project
use the goal: mvn install - installs the dependencies
use the goal: mvn clean test - from the path pointing to POM.xml

## Authors
Vinayak kaladhar
