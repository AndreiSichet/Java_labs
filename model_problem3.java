package test;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class model_problem3 extends Frame {
	 private TextField nameField;
	 private TextField ageField;
	 private CheckboxGroup genderGroup;
	 private Checkbox male, female, other;
	 private Button submitBtn;
	 private TextArea outputArea;
	 // Error labels — one per field that can fail
	 private Label nameError;
	 private Label ageError;
	 private Label genderError;
	model_problem3(){
		super("Choice Problem");
        setSize(600, 400);
        setLayout(new GridLayout(5, 3, 10, 10));
        nameField = new TextField();
        ageField = new TextField();
        genderGroup = new CheckboxGroup();
        male   = new Checkbox("Male",   false, genderGroup);
        female = new Checkbox("Female", false, genderGroup);
        other  = new Checkbox("Other",  false, genderGroup);
        submitBtn = new Button("Submit");
        outputArea = new TextArea(5, 40);
        outputArea.setEditable(false);
        nameError   = new Label(""); nameError.setForeground(Color.red);
        ageError    = new Label(""); ageError.setForeground(Color.red);
        genderError = new Label(""); genderError.setForeground(Color.red);
        
        Panel genderPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.add(other);
        
        add(new Label("Name:", Label.RIGHT)); add(nameField);   add(nameError);
        add(new Label("Age:",  Label.RIGHT)); add(ageField);    add(ageError);
        add(new Label("Gender:", Label.RIGHT)); add(genderPanel); add(genderError);
        add(new Label(""));                   add(submitBtn);   add(new Label(""));
        add(new Label("Output:"));            add(outputArea);  add(new Label(""));
        
        ageField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        submitBtn.addActionListener(e -> {
            nameError.setText("");
            ageError.setText("");
            genderError.setText("");
            boolean valid = true;

            String nameText = nameField.getText().trim();
            if (nameText.isEmpty()) {
                nameError.setText("Name cannot be empty");
                valid = false;
            }

            String ageText = ageField.getText().trim();
            int ageValue = 0;
            if (ageText.isEmpty()) {
                ageError.setText("Age cannot be empty");
                valid = false;
            } else {
                ageValue = Integer.parseInt(ageText);
                if (ageValue < 1 || ageValue > 120) {
                    ageError.setText("Age must be 1-120");
                    valid = false;
                }
            }

            Checkbox selectedGender = genderGroup.getSelectedCheckbox();
            if (selectedGender == null) {
                genderError.setText("Please select a gender");
                valid = false;
            }

            if (valid) {
                outputArea.setText("");
                outputArea.append("Name:   " + nameText + "\n");
                outputArea.append("Age:    " + ageValue + "\n");
                outputArea.append("Gender: " + selectedGender.getLabel() + "\n");
            }
        });
		  addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) { System.exit(0); }
	        });

	        setVisible(true);  
	}
	public static void main(String[] args) {
		new model_problem3();

	}

}
