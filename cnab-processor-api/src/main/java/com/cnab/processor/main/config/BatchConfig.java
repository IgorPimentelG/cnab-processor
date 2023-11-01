package com.cnab.processor.main.config;

import com.cnab.processor.domain.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Configuration
public class BatchConfig {
	private final PlatformTransactionManager transactionManager;
	private final JobRepository jobRepository;

	public BatchConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
		this.transactionManager = transactionManager;
		this.jobRepository = jobRepository;
	}

	@Bean
	public Job job(Step step) {
		return new JobBuilder("job", jobRepository)
		  .start(step)
		  .incrementer(new RunIdIncrementer())
		  .build();
	}

	@Bean
	public Step step(
	  ItemReader<TransactionInput> reader,
	  ItemProcessor<TransactionInput, TransactionOutput> processor,
	  ItemWriter<TransactionOutput> writer
	) {
		return new StepBuilder("step", jobRepository)
		  .<TransactionInput, TransactionOutput>chunk(1000, transactionManager)
		  .reader(reader)
		  .processor(processor)
		  .writer(writer)
		  .build();
	}

	@Bean
	public FlatFileItemReader<TransactionInput> reader() {
		return new FlatFileItemReaderBuilder<TransactionInput>()
		  .name("reader")
		  .resource(new FileSystemResource("files/CNAB.txt"))
		  .fixedLength()
		  .columns(
			new Range(1, 1), new Range(2, 9),
		    new Range(10 ,19), new Range(20, 30),
		    new Range(31, 42), new Range(43, 48),
		    new Range(49, 62), new Range(63, 80)
		  )
		  .names("type", "data", "amount", "cpf", "cardNumber", "hour", "storeHolder", "storeName")
		  .targetType(TransactionInput.class)
		  .build();
	}

	@Bean
	ItemProcessor<TransactionInput, TransactionOutput> processor() {
		return item -> {
			return new TransactionOutput(
			    UUID.randomUUID().toString(),
                item.type(),
                null,
                item.amount(),
                item.cpf().toString(),
                item.cardNumber(),
                item.storeHolder(),
                item.storeName())
			  .withAmount(item.amount().divide(BigDecimal.valueOf(100), RoundingMode.CEILING))
			  .withRegisteredAt(item.data(), item.hour())
			  .withCPF(String.valueOf(item.cpf()));
		};
	}

	@Bean
	JdbcBatchItemWriter<TransactionOutput> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<TransactionOutput>()
		  .dataSource(dataSource)
		  .sql("""
			INSERT INTO transactions (id, type, registered_at, amount, cpf, card_number, store_holder, store_name)
			VALUES (:id, :type, :registeredAt, :amount, :cpf, :cardNumber, :storeHolder, :storeName)
		  """)
		  .beanMapped()
         .build();
	}
}
