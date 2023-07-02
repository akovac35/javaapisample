ARG BUILD_IMAGE=''
ARG RUNTIME_IMAGE=''

FROM ${BUILD_IMAGE} AS builder

RUN apk add --no-cache \
    maven

WORKDIR /app
COPY pom.xml /app
COPY src /app/src

RUN mvn clean package

FROM ${RUNTIME_IMAGE} AS runner
ARG UID=1000
ARG USER=webapi-user

RUN apk add --no-cache icu-libs krb5-libs libgcc libintl libssl1.1 libstdc++ zlib

WORKDIR /app

RUN addgroup --system "$USER" && \
    adduser --disabled-password --gecos "" --ingroup "$USER" --uid "$UID" --system "$USER" && \
    chown -R $UID:$UID /app

WORKDIR /app

COPY --chown=$UID:$UID --from=builder /app/target/JavaSpringBootApiSample-1.0.0.jar /app

USER $USER
EXPOSE 8080
CMD ["java", "-jar", "JavaSpringBootApiSample-1.0.0.jar"]