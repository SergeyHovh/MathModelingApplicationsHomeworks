package com.company.Projects.Project1;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Vector;

class BilliardsBall extends Ellipse2D.Double {
    private Vector<Rectangle2D.Double> pathBefore = new Vector<>(), pathAfter = new Vector<>();
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

    void bounce(BilliardsTable table) {
        move();
        Ellipse2D center;
        if (getCenterX() > table.getCenterX()) {
            center = table.centerRight;
        } else {
            center = table.centerLeft;
        }
        if (!table.contains(getBounds())) {
            bounceCount++;
            table.incrementBounceCount();
            if (getCenterX() > table.centerLeft.getCenterX()
                    && getCenterX() < table.centerRight.getCenterX()) {
                move(0, -momentumY);
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

    void reverse(Graphics2D graphics2D, int numberOfCriticalHits) {
        trackUntil(numberOfCriticalHits);
        if (bounceCount > numberOfCriticalHits)
            pathAfter.add(new Rectangle2D.Double(getCenterX(), getCenterY(), 4, 4));

        if (bounceCount == numberOfCriticalHits) setMomentum(-momentumX, -momentumY);
        if (bounceCount == 2 * numberOfCriticalHits + 1) stop();

        draw(graphics2D, pathBefore, Color.BLACK);
        draw(graphics2D, pathAfter, Color.ORANGE);
    }

    void trackUntil(int limit) {
        if (bounceCount < limit) {
            pathBefore.add(new Rectangle2D.Double(getCenterX(), getCenterY(), 3, 3));
        }
    }

    void draw(Graphics2D graphics2D, Vector<Rectangle2D.Double> data, Color color) {
        graphics2D.setColor(color);
        for (Rectangle2D.Double datum : data) {
            graphics2D.fill(datum);
        }
        graphics2D.setColor(Color.BLACK);
    }
}
