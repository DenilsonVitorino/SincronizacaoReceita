package br.com.sicredi.test.api.controller;

import br.com.sicredi.test.api.model.Conta;
import br.com.sicredi.test.api.service.ReceitaService;
import br.com.sicredi.test.api.util.FormatUtil;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class ContaController {
    public final String mesagemContaOk = "Conta atualizada com sucesso.";
    public final String mesagemContaErro = "Falha ao atualizar conta.";

    private String retornarMensagemAtualizacaoConta(Boolean atualizada) {
        return atualizada ? mesagemContaOk : mesagemContaErro;
    }

    public List<Conta> converterParaListaConta(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Conta> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Conta.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Conta> list = csvToBean.parse();
            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Conta> atualizar(List<Conta> contas) throws InterruptedException {
        ReceitaService receitaService = new ReceitaService();
        FormatUtil formatUtil = new FormatUtil();
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);
            String contaSemSeparador = formatUtil.somenteNumeros(conta.getConta());
            Boolean contaAtualizada = receitaService.atualizarConta(
                    conta.getAgencia(),
                    contaSemSeparador,
                    conta.getSaldo(),
                    conta.getStatus()
            );
            String mensagemResultado = retornarMensagemAtualizacaoConta(contaAtualizada);
            conta.setResultado(mensagemResultado);
        }
        return contas;
    }
}
