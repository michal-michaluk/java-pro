package workshop.java.fundamentals.sexy.and.latest.bizz.task2;

public sealed interface TokenDetails permits TokenDetails.Card, TokenDetails.Keychain, TokenDetails.Car, TokenDetails.Virtual {
    record Card(String rfid, String number) implements TokenDetails {
    }

    record Keychain(String rfid, String series) implements TokenDetails {
    }

    record Car(String modemId, String certificate) implements TokenDetails {
    }

    record Virtual(String rfid) implements TokenDetails {
    }
}
