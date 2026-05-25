package exam_prep;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class Dish {
    private int type;
    private String name;

    public Dish(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public void setName(String name) { this.name = name; }
    public void setType(int type)    { this.type = type; }
    public String getName()          { return name; }
    public int getType()             { return type; }
}

public class SelectionListProblem extends Frame {

    private TextArea textarea;
    private Button   adder;
    private Label    errorLabel;
    private List     dish1, dish2, dish3;
    private ArrayList<Dish> dishList;

    public static void populate(ArrayList<Dish> arr) {
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            int    t = rnd.nextInt(3) + 1;          
            String n = "Dish" + i;                  
            arr.add(new Dish(t, n));
        }
    }

    public SelectionListProblem() {
        setTitle("Select Problem");
        setSize(600, 450);
        setLayout(new BorderLayout(5, 5));

        
        Panel controls = new Panel(new FlowLayout(FlowLayout.LEFT));
        textarea   = new TextArea(5, 40);
        adder      = new Button("AddBtn");
        errorLabel = new Label("");
        errorLabel.setForeground(Color.RED);

        controls.add(textarea);
        controls.add(adder);
        controls.add(errorLabel);

        
        Panel lists = new Panel(new GridLayout(3, 2, 5, 5));
        dish1 = new List(5, false);
        dish2 = new List(5, false);
        dish3 = new List(5, false);

        lists.add(new Label("Type 1:")); lists.add(dish1);
        lists.add(new Label("Type 2:")); lists.add(dish2);
        lists.add(new Label("Type 3:")); lists.add(dish3);

        add(controls, BorderLayout.NORTH);
        add(lists,    BorderLayout.CENTER);

        
        dishList = new ArrayList<>();
        populate(dishList);

        for (Dish d : dishList) {
            if      (d.getType() == 1) dish1.add(d.getName());
            else if (d.getType() == 2) dish2.add(d.getName());
            else                       dish3.add(d.getName());
        }

      
        adder.addActionListener(e -> {
            int sel1 = dish1.getSelectedIndex();
            int sel2 = dish2.getSelectedIndex();
            int sel3 = dish3.getSelectedIndex();

            
            if (sel1 == -1) {
                errorLabel.setText("No dish selected for type 1!");
                return;
            }
            if (sel2 == -1) {
                errorLabel.setText("No dish selected for type 2!");
                return;
            }
            if (sel3 == -1) {
                errorLabel.setText("No dish selected for type 3!");
                return;
            }

            errorLabel.setText("");   

            
            String con = dish1.getSelectedItem()
                       + dish2.getSelectedItem()
                       + dish3.getSelectedItem();
            textarea.append(con + "\n");
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SelectionListProblem();
    }
}