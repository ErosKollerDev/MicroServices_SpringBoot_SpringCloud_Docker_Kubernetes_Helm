#!/usr/bin/env bash
set -euo pipefail

# Build Docker images for specific microservices using Google Jib
# Prereqs: Maven, JDK 21, and Docker daemon running (for jib:dockerBuild)

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"

build_service() {
  local service_dir="$1"
  echo "============================================================"
  echo "Building Jib image for: ${service_dir}"
  echo "============================================================"
  (cd "${ROOT_DIR}/${service_dir}" && mvn versions:set -DnewVersion=s14.1 -DgenerateBackupPoms=false && mvn -q -DskipTests compile jib:dockerBuild)
}

# Correct order for config dependencies (configserver first is optional for image build,
# but building accounts/cards does not require running services)

build_service accounts
build_service cards
build_service configserver
build_service eurekaserver
build_service gatewayserver
build_service loans
build_service message

echo "All images built successfully."
