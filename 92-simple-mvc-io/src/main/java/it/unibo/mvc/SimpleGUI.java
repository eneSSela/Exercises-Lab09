package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

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
}
