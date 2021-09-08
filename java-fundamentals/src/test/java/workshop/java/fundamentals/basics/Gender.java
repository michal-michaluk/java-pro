package workshop.java.fundamentals.basics;

/**
 * Enum is special variant of class
 * with limited / predefined set of instances.
 * Enum can not be instantiated with "new" operator.
 * Can have members (fields and methods) like normal class.
 * <p>
 * Created by michal on 14.06.2016.
 */
public enum Gender {

    // predefined instances:
    FEMALE("Ms"),
    MALE("Sir"),
    // please be tolerant
    MIXED("Mix");

    private final String honorific;

    Gender(String honorific) {
        this.honorific = honorific;
    }

    public String getHonorific() {
        return honorific;
    }
}
