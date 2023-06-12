package workshop.java.fundamentals.sexy.and.latest;

import org.junit.jupiter.api.Test;

class SealedClassesTest {

    @Test
    void name(SealedClasses object) {
        switch (object) {
            case SealedClasses.A a -> System.out.println(a);
            case SealedClasses.C c -> System.out.println(c);
            case SealedClasses.B b -> System.out.println(b);
            case SealedClasses base -> System.out.println(base);
        }
    }
}
