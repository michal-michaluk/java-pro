package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFun {

    @DataProvider
    public static Object[][] strings() {
        return new Object[][]{ {""}, {"text"}, {" | "} };
    }

    @Test(dataProvider = "strings")
    public void testName(String text) throws Exception {

        String concatenated = "prefix " + text + " suffix";

        String built = new StringBuilder()
                .append("prefix ")
                .append(text)
                .append(" suffix")
                .toString();

        String formatted = String.format("prefix %s suffix", text);

        System.out.println(formatted);
        Assertions.assertThat(concatenated)
                .isEqualTo(built)
                .isEqualTo(formatted);
    }

    @Test
    public void doTheSameInLoop() throws Exception {
        String[] strings = {"", "text", " | "};

        String concatenated = "";
        for (String text : strings) {
            concatenated += "prefix " + text + " suffix";
        }
        System.out.println(concatenated);

        StringBuilder builder = new StringBuilder();
        for (String text : strings) {
            builder.append("prefix ").append(text).append(" suffix");
        }
        System.out.println(builder.toString());

        Assertions.assertThat(concatenated).isEqualTo(builder.toString());
    }

    @Test(enabled = false)
    public void regexp() throws Exception {
        String text = "some string we 12.34 want match to regexp";

        Pattern p = Pattern.compile(".*(\\d+[.]?\\d*).*");

        Matcher matcher = p.matcher(text);
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group());

    }
}
