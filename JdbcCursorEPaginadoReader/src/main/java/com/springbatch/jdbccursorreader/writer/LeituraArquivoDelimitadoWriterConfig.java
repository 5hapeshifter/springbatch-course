package com.springbatch.jdbccursorreader.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.jdbccursorreader.dominio.Cliente;

@Configuration
public class LeituraArquivoDelimitadoWriterConfig {
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
