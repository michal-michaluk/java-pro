package workshop.java.fundamentals.streamsandcollections;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.*;

public class ImmutableCollections {

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void unmodifiableCollections() throws Exception {

        // interfaces of all collections in java allow to modify entries
        List<String> list = new ArrayList<>();
        list.add("some");
        list.add("some other");

        // if we want prevent further modifications of that list
        // we can wrap it - in unmodifiable decorator

        List<String> unmodifiable = Collections.unmodifiableList(list);

        // now we can get entries or iterate
        for (String string : unmodifiable) {
            System.out.println(string);
        }
        Assertions.assertThat(unmodifiable)
                .containsExactly("some", "some other");

        // but any modification will throw error UnsupportedOperationException

        unmodifiable.add("another");

        // similar with
        // Collections.unmodifiableCollection(anyCollection);
        // Collections.unmodifiableMap(map);
        // Collections.unmodifiableSet(set);
    }

    @Test
    public void manyConvenienceMethodsReturnsUnmodifiableCollection() throws Exception {

        // many convenience methods returns unmodifiable collection

        // try it by uncommenting add call
        Collections.emptyList()
        //        .add("will brake")
        ;
        Collections.singletonList("single entry")
        //        .add("will brake")
        //        .set(0, "will brake")
        ;
        Arrays.asList("entry one", "entry two")
        //        .add("will break")
        ;

    }

    @Test
    public void notTrulyImmutableCollections() throws Exception {

        // collection will be truly immutable
        // if we cannot edit entries in collection
        // and each entry is immutable

        // two instances of lists but equal
        List<Date> dateOnList1 = Collections.singletonList(new Date(2016 - 1900, 6 - 1, 15));
        List<Date> dateOnList2 = Collections.singletonList(new Date(2016 - 1900, 6 - 1, 15));
        Assertions.assertThat(dateOnList1).isEqualTo(dateOnList2);

        // old mutable java.util.Date representation
        Date date20160615 = dateOnList2.get(0);

        // change value of year
        date20160615.setYear(2020 - 1900);
        // same date instance but internal state is changed
        Assertions.assertThat(date20160615).isSameAs(dateOnList2.get(0));

        // unfortunately lists are no longer equals
        // even if lists are unmodifiable
        Assertions.assertThat(dateOnList1).isNotEqualTo(dateOnList2);

        System.out.println(dateOnList1);
        System.out.println(dateOnList2);
    }
}
