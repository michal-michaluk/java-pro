package workshop.java.advanced.dsl;

import org.assertj.core.api.Assertions;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import workshop.java.advanced.dsl.DomainObjects.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class $EntryTest {

    public static final @Language("JSON") String text = "{\n" +
            "  \"created\": \"2007-12-03\",\n" +
            "  \"products\": [\n" +
            "    {\n" +
            "      \"refNo\": \"461952819311\",\n" +
            "      \"order\": [\n" +
            "        1042, 850, 1042, 850, 0, 0, 0,\n" +
            "        1042, 850, 1042, 850, 0, 0, 0,\n" +
            "        1042, 850, 1042, 850, 0, 0, 0\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"refNo\": \"315452831481\",\n" +
            "      \"order\": [\n" +
            "        \"10+\", \"0\", \"0\", \"0\", \"8+\", \"0\", \"0\",\n" +
            "        \"0\", \"0\", \"0\", \"0\", \"0\", \"0\", \"0\",\n" +
            "        \"0\", \"0\", \"20+\", \"0\", \"0\", \"0\", \"0\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    // Task 1.
    // build example json object with java 7 jsonp api
    //

    @Test
    public void buildWithJsonp() throws Exception {
        JsonObject json = Json.createObjectBuilder()
                .build();

        Assertions.assertThat(json.toString()).isEqualTo(text);
    }


    // Task 2.
    // parse given text into domain objects using java 7 jsonp api
    //
    // note: products.order can by an array of strings with optional '+' and '*' modifiers
    // use methods level(order) deliveryOn(order) to parse its elements.
    //
    // note: products.order are ordered levels of product in next 3 weeks,
    // use tool DomainObjects.DateIncrementer for generating delivery date for each element of products.order
    //
    // try to prepare code for any potential data violations in incoming json file
    // - missing fields created or products
    // - wrong types of fields created or products
    // - invalid date format in field created
    // - missing fields products.refNo or products.orders
    // - wrong types of fields products.refNo or products.orders
    // - invalid content of any products.orders
    //
    // each violations should include path to wrong field ex.: products[3].orders[14] cannot parse as long
    //
    // all violations should be accumulated and available after json processing for review
    // in other words we don't want 'fail fast' strategy
    //
    //
    // empty products array is an valid state, but orders for less than 3*7 days generates a warning.
    // in any case, we should process all correct products
    //

    @Test
    public void createDomainObjectsFromJson() throws Exception {
        JsonObject doc = Json.createReader(new StringReader(text)).readObject();
        List<String> violations = new LinkedList<>();

        LocalDate created = LocalDate.parse(doc.getString("created"), DateTimeFormatter.ofPattern("uuuu-MM-dd"));
        CallofDocument callofDocument = new CallofDocument(created,
                doc.getJsonArray("products").getValuesAs(JsonObject.class).stream()
                        .map(product -> {
                            DateIncrementer date = new DateIncrementer(created);
                            // TODO: finish json processing

                            return new ProductDemand(null, null);
                        })
                        .collect(Collectors.toList())
        );
        Assertions.assertThat(violations).isEmpty();
        Assertions.assertThat(callofDocument).isNotNull()
                .extracting(CallofDocument::getCreated, CallofDocument::getDemands)
                .doesNotContainNull();
    }

    public static DeliveryOn deliveryOn(String order) {
        return order.contains("+") ? DeliveryOn.END_OF_DAY : DeliveryOn.START_OF_DAY;
    }

    public static long level(String order) {
        return Long.parseLong(order.replaceAll("\\*", "").replaceAll("\\+", ""));
    }
}
