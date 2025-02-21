import org.example.Assignment.FilterInvoice;
import org.example.Assignment.Invoice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterInvoiceTest {
    @Test
    void filterInvoiceTest() {
        FilterInvoice fi = new FilterInvoice();
        List<Invoice> list = fi.lowValueInvoices();

        for (Invoice i : list) {
            assertTrue (i.getValue() < 100);
        }
    }
}
