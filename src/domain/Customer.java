package domain;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a customer who places orders in the restaurant.
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    // ID should not be modified normally; no setter provided

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Dentro de la clase Customer
    private Table table; // atributo

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return this.table;
    }

}
