
import java.time.LocalDateTime;

public class Bid {
    private Buyer buyer;
    private double amount;
    private LocalDateTime bidTime;

    public Bid(Buyer buyer, double amount) {
        this.buyer = buyer;
        this.amount = amount;
        this.bidTime = LocalDateTime.now();
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }
    
}
