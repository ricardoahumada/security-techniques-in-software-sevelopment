# pull images
docker pull ghcr.io/zaproxy/zaproxy:stable
docker pull zaproxy/zap-stable

# run container
docker run --detach --name zap -u zap -v "$(pwd)/reports":/zap/reports/:rw  -i zaproxy/zap-stable zap.sh -daemon -host 0.0.0.0 -port 8080  -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true  -config api.disablekey=true

# access panel
localhost:8080/zap

# try scan http://vapi.apisec.ai/vapi

# For scanning host services user inner host IP:
## access
docker exec -it <CONTAINER_NAME> sh
## extracted IP 
route #. E.g. 172.17.0.1
## use this IP for scan

# Fuzzing
- Add site
- Right click > attack > fuzz
- Add and/or select param(s) value to fuzz >  add (note that the param name is defined) > add
- add > Add payloads from txt > ok
- start fuzzing

