package com.udemy.exercicio_leitores_spring_batch.reader;

import com.udemy.exercicio_leitores_spring_batch.domain.ItemsDoOrcamento;
import com.udemy.exercicio_leitores_spring_batch.domain.Orcamento;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OrcamentoItemLineMapper {

    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        //PatternMatchingCompositeLineMapper é uma implementacao do linemapper que usa um padrao para descobrir qual tipo de linemapper sera aplicado
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    private Map<String, FieldSetMapper<?>> fieldSetMappers() {
        // Estamos definindo os tipos de objetos que podemos pegar no arquivo
        Map<String, FieldSetMapper<?>> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("[*", fieldSetMapper(Orcamento.class));
        fieldSetMappers.put(" *", fieldSetMapper(ItemsDoOrcamento.class));
        return fieldSetMappers;
    }

    private <T> FieldSetMapper<T> fieldSetMapper(Class<T> clazz) {
        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(clazz);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("[*", orcamentoTokenizer());
        tokenizers.put(" *", itemsOrcamentoTokenizer());
        return tokenizers;
    }

    private LineTokenizer orcamentoTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("[","id", "] ", " ", "nome", " - R$ ", "total");
        lineTokenizer.setIncludedFields(1,4,6);
        lineTokenizer.setDelimiter(" ");
        return lineTokenizer;
    }

    private LineTokenizer itemsOrcamentoTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("    [", "data", "] ", "nome", " - R$ ", "valorUnitario");
        lineTokenizer.setIncludedFields(2,4,6);
        lineTokenizer.setDelimiter(" ");
        return lineTokenizer;
    };

}
