package com.gvaneyck.util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.io.PrintStream;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleWindow extends JDialog {
    private static final long serialVersionUID = 6067310150468068797L;
    
    StringBuffer data = new StringBuffer();
    StringOutputStream sos = new StringOutputStream(data);

    private int width = 480;
    private int height = 320;

    private static final JTextArea txtConsole = new JTextArea();
    private static final JScrollPane scrollArea = new JScrollPane(txtConsole);

    public static void main(String[] args) {
        ConsoleWindow temp = new ConsoleWindow();
        
        while (temp.isVisible()) {
            try { Thread.sleep(10); } catch (Exception e) { }
        }
        
        System.exit(0);
    }

    public ConsoleWindow() {
        this(0, 0);
    }

    public ConsoleWindow(int xpos, int ypos) {
        redirectOutput();
        initWindow(xpos, ypos);
    }
    
    private void redirectOutput() {
        // Redirect output
        System.setOut(new PrintStream(sos));
        System.setErr(new PrintStream(sos));

        // Start up the thread
        Thread t = new Thread() {
            String lastData = "";

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {}
                    String tempData = data.toString();
                    if (!lastData.equals(tempData)) {
                        lastData = tempData;
                        txtConsole.setText(lastData);
                    }
                }
            }
        };
        t.setDaemon(true);
        t.setName("ConsoleWindow");
        t.start();
    }
    
    private void initWindow(int xpos, int ypos) {
        setTitle("Console");
        
        txtConsole.setEditable(false);
        scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(scrollArea);

        pane.addHierarchyBoundsListener(new HierarchyBoundsListener() {
            public void ancestorMoved(HierarchyEvent e) {}

            public void ancestorResized(HierarchyEvent e) {
                Dimension d = getSize();
                width = d.width;
                height = d.height;
                doMyLayout();
            }
        });

        // Window settings
        setSize(width, height);
        setLocation(xpos, ypos);
        setMinimumSize(new Dimension(width, height));
        setVisible(true);
    }

    public void doMyLayout() {
        Insets i = getInsets();
        int twidth = width - i.left - i.right;
        int theight = height - i.top - i.bottom;

        scrollArea.setBounds(5, 5, twidth - 10, theight - 10);
    }
}
