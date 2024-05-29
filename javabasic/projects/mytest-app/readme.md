#To build

```
mvn package

````
#To Compile

```
java -cp target/mytest-app-1.0-SNAPSHOT.jar com.mycompany.app.App

```

#To compile and run unit test
```
mvn test

```

#To only compile test but not running tests
```
mvn test-compile

```

#To install plug in and dependency in pom.xml, after adding the dependency do
```
mvn install
```

#Run to see the different dependencies with running with different jar

```
jar -tf target/mytest-app-1.0-SNAPSHOT.jar
jar -tf target/mytest-app-1.0-SNAPSHOT.jar-with-dependencies.jar
jar -tf target/mytest-app-1.0-SNAPSHOT.jar-with-dependencies.jar | grep properties
```
#Build the project with dependency:

```
mvn package assembly:single
```



