package exam_prep;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student1 {
    private String name;
    private double grade;

    public Student1(String name, double grade) {
        this.name  = name;
        this.grade = grade;
    }

    public String getName()  { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() { return name + " - " + String.format("%.2f", grade); }
}

public class class_manager extends Frame {
    private TextField         nameField;
    private TextField         gradeField;
    private Button            addBtn;
    private Button            removeBtn;
    private Label             errorLabel;
    private Label             averageLabel;
    private Label             statsLabel;
    private List              studentList;
    private ArrayList<Student1> students;

    public class_manager() {
        students = new ArrayList<>();

        setTitle("Class Manager");
        setSize(500, 500);
        setLayout(new BorderLayout());

        //  MENU 
        MenuBar mb    = new MenuBar();
        Menu    stats = new Menu("Stats");
        MenuItem highItem = new MenuItem("Show Highest");
        MenuItem lowItem  = new MenuItem("Show Lowest");
        stats.add(highItem);
        stats.add(lowItem);
        mb.add(stats);
        setMenuBar(mb);

        // CONTROLS (GridLayout)
        Panel controls = new Panel(new GridLayout(4, 2, 10, 10));

        nameField    = new TextField();
        gradeField   = new TextField();
        addBtn       = new Button("Add");
        removeBtn    = new Button("Remove Selected");
        errorLabel   = new Label("");
        errorLabel.setForeground(Color.red);
        averageLabel = new Label("Average: -");

        controls.add(new Label("Name:",  Label.RIGHT));  controls.add(nameField);
        controls.add(new Label("Grade:", Label.RIGHT));  controls.add(gradeField);
        controls.add(addBtn);                            controls.add(removeBtn);
        controls.add(averageLabel);                      controls.add(errorLabel);

        // LIST
        studentList = new List(8);

        // STATS LABEL 
        statsLabel = new Label("");
        statsLabel.setForeground(Color.red);

        add(controls,    BorderLayout.NORTH);
        add(studentList, BorderLayout.CENTER);
        add(statsLabel,  BorderLayout.SOUTH);

        // KEY FILTER on grade 
        gradeField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '.') {
                    e.consume();
                }
            }
        });

        // ADD 
        addBtn.addActionListener(e -> {
            errorLabel.setText("");
            String nameText  = nameField.getText().trim();
            String gradeText = gradeField.getText().trim();

            if (nameText.isEmpty())  { errorLabel.setText("Enter a name!");  return; }
            if (gradeText.isEmpty()) { errorLabel.setText("Enter a grade!"); return; }

            double gradeDouble = Double.parseDouble(gradeText);
            if (gradeDouble < 0 || gradeDouble > 10) {
                errorLabel.setText("Grade must be 0-10!");
                return;
            }

            Student1 student = new Student1(nameText, gradeDouble);
            students.add(student);
            studentList.add(student.toString());
            nameField.setText("");
            gradeField.setText("");
            updateAverage();
        });

        //  REMOVE SELECTED 
        removeBtn.addActionListener(e -> {
            errorLabel.setText("");
            int selected = studentList.getSelectedIndex();
            if (selected == -1) { errorLabel.setText("Select a student first!"); return; }
            students.remove(selected);
            studentList.remove(selected);
            updateAverage();
        });

        // SHOW HIGHEST 
        highItem.addActionListener(e -> {
            if (students.isEmpty()) { statsLabel.setText("No students yet!"); return; }
            Student1 highest = students.get(0);
            for (Student1 s : students)
                if (s.getGrade() > highest.getGrade()) highest = s;
            statsLabel.setText("Highest: " + highest.toString());
        });

        // SHOW LOWEST 
        lowItem.addActionListener(e -> {
            if (students.isEmpty()) { statsLabel.setText("No students yet!"); return; }
            Student1 lowest = students.get(0);
            for (Student1 s : students)
                if (s.getGrade() < lowest.getGrade()) lowest = s;
            statsLabel.setText("Lowest: " + lowest.toString());
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    private void updateAverage() {
        if (students.isEmpty()) { averageLabel.setText("Average: -"); return; }
        double sum = 0;
        for (Student1 s : students) sum += s.getGrade();
        averageLabel.setText(String.format("Average: %.2f", sum / students.size()));
    }

    public static void main(String[] args) { new class_manager(); }
}