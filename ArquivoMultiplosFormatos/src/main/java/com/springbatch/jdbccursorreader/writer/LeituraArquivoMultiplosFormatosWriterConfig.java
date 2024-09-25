package com.springbatch.jdbccursorreader.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiplosFormatosWriterConfig {
	@Bean
	public ItemWriter<?> leituraArquivoMultiplosFormatosWriter() {
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
