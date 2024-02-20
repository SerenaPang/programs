
#Build the Project:
At the pom.xml level

```
mvn package assembly:single

```



#Shows all the denpendencies in the jar

```
jar -tvf target/mycat-app-2.0-RELEASE-jar-with-dependencies.jar 

```

#Compile and Execute the file

```
java -classpath ./target/mycat-app-2.0-RELEASE-jar-with-dependencies.jar \
    com.mycompany.app.TestCatDB  \
    ./src/main/java/com/mycompany/app/database.properties
    
```

#How to connect to Derby at:

```
https://www.codejava.net/java-se/jdbc/connect-to-apache-derby-java-db-via-jdbc
```

#Connect to server:

```
$DERBY_HOME/BIN/startNetworkServer
```
If server is not responding, kill the process and restart it:

ps -fea  finds all the processes, and we just need to grab the ones relates to derby

```
ps -fea | grep derby
```

If 501 28757 19344 is the process code, take the process id:

```
kill -9 28757 

kill -9 30578

```

#Check the version:

```
$DERBY_HOME/bin/ij
```

#Create Database:

```
 connect 'jdbc:derby:catjavadb;create=true';
```

#Connect to database:

```
connect 'jdbc:derby:catjavadb' user 'test' password 'test123';
```

#Once that the database is created, connect using:

```
$DERBY_HOME/bin/ij
connect 'jdbc:derby:catjavadb';
```




#Execute the Query in the sql file:

```
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbynet.jar:.  \
          org.bigjava.ch24.rationaldatabases.practice.ExecuteSQL \
           ./org/bigjava/ch24/rationaldatabases/practice/database.properties ./org/bigjava/ch24/rationaldatabases/practice/Cats.sql
```


