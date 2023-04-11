import javax.swing.*;
import java.awt.*;

public class GroupMaker extends JFrame {

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel infoLabel;
    private JTextField infoField;
    private JButton submitButton;

    /** @author MaxLoshak */
    public GroupMaker(Warehouse warehouse, GoodsGroupFrame frame) {
        super("Group Maker");
        setSize(400, 300);

        nameLabel = new JLabel("Назва:");
        nameField = new JTextField(20);
        infoLabel = new JLabel("Інформація:");
        infoField = new JTextField(20);
        submitButton = new JButton("Submit");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(infoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(infoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String info = infoField.getText();
            if (warehouse.isTaken(name)) {
                JOptionPane.showMessageDialog(GroupMaker.this , "There is already a group with that name");
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                nameField.setText("");
            }
            else {
                GoodsGroup group = new GoodsGroup(name, info);
                warehouse.goodsGroups.add(group);
                frame.getGroupComboBox().addItem(warehouse.goodsGroups.get(warehouse.goodsGroups.size() - 1).getName());
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }

}
