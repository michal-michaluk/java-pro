package workshop.java.fundamentals.sexy.and.latest.bizz.task2;

import java.util.Objects;

public abstract class TokenDetails {

    static Card card(String rfid, String number) {
        return new Card(rfid, number);
    }

    static Keychain keychain(String rfid, String series) {
        return new Keychain(rfid, series);
    }

    static Car car(String modemId, String certificate) {
        return new Car(modemId, certificate);
    }

    public static class Card extends TokenDetails {
        private final String rfid;
        private final String number;

        public Card(String rfid, String number) {
            this.rfid = rfid;
            this.number = number;
        }

        public String getRfid() {
            return rfid;
        }

        public String getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Card card = (Card) o;
            return Objects.equals(rfid, card.rfid) && Objects.equals(number, card.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rfid, number);
        }

        @Override
        public String toString() {
            return "Card{" +
                    "rfid='" + rfid + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

    public static class Keychain extends TokenDetails {
        private final String rfid;
        private final String series;

        public Keychain(String rfid, String series) {
            this.rfid = rfid;
            this.series = series;
        }

        public String getRfid() {
            return rfid;
        }

        public String getNumber() {
            return series;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Keychain keychain = (Keychain) o;
            return Objects.equals(rfid, keychain.rfid) && Objects.equals(series, keychain.series);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rfid, series);
        }

        @Override
        public String toString() {
            return "Card{" +
                    "rfid='" + rfid + '\'' +
                    ", series='" + series + '\'' +
                    '}';
        }
    }

    public static class Car extends TokenDetails {
        private final String modemId;
        private final String certificate;

        public Car(String modemId, String certificate) {
            this.modemId = modemId;
            this.certificate = certificate;
        }

        public String getModemId() {
            return modemId;
        }

        public String getCertificate() {
            return certificate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Car car = (Car) o;
            return Objects.equals(modemId, car.modemId) && Objects.equals(certificate, car.certificate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(modemId, certificate);
        }

        @Override
        public String toString() {
            return "Car{" +
                    "modemId='" + modemId + '\'' +
                    ", certificate='" + certificate + '\'' +
                    '}';
        }
    }
}
