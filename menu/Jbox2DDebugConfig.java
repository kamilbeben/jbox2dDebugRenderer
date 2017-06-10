package debug_renderer.menu;

import java.awt.*;

public class Jbox2DDebugConfig {

    private String title = "JBox2D debug renderer";
    private Color colorBackground = Color.DARK_GRAY;
    private Color colorPolygon = Color.RED;
    private Color colorCircle = Color.BLUE;
    private Color colorEdge = Color.GREEN;
    private float alpha = 0.25f;

    private int pixelsPerUnit = 1;
    private Color colorPolygonAlpha;
    private Color colorCircleAlpha;
    private Color colorEdgeAlpha;

    public Jbox2DDebugConfig() {
        updateAlphaColors();
    }

    public void setPixelsPerUnit(int PPU) {
        this.pixelsPerUnit = PPU > 0 ? PPU : 1;
    }

    public int getPixelsPerUnit() {
        return pixelsPerUnit;
    }

    public String getTitle() {
        return title;
    }

    public Color getColorBackground() {
        return colorBackground;
    }

    public Color getColorPolygon() {
        return colorPolygon;
    }

    public Color getColorCircle() {
        return colorCircle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setColorBackground(Color colorBackground) {
        this.colorBackground = colorBackground;
    }

    public void setColorPolygon(Color colorPolygon) {
        this.colorPolygon = colorPolygon;
        updateAlphaColors();
    }

    public void setColorCircle(Color colorCircle) {
        this.colorCircle = colorCircle;
        updateAlphaColors();
    }

    public void setColorEdge(Color colorEdge) {
        this.colorEdge = colorEdge;
        updateAlphaColors();
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha >= 0 ? (alpha <= 1 ? alpha : 1) : 0f;
        updateAlphaColors();
    }

    public Color getColorPolygonAlpha() {
        return colorPolygonAlpha;
    }

    public Color getColorEdge() {
        return colorEdge;
    }

    public Color getColorEdgeAlpha() {
        return colorEdgeAlpha;
    }

    public Color getColorCircleAlpha() {
        return colorCircleAlpha;
    }

    private void updateAlphaColors() {
        colorPolygonAlpha = getAlphaColor(colorPolygon);
        colorCircleAlpha = getAlphaColor(colorCircle);
        colorEdgeAlpha = getAlphaColor(colorEdge);
    }

    private Color getAlphaColor(Color base) {
        return new Color(
                base.getRed() / 255,
                base.getGreen() / 255,
                base.getBlue() / 255,
                alpha
        );
    }
}
