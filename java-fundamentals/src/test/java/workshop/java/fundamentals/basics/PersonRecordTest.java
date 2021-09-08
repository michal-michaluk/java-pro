package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonRecordTest {

    Person anna = new Person("Anna", 21, List.of());
    Person bartek = new Person("Anna", 21, List.of(anna));

    @Test
    public void accessors() {
        assertThat(anna.age()).isEqualTo(21);
        assertThat(anna.name()).isEqualTo("Anna");
    }

    @Test
    public void toStringImplementation() {
        assertThat(anna.toString()).isEqualTo("Person[name=Anna, age=21, friends=[]]");
    }

    @Test
    public void equalsAndHashcodeImplementation() {
        Person otherAnna = new Person("Anna", 21, List.of());

        assertThat(anna.equals(otherAnna)).isTrue();
        assertThat(anna.hashCode() == otherAnna.hashCode()).isTrue();
    }

    @Test
    public void localRecord() {
        interface MarkerInterface {}
        enum DidYouKnown {Yes, No}
        record LocalRecord(String info, DidYouKnown option) implements MarkerInterface {
        }

        MarkerInterface local = new LocalRecord("declaring local classes was possible since Java 1.0", DidYouKnown.Yes);

        assertThat(local).isInstanceOf(Record.class);
    }

    @Test
    public void compactConstructors() {
        record LocalRecord(String important, String dummy) {
            LocalRecord {
                Objects.requireNonNull(important);
                dummy = "";
            }
        }

        LocalRecord record = new LocalRecord("interesujące", "ale komu to potrzebne!");

        assertThat(record.important()).isEqualTo("interesujące");
        assertThat(record.dummy()).isEqualTo("");


        assertThatThrownBy(() -> new LocalRecord(null, "ale komu to potrzebne!"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void canonicalConstructors() {
        record LocalRecord(String important, String dummy) {
            LocalRecord(String important, String dummy) {
                Objects.requireNonNull(important);
                this.important = important;
                this.dummy = "";
            }
        }

        LocalRecord record = new LocalRecord("interesujące", "ale komu to potrzebne!");

        assertThat(record.important()).isEqualTo("interesujące");
        assertThat(record.dummy()).isEqualTo("");


        assertThatThrownBy(() -> new LocalRecord(null, "ale komu to potrzebne!"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void moreConstructors() {
        record LocalRecord(String important, String dummy) {
            LocalRecord(String important) {
                this(Objects.requireNonNull(important), "");
            }
        }

        LocalRecord record = new LocalRecord("interesujące");

        assertThat(record.important()).isEqualTo("interesujące");
        assertThat(record.dummy()).isEqualTo("");


        assertThatThrownBy(() -> new LocalRecord(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void staticMembersAndInnerClasses() {
        record LocalRecord(String important, String dummy, Within s, Within.Inner i) {
            private static final LocalRecord EMPTY = new LocalRecord("", "", new Within(), new Within.Inner());

            //String nonFinalMember = "not allowed;

            static LocalRecord empty() {
                return EMPTY;
            }

            static class Within {
                record Inner() {}
            }
        }
        Assertions.assertThat(LocalRecord.empty()).isNotNull();
    }

    @Test
    public void issueWithRecordsImmutability() {
        Person ewa = new Person("Anna", 21, new ArrayList<>());
        ewa.friends().add(anna);
        ewa.friends().add(bartek);

        assertThat(ewa.friends()).containsExactly(anna, bartek);
    }
}
