package domain;

public class Customer {
    private final int id;
    private final String name;
    private final String phone;
    private Table table; // assigned table; can be null

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.table = null;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public Table getTable() { return table; }
    public void setTable(Table table) { this.table = table; }

    @Override
    public String toString() {
        String tableInfo = (table != null) ? (" | Table #" + table.getId()) : "";
        return id + " -> " + name + " | phone=" + phone + tableInfo;
    }
}
