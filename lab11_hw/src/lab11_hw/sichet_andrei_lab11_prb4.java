package lab11_hw;
import java.awt.*;
import java.awt.event.*;

public class sichet_andrei_lab11_prb4 extends Frame implements ActionListener {
    TextField nameField;
    TextField groupField;
    Label resultLabel;
    Button displayButton;
    public sichet_andrei_lab11_prb4() {
        // Frame settings
        setTitle("Student Information");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        // Components
        Label nameLabel = new Label("Enter your name:");
        nameField = new TextField();

        Label groupLabel = new Label("Enter your group:");
        groupField = new TextField();

        displayButton = new Button("Display Info");

        resultLabel = new Label("", Label.CENTER);

        // RGB Color (122,123,129)
        resultLabel.setForeground(new Color(122, 123, 129));

        // Add components
        add(nameLabel);
        add(nameField);

        add(groupLabel);
        add(groupField);

        add(new Label(""));
        add(displayButton);

        add(resultLabel);

        // Button event
        displayButton.addActionListener(this);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String group = groupField.getText();
        resultLabel.setText(
                "Name: " + name + " | Group: " + group
        );
    }
    public static void main(String[] args) {

        new sichet_andrei_lab11_prb4();
    }
}