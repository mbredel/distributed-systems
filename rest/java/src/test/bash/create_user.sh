#!/bin/bash

##
## Some variables.
##
HOST="localhost"
PORT="4567"

NAME="michael"
SURNAME="bredel"
USERNAME="mbredel"
PASSWORD="123456"

##
## Parse the command-line arguments.
##
OPTS=`getopt -o n:s:u:p:H:P:h --l name:,surname:,usernmae:,password:,host:,port:,help -n 'create_user.sh' -- "$@"`
if [ $? != 0 ] ; then echo "Failed parsing options." >&2 ; exit 1 ; fi
eval set -- "$OPTS"
while true; do
  case "$1" in
    -n|--name)
      case "$2" in
        *) NAME=$2; shift 2 ;;
      esac ;;
    -s|--surname)
      case "$2" in
        *) SURNAME=$2; shift 2 ;;
      esac ;;
    -u|--username)
      case "$2" in
        *) USERNAME=$2; shift 2 ;;
      esac ;;
    -p|--password)
      case "$2" in
        *) PASSWORD=$2; shift 2 ;;
      esac ;;
    -H|--host)
      case "$2" in
        *) HOST=$2; shift 2 ;;
      esac ;;
    -P|--port)
      case "$2" in
        *) PORT=$2; shift 2 ;;
      esac ;;
    -h|--help)
      echo "Usage:"
      echo "  create_user.sh [-n <NAME>] [-s <SURNAME>] [-u <USERNAME>] [-p <PASSWORD>] [-H <HOST>] [-P <PORT>]"
      shift ;;
    --) shift; break ;;
    *) break ;;
  esac
done

echo ${USERNAME}

##
## Modify the json template.
##
sed -e "s/%NAME%/${NAME}/g" -e "s/%SURNAME%/${SURNAME}/g" -e "s/%USERNAME%/${USERNAME}/g" -e "s/%PASSWORD%/${PASSWORD}/g" ../resources/user.json.template > /tmp/user.json

##
## Get users from the REST service.
##
curl -i -X POST -H "Content-Type: application/json" -d @/tmp/user.json http://${HOST}:${PORT}/users

echo