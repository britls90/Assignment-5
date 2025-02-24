import org.example.Assignment.Database;
import org.example.Assignment.FilterInvoice;
import org.example.Assignment.Invoice;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterInvoiceTest {
//    @Test
//    void filterInvoiceTest() {
//        FilterInvoice fi = new FilterInvoice();
//        List<Invoice> list = fi.lowValueInvoices();
//
//        for (Invoice i : list) {
//            assertTrue (i.getValue() < 100);
//        }
//    }

    @Test
    void filterInvoiceStubbedTest(){
        Database db = new Database();
        FilterInvoice fi = new FilterInvoice(db);
        List<Invoice> list = fi.lowValueInvoices();

        for (Invoice i : list) {
            assertTrue (i.getValue() < 100);
        }

    }


}
