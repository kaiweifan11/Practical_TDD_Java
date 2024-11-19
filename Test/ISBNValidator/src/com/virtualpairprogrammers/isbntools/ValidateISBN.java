package com.virtualpairprogrammers.isbntools;


// TDD helps when refactoring or changing codes to make sure
// the changes does not affect the integrity of the code
public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {
        //        return false;
        //        if(isbn == "0140449116"){
        //            return true;
        //        }
        //        return false;

        if(isbn.length() == LONG_ISBN_LENGTH) {
            return isThisLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isThisShortISBN(isbn);
        }

        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    private static boolean isThisShortISBN(String isbn) {
        int total = 0;

        for(int i = 0; i < SHORT_ISBN_LENGTH; i++){
            if(!Character.isDigit(isbn.charAt(i))){
                if(i == 9 && isbn.charAt(i) == 'X'){
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN numbers can only contain numeric digits");
                }
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }
        }

        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }

    private static boolean isThisLongISBN(String isbn) {
        int total = 0;

        for(int i = 0; i < LONG_ISBN_LENGTH; i++){
            if(i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }

        return (total % LONG_ISBN_MULTIPLIER == 0);
    }
}
