package br.com.sicredi.test.api.util;

public class FormatUtil {
    public String somenteNumeros(String texto) {
        return texto.replaceAll("[^0-9]","");
    }
}
