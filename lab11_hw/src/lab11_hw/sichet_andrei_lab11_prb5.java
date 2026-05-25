package lab11_hw;
import java.awt.*;
import java.awt.event.*;
public class sichet_andrei_lab11_prb5 extends Frame implements ActionListener {
    // Text fields
    TextField rField;
    TextField gField;
    TextField bField;
    // Button
    Button showButton;
    // Result label
    Label resultLabel;
    public sichet_andrei_lab11_prb5() {
        // Frame settings
        setTitle("RGB Color Application");
        setSize(450, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        // Labels and TextFields
        add(new Label("R value (0-255):"));
        rField = new TextField();
        add(rField);

        add(new Label("G value (0-255):"));
        gField = new TextField();
        add(gField);

        add(new Label("B value (0-255):"));
        bField = new TextField();
        add(bField);

        // Button
        showButton = new Button("Show Color");
        showButton.addActionListener(this);

        add(new Label(""));
        add(showButton);

        // Result Label
        resultLabel = new Label("", Label.CENTER);
        add(resultLabel);

        // Window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Convert text to integers
            int r = Integer.parseInt(rField.getText());
            int g = Integer.parseInt(gField.getText());
            int b = Integer.parseInt(bField.getText());

            // Validate range
            if (r < 0 || r > 255 ||
                g < 0 || g > 255 ||
                b < 0 || b > 255) {

                throw new IllegalArgumentException(
                        "Values must be between 0 and 255"
                );
            }

            // Set success message
            resultLabel.setText(
                    "RGB(" + r + ", " + g + ", " + b + ")"
            );

            // Set resulting color
            resultLabel.setForeground(new Color(r, g, b));

        }
        catch (NumberFormatException ex) {
            resultLabel.setText("Error: Please enter numeric values!");
            resultLabel.setForeground(Color.RED);
        }
        catch (IllegalArgumentException ex) {
            resultLabel.setText("Error: " + ex.getMessage());
            resultLabel.setForeground(Color.RED);
        }
    }
    public static void main(String[] args) {
        new sichet_andrei_lab11_prb5();
    }
}