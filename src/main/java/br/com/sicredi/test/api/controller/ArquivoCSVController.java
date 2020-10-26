package br.com.sicredi.test.api.controller;

import br.com.sicredi.test.api.model.ArquivoCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class ArquivoCSVController {
    private final String mesagemContaOk = "Conta atualizada com sucesso.";
    private final String mesagemContaErro = "Houve um erro inesperado, conta não atualizada.";

    public List<ArquivoCSV> converterCSVParaListaArquivoCSV(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<ArquivoCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(ArquivoCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<ArquivoCSV> list = csvToBean.parse();
            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    public String retornarMensagemAtualizacaoConta(Boolean atualizada) {
        return atualizada ? mesagemContaOk : mesagemContaErro;
    }
}
