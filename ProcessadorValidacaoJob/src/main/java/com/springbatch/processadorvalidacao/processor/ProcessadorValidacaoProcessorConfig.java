package com.springbatch.processadorvalidacao.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorvalidacao.dominio.Cliente;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProcessadorValidacaoProcessorConfig {

    private Set<String> emails = new HashSet<>();

    @Bean
    public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() throws Exception {

        // validador mais simples -- EXTRAIDO PARA O METODO "beanValidatingProcessor" para fazer o exemplo com multiplos processadores
//		BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
//		processor.setFilter(true);

        // validador mais complexo e customizado -- EXTRAIDO PARA O METODO "emailValidatingProcessor" para fazer o exemplo com multiplos processadores
//        ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
//        processor.setValidator(validator());
//        processor.setFilter(true);
//
//        return processor;

        // Exemplo de execução de multiplos processadores
        return new CompositeItemProcessorBuilder<Cliente, Cliente>()
                .delegates(beanValidatingProcessor(), emailValidatingProcessor())
                .build();

    }

    private ValidatingItemProcessor<Cliente> emailValidatingProcessor() {
        ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
        processor.setValidator(validator());
        processor.setFilter(true);
        return processor;
    }

    private BeanValidatingItemProcessor<Cliente> beanValidatingProcessor() throws Exception {
        // validador mais simples
		BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
        processor.afterPropertiesSet(); // metodo para setar as propriedades desse processador, NECESSARIO QUANDO UTILIZADO COM O 'CompositeItemProcessorBuilder'
		processor.setFilter(true);
        return processor;
    }

    private Validator<Cliente> validator() {
        return new Validator<Cliente>() {
            @Override
            public void validate(Cliente cliente) throws ValidationException {
                if (emails.contains(cliente.getEmail())) {
                    throw new ValidationException(String.format("O cliente %s já foi processado", cliente.getEmail()));
                }
                emails.add(cliente.getEmail());
            }
        };
    }
}
