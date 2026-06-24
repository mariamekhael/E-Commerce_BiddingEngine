public class Admin extends User {
    public Admin(String id, String name, String username, String password){
        super(id,name,username,password);
    }
    public void approveProduct(){
        
    }
    public void viewReport(){
        
    }

    @Override
    public String toString() {
    return super.toString() + " [Role: Admin]"; 
    }
    
}
