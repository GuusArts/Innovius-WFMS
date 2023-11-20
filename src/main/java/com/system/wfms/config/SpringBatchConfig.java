package com.system.wfms.config;

import com.system.wfms.Sensors;
import lombok.AllArgsConstructor;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


@Configuration
@AllArgsConstructor
public class SpringBatchConfig {
    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;
    @Bean
    public FlatFileItemReader<Sensors> reader(){
        FlatFileItemReader itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/Test1 (2).csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper lineMapper() {
        DefaultLineMapper<Sensors> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer LineTokenizer = new DelimitedLineTokenizer();
        LineTokenizer.setDelimiter(",");
        LineTokenizer.setStrict(false);
        LineTokenizer.setNames("time",
                "probespark1/sgprobe1/bridge1",
                "probespark1/sgprobe1/bridge2",
                "probespark1/sgprobe1/pressure1Filtered[millibar]",
                "probespark1/sgprobe1/pressure2Filtered[millibar]",
                "probespark1/sgprobe1/pressureDiffFiltered[millibar]",
               " probespark1/sgprobe1/raw1Filtered",
               " probespark1/sgprobe1/raw2Filtered",
               " probespark1/sgprobe1/tempRtd1",
                "probespark1/sgprobe1/tempRtd2",
               " probespark1/chemprobe1/orpVoltage[volt]",
                "probespark1/sgprobe1/densityFilt",
                "ID");
        BeanWrapperFieldSetMapper<Sensors> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Sensors.class);
        lineMapper.setLineTokenizer(LineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public  CustomerProcessor processor(){
        return new CustomerProcessor();
    }



}
