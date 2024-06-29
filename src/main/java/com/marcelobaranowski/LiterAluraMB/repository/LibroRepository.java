package com.marcelobaranowski.LiterAluraMB.repository;

import com.marcelobaranowski.LiterAluraMB.model.DatosIdioma;
import com.marcelobaranowski.LiterAluraMB.model.LibroDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<LibroDB, Long> {

    List<LibroDB> findByIdioma(DatosIdioma idioma);

    Optional<LibroDB> findByTitulo(String titulo);

}

