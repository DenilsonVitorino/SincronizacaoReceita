package br.com.sicredi.test.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormatUtilTest {

    @Test
    void somenteNumeros() {
        FormatUtil formatUtil = new FormatUtil();
        String resultadoEsperado = "123456";
        String resultado = formatUtil.somenteNumeros("12345-6");
        assertTrue(resultado.equals(resultadoEsperado));
    }
}