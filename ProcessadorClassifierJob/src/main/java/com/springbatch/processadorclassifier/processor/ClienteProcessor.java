package com.springbatch.processadorclassifier.processor;

import com.springbatch.processadorclassifier.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {

    // Aqui é onde colocamos a regra de nogocio para o objeto
    @Override
    public Cliente process(Cliente cliente) throws Exception {
        System.out.printf("\nAplicando regras de negócio no cliente %s.%n", cliente);
        return cliente;
    }
}
