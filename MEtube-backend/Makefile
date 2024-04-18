SERVICE_NAME=metube-backend
BUILDER_TAG=$(SERVICE_NAME)-build
IMAGE_TAG=$(SERVICE_NAME)-img
IMAGE_VERSION=latest
default: build

clean:
	rm -rf build

start: clean
    # Building using gradlew
	./gradlew clean build

    # Running using docker compose
	docker compose -f docker-compose-local.yml up --build -d