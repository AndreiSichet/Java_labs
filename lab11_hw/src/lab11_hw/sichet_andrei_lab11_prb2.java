package lab11_hw;
import java.awt.*;
import java.awt.event.*;

public class sichet_andrei_lab11_prb2 extends Frame implements ActionListener {
    private TextField display;
    private String num1 = "";
    private String num2 = "";
    private String operator = "";
    public sichet_andrei_lab11_prb2() {

        setTitle("GridBagLayout Calculator");
        setSize(400, 500);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipady = 20;
        add(display, gbc);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };
        int x = 0;
        int y = 1;
        
        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            gbc = new GridBagConstraints();
            gbc.gridx = x;
            gbc.gridy = y;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            add(btn, gbc);
            x++;
            if (x == 4) {
                x = 0;
                y++;
            }
        }
        
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
            if (num1.isEmpty() || num2.isEmpty())
                return;
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            double result = 0;
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
        new sichet_andrei_lab11_prb2();
    }
}