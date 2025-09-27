#!/usr/bin/env bash
set -euo pipefail

# Build Docker images for specific microservices using Google Jib
# Prereqs: Maven, JDK 21, and Docker daemon running (for jib:dockerBuild)

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"

build_service() {
  local service_dir="$1"
  local VERSION="$2";
  echo "============================================================"
  echo "Building Jib image for: ${service_dir}"
  echo "============================================================"
  (cd "${ROOT_DIR}/${service_dir}" && mvn versions:set -DnewVersion=${VERSION} -DgenerateBackupPoms=false && mvn -q -DskipTests compile jib:dockerBuild)
}

# Correct order for config dependencies (configserver first is optional for image build,
# but building accounts/cards does not require running services)


VERSION="s15.5"
build_service accounts "${VERSION}"
build_service cards "${VERSION}"
build_service configserver "${VERSION}"
build_service eurekaserver "${VERSION}"
build_service gatewayserver "${VERSION}"
build_service loans "${VERSION}"
build_service message "${VERSION}"

echo "All images built successfully."
