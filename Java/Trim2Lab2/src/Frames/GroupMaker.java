package Frames;

import FilesOperations.FilesOperator;
import Structure.GoodsGroup;
import Structure.Warehouse;

import javax.swing.*;
import java.awt.*;

public class GroupMaker extends JFrame implements Setup{

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel infoLabel;
    private JTextArea infoField;
    private JButton submitButton, backButton;

    /** @author MaxLoshak && OlegKhodko */
    public GroupMaker(Warehouse warehouse, GoodsGroupFrame frame) {
        super("Створити Групу Товарів");
        setSize(400, 300);

        nameField = new JTextField(20);
        infoField = new JTextArea();

        setup();

        backButton.addActionListener(e -> {
            dispose();
        });

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String info = infoField.getText();
            if (warehouse.isTaken(name)||name.equals("")) {
                if(warehouse.isTaken(name))
                    JOptionPane.showMessageDialog(GroupMaker.this , "There is already a group with that name");
                else if (name.equals("")) {
                    JOptionPane.showMessageDialog(GroupMaker.this , "Enter a name for a group");
                }
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                nameField.setText("");
            }
            else {
                GoodsGroup group = new GoodsGroup(name, info);
                warehouse.goodsGroups.add(group);
                FilesOperator.addGoodsGroupFile(group); // додавання файлу відповідної групи
                frame.getGroupComboBox().addItem(warehouse.goodsGroups.get(warehouse.goodsGroups.size() - 1).getName());
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    public GroupMaker(Warehouse warehouse, GoodsGroupFrame frame, GoodsGroup group) {
        super("Редагувати Групу Товарів");
        nameField = new JTextField(group.getName());
        infoField = new JTextArea(group.getDescription());

        setup();

        backButton.addActionListener(e -> {
            dispose();
        });

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String info = infoField.getText();
            if (warehouse.isTaken(name, group)||name.equals("")) {
                if(warehouse.isTaken(name, group))
                    JOptionPane.showMessageDialog(GroupMaker.this , "There is already a group with that name\nwhich is not the group being edited");
                else if (name.equals("")) {
                    JOptionPane.showMessageDialog(GroupMaker.this , "Enter a new name for a group");
                }
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                nameField.setText("");
            }
            else {
                int index = frame.getGroupComboBox().getSelectedIndex();
                frame.getGroupComboBox().removeItemAt(index);
                GoodsGroup oldGroup = new GoodsGroup(group.getName(), group.getDescription() ); // створення об'єкту групи товарів, який відповідає групі товарів до редагування, для здійснення пошуку файлу, який треба редагувати, за (старою) назвою
                group.setName(name);
                group.setDescription(info);
                FilesOperator.editGoodsGroupFile(oldGroup, group); // редагування файлу відповідної групи товарів
                frame.getGroupComboBox().insertItemAt(name, index);
                frame.getGroupComboBox().setSelectedIndex(index);
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    public void setup() {
        setSize(400, 300);

        nameLabel = new JLabel("Назва:");
        infoLabel = new JLabel("Інформація:");
        submitButton = new JButton("Підтвердити");
        backButton = new JButton("Назад");

        setLayout(new BorderLayout());
        infoField.setPreferredSize(new Dimension(200, 100));
        infoField.setBorder(BorderFactory.createLineBorder(Color.gray));
        infoField.setLineWrap(true);
        infoField.setWrapStyleWord(true);
        nameField.setColumns(20);
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel formPanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(infoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(infoField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }
}
