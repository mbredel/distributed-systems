# REST-Server as Docker

## Start as single Docker container


```
  $ docker build -t mbredel/rest/rest:latest .
```

```
  $ docker run -p 80:4567 mbredel/rest/rest
```

Please note, that without the Traefik reverse proxy,
the URL is slightly different, i.e. without the
'_/rest/api/v1_' part.

## Load-balanced using Traefik

```
  $ docker network create traefik
```

```
  $ docker-compose up -d --scale rest=2
```

### Create a new user

```
  $ curl -X POST http://localhost/rest/users -H "Content-Type: application/json" -d '{"username":"mvr", "name":"michael", "surname":"vonrueden", "password":"123456"}' 
```
### Get all users

```
  $ curl -X GET http://localhost/rest/users -H "Content-Type: application/json"
```

### Get a single user

```
  $ curl -X GET http://localhost/rest/users/mvr -H "Content-Type: "application/json"
```

