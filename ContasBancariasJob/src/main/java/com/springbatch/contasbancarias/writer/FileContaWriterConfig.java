package com.springbatch.contasbancarias.writer;

import com.springbatch.contasbancarias.dominio.Conta;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FileContaWriterConfig {

    @Bean
    public FlatFileItemWriter<Conta> clienteValidoWriter() {
        return new FlatFileItemWriterBuilder<Conta>()
                .name("clienteValidoWriter")
                .resource(new FileSystemResource("./ContasBancariasJob/files/contas.txt"))
                .delimited()
                .names("tipo", "limite", "clienteId")
                .build();
    }
}
