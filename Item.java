public class Item {
    private final String username;
    private final String reg;
    private final String department;
    private final String Id;
    public Item(String username, String reg, String department, String Id){
        this.username = username;
        this.reg = reg;
        this.department = department;
        this.Id = Id;
    }


    public String getUsername(){
        return this.username;
    }

    public String getReg(){
        return this.reg;
    }

    public String getDepartment(){
        return this.department;
    }

    public String getId(){
        return this.Id;
    }
}
