#!/usr/bin/env bash
set -euo pipefail

# Scale down selected deployments to zero replicas.
# Usage:
#   ./bash-scale-down.sh                  # uses current kubectl context & namespace
#   ./bash-scale-down.sh -n <namespace>   # specify namespace
#
# Example:
#   ./bash-scale-down.sh -n default

NAMESPACE_FLAG=""
if [[ ${1:-} == "-n" && -n ${2:-} ]]; then
  NAMESPACE_FLAG="-n $2"
fi

qt=0
default_sleep=20

# Define sleep times for specific deployments
declare -A sleep_times=(
    ["redis"]=5
    ["rabbit"]=10
    ["keycloak"]=15
    ["configserver-deployment"]=25
    ["eurekaserver-deployment"]=20
    ["cards-deployment"]=15
    ["loans-deployment"]=15
    ["accounts-deployment"]=35
    ["gatewayserver-deployment"]=20
    ["message-deployment"]=1
)



deployments=(
    "redis"
    "rabbit"
    "keycloak"
    "configserver-deployment"
    "eurekaserver-deployment"
    "cards-deployment"
    "loans-deployment"
    "accounts-deployment"
    "gatewayserver-deployment"
    "message-deployment"
)

for deployment in "${deployments[@]}"; do
    echo "Scaling down deployment: $deployment"
    kubectl scale deployment "$deployment" --replicas=${qt} ${NAMESPACE_FLAG}
    # Get deployment-specific sleep time or use default
    sleep_time=${sleep_times[$deployment]:-$default_sleep}
    # Override with 1s if qt is 0
    [[ $qt -eq 0 ]] && sleep_time=3
    echo "Waiting ${sleep_time} seconds before next deployment scale ..."
    sleep ${sleep_time}
done

echo "All listed deployments scaled to 0 replicas."