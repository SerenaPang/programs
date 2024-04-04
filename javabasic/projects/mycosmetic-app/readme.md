#To Compile 
At pom xml level

```
 mvn package  
```


#To run the java class

```
without passing the file path in the command line: 

java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp
 
 
 pass the file path:

java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/cosmetic.txt    

```


```
/Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/src/main/java/com/mycompany/model/Cosmetic.java
```

```
/javabasic/projects/mycosmetic-app/cosmetic.txt
```

#Compile with dependecies


In pom.xml, add dependencies for mysql, and build for dependencies


#build with denpendency:

```
mvn package assembly:single


```

#compile and run the classes with dependency: 

```
without denpendencies:

java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.TestCosmeticDB /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/src/main/java/com/mycompany/app/database.properties


with dependencies
java -classpath ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \
    com.mycompany.app.TestCosmeticDB  \
    ./src/main/java/com/mycompany/app/database.properties
    
    
    target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar

```