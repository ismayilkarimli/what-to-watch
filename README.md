# what-to-watch

SpringBoot app that searches IMDb using OMDb API

## Requirements (skip if you'll use Docker approach)
1. [maven](https://maven.apache.org/download.cgi)
2. Java 10+

## Installation

#### Git:
```bash
git clone https://github.com/ismayilkarimli/what-to-watch $HOME/wtw
```
#### Docker:
```bash
docker pull ismayilkarimli/wtw:25
```

## Usage
#### Git:
```bash
cd $HOME/wtw
mvn spring-boot:run
```
or import it to the IDE of your choice and run from there

#### Docker:
```
docker run -p 8080:8080 ismayilkarimli/wtw:25
```
###### (Ensure that port 8080 is not used)

### Notes:
1. The project is not complete, therefore UI/UX is awful.
2. There are probably bugs somewhere.
