package com.enset.billingservice.mappers;

import com.enset.billingservice.dtos.InvoiceRequestDTO;
import com.enset.billingservice.dtos.InvoiceResponseDTO;
import com.enset.billingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice invoiceRequestDTOToInvoice(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO invoiceToInvoiceResponseDTO(Invoice invoice);
}
