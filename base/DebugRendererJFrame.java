package debug_renderer.base;

import debug_renderer.menu.Jbox2DDebugConfig;
import debug_renderer.menu.MutableSettings;
import org.jbox2d.dynamics.World;

import javax.swing.*;
import java.awt.*;

public class DebugRendererJFrame extends JFrame {


    public static final int INIT_WIDTH = 640;
    public static final int INIT_HEIGHT = 400;
    public PanelContainer container;


    public DebugRendererJFrame(World world, Jbox2DDebugConfig config, MutableSettings mutableSettings) {
        super(config.getTitle());
        container = new PanelContainer(world, config, mutableSettings);

        setMinimumSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
        getContentPane().add(container);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}