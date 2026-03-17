import java.util.*;

class Product {
    static int id = 2345;
    String pid;
    String name;
    String category;
    String desc;
    double price;
    double totalBill;
    int qnt;

    {
        this.pid = "PID" + id++;
    }

    Product(String name, String category, String desc, double price) {
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.price = price;
    }

    void displayProduct() {
        System.out.println("ProductId : " + pid);
        System.out.println("Name : " + this.name);
        System.out.println("Category : " + category);
        System.out.println("Desc : " + desc);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + qnt);
    }

    public void updateAmount(int qnt) {
        this.totalBill = this.price * qnt;
    }
}

class Amazon {

    static ArrayList<Product> clothList = new ArrayList<Product>();
    static ArrayList<Product> footwearList = new ArrayList<Product>();
    static ArrayList<Product> electronicsList = new ArrayList<Product>();
    static ArrayList<Product> stationaryList = new ArrayList<Product>();
    static ArrayList<Product> cart = new ArrayList<Product>();
    static ArrayList<ArrayList<Product>> orderList = new ArrayList<ArrayList<Product>>();

    static {
        Product p1 = new Product("Shirt", "clothing", "Plain Cotton Shirt", 1200);
        Product p2 = new Product("TShirt", "clothing", "Plain Cotton Tshirt", 800);
        Product p3 = new Product("Jeans", "clothing", "Denim Jeans", 1400);

        clothList.add(p1);
        clothList.add(p2);
        clothList.add(p3);
    }

    static {
        Product p1 = new Product("Shoes", "footwear", "Sneakers", 3200);
        Product p2 = new Product("Flipflop", "footwear", "Crocs", 1600);

        footwearList.add(p1);
        footwearList.add(p2);
    }

    static {
        Product p1 = new Product("Mobile", "Electronics", "SAMSUNG S25 Ultra", 120000);
        Product p2 = new Product("Airbuds", "Electronics", "Boat Nirvana", 1800);

        electronicsList.add(p1);
        electronicsList.add(p2);
    }

    static {
        Product p1 = new Product("Book", "Stationary", "Single Ruled Book", 50);
        Product p2 = new Product("Pen", "Stationary", "Ball pen", 15);
        Product p3 = new Product("Bag", "Stationary", "Laptop Bag", 1000);

        stationaryList.add(p1);
        stationaryList.add(p2);
        stationaryList.add(p3);
    }

    void homePage() {
        for (;;) {
            System.out.println("\n Welcome to Amazon\n");

            System.out.println("1: Clothes\n2: Footwear\n3: Electronics\n4: Stationary\n5: Cart\n6: Order\n7: Logout");

            System.out.println("Enter a option : ");
            Scanner sc = new Scanner(System.in);

            int option = sc.nextInt();

            switch (option) {
                case 1 -> productList(clothList, "clothing");
                case 2 -> productList(footwearList, "footwear");
                case 3 -> productList(electronicsList, "electronics");
                case 4 -> productList(stationaryList, "stationary");
                case 5 -> cart();
                case 6 -> order();
                default -> System.out.println("\n Invalid Response \n");
            }
        }
    }

    void productList(ArrayList<Product> list, String category) {

        outerloop: for (;;) {

            System.out.println("\n Category : " + category + " Module \n");

            for (Product ele : list) {
                ele.displayProduct();
            }

            System.out.println("\n Enter the pid : ");
            String pidUser = new Scanner(System.in).next().toUpperCase();

            boolean assump = false;

            for (Product ele : list) {
                if (pidUser.equals(ele.pid)) {
                    cart.add(ele);
                    assump = true;

                    System.out.println("\n Enter the quantity : ");
                    int qnt = new Scanner(System.in).nextInt();
                    ele.qnt = qnt;
                }
            }

            if (!assump) {
                System.out.println("\n Invalid pid \n");
                continue outerloop;
            }

            System.out.println("\n Do u want to continue shopping : ");
            String resp = new Scanner(System.in).next().toUpperCase();

            if (resp.equals("YES")) {
                break outerloop;
            }
        }
    }

    public void cart() {
        if (cart.size() == 0) {
            System.out.println("\n Cart is Empty \n");
            return;
        }
        double totalAmount = 0;
        System.out.println("\n Cart \n");
        for (Product ele : cart) {
            ele.displayProduct();
            ele.updateAmount(ele.qnt);
            totalAmount += ele.totalBill;
        }
        System.out.println("\n Total Amount : " + totalAmount);
        System.out.println("\n Do u want to proceed with payment : ");
        String resp = new Scanner(System.in).next().toUpperCase();

        if (resp.equals("YES")) {
            System.out.println("Thank you , your order has been placed");
            orderList.add(new ArrayList<Product>(cart));
            cart.clear();
        }
    }

    public void order() {
        if (orderList.size() == 0) {
            System.out.println("\n No orders placed yet \n");
            return;
        }
        for (ArrayList<Product> list : orderList) {
            double amt = 0;
            for (Product ele : list) {
                ele.displayProduct();
                amt += ele.totalBill;
            }
            System.out.println("\n Total Amount : " + amt);
            System.out.println("\n ---------------------------------- \n");
        }
    }
}

class Driver {
    public static void main(String[] args) {
        Amazon amazon = new Amazon();
        amazon.homePage();
    }
}