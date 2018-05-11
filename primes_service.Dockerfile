FROM openjdk:8-jre-alpine
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
ADD java_prime_service/build/libs/primes-*.jar /app/app.jar
