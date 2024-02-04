package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.Ui;


public class DeadlineCommandTest {

    @Test
    public void execute_deadlineFormat_deadline() {
        DeadlineCommand dc = new DeadlineCommand("deadline return book /by 20-12-2020 10:01");
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        TaskList list = new TaskList();
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            assertEquals("D | 0 | return book | 20-12-2020 10:01", list.getTask(0).toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_deadlineWrongDateFormat_dukeException() {
        DeadlineCommand dc = new DeadlineCommand("deadline return book /by 20/12/2020 10:01");
        TaskList list = new TaskList();
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid start/end date. Specify date in format 'dd-MM-yyyy HH:mm'.", e.getMessage());
        }
    }
}
