package ui;

import domain.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Restaurant restaurant = new Restaurant("Delicias Colombianas");

            // Datos iniciales para probar
            restaurant.addTable(2);
            restaurant.addTable(4);
            restaurant.addTable(6);

            restaurant.addMenuItem("Bandeja Paisa", "Carne, frijoles, arroz, huevo", 35000);
            restaurant.addMenuItem("Ajiaco", "Sopa típica", 25000);

            boolean running = true;
            while (running) {
                try {
                    System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
                    System.out.println("1. Manage Menu Items");
                    System.out.println("2. Manage Tables");
                    System.out.println("3. Manage Customers");
                    System.out.println("4. Manage Orders");
                    System.out.println("5. Exit");
                    System.out.print("Choose an option: ");
                    String option = scanner.nextLine();

                    switch (option) {
                        case "1": manageMenu(restaurant); break;
                        case "2": manageTables(restaurant); break;
                        case "3": manageCustomers(restaurant); break;
                        case "4": manageOrders(restaurant); break;
                        case "5": running = false; break;
                        default: System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            System.out.println("Program finished.");
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }

    // ---------------- MENU ----------------
    private static void manageMenu(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- MENU MANAGEMENT ---");
                System.out.println("1. Add Menu Item");
                System.out.println("2. List Menu Items");
                System.out.println("3. Delete Menu Item");
                System.out.println("4. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            System.out.print("Enter name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter description: ");
                            String desc = scanner.nextLine();
                            System.out.print("Enter price: ");
                            double price = Double.parseDouble(scanner.nextLine());
                            MenuItem mi = r.addMenuItem(name, desc, price);
                            System.out.println("Added: " + mi);
                        } catch (Exception e) {
                            System.out.println("Error adding menu item.");
                        }
                        break;

                    case "2":
                        try {
                            listMenuItems(r);
                        } catch (Exception e) {
                            System.out.println("Error listing menu.");
                        }
                        break;

                    case "3":
                        try {
                            listMenuItems(r);
                            System.out.print("Enter menu ID to delete: ");
                            int mid = Integer.parseInt(scanner.nextLine());
                            boolean removed = r.deleteMenuItem(mid);
                            System.out.println(removed ? "Deleted." : "Not found.");
                        } catch (Exception e) {
                            System.out.println("Error deleting menu item.");
                        }
                        break;

                    case "4": loop = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void listMenuItems(Restaurant r) {
        try {
            System.out.println("\n--- MENU ITEMS ---");
            for (MenuItem m : r.getMenu()) System.out.println(m);
        } catch (Exception e) {
            System.out.println("Error printing menu.");
        }
    }

    // ---------------- TABLES ----------------
    private static void manageTables(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- TABLE MANAGEMENT ---");
                System.out.println("1. Add Table");
                System.out.println("2. List Tables");
                System.out.println("3. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            System.out.print("Enter number of seats: ");
                            int seats = Integer.parseInt(scanner.nextLine());
                            Table t = r.addTable(seats);
                            System.out.println("Added table: " + t);
                        } catch (Exception e) {
                            System.out.println("Error adding table.");
                        }
                        break;

                    case "2":
                        try {
                            System.out.println("\n--- TABLES ---");
                            for (Table table : r.getTables()) System.out.println(table);
                        } catch (Exception e) {
                            System.out.println("Error listing tables.");
                        }
                        break;

                    case "3": loop = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- CUSTOMERS ----------------
    private static void manageCustomers(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- CUSTOMER MANAGEMENT ---");
                System.out.println("1. Add Customer (auto-assign first free table)");
                System.out.println("2. List Customers");
                System.out.println("3. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            System.out.print("Enter customer name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter phone: ");
                            String phone = scanner.nextLine();
                            Customer c = r.addCustomer(name, phone);
                            System.out.println("Added: " + c);
                        } catch (Exception e) {
                            System.out.println("Error adding customer.");
                        }
                        break;

                    case "2":
                        try {
                            System.out.println("\n--- CUSTOMERS ---");
                            for (Customer cu : r.getCustomers()) System.out.println(cu);
                        } catch (Exception e) {
                            System.out.println("Error listing customers.");
                        }
                        break;

                    case "3": loop = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- ORDERS ----------------
    private static void manageOrders(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
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
                    case "1":
                        try {
                            System.out.println("Customers:");
                            for (Customer cu : r.getCustomers()) System.out.println(cu);
                            System.out.print("Enter customer ID: ");
                            int cid = Integer.parseInt(scanner.nextLine());
                            Order o = r.createOrder(cid);
                            System.out.println(o != null ? "Order created: " + o : "Customer not found.");
                        } catch (Exception e) {
                            System.out.println("Error creating order.");
                        }
                        break;

                    case "2":
                        try {
                            System.out.println("Orders:");
                            for (Order or : r.getOrders()) System.out.println(or);

                            System.out.print("Enter order ID: ");
                            int oid = Integer.parseInt(scanner.nextLine());

                            System.out.println("Menu items:");
                            for (MenuItem mi : r.getMenu()) System.out.println(mi);

                            System.out.print("Enter menu item ID to add (0 to stop): ");
                            while (true) {
                                try {
                                    int mid = Integer.parseInt(scanner.nextLine());
                                    if (mid == 0) break;

                                    boolean ok = r.addItemToOrder(oid, mid);
                                    System.out.println(ok ? "Item added." : "Failed to add (order or item not found).");
                                    System.out.print("Add another (menu ID) or 0 to stop: ");
                                } catch (Exception e) {
                                    System.out.println("Invalid input.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error adding items.");
                        }
                        break;

                    case "3":
                        try {
                            System.out.println("Orders:");
                            for (Order or : r.getOrders()) System.out.println(or);

                            System.out.print("Enter order ID: ");
                            int oid2 = Integer.parseInt(scanner.nextLine());

                            System.out.println("Order items:");
                            Order ord = r.getOrderById(oid2);

                            if (ord == null) {
                                System.out.println("Order not found.");
                                break;
                            }

                            for (MenuItem mi : ord.getItems()) System.out.println(mi);

                            System.out.print("Enter menu item ID to remove: ");
                            int rid = Integer.parseInt(scanner.nextLine());
                            System.out.print("How many to remove?: ");
                            int qty = Integer.parseInt(scanner.nextLine());

                            r.removeItemFromOrder(oid2, rid, qty);
                            System.out.println("Removed (if present).");
                        } catch (Exception e) {
                            System.out.println("Error removing item.");
                        }
                        break;

                    case "4":
                        try {
                            System.out.println("Orders:");
                            for (Order or : r.getOrders()) System.out.println(or);

                            System.out.print("Enter order ID to close: ");
                            int oid3 = Integer.parseInt(scanner.nextLine());

                            boolean closed = r.closeOrder(oid3);
                            if (closed) {
                                Order closedOrder = r.getOrderById(oid3);
                                System.out.println("Order closed. Total: $" + closedOrder.calculateTotal());
                            } else System.out.println("Order not found.");
                        } catch (Exception e) {
                            System.out.println("Error closing order.");
                        }
                        break;

                    case "5":
                        try {
                            System.out.println("\n--- ORDERS ---");
                            for (Order or : r.getOrders()) System.out.println(or);
                        } catch (Exception e) {
                            System.out.println("Error listing orders.");
                        }
                        break;

                    case "6": loop = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
