package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

	@StepScope
	@Bean
	public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(
			// Program arguments setado nas configuracoes de execucao - valor: arquivoClientes=file:ArquivoDelimitadoJob/files/clientes.txt
			@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
		return new FlatFileItemReaderBuilder<Cliente>()
				.name("leituraArquivoDelimitadoReader")
				.resource(arquivoClientes)
				.delimited() // tipo do arquivo, delimitado, indefinido e etc
				.names(new String[] {"nome", "sobrenome", "idade", "email"})
				.targetType(Cliente.class) // conversao dos dados para o objeto de dominio Cliente
				.build();
	}
}
