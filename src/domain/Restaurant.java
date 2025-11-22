package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Central class that stores restaurant menu, tables and orders.
 */
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<MenuItem> menu;
    private List<Table> tables;
    private List<Order> orders;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // --- Name ---
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // --- Menu operations ---
    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public Optional<MenuItem> findMenuItemById(String id) {
        return menu.stream()
                   .filter(m -> m.getId().equals(id))
                   .findFirst();
    }


    // --- Tables operations ---
    public void addTable(Table table) {
        tables.add(table);
    }

    public List<Table> getTables() {
        return tables;
    }


    // --- Orders operations ---
    public Order createOrder(Customer customer) {
        Order order = new Order(customer);
        orders.add(order);
        return order;
    }

    public Optional<Order> findOrderById(UUID id) {
        return orders.stream()
                     .filter(o -> o.getId().equals(id))
                     .findFirst();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
