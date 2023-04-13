package Frames;

import Structure.GoodsGroup;
import Structure.Warehouse;

import javax.swing.*;
import java.awt.*;

public class GoodsGroupFrame extends JFrame {

    private JComboBox<String> groupComboBox;
    private JButton addGroupButton;
    private JButton submitButton;
    private JButton infoButton;
    private JButton editButton;
    private JButton deleteButton;
    static Warehouse warehouse;

    /** @author MaxLoshak */
    public GoodsGroupFrame(Warehouse warehouse) {
        super("Склад");
        setSize(400, 300);

        groupComboBox = new JComboBox<>(warehouse.getNames());
        addGroupButton = new JButton("Нова Група");
        submitButton = new JButton("Підтвердити");
        infoButton = new JButton("Інформація");
        editButton = new JButton("Редагувати");
        deleteButton = new JButton("Видалити");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(groupComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(addGroupButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(infoButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(editButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(deleteButton, gbc);


        infoButton.addActionListener(e -> {
            GoodsGroup selectedGroup = warehouse.getByName((String)groupComboBox.getSelectedItem());

            JOptionPane.showMessageDialog(GoodsGroupFrame.this , selectedGroup.getDescription());
        });

        addGroupButton.addActionListener(e -> {
            GroupMaker frame = new GroupMaker(warehouse, this);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        submitButton.addActionListener(e ->{
            GoodsGroup selectedGroup = warehouse.getByName((String)groupComboBox.getSelectedItem());
            new GoodsFrame(selectedGroup);
        });

        editButton.addActionListener(e ->{
            GoodsGroup selectedGroup = warehouse.getByName((String)groupComboBox.getSelectedItem());
            GroupMaker frame = new GroupMaker(warehouse, this, selectedGroup);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        deleteButton.addActionListener(e ->{
            GoodsGroup selectedGroup = warehouse.getByName((String)groupComboBox.getSelectedItem());

            groupComboBox.removeItem(groupComboBox.getSelectedItem());
            warehouse.goodsGroups.remove(selectedGroup);
        });

        setLocationRelativeTo(null);
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
