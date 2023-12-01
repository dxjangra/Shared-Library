#!/bin/bash

# Define the service unit file content
SERVICE_UNIT_CONTENT="[Unit]
Description=Attendance API Service
After=network.target

[Service]
WorkingDirectory=/home/ubuntu/attendance-api
ExecStartPre=/home/ubuntu/liquibase update --driver-properties-file=liquibase.properties
ExecStartPre=poetry config virtualenvs.create false
ExecStartPre=poetry install --no-root --no-interaction --no-ansi
ExecStart=/usr/local/bin/gunicorn app:app --log-config /home/ubuntu/log.conf -b 0.0.0.0:8080

Restart=always
User=ubuntu
Group=ubuntu

[Install]
WantedBy=multi-user.target"

# Define the service unit file path
SERVICE_UNIT_FILE="/etc/systemd/system/attendance-api.service"

# Create the service unit file
echo "$SERVICE_UNIT_CONTENT" | sudo tee "$SERVICE_UNIT_FILE" > /dev/null

# Reload systemd to apply the new service unit file
sudo systemctl daemon-reload

# Start the service
sudo systemctl start attendance-api

# Enable the service to start at boot
sudo systemctl enable attendance-api

# Output a success message
echo "The Attendance API service has been created and enabled."

# Check the status of the service
sudo systemctl status attendance-api
