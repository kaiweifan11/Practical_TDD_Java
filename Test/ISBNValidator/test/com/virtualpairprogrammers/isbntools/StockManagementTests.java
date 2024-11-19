package com.virtualpairprogrammers.isbntools;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

// Use stubs to test data
// use mocks to test behaviour
// use fakes if it's not required for the test at all

public class StockManagementTests {

    // Creating test stub with external data service to mock
    // STUB
    //    @Test
    //    public void testCanGetCorrectLocatorCode() {
    //        // create a stub for the service
    //        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
    //            @Override
    //            public Book lookup(String isbn) {
    //                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
    //            }
    //        };
    //
    //        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
    //            @Override
    //            public Book lookup(String isbn) {
    //                return null;
    //            }
    //        };
    //
    //        String isbn = "0140177396";
    //        StockManager stockManager = new StockManager();
    //        stockManager.setDatabaseService(testDatabaseService);
    //        stockManager.setWebService(testWebService);
    //        String locatorCode = stockManager.getLocatorCode(isbn);
    //        assertEquals("7396J4", locatorCode);
    //    }

    // JUnit setup method
    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager ;

    @Before
    public void setup() {
        // runs before every test
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    // MOCK
    @Test
    public void testCanGetCorrectLocatorCode() {
        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "J. Steinbeck"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        // times()
        // atLeast()
        // atMost()
        // never()
        verify(testDatabaseService).lookup("0140177396");
        // anyString()
        // any(Book)
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        // mock cannot find in database
        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("0140177396");
        verify(testWebService).lookup("0140177396");
    }
}
