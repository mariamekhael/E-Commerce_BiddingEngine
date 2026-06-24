
import java.util.ArrayList;

public class Buyer extends User implements Observer {
    private double walletBalance;
    private ArrayList<Product> cart;
    
    public Buyer(String id, String name, String username, String password,double initialBalance){
        super(id,name,username,password);
        this.walletBalance=initialBalance;
        this.cart = new ArrayList<>();
    }
    
    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        if(walletBalance >= 0) {
            this.walletBalance = walletBalance;
        }
    }

    public ArrayList<Product> getListedProducts() {
        return cart;
    }

    public void setListedProducts(ArrayList<Product> listedProducts) {
        this.cart = listedProducts;
    }
    
    //methods 
    public void addToCart(Product p){
        this.cart.add(p);
    }
    public void deductMoney(double amount){
        
        this.walletBalance-=amount;
    }

    @Override
    public String toString() {
    return super.toString() + " Buyer{walletBalance=" + walletBalance + ", cartSize=" + cart.size() + "}";
    }
    
     @Override
    public void update(String message) {
        System.out.println("[Notification for " + getName() + "]: " + message);
    }
    
    
}
