package test2.spring.batch.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test2.spring.batch.model.User;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private ItemProcessor<User,User> itemProcessor;

    @Autowired
    private ItemWriter<User> itemWriter;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public Job userJob(){
        return jobBuilderFactory.get("userJob")
                .start(userStep())
                .build();
    }

    @Bean
    public Step userStep(){
        return stepBuilderFactory.get("userStep")
                .<User,User>chunk(new SimpleCompletionPolicy())
                .reader(itemReader())
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public JpaPagingItemReader<User> itemReader() {
        return new JpaPagingItemReaderBuilder<User>()
                .entityManagerFactory(entityManagerFactory)
                .name("reader")
                .queryString("select u from User u")
                .build();
    }


    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
