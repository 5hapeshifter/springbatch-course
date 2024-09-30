package com.springbatch.jdbccursorreader.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

	@StepScope // quando usar o jobparameters tem que usar o stepscope
	@Bean
	public FlatFileItemWriter<Cliente> leituraArquivoLarguraFixaWriter(
			@Value("#{jobParameters['arquivoClientesSaida']}") WritableResource arquivoClientesSaida) {

		return new FlatFileItemWriterBuilder<Cliente>()
				.name("leituraArquivoLarguraFixaWriter")
				.resource(arquivoClientesSaida)
				.formatted()
				.format("%-9s %-9s %-2s %-19s")
				.names("nome", "sobrenome", "idade", "email")
				.build();


//		return items -> items.forEach(System.out::println);
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
