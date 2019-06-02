# REST-Server as Docker

## Load-balanced using Traefik

```
  $ docker network create traefik
```

```
  $ docker-compose up -d --scale rest=2
```

### Create a new user

```
  $ curl -X POST http://localhost/rest/users -H "Content-Type: application/json" -d '{"username":"mvr", "name":"michael", "surname":"vonrueden", "password"."123456"}' 
```
### Get all users

```
  $ curl -X GET http://localhost/rest/users -H "Content-Type: application/json"
```

### Get a single user

```
  $ curl -X GET http://localhost/rest/users/mvr -H "Content-Type: "application/json"
```

