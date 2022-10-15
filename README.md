# ProPet Service API

Backend do Sistema ProPet

## Docker Image

Link: [ProPet Service](https://hub.docker.com/r/deividrx/propet-service)

### Como usar

```bash
docker pull deividrx/propet-service
docker run -d --network host \
        -e POSTGRES_IP={} \
        -e POSTGRES_PORT={} \
        -e POSTGRES_PASSWD={} \
        -e POSTGRES_USER={} \
        -e POSTGRES_DB={} \
        propet-service:latest
```
