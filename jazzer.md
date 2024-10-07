# Manual
## simple example

javac DivisionExample.java
jar -cf DivisionExample.jar DivisionExample.class
javac -cp ~/workspace/tools/jazzer/jazzer_standalone.jar:DivisionExample.jar DivisionExampleFuzzer.java
jar -cf DivisionExampleFuzzer.jar DivisionExampleFuzzer.class


~/workspace/tools/jazzer/jazzer --cp=DivisionExample.jar:DivisionExampleFuzzer.jar --target_class=DivisionExampleFuzzer

~/workspace/tools/jazzer/jazzer --cp=DivisionExample.jar --autofuzz=DivisionExample::divide --autofuzz_ignore=java.lang.IllegalArgumentException  -max_total_time=5




## maven projects example
### DivisionExample
mvn clean install
### DivisionExampleFuzzer
mvn clean install

~/workspace/tools/jazzer/jazzer --cp=DivisionExample-1.0-SNAPSHOT.jar:DivisionExampleFuzzer-1.0-SNAPSHOT.jar --target_class=com.banana.DivisionExampleFuzzer


~/workspace/tools/jazzer/jazzer --cp=DivisionExample-1.0-SNAPSHOT.jar --autofuzz=com.banana.DivisionExample::divide --autofuzz_ignore=java.lang.IllegalArgumentException  -max_total_time=5


# products service example
TO-DO

# Docker
## download image
docker pull cifuzz/jazzer:latest

## look into image
docker run -it --entrypoint /bin/sh cifuzz/jazzer


## fuzz jsoup
### API: https://jsoup.org/apidocs/org/jsoup/Jsoup.html
### maven: https://mvnrepository.com/artifact/org.jsoup/jsoup/1.14.1
docker run  -v $(pwd):/fuzzing  -it cifuzz/jazzer-autofuzz  org.jsoup:jsoup:1.14.1  "org.jsoup.Jsoup::parse(java.lang.String)"

## fuzz a jar
### '$(pwd):/fuzzing' jars will be copies to fuzzing.

docker run -v $(pwd):/fuzzing -it cifuzz/jazzer --cp=DivisionExample.jar  --autofuzz=DivisionExample::divide --autofuzz_ignore=java.lang.IllegalArgumentException  -max_total_time=5

docker run -v $(pwd):/fuzzing -it cifuzz/jazzer --cp=DivisionExample-1.0-SNAPSHOT.jar  --autofuzz=com.banana.DivisionExample::divide --autofuzz_ignore=java.lang.IllegalArgumentException  -max_total_time=5

