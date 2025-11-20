package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * It has a text field, a text area and two buttons: "Print" and "Show History".
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Simple GUI");

    public SimpleGUI() {
        final JPanel northPanel = new JPanel();
        final JPanel centerPanel = new JPanel();
        final JPanel southPanel = new JPanel();

        final JTextArea textArea = new JTextArea();
        final JTextField textField = new JTextField();
        final JButton print = new JButton("Print");
        final JButton showHistory = new JButton("Show History");

        final Controller controller = new SimpleController();

        frame.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new FlowLayout());

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        northPanel.add(textField, BorderLayout.CENTER);

        final JScrollPane scrollPane = new JScrollPane();
        textArea.setEditable(false);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        southPanel.add(print);
        southPanel.add(showHistory);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String input = textField.getText();
                controller.setNextString(input);
                controller.print();
            }
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> listHistory = controller.getHistory();
                final StringBuilder sb = new StringBuilder();
                for (final String s : listHistory) {
                    sb.append(s).append('\n');
                }
                textArea.setText(sb.toString());
            }
        });
    }

}
