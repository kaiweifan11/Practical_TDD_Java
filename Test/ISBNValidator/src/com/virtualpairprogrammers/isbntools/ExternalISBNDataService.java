package com.virtualpairprogrammers.isbntools;

public interface ExternalISBNDataService {
    // to mock a api external service to get a book by the isbn
    public Book lookup(String isbn);
}
