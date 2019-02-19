import java.applet.Applet;
import java.awt.*;
import java.awt.geom.AffineTransform;


public class MyApplet extends Applet implements Runnable {

    private int centerX, centerY, radius = 75;
    private float border;

    private Color borderColor, backgroundColor;

    private Circle circle;

    private static int APPLET_WIDTH = 1000;
    private static int APPLET_HEIGHT = 600;

    private boolean moveRight = true, changeDirection = false;

    private Thread thread;

    private float zoom = 0.7f;
    private int step = 5;

    @Override
    public void init() {

        setBackground(Color.WHITE);
        setSize(APPLET_WIDTH, APPLET_HEIGHT);
        try {
            border = Float.parseFloat(this.getParameter("border"));
        } catch (Exception e) {
            border = 1;
        }

        borderColor = getColor(this.getParameter("borderColor"), new Color(0,0,0));
        backgroundColor = getColor(this.getParameter("backgroundColor"), new Color(255,255,0));

        centerX = APPLET_WIDTH / 2;
        centerY = APPLET_HEIGHT / 2;

        circle = new Circle(centerX, centerY, radius, borderColor, backgroundColor);
    }

    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
        }
        thread.start();
    }

    @Override
    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    @Override
    public void destroy() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }


    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(border));

        if (centerX + radius >= APPLET_WIDTH) {
            changeDirection = true;
            moveRight = false;
        }
        if (centerX - radius <= 0) {
            changeDirection = true;
            moveRight = true;
        }

        circle = new Circle(centerX, centerY, radius, borderColor, backgroundColor);

        if (changeDirection == true) {
            if ((int) (centerX + radius + zoom * radius) >= APPLET_WIDTH || (int) (centerX - radius - zoom * radius) <= 0) {
                AffineTransform at = new AffineTransform();
                at.translate((int) ((1-zoom) * centerX), 0);
                at.scale(zoom, 1);
                graphics2D.transform(at);
            } else
                changeDirection = false;
        }

        circle.paint(graphics2D);

        if (moveRight)
            centerX += step;
        else
            centerX -= step;

    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException ignored) {
                break;
            }
        }
    }

    private Color getColor(String strRGB, Color color) {
        if (strRGB != null && strRGB.charAt(0) == '#') {
            try {
                return new Color(Integer.parseInt(strRGB.substring(1), 16));
            } catch (NumberFormatException e) {
                return color;
            }
        }
        return color;
    }
}