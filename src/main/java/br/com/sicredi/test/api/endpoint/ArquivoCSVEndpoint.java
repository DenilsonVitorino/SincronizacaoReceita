package br.com.sicredi.test.api.endpoint;

import br.com.sicredi.test.api.controller.ArquivoCSVController;
import br.com.sicredi.test.api.model.ArquivoCSV;
import br.com.sicredi.test.api.service.ReceitaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/arquivocsv")
public class ArquivoCSVEndpoint {

    @PostMapping("/upload")
    private void upload(@RequestParam("file") MultipartFile file) throws InterruptedException {
        ArquivoCSVController arquivoCSVController = new ArquivoCSVController();
        List<ArquivoCSV> list = arquivoCSVController.converterCSVParaListaArquivoCSV(file);
        ReceitaService receitaService = new ReceitaService();
        for (int i = 0; i < list.size(); i++) {
            ArquivoCSV arquivoCSV = list.get(i);
            Boolean contaAtualizada = receitaService.atualizarConta(
                    arquivoCSV.getAgencia(),
                    arquivoCSV.getConta(),
                    arquivoCSV.getSaldo(),
                    arquivoCSV.getStatus());
            String mensagemResultado = arquivoCSVController
                    .retornarMensagemAtualizacaoConta(contaAtualizada);
            arquivoCSV.setResultado(mensagemResultado);
        }

    }
}
