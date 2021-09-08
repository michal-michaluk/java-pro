package workshop.java.fundamentals.exceptions;

import org.testng.annotations.Test;

import java.io.IOException;

public class ThrowingExceptions {

    @Test(expectedExceptions = Exception.class)
    public void throwCheckedException() throws Exception {

        // Exceptions not extending RuntimeException are called "checked exceptions".
        // Compiler will force you to declare them in throws clause
        // or handled them internally with try-catch.

        throw new Exception("it will not work");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throwUncheckedException() {

        // Exceptions extending RuntimeException are called "unchecked exceptions".
        // compiler is not checking them in compile time
        // there os no need declare them in throws clause

        throw new RuntimeException("I'm unchecked");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throwsClauseWithUncheckedException() throws RuntimeException {

        // but you can declare them in throws clause ^^^^^^^^^^^^^^^^^^^^^^^

        throw new RuntimeException("I'm unchecked");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void rethrowException() {
        try {
            // let say something went wrong
            throw new IllegalStateException("something went wrong");

        } catch (IllegalStateException e) {
            // we can perform additional task
            // and rethrow
            throw e;
        }
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void wrappingAndRethrowingException() {
        try {
            // let say something went wrong
            // note IOException is an checked exception
            throw new IOException("something went wrong");

        } catch (IOException e) {
            // we wrap it in RuntimeException - and rethrow
            // and rethrow
            throw new RuntimeException("processing of file X failed", e);

            // in that case we don't add them to throws clause
        }
    }

}
