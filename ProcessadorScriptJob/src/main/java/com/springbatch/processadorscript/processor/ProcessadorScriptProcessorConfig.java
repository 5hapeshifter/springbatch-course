package com.springbatch.processadorscript.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorscript.dominio.Cliente;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProcessadorScriptProcessorConfig {

    private Set<String> emails = new HashSet<>();

    /*
      Para fazer funcionar tem que usar a graalvm conforme orientado abaixo:

        Consegui resolver meu problema usando o Java 11 e usando o graalVM já que ela é uma VM poliglota e a partir disso no método language eu uso como parâmetro a linguagem "graal.js" e dessa forma consigo rodar meu script diretamente.
        E como argumento para VM habilitei o nashorn com o parâmetro -Dpolyglot.js.nashorn-compat=true

     */

    @Bean
    public ItemProcessor<Cliente, Cliente> procesadorScriptProcessor() throws Exception {
        return new ScriptItemProcessorBuilder<Cliente, Cliente>()
                .language("nashorn")
                .scriptSource(
                        "var email = item.getEmail();" +
                        "var arquivoExiste = `ls | grep ${email}.txt`;" +
                        "if (!arquivoExiste) item; else null;")
                .build();
    }
}
