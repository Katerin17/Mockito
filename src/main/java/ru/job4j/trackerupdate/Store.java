package main.java.ru.job4j.trackerupdate;

import java.util.List;

public interface Store {
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
