package Frames;

import Structure.Goods;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InformationFrame extends JFrame {

    private ArrayList<Goods> goodsList;
    private JTable table;
    private JButton backButton;

    public InformationFrame(ArrayList<Goods> goodsList) {
        super("Інформація про товари");
        this.goodsList = goodsList;
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        String[] columnNames = {"Назва", "Кількість", "Ціна", "Всього"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        double totalValue = 0.0;
        for (Goods goods : goodsList) {
            double goodsValue = goods.getAmount() * goods.getPrice();
            totalValue += goodsValue;
            Object[] rowData = {goods.getName(), goods.getAmount(), goods.getPrice(), goodsValue};
            tableModel.addRow(rowData);
        }
        Object[] space = {"", "", "", ""};
        Object[] totalRowData = {"Всього:", "", "", totalValue};
        tableModel.addRow(space);
        tableModel.addRow(totalRowData);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);
    }
}
