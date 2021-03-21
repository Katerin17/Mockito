package main.ru.job4j.tracker;

import junit.framework.TestCase;

import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionMockitoTest extends TestCase {

    public void testExecute() {
        Consumer<String> output = new StubOutput();
        Tracker tracker = new Tracker();

        Item first = tracker.add(new Item("new item"));
        Item second = tracker.add(new Item("new item"));

        FindByNameAction findByNameAction = new FindByNameAction();

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("new item");

        findByNameAction.execute(input, tracker, output);

        assertThat(output.toString(), is("Name: new item| Id: "
                + first.getId() + "Name: new item| Id: " + second.getId()));
    }
}
