# How to execute this


Configure JAVA_HOME


```
Display system information regarding java environment and derby version:
 java -jar $DERBY_HOME/lib/derbyrun.jar sysinfo
````

Set up the path:

```
 echo ~/.zshrc
 vim ~/.zshrc
 source ~/.zshrc 

 export DERBY_HOME=/Users/serenapang/Downloads/db-derby-10.15.2.0-bin
 export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home
 
```
Depends on the url we put in the properties file, we can determine if it's running in an embedded system or in a server

Connect to server:

```
$DERBY_HOME/BIN/startNetworkServer
```
Check the version:

```
$DERBY_HOME/bin/ij
```

Create Database:

```
 connect 'jdbc:derby:bigjavadb;create=true';
```

Connect to database:

```
connect 'jdbc:derby:bigjavadb' user 'test' password 'test123';
```

Once that the database is created, connect using:

```
$DERBY_HOME/bin/ij
connect 'jdbc:derby:bigjavadb';
```
#FOR REFERENCE READ AND UNDERSTAND AT:

```
https://www.codejava.net/java-se/jdbc/connect-to-apache-derby-java-db-via-jdbc
```

Compile and execute class: Go to the package where all the classes are and execute:

```
javac *.java
```

Then go to where you can see src folder and execute:

```
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:.  \
          org.bigjava.ch24.rationaldatabases.TestDB \
           ./org/bigjava/ch24/rationaldatabases/derby.properties
           
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:.  \
          org.bigjava.ch24.rationaldatabases.InvoiceEntry\
           ./org/bigjava/ch24/rationaldatabases/derby.properties
```

#To compile and execute sql quries from a file:
 
```
connect to server:   $DERBY_HOME/bin/startNetworkServer

javac org/bigjava/ch24/rationaldatabases/*.java
 
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbynet.jar:.  \
          org.bigjava.ch24.rationaldatabases.ExecSQL \
           ./org/bigjava/ch24/rationaldatabases/derby.properties ./org/bigjava/ch24/rationaldatabases/Product.sql
           
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbynet.jar:.  \
          org.bigjava.ch24.rationaldatabases.ExecSQL \
           ./org/bigjava/ch24/rationaldatabases/derby.properties ./org/bigjava/ch24/rationaldatabases/Invoice.sql
           
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbynet.jar:.  \
          org.bigjava.ch24.rationaldatabases.ExecSQL \
           ./org/bigjava/ch24/rationaldatabases/derby.properties ./org/bigjava/ch24/rationaldatabases/Customer.sql
           
java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbynet.jar:.  \
          org.bigjava.ch24.rationaldatabases.ExecSQL \
           ./org/bigjava/ch24/rationaldatabases/derby.properties ./org/bigjava/ch24/rationaldatabases/LineItem.sql
           
```

