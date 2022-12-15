package org.acme.dto;

import io.quarkus.mongodb.panache.common.ProjectionFor;
import org.acme.models.Team;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@ProjectionFor(Team.class)
public record TeamDTO(
        @Schema(implementation = String.class, example = "639b94973c64d069f2fbab94", description = "MongoDB ID format")
        ObjectId _id,
        String name,
        String flagUrl
) { }
