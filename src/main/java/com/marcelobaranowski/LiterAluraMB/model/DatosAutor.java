package com.marcelobaranowski.LiterAluraMB.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor (
        @JsonAlias("name") String nombreAutor,
        @JsonAlias ("birth_year") Integer anioDeNacimiento,
        @JsonAlias ("death_year") Integer anioDeFallecimiento
) {
}
