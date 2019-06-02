#!/bin/bash

curl -X POST http://localhost/rest/users -H "Content-Type: application/json" -d '{"username":"mvr", "name":"michael", "surname":"vonrueden", "password":"123456"}'
echo
