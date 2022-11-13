# ProPet Service API

![Docker Workflow](https://github.com/propet-tech/propet-service/actions/workflows/docker-image.yml/badge.svg)

Backend do Sistema ProPet

## Docker Image

Link: [ProPet Service](https://hub.docker.com/r/deividrx/propet-service)

> Por padrão o container irá usar os seguintes valores para variável de ambiente:
>
> `POSTGRES_PORT=5432`
>
> `POSTGRES_USER=postgres`
>
> `POSTGRES_DB=postgres`

## Como usar

> ℹ️ o colchetes devem ser butituitos pelos valores desejados. Exemplo:
> `POSTGRES_IP=123`

### Simples

```bash
docker run -d \
        -e POSTGRES_PASSWD={} 
        -e POSTGRES_IP={}
        propet-service:latest
```

### Completa

```bash
docker run -d \
        -e POSTGRES_IP={} \
        -e POSTGRES_PORT={} \
        -e POSTGRES_PASSWD={} \
        -e POSTGRES_USER={} \
        -e POSTGRES_DB={} \
        propet-service:latest
```
