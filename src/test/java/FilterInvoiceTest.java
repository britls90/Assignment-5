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
    @Test
    // works because the database is established in the constructor of FilterInvoice,
    // so when the objects are created they already have the database.

    void filterInvoiceTest() {
        FilterInvoice fi = new FilterInvoice();
        List<Invoice> list = fi.lowValueInvoices();

        for (Invoice i : list) {
            assertTrue (i.getValue() < 100);
        }
    }

    @Test
    void filterInvoiceStubbedTest(){
        // works because the database is created then passed into the constructor.
        Database db = new Database();
        FilterInvoice fi = new FilterInvoice(db);
        List<Invoice> list = fi.lowValueInvoices();

        for (Invoice i : list) {
            assertTrue (i.getValue() < 100);
        }

    }


}
