import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TransactionStatusTest {

    @Test
    public void testTransactionStatusLength() {
        assertEquals(3, TransactionStatus.values().length);
    }

    @Test
    public void testTransactionStatusValues() {
        assertEquals("[PENDING, FINALISED, CANCELLED]", Arrays.toString(TransactionStatus.values()));
    }
}
