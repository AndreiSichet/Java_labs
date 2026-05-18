package lab11_hw;
import java.awt.*;
import java.awt.event.*;
public class sichet_andrei_lab11_prb1 extends Frame implements ActionListener {
    TextField display;
    String num1 = "", num2 = "", operator = "";
    public sichet_andrei_lab11_prb1() {
        
        setTitle("GridLayout Calculator");
        setSize(350, 450);
        setLayout(new BorderLayout());
        
        display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);
        
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        
        if (command.matches("[0-9]")) {
            if (operator.isEmpty()) {
                num1 += command;
                display.setText(num1);
            } else {
                num2 += command;
                display.setText(num1 + " " + operator + " " + num2);
            }
        }
        
        else if (command.equals("C")) {
            num1 = "";
            num2 = "";
            operator = "";
            display.setText("");
        }
        
        else if (command.equals("=")) {
            double result = 0;
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            switch (operator) {
                case "+":
                    result = n1 + n2;
                    break;
                case "-":
                    result = n1 - n2;
                    break;
                case "*":
                    result = n1 * n2;
                    break;
                case "/":
                    if (n2 != 0)
                        result = n1 / n2;
                    else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            
            num1 = String.valueOf(result);
            num2 = "";
            operator = "";
        }

        
        else {
            if (!num1.isEmpty()) {
                operator = command;
                display.setText(num1 + " " + operator);
            }
        }
    }
    public static void main(String[] args) {
        new sichet_andrei_lab11_prb1();
    }
}