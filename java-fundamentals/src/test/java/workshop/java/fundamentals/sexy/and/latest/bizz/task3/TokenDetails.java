package workshop.java.fundamentals.sexy.and.latest.bizz.task3;

public sealed interface TokenDetails {

    record Card(String rfid, String number) implements TokenDetails {}

    record Keychain(String rfid, String series) implements TokenDetails {}

    record Car(String modemId, String certificate) implements TokenDetails {}
}
