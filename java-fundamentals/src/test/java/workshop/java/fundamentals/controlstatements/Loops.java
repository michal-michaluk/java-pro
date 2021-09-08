package workshop.java.fundamentals.controlstatements;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class Loops {

    // let use simple collection - a list of three elements
    List<String> strings = asList("first", "second", "third");

    // any time we need execute some logic for each element of collection
    // we need use some form of loop-statement

    @Test
    public void simpleForeachLoop() {

        // most popular and friendly form of loop
        // is called foreach
        // code inside { }-brackets will be repeated for each element

        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void classicForLoop() {

        // classic form derived from C language
        // 1. we start with our index variable "i" from value 0
        // 2. before we loop we check our condition "i < strings.size()" is true
        // 3. if it is true body of loop is executed
        // 4. at the end "i" is incremented with i++ (unary increment operator)
        // and we go back to point 2.

        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }
    }

    @Test
    public void whileLoop() {

        // really uncommon nowadays
        // typically used with iterator object
        // 1. we create iterator instance for our collection
        // 2. we loop as long as condition "iterator.hasNext()" returns true
        // 3. each time body of loop is executed
        // anytime condition returns false, loop is finished

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            System.out.println(string);
        }
    }

    @Test
    public void doWhile() {

        // extremely uncommon
        // 1. body of loop is always executed at least once
        // 2. then condition is evaluated, in case of true loop is repeated
        // anytime condition returns false, loop is finished

        int count = 10;
        do {
            System.out.println("count = " + count);
            count = count + 1;
        } while (count < 5); // that condition will never be true
    }

}
