package com.cnab.processor.main.config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.cnab.processor.domain.TransactionInput;
import com.cnab.processor.domain.TransactionOutput;

@Configuration
public class BatchConfig {
	private final PlatformTransactionManager transactionManager;
	private final JobRepository jobRepository;

	public BatchConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
		this.transactionManager = transactionManager;
		this.jobRepository = jobRepository;
	}

	@Bean
	Job job(Step step) {
		return new JobBuilder("job", jobRepository)
		  .start(step)
		  .incrementer(new RunIdIncrementer())
		  .build();
	}

	@Bean
	Step step(
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

	@StepScope
	@Bean
	FlatFileItemReader<TransactionInput> reader(@Value("#{jobParameters['cnabFile']}") Resource resource) {
		return new FlatFileItemReaderBuilder<TransactionInput>()
		  .name("reader")
		  .resource(resource)
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

	@Bean
	JobLauncher jobLauncherAsync(JobRepository jobRepository) throws Exception {
		var jobLauncher = new TaskExecutorJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
}
