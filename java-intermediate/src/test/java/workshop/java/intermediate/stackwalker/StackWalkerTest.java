package workshop.java.intermediate.stackwalker;


import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StackWalkerTest {

    @Test
    public void checkStack() {
        StackWalker.getInstance().forEach(System.out::println);
    }

    @Test
    public void itIsNotWorking() {
        Assertions.assertThatThrownBy(
                () -> StackWalker.getInstance().getCallerClass()
        ).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void orMaybe() {
        System.out.println(StackWalker.getInstance(
                StackWalker.Option.RETAIN_CLASS_REFERENCE
        ).getCallerClass());
    }

    @Test
    public void junitStack() {
        StackWalker.getInstance(
                StackWalker.Option.RETAIN_CLASS_REFERENCE
        ).walk(stream -> stream
                .dropWhile(f -> f.getDeclaringClass().getPackageName().equals(StackWalkerTest.class.getPackageName()))
                .takeWhile(f -> f.getDeclaringClass().getPackageName().startsWith("org.junit"))
                .peek(System.out::println)
                .count()
        );
    }
}
