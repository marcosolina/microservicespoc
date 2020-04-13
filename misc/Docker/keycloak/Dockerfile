FROM jboss/keycloak:9.0.2

COPY ./realm-export.json /realm-export.json

ENV KEYCLOAK_IMPORT /realm-export.json
ENV KEYCLOAK_MIGRATION_STRATEGY OVERWRITE_EXISTING


#RUN /opt/jboss/keycloak/bin/standalone.sh -Dkeycloak.profile.feature.upload_scripts=enabled -Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=realm-export.json -Dkeycloak.migration.strategy=OVERWRITE_EXISTING
