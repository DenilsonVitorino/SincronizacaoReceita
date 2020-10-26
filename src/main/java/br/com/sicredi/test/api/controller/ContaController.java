package br.com.sicredi.test.api.controller;

import br.com.sicredi.test.api.model.Conta;
import br.com.sicredi.test.api.service.ReceitaService;
import br.com.sicredi.test.api.util.FormatUtil;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.Format;
import java.util.List;

public class ContaController {
    private final String mesagemContaOk = "Conta atualizada com sucesso.";
    private final String mesagemContaErro = "Falha ao atualizar conta.";

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

    public void alimentarResponse(HttpServletResponse response, List<Conta> list) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        String filename = "retorno.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"");
        StatefulBeanToCsv<Conta> writer = new StatefulBeanToCsvBuilder<Conta>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        writer.write(list);
    }

    public void atualizar(List<Conta> contas) throws InterruptedException {
        ReceitaService receitaService = new ReceitaService();
        FormatUtil formatUtil = new FormatUtil();
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);
            String contaSemSeparador = formatUtil.somenteNumeros(conta.getConta());
            Boolean contaAtualizada = receitaService.atualizarConta(
                    conta.getAgencia(),
                    contaSemSeparador,
                    conta.getSaldo(),
                    conta.getStatus());
            String mensagemResultado = retornarMensagemAtualizacaoConta(contaAtualizada);
            conta.setResultado(mensagemResultado);
        }
    }
}
