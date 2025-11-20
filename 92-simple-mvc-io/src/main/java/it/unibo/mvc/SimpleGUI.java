package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame("Simple GUI");

    /**
     * Builds the whole graphical interface
     */
    public SimpleGUI() {
        final JPanel panelText = new JPanel();
        final JPanel panelSave = new JPanel();

        frame.setLayout(new BorderLayout());
        frame.add(panelText, BorderLayout.CENTER);
        frame.add(panelSave, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea text = new JTextArea();
        panelText.add(text, BorderLayout.CENTER);

        final JButton save = new JButton("Save");
        panelSave.add(save, BorderLayout.CENTER);

        final Controller controller = new Controller();

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.save(text.getText());
                } catch (final IOException ex) {
                    JOptionPane.showMessageDialog(frame,
                        "Error while selecting file:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    /**
     * Displays the main frame, resizing it proportionally to the screen
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
