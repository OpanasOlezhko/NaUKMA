import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartingFrame extends JFrame {

    private JComboBox<String> groupComboBox;
    private JButton addGroupButton;
    private JButton submitButton;
    private JButton infoButton;
    static Warehouse warehouse;

    /** @author MaxLoshak */
    public StartingFrame() {
        super("Склад");
        setSize(400, 300);

        groupComboBox = new JComboBox<>(warehouse.getGoodsGroupsNames());
        addGroupButton = new JButton("Нова Група");
        submitButton = new JButton("Далі");
        infoButton = new JButton("Інформація");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the components to the frame
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


        infoButton.addActionListener(e -> {
            String selectedGroup = (String)groupComboBox.getSelectedItem();

            JOptionPane.showMessageDialog(StartingFrame.this , warehouse.getGoodsGroupByName(selectedGroup).getDescription());
        });

        addGroupButton.addActionListener(e -> {
            GroupMaker frame = new GroupMaker(warehouse, this);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        init();
        StartingFrame frame = new StartingFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     *                     ^^^^^^
     *
     * Це я потім перенесу в class Main (psvm), поки воно тут для зручності
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
}
