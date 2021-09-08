package workshop.java.fundamentals.controlstatements;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.zip.ZipFile;

public class TryCatchStatement {

    @Test(dataProvider = "numbersAsText") // will be executed for "12", "-2", "broken"
    public void tryCatch(String numberAsText) {

        // not always everything goes right
        // we need to be prepared for errors
        // lets "try" some code
        // if something goes wrong we will "catch" that an error

        try {
            // parsing of text to integer may fail,
            // it will work only if text starts with correct integer
            Integer value = Integer.valueOf(numberAsText);
            System.out.println("parsed number is " + value);
        } catch (NumberFormatException e) {
            System.out.println("can't parse text as number, " + e.getMessage());
        }
    }

    @Test(dataProvider = "numbersAsText") // will be executed for "12", "-2", "broken"
    public void tryCatch2(String numberAsText) {

        // we can handle multiple error types

        try {
            Integer value = Integer.valueOf(numberAsText);
            System.out.println("parsed number is " + value);
        } catch (NumberFormatException e) {
            System.out.println("can't parse text to number, " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("something went wrong when parsing text=" + numberAsText);
        }
        // order of catch block is important in case of Exceptions hierarchy:
        // NumberFormatException inherits from RuntimeException
        // correct order is:
        // from most precise Exceptions types (lower in hierarchy)
        // to more general type of error (higher in hierarchy)
    }

    @Test(dataProvider = "numbersAsText") // will be executed for "12", "-2", "broken"
    public void tryMultiCatch(String numberAsText) {

        // since java 7 we can handle different exceptions in the same way
        // without repeating catch-block

        try {
            Integer value = Integer.valueOf(numberAsText);
            System.out.println("parsed number is " + value);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("can't parse text to number, " + e.getMessage());
        }
    }

    @Test(dataProvider = "numbersAsText") // will be executed for "12", "-2", "broken"
    public void tryCatchFinally(String numberAsText) {

        // not always everything goes right
        // we need to be prepared for errors
        // lets "try" some code
        // if something goes wrong we will "catch" that an error

        try {
            // parsing of text to integer may fail,
            // it will work only if text starts with correct integer
            Integer value = Integer.valueOf(numberAsText);
            System.out.println("parsed number is " + value);
        } catch (NumberFormatException e) {
            System.out.println("can't parse text as number, " + e.getMessage());
        } finally {
            System.out.println("\tfinally is executed in any case");
        }
    }

    @Test
    public void tryWithResources() {

        // objects implementing AutoCloseable interface
        // can be used in try-with-resources introduced in java 7

        // zip file will be opened (if possible)
        // and closed (if needed) after try block
        // in case of IOException error will be handled

        try (ZipFile file = new ZipFile("some/not/existing/file.zip")) {
            file.getComment();
        } catch (IOException e) {
            System.out.println("can't open file or error while processing");
        }
    }

    @DataProvider
    public static Object[][] numbersAsText() {
        return new Object[][] {{"12"},{"-2"},{"broken"}};
    }

}
