#!/bin/bash

##
## Some variables.
##
HOST="localhost"
PORT="4567"

USERNAME="mbredel"

##
## Parse the command-line arguments.
##
OPTS=`getopt -o H:P:h --l host:,port:,help -n 'get_user.sh' -- "$@"`
if [ $? != 0 ] ; then echo "Failed parsing options." >&2 ; exit 1 ; fi
eval set -- "$OPTS"
while true; do
  case "$1" in
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
      echo "  get_user.sh [-H <HOST>] [-P <PORT>] <USERNAME>"
      shift ;;
    --) shift; break ;;
    *) break ;;
  esac
done

##
## Check for remaining argument.
##
if [ $# -eq 1 ]; then
    USERNAME=$1;
fi

##
## Get users from the REST service.
##
curl -i -X GET http://${HOST}:${PORT}/users/${USERNAME}

echo
