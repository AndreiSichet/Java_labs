package test;
import java.awt.*;
import java.awt.event.*;

public class model_problem7 extends Frame {
    private TextField taskField;
    private List      taskList;
    private Button    addBtn;
    private Button    removeBtn;
    private Label     errorLabel;

    public model_problem7() {
        setTitle("To-Do List Manager");
        setSize(500, 400);
        setLayout(new BorderLayout());

        //  MENU 
        MenuBar  mb      = new MenuBar();
        Menu     options = new Menu("Options");
        MenuItem sortItem  = new MenuItem("Sort A-Z");
        MenuItem clearItem = new MenuItem("Clear All");
        options.add(sortItem);
        options.add(clearItem);
        mb.add(options);
        setMenuBar(mb);

        // CONTROLS (GridLayout) 
        Panel controls = new Panel(new GridLayout(3, 2, 10, 10));

        taskField  = new TextField();
        addBtn     = new Button("Add Task");
        removeBtn  = new Button("Remove Selected");
        errorLabel = new Label("");
        errorLabel.setForeground(Color.red);

        controls.add(new Label("Task:", Label.RIGHT)); controls.add(taskField);
        controls.add(addBtn);                          controls.add(removeBtn);
        controls.add(new Label(""));                   controls.add(errorLabel);

        //  LIST 
        taskList = new List(10);  // 10 visible rows

        add(controls,  BorderLayout.NORTH);
        add(taskList,  BorderLayout.CENTER);

        //  ADD 
        addBtn.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (task.isEmpty()) {
                errorLabel.setText("Task cannot be empty!");
                return;
            }
            taskList.add(task);
            taskField.setText("");
            errorLabel.setText("");
        });

        //  REMOVE 
        removeBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected == -1) {
                errorLabel.setText("No task selected!");
                return;
            }
            taskList.remove(selected);
            errorLabel.setText("");
        });

        //  SORT A-Z (menu) 
        sortItem.addActionListener(e -> {
            // pull everything out, sort, put back in
            String[] items = taskList.getItems();
            java.util.Arrays.sort(items);
            taskList.removeAll();
            for (String item : items) taskList.add(item);
            errorLabel.setText("");
        });

        //  CLEAR ALL (menu) 
        clearItem.addActionListener(e -> {
            taskList.removeAll();
            errorLabel.setText("");
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new model_problem7(); }
}