# xERP
> My Company ERP

## Prerequisites

* JDK 1.8.0_latest
* Maven 3.x

Before moving on, make sure you have the required JDK and Maven version.

	$ mvn -version
	$ java -version
	$ javac -version

###### Install Maven on Ubuntu
	sudo apt-get install maven

###### Install Maven on OS X
	brew install maven

## Run the Application

	$ cd xerp
	$ mvn clean install
	$ cd xerp-app
	$ mvn spring-boot:run
