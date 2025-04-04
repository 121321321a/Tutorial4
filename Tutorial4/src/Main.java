public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(1, "Dell Laptop", "Laptop", 3000, 10);
        Product p2 = new Product(2, "Logitech Mouse", "Mouse", 150, 20);

        Customer c1 = new Customer(1, "Ali", "Veli", "ali@example.com", true);

        Product[] orderProducts = {p1, p2};
        int[] quantities = {1, 2};

        Order order1 = new Order(1, c1, orderProducts, quantities, "2025-04-04", "New");
        order1.applyDiscount();
        order1.updateStock();
        order1.displayDetails();

        order1.setStatus("Completed");
        order1.displayDetails();

        System.out.println("Laptops in stock:");
        if ("Laptop".equals(p1.getCategory())) {
            p1.displayInfo();
        }
    }
}

class Product {
    private final int id;
    private final String name;
    private final String category;
    private final double price;
    private int stockQuantity;

    public Product(int id, String name, String category, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int qty) { this.stockQuantity = qty; }

    public void displayInfo() {
        System.out.println("Product ID: " + id + ", Name: " + name + ", Category: " + category + ", Price: " + price + ", In stock: " + stockQuantity);
    }
}

class Customer {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final boolean isLoyal;

    public Customer(int id, String firstName, String lastName, String email, boolean isLoyal) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isLoyal = isLoyal;
    }

    public int getId() { return id; }
    public boolean isLoyal() { return isLoyal; }

    public void displayInfo() {
        System.out.println("Customer: " + firstName + " " + lastName + ", Email: " + email + ", Loyal: " + isLoyal);
    }
}

class Order {
    private final int id;
    private final Customer customer;
    private final Product[] products;
    private final int[] quantities;
    private final String orderDate;
    private String status;

    public Order(int id, Customer customer, Product[] products, int[] quantities, String date, String status) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.quantities = quantities;
        this.orderDate = date;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void applyDiscount() {
        if (customer.isLoyal()) {
            double total = calculateTotal();
            System.out.println("Total before discount: " + total);
            System.out.println("Discount: 10%");
            System.out.println("Total after discount: " + total * 0.9);
        }
    }

    public void updateStock() {
        for (int i = 0; i < products.length; i++) {
            int currentStock = products[i].getStockQuantity();
            products[i].setStockQuantity(currentStock - quantities[i]);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < products.length; i++) {
            total += products[i].getPrice() * quantities[i];
        }
        return total;
    }

    public void displayDetails() {
        System.out.println("Order ID: " + id + ", Status: " + status + ", Date: " + orderDate);
        customer.displayInfo();
        for (int i = 0; i < products.length; i++) {
            products[i].displayInfo();
            System.out.println("Quantity: " + quantities[i]);
        }
        System.out.println("Total: " + calculateTotal());
        System.out.println("---------------------------");
    }
}
