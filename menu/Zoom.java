package debug_renderer.menu;

import javax.swing.*;
import java.awt.*;

public class Zoom extends JPanel {

    private static final Dimension dimension = new Dimension(160, 40);

    private JButton buttonZoomIn;
    private JButton buttonZoomOut;

    private Jbox2DDebugConfig config;
    private MutableSettings mutableSettings;

    public Zoom(Jbox2DDebugConfig config, MutableSettings mutableSettings) {
        this.config = config;
        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);

        buttonZoomIn = new JButton("+");
        buttonZoomIn.addActionListener(e -> mutableSettings.zoomIn());

        buttonZoomOut = new JButton("-");
        buttonZoomOut.addActionListener(e -> mutableSettings.zoomOut());

        add(buttonZoomIn);
        add(buttonZoomOut);
    }
}
