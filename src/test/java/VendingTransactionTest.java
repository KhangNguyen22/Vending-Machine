import org.junit.Test;

import java.lang.Math;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class VendingTransactionTest {

    @Test 
    public void testGetTransactionSummary(){
        VendingTransaction vt = new VendingTransaction(1);
        Drinks.Water.setPrice(10.0);
        vt.addProductToTransaction(1, Drinks.Water, 10);
        assertEquals("Snack: Water | Quantity: 10 | Subtotal: 100.0\n", vt.getTransactionSummary());
    }

    @Test 
    public void testToString1(){
        VendingTransaction vt = new VendingTransaction(1);
        vt.commit(50);

        String result = "TRANSACTION STATUS: FINALISED\n" + "TRANSACTION DATE: " + new Date() + "\nChange Given: 50.0\n";
        assertEquals(result, vt.toString()); 
    }


    @Test
    public void testStatus() {
        VendingTransaction vt = new VendingTransaction(1);
        vt.setStatus(TransactionStatus.FINALISED);
        assertEquals(TransactionStatus.FINALISED, vt.getStatus());
    }
    
    @Test 
    public void testGetTransactionList(){
        VendingTransaction vt = new VendingTransaction(1);
        vt.addProductToTransaction(1, Drinks.Water, 10);
        vt.getTransactionItemsAsList();
        vt.cancelTransaction();
        vt.setStatus(TransactionStatus.FINALISED);
        vt.getTotalPrice();
        vt.getTransactionItems();
        assertEquals("Snack: Water | Quantity: 10 | Subtotal: 100.0\n", vt.getTransactionSummary());
    }
    @Test 
    public void testGetTotalPrice(){
        VendingTransaction vt = new VendingTransaction(1);
        vt.addProductToTransaction(1, Drinks.Water, 10);
        vt.getTransactionItemsAsList();
        vt.cancelTransaction();
        vt.getTransactionItems();
        assertEquals(100,Math.round(vt.getTotalPrice()));
    }
}
