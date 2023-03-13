import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

enum RecurrenceOption {
    ONE_TIME, DAILY, WEEKLY, MONTHLY, ANNUAL;

    public LocalDate getNextDate(LocalDate currentDate) {
        switch (this) {
            case ONE_TIME:
                return null;
            case DAILY:
                return currentDate.plusDays(1);
            case WEEKLY:
                return currentDate.plusWeeks(1);
            case MONTHLY:
                return currentDate.plusMonths(1);
            case ANNUAL:
                return currentDate.plusYears(1);
            default:
                return null;
        }
    }
}