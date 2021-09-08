package workshop.java.fundamentals.sexy.and.latest;

public sealed interface SealedWithRecords permits SealedWithRecords.A, SealedWithRecords.B {

    record A() implements SealedWithRecords {
    }

    record B() implements SealedWithRecords {
    }

    static String switchOnIt(SealedWithRecords r) {
        return switch (r) {
            case A a -> "a";
            case B b -> "b";
        };
    }
}
