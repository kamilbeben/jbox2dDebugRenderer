package debug_renderer.menu;

import org.jbox2d.common.Vec2;

public class MutableSettings {

    private Jbox2DDebugConfig config;
    private static final float SCALE_STEP = 0.15f;
    private float scale = 1.0f;
    private Vec2 offset = new Vec2();
    public boolean flipY = true;

    public MutableSettings(Jbox2DDebugConfig config) {
        this.config = config;
    }


    public void zoomIn() {
        scale += scale > 1 ? (scale * scale) / 3 : scale + SCALE_STEP;
    }

    public void zoomOut() {
        scale = scale > 1.15f ? ((float) Math.sqrt((double) scale))
                : (scale > SCALE_STEP ? scale - SCALE_STEP : SCALE_STEP);
    }

    public int getScale() {
        return (int) ((float) config.getPixelsPerUnit() * scale);
    }

    private static final float MOVE_STEP = 64f;

    public void moveRight() {
        offset.x -= MOVE_STEP / getScale();
    }

    public void moveLeft() {
        offset.x += MOVE_STEP / getScale();
    }

    public void moveUp() {
        offset.y += MOVE_STEP / getScale();
    }

    public void moveDown() {
        offset.y -= MOVE_STEP / getScale();
    }

    public Vec2 getOffset() {
        return offset;
    }
}
