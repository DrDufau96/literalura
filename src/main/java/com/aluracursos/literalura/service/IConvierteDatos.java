package com.aluracursos.literalura.service;


import java.util.List;

public interface IConvierteDatos {

    <T> T obtenerLosDatos(String json, Class<T> clase);

    <T> List<T> obtenerLista(String json, Class<T> clase);
}