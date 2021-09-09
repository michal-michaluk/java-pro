package workshop.java.fundamentals.sexy.and.latest.bizz.task2;

import org.jetbrains.annotations.NotNull;

public record TokenSnapshotV1(String tokenId, DetailsV1 details, StatusV1 status) {

    public static TokenSnapshotV1 from(Token token) {
        return new TokenSnapshotV1(
                token.getTokenId(),
                DetailsV1.from(token.getDetails()),
                StatusV1.from(token)
        );
    }

    static record DetailsV1(String type, String rfid, String number, String series, String modemId) {
        public static DetailsV1 from(TokenDetails details) {
            return switch (details) {
                case TokenDetails.Card card -> toCard(card);
                case TokenDetails.Keychain card -> toKeychain(card);
                case TokenDetails.Car card -> toCar(card);
                case TokenDetails.Virtual virtual -> toVirtual(virtual);
            };
        }

        @NotNull
        private static TokenSnapshotV1.DetailsV1 toCard(TokenDetails.Card card) {
            return new DetailsV1(
                    "CARD",
                    card.rfid(),
                    card.number(),
                    null,
                    null
            );
        }

        @NotNull
        private static TokenSnapshotV1.DetailsV1 toKeychain(TokenDetails.Keychain card) {
            return new DetailsV1(
                    "KEYCHAIN",
                    card.rfid(),
                    null,
                    card.series(),
                    null
            );
        }

        @NotNull
        private static TokenSnapshotV1.DetailsV1 toCar(TokenDetails.Car card) {
            return new DetailsV1(
                    "CAR",
                    null,
                    null,
                    null,
                    card.modemId()
            );
        }

        @NotNull
        private static TokenSnapshotV1.DetailsV1 toVirtual(TokenDetails.Virtual virtual) {
            return new DetailsV1(
                    "VIRTUAL",
                    virtual.rfid(),
                    null,
                    null,
                    null
            );
        }
    }

    enum StatusV1 {
        VALID,
        INVALID;

        public static StatusV1 from(Token token) {
            return token.isActive() ? VALID : INVALID;
        }
    }
}
