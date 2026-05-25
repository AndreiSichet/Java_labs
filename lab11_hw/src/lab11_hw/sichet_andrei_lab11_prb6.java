package lab11_hw;
import java.awt.*;
import java.awt.event.*;

public class sichet_andrei_lab11_prb6 extends Frame implements ActionListener {
    // Text fields
    TextField nameField;
    TextField surnameField;
    // Drop-down lists
    Choice yearChoice;
    Choice facultyChoice;
    Choice courseChoice;
    // Checkbox
    Checkbox financingBox;
    // Button
    Button signUpButton;
    // TextArea
    TextArea resultArea;
    public sichet_andrei_lab11_prb6() {
        // Frame settings
        setTitle("Online Course Sign-Up");
        setSize(500, 500);
        setLayout(new GridLayout(8, 2, 10, 10));
        // Name
        add(new Label("Name:"));
        nameField = new TextField();
        add(nameField);
        // Surname
        add(new Label("Surname:"));
        surnameField = new TextField();
        add(surnameField);
        // Year of study
        add(new Label("Year of Study:"));
        yearChoice = new Choice();

        yearChoice.add("1");
        yearChoice.add("2");
        yearChoice.add("3");
        yearChoice.add("4");

        add(yearChoice);

        // Faculty
        add(new Label("Faculty:"));
        facultyChoice = new Choice();

        facultyChoice.add("Computer Science");
        facultyChoice.add("Electronics");
        facultyChoice.add("Mathematics");
        facultyChoice.add("Automation");

        add(facultyChoice);

        // Course
        add(new Label("Course:"));
        courseChoice = new Choice();

        courseChoice.add("Java Programming");
        courseChoice.add("Python");
        courseChoice.add("Web Development");
        courseChoice.add("Artificial Intelligence");

        add(courseChoice);

        // Financing checkbox
        add(new Label("Financing (checked = Budget):"));
        financingBox = new Checkbox("Budget");
        add(financingBox);

        // Sign-up button
        signUpButton = new Button("Sign Up");
        signUpButton.addActionListener(this);

        add(new Label(""));
        add(signUpButton);

        // TextArea
        resultArea = new TextArea("", 5, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);

        add(new Label("Registered Information:"));
        add(resultArea);

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
        String surname = surnameField.getText();

        String year = yearChoice.getSelectedItem();
        String faculty = facultyChoice.getSelectedItem();
        String course = courseChoice.getSelectedItem();

        String financing;

        if (financingBox.getState()) {
            financing = "Budget";
        } else {
            financing = "Tax";
        }

        // Display information in TextArea
        resultArea.setText(
                "=== Student Registration ===\n" +
                "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Year of Study: " + year + "\n" +
                "Faculty: " + faculty + "\n" +
                "Financing: " + financing + "\n" +
                "Course: " + course
        );
    }

    public static void main(String[] args) {
        new sichet_andrei_lab11_prb6();
    }
}