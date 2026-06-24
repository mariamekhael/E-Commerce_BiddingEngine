import java.util.ArrayList;
import java.util.Scanner;

public class EngineController {
    private ArrayList<User> allUsers;
    private ArrayList<AuctionListing> allAuctions;
    private Scanner scanner;
    private User currentUser;

    public EngineController() {
        this.allUsers = new ArrayList<>();
        this.allAuctions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.currentUser = null;
        generateSeedData();
    }
    
    private void generateSeedData(){
        allUsers.add(new Admin("A1", "Super Admin", "admin", "123"));
        Seller s1=new Seller("S1", "Dubai phone", "seller", "123",0.0);
        allUsers.add(s1);
        allUsers.add(new Buyer("B1", "Mohamed Buyer", "buyer", "123", 5000.0));

    }
    public void start(){
        OUTER:
        while (true) {
            System.out.println("\n=== Welcome to E-Commerce & Bidding Engine ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegister();
                    break;    
                case 3:
                    System.out.println("Thank you for using our system. Goodbye!");
                    break OUTER;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    
    }
    
    private void handleRegister() {
    System.out.println("Register as: 1. Buyer  2. Seller");
    int type = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter ID: ");
    String id = scanner.nextLine();
    for (User u : allUsers) {
    if (u.getId().equals(id)) {
        System.out.println("ID already exists! Choose another.");
        return;
    }
}
    System.out.print("Enter Name: ");
    String name = scanner.nextLine();
    System.out.print("Enter Username: ");
    String username = scanner.nextLine();
    for (User u : allUsers) {
    if (u.getUsername().equals(username)) {
        System.out.println("Username already exists! Choose another.");
        return;
    }
}
    System.out.print("Enter Password: ");
    String password = scanner.nextLine();

        switch (type) {
            case 1:
                System.out.print("Enter Initial Balance: ");
                double balance = scanner.nextDouble();
                scanner.nextLine();
                allUsers.add(new Buyer(id, name, username, password, balance));
                System.out.println("Buyer registered successfully!");
                break;
            case 2:
                allUsers.add(new Seller(id, name, username, password, 0.0));
                System.out.println("Seller registered successfully!");
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
}
    
    private void handleLogin(){
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        currentUser = null;
        for (User u : allUsers) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                break;
            }
        }
        if (currentUser != null) {
            System.out.println("Login Successful! Welcome " + currentUser.getName());
            redirectUserMenu();
        } else {
            System.out.println("Invalid Username or Password!");
        }
    }
    
    private void redirectUserMenu(){
        if (currentUser instanceof Admin)
            showAdminMenu();
        else if (currentUser instanceof Seller)
            showSellerMenu();
        else if (currentUser instanceof Buyer)
            showBuyerMenu();
    }
    
    private void showAdminMenu() {
        OUTER:
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. Close an Auction");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    closeAuctionOnProduct();
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    currentUser = null;
                    break OUTER ;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
        
    }

    private void showSellerMenu() {
        OUTER:
        while (true) {
            System.out.println("\n--- Seller Dashboard ---");
            System.out.println("1. Add Product");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewProductBySeller();
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    currentUser = null;
                    break OUTER;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
            
            
        }
    }
    
    private void addNewProductBySeller(){
                System.out.println("enter product ID: ");
                String id = scanner.nextLine();
                System.out.println("enter product Title: ");
                String title = scanner.nextLine();
                System.out.println("enter product Description: ");
                String description = scanner.nextLine();
                System.out.println("enter Base Price: ");
                double basePrice = scanner.nextDouble();
                scanner.nextLine();
                
                Seller s2=(Seller)currentUser;
                Product p2=new Product(id,title,description,basePrice,s2);
                s2.addProduct(p2);
                System.out.println("Product added Successfully!!");
                
                System.out.println("Enter auction duration in minutes: ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                AuctionListing auction = new AuctionListing(p2, basePrice, duration);
                allAuctions.add(auction);
                
            }
            

    private void showBuyerMenu() {
        OUTER:
        while (true) {
            System.out.println("\n--- Buyer Dashboard ---");
            System.out.println("1. View Products");
            System.out.println("2. Buy Product (Direct)");
            System.out.println("3. Place a Bid on Product");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2: 
                    buyProductDirectly();
                    break;
                case 3:
                    placeBidOnProduct();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    currentUser = null;
                    break OUTER;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }    
    
    }
   private void viewAllProducts() {
    if (allAuctions.isEmpty()) {
        System.out.println("The product list is empty!!");
    } else {
        for (AuctionListing a : allAuctions) {
            System.out.println(a.getProduct());
        }
    }
}
    
    private void buyProductDirectly() {
    if (allAuctions.isEmpty()) {
        System.out.println("No products available!");
        return;
    }
    for (AuctionListing a : allAuctions) {
        System.out.println(a.getProduct());
    }
    System.out.println("Enter product ID:");
    String id = scanner.nextLine();
    AuctionListing selected = null;
    for (AuctionListing a : allAuctions) {
        if (a.getProduct().getId().equals(id)) {
            selected = a;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Product not found!");
        return;
    }
    Buyer currentBuyer = (Buyer) currentUser;
    if (selected.getHighestBidder() != null) {
        System.out.println("This product has active bids! Cannot buy directly.");
        return;
    }
    double price = selected.getProduct().getBasePrice();
    if (currentBuyer.getWalletBalance() >= price) {
        currentBuyer.deductMoney(price);
        selected.getProduct().getSeller().setWalletBalance(
            selected.getProduct().getSeller().getWalletBalance() + price);
        currentBuyer.addToCart(selected.getProduct());
        allAuctions.remove(selected);
        System.out.println("Product bought successfully!");
    } else {
        System.out.println("Insufficient balance! Your wallet has only " + currentBuyer.getWalletBalance());
    }
}
    
    private void placeBidOnProduct() {
    if (allAuctions.isEmpty()) {
        System.out.println("No active auctions!");
        return;
    }
    for (AuctionListing a : allAuctions) {
        System.out.println("Product: " + a.getProduct().getTitle() 
            + " | Current Bid: " + a.getHighestBid() 
            + " | " + a.getTimeRemaining());
    }
    
    System.out.println("Enter product ID:");
    String id = scanner.nextLine();
    AuctionListing selected = null;
    for (AuctionListing a : allAuctions) {
        if (a.getProduct().getId().equals(id)) {
            selected = a;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Auction not found!");
        return;
    }
    if (!selected.isActive()) {
    System.out.println("This auction has already ended!");
    return;
}
    System.out.println("Enter your bid amount:");
    double amount = scanner.nextDouble();
    scanner.nextLine();
    selected.placeBid((Buyer) currentUser, amount);
}
    
//    private void placeBidOnProduct(){
//        viewAllProducts();
//        System.out.println("enter product ID:");
//        String ID =scanner.nextLine();
//        Product selectedProduct = null;
//        for (Product p:allProducts){
//            if (p.getId().equals(ID)){
//                selectedProduct=p;
//                break;
//            }
//        }
//        if(selectedProduct==null)
//            System.out.println("Product not found!");
//        else{
//            Buyer currentBuyer=(Buyer)currentUser;
//            System.out.println("enter your bid price:");
//            double bidAmount = scanner.nextDouble(); 
//            scanner.nextLine();
//            if (bidAmount>=selectedProduct.getCurrentHighestBid()&&currentBuyer.getWalletBalance() >= bidAmount){
//                String msg = "The price of product (" + selectedProduct.getTitle() + ") has increased to " + bidAmount;
//                selectedProduct.notifyObservers(msg,currentBuyer);
//                
//                selectedProduct.setCurrentHighestBid(bidAmount);
//                selectedProduct.setHighestBidder(currentBuyer);
//                
//                selectedProduct.attach(currentBuyer);
//                
//                System.out.println("Your bid has been placed successfully!");
//            }
//            else if (bidAmount<selectedProduct.getCurrentHighestBid()){
//                System.out.println("Another client bid a higher price \n the current bid price ="+selectedProduct.getCurrentHighestBid());
//            }
//            else if (currentBuyer.getWalletBalance() < bidAmount){
//                System.out.println("Insufficient balance! Your wallet has only "+currentBuyer.getWalletBalance());
//            }
//        }
//    }
//    
//    private void closeAuctionOnProduct(){
//        System.out.println("enter product ID:");
//        String ID =scanner.nextLine();
//        Product selectedProduct = null;
//        for (Product p:allProducts){
//            if (p.getId().equals(ID)){
//                selectedProduct=p;
//                break;
//            }
//        }
//        if(selectedProduct==null)
//            System.out.println("Product not found!");
//        else {
//            if (selectedProduct.getHighestBidder() != null){
//                Buyer winner=selectedProduct.getHighestBidder();
//                Seller seller=selectedProduct.getSeller();
//                double finalPrice = selectedProduct.getCurrentHighestBid();
//                if (winner.getWalletBalance() < finalPrice) {
//                    System.out.println("You have insufficient balance! Auction cancelled.");
//                    return;
//                }
//                winner.deductMoney(finalPrice);
//                seller.setWalletBalance(seller.getWalletBalance() + finalPrice);
//                winner.addToCart(selectedProduct);
//                allProducts.remove(selectedProduct);
//                System.out.println("congratulations to "+winner.getName()+"with price "+finalPrice);
//            }
//            else{
//                System.out.println("No bids were placed on this product. Auction closed without sale");
//            }
//        }
//    }
    
    private void closeAuctionOnProduct() {
    System.out.println("Enter product ID:");
    String id = scanner.nextLine();
    AuctionListing selected = null;
    for (AuctionListing a : allAuctions) {
        if (a.getProduct().getId().equals(id)) {
            selected = a;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Auction not found!");
        return;
    }
    selected.closeAuction();
    allAuctions.remove(selected);
}
    
    
    
}

    
    

