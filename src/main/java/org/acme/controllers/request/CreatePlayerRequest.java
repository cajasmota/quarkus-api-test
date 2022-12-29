package org.acme.controllers.request;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record CreatePlayerRequest(

        @NotNull
        @Size(min = 3, max = 50)
        String name,

        @Past
        LocalDate birthdate,

        @NotNull
        @Size(min = 3, max = 50)
        String position,

        @Min(0)
        @Max(99)
        int number,

        @Pattern(regexp = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$")
        String photoURL
) { }
