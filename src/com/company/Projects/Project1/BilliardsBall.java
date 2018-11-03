package com.company.Projects.Project1;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class BilliardsBall extends Ellipse2D.Double {
    public static final String X = "x";
    public static final String Y = "y";
    public static final String V_X = "vx";
    public static final String V_Y = "vy";
    Map<Integer, Map<String, java.lang.Double>> map = new HashMap<>();
    Map<String, java.lang.Double> props = new HashMap<>();
    private int bounceCount = 0;
    private double theta = 2 * Math.PI * new Random().nextDouble();
    private double momentumModule = 10;
    double momentumX = momentumModule * Math.sin(theta), momentumY = momentumModule * Math.cos(theta);

    BilliardsBall(double radius) {
        super(0, 0, radius, radius);
        this.setMomentum(momentumX, momentumY);
    }

    void stop() {
        setMomentum(0, 0);
    }

    void setMomentum(double x, double y) {
        this.momentumX = x;
        this.momentumY = y;
    }

    private void move() {
        setFrame(getX() + momentumX, getY() + momentumY, getHeight(), getWidth());
    }

    private void move(double x, double y) {
        setFrame(getX() + x, getY() + y, getHeight(), getWidth());
    }

    public int getBounceCount() {
        return bounceCount;
    }

    void bounce(BilliardsTable table, Graphics2D graphics2D) {
        move();
        Ellipse2D center;
        if (getCenterX() > table.getCenterX()) {
            center = table.centerRight;
        } else {
            center = table.centerLeft;
        }
        if (!table.contains(getBounds())) {
            props.put(X, getX());
            props.put(Y, getY());
            props.put(V_X, momentumX);
            props.put(V_Y, momentumY);
            map.put(bounceCount, props);
            bounceCount++;
//            graphics2D.setColor(Color.RED);
//            int length = 20;
//            double radius = getWidth() / 2;
//            double dx = momentumX / momentumModule, dy = momentumY / momentumModule;
//            double boxX = getCenterX() + radius * dx - length * 0.5;
//            double boxY = getCenterY() + radius * dy - length * 0.5;
////            System.out.println("--------------------------" + bounceCount + "-----------------------");
////            System.out.println(dx + " " + dy);
////            System.out.println("boxX = " + boxX);
////            System.out.println("boxY = " + boxY);
//            Rectangle2D box = new Rectangle2D.Double(boxX, boxY, length, length);
//            graphics2D.draw(box);

            if (getCenterX() > table.centerLeft.getCenterX()
                    && getCenterX() < table.centerRight.getCenterX()) {
                move(0, -2 * momentumY);
                setMomentum(momentumX, -momentumY);
            } else {
                double Px = momentumX, Py = momentumY;
                double deltaY = getCenterY() - center.getCenterY(), deltaX = getCenterX() - center.getCenterX();
                double quadDelta = Math.pow(deltaY, 2) - Math.pow(deltaX, 2);
                double PxPrime = (quadDelta * Px - 2 * deltaX * deltaY * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                double PyPrime = -(2 * deltaX * deltaY * Px + quadDelta * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                move(PxPrime, PyPrime);
                momentumX = PxPrime;
                momentumY = PyPrime;
                setMomentum(momentumX, momentumY);
            }
        }
    }

    public java.lang.Double get(int hitNumber, String key) {
        if (map.get(hitNumber) == null) {
            return null;
        } else {
            return map.get(hitNumber).get(key);
        }
    }

    public Point2D getIntersectionPoint(Line2D lineA, Line2D lineB) {

        double x1 = lineA.getX1();
        double y1 = lineA.getY1();
        double x2 = lineA.getX2();
        double y2 = lineA.getY2();

        double x3 = lineB.getX1();
        double y3 = lineB.getY1();
        double x4 = lineB.getX2();
        double y4 = lineB.getY2();

        Point2D p = null;

        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d != 0) {
            double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
            double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

            p = new Point2D.Double(xi, yi);

        }
        return p;
    }
}
