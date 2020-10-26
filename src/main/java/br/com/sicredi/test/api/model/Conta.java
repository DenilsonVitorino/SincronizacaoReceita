package br.com.sicredi.test.api.model;

import com.opencsv.bean.CsvBindByName;

public class Conta {

    @CsvBindByName
    private String agencia;
    @CsvBindByName
    private String conta;
    @CsvBindByName
    private Double saldo;
    @CsvBindByName
    private String status;
    @CsvBindByName
    private String resultado;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
