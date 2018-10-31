package com.company.Projects.Project1;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import java.util.Vector;

class BilliardsTable extends RoundRectangle2D.Double {
    Ellipse2D centerLeft, centerRight;
    private Vector<BilliardsBall> balls;

    BilliardsTable(double x, double y, double length, double diameter) {
        super(x, y, length + diameter, diameter, diameter, diameter);
        this.centerLeft = new Ellipse2D.Double(this.getCenterX() - length / 2, this.getCenterY(), 1, 1);
        this.centerRight = new Ellipse2D.Double(this.getCenterX() + length / 2, this.getCenterY(), 1, 1);
        this.balls = new Vector<>();
    }

    void add(BilliardsBall ball) {
        double x = getX(), y = getY();
        while (!(y > getY())
                || !(y < getY() + getHeight())
                || !(x > centerLeft.getCenterX())
                || !(x < centerRight.getCenterX())) {
            y = getHeight() * new Random().nextDouble();
            x = getWidth() * new Random().nextDouble();
        }
        ball.setFrame(x, y, ball.getHeight(), ball.getWidth());
        this.balls.add(ball);
    }

    Vector<BilliardsBall> getBalls() {
        return balls;
    }
}
