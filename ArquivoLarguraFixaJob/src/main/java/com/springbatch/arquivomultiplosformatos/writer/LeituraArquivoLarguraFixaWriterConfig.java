package com.springbatch.arquivomultiplosformatos.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {
	@Bean
	public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
		return items -> items.forEach(System.out::println);
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
