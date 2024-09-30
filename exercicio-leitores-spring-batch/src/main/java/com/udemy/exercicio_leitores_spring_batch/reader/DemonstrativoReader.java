package com.udemy.exercicio_leitores_spring_batch.reader;

import com.udemy.exercicio_leitores_spring_batch.domain.Orcamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DemonstrativoReader {

    @SuppressWarnings({"rawtype", "unchecked"})
    @Bean
    @StepScope
    public FlatFileItemReader<Orcamento> leituraOrcamentoReader(
            @Value("#{jobParameters['demonstrativo']}") Resource demonstrativo,
            LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder()
                .name("leituraOrcamento")
                .resource(demonstrativo)
                .lineMapper(lineMapper)
                .build();
    }
}
