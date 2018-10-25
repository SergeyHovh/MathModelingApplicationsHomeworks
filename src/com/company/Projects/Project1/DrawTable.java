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
    Timer timer = new Timer(5, this);
    RoundRectangle2D table;
    Ellipse2D ball, centerLeft, centerRight;
    private int diameter, length;
    private double theta = new Random().nextDouble();
    private double tableX = 50, tableY = 50, ballX, ballY;
    private double momentumX = Math.sin(theta), momentumY = Math.cos(theta);
//    private double momentumX = 0.7, momentumY = 0.7;

    DrawTable(int diameter, int length, double ballX, double ballY) {
        this.diameter = diameter;
        this.length = length;
        this.ballX = ballX;
        this.ballY = ballY;
    }

    DrawTable(int diameter, int length) {
        this.diameter = diameter;
        this.length = length;
        this.ballX = 2 * tableX;
        this.ballY = 2 * tableY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // table
        RoundRectangle2D billiardsTable = new RoundRectangle2D.Double(tableX, tableY, length, diameter, diameter, diameter);
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fill(billiardsTable);
        this.table = billiardsTable;
        // ball
        Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, 20, 20);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(ball);
        this.ball = ball;
        // center
        Ellipse2D centerRight = new Ellipse2D.Double(table.getCenterX() + length / 4, table.getCenterY(), 10, 10);
        Ellipse2D centerLeft = new Ellipse2D.Double(table.getCenterX() - length / 4, table.getCenterY(), 10, 10);
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
        if (!(ballY > tableY && ballY + ball.getHeight() < tableY + table.getHeight())) {
            momentumY *= -1;
        }
        /*if (!table.intersects(ball.getBounds())) {
            double Y_0 = ball.getCenterY();
            double X_0 = ball.getCenterX() - center.getCenterX();
            System.out.println(X_0 + " " + Y_0);
            momentumX = 0; momentumY  = 0;
        }*/
        if ((ball.getCenterX() > center.getCenterX() && momentumX > 0 && ball.getCenterX() > table.getCenterX())
                || (ball.getCenterX() < center.getCenterX() && momentumX < 0) && ball.getCenterX() < table.getCenterX()) {
//            System.out.println(distance(ball, center) + " " + diameter / 2);
            if (distance(ball, center) >= diameter / 2) {
                double Px = momentumX, Py = momentumY;
                double deltaY = ball.getCenterY() - center.getCenterY(), deltaX = ball.getCenterX() - center.getCenterX();
                double quadDelta = Math.pow(deltaY, 2) - Math.pow(deltaX, 2);
//                System.out.println("delta x " + deltaX + " | delta y " + deltaY);
//                System.out.println("Px prime " + (quadDelta * Px - 2 * deltaX * deltaY * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
//                System.out.println("Py prime " + (-2 * deltaX * deltaY * Px - quadDelta * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
//                System.out.println("---------" + diameter / 2 + "----------");
                double PxPrime = (quadDelta * Px - 2 * deltaX * deltaY * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                double PyPrime = -(2 * deltaX * deltaY * Px + quadDelta * Py) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                System.out.println(Math.pow(PxPrime, 2) + Math.pow(PyPrime, 2));
                momentumX = PxPrime;
                momentumY = PyPrime;
//                momentumX = Px * (Math.pow(ball.getCenterY() - center.getCenterY(), 2)
//                        - Math.pow(ball.getCenterX() - center.getCenterX(), 2))
//                        - 2 * Py * (ball.getCenterY() - center.getCenterY()) * (ball.getCenterX() - center.getCenterX());
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
}
