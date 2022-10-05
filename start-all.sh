#!/bin/bash

source ./framework.sh

echo "starting all"
docker-compose --file spring/menu-service/docker-compose-menu.yml \
               --file spring/dining-service/docker-compose-dining.yml \
               --file spring/kitchen-service/docker-compose-kitchen.yml \
	       --file bff/docker-compose.yaml \
               --file spring/gateway/docker-compose-gateway.yml up -d

echo "all services started behind gateway"