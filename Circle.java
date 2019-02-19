import java.awt.*;
import java.awt.geom.*;

public class Circle extends Canvas implements Shape{

    private Ellipse2D circle;

    private Color borderColor;
    private Color backgroundColor;

    public Circle(int centerX, int centerY, int radius, Color border, Color background) {
        this.circle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        this.borderColor = border;
        this.backgroundColor = background;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform affineTransform) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform affineTransform, double v) {
        return null;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(backgroundColor);
        graphics2D.fillOval((int)circle.getX(), (int) circle.getY(),
                (int) circle.getWidth(), (int) circle.getHeight());

        graphics2D.setColor(borderColor);
        graphics2D.draw(circle);
    }

}

