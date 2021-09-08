package workshop.java.fundamentals.basics;

import org.intellij.lang.annotations.Language;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringNewFeatures {

    @Test
    public void textBlocks() {
        @Language("JSON") var block = """
                {
                  "@type": "TokenSnapshotV1",
                  "tokenId": "048DFAB2153C80",
                  "ownership": {
                    "emsp": "Nuon NL",
                    "pcu": "92112"
                  },
                  "details": {
                    "rfid": "048DFAB2153C80",
                    "number": "NL-NUO-C01074651-B"
                  },
                  "status": "VALID"
                }""";

        @Language("JSON") var concatenated = "{\n" +
                "  \"@type\": \"TokenSnapshotV1\",\n" +
                "  \"tokenId\": \"048DFAB2153C80\",\n" +
                "  \"ownership\": {\n" +
                "    \"emsp\": \"Nuon NL\",\n" +
                "    \"pcu\": \"92112\"\n" +
                "  },\n" +
                "  \"details\": {\n" +
                "    \"rfid\": \"048DFAB2153C80\",\n" +
                "    \"number\": \"NL-NUO-C01074651-B\"\n" +
                "  },\n" +
                "  \"status\": \"VALID\"\n" +
                "}";

        assertThat(block).isEqualTo(concatenated);
    }

    @Test
    public void formatted() {
        @Language("JSON") var block = """
                {
                  "@type": "TokenSnapshotV1",
                  "tokenId": "048DFAB2153C80",
                  "ownership": {
                    "emsp": "%s",
                    "pcu": "%s"
                  },
                  "details": {
                    "rfid": "04153C80",
                    "number": "NUO-C0107-B"
                  },
                  "status": "VALID"
                }""".formatted("emsp", "pcu");

        assertThat(block)
                .contains("\"emsp\": \"emsp\"")
                .contains("\"pcu\": \"pcu\"");
    }

    @Test
    public void existingReplace() {
        @Language("JSON") var block = """
                {
                  "@type": "TokenSnapshotV1",
                  "tokenId": "048DFAB2153C80",
                  "ownership": {
                    "emsp": "$par1",
                    "pcu": "$par2"
                  },
                  "details": {
                    "rfid": "04153C80",
                    "number": "NUO-C0107-B"
                  },
                  "status": "VALID"
                }"""
                .replace("$par1", "emsp")
                .replace("$par2", "pcu");

        assertThat(block)
                .contains("\"emsp\": \"emsp\"")
                .contains("\"pcu\": \"pcu\"");
    }

    @Test
    public void textBlocksEscaping() {
        String escaped = """
                windows \\cr\\lf \r
                no new line \
                at end of line
                unnecessary \n new line
                whitespaces at end     
                important whitespaces at end      \s
                one "
                two ""
                tree \"""
                or   "\""
                or   ""\"
                """;
        System.out.println(escaped);
    }

    @Test
    public void indent() {
        String indented = """
                 one
                  two
                   tree
                """.indent(2);

        String unindented = """
                   one
                    two
                     tree
                """;

        assertThat(unindented).isEqualTo(indented);

    }

    @Test
    public void transform() {
        String transformed = "some string".transform(String::toUpperCase);

        assertThat(transformed).isEqualTo("SOME STRING");
    }

    @Test
    public void repeat() {
        String repeated = "ping...pong ".repeat(3);

        assertThat(repeated).isEqualTo("ping...pong ping...pong ping...pong ");
    }

    @Test
    public void translateEscapes() {
        var text = "line1\nline2\\nline3";
        assertThat(text).isEqualTo("""
                line1
                line2\\nline3""");
        System.out.println(text);

        System.out.println();

        assertThat(text.translateEscapes()).isEqualTo("""
                line1
                line2
                line3""");
        System.out.println(text.translateEscapes());
    }
}
