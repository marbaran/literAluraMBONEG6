package com.marcelobaranowski.LiterAluraMB.service;

public interface IConvierteDatos {
    <T> T obtenerDatosLibros (String json, Class <T> clase);
}
