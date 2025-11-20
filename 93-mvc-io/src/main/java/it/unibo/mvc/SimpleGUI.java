package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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

    private static final int PROPORTION = 5;
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

    /**
     * Displays the main frame, resizing it proportionally to the screen.
     */
    private void display() {
        /*
        * Make the frame one fifth the resolution of the screen. This very method is
        * enough for a single screen setup. In case of multiple monitors, the
        * primary is selected. In order to deal coherently with multimonitor
        * setups, other facilities exist (see the Java documentation about this
        * issue). It is MUCH better than manually specify the size of a window
        * in pixel: it takes into account the current resolution.
        */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
        * Instead of appearing at (0,0), upper left corner of the screen, this
        * flag makes the OS window manager take care of the default positioning
        * on screen. Results may vary, but it is generally the best choice.
        */
        frame.setLocationByPlatform(true);
        frame.pack();
        /*
        * OK, ready to push the frame onscreen
        */
        frame.setVisible(true);
    }

}
