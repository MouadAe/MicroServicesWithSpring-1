package com.enset.billingservice.mappers;

import com.enset.billingservice.dtos.InvoiceRequestDTO;
import com.enset.billingservice.dtos.InvoiceResponseDTO;
import com.enset.billingservice.entities.Invoice;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-31T20:53:22+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9 (AdoptOpenJDK)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice invoiceRequestDTOToInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        if ( invoiceRequestDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setAmount( invoiceRequestDTO.getAmount() );
        invoice.setCustomerId( invoiceRequestDTO.getCustomerId() );

        return invoice;
    }

    @Override
    public InvoiceResponseDTO invoiceToInvoiceResponseDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();

        invoiceResponseDTO.setId( invoice.getId() );
        invoiceResponseDTO.setDate( invoice.getDate() );
        invoiceResponseDTO.setAmount( invoice.getAmount() );
        invoiceResponseDTO.setCustomer( invoice.getCustomer() );

        return invoiceResponseDTO;
    }
}
