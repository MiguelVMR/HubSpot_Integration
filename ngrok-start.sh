#!/bin/bash

sleep 5

NGROK_URL=$(curl --silent --request GET --url "http://ngrok:4040/api/tunnels" | jq -r '.tunnels[0].public_url')

echo "NGROK_URL=${NGROK_URL}" >> /app/.env

java -jar /app/hubspot.jar