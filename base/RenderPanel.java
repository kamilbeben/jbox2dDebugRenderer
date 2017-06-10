package debug_renderer.base;


import debug_renderer.menu.Jbox2DDebugConfig;
import debug_renderer.menu.MutableSettings;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class RenderPanel extends JPanel {

    private World world;
    private Jbox2DDebugConfig config;
    private MutableSettings mutableSettings;

    public RenderPanel(World world, Jbox2DDebugConfig config, MutableSettings mutableSettings) {
        super();
        this.mutableSettings = mutableSettings;
        this.config = config;

        this.world = world;
        setBackground(config.getColorBackground());
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (mutableSettings.flipY) {
            g2d.scale(1.0, -1.0);
            g2d.translate(0, -getHeight());
        }

        for (Body body = world.getBodyList(); body != null; body = body.getNext()) {
            drawBody(g2d, body);
        }

        for (Joint joint = world.getJointList(); joint != null; joint = joint.getNext()) {
            drawJoint(g2d, joint);
        }
    }

    private Color color;
    private Color colorAlpha;

    private void drawBody(Graphics2D g2d, Body body) {
        for (Fixture fixture = body.getFixtureList(); fixture != null; fixture = fixture.getNext()) {
            Shape[] shapes;
            Vec2 position = new Vec2(body.getPosition());
            position.addLocal(mutableSettings.getOffset());
            float angle = body.getAngle();

            switch (fixture.getType()) {
                case CIRCLE:
                    shapes = getEllipse(fixture, position, angle);
                    color = config.getColorCircle();
                    colorAlpha = config.getColorCircleAlpha();
                    break;
                case POLYGON:
                    shapes = getPolygon(fixture, position, angle);
                    color = config.getColorPolygon();
                    colorAlpha = config.getColorPolygonAlpha();
                    break;
                case EDGE:
                    shapes = getEdge(fixture, position, angle);
                    color = config.getColorEdge();
                    colorAlpha = config.getColorEdgeAlpha();
                    break;
                default:
                    color = null;
                    shapes = null;
                    break;
            }

            if (shapes != null) {
                for (Shape shape : shapes) {
                    draw(g2d, color, colorAlpha, shape);
                }

            }

        }

    }

    private Shape[] getEdge(Fixture fixture, Vec2 position, float angle) {
        int scale = mutableSettings.getScale();
        EdgeShape edgeShape = ((EdgeShape) fixture.getShape());

        Vec2 a = new Vec2(edgeShape.m_vertex1);
        Vec2 b = new Vec2(edgeShape.m_vertex2);
        a.addLocal(position);
        b.addLocal(position);

        Shape lineA = new Line2D.Float(a.x * scale + getSize().width / 2,
                a.y * scale + getSize().height / 2,
                b.x * scale + getSize().width / 2,
                b.y * scale + getSize().height / 2);

        if (edgeShape.m_hasVertex0 && edgeShape.m_hasVertex3) {
            a = new Vec2(edgeShape.m_vertex1);
            b = new Vec2(edgeShape.m_vertex2);
            a.addLocal(position);
            b.addLocal(position);

            Shape lineB = new Line2D.Float(a.x * scale + getSize().width / 2,
                    a.y * scale + getSize().height / 2,
                    b.x * scale + getSize().width / 2,
                    b.y * scale + getSize().height / 2);
            return new Shape[]{
                    lineA,
                    lineB
            };
        } else {
            return new Shape[]{
                    lineA
            };
        }
    }

    private Shape[] getEllipse(Fixture fixture, Vec2 position, float angle) {
        int scale = mutableSettings.getScale();
        CircleShape shape = (CircleShape) fixture.getShape();
        float radius = shape.m_radius;

        Vec2 relativePosition = rotateVec2(new Vec2(shape.m_p), angle);
        position.addLocal(relativePosition);

        Vec2 lineVector = getVector(radius, angle);
        lineVector.addLocal(position);

        return new Shape[]{
                new Ellipse2D.Float((position.x - radius) * scale + getSize().width / 2,
                        (position.y - radius) * scale + getSize().height / 2,
                        radius * 2 * scale, radius * 2 * scale),
                new Line2D.Float(position.x * scale + getSize().width / 2,
                        position.y * scale + getSize().height / 2,
                        lineVector.x * scale + getSize().width / 2,
                        lineVector.y * scale + getSize().height / 2)
        };
    }

    private Vec2 getVector(float length, float angleRadians) {
        return new Vec2(-length * (float) Math.sin(angleRadians), length * (float) Math.cos(angleRadians));
    }

    private Shape[] getPolygon(Fixture fixture, Vec2 position, float angle) {
        PolygonShape jboxShape = ((PolygonShape) fixture.getShape());
        Vec2[] jboxVertices = jboxShape.getVertices();
        Vec2[] vertices = new Vec2[jboxShape.getVertexCount()];

        for (int i = 0; i < vertices.length; i++)
            vertices[i] = jboxVertices[i];

        vertices = rotatePolygon(vertices, angle);

        for (Vec2 vertex : vertices)
            vertex.addLocal(position);

        int[] x = new int[vertices.length];
        int[] y = new int[vertices.length];

        int scale = mutableSettings.getScale();

        for (int i = 0; i < vertices.length; i++) {
            x[i] = ((int) (vertices[i].x * scale) + this.getSize().width / 2);
            y[i] = ((int) (vertices[i].y * scale + this.getSize().height / 2));
        }

        Polygon polygon = new Polygon(x, y, x.length);
        return new Shape[]{
                polygon
        };
    }

    private Vec2[] rotatePolygon(Vec2[] vertices, float angle) {
        Vec2[] x = new Vec2[vertices.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = rotateVec2(vertices[i], angle);
        }
        return x;
    }

    private Vec2 rotateVec2(Vec2 v, float radians) {
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);
        float newX = v.x * cos - v.y * sin;
        float newY = v.x * sin + v.y * cos;
        return new Vec2(newX, newY);
    }

    private void drawJoint(Graphics2D g2d, Joint joint) {
        Vec2 A = joint.getBodyA().getPosition().add(mutableSettings.getOffset());
        Vec2 B = joint.getBodyB().getPosition().add(mutableSettings.getOffset());

        int scale = mutableSettings.getScale();

        Line2D.Float line = new Line2D.Float(A.x * scale + getSize().width / 2,
                A.y * scale + getSize().height / 2,
                B.x * scale + getSize().width / 2,
                B.y * scale + getSize().height / 2);

        draw(g2d, Color.RED, Color.RED, line);
    }

    private void draw(Graphics2D g2d, Color color, Color colorAlpha, Shape shape) {
        g2d.setColor(color);
        g2d.draw(shape);
        g2d.setColor(colorAlpha);
        g2d.fill(shape);
    }
}
