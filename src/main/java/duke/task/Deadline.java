package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String task, String date) {
        super(task);
        this.date = setDate(date);
    }

    /**
     * Assigns value of LocalDateTime from String.
     *
     * @param s String specifying date and time in format 'dd-MM-yyyy HH:mm"'.
     * @return LocalDateTime from parsed String s.
     * @throws DateTimeParseException if s is in invalid format or specifies an invalid date time value.
     */
    public LocalDateTime setDate(String s) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid start/end date. Specify date in format 'dd-MM-yyyy HH:mm'.",
                    e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }

    /**
     * {@inheritDoc}
     *
     * @return String with type of task, description of deadline, and time to do by.
     */
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + printDate() + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    /**
     * Returns date in format 'MMM dd yyyy, HH:mm'.
     *
     * @return String of formatted localDateTime.
     */
    public String printDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return this.date.format(formatter);
    }
}
