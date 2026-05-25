package lab12_hw;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
public class sichet_andrei_lab12_prb5 extends Frame
        implements ActionListener, KeyListener {
    // Input fields
    TextField studentIdentityField;
    TextField studyGroupField;
    // Course selection
    Choice academicCourseSelector;
    // Register button
    Button enrollmentTriggerButton;
    // Status label
    Label operationFeedbackLabel;
    public sichet_andrei_lab12_prb5() {
        // Frame settings
        setTitle("Course Registration Form");
        setSize(500, 350);
        setLayout(new GridLayout(5, 2, 10, 10));
        // NAME FIELD
        add(new Label("Student Name:"));
        studentIdentityField = new TextField();
        studentIdentityField.addKeyListener(this);
        add(studentIdentityField);
        // GROUP FIELD
        add(new Label("Study Group:"));
        studyGroupField = new TextField();
        studyGroupField.addKeyListener(this);
        add(studyGroupField);
        // COURSE SELECTION
        add(new Label("Choose Course:"));
        academicCourseSelector = new Choice();
        academicCourseSelector.add("Java Programming");
        academicCourseSelector.add("Python");
        academicCourseSelector.add("Databases");
        academicCourseSelector.add("Artificial Intelligence");
        add(academicCourseSelector);
        // REGISTER BUTTON
        enrollmentTriggerButton =new Button("Register");
        enrollmentTriggerButton.addActionListener(this);
        add(new Label(""));
        add(enrollmentTriggerButton);
        // STATUS LABEL
        operationFeedbackLabel =new Label("", Label.CENTER);
        add(operationFeedbackLabel);
        // WINDOW CLOSE
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    // BUTTON EVENT
    @Override
    public void actionPerformed(ActionEvent e) {
        String registeredName =studentIdentityField.getText();
        String registeredGroup =studyGroupField.getText();
        String selectedCourse =academicCourseSelector.getSelectedItem();
        try {
            FileWriter enrollmentWriter =
                    new FileWriter(
                            "course_registrations.txt",
                            true
                    );
            enrollmentWriter.write(
                    "Name: " + registeredName
                    + " | Group: " + registeredGroup
                    + " | Course: " + selectedCourse
                    + "\n"
            );
            enrollmentWriter.close();
            operationFeedbackLabel.setText(
                    "Registration saved successfully!"
            );

            operationFeedbackLabel.setForeground(
                    Color.BLUE
            );
        } catch (IOException fileException) {
            operationFeedbackLabel.setText(
                    "Error while saving data!"
            );
            operationFeedbackLabel.setForeground(
                    Color.RED
            );
        }
    }
    // KEY VALIDATION
    @Override
    public void keyTyped(KeyEvent e) {
        // Name field validation
        if (e.getSource() == studentIdentityField) {
            char typedSymbol = e.getKeyChar();
            if (!Character.isLetter(typedSymbol)
                    && typedSymbol != ' '
                    && typedSymbol != '\b') {
                e.consume();
            }
        }
        // Group field validation
        if (e.getSource() == studyGroupField) {
            char typedDigit = e.getKeyChar();
            if (!Character.isDigit(typedDigit)
                    && typedDigit != '\b') {
                e.consume();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public static void main(String[] args) {
        new sichet_andrei_lab12_prb5();
    }
}