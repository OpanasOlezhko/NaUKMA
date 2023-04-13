package Structure;

import java.util.ArrayList;

public class Warehouse implements Group<GoodsGroup> {
    public ArrayList<GoodsGroup> goodsGroups;

    public Warehouse() {
        this.goodsGroups = new ArrayList<GoodsGroup>();
    }

    /** @author MaxLoshak */
    public String[] getNames(){
        String[] names = new String[goodsGroups.size()];

        for (int i = 0; i < goodsGroups.size(); i++) {
            names[i] = goodsGroups.get(i).getName();
        }
        return names;
    }

    public GoodsGroup getByName(String name){
        for (int i = 0; i < goodsGroups.size(); i++) {
            if(goodsGroups.get(i).getName().equals(name))
                return goodsGroups.get(i);
        }
        return null;
    }

    public boolean isTaken(String str) {
        str = ignoreNums(str);
        for (int i = 0; i < goodsGroups.size(); i++) {
            if (goodsGroups.get(i).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTaken(String str, GoodsGroup group) {
        str = ignoreNums(str);
        for (int i = 0; i < goodsGroups.size(); i++) {
            if (goodsGroups.get(i).getName().equalsIgnoreCase(str) && goodsGroups.get(i)!=group) {
                return true;
            }
        }
        return false;
    }

    public String ignoreNums(String str) {
        str = str.replaceAll("\\d", "");
        return str;
    }

}
