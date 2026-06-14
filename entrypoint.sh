#!/bin/sh
set -e

# If JDBC_DATABASE_URL not set, build it from DATABASE_URL ensuring the jdbc: prefix
if [ -z "${JDBC_DATABASE_URL}" ]; then
  if [ -n "${DATABASE_URL}" ]; then
    # If DATABASE_URL contains credentials (user:pass@host), extract them and set DATABASE_USER/PASSWORD
    if echo "${DATABASE_URL}" | grep -q "@"; then
      # strip scheme (e.g. jdbc:postgresql:// or postgresql://)
      scheme=$(echo "${DATABASE_URL}" | sed -E 's#^([a-zA-Z0-9+.-]+:)//.*#\1#')
      rest=$(echo "${DATABASE_URL}" | sed -E 's#^[a-zA-Z0-9+.-]+://(.*)#\1#')
      userpass=$(echo "${rest}" | sed -E 's#^(.*)@.*#\1#')
      hostrest=$(echo "${rest}" | sed -E 's#^[^@]+@(.*)#\1#')
      user=$(echo "${userpass}" | cut -d: -f1)
      pass=$(echo "${userpass}" | cut -d: -f2-)
      if [ -n "${user}" ] && [ -z "${DATABASE_USER}" ]; then
        export DATABASE_USER="${user}"
      fi
      if [ -n "${pass}" ] && [ -z "${DATABASE_PASSWORD}" ]; then
        export DATABASE_PASSWORD="${pass}"
      fi
      # rebuild DATABASE_URL without credentials
      if echo "${scheme}" | grep -q "^jdbc:"; then
        export DATABASE_URL="${scheme}//${hostrest}"
      else
        export DATABASE_URL="jdbc:${scheme}//${hostrest}"
      fi
    fi
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

# Log what we will use (mask password when printing)
if [ -n "${JDBC_DATABASE_URL}" ]; then
  MASKED=$(echo "${JDBC_DATABASE_URL}" | sed -E 's#(://[^:]+:)[^@]*@#\1***@#')
  echo "[entrypoint] JDBC_DATABASE_URL=${MASKED}"
else
  echo "[entrypoint] JDBC_DATABASE_URL is not set"
fi

# Also show whether DATABASE_URL, DATABASE_USER and DATABASE_PASSWORD are present (don't print secrets)
if [ -n "${DATABASE_URL}" ]; then
  echo "[entrypoint] DATABASE_URL is set"
fi
if [ -n "${DATABASE_USER}" ]; then
  echo "[entrypoint] DATABASE_USER is set"
fi
if [ -n "${DATABASE_PASSWORD}" ]; then
  echo "[entrypoint] DATABASE_PASSWORD is set"
fi

# Start the application with the production profile (or respect SPRING_PROFILES_ACTIVE)
exec java -Dserver.port=${PORT} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} -jar /app/app.jar

