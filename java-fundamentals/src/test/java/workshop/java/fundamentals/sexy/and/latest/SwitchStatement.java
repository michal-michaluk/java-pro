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
            case String s && !s.isEmpty() -> String.format("String %s", s);
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

}
