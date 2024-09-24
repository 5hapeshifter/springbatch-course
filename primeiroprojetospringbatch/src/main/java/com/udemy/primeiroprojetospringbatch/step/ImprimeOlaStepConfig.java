package com.udemy.primeiroprojetospringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ImprimeOlaStepConfig {

//    @Bean
//    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("Imprime Step", jobRepository)
//                .tasklet(imprimeOlaTasklet(null), platformTransactionManager) // tasklet é mais indicado para steps simples
//                .build();
//    }
//


    @Bean
    public Step imprimeParOuImpar(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("Imprime Step", jobRepository)
                /*
                  O custo para criar uma transaca eh alto, entao se colocarmos '1' no chunk, serao feitos 11 commits no BD,
                  no entanto se colocarmos '10' será executado 2 commits, mas os valores ficarao em memoria, portanto sempre
                  que aumentarmos esse valor devemos levar a memoria em conta.
                  Para escolher o valor adequado devemos estudar a quantidade de dados e as maquinas envolvidas, ou seja
                  eh um teste empirico.
                 */

                .<Integer, String>chunk(10, platformTransactionManager) // chunk é indicado para steps mais complexos
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }
    private ItemWriter<String> imprimeWriter() {
        return items -> items.forEach(System.out::println);
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<>(
                item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Impar", item)
        );


    }

    public IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<>(numerosDeUmAteDez.iterator());
    }
}
