import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverterGUI {

    public static void main(String[] args) {
        // Set Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Frame setup
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] currencies = {"USD", "INR", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNH", "HKD", "NZD"};

        JLabel fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(fromLabel, gbc);

        JComboBox<String> fromCurrency = new JComboBox<>(currencies);
        gbc.gridx = 1;
        fromCurrency.setSelectedItem("INR");
        frame.add(fromCurrency, gbc);

        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(toLabel, gbc);

        JComboBox<String> toCurrency = new JComboBox<>(currencies);
        gbc.gridx = 1;
        toCurrency.setSelectedItem("USD");
        frame.add(toCurrency, gbc);

        JButton swapButton = new JButton("Swap");
        gbc.gridx = 2;
        frame.add(swapButton, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(amountLabel, gbc);

        JTextField amountField = new JTextField(10);
        gbc.gridx = 1;
        frame.add(amountField, gbc);

        JButton convertButton = new JButton("Convert");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(convertButton, gbc);

        JButton clearButton = new JButton("Clear");
        gbc.gridx = 1;
        frame.add(clearButton, gbc);

        JLabel resultLabel = new JLabel("Converted Amount:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        frame.add(resultLabel, gbc);

        // Conversion rates
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 83.2);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.87);
        rates.put("JPY", 156.2);
        rates.put("AUD", 1.51);
        rates.put("CAD", 1.36);
        rates.put("CHF", 0.91);
        rates.put("CNH", 7.25);
        rates.put("HKD", 7.81);
        rates.put("NZD", 1.62);

        // Convert button
        convertButton.addActionListener(e -> {
            String amountText = amountField.getText();
            if (!amountText.matches("\\d+(\\.\\d+)?")) {
                resultLabel.setText("Please enter a valid numeric amount.");
                return;
            }

            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountText);
            double convertedAmount = amount / rates.get(from) * rates.get(to);
            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
        });

        // Clear button
        clearButton.addActionListener(e -> {
            fromCurrency.setSelectedIndex(0);
            toCurrency.setSelectedIndex(0);
            amountField.setText("");
            resultLabel.setText("Converted Amount:");
        });

        // Swap button
        swapButton.addActionListener(e -> {
            int fromIndex = fromCurrency.getSelectedIndex();
            fromCurrency.setSelectedIndex(toCurrency.getSelectedIndex());
            toCurrency.setSelectedIndex(fromIndex);
        });

        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
}
