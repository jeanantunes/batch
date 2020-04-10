package com.poc.tesouro;

import com.poc.tesouro.model.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DuplicateKeyException;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"id", "nome", "data", "porcentagem"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType(User.class);
                }})
                .build();
    }


    @Bean
    public FlatFileItemReader<Cliente> readerCliente() {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("clienteItemReader")
                .resource(new ClassPathResource("cliente.csv"))
                .delimited()
                .names(new String[]{"id", "nome", "cpf"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Cliente>() {{
                    setTargetType(Cliente.class);
                }})
                .build();
    }

    @Bean
    public FlatFileItemReader<CoTitulares> readerCoTitulares() {
        return new FlatFileItemReaderBuilder<CoTitulares>()
                .name("coTitularesItemReader")
                .resource(new ClassPathResource("coTitulares.csv"))
                .delimited()
                .names(new String[]{"nomeDoCotitular", "cpf"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CoTitulares>() {{
                    setTargetType(CoTitulares.class);
                }})
                .build();
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    public ClienteItemProcessor processorCliente() {
        return new ClienteItemProcessor();
    }

    @Bean
    public CoTitularesItemProcessor processorCoTitulares() {
        return new CoTitularesItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO user (id, nome, data, porcentagem) VALUES (:id, :nome, :data, :porcentagem)")
                .dataSource(dataSource)
                .build();
    }

    @Bean//Cliente
    public JdbcBatchItemWriter<Cliente> writerCliente(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Cliente>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO cliente (id, nome, cpf) VALUES (:id, :nome, :cpf)")
                .dataSource(dataSource)
                .build();
    }

    @Bean//CoTitular
    public JdbcBatchItemWriter<CoTitulares> writerCoTitulares(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CoTitulares>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO co_titulares (nome_do_cotitular, cpf) VALUES (:nomeDoCotitular, (SELECT id FROM cliente WHERE cliente.id = :id))")
                .dataSource(dataSource)
                .build();
    }

    @Bean//FontePagadora
    public JdbcBatchItemWriter<FontePagadora> writerFontePagadora(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<FontePagadora>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO fontePagadora (id, nome, cnpj, saldos) VALUES (:id, :nome, :cnpj, :saldos)")
                .dataSource(dataSource)
                .build();
    }

    @Bean//SaldoOuRendimento
    public JdbcBatchItemWriter<SaldoOuRendimento> writerSaldoOuRendimento(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<SaldoOuRendimento>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO saldoOuRendimento (id, fontesPagadoras, titular, coTitulares, nmConta, numeroDaConta," +
                        "vrSaldoAnterior, vrSaldoAtual, dtApuracao, legadoOrigem, produto, categoriaRendto, vrRendto) " +
                        "VALUES (:id, :fontesPagadoras, :titular, :coTitulares, :nmConta, :numeroDaConta, :vrSaldoAnterior, " +
                        ":vrSaldoAtual, :dtApuracao, :legadoOrigem, :produto, :categoriaRendto, :vrRendto)")
                .dataSource(dataSource)
                .build();
    }
/*
    @Bean
    public JdbcBatchItemWriter<User> writerUpdate(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("UPDATE user SET  nome = :nome, data = :data, porcentagem = :porcentagem WHERE id=:id")
                .dataSource(dataSource)
                .build();
    }
*/

    @Bean(name = "job1")
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1, Step step2, Step step3) {
        return jobBuilderFactory.get("importUserAndClienteAndCoTitularesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .next(step2)
                .next(step3)
                .end()
                .build();
    }

    /*
        @Bean(name = "job2")
        public Job updateUserJob(JobCompletionNotificationListener listener, Step step2) {
            return jobBuilderFactory.get("updateUserJob")
                    .incrementer(new RunIdIncrementer())
                    .listener(listener)
                    .flow(step2)
                    .end()
                    .build();
        }
    */
    @Bean
    public Step step1(JdbcBatchItemWriter<User> writer) {
        return stepBuilderFactory.get("step1")
                //.allowStartIfComplete(true)
                .<User, User>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .faultTolerant() //inicio Retry
                .retryLimit(3) //Retry 3 vezes
                .retry(FlatFileParseException.class)
                .retry(DuplicateKeyException.class)
                .retry(ItemReaderException.class)
                .retry(NullPointerException.class)
                //.retry(NonTransientResourceException.class)
                //.retry(TimeoutException.class)
                //.retry(Throwable.class)
                //.retry(DeadlockLoserDataAccessException.class) //fim Retry
                //.noRollback(ValidationException.class)
                //.readerIsTransactionalQueue()
                .startLimit(1)
                .build();
    }


    @Bean
    public Step step2(JdbcBatchItemWriter<Cliente> writerCliente) {
        return stepBuilderFactory.get("step2 - CLIENTE")
                //.allowStartIfComplete(true)
                .<Cliente, Cliente>chunk(100)
                .reader(readerCliente())
                .processor(processorCliente())
                .writer(writerCliente)
                .faultTolerant() //inicio Retry
                .retryLimit(3) //Retry 3 vezes
                .retry(FlatFileParseException.class)
                .retry(DuplicateKeyException.class)
                .retry(ItemReaderException.class)
                .retry(NullPointerException.class)
                //.retry(NonTransientResourceException.class)
                //.retry(TimeoutException.class)
                //.retry(Throwable.class)
                //.retry(DeadlockLoserDataAccessException.class) //fim Retry
                //.noRollback(ValidationException.class)
                //.readerIsTransactionalQueue()
                .build();
    }

    @Bean
    public Step step3(JdbcBatchItemWriter<CoTitulares> writerCoTitulares) {
        return stepBuilderFactory.get("step3 - CO_TITULARES")
                .allowStartIfComplete(true)
                .<CoTitulares, CoTitulares>chunk(100)
                .reader(readerCoTitulares())
                .processor(processorCoTitulares())
                .writer(writerCoTitulares)
                .faultTolerant() //inicio Retry
                .retryLimit(3) //Retry 3 vezes
                .retry(FlatFileParseException.class)
                .retry(DuplicateKeyException.class)
                .retry(ItemReaderException.class)
                .retry(NullPointerException.class)
                //.retry(NonTransientResourceException.class)
                //.retry(TimeoutException.class)
                //.retry(Throwable.class)
                //.retry(DeadlockLoserDataAccessException.class) //fim Retry
                //.noRollback(ValidationException.class)
                //.readerIsTransactionalQueue()
                .build();
    }
}