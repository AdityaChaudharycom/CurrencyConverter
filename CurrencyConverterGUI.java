import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverterGUI extends JFrame implements ActionListener {
    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField;
    JLabel resultLabel;

    HashMap<String, Double> rates;

    public CurrencyConverterGUI() {
        // Set up frame
        setTitle("Currency Converter");
        setSize(500, 250);
        setLayout(new GridLayout(6, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define currencies
        String[] currencies = {
            "USD", "INR", "EUR", "GBP", "JPY",
            "AUD", "CAD", "CHF", "CNH", "HKD", "NZD"
        };

        // Exchange rates with respect to USD
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 85.2);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.79);
        rates.put("JPY", 155.21);
        rates.put("AUD", 1.52);
        rates.put("CAD", 1.37);
        rates.put("CHF", 0.91);
        rates.put("CNH", 7.24);
        rates.put("HKD", 7.83);
        rates.put("NZD", 1.66);

        // GUI components
        JLabel fromLabel = new JLabel("From:");
        fromCurrency = new JComboBox<>(currencies);

        JLabel toLabel = new JLabel("To:");
        toCurrency = new JComboBox<>(currencies);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultLabel = new JLabel("Converted Amount: ");

        // Add components to frame
        add(fromLabel); add(fromCurrency);
        add(toLabel); add(toCurrency);
        add(amountLabel); add(amountField);
        add(new JLabel()); add(convertButton);
        add(new JLabel()); add(resultLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            // Convert to USD then to target currency
            double inUSD = amount / rates.get(from);
            double converted = inUSD * rates.get(to);

            resultLabel.setText(String.format("Converted Amount: %.2f %s", converted, to));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverterGUI();
    }
}
