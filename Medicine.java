import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Medicine {
    private JFrame frame;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField mobileField;
    private JTextField otpField;
    private JTextArea outputArea;
    private JLabel warningText;
    private JComboBox<String> medicineComboBox;
    private JComboBox<String> paymentComboBox;
    private JLabel medicinePriceLabel;

    public Medicine() {
        frame = new JFrame("Medicine ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Patient's Name:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Patient's Age:");
        ageField = new JTextField();
        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileField = new JTextField();
        JLabel otpLabel = new JLabel("OTP:");
        otpField = new JTextField();
        JLabel medicineLabel = new JLabel("Select Medicine:");
        medicineComboBox = new JComboBox<>(new String[]{
                "DOLO-650", "ENO", "GELUSIR", "MEDICAL KIT", "STAYFREE", "Strepsils"
        });

        medicinePriceLabel = new JLabel("Medicine Price: ");
        warningText = new JLabel();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(mobileLabel);
        inputPanel.add(mobileField);
        inputPanel.add(otpLabel);
        inputPanel.add(otpField);
        inputPanel.add(medicineLabel);
        inputPanel.add(medicineComboBox);
        inputPanel.add(medicinePriceLabel);
        inputPanel.add(warningText);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(58, 175, 169));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorderPainted(false);
        submitButton.setOpaque(true);

        buttonPanel.add(submitButton);

        JPanel paymentPanel = new JPanel(new GridLayout(2, 2));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Payment Options"));
        paymentPanel.setBackground(new Color(245, 245, 245));

        JLabel paymentLabel = new JLabel("Select Payment Method:");
        paymentComboBox = new JComboBox<>(new String[]{"Cash", "Card"});

        paymentPanel.add(paymentLabel);
        paymentPanel.add(paymentComboBox);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(paymentPanel, BorderLayout.SOUTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmission();
            }
        });

        frame.setVisible(true);
    }

    private void handleSubmission() {
        String name = nameField.getText();
        int age = 0;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            warningText.setText("Please Enter Valid Age.");
            return;
        }
        String mobileNumber = mobileField.getText();
        String otp = otpField.getText();
        String selectedMedicine = (String) medicineComboBox.getSelectedItem();
        String selectedPayment = (String) paymentComboBox.getSelectedItem();

        if (age < 18 || age > 100) {
            warningText.setText("Please Enter Valid Age.");
            return;
        } else {
            warningText.setText("");
        }

        int medicineCost = 0;
        String medicinePriceText = "Medicine Price: ";
        switch (selectedMedicine) {
            case "DOLO-650":
                medicineCost = 5;
                medicinePriceText += "5 rupees";
                break;
            case "ENO":
                medicineCost = 3;
                medicinePriceText += "3 rupees";
                break;
            case "GELUSIR":
                medicineCost = 30;
                medicinePriceText += "30 rupees";
                break;
            case "MEDICAL KIT":
                medicineCost = 50;
                medicinePriceText += "50 rupees";
                break;
            case "STAYFREE":
                medicineCost = 40;
                medicinePriceText += "40 rupees";
                break;
            case "Strepsils":
                medicineCost = 4;
                medicinePriceText += "4 rupees";
                break;
        }

        medicinePriceLabel.setText(medicinePriceText);

        String transactionResult = "";
        if (selectedPayment.equals("Cash")) {
            int paymentAmount;
            try {
                paymentAmount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount in rupees:"));
            } catch (NumberFormatException e) {
                transactionResult = "Invalid payment amount. Please enter a valid amount.";
                outputArea.setText(transactionResult);
                return;
            }

            if (paymentAmount == medicineCost) {
                transactionResult = "Payment Successful. Collect your medicine!";
                generateBill(name, selectedMedicine, medicineCost);
            } else if (paymentAmount < medicineCost) {
                transactionResult = "Insufficient amount! Please collect your " + paymentAmount + " rupees.";
            } else {
                int change = paymentAmount - medicineCost;
                transactionResult = "Payment Successful. Collect your medicine and change";
            }
        }
    }

    private void generateBill(String name, String medicine, int cost) {
        String bill = "Patient Name: " + name + "\n";
        bill += "Medicine: " + medicine + "\n";
        bill += "Cost: " + cost + " rupees\n";
        bill += "Thank you for using our service!";
        outputArea.setText(bill);
    }

    public static void main(String[] args) {
        new Medicine();
    }
}