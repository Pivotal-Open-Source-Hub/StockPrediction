#!/bin/bash

SERVER=`docker ps |grep -i startServer | awk '{print $1}'`

echo $SERVER

sed -i '' "s/HOSTNAME/${SERVER}/g" setup.gfsh  

docker exec -t $SERVER gfsh run --file=data/setup.gfsh



