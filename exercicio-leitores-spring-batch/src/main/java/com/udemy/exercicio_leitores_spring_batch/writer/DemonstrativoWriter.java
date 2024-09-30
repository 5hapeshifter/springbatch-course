package com.udemy.exercicio_leitores_spring_batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemonstrativoWriter {

    @Bean
    public ItemWriter<?> leituraOrcamento() {
        return items -> items.forEach(System.out::println);
    }
}
