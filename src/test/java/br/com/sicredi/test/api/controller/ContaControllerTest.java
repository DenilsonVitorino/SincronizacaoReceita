package br.com.sicredi.test.api.controller;

import br.com.sicredi.test.api.model.Conta;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContaControllerTest {

    @Test
    void converterParaListaConta() {
        //Teste nao implementado pois existem parametros recebidos pelo cliente;
    }

    @Test
    void atualizar() throws InterruptedException {
        List<Conta> contas = new ArrayList<>();
        Conta conta = new Conta("0101", "122256", 100.0, "A","");
        contas.add(conta);
        ContaController contaController = new ContaController();
        contas = contaController.atualizar(contas);
        String resultado = contas.get(0).getResultado();
        conta.setResultado(resultado);
        assertTrue(! conta.getResultado().equals(""));
    }
}