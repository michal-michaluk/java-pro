package workshop.java.advanced.dsl;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedMap;

public class DomainObjects {

    @Value
    public static class CallofDocument {
        private LocalDate created;
        private List<ProductDemand> demands;
    }

    @Value
    public static class ProductDemand {
        private String refNo;
        private SortedMap<LocalDate, DailyDemand> days;

        public void fancyBusinessLogic() {
            throw new IllegalStateException("Please not call it!");
        }
    }

    public enum DeliveryOn {
        START_OF_DAY, END_OF_DAY
    }

    @Value
    public static class DailyDemand {
        private LocalDate date;
        private long level;
        private DeliveryOn deliveryOn;
    }

    // util
    public static class DateIncrementer {
        private LocalDate date;

        public DateIncrementer(LocalDate date) {
            this.date = date;
        }

        public LocalDate getAndIncrement() {
            LocalDate current = date;
            date = date.plusDays(1);
            return current;
        }

        public LocalDate last() {
            return date;
        }
    }

    private DomainObjects() {
    }
}
