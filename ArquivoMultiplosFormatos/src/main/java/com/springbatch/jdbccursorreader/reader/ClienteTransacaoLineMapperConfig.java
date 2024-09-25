package com.springbatch.jdbccursorreader.reader;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import com.springbatch.jdbccursorreader.dominio.Transacao;
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
public class ClienteTransacaoLineMapperConfig {

    @SuppressWarnings({"rawtype", "unchecked"})
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() { //PatternMatchingCompositeLineMapper é uma implementacao do linemapper que usa um padrao para descobrir qual tipo de linemapper sera aplicado
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    private Map<String, FieldSetMapper<?>> fieldSetMappers() {
        // Estamos definindo os tipos de objetos que podemos pegar no arquivo
        Map<String, FieldSetMapper<?>> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*", fieldSetMapper(Cliente.class));
        fieldSetMappers.put("1*", fieldSetMapper(Transacao.class));
        return fieldSetMappers;
    }

    private <T> FieldSetMapper<T> fieldSetMapper(Class<T> clienteClass) {
        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(clienteClass);
        return fieldSetMapper;
    }

    // metodo para pegar os dados do arquivo com base no primeiro caracter
    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clienteLineTokenizer());
        tokenizers.put("1*", transacaoLineTokenizer());
        return tokenizers;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        // pulamos o 0 pq ele representa a transacao
        lineTokenizer.setNames("id", "descricao", "valor");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
        // pulamos o 0 pq ele representa o cliente
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }
}
