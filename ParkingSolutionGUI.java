import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingSolutionGUI extends JFrame {
    private JTextField nameField;
    private JTextField mobileField;
    private JTextField otpField;
    private JTextField locationField;
    private JRadioButton bikeOption;
    private JRadioButton carOption;
    private JRadioButton bothOption;
    private JComboBox<String> slotComboBox;
    private JButton submitButton;

    public ParkingSolutionGUI() {
        setTitle("Parking Solutions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 102, 204)); // Set the background color

        JPanel mainPanel = new JPanel(new GridLayout(9, 1));
        mainPanel.setBackground(new Color(0, 102, 204)); // Set the background color

        nameField = createInputField("Name");
        mobileField = createInputField("Mobile Number");
        otpField = createInputField("OTP");
        locationField = createInputField("Parking Location");

        bikeOption = createRadioButton("Bike");
        carOption = createRadioButton("Car");
        bothOption = createRadioButton("Both");

        slotComboBox = new JComboBox<>(new String[]{"Parking Parade", "Narasimha's House", "Mohan Rao's Home", "Nikhil's Residence"});
        slotComboBox.setForeground(Color.BLACK);
        slotComboBox.setBackground(Color.WHITE);

        JPanel vehiclePanel = new JPanel(new GridLayout(1, 3));
        vehiclePanel.setBackground(new Color(0, 102, 204)); // Set the background color
        vehiclePanel.add(bikeOption);
        vehiclePanel.add(carOption);
        vehiclePanel.add(bothOption);

        mainPanel.add(nameField);
        mainPanel.add(mobileField);
        mainPanel.add(otpField);
        mainPanel.add(locationField);
        mainPanel.add(vehiclePanel);
        mainPanel.add(slotComboBox);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 102, 0)); // Set the background color
        submitButton.setForeground(Color.WHITE); // Set the text color
        submitButton.setPreferredSize(new Dimension(200, 40));
        submitButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font

        mainPanel.add(submitButton);

        add(mainPanel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateParkingCost();
            }
        });
    }

    private JTextField createInputField(String labelText) {
        JTextField field = new JTextField(20);
        field.setBorder(BorderFactory.createTitledBorder(labelText));
        field.setForeground(Color.BLACK); // Set the text color
        field.setBackground(Color.WHITE); // Set the background color
        return field;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setForeground(Color.BLACK); // Set the text color
        radioButton.setBackground(new Color(0, 102, 204)); // Set the background color
        return radioButton;
    }

    private void calculateParkingCost() {
        String name = nameField.getText();
        String mobile = mobileField.getText();
        String otp = otpField.getText();
        String location = locationField.getText();
        String selectedVehicle = bikeOption.isSelected() ? "Bike" : carOption.isSelected() ? "Car" : bothOption.isSelected() ? "Both" : "";
        String selectedSlot = slotComboBox.getSelectedItem().toString();

        double cost = 0.0;
        if (selectedVehicle.equals("Bike")) {
            cost = calculateCostForBike();
        } else if (selectedVehicle.equals("Car")) {
            cost = calculateCostForCar();
        } else if (selectedVehicle.equals("Both")) {
            cost = calculateCostForBoth();
        }

        String receipt = "Name: " + name + "\n" +
                        "Mobile Number: " + mobile + "\n" +
                        "OTP: " + otp + "\n" +
                        "Parking Location: " + location + "\n" +
                        "Selected Vehicle: " + selectedVehicle + "\n" +
                        "Selected Slot: " + selectedSlot + "\n" +
                        "Parking Cost: $" + cost;

        JOptionPane.showMessageDialog(this, receipt, "Parking Receipt", JOptionPane.INFORMATION_MESSAGE);
    }

    private double calculateCostForBike() {
        int hours = 3;
        double rate = 30.0;
        return hours * rate;
    }

    private double calculateCostForCar() {
        int hours = 3;
        double rate = 50.0;
        return hours * rate;
    }

    private double calculateCostForBoth() {
        int bikeHours = 2;
        int carHours = 3;
        double bikeRate = 30.0;
        double carRate = 50.0;
        return (bikeHours * bikeRate) + (carHours * carRate);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ParkingSolutionGUI frame = new ParkingSolutionGUI();
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}