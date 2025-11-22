package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {
    public enum OrderStatus { OPEN, CLOSED }

    private final int id;
    private final Customer customer;
    private final List<MenuItem> items;
    private OrderStatus status;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.OPEN;
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<MenuItem> getItems() { return new ArrayList<>(items); }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public void addItem(MenuItem item) {
        if (status == OrderStatus.OPEN) items.add(item);
    }

    public int countItem(int menuItemId) {
        int count = 0;
        for (MenuItem m : items) if (m.getId() == menuItemId) count++;
        return count;
    }

    public void removeItem(int menuItemId, int quantity) {
        if (status != OrderStatus.OPEN) return;
        int removed = 0;
        Iterator<MenuItem> iter = items.iterator();
        while (iter.hasNext() && removed < quantity) {
            MenuItem m = iter.next();
            if (m.getId() == menuItemId) { iter.remove(); removed++; }
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem m : items) total += m.getPrice();
        return total;
    }

    @Override
    public String toString() {
        return id + " -> " + customer.getName() + " | Items: " + items.size() + " | Status: " + status;
    }
}
