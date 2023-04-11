import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupMaker extends JFrame {

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel infoLabel;
    private JTextField infoField;
    private JButton submitButton;

    public GroupMaker(Warehouse warehouse, StartingFrame frame) {
        // Set the title and size of the frame
        super("Group Maker");
        setSize(400, 300);

        // Create the components
        nameLabel = new JLabel("Назва:");
        nameField = new JTextField(20);
        infoLabel = new JLabel("Інформація:");
        infoField = new JTextField(20);
        submitButton = new JButton("Submit");

        // Set the layout manager to center the components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the components to the frame
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

        // Add an ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String name = nameField.getText();
                String info = infoField.getText();
                GoodsGroup group = new GoodsGroup(name, info);
                warehouse.goodsGroups.add(group);
                frame.getGroupComboBox().addItem(warehouse.goodsGroups.get(warehouse.goodsGroups.size()-1).getName());
                dispose();
            }
        });

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

}
