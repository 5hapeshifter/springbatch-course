package com.springbatch.jdbccursorreader.reader;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcCursorReaderConfig {

	/*
	 	Consulta utilizando o cursor, é mais rápida mas consome mais memoria
	 	Devemos tomar cuidado com esse tipo de consulta, pois se tiver muitos dados, pode estourar a memória
	 */
//	@StepScope
//	@Bean
//	public JdbcCursorItemReader<Cliente> leituraArquivoDelimitadoReader(
//			@Qualifier("appDataSource")DataSource dataSource // recebendo o bean da classe DataSourceConfig
//			) {
//		return new JdbcCursorItemReaderBuilder<Cliente>()
//				.name("jdbcCursorReader")
//				.dataSource(dataSource) // passando o bean
//				.sql("select * from cliente")
//				.rowMapper(new BeanPropertyRowMapper<>(Cliente.class)) // passando a Classe que vai ser serializada a partir dos dados
//				.build();
//	}

	/*
	 	Consulta paginada, é mais lenta mas consome menos memória
	 	Devemos usar a consulta paginada quando o volume de dados retornados for muito alto, pois ele vai realizar a
	 		consulta com base no limite que definimos no page size, ou seja, vai guardar somente uma pagina por vez em memoria
	 		dessa forma nao ha risco de ficar sem memoria por causa da quantidade de dados.
	 */
	@Bean
	public JdbcPagingItemReader<Cliente> jdbcPagingReader(
			@Qualifier("appDataSource")DataSource dataSource,
			PagingQueryProvider queryProvider) {
		return new JdbcPagingItemReaderBuilder<Cliente>()
				.name("jdbcPagingReader")
				.dataSource(dataSource)
				.queryProvider(queryProvider)
				.pageSize(5)
				.rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
				.build();
	}

	@Bean
	public SqlPagingQueryProviderFactoryBean queryProvider(
			@Qualifier("appDataSource") DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource);
		queryProvider.setSelectClause("select *");
		queryProvider.setFromClause(" from cliente");
		queryProvider.setSortKey("email");

		return queryProvider;
	}
}
