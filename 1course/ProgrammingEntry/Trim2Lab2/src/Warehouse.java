import java.util.ArrayList;

public class Warehouse {
    public ArrayList<GoodsGroup> goodsGroups;

    public Warehouse() {
        this.goodsGroups = new ArrayList<GoodsGroup>();
    }

    /** @author MaxLoshak */
    public String[] getGoodsGroupsNames(){
        String[] names = new String[goodsGroups.size()];

        for (int i = 0; i < goodsGroups.size(); i++) {
            names[i] = goodsGroups.get(i).getName();
        }
        return names;
    }

    public GoodsGroup getGoodsGroupByName(String name){
        for (int i = 0; i < goodsGroups.size(); i++) {
            if(goodsGroups.get(i).getName().equals(name))
                return goodsGroups.get(i);
        }
        return null;
    }

    public boolean isTaken(String str) {
        str = str.replaceAll("\\d", "");
        for (int i = 0; i < goodsGroups.size(); i++) {
            if (goodsGroups.get(i).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

}
