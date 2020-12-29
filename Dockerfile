FROM openjdk:11
VOLUME /tmp
ADD ./target/base-0.0.1-SNAPSHOT.jar base.jar
ENTRYPOINT ["java","-jar","/hr-worker.jar"]