#!/bin/sh

# Decode environment variables and write to JSON file
#Google Firestore
echo "$ENCODED_GOOGLE_API_KEY" | base64 -d > /config/api_keys/geofencing_firebase_api_key.json

# Twilio Keys
echo "$ENCODED_TWILIO_KEY" | base64 -d > /config/api_keys/twilio_keys.json
