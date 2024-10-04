package com.springbatch.contasbancarias.processor;

import com.springbatch.contasbancarias.dominio.Cliente;
import com.springbatch.contasbancarias.dominio.Conta;

public class ContaInvalidaItemProcessor implements org.springframework.batch.item.ItemProcessor<com.springbatch.contasbancarias.dominio.Cliente, com.springbatch.contasbancarias.dominio.Conta> {
    @Override
    public Conta process(Cliente cliente) throws Exception {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        return conta;
    }
}
