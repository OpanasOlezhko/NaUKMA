package Frames;

import FilesOperations.FilesOperator;
import Structure.Goods;
import Structure.GoodsGroup;
import Structure.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class GoodsFrame extends JFrame {
    private JComboBox<String> goodsComboBox;
    private JButton addGoodButton, submitButton, deleteGoodButton, informationButton, editButton, backButton;
    private JTextField searchTextField;
    private JSpinner addSpinner;
    private JCheckBox allGroups;
    private GoodsGroup group;
    private Warehouse warehouse;

    public GoodsFrame(GoodsGroup group, Warehouse warehouse) {
        setTitle(group.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        this.group = group;
        this.warehouse = warehouse;

        goodsComboBox = new JComboBox<>(group.getNames());
        addGoodButton = new JButton("Додати");
        deleteGoodButton = new JButton("Видалити");
        searchTextField = new JTextField(15);
        addSpinner = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
        informationButton = new JButton("Інформація");
        submitButton = new JButton("Підтвердити");
        editButton = new JButton("Редагувати");
        backButton = new JButton("Назад");
        allGroups = new JCheckBox("Шукати у всіх групах");

        searchTextField.setBorder(BorderFactory.createTitledBorder("Search"));
        addSpinner.setBorder(BorderFactory.createTitledBorder("Нарахувати/Списати"));
        addSpinner.setSize(200, 30);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(searchTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(allGroups, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(goodsComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(addSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(deleteGoodButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(editButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        add(addGoodButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(backButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(informationButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        add(submitButton, gbc);

        setVisible(true);
        setLocationRelativeTo(null);

        backButton.addActionListener(e -> {
            dispose();
        });

        searchTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String[] items;
                    if(allGroups.isSelected()) {
                        items = sortStringsBySubstring(warehouse.getAllGoodsNames(), searchTextField.getText().toLowerCase());
                        goodsComboBox.removeAllItems();
                        for (int i = 0; i < items.length; i++) {
                            goodsComboBox.addItem(items[i]);
                        }
                    }
                    else {
                        items = sortStringsBySubstring(group.getNames(), searchTextField.getText().toLowerCase());
                        goodsComboBox.removeAllItems();
                        for (int i = 0; i < items.length; i++) {
                            goodsComboBox.addItem(items[i]);
                        }
                    }
                }
            }
        });

        addGoodButton.addActionListener(e -> {
            new GoodMaker(this);
        });

        editButton.addActionListener(e -> {
            try {
                Goods selectedGood = warehouse.getGoodByName((String) goodsComboBox.getSelectedItem());
                new GoodMaker(this, selectedGood);
            }catch (NullPointerException ex) {JOptionPane.showMessageDialog(GoodsFrame.this, "No selected object!");}
        });

        informationButton.addActionListener(e -> {
            try {
                Goods selectedGood = warehouse.getGoodByName((String) goodsComboBox.getSelectedItem());

                JOptionPane.showMessageDialog(GoodsFrame.this, selectedGood.getDescription());
            }catch (NullPointerException ex) {JOptionPane.showMessageDialog(GoodsFrame.this, "No selected object!");}
        });

        submitButton.addActionListener(e -> {
            try {
                if((int)addSpinner.getValue()!=0) {
                    Goods selectedGood = warehouse.getGoodByName((String) goodsComboBox.getSelectedItem());
                    int value = (int) addSpinner.getValue();

                    Goods oldGood = selectedGood; // створення об'єкту товару, який відповідає товару до редагування, для здійснення пошуку рядку у файлі, який треба редагувати, за (старою) назвою
                    selectedGood.setAmount(selectedGood.getAmount() + value);
                    FilesOperator.editGoodInFile(oldGood, selectedGood, group); // // редагування рядка товару у файлі відповідної групи товарів
                    JOptionPane.showMessageDialog(GoodsFrame.this, "Success!\nThe amount of "
                            + selectedGood.getName() + " is " + selectedGood.getAmount());
                    addSpinner.setValue(0);
                }
            }catch (NullPointerException ex) {JOptionPane.showMessageDialog(GoodsFrame.this, "No selected object!");}
        });

        deleteGoodButton.addActionListener(e ->{
            Goods selectedGood = warehouse.getGoodByName((String)goodsComboBox.getSelectedItem());

            goodsComboBox.removeItem(goodsComboBox.getSelectedItem());
            warehouse.removeGood(selectedGood);
            FilesOperator.deleteGoodFromFile(selectedGood, group); // видалення рядка товару із відповідного його групі файлу
        });
    }

    public String[] sortStringsBySubstring(String[] arr, String substr) {
        ArrayList<String> result = new ArrayList();
        for (String s : arr) {
            if (s.contains(substr)) {
                result.add(s);
            }
        }
        Collections.sort(result);
        return result.toArray(new String[result.size()]);
    }

    public GoodsGroup getGroup(){return group;}
    public Warehouse getWarehouse() {return warehouse;}
    public void setGroup(GoodsGroup group) {this.group = group;}
    public void setWarehouse(Warehouse warehouse) {this.warehouse = warehouse;}
    public JComboBox<String> getGoodsComboBox() {
        return goodsComboBox;
    }

    public void setGoodsComboBox(JComboBox<String> goodsComboBox) {
        this.goodsComboBox = goodsComboBox;
    }

    public JButton getAddGoodButton() {
        return addGoodButton;
    }

    public void setAddGoodButton(JButton addGoodButton) {
        this.addGoodButton = addGoodButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JButton getDeleteGoodButton() {
        return deleteGoodButton;
    }

    public void setDeleteGoodButton(JButton deleteGoodButton) {
        this.deleteGoodButton = deleteGoodButton;
    }

    public JButton getInformationButton() {
        return informationButton;
    }

    public void setInformationButton(JButton informationButton) {
        this.informationButton = informationButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public JSpinner getAddSpinner() {
        return addSpinner;
    }

    public void setAddSpinner(JSpinner addSpinner) {
        this.addSpinner = addSpinner;
    }

    public JCheckBox getAllGroups() {
        return allGroups;
    }

    public void setAllGroups(JCheckBox allGroups) {
        this.allGroups = allGroups;
    }


}


