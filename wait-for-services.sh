#!/bin/bash
set -e

host_rabbitmq=$1
host_mongodb=$2
shift 2
cmd="$@"

until nc -z "$host_rabbitmq" 5672; do
  echo "RabbitMQ is unavailable - sleeping"
  sleep 1
done

until nc -z "$host_mongodb" 27017; do
  echo "MongoDB is unavailable - sleeping"
  sleep 1
done

>&2 echo "RabbitMQ and MongoDB are up - executing command"
exec $cmd