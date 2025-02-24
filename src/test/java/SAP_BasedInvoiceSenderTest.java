
//package org.example;
import org.example.Assignment.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

    public class SAP_BasedInvoiceSenderTest {

        @Test
        void testWhenLowValueInvoices() {
            // works because you're mocking the filter and SAP which are both external.
            // then you're stubbing (hard coding) the invoice list to be sent to the mocked
            // SAP. Then verifying with mokito that the SAPs are sent.
            FilterInvoice mockFilter = mock(FilterInvoice.class);
            SAP mockSap = mock(SAP.class);

            List<Invoice> mockLowValuedInvoices = List.of(
                    new Invoice("1", 50), // value < 100
                    new Invoice("2", 75)  // value < 100
            );
            when(mockFilter.lowValueInvoices()).thenReturn(mockLowValuedInvoices);

            SAP_BasedInvoiceSender invoiceSender = new SAP_BasedInvoiceSender(mockFilter, mockSap);
            invoiceSender.sendLowValuedInvoices();

            for (Invoice invoice : mockLowValuedInvoices) {
                verify(mockSap).send(invoice);
            }
        }

        @Test
        void testWhenNoInvoices() {
            // works because SAP and filter are mocked then creating a list
            // with a value >100 (shouldn't be sent). No list sent because none
            // of the invoices are within the limit. Then verified by mokito that it's sent.
            FilterInvoice mockFilter = mock(FilterInvoice.class);
            SAP mockSap = mock(SAP.class);

            List<Invoice> mockLowValuedInvoices = List.of(
                    new Invoice("3", 150)
            );
            when(mockFilter.lowValueInvoices()).thenReturn(mockLowValuedInvoices);

            SAP_BasedInvoiceSender invoiceSender = new SAP_BasedInvoiceSender(mockFilter, mockSap);
            invoiceSender.sendLowValuedInvoices();

            for (Invoice invoice : mockLowValuedInvoices) {
                verify(mockSap).send(invoice);
            }
        }

        @Test
        void testThrowExceptionWhenBadInvoice() {
            // works because the invoice that's passed in is a bad value (-5) which then
            // throws the exception.
            FilterInvoice mockFilter = mock(FilterInvoice.class);
            SAP mockSap = mock(SAP.class);

            Invoice invoice;
            List<Invoice> mockLowValuedInvoices = List.of(
                    invoice = new Invoice("4", -5)
            );
            when(mockFilter.lowValueInvoices()).thenReturn(mockLowValuedInvoices);

            SAP_BasedInvoiceSender invoiceSender = new SAP_BasedInvoiceSender(mockFilter, mockSap);
            invoiceSender.sendLowValuedInvoices();

            doThrow(new FailToSendSAPInvoiceException("SAP invoice failed.")).when(mockSap).send(invoice);
        }
    }


