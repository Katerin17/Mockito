package main.ru.job4j.tracker;

import org.junit.Test;

import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FindByIdActionMockitoTest {

    @Test
    public void testExecute() {
        Consumer<String> output = new StubOutput();
        Tracker tracker = new Tracker();

        Item i = tracker.add(new Item("new item"));
        FindByIdAction findByIdAction = new FindByIdAction();

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(i.getId());

        findByIdAction.execute(input, tracker, output);

        assertThat(output.toString(), is("Name: new item| Id: " + i.getId()));
    }
}
