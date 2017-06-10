package debug_renderer.base;

/**
 * This renderer is adapted to Jbox2D version 2.2.1.1
 * It will probably work with other versions, but it may need a few changes
 * You can use it as you want, you don't have to ask me for permission. Just please mention the original creator.
 * If you have any questions, or bugs to report contact me at kamilbeben94@gmail.com
 */

import debug_renderer.menu.Jbox2DDebugConfig;
import debug_renderer.menu.MutableSettings;
import org.jbox2d.dynamics.World;

import javax.swing.*;

public class JBox2DDebugRenderer {

    public JBox2DDebugRenderer(World world, Jbox2DDebugConfig config) {
        start(world, config);
    }

    public JBox2DDebugRenderer(World world, int PPU) {
        Jbox2DDebugConfig config = new Jbox2DDebugConfig();
        config.setPixelsPerUnit(PPU);
        start(world, config);
    }

    private void start(World world, Jbox2DDebugConfig config) {
        MutableSettings mutableSettings = new MutableSettings(config);
        SwingUtilities.invokeLater(() -> new DebugRendererJFrame(world, config, mutableSettings));
    }
}
