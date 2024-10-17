# increase memory before running container
sudo sysctl -w vm.max_map_count=262144

# exec compose all
docker compose -f docker-compose-all.yaml up -d
docker compose -f docker-compose-all.yaml ps
docker compose -f docker-compose-all.yaml logs -f
docker compose -f docker-compose-all.yaml down


# access container with prompt
docker exec -it <CONTAINER_NAME> sh

docker exec -it products-service sh

# exec sonarq analysis from inside 
## get the host ip
ip addr show eth0 # get the ip mask
route # get the gateway for the ip mask

/sbin/ip route|awk '/default/ { print $3 }'


## exec analysis
mvn clean verify sonar:sonar -Dsonar.projectKey=ProductsService -Dsonar.host.url=http://<IP>:9001 -Dsonar.login=<token> -Dmaven.test.skip

mvn clean verify sonar:sonar -Dsonar.projectKey=ProductsService -Dsonar.host.url=http://172.18.0.1:9001 -Dsonar.login=sqp_9cb335a23bfa81fbb7f299ca158643ea4b0d7440 -Dmaven.test.skip