FROM gradle:8.1-jdk20 AS builder

COPY --chown=gradle:gradle . /build/

WORKDIR /build

RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:20-jre

COPY --from=builder /build/build/libs/*.jar server.jar
COPY image/bin/start.sh /start.sh

ENV MEMORY="512M"
ENTRYPOINT ["/start.sh"]
