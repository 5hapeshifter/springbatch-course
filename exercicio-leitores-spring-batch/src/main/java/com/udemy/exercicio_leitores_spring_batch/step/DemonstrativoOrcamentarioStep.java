package com.udemy.exercicio_leitores_spring_batch.step;

import com.udemy.exercicio_leitores_spring_batch.domain.Orcamento;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DemonstrativoOrcamentarioStep {

    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;

    public DemonstrativoOrcamentarioStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @SuppressWarnings({"rawtype", "unchecked"})
    @Bean
    public Step createDemonstrativoOrcamentarioStep(
            FlatFileItemReader leituraArquivoMultiplosFormatos,
            ItemWriter<Orcamento> leituraArquivoMultiplosFormatosWriter                                        ) {
        return new StepBuilder("createDemonstrativoOrcamentarioStep", jobRepository)
                .<Orcamento, Orcamento>chunk(1, platformTransactionManager)
                .reader(leituraArquivoMultiplosFormatos)
                .faultTolerant()
                .skip(FlatFileParseException.class)
                .skipLimit(30)
                .writer(leituraArquivoMultiplosFormatosWriter)
                .build();
    }
}
