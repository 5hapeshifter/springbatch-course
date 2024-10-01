package com.springbatch.jdbccursorreader.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class LeituraArquivoDelimitadoWriterConfig {

	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> leituraArquivoDelimitadoWriter(
			@Value("#{jobParameters['arquivoClientesSaida']}") WritableResource arquivoClientesSaida) {

		return new FlatFileItemWriterBuilder<Cliente>()
				.name("leituraArquivoDelimitadoWriter")
				.resource(arquivoClientesSaida)
				.delimited()
				.names("nome", "sobrenome", "idade", "email")
				.build();

//		return items -> {
//			for (Cliente cliente : items) {
//				if ("Maria".equals(cliente.getNome())) {
//					throw new Exception("Maria da erro.");
//				} else {
//					System.out.println("Cliente: " + cliente.getNome());
//				}
//			}
//		};
	}
}
