package br.com.sicredi.test.api.endpoint;

import br.com.sicredi.test.api.controller.ContaController;
import br.com.sicredi.test.api.model.Conta;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaEndpoint {

    @PostMapping("/atualizar")
    private void atualizar(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws InterruptedException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        ContaController contaController = new ContaController();
        List<Conta> contas = contaController.converterParaListaConta(file);
        contas = contaController.atualizar(contas);
        String filename = "retorno.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"");
        StatefulBeanToCsv<Conta> writer = new StatefulBeanToCsvBuilder<Conta>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        writer.write(contas);
    }
}
