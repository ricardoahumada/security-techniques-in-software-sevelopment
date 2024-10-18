# increase memory before running container
sudo sysctl -w vm.max_map_count=262144

# exec compose all
docker compose -f docker-compose-all.yaml up -d
docker compose -f docker-compose-all.yaml ps
docker compose -f docker-compose-all.yaml logs -f
docker compose -f docker-compose-all.yaml down
