#!/bin/sh
mkdir -p /data/$HOSTNAME

gfsh start server --name=$HOSTNAME --locators=locator[10334] --dir=/data/$HOSTNAME/ "$@"

while true; do
    sleep 10
  done
done
