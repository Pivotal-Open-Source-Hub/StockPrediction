#!/bin/sh

if [ -z "$SERVER_NAME" ]; then 
	echo "SERVER_NAME is unset"; 
        exit 1;
fi

mkdir -p /data/$SERVER_NAME

gfsh start server --name=$SERVER_NAME --locators=locator[10334] --dir=/data/$SERVER_NAME/ "$@"

while true; do
    sleep 10
  done
done
