package br.com.sicredi.test.api.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReceitaServiceTest {

    @Test
    void atualizarConta() throws InterruptedException {
        ReceitaService receitaService = new ReceitaService();
        Boolean resultado = receitaService.atualizarConta(
                "0101",
                "122256",
                100.0,
                "A"
        );
        assertTrue(resultado);
    }
}