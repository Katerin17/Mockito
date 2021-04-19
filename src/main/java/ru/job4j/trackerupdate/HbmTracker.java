package main.java.ru.job4j.trackerupdate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            item.setId(Integer.parseInt(id));
            session.update(item);
            session.getTransaction().commit();
        }
        return findById(id).equals(item);
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) != null) {
            try (Session session = sf.openSession()) {
                session.beginTransaction();
                Item itemForDelete = new Item(null, null, null);
                itemForDelete.setId(Integer.parseInt(id));
                session.delete(itemForDelete);
                session.getTransaction().commit();
            }
        }
        return findById(id) == null;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from main.java.ru.job4j.trackerupdate.Item", Item.class).list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "from main.java.ru.job4j.trackerupdate.Item i where i.name = :name", Item.class);
            query.setParameter("name", key);
            result = query.list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Item.class, Integer.parseInt(id));
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
