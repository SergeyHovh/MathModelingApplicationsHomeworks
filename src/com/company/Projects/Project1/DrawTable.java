package com.company.Projects.Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawTable extends JPanel implements ActionListener {
    private BilliardsTable table;
    private Timer timer;

    DrawTable(int length, int diameter) {
        double tableX = 50;
        double tableY = 50;
        table = new BilliardsTable(tableX, tableY, length, diameter);
        timer = new Timer(5, this);
        fill(100, 10, table);
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
            tableBall.bounce(table);
            graphics2D.fill(tableBall);
        }
        timer.start();
    }

    private void fill(int N, double size, BilliardsTable billiardsTable) {
        for (int i = 0; i < N; i++) {
            billiardsTable.add(new BilliardsBall(size));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
