package Frames;

import Structure.GoodsGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GoodsFrame extends JFrame {
    private JComboBox<String> goodsComboBox;
    private JButton newButton;
    private JButton deleteButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private JSpinner addSpinner;
    private JButton informationButton;
    private JButton submitButton;
    private JButton editButton;
    private JButton backButton;

    public GoodsFrame(GoodsGroup group) {
        setTitle(group.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        goodsComboBox = new JComboBox<>(group.getNames());
        newButton = new JButton("Додати");
        deleteButton = new JButton("Видалити");
        searchTextField = new JTextField(11);
        searchButton = new JButton("Пошук");
        addSpinner = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
        informationButton = new JButton("Інформація");
        submitButton = new JButton("Підтвердити");
        editButton = new JButton("Редагувати");
        backButton = new JButton("Назад");

        searchTextField.setBorder(BorderFactory.createTitledBorder("Search"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 4;
        gbc.gridy = 1;
        add(goodsComboBox, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        add(searchTextField, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        add(searchButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        add(newButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        add(editButton, gbc);

        gbc.gridx = 7;
        gbc.gridy = 2;
        add(deleteButton, gbc);

        gbc.gridx = 6;
        gbc.gridy = 1;
        add(addSpinner, gbc);

        gbc.gridx = 5;
        gbc.gridy = 3;
        add(informationButton, gbc);

        gbc.gridx = 7;
        gbc.gridy = 3;
        add(submitButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        add(backButton, gbc);

        setVisible(true);
        setLocationRelativeTo(null);

        backButton.addActionListener(e -> {
            dispose();
        });

        searchTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //.....
                }
            }
        });

    }
}
