#!/bin/sh
set -e

# If JDBC_DATABASE_URL not set, build it from DATABASE_URL ensuring the jdbc: prefix
if [ -z "${JDBC_DATABASE_URL}" ]; then
  if [ -n "${DATABASE_URL}" ]; then
    case "${DATABASE_URL}" in
      jdbc:*)
        export JDBC_DATABASE_URL="${DATABASE_URL}"
        ;;
      postgresql:/*)
        # unlikely case
        export JDBC_DATABASE_URL="jdbc:${DATABASE_URL}"
        ;;
      postgresql://*)
        export JDBC_DATABASE_URL="jdbc:${DATABASE_URL}"
        ;;
      *)
        # fallback: assume it's already a jdbc URL or leave empty
        export JDBC_DATABASE_URL="${DATABASE_URL}"
        ;;
    esac
  fi
fi

# Export PORT default 8080 if not set
if [ -z "${PORT}" ]; then
  export PORT=8080
fi

# Start the application with the production profile (or respect SPRING_PROFILES_ACTIVE)
exec java -Dserver.port=${PORT} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} -jar /app/app.jar

