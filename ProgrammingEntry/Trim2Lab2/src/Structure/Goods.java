package Structure;

public class Goods {

    private String name;
    private String description;
    private String provider;
    private int amount;
    private double price;

    public Goods(String name, String description, String provider, int amount, double price){
        this.name = name;
        this.description = description;
        this.provider = provider;
        this.amount = amount;
        this.price = price;
    }

    /** @author MaxLoshak */
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setProvider(String provider) {this.provider = provider;}
    public void setAmount(int amount) {this.amount = amount;}
    public void setPrice(double price) {this.price = price;}
    public String getName() {return this.name;}
    public String getDescription(){return this.description;}
    public String getProvider() {return this.provider;}
    public int getAmount() {return this.amount;}
    public double getPrice() {return this.price;}

}
