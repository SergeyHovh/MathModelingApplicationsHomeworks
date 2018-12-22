package com.company.Projects.Project2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class Draw extends JPanel implements ActionListener {
    private Vector<Particle> particles = new Vector<>();
    private double f, g;

    Draw(int n, double f, double g) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            double x = 100 + rand.nextDouble() * 350, y = 100 + rand.nextDouble() * 250;
            particles.add(new Particle(10, x, y));
        }
        Timer timer = new Timer(5, this);
        timer.start();
        this.f = f;
        this.g = g;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        for (Particle particle : particles) {
            graphics2D.fill(particle);
            particle.setFrame(particle.x, particle.y, particle.getWidth(), particle.getHeight());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        for (Particle particle : particles) {
            for (Particle particle1 : particles) {
                if (!particle.equals(particle1)) {
                    particle.interact(particle1, particles.size(), f, g);
                }
            }
        }
    }
}