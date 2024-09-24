package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente>, ResourceAwareItemReaderItemStream<Cliente> {

    private Object objetoAtual;

    // usando o padrao delegate
    private FlatFileItemReader<Object> delegate;

    public ArquivoClienteTransacaoReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    // Aqui estamos associando as transacoes ao cliente
    @Override
    public Cliente read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (objetoAtual == null) {
            objetoAtual = delegate.read();
        }
        Cliente cliente = (Cliente) objetoAtual;
        // zerando o objeto para que ele seja lido novamente
        objetoAtual = null;

        if (cliente != null) {
            while (peek() instanceof Transacao) {
                cliente.getTransacoes().add((Transacao) objetoAtual);
            }
        }
        return cliente;

    }

    private Object peek() throws Exception {
        objetoAtual = delegate.read();
        return objetoAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}



