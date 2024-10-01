package com.springbatch.demonstrativoorcamentario.writer;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

@Component
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {
    Double totalGeral = 0.0;
    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("\n");
        writer.append(String.format("\t\t\t\t\t\t\t  Total: %s\n", NumberFormat.getCurrencyInstance().format(totalGeral)));
        writer.append(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s\n", "fkyew6868fewjfhjjewf"));
    }

    // Listener é uma forma que o Spring fornece de captar evento para que possamos tratar
    // mesmo usando a anotacao temos que registrar esse listener no Step para ele ser processado
    @BeforeWrite
    public void beforeWrite(Chunk<GrupoLancamento> chunk) { // assinatura obrigatoria dessa forma
        totalGeral += chunk.getItems()
                .stream()
                .mapToDouble(GrupoLancamento::getTotal)
                .sum();
    }
}
