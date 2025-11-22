package domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;

    private final List<MenuItem> menu;
    private final List<Table> tables;
    private final List<Customer> customers;
    private final List<Order> orders;

    // Counters de IDs (internos)
    private int menuIdCounter = 1;
    private int tableIdCounter = 1;
    private int customerIdCounter = 1;
    private int orderIdCounter = 1;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // ---------------------- Menu operations ----------------------
    public MenuItem addMenuItem(String name, String description, double price) {
        MenuItem item = new MenuItem(menuIdCounter++, name, description, price);
        menu.add(item);
        return item;
    }

    public List<MenuItem> getMenu() { return new ArrayList<>(menu); }

    public MenuItem getMenuItemById(int id) {
        for (MenuItem m : menu) if (m.getId() == id) return m;
        return null;
    }

    public boolean deleteMenuItem(int id) {
        return menu.removeIf(m -> m.getId() == id);
    }

    // ---------------------- Table operations ----------------------
    public Table addTable(int seats) {
        Table t = new Table(tableIdCounter++, seats);
        tables.add(t);
        return t;
    }

    public List<Table> getTables() { return new ArrayList<>(tables); }

    public Table getTableById(int id) {
        for (Table t : tables) if (t.getId() == id) return t;
        return null;
    }

    // Encuentra la primera mesa libre (no ocupada)
    public Table findFirstFreeTable() {
        for (Table t : tables) if (!t.isOccupied()) return t;
        return null;
    }

    // ---------------------- Customer operations ----------------------
    // Crea un cliente y le asigna la primera mesa libre si existe
    public Customer addCustomer(String name, String phone) {
        Customer c = new Customer(customerIdCounter++, name, phone);
        Table free = findFirstFreeTable();
        if (free != null) {
            c.setTable(free);
            free.setOccupied(true);
        }
        customers.add(c);
        return c;
    }

    public List<Customer> getCustomers() { return new ArrayList<>(customers); }

    public Customer getCustomerById(int id) {
        for (Customer c : customers) if (c.getId() == id) return c;
        return null;
    }

    // ---------------------- Order operations ----------------------
    public Order createOrder(int customerId) {
        Customer c = getCustomerById(customerId);
        if (c == null) return null;
        Order o = new Order(orderIdCounter++, c);
        orders.add(o);
        return o;
    }

    public Order getOrderById(int id) {
        for (Order o : orders) if (o.getId() == id) return o;
        return null;
    }

    public List<Order> getOrders() { return new ArrayList<>(orders); }

    public boolean addItemToOrder(int orderId, int menuItemId) {
        Order o = getOrderById(orderId);
        MenuItem m = getMenuItemById(menuItemId);
        if (o == null || m == null) return false;
        o.addItem(m);
        return true;
    }

    public boolean removeItemFromOrder(int orderId, int menuItemId, int quantity) {
        Order o = getOrderById(orderId);
        if (o == null) return false;
        o.removeItem(menuItemId, quantity);
        return true;
    }

    public boolean closeOrder(int orderId) {
        Order o = getOrderById(orderId);
        if (o == null) return false;
        o.setStatus(Order.OrderStatus.CLOSED);
        // liberar mesa asociada al cliente
        Customer c = o.getCustomer();
        if (c != null) {
            Table t = c.getTable();
            if (t != null) { t.setOccupied(false); c.setTable(null); }
        }
        return true;
    }

    public String getName() { return name; }
}