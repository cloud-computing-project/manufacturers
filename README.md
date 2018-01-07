# RSO: Manufacturers microservice

## Prerequisites

```bash
docker run -d --name manufacturers -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=manufacturer -p 5435:5435 postgres:latest
```

## Run application in Docker

```bash
docker run -p 8080:8080 -e KUMULUZEE_CONFIG_ETCD_HOSTS=http://192.168.99.100:2379 ejmric/manufacturers
```