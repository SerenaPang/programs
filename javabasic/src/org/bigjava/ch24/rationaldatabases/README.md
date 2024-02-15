# How to execute this


Configure JAVA_HOME


```
Display system information regarding java environment and derby version:
 java -jar $DERBY_HOME/lib/derbyrun.jar sysinfo
````

```
Set up the path:

 export DERBY_HOME=/Users/serenapang/Downloads/db-derby-10.15.2.0-bin
 export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home
 
 vim ~/.zshrc
 source ~/.zshrc 

```

```
Connect to server:
$DERBY_HOME/BIN/startNetworkServer

Check the version:
$DERBY_HOME/bin/ij

Create Database:
ij> connect 'jdbc:derby:bigjavadb;create=true';

Connect to database:
ij> connect 'jdbc:derby:bigjavadb' user 'test' password 'test123';
```


```
Compile and execute class:

Go to the package where all the classes are and execute:
javac *.java

Then go to where you can see src folder and execute:

java -classpath /Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derby.jar:/Users/serenapang/Downloads/db-derby-10.15.2.0-bin/lib/derbyclient.jar:.  \
          org.bigjava.ch24.rationaldatabases.TestDB \
           ./org/bigjava/ch24/rationaldatabases/derby.properties
```