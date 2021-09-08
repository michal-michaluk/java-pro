package workshop.java.fundamentals.sexy.and.latest.bizz.task2;


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
            // TODO: implement all cases
            TokenDetails.Card card = (TokenDetails.Card) details;
            return new DetailsV1(
                    "CARD",
                    card.getRfid(),
                    card.getNumber(),
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
