package ui;

import domain.*;
import java.util.*;
import java.math.BigDecimal;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static List<Customer> customerList = new ArrayList<>();
    private static List<MenuItem> menuItemList = new ArrayList<>();
    private static List<Table> tableList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();

    private static int customerCounter = 1;
    private static int menuItemCounter = 1;
    private static int tableCounter = 1;
    private static int orderCounter = 1;

    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant("My Restaurant");

        boolean running = true;

        while (running) {
            System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
            System.out.println("1. Manage Menu Items");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Tables");
            System.out.println("4. Manage Orders");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    manageMenu(restaurant);
                    break;
                case "2":
                    manageCustomers(restaurant);
                    break;
                case "3":
                    manageTables(restaurant);
                    break;
                case "4":
                    manageOrders(restaurant);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Program finished.");
    }

    // =========================================
    // MÉTODOS PARA BUSCAR POR SIMPLE ID
    // =========================================
    private static Customer getCustomerById(int id) {
        return (id >= 1 && id <= customerList.size()) ? customerList.get(id - 1) : null;
    }

    private static MenuItem getMenuItemById(int id) {
        return (id >= 1 && id <= menuItemList.size()) ? menuItemList.get(id - 1) : null;
    }

    private static Table getTableById(int id) {
        return (id >= 1 && id <= tableList.size()) ? tableList.get(id - 1) : null;
    }

    private static Order getOrderById(int id) {
        return (id >= 1 && id <= orderList.size()) ? orderList.get(id - 1) : null;
    }

    // ================================================================
    // MENU ITEMS
    // ================================================================
    private static void manageMenu(Restaurant restaurant) {
        boolean loop = true;

        while (loop) {
            System.out.println("\n--- MENU MANAGEMENT ---");
            System.out.println("1. Add Menu Item");
            System.out.println("2. List Menu Items");
            System.out.println("3. Delete Menu Item");
            System.out.println("4. Back");
            System.out.print("Choose: ");

            String op = scanner.nextLine();

            switch (op) {
                case "1": addMenuItem(restaurant); break;
                case "2": listMenuItems(restaurant); break;
                case "3": deleteMenuItem(restaurant); break;
                case "4": loop = false; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void addMenuItem(Restaurant restaurant) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter description: ");
            String desc = scanner.nextLine();

            System.out.print("Enter price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine());

            String autoId = "ITEM-" + menuItemCounter;

            MenuItem item = new MenuItem(autoId, name, desc, price);
            restaurant.addMenuItem(item);

            menuItemList.add(item);

            System.out.println("Menu item added with ID: " + menuItemCounter);

            menuItemCounter++;

        } catch (Exception e) {
            System.out.println("Error adding menu item.");
        }
    }

    private static void listMenuItems(Restaurant restaurant) {
        System.out.println("\n--- MENU ITEMS ---");
        for (int i = 0; i < menuItemList.size(); i++) {
            MenuItem m = menuItemList.get(i);
            int id = i + 1;
            System.out.println(id + " -> " + m.getName() + " | $" + m.getPrice());
        }
    }

    private static void deleteMenuItem(Restaurant restaurant) {
        System.out.print("Enter SIMPLE ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            MenuItem item = getMenuItemById(id);

            if (item != null) {
                menuItemList.remove(id - 1);
                restaurant.getMenu().remove(item);
                System.out.println("Menu item removed.");
            } else {
                System.out.println("Invalid ID.");
            }

        } catch (Exception e) {
            System.out.println("Error deleting item.");
        }
    }

    // ================================================================
    // CUSTOMERS
    // ================================================================
    private static void manageCustomers(Restaurant restaurant) {
        boolean loop = true;

        while (loop) {
            System.out.println("\n--- CUSTOMER MANAGEMENT ---");
            System.out.println("1. Add Customer");
            System.out.println("2. List Customers");
            System.out.println("3. Back");
            System.out.print("Choose: ");

            String op = scanner.nextLine();

            switch (op) {
                case "1": addCustomer(restaurant); break;
                case "2": listCustomers(); break;
                case "3": loop = false; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void addCustomer(Restaurant restaurant) {
        try {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();

            // Mostrar mesas disponibles
            System.out.println("\nAvailable tables:");
            for (int i = 0; i < tableList.size(); i++) {
                Table t = tableList.get(i);
                if (!t.isOccupied()) {
                    System.out.println((i+1) + " -> Table #" + t.getId() + " | Seats: " + t.getSeats());
                }
            }

            System.out.print("Enter table SIMPLE ID to assign: ");
            int tableId = Integer.parseInt(scanner.nextLine());

            Table table = getTableById(tableId);
            if (table == null) {
                System.out.println("Invalid table ID.");
                return;
            }
            if (table.isOccupied()) {
                System.out.println("This table is already occupied!");
                return;
            }

            Customer customer = new Customer(name, phone);
            customer.setTable(table);
            table.setOccupied(true);

            customerList.add(customer);

            System.out.println("Customer added with SIMPLE ID: " + customerCounter + " and assigned Table #" + table.getId());
            customerCounter++;

        } catch (Exception e) {
            System.out.println("Error adding customer.");
        }
    }

    private static void listCustomers() {
        System.out.println("\n--- CUSTOMERS ---");
        for (int i = 0; i < customerList.size(); i++) {
            Customer c = customerList.get(i);
            String tableInfo = (c.getTable() != null) ? " | Table #" + c.getTable().getId() : "";
            System.out.println((i + 1) + " -> " + c.getName() + " | phone=" + c.getPhone() + tableInfo);
        }
    }

    // ================================================================
    // TABLES
    // ================================================================
    private static void manageTables(Restaurant restaurant) {
        boolean loop = true;

        while (loop) {
            System.out.println("\n--- TABLE MANAGEMENT ---");
            System.out.println("1. Add Table");
            System.out.println("2. List Tables");
            System.out.println("3. Back");
            System.out.print("Choose: ");

            String op = scanner.nextLine();

            switch (op) {
                case "1": addTable(restaurant); break;
                case "2": listTables(restaurant); break;
                case "3": loop = false; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void addTable(Restaurant restaurant) {
        try {
            System.out.print("Enter number of seats: ");
            int seats = Integer.parseInt(scanner.nextLine());

            int assignedId = tableCounter;

            Table table = new Table(assignedId, seats);
            restaurant.addTable(table);

            tableList.add(table);

            System.out.println("Table added with SIMPLE ID: " + tableCounter);

            tableCounter++;

        } catch (Exception e) {
            System.out.println("Error adding table.");
        }
    }

    private static void listTables(Restaurant restaurant) {
        System.out.println("\n--- TABLES ---");
        for (int i = 0; i < tableList.size(); i++) {
            Table t = tableList.get(i);
            System.out.println((i + 1) + " -> Table #" + t.getId() +
                    " | Seats: " + t.getSeats() +
                    " | Occupied: " + t.isOccupied()
            );
        }
    }

    // ================================================================
    // ORDERS
    // ================================================================
    private static void manageOrders(Restaurant restaurant) {
        boolean loop = true;

        while (loop) {
            System.out.println("\n--- ORDERS MANAGEMENT ---");
            System.out.println("1. Create Order");
            System.out.println("2. Add Item to Order");
            System.out.println("3. Remove Item from Order");
            System.out.println("4. Close Order");
            System.out.println("5. List Orders");
            System.out.println("6. Back");
            System.out.print("Choose: ");

            String op = scanner.nextLine();

            switch (op) {
                case "1": createOrder(restaurant); break;
                case "2": addItemToOrder(restaurant); break;
                case "3": removeItemFromOrder(); break;
                case "4": closeOrder(); break;
                case "5": listOrders(); break;
                case "6": loop = false; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void createOrder(Restaurant restaurant) {
        try {
            listCustomers();
            System.out.print("Enter customer SIMPLE ID: ");
            int cid = Integer.parseInt(scanner.nextLine());

            Customer customer = getCustomerById(cid);
            if (customer == null) {
                System.out.println("Invalid customer.");
                return;
            }

            Order order = restaurant.createOrder(customer);
            orderList.add(order);

            System.out.println("Order created with SIMPLE ID: " + orderCounter);
            orderCounter++;

        } catch (Exception e) {
            System.out.println("Error creating order.");
        }
    }

    private static void addItemToOrder(Restaurant restaurant) {
        try {
            listOrders();
            System.out.print("Enter order SIMPLE ID: ");
            int oid = Integer.parseInt(scanner.nextLine());

            Order order = getOrderById(oid);
            if (order == null) {
                System.out.println("Invalid order.");
                return;
            }

            listMenuItems(restaurant);
            System.out.print("Enter menu item SIMPLE ID: ");
            int mid = Integer.parseInt(scanner.nextLine());

            MenuItem item = getMenuItemById(mid);
            if (item == null) {
                System.out.println("Invalid menu item.");
                return;
            }

            order.addItem(item);
            System.out.println("Item added to order.");

        } catch (Exception e) {
            System.out.println("Error adding item.");
        }
    }

    private static void removeItemFromOrder() {
        try {
            listOrders();
            System.out.print("Enter order SIMPLE ID: ");
            int oid = Integer.parseInt(scanner.nextLine());

            Order order = getOrderById(oid);
            if (order == null) {
                System.out.println("Invalid order.");
                return;
            }

            listMenuItems(null);
            System.out.print("Enter menu SIMPLE ID to remove: ");
            int mid = Integer.parseInt(scanner.nextLine());

            MenuItem item = getMenuItemById(mid);
            if (item == null) {
                System.out.println("Menu item not found.");
                return;
            }

            int count = order.countItem(item.getId());
            if (count == 0) {
                System.out.println("Item not found in order.");
                return;
            }

            System.out.println("This item appears " + count + " time(s) in the order.");
            System.out.print("How many do you want to remove? ");
            int toRemove = Integer.parseInt(scanner.nextLine());

            if (toRemove > count) toRemove = count;

            order.removeItem(item.getId(), toRemove);
            System.out.println(toRemove + " item(s) removed from order.");

        } catch (Exception e) {
            System.out.println("Error removing item.");
        }
    }

    private static void closeOrder() {
        try {
            listOrders();
            System.out.print("Enter order SIMPLE ID: ");
            int oid = Integer.parseInt(scanner.nextLine());

            Order order = getOrderById(oid);
            if (order == null) {
                System.out.println("Invalid order.");
                return;
            }

            order.setStatus(Order.OrderStatus.CLOSED);
            System.out.println("Order closed. Total: $" + order.calculateTotal());

        } catch (Exception e) {
            System.out.println("Error closing order.");
        }
    }

    private static void listOrders() {
        System.out.println("\n--- ORDERS ---");
        for (int i = 0; i < orderList.size(); i++) {
            Order o = orderList.get(i);
            System.out.println((i + 1) + " -> " +
                    o.getCustomer().getName() +
                    " | Items: " + o.getItems().size() +
                    " | Status: " + o.getStatus() +
                    " | Created: " + o.getCreatedAt()
            );
        }
    }
}
