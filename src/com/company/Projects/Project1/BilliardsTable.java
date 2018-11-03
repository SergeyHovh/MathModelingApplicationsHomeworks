package com.company.Projects.Project1;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import java.util.Vector;

class BilliardsTable extends RoundRectangle2D.Double {
    Ellipse2D centerLeft, centerRight;
    private double length, diameter;
    private Vector<BilliardsBall> balls;

    BilliardsTable(double x, double y, double length, double diameter) {
        super(x, y, length + diameter, diameter, diameter, diameter);
        this.centerLeft = new Ellipse2D.Double(this.getCenterX() - length / 2, this.getCenterY(), 1, 1);
        this.centerRight = new Ellipse2D.Double(this.getCenterX() + length / 2, this.getCenterY(), 1, 1);
        this.balls = new Vector<>();
        this.length = length;
        this.diameter = diameter;
    }

    void add(BilliardsBall ball, boolean nextToEachOther) {
        double x = java.lang.Double.MAX_VALUE;
        double y = x;
        if (nextToEachOther) {
            if (balls.isEmpty()) {
                while (!getBounds().contains(x, y)) {
                    x = getWidth() * new Random().nextDouble();
                    y = getHeight() * new Random().nextDouble();
                }
                System.out.println(x + " " + y);
                ball.setFrame(x, y, ball.getHeight(), ball.getWidth());
                this.balls.add(ball);
            } else {
                ball.setFrame(balls.lastElement().getX() + Math.pow(10, -5), balls.lastElement().getY(), ball.getHeight(), ball.getWidth());
                ball.setMomentum(balls.lastElement().momentumX, balls.lastElement().momentumY);
                this.balls.add(ball);
            }
        } else {
            if (length / diameter > 0.3) {
                while (!(y > getY())
                        || !(y < getY() + getHeight())
                        || !(x > centerLeft.getCenterX())
                        || !(x < centerRight.getCenterX())) {
                    y = getHeight() * new Random().nextDouble();
                    x = getWidth() * new Random().nextDouble();
                }
            } else {
                while (distance(centerLeft, x, y) > diameter / 3) {
                    y = getHeight() * new Random().nextDouble();
                    x = getWidth() * new Random().nextDouble();
                }
            }
            ball.setFrame(x, y, ball.getHeight(), ball.getWidth());
            this.balls.add(ball);
        }
    }

    Vector<BilliardsBall> getBalls() {
        return balls;
    }

    private double distance(RectangularShape shape, double x, double y) {
        return Math.sqrt(
                Math.pow(shape.getCenterX() - x, 2)
                        + Math.pow(shape.getCenterY() - y, 2)
        );
    }
}
