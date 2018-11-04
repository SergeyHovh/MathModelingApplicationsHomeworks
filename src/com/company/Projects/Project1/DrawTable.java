package com.company.Projects.Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;
import java.util.Vector;

class DrawTable extends JPanel implements ActionListener {
    private static final String PATH = "src\\com\\company\\Projects\\Project1\\data\\data.txt";
    private static double unitLength = 200;
    private Formatter file;
    private BilliardsTable table;
    private Timer timer;
    private boolean reverse = false, hole = false;
    private int criticalHits = 0, bounceCount = 0, numberOfParticles;
    private Vector<Integer> ballsLeft = new Vector<>();
    private int N = 0;

    DrawTable(double length) {
        this(length, 1);
    }

    DrawTable(double length, double diameter) {
        double tableX = 50;
        double tableY = 50;
        table = new BilliardsTable(tableX, tableY, length * unitLength, diameter * unitLength);
        timer = new Timer(5, this);
        timer.start();
        try {
            file = new Formatter(PATH);
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getUnitLength() {
        return unitLength;
    }

    public static void setUnitLength(double unitLength) {
        DrawTable.unitLength = unitLength;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // create table
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fill(this.table);
        // fill with billiard balls
        Vector<BilliardsBall> balls = table.getBalls();
        if (balls.isEmpty()) {
            close();
        } else {
            for (int i = 0; i < table.getBalls().size(); ++i) {
                BilliardsBall tableBall = table.getBalls().elementAt(i);
                graphics2D.setColor(Color.BLACK);
                tableBall.bounce(table);
                bounceCount = table.getBouncedOffTheTable();
                // reversible
                if (reverse) {
                    tableBall.reverse(graphics2D, criticalHits);
                }
                // hole
                if (hole) {
                    table.drawHole(graphics2D);
                    if (table.hole.intersects(tableBall.getBounds2D())) {
                        table.remove(tableBall);
                    }
                    // write into data.txt
                    ballsLeft.add(table.getBalls().size());
                    if (bounceCount % numberOfParticles / 10 == 0) // taking every n-th value
                        file.format("%s", table.getBalls().size() + "\n");
                    if (tableBall.getBounceCount() > Math.sqrt(numberOfParticles * N)) close();
                }
                graphics2D.fill(tableBall);
            }
        }
    }

    private void close() {
        System.out.println("---------------------------ֆսյո--------------------------");
        System.out.println(ballsLeft.size());
        file.close();
        timer.stop();
    }

    public void setN(int n) {
        N = n;
    }

    private void fill(int N, double size, BilliardsTable billiardsTable, boolean nextToEachOther) {
        for (int i = 0; i < N; i++) {
            billiardsTable.add(new BilliardsBall(size), nextToEachOther);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(
                Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)
        );
    }

    void addParticles(int numberOfParticles, int particleSize, boolean nextToEachOther) {
        this.numberOfParticles = numberOfParticles;
        fill(numberOfParticles, particleSize, table, nextToEachOther);
    }

    public void addHole(boolean hole) {
        this.hole = hole;
    }

    public void setReverse(int criticalHits) {
        fill(1, 10, table, false);
        this.reverse = true;
        this.criticalHits = criticalHits;
    }
}
