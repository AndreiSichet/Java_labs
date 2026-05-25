package test;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class model_problem6 extends Frame {
    private TextArea textArea;
    private Button   loadBtn;
    private Button   saveBtn;
    private Label    filenameLabel;
    private String   currentFile = null;
    private boolean  modified    = false;

    public model_problem6() {
        setTitle("Text File Editor");
        setSize(600, 500);
        setLayout(new BorderLayout());

        Panel controls = new Panel(new GridLayout(2, 3, 10, 10));

        loadBtn       = new Button("Load");
        saveBtn       = new Button("Save");
        filenameLabel = new Label("No file loaded");

        controls.add(loadBtn);       controls.add(saveBtn);        controls.add(filenameLabel);
        controls.add(new Label("")); controls.add(new Label(""));  controls.add(new Label(""));

        textArea = new TextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        add(controls, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);

        loadBtn.addActionListener(e -> {
            FileDialog fd = new FileDialog(this, "Load file", FileDialog.LOAD);
            fd.setFile("*.txt");
            fd.setVisible(true);
            if (fd.getFile() == null) return;
            currentFile = fd.getDirectory() + fd.getFile();
            filenameLabel.setText(fd.getFile());
            try {
                BufferedReader br = new BufferedReader(new FileReader(currentFile));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append("\n");
                br.close();
                textArea.setText(sb.toString());
                modified = false;
                setTitle("Text File Editor - " + fd.getFile());
            } catch (IOException ex) {
                filenameLabel.setText("Error: " + ex.getMessage());
            }
        });

        saveBtn.addActionListener(e -> {
            if (currentFile == null) {
                FileDialog fd = new FileDialog(this, "Save file", FileDialog.SAVE);
                fd.setFile("*.txt");
                fd.setVisible(true);
                if (fd.getFile() == null) return;
                currentFile = fd.getDirectory() + fd.getFile();
                filenameLabel.setText(fd.getFile());
            }
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(currentFile));
                pw.print(textArea.getText());
                pw.close();
                modified = false;
                setTitle("Text File Editor - " + filenameLabel.getText());
            } catch (IOException ex) {
                filenameLabel.setText("Error: " + ex.getMessage());
            }
        });

        textArea.addTextListener(e -> {
            modified = true;
            setTitle("Text File Editor - Unsaved changes*");
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (!modified) { System.exit(0); return; }
                Dialog d = new Dialog(model_problem6.this, "Unsaved Changes", true); // true = modal
                d.setSize(320, 130);
                d.setLayout(new GridLayout(2, 1, 10, 10));
                d.add(new Label("You have unsaved changes. Exit anyway?", Label.CENTER));
                Panel btnPanel = new Panel(new FlowLayout());
                Button yes = new Button("Exit without saving");
                Button no  = new Button("Cancel");
                btnPanel.add(yes);
                btnPanel.add(no);
                d.add(btnPanel);
                yes.addActionListener(ev -> System.exit(0));
                no.addActionListener(ev  -> d.setVisible(false));
                d.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent ev) { d.setVisible(false); }
                });
                d.setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new model_problem6(); }
}