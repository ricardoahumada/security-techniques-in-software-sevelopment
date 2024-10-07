# simple example
javac DivisionExample.java
jar -cf DivisionExample.jar DivisionExample.class
javac -cp jazzer_standalone.jar:DivisionExample.jar DivisionExampleFuzzer.java
jar -cf DivisionExampleFuzzer.jar DivisionExampleFuzzer.class


# download image
docker pull cifuzz/jazzer:latest


# fuzz jsoup
## API: https://jsoup.org/apidocs/org/jsoup/Jsoup.html
## maven: https://mvnrepository.com/artifact/org.jsoup/jsoup/1.14.1
docker run  -v $(pwd):/fuzzing  -it cifuzz/jazzer-autofuzz  org.jsoup:jsoup:1.14.1  "org.jsoup.Jsoup::parse(java.lang.String)"

# fuzz example
## '$(pwd):/fuzzing' Should contain the jars.
docker run -v $(pwd):/fuzzing -it cifuzz/jazzer --cp=project.jar:fuzzers.jar --target_class=com.example.ExampleFuzzTarget

docker run -v $(pwd):/fuzzing -it cifuzz/jazzer --cp=ProductsService-0.0.1-SNAPSHOT.jar:fuzzers.jar --target_class=com.microcompany.productsservice.controller.ProductServiceController
