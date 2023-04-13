package Structure;

import Structure.Goods;

import java.util.ArrayList;

public class GoodsGroup implements Group<Goods>{

    private String name;
    private String description;
    public ArrayList<Goods> goods = new ArrayList<Goods>();

    public GoodsGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /** @author MaxLoshak */
    @Override
    public String[] getNames(){
        String[] names = new String[goods.size()];

        for (int i = 0; i < goods.size(); i++) {
            names[i] = goods.get(i).getName();
        }
        return names;
    }
    @Override
    public Goods getByName(String name){
        for (int i = 0; i < goods.size(); i++) {
            if(goods.get(i).getName().equals(name))
                return goods.get(i);
        }
        return null;
    }

    @Override
    public boolean isTaken(String str) {
        str = ignoreNums(str);
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTaken(String str, Goods good){
        str = ignoreNums(str);
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).getName().equalsIgnoreCase(str) && goods.get(i)!=good) {
                return true;
            }
        }
        return false;
    }

    public String ignoreNums(String str) {
        str = str.replaceAll("\\d", "");
        return str;
    }

    /** @author MaxLoshak */
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
}
