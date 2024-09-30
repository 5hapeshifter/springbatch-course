package com.springbatch.processadorscript.reader;

import com.springbatch.processadorscript.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessadorScriptReaderConfig {

	@Bean
	@StepScope
	public FlatFileItemReader<Cliente> processadorScriptReader(
			@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
		System.out.println("Arquivo recebido: " + arquivoClientes.getFilename());
		return new FlatFileItemReaderBuilder<Cliente>()
				.name("processadorScriptReader")
				.resource(arquivoClientes)
				.delimited()
				.names("nome", "idade", "email")
				.targetType(Cliente.class)
				.build();
	}
}
