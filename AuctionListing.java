import java.time.LocalDateTime;
import java.util.ArrayList;

public class AuctionListing {
    private Product product;
    private double startingPrice;
    private double highestBid;
    private Buyer highestBidder;
    private LocalDateTime endTime;
    private ArrayList<Bid> bids;

    public AuctionListing(Product product, double startingPrice, int durationInMinutes) {
        this.product = product;
        this.startingPrice = startingPrice;
        this.highestBid = startingPrice;
        this.highestBidder = null;
        this.endTime = LocalDateTime.now().plusMinutes(durationInMinutes);
        this.bids = new ArrayList<>();
    }

    public boolean placeBid(Buyer buyer, double amount) {
        if (LocalDateTime.now().isAfter(endTime)) {
            System.out.println("Auction has ended!");
            return false;
        }
        if (amount <= highestBid) {
            System.out.println("Bid must be higher than current highest: " + highestBid);
            return false;
        }
        if (buyer.getWalletBalance() < amount) {
            System.out.println("Insufficient balance!");
            return false;
        }

        Bid bid = new Bid(buyer, amount);
        bids.add(bid);
        highestBid = amount;
        highestBidder = buyer;

        String msg = "New bid on (" + product.getTitle() + ") → " + amount;
        product.notifyObservers(msg, buyer);
        product.attach(buyer);

        System.out.println("Bid placed successfully!");
        return true;
    }

    public boolean isActive() {
        return LocalDateTime.now().isBefore(endTime);
    }
    
    public String getTimeRemaining() {
        if (!isActive()) return "Auction ended";
        long minutes = java.time.Duration.between(LocalDateTime.now(), endTime).toMinutes();
        long seconds = java.time.Duration.between(LocalDateTime.now(), endTime).toSeconds() % 60;
        return minutes + "m " + seconds + "s remaining";
    }

    public Product getProduct() { 
        return product;
    }
    public double getHighestBid() { 
        return highestBid; 
    }
    public Buyer getHighestBidder() { 
        return highestBidder; 
    }
    public LocalDateTime getEndTime() { 
        return endTime; 
    }
    public ArrayList<Bid> getBids() { 
        return bids; 
    }
    public void closeAuction() {
    if (highestBidder != null) {
        if (highestBidder.getWalletBalance() < highestBid) {
            System.out.println("Winner has insufficient balance! Auction cancelled.");
            return;
        }
        highestBidder.deductMoney(highestBid);
        product.getSeller().setWalletBalance(
            product.getSeller().getWalletBalance() + highestBid);
        highestBidder.addToCart(product);
        System.out.println("Congratulations to " + highestBidder.getName() 
            + " with price " + highestBid);
    } else {
        System.out.println("No bids placed. Auction closed without sale.");
    }
}
}