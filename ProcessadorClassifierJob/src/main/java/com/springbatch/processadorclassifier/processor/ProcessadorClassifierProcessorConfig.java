package com.springbatch.processadorclassifier.processor;

import com.springbatch.processadorclassifier.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorClassifierProcessorConfig {

    // Forma indicada para quando aplicamos regras de negócio por objeto especifico em um arquivo com multiplos objetos
    @Bean
    public ItemProcessor procesadorClassifierProcessor() throws Exception {

        return new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(classifier())
                .build();

    }

    // Metodo para decidir qual processador sera aplicado
    private Classifier<? super Object, ItemProcessor<?,?>> classifier() {
        return new Classifier<Object, ItemProcessor<?, ?>>() {
            @Override
            public ItemProcessor<?, ?> classify(Object o) {
                if (o instanceof Cliente) {
                    return new ClienteProcessor();
                } else {
                    return new TransacaoProcessor();
                }
            }
        };
    }
}
