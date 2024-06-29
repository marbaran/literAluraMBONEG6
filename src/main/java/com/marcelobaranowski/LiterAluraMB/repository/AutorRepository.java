package com.marcelobaranowski.LiterAluraMB.repository;

import com.marcelobaranowski.LiterAluraMB.model.AutorDB;
import com.marcelobaranowski.LiterAluraMB.model.DatosAutor;
import com.marcelobaranowski.LiterAluraMB.model.LibroDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AutorRepository extends JpaRepository<AutorDB,Long>{

    Optional<AutorDB> findByNombre(String nombre);

    @Query("SELECT a FROM AutorDB a WHERE a.anioDeNacimiento <= :anio AND a.anioDeFallecimiento >= :anio")
    List<AutorDB> listaAutoresVivos(Integer anio);
}
