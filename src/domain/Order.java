package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a customer order in the restaurant.
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum OrderStatus {
        OPEN,
        CLOSED,
        CANCELLED
    }

    private UUID id;
    private Customer customer;
    private List<MenuItem> items;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order(Customer customer) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    // Adds item to order
    public void addItem(MenuItem item) {
        items.add(item);
    }

    // Removes item by ID
    public boolean removeItemById(String itemId) {
        return items.removeIf(i -> i.getId().equals(itemId));
    }

    // Calculates total price
    public BigDecimal calculateTotal() {
        return items.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Dentro de la clase Order
    public int countItem(String itemId) {
        int count = 0;
        for (MenuItem m : items) {
            if (m.getId().equals(itemId)) {
                count++;
            }
        }
        return count;
    }

    public void removeItem(String itemId, int quantity) {
        int removed = 0;
        for (int i = 0; i < items.size() && removed < quantity;) {
            MenuItem m = items.get(i);
            if (m.getId().equals(itemId)) {
                items.remove(i);
                removed++;
            } else {
                i++;
            }
        }
    }

}
