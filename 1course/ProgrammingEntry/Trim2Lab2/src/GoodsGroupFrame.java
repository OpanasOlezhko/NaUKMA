import javax.swing.*;
import java.awt.*;

public class GoodsGroupFrame extends JFrame {

    private JComboBox<String> groupComboBox;
    private JButton addGroupButton;
    private JButton submitButton;
    private JButton infoButton;
    private JButton deleteButton;
    static Warehouse warehouse;

    /** @author MaxLoshak */
    public GoodsGroupFrame() {
        super("Склад");
        setSize(400, 300);

        groupComboBox = new JComboBox<>(warehouse.getGoodsGroupsNames());
        addGroupButton = new JButton("Нова Група");
        submitButton = new JButton("Підтвердити");
        infoButton = new JButton("Інформація");
        deleteButton = new JButton("Видалити");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(groupComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(addGroupButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(infoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(deleteButton, gbc);


        infoButton.addActionListener(e -> {
            GoodsGroup selectedGroup = warehouse.getGoodsGroupByName((String)groupComboBox.getSelectedItem());

            JOptionPane.showMessageDialog(GoodsGroupFrame.this , selectedGroup.getDescription());
        });

        addGroupButton.addActionListener(e -> {
            GroupMaker frame = new GroupMaker(warehouse, this);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        submitButton.addActionListener(e ->{
            // ......
        });

        deleteButton.addActionListener(e ->{
            GoodsGroup selectedGroup = warehouse.getGoodsGroupByName((String)groupComboBox.getSelectedItem());

            groupComboBox.removeItem(groupComboBox.getSelectedItem());
            warehouse.goodsGroups.remove(selectedGroup);
        });

        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        init();
        GoodsGroupFrame frame = new GoodsGroupFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     *                     ⏫⏫⏫⏫⏫⏫⏫
     * Це я в подальшому перенесу в class Main (psvm, psvi), поки воно тут для зручності
     *                      ⏬⏬⏬⏬⏬⏬
     */

    public static void init(){
        warehouse = new Warehouse();
        GoodsGroup groceries = new GoodsGroup("Edible", "Goods Group Description");
        GoodsGroup householdGoods = new GoodsGroup("Household Goods", "Goods Group Description");
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
    /** @author MaxLoshak */
    public void setGroupComboBox(JComboBox<String> groupComboBox) {
        this.groupComboBox = groupComboBox;
    }

    public JComboBox<String> getGroupComboBox() {
        return groupComboBox;
    }

    public void setAddGroupButton(JButton addGroupButton) {
        this.addGroupButton = addGroupButton;
    }

    public JButton getAddGroupButton() {
        return addGroupButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setInfoButton(JButton infoButton) {
        this.infoButton = infoButton;
    }

    public JButton getInfoButton() {
        return infoButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getDeleteButton(){
        return deleteButton;
    }
}
