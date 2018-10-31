package com.company.Projects.Project1;

import java.awt.geom.Ellipse2D;
import java.util.Random;

class BilliardsBall extends Ellipse2D.Double {
    private double theta = new Random().nextDouble();
    private double momentumModule = 2;
    double momentumX = momentumModule * Math.sin(theta), momentumY = momentumModule * Math.cos(theta);

    BilliardsBall(double radius) {
        super(0, 0, radius, radius);
        this.setMomentum(momentumX, momentumY);
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

    void bounce(BilliardsTable table) {
        move();
        Ellipse2D center;
        if (getCenterX() > table.getCenterX()) {
            center = table.centerRight;
        } else {
            center = table.centerLeft;
        }
        if (!table.contains(getBounds())) {
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
                move(-2 * momentumX, -2 * momentumY);
                momentumX = PxPrime;
                momentumY = PyPrime;
                setMomentum(momentumX, momentumY);
            }
        }
    }
}
