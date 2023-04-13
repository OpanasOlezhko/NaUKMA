import Frames.GoodsGroupFrame;
import Structure.Goods;
import Structure.GoodsGroup;
import Structure.Warehouse;

import javax.swing.*;

public class Main {
    private static Warehouse warehouse;
    public static void main(String[] args) {
        init();
        GoodsGroupFrame frame = new GoodsGroupFrame(warehouse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void init(){
        warehouse = new Warehouse();
        GoodsGroup groceries = new GoodsGroup("Edible", "Group Description");
        GoodsGroup householdGoods = new GoodsGroup("Household Goods", " Group Description");
        Goods bread = new Goods("bread", "description", "UkrHlib", 10, 15);
        Goods wheat = new Goods("wheat", "description", "UkrHlib", 100, 150);
        Goods soap = new Goods("soap", "description", "UkrHouse", 20, 30);
        Goods toothbrush = new Goods("toothbrush", "description", "UkrHouse", 20, 20);
        warehouse.goodsGroups.add(groceries);
        warehouse.goodsGroups.add(householdGoods);
        groceries.goods.add(bread);
        groceries.goods.add(wheat);
        householdGoods.goods.add(soap);
        householdGoods.goods.add(toothbrush);
    }
}
