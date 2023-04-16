package Structure;

import FilesOperations.FilesOperator;

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

    public Goods getGoodByName(String name){
        for (int i = 0; i < goodsGroups.size(); i++) {
            if(goodsGroups.get(i).getByName(name)!=null)
                return goodsGroups.get(i).getByName(name);
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
    public boolean groupIsTaken(String str){
        for (int i = 0; i < goodsGroups.size(); i++) {
            return goodsGroups.get(i).isTaken(str);
        }
        return false;
    }
    public boolean groupIsTaken(String str, Goods goods){
        for (int i = 0; i < goodsGroups.size(); i++) {
            return goodsGroups.get(i).isTaken(str, goods);
        }
        return false;
    }
    public void removeGood(Goods good){
        for (int i = 0; i < goodsGroups.size(); i++) {
            goodsGroups.get(i).goods.remove(good);
        }
    }

    public String[] getAllGoodsNames(){
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i<goodsGroups.size(); i++) {
            for (int j = 0; j < goodsGroups.get(i).goods.size(); j++) {
                names.add(goodsGroups.get(i).goods.get(j).getName());
            }
        }
        return names.toArray(new String[names.size()]);
    }



    public String ignoreNums(String str) {
        str = str.replaceAll("\\d", "");
        return str;
    }


    public void setGoodsGroupsByFiles (){
        this.goodsGroups = FilesOperator.getGoodsGroupsArrayFromFile();
    }

}
