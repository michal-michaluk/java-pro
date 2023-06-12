package workshop.java.intermediate.almostlikefunctional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class $ExitTest {

    // Task 1.
    // reimplement VAT calculator with strategy pattern
    // use existing functional interfaces

    // Task 2.
    // write Try monad
    // with static factory methods taking functional interface ThrowingSupplier
    // implement isSuccess method
    // implement getNullable, getOptional, getThrowing, getUnchecked
    // implement map, flatMap methods
    // implement orElse, orElseGet
    // implement recover(Function<? super Throwable, T> f)
    // provide public static factory methods successful and failure

    // make it sens to accept ThrowingRunnable in static factory method ?

    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        T get() throws Throwable;
    }

    public interface Try<T> {

        static <Input> Try<Input> tryIt(ThrowingSupplier<Input> supplier) {
            try {
                Input value = supplier.get();
                return new TrySuccess<>(value);
            } catch (Throwable t) {
                return new TryFailure<>(t);
            }
        }

        boolean isSuccess();

        T getNullable();

        Optional<T> getOptional();

        T getThrowing() throws Throwable;

        T getUnchecked();

        T orElse(T def);

        T orElseGe(Supplier<T> defSupplier);

        <U> Try<U> map(Function<T, U> f);

        //<U> Try<U> flatMap(Function<T, Try<U>> f);

        class TrySuccess<T> implements Try<T> {
            private final T value;

            private TrySuccess(T value) {
                this.value = value;
            }

            public boolean isSuccess() {
                return true;
            }

            @Override
            public T getNullable() {
                return value;
            }

            @Override
            public Optional<T> getOptional() {
                return Optional.ofNullable(value);
            }

            @Override
            public T getThrowing() {
                return value;
            }

            @Override
            public T getUnchecked() {
                return value;
            }

            @Override
            public T orElse(T def) {
                return value;
            }

            @Override
            public T orElseGe(Supplier<T> defSupplier) {
                return value;
            }

            @Override
            public <U> Try<U> map(Function<T, U> f) {
                return new TrySuccess<>(f.apply(value));
            }
        }

        class TryFailure<T> implements Try<T> {
            private final Throwable throwable;

            private TryFailure(Throwable throwable) {
                this.throwable = throwable;
            }

            public boolean isSuccess() {
                return false;
            }

            @Override
            public T getNullable() {
                return null;
            }

            @Override
            public Optional<T> getOptional() {
                return Optional.empty();
            }

            @Override
            public T getThrowing() throws Throwable {
                throw throwable;
            }

            @Override
            public T getUnchecked() {
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                } else {
                    throw new RuntimeException(throwable);
                }
            }

            @Override
            public T orElse(T def) {
                return def;
            }

            @Override
            public T orElseGe(Supplier<T> defSupplier) {
                return defSupplier.get();
            }

            @Override
            public <U> Try<U> map(Function<T, U> f) {
                return new TryFailure<>(throwable);
            }

        }
    }

    @Test
    public void first() throws Exception {
        boolean isSuccess = Try.tryIt(() -> "It works :)").isSuccess();

        Assertions.assertThat(isSuccess).isTrue();
    }

    @Test
    public void getOptional() throws Exception {
        String nonEmpty = Try.tryIt(() -> "it worked")
                .getOptional()
                .filter(s -> !s.isEmpty())
                .get();

        String empty = Try.<String>tryIt(() -> {
            throw new RuntimeException("thrown from monad");
        }).getOptional().orElse("");

        Assertions.assertThat(nonEmpty).isNotEmpty();
        Assertions.assertThat(empty).isEmpty();
    }

    @Test
    public void getUnchecked() throws Exception {
        Try<Object> aTry = Try.tryIt(() -> {
            throw new RuntimeException("thrown from monad");
        });

        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        aTry::getUnchecked
                )
        ;
    }

    @Test
    public void map() throws Exception {
        byte[] nonEmpty = Try.tryIt(() -> "it worked")
                .map(String::toUpperCase)
                .map(String::getBytes)
                .getOptional()
                .filter((s) -> s.length > 0)
                .get();

        System.out.println(Arrays.toString(nonEmpty));
    }

    @Test
    public void tryItOnCheckedExceptionStatement() throws Exception {
        Optional<String> s1 = Optional.of("slow processed string")
                .flatMap(s -> Try.tryIt(() -> {
                            Thread.sleep(100);
                            return s;
                        }).getOptional()
                );

        Assertions.assertThat(s1).hasValue("slow processed string");
    }

    // Task 3.
    // write ObservableValue class
    // implement set and get methods
    // implement map, filter, consume methods
    // implement map, filter, consume variant-'Bi' taking old and new values
    // implement combine(BiFunction<T, ObservableValue<U>, R> f)

}
