package debug_renderer.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Arrows extends JPanel {

    private static final Dimension dimension = new Dimension(160, 80);
    private Jbox2DDebugConfig config;
    private MutableSettings mutableSettings;

    private JButton buttonRight;
    private JButton buttonLeft;
    private JButton buttonUp;
    private JButton buttonDown;

    private boolean mouseDown = false;
    private Timer timer;

    public Arrows(Jbox2DDebugConfig config, MutableSettings mutableSettings) {
        this.config = config;
        this.mutableSettings = mutableSettings;

        GridLayout layout = new GridLayout(3, 3);
        setLayout(layout);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);

        buttonRight = newButton(">", e -> mutableSettings.moveRight());
        buttonLeft = newButton("<", e -> mutableSettings.moveLeft());
        buttonUp = newButton("/\\", e -> mutableSettings.moveUp());
        buttonDown = newButton("\\/", e -> mutableSettings.moveDown());

        addEmptyField();
        add(buttonUp);
        addEmptyField();

        add(buttonLeft);
        addEmptyField();
        add(buttonRight);

        addEmptyField();
        add(buttonDown);
        addEmptyField();
    }

    private JButton newButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void addEmptyField() {
        add(new JLabel(" "));
    }
}
