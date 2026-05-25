package exam_prep;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student2 {
    private String name;
    private int    age;
    private int    year;

    public Student2(String name, int age, int year) {
        this.name = name;
        this.age  = age;
        this.year = year;
    }

    public String getName() { return name; }
    public int    getAge()  { return age; }
    public int    getYear() { return year; }

    @Override
    public String toString() {
        return name + " - " + age + " - Anul " + year;
    }
}

public class TabbedViewProblem extends Frame {

    private ArrayList<Student2> students = new ArrayList<>();

    //  TAB 1 components
    private TextField    nameField;
    private TextField    ageField;
    private CheckboxGroup yearGroup;
    private Checkbox     y1, y2, y3, y4;
    private Button       addBtn;
    private Label        addStatusLabel;

    //  TAB 2 components
    private java.awt.List studentList;
    private Button        removeBtn;
    private Label         removeErrorLabel;

    // TAB 3 components
    private Label  totalLabel;
    private Label  avgAgeLabel;
    private Label  year1Label;
    private Label  year2Label;
    private Label  year3Label;
    private Label  year4Label;
    private Button refreshBtn;

    public TabbedViewProblem() {
        setTitle("Student Profile Viewer");
        setSize(500, 450);
        setLayout(new BorderLayout());

        //TABBED PANE 
        // TabbedPane is a Swing component so we need javax.swing
        javax.swing.JTabbedPane tabs = new javax.swing.JTabbedPane();

        tabs.addTab("Add Student", buildAddPanel());
        tabs.addTab("View Students", buildViewPanel());
        tabs.addTab("Stats", buildStatsPanel());

        add(tabs, BorderLayout.CENTER);

        // EVENTS
        ageField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) e.consume();
            }
        });

        addBtn.addActionListener(e -> {
            addStatusLabel.setForeground(Color.red);
            String nameText = nameField.getText().trim();
            String ageText  = ageField.getText().trim();

            if (nameText.isEmpty()) { addStatusLabel.setText("Enter a name!"); return; }
            if (ageText.isEmpty())  { addStatusLabel.setText("Enter an age!"); return; }

            int age = Integer.parseInt(ageText);
            if (age < 18 || age > 30) { addStatusLabel.setText("Age must be 18-30!"); return; }

            Checkbox selected = yearGroup.getSelectedCheckbox();
            if (selected == null) { addStatusLabel.setText("Select a year!"); return; }

            int year = Integer.parseInt(selected.getLabel());
            students.add(new Student2(nameText, age, year));

            // sync the View tab list
            studentList.add(students.get(students.size() - 1).toString());

            nameField.setText("");
            ageField.setText("");
            addStatusLabel.setForeground(Color.green.darker());
            addStatusLabel.setText("Student added!");
        });

        removeBtn.addActionListener(e -> {
            int idx = studentList.getSelectedIndex();
            if (idx == -1) { removeErrorLabel.setText("Select a student first!"); return; }
            students.remove(idx);
            studentList.remove(idx);
            removeErrorLabel.setText("");
        });

        refreshBtn.addActionListener(e -> updateStats());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    //  BUILD TAB 1 
    private Panel buildAddPanel() {
        Panel p = new Panel(new GridLayout(6, 2, 10, 10));

        nameField     = new TextField();
        ageField      = new TextField();
        yearGroup     = new CheckboxGroup();
        y1 = new Checkbox("1", false, yearGroup);
        y2 = new Checkbox("2", false, yearGroup);
        y3 = new Checkbox("3", false, yearGroup);
        y4 = new Checkbox("4", false, yearGroup);
        addBtn        = new Button("Add");
        addStatusLabel = new Label("");

        Panel yearPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        yearPanel.add(y1); yearPanel.add(y2);
        yearPanel.add(y3); yearPanel.add(y4);

        p.add(new Label("Name:",  Label.RIGHT)); p.add(nameField);
        p.add(new Label("Age:",   Label.RIGHT)); p.add(ageField);
        p.add(new Label("Year:",  Label.RIGHT)); p.add(yearPanel);
        p.add(new Label(""));                    p.add(addBtn);
        p.add(new Label(""));                    p.add(addStatusLabel);

        return p;
    }

    // BUILD TAB 2
    private Panel buildViewPanel() {
        Panel p = new Panel(new BorderLayout());

        studentList      = new java.awt.List(10);
        removeBtn        = new Button("Remove Selected");
        removeErrorLabel = new Label("");
        removeErrorLabel.setForeground(Color.red);

        Panel bottom = new Panel(new GridLayout(1, 2, 10, 10));
        bottom.add(removeBtn);
        bottom.add(removeErrorLabel);

        p.add(studentList, BorderLayout.CENTER);
        p.add(bottom,      BorderLayout.SOUTH);

        return p;
    }

    //BUILD TAB 3
    private Panel buildStatsPanel() {
        Panel p = new Panel(new GridLayout(8, 2, 10, 10));

        totalLabel  = new Label("-");
        avgAgeLabel = new Label("-");
        year1Label  = new Label("-");
        year2Label  = new Label("-");
        year3Label  = new Label("-");
        year4Label  = new Label("-");
        refreshBtn  = new Button("Refresh");

        p.add(new Label("Total students:", Label.RIGHT)); p.add(totalLabel);
        p.add(new Label("Average age:",    Label.RIGHT)); p.add(avgAgeLabel);
        p.add(new Label("Year 1:",         Label.RIGHT)); p.add(year1Label);
        p.add(new Label("Year 2:",         Label.RIGHT)); p.add(year2Label);
        p.add(new Label("Year 3:",         Label.RIGHT)); p.add(year3Label);
        p.add(new Label("Year 4:",         Label.RIGHT)); p.add(year4Label);
        p.add(new Label(""));                             p.add(refreshBtn);

        return p;
    }

    // UPDATE STATS 
    private void updateStats() {
        if (students.isEmpty()) {
            totalLabel.setText("0");
            avgAgeLabel.setText("-");
            year1Label.setText("0"); year2Label.setText("0");
            year3Label.setText("0"); year4Label.setText("0");
            return;
        }

        int total = students.size();
        int sumAge = 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0;

        for (Student2 s : students) {
            sumAge += s.getAge();
            if      (s.getYear() == 1) c1++;
            else if (s.getYear() == 2) c2++;
            else if (s.getYear() == 3) c3++;
            else                       c4++;
        }

        totalLabel.setText(String.valueOf(total));
        avgAgeLabel.setText(String.format("%.1f", (double) sumAge / total));
        year1Label.setText(String.valueOf(c1));
        year2Label.setText(String.valueOf(c2));
        year3Label.setText(String.valueOf(c3));
        year4Label.setText(String.valueOf(c4));
    }

    public static void main(String[] args) { new TabbedViewProblem(); }
}