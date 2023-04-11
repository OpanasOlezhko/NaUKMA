import java.util.ArrayList;

public class GoodsGroup {

    private String name;
    private String description;
    public ArrayList<Goods> goods = new ArrayList<Goods>();

    public GoodsGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /** @author MaxLoshak */
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
}
