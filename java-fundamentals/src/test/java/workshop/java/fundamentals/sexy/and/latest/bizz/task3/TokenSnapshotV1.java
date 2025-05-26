package workshop.java.fundamentals.sexy.and.latest.bizz.task3;

public record TokenSnapshotV1(String tokenId, DetailsV1 details, StatusV1 status) {

    public static TokenSnapshotV1 from(Token token) {
        return new TokenSnapshotV1(
                token.getTokenId(),
                switch (token.getDetails()) {
                    case TokenDetails.Card card -> DetailsV1.from(card.rfid(), card.number());
                    case TokenDetails.Keychain keychain -> DetailsV1.keychanin(keychain);
                    case TokenDetails.Car car -> DetailsV1.from(car);
                },
                StatusV1.from(token)
        );
    }

    record DetailsV1(String type, String rfid, String number, String series, String modemId) {

        private static DetailsV1 keychanin(TokenDetails.Keychain keychain) {
            return new DetailsV1(
                    "Keychain",
                    keychain.rfid(),
                    null,
                    keychain.series(),
                    null
            );
        }

        private static DetailsV1 from(String rfid, String number) {
            return new DetailsV1(
                    "CARD",
                    rfid, number,
                    null, null
            );
        }

        private static DetailsV1 from(TokenDetails.Car v) {
            return new DetailsV1(
                    "Car",
                    null, null,
                    null, v.modemId()
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
