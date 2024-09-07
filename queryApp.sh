#!/bin/bash

BASE_URL="http://localhost:8080"

PATH="/v1/data-filter"

URL="$BASE_URL$PATH"

build_query_string() {
    local query_string=""

    for arg in "$@"; do
        # Each argument should be in the form of key=value
        if [[ "$arg" == *"="* ]]; then
            if [ -z "$query_string" ]; then
                query_string="?$arg"
            else
                query_string="$query_string&$arg"
            fi
        else
            echo "Invalid query parameter: $arg (should be in key=value format)"
            exit 1
        fi
    done

    echo "$query_string"
}

# Build the query string based on passed arguments
QUERY_STRING=$(build_query_string "$@")

# Full URL with query parameters
FULL_URL="$URL$QUERY_STRING"

# Perform the GET request using curl
echo "Sending GET request to: $FULL_URL"
response=$(curl -s -w "\nHTTP Status: %{http_code}\n" "$FULL_URL")

# Print the response from the server
echo "Response:"
echo "$response"