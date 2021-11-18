package com.enset.billingservice.services;

import com.enset.billingservice.dtos.InvoiceRequestDTO;
import com.enset.billingservice.dtos.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
     InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
     InvoiceResponseDTO getInvoice(String invoiceId);
     List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);
     List<InvoiceResponseDTO> getAllInvoices();
}
