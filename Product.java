
import java.util.ArrayList;

public class Product {
    private String id, title, description;
    private double basePrice;
    private Seller seller;
    private ArrayList<Buyer> observers = new ArrayList<>();;
    
    private double currentHighestBid;
    private Buyer highestBidder;

    public Product(String id, String title, String description, double basePrice, Seller seller) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.basePrice = basePrice;
        this.seller = seller;
        this.currentHighestBid=basePrice;
        this.highestBidder=null;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    public void setCurrentHighestBid(double currentHighestBid) {
        this.currentHighestBid = currentHighestBid;
    }

    public Buyer getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(Buyer highestBidder) {
        this.highestBidder = highestBidder;
    }

    
    public void attach(Buyer buyer){
        if (!observers.contains(buyer)) {
        observers.add(buyer);
    }
    }
    public void notifyObservers(String message, Buyer activeBidder) {
    for (Buyer buyer : observers) {
        if (!buyer.getId().equals(activeBidder.getId())) {
            buyer.update(message);
        }
    }
    }
    
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", title=" + title + ", description=" + description + ", basePrice=" + basePrice + ", seller=" + seller + '}';
    }
    
    
    
}
