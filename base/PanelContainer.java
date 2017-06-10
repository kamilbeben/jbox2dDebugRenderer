package debug_renderer.base;

import debug_renderer.menu.Jbox2DDebugConfig;
import debug_renderer.menu.MenuPanel;
import debug_renderer.menu.MutableSettings;
import org.jbox2d.dynamics.World;

import javax.swing.*;

public class PanelContainer extends JPanel {

    private RenderPanel renderPanel;
    private MenuPanel menuPanel;

    public PanelContainer(World world, Jbox2DDebugConfig config, MutableSettings mutableSettings) {
        super();
        menuPanel = new MenuPanel(config, mutableSettings);
        renderPanel = new RenderPanel(world, config, mutableSettings);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(renderPanel);
        add(menuPanel);
    }

    public void update() {

    }

}
