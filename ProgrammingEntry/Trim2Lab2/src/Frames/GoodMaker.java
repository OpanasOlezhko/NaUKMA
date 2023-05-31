package Frames;

import FilesOperations.FilesOperator;
import Structure.Goods;

import java.awt.*;
import java.nio.file.FileVisitOption;
import javax.swing.*;

public class GoodMaker extends JFrame implements Setup{

    private JLabel nameLabel, descLabel, providerLabel, priceLabel;
    private JTextField nameField, providerField;
    private JTextArea descField;
    private JSpinner amountSpinner;
    private JSpinner.NumberEditor amountEditor;
    private JSpinner priceSpinner;
    private JSpinner.NumberEditor priceEditor;
    private JButton submitButton, backButton;

    /** @author MaxLoshak && OlegKhodko */
    public GoodMaker(GoodsFrame basis) {
        super("Додати товар");

        nameField = new JTextField(20);
        descField = new JTextArea();
        providerField = new JTextField(20);
        amountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        priceSpinner = new JSpinner(new SpinnerNumberModel(0.00, 0.00, null, 1.00));

        setup();

        backButton.addActionListener(e -> {
            dispose();
        });

        submitButton.addActionListener(e -> {
            String name = nameField.getText().toLowerCase();
            String desc = descField.getText();
            String provider = providerField.getText();
            double price = (double)priceSpinner.getValue();
            if(basis.getWarehouse().groupIsTaken(name)||name.equals("")||price==0.0){
                if(basis.getWarehouse().groupIsTaken(name)) {
                    JOptionPane.showMessageDialog(GoodMaker.this, "There is already a group with that name");
                    nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    nameField.setText("");
                }
                else if (name.equals("")) {
                    JOptionPane.showMessageDialog(GoodMaker.this , "Enter a name for a good");
                    nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else if (price==0.0) {
                    JOptionPane.showMessageDialog(GoodMaker.this , "Enter a price for a good");
                    priceSpinner.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
            else {
                Goods good = new Goods(name, desc, provider, 0, price);
                basis.getGroup().goods.add(good);
                FilesOperator.addGoodToFile(good, basis.getGroup()); // додає рядок товару у файл відповідної групи товарів
                basis.getGoodsComboBox().addItem(good.getName());
                dispose();
            }
        });

        setSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public GoodMaker(GoodsFrame basis, Goods good) {
        super("Змінити товар");

        nameField = new JTextField(good.getName());
        descField = new JTextArea(good.getDescription());
        providerField = new JTextField(good.getProvider());
        amountSpinner = new JSpinner(new SpinnerNumberModel(good.getAmount(), 0, 1000, 1));
        priceSpinner = new JSpinner(new SpinnerNumberModel(good.getPrice(), 0.00, null, 1.00));

        setup();

        backButton.addActionListener(e -> {
            dispose();
        });

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String desc = descField.getText();
            String provider = providerField.getText();
            double price = (double)priceSpinner.getValue();
            if(basis.getWarehouse().groupIsTaken(name, good)||name.equals("")||price==0.0){
                if(basis.getWarehouse().groupIsTaken(name, good)) {
                    JOptionPane.showMessageDialog(GoodMaker.this, "There is already a good with that name\nwhich is not the good bieng edited");
                    nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    nameField.setText("");
                }
                else if (name.equals("")) {
                    JOptionPane.showMessageDialog(GoodMaker.this , "Enter a name for a good");
                    nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else if (price==0.0) {
                    JOptionPane.showMessageDialog(GoodMaker.this , "Enter a price for a good");
                    priceSpinner.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
            else {
                int index = basis.getGoodsComboBox().getSelectedIndex();
                basis.getGoodsComboBox().removeItemAt(index);
                Goods oldGood = good; // створення об'єкту товару, який відповідає товару до редагування, для здійснення пошуку рядку у файлі, який треба редагувати, за (старою) назвою
                good.setName(name);
                good.setDescription(desc);
                good.setProvider(provider);
                good.setPrice(price);
                FilesOperator.editGoodInFile(oldGood, good, basis.getGroup()); // редагування рядка товару у файлі відповідної групи товарів
                basis.getGoodsComboBox().insertItemAt(name, index);
                basis.getGoodsComboBox().setSelectedIndex(index);
                dispose();
            }
        });

        setSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setup() {
        setLayout(new BorderLayout());


        nameField.setColumns(20);
        descField.setPreferredSize(new Dimension(200, 100));
        descField.setBorder(BorderFactory.createLineBorder(Color.gray));
        descField.setLineWrap(true);
        descField.setWrapStyleWord(true);
        providerField.setColumns(20);

        nameLabel = new JLabel("Назва:");
        descLabel = new JLabel("Опис:");
        providerLabel = new JLabel("Виробник:");
        amountEditor = new JSpinner.NumberEditor(amountSpinner, "#");
        amountSpinner.setEditor(amountEditor);
        priceLabel = new JLabel("Ціна:");
        priceEditor = new JSpinner.NumberEditor(priceSpinner, "#.00");
        priceSpinner.setEditor(priceEditor);

        submitButton = new JButton("Підтвердити");
        backButton = new JButton("Назад");

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridy = 1;
        formPanel.add(descLabel, gbc);
        gbc.gridy = 2;
        formPanel.add(providerLabel, gbc);
        gbc.gridy = 3;
        formPanel.add(priceLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);
        gbc.gridy = 1;
        formPanel.add(descField, gbc);
        gbc.gridy = 2;
        formPanel.add(providerField, gbc);
        gbc.gridy = 3;
        formPanel.add(priceSpinner, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }
}
