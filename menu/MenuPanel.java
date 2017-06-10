package debug_renderer.menu;

import debug_renderer.base.DebugRendererJFrame;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private static final JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    public static final int WIDTH = 160;

    public MenuPanel(Jbox2DDebugConfig config, MutableSettings mutableSettings) {
        Dimension dimension = new Dimension(WIDTH, DebugRendererJFrame.INIT_HEIGHT);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);


        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

        setLayout(layout);
        add(new Zoom(config, mutableSettings));
        add(separator);
        add(new Arrows(config, mutableSettings));
        JButton buttonFlipY = new JButton("Flip Y");
        buttonFlipY.addActionListener(e -> mutableSettings.flipY = !mutableSettings.flipY);
        add(buttonFlipY);
    }


}
