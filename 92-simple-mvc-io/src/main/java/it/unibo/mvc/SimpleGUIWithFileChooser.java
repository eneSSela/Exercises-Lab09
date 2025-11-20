package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame("Simple GUI with FileChooser");

    /**
     * Builds the whole graphical interface.
     */
    public SimpleGUIWithFileChooser() {

        final JPanel northPanel = new JPanel(new BorderLayout());
        final JPanel centerPanel = new JPanel(new BorderLayout());
        final JPanel southPanel = new JPanel(new BorderLayout());

        final JButton browse = new JButton("Browse...");
        final JButton save = new JButton("Save");
        final JTextArea text = new JTextArea();
        final JTextField textField = new JTextField();

        frame.setLayout(new BorderLayout());
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        northPanel.add(textField, BorderLayout.CENTER);
        northPanel.add(browse, BorderLayout.LINE_END);

        centerPanel.add(text, BorderLayout.CENTER);
        southPanel.add(save, BorderLayout.CENTER);

        textField.setEditable(false);

        final Controller controller = new Controller();
        textField.setText(controller.getPath());

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    final File file = fileChooser.getSelectedFile();
                    controller.setCurrentFile(file);
                    textField.setText(controller.getPath());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error while selecting the file\n",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.save(text.getText());
                } catch (final IOException ex) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Error while saving the file :\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
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
        * OK, ready to push the frame onscreen.
        */
        frame.setVisible(true);
    }

    /**
     * Main method that starts the graphical application.
     * 
     * @param args are ignores
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
