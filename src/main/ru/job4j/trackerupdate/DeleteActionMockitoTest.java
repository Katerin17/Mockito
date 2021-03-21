package main.ru.job4j.trackerupdate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DeleteActionMockitoTest {

    @Test
    public void testExecute() {
        Output output = new StubOutput();
        Tracker tracker = Tracker.getInstance();
        tracker.add(new Item("Delete item new"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Delete Item ===" + ln + "Item was deleted." + ln));
        assertThat(tracker.findById(1), is(nullValue()));
    }
}
