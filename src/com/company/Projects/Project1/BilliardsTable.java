package com.company.Projects.Project1;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import java.util.Vector;

class BilliardsTable extends RoundRectangle2D.Double {
    Ellipse2D centerLeft, centerRight, hole;
    private double length, diameter;
    private Vector<BilliardsBall> balls;
    private double[] holePosition;
    private int bouncedOffTheTable = 0;

    BilliardsTable(double x, double y, double length, double diameter) {
        super(x, y, length + diameter, diameter, diameter, diameter);
        this.centerLeft = new Ellipse2D.Double(this.getCenterX() - length / 2, this.getCenterY(), 1, 1);
        this.centerRight = new Ellipse2D.Double(this.getCenterX() + length / 2, this.getCenterY(), 1, 1);
        this.balls = new Vector<>();
        this.length = length;
        this.diameter = diameter;
        this.holePosition = place(false);
    }

    void add(BilliardsBall ball, boolean nextToEachOther) {
        double[] xy = place(nextToEachOther);
        ball.setFrame(xy[0], xy[1], ball.getWidth(), ball.getHeight());
        if (nextToEachOther) {
            if (!balls.isEmpty()) {
                ball.setMomentum(balls.lastElement().momentumX, balls.lastElement().momentumY);
            }
        }
        balls.add(ball);
    }

    void remove(BilliardsBall ball) {
        balls.removeElement(ball);
    }

    private double[] place(boolean nextToEachOther) {
        double res[] = new double[2];
        double x = java.lang.Double.MAX_VALUE;
        double y = x;
        if (nextToEachOther) {
            if (balls.isEmpty()) {
                while (!getBounds().contains(x, y)) {
                    x = getWidth() * new Random().nextDouble();
                    y = getHeight() * new Random().nextDouble();
                }
                res[0] = x;
                res[1] = y;
            } else {
                res[0] = balls.lastElement().getX() + Math.pow(10, -5);
                res[1] = balls.lastElement().getY();
            }
        } else {
            if (length / diameter > 0.3) {
                while (!(y > getY())
                        || !(y < getY() + getHeight())
                        || !(x > centerLeft.getCenterX())
                        || !(x < centerRight.getCenterX())) {
                    x = getWidth() * new Random().nextDouble();
                    y = getHeight() * new Random().nextDouble();
                }
            } else {
                while (distance(centerLeft, x, y) > diameter / 3 || distance(centerRight, x, y) > diameter / 3) {
                    x = getWidth() * new Random().nextDouble();
                    y = getHeight() * new Random().nextDouble();
                }
            }
            res[0] = x;
            res[1] = y;
        }
        return res;
    }

    public void drawHole(Graphics2D graphics2D) {
        hole = new Ellipse2D.Double(holePosition[0], holePosition[1],
                DrawTable.getUnitLength() * 0.02, DrawTable.getUnitLength() * 0.02);
        graphics2D.setColor(Color.RED);
        graphics2D.fill(hole);
        graphics2D.setColor(Color.BLACK);
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

    public void incrementBounceCount() {
        bouncedOffTheTable++;
    }

    public int getBouncedOffTheTable() {
        return bouncedOffTheTable;
    }
}
