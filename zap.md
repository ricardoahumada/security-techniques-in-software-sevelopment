# pull images
docker pull ghcr.io/zaproxy/zaproxy:stable
docker pull zaproxy/zap-stable

# run container
docker run --detach --name zap -u zap -v "$(pwd)/reports":/zap/reports/:rw  -i zaproxy/zap-stable zap.sh -daemon -host 0.0.0.0 -port 8080  -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true  -config api.disablekey=true

# access panel
localhost:8080/zap

# try scan http://vapi.apisec.ai/vapi


# Quick scan
docker exec  