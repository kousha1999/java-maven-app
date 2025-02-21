FROM amazoncorretto:8-alpine3.17-jre

EXPOSE 8080

COPY /var/jenkins_home/workspace/java-maven-app/target/java-mapen-app-*.jar /usr/app
WORKDIR /usr/app

CMD java -jar java-maven-app-*.jar
