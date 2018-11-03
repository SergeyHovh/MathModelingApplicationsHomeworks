package com.company.Projects.Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawTable extends JPanel implements ActionListener {
    int hitNumber = 200;
    private BilliardsTable table;
    private BilliardsBall ball1, ball2;
    private Timer timer;

    DrawTable(double length, double diameter, int numberOfParticles, int particleSize, boolean nextToEachOther) {
        double unitLength = 100;
        double tableX = 50;
        double tableY = 50;
        table = new BilliardsTable(tableX, tableY, length * unitLength, diameter * unitLength);
        timer = new Timer(5, this);
        fill(numberOfParticles, particleSize, table, nextToEachOther);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // create table
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fill(this.table);
        // fill with billiard balls
        for (BilliardsBall tableBall : table.getBalls()) {
            graphics2D.setColor(Color.BLACK);
            tableBall.bounce(table, graphics2D);
            graphics2D.fill(tableBall);
        }
        timer.start();
    }

    private void fill(int N, double size, BilliardsTable billiardsTable, boolean nextToEachOther) {
        for (int i = 0; i < N; i++) {
            billiardsTable.add(new BilliardsBall(size), nextToEachOther);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
//        for (BilliardsBall billiardsBall : table.getBalls()) {
//            Double V_X = billiardsBall.get(200, BilliardsBall.V_X);
//            if (V_X != null) {
//                System.out.println(V_X);
//            }
//        }

        ball1 = table.getBalls().firstElement();
        ball2 = table.getBalls().lastElement();
        int i = ball1.getBounceCount();
        Double X_1 = ball1.get(i, BilliardsBall.X);
        Double Y_1 = ball1.get(i, BilliardsBall.Y);

        Double X_2 = ball2.get(i, BilliardsBall.X);
        Double Y_2 = ball2.get(i, BilliardsBall.Y);
//        if (!(X_1 == null && X_2 == null && Y_1 == null && Y_2 == null)) {
//            System.out.println(i + ") " + distance(X_1, Y_1, X_2, Y_2));
//        }
    }

    double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(
                Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)
        );
    }
}
