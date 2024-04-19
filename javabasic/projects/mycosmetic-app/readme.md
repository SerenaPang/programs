#Create the Maven project

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=mycosmetic-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Build the Maven project

```
java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp

```

To Compile the project 

run at pom.xml level

```
mvn compile
```
Making a JAR file:

```
mvn package  
```
To run the java class

```
java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp
 ```
pass the file path so we can store info in file:
 
```
java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.CosmeticApp /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/cosmetic.txt    

```
pass the database property file but won't work because we haven't add build and dependencies:

```
java -cp target/mycosmetic-app-1.0-SNAPSHOT.jar com.mycompany.app.TestCosmeticDB /Users/serenapang/Development/JavaBasics/javabasic/projects/mycosmetic-app/src/main/java/com/mycompany/app/database.properties
```

#Compile with dependencies

In pom.xml file, add dependencies for mysql, and add build in pom.xml file so that we can build the project with dependencies

Run to install dependencies

```
mvn clean

mvn install
```

Run to see the different dependencies with running with different jar

```
jar -tf target/mycosmetic-app-1.0-SNAPSHOT.jar
jar -tf target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar
jar -tf target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar | grep properties
```

Build the project with dependency:

```
mvn package assembly:single

```

Compile and run the classes with dependency: 

```    
    java -classpath ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \
    com.mycompany.app.CosmeticApp  \
    ./src/main/java/com/mycompany/app/database.properties

```

#Pass flag to compile and run the classes with dependency
The flag "-file" and "-db" indicates using text file / database to store the information

For Text File

```
java -classpath ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \
    com.mycompany.app.CosmeticApp -file
```

For Database

```
java -classpath ./target/mycosmetic-app-1.0-SNAPSHOT-jar-with-dependencies.jar \
    com.mycompany.app.CosmeticApp -db
```

#Connect to Mysql Database
```
 /usr/local/mysql/bin/mysql -u root -p
 
```

Check all the databases created:

```
show databases;
```

Go to the database we want to use and check the tables:

```
use cosmetic;

describe cosmetics;

show tables;
```
Check all the information for a table:

```
 select id, brand, name, category from cosmetics;
 
```

#Commit to Github
Delete the target folder (contains jar file) and classes files(in target folder) before commit to github

```
git status
git rm -r target
```

Add java classes

````
git status

git add *.java

ls

git add src/main/java

git status

git diff --cached

git add src/main/java

````

Commit changes

```
git commit
```
Enter to vim mode, write the commit message

insert the message

```
i
```
Exit the vim mode and save changes in file

```
esc

:wq

```

or

```
git commit -m "update read me"
```

push the changes to github

```
git push origin master:main
git log --oneline
git show
```


