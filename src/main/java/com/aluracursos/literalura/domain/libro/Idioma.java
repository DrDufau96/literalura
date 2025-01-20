package com.aluracursos.literalura.domain.libro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Idioma {
    CH("ch", "Chino"),
    DA("da", "Danés"),
    NL("nl", "Holandés"),
    EN("en", "Inglés"),
    EO("eo", "Esperanto"),
    FI("fi", "Finlandés"),
    FR("fr", "Francés"),
    DE("de", "Alemán"),
    EL("el", "Griego"),
    HU("hu", "Húngaro"),
    IT("it", "Italiano"),
    LA("la", "Latín"),
    PT("pt", "Portugués"),
    ES("es", "Español"),
    SV("sv", "Sueco"),
    TL("tl", "Tagalo");

    private String idioma;
    private String idiomaEnEspanol;


    public static Idioma fromString(String text) {
        if (text != null) {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idioma.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
        }
        throw new IllegalArgumentException("No se encontró ningún idioma con el código: " + text);
    }


    public static Idioma fromEspanol(String text) {
        if (text != null) {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idiomaEnEspanol.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
        }
        throw new IllegalArgumentException("No se encontró ningún idioma con el nombre en español: " + text);
    }


}