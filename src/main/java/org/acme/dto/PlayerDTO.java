package org.acme.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Data
public class PlayerDTO {

    @Schema(implementation = String.class, example = "639b94973c64d069f2fbab94", description = "MongoDB ID format")
    ObjectId _id;
    String name;
    LocalDate birthdate;
    String position;
    int number;
    String photoURL;
}
