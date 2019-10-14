# `graalvm-quarkus-micronaut-springboot`
## `> producer-consumer > micronaut`

The goal of this project is to implement two [`Micronaut`](https://micronaut.io/) applications: one that _produces_ messages to a [`Kafka`](https://kafka.apache.org/) topic and another that _consumes_ those messages. Besides, we will use `GraalVM`'s `native-image` tool to generate the native image of the applications.

## Applications

### producer-api

`Micronaut` Web Java application that exposes one endpoint at which users can post `news`. Once a request is made, `producer-api` pushes a message about the `news` to `Kafka`.

It has the following endpoint:
```
POST /api/news {"source": "...", "title": "..."}
```

### consumer-api

`Micronaut` Web Java application that listens to messages (published by the `producer-api`) and logs it.

## Running applications

> Note: `Kafka`, `Zookeeper` and other containers present in `docker-compose.yml` file must be up and running as explained [here]()

### `producer-api`

### Development Mode

Open a terminal and inside `graalvm-quarkus-micronaut-springboot/producer-consumer/micronaut/producer-api` folder run
```
./gradlew run
```

### Docker in JVM Mode

Before building the docker image, you need to package the application `jar` file. So, in a terminal and inside
`graalvm-quarkus-micronaut-springboot/producer-consumer/micronaut/producer-api`  folder run
```
./gradlew clean assemble
```

Then, build the image with the script
```
./docker-build.sh
```

Finally, run the container using
```
docker run -d --rm --name micronaut-producer-api-jvm --network producer-consumer_default -e KAFKA_HOST=kafka -e ZIPKIN_HOST=zipkin -p 9102:8080 docker.mycompany.com/micronaut-producer-api-jvm:1.0.0
```

### Docker in Native Mode

Before building the docker image, you need to package the application `jar` file. So, in a terminal and inside
`graalvm-quarkus-micronaut-springboot/producer-consumer/micronaut/producer-api` folder run
```
./gradlew clean assemble
```

Then, build the image with the script
```
./docker-build.sh native
```

Finally, run the container using
```
docker run -d --rm --name micronaut-producer-api-native --network producer-consumer_default -e KAFKA_HOST=kafka -e ZIPKIN_HOST=zipkin -p 9103:8080 docker.mycompany.com/micronaut-producer-api-native:1.0.0
```

### `consumer-api`

### Development Mode

Open a terminal and inside `graalvm-quarkus-micronaut-springboot/producer-consumer/micronaut/consumer-api` folder run
```
./gradlew run
```

### Docker in JVM Mode

Before building the docker image, you need to package the application `jar` file. So, in a terminal and inside
`graalvm-quarkus-micronaut-springboot/producer-consumer/micronaut/consumer-api`  folder run
```
./gradlew clean assemble
```

Then, build the image with the script
```
./docker-build.sh
```

Finally, run the container using
```
docker run -d --rm --name micronaut-consumer-api-jvm --network producer-consumer_default -e KAFKA_HOST=kafka -e ZIPKIN_HOST=zipkin -p 9107:8080 docker.mycompany.com/micronaut-consumer-api-jvm:1.0.0
```

### Docker in Native Mode

Before building the docker image, you need to package the application `jar` file. So, in a terminal and inside
`graalvm-quarkus-micronaut-springboot/producer-consumer/micronaut/consumer-api` folder run
```
./gradlew clean assemble
```

Then, build the image with the script
```
./docker-build.sh native
```

Finally, run the container using
```
docker run -d --rm --name micronaut-consumer-api-native --network producer-consumer_default -e KAFKA_HOST=kafka -e ZIPKIN_HOST=zipkin -p 9108:8080 docker.mycompany.com/micronaut-consumer-api-native:1.0.0
```

## Simple Test

- Posting a news
> I am using [HTTPie](https://httpie.org/) 
```
http :9102/api/news source="Micronaut Blog" title="Micronaut Framework"
http :9103/api/news source="Micronaut Blog" title="Micronaut Framework & GraalVM"
```

- See `producer` and `consumer` Docker logs

## Shutdown

To stop and remove application containers run
```
docker stop micronaut-producer-api-jvm micronaut-producer-api-native micronaut-consumer-api-jvm micronaut-consumer-api-native
```