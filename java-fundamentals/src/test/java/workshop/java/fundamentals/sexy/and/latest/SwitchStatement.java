package workshop.java.fundamentals.sexy.and.latest;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Optional;

public class SwitchStatement {

    private String switchExpression(String value) {
        String out = switch (value) {
            case null -> "null";
            case "poniedziałek" -> "znowu?";
            case "wtorek" -> "eee";
            case "środa", "czwartek" -> "...";
            case "piątek" -> "Yeeeeeeeee";
            case "sobota", "niedziela" -> "impra";
            default -> "?";
        };
        return out;
    }

    private String switchExpressionWithPatternMatching(Object obj) {
        return switch (obj) {
            case null -> "null";
            case Integer i -> String.format("int %d", i);
            case Byte b -> String.format("byte %d", b);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s when !s.isEmpty() -> String.format("String %s", s);
            case String s -> "empty String";
            default -> String.format("Object %s", obj);
        };
    }

    @Test
    public void testSwitchExpression() {
        Assertions.assertThat(switchExpressionWithPatternMatching("Hi")).isEqualTo("String Hi");
        Assertions.assertThat(switchExpressionWithPatternMatching("")).isEqualTo("empty String");
        Assertions.assertThat(switchExpressionWithPatternMatching(2)).isEqualTo("int 2");
        Assertions.assertThat(switchExpressionWithPatternMatching(Optional.empty())).isEqualTo("Object Optional.empty");
    }

    private String switchExpressionWithYield(Object obj) {
        return switch (obj) {
            case Integer i:
                yield String.format("int %d", i);
            case Byte b:
                yield String.format("byte %d", b);
            case Long l:
                yield String.format("long %d", l);
            case Double d:
                yield String.format("double %f", d);
            case String s:
                yield String.format("String %s", s);
            default:
                yield String.format("Object %s", obj);
        };
    }

    @Test
    public void testSwitchWithYield() {
        Assertions.assertThat(switchExpressionWithPatternMatching("Hi")).isEqualTo("String Hi");
        Assertions.assertThat(switchExpressionWithPatternMatching(2)).isEqualTo("int 2");
        Assertions.assertThat(switchExpressionWithPatternMatching(Optional.empty())).isEqualTo("Object Optional.empty");
    }


    sealed interface Interface {}

    record Impl1(String value) implements Interface {}

    record Impl2(long value) implements Interface {}

    @Test
    public void testSwitchRecordPatternMatching() {
        Assertions.assertThat(switchExpressionWithRecordPatternMatching(new Impl1("Hi Impl1"))).isEqualTo("Hi Impl1");
        Assertions.assertThat(switchExpressionWithRecordPatternMatching(new Impl2(44))).isEqualTo("44");
        Assertions.assertThat(switchExpressionWithRecordPatternMatching(null)).isEqualTo("null");
    }

    private String switchExpressionWithRecordPatternMatching(Interface obj) {
        return switch (obj) {
            case null -> "null";
            case Impl1(String value) -> value;
            case Impl2(long value) -> Long.toString(value);
        };
    }
}
