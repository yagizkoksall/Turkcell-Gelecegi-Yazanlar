package com.example.invoiceservice.repository;



import com.example.invoiceservice.entities.Invoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
import java.util.UUID;


public interface InvoiceRepository extends ElasticsearchRepository<Invoice,UUID> {
    List<Invoice> findAll();
}
