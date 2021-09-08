package workshop.java.fundamentals.sexy.and.latest.bizz.task1;


public record TokenSnapshotV1(String tokenId, DetailsV1 details, StatusV1 status) {

    static record DetailsV1(String type, String rfid, String number, String series, String modemId) {
    }

    enum StatusV1 {
        VALID,
        INVALID;

        public static StatusV1 from(Token token) {
            return token.isActive() ? VALID : INVALID;
        }
    }
}
