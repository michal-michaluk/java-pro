package workshop.java.fundamentals.inheritance;

public sealed class SealedClasses permits SealedClasses.A, SealedClasses.B {

    final class A extends SealedClasses {
    }

    sealed class B extends SealedClasses permits C {
    }

    non-sealed class C extends B {
    }
}
