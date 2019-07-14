# Accounting

### Project description 


### Run the project

Run in the project directory:
```
docker-compose up -d
```

### Build the Project manually
To run the project you need to have installed [Docker](http://docker.com) with Docker-compose.  
To run the mysql DB first: 
```
docker-compose up -d db
```

Run in the project directory:
```
docker build -t accounting .
```
It will use 2 step-build in Dockerfile and create docker image accounting:lastest. 
1st step pulls maven as a container and builds project, 
2nd step uses jar file, prepared in maven 1st step. 

### Tests 
Tests are removed in dockerfile during build with -Dmaven.test.skip=true to compile with no DB
