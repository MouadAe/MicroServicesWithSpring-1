package com.enset.billingservice.web;

import com.enset.billingservice.dtos.InvoiceRequestDTO;
import com.enset.billingservice.dtos.InvoiceResponseDTO;
import com.enset.billingservice.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class InvoiceRestAPI {
    private InvoiceService invoiceService;

    @PostMapping("/addInvoice")
    public InvoiceResponseDTO saveInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceResponseDTO getInvoice(@PathVariable String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping("/invoicesByCustomerId/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomerId(@PathVariable String customerId){
        return invoiceService.invoicesByCustomerId(customerId);
    }
    @GetMapping("/invoices")
    public List<InvoiceResponseDTO> getInvoices(){
        return invoiceService.getAllInvoices();
    }
}
