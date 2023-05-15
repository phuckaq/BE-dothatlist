# Define variables
DOCKER_COMPOSE_FILE := docker-compose.dev.yml
DOCKER_COMPOSE := docker-compose -f $(DOCKER_COMPOSE_FILE)

.PHONY: build up down debug

# Build Docker images
build:
	$(DOCKER_COMPOSE) build --no-cache

# Start the application
up:
	$(DOCKER_COMPOSE) up -d

# Stop the application
down:
	$(DOCKER_COMPOSE) down





