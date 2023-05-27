package com.example.invoiceservice;

import com.example.commonpackage.utils.constants.Paths;
import com.example.invoiceservice.entities.Invoice;
import com.example.invoiceservice.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableDiscoveryClient
@EnableElasticsearchRepositories(basePackages = "com.example.invoiceservice.repository")
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Invoice.InvoiceBasePackage})
public class InvoiceServiceApplication {

    private final InvoiceRepository repository;

    public InvoiceServiceApplication(InvoiceRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(InvoiceServiceApplication.class, args);



    }

    @PostConstruct
    public void add(){
        repository.save(new Invoice());
    }

}
