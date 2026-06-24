import java.util.ArrayList;
public class Seller extends User{
    private double walletBalance;
    private ArrayList<Product> listedProducts;
    
    public Seller(String id, String name, String username, String password,double walletBalance){
        super(id,name,username,password);
        this.walletBalance=walletBalance;
        this.listedProducts = new ArrayList<>();
    }

    public ArrayList<Product> getListedProducts() {
        return listedProducts;
    }

    public void setListedProducts(ArrayList<Product> listedProducts) {
        this.listedProducts = listedProducts;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
    
    
    //method 
    public void addProduct(Product P){
        listedProducts.add(P);
    }

    @Override
    public String toString() {
    return "Seller{name=" + getName() + "}";
}

   
    
}
