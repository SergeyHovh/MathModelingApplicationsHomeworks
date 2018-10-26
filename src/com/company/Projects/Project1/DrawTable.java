package com.company.Projects.Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

class DrawTable extends JPanel implements ActionListener {
    private Timer timer = new Timer(5, this);
    private RoundRectangle2D table;
    private Ellipse2D ball, centerLeft, centerRight;
    private int diameter, length;
    private double theta = new Random().nextDouble();
    private double tableX = 50, tableY = 50, ballX, ballY;
    //    private double momentumX = 0.7, momentumY = 0.7;
    private double momentumX = Math.sin(theta), momentumY = Math.cos(theta);

    DrawTable(int diameter, int length, double ballX, double ballY) {
        this.diameter = diameter;
        this.length = length;
        this.ballX = ballX;
        this.ballY = ballY;
    }

    DrawTable(int diameter, int length) {
        this.diameter = diameter;
        this.length = length;
        this.ballX = 4 * tableX;
        this.ballY = 2 * tableY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // table
        RoundRectangle2D billiardsTable = new RoundRectangle2D.Double(tableX, tableY, length + diameter, diameter, diameter, diameter);
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fill(billiardsTable);
        this.table = billiardsTable;
        // ball
        Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, 10, 10);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(ball);
        this.ball = ball;
        // center
        double centerD = 1;
        Ellipse2D centerRight = new Ellipse2D.Double(table.getCenterX() + length / 2, table.getCenterY(), centerD, centerD);
        Ellipse2D centerLeft = new Ellipse2D.Double(table.getCenterX() - length / 2, table.getCenterY(), centerD, centerD);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(centerLeft);
        graphics2D.fill(centerRight);
        this.centerLeft = centerLeft;
        this.centerRight = centerRight;
        // timer
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballY += momentumY;
        ballX += momentumX;
        Ellipse2D center;
        if (ball.getCenterX() > table.getCenterX()) {
            center = centerRight;
        } else {
            center = centerLeft;
        }

        if (!table.contains(ball.getBounds())) {
            if (ball.getX() > centerLeft.getX() && ball.getX() < centerRight.getX()) {
                ballY -= 2 * momentumY;
                momentumY *= -1;
            } else {
                double Px = momentumX, Py = momentumY;
                double deltaY = ball.getCenterY() - center.getCenterY(), deltaX = ball.getCenterX() - center.getCenterX();
                double quadDelta = Math.pow(deltaY, 2) - Math.pow(deltaX, 2);
                double PxPrime = (quadDelta * Px - 2 * deltaX * deltaY * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                double PyPrime = -(2 * deltaX * deltaY * Px + quadDelta * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                ballX -= 2 * momentumX;
                ballY -= 2 * momentumY;
                momentumX = PxPrime;
                momentumY = PyPrime;
            }
        }
        repaint();
    }

    double distance(RectangularShape shape1, RectangularShape shape2) {
        return Math.sqrt(
                Math.pow(shape1.getCenterX() - shape2.getCenterX(), 2)
                        + Math.pow(shape1.getCenterY() - shape2.getCenterY(), 2)
        );
    }

    double distance(RectangularShape shape, double x, double y) {
        return Math.sqrt(
                Math.pow(shape.getCenterX() - x, 2)
                        + Math.pow(shape.getCenterY() - y, 2)
        );
    }
}
