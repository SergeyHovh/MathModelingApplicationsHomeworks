package com.company.Projects.Project2;

import java.awt.geom.Ellipse2D;
import java.util.Objects;

import static java.lang.Math.*;

public class Particle extends Ellipse2D.Double {
    double x, y, r;

    public Particle(double radius, double x, double y) {
        super(x, y, radius, radius);
        this.x = x;
        this.y = y;
        this.r = radius;
    }

    private double attract(double r, double f) {
        return pow(abs(r), -f) * 100;
    }

    private double repulse(double r, double g) {
        return pow(-abs(r), -g) * 100000;
    }

    void interact(Particle particle, int numberOfParticles, double f, double g) {
        double distance = distance(particle);
        double xDist = (particle.getCenterX() - getCenterX());
        double yDist = (particle.getCenterY() - getCenterY());
        if (distance >= (numberOfParticles * 0.1 + 1) * sqrt(2) * r) {
            double attract = distance * attract(distance, f);
            double attractX = 0;
            double attractY = 0;
            if (abs(xDist) < 1.0E-4) { // on the same vertical line
                attractY = attract;
            } else if (abs(yDist) < 1.0E-4) { // on the same horizontal line
                attractX = attract;
            } else {
                attractX = xDist / distance * attract;
                attractY = yDist / distance * attract;
            }
            x += attractX;
            y += attractY;
        } else {
            double repulse = distance * repulse(distance, g);
            double repulseX = 0;
            double repulseY = 0;
            if (abs(xDist) < 1.0E-4) {
                repulseY = repulse;
            } else if (abs(yDist) < 1.0E-4) {
                repulseX = repulse;
            } else {
                repulseX = xDist / distance * repulse;
                repulseY = yDist / distance * repulse;
            }
            x += repulseX;
            y += repulseY;
        }
    }

    private double distance(Particle particle) {
        double x = pow(particle.getCenterX() - this.getCenterX(), 2);
        double y = pow(particle.getCenterY() - this.getCenterY(), 2);
        return sqrt(x + y);
    }

    @Override
    public String toString() {
        return x + " " + y + ": ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Particle particle = (Particle) o;
        return java.lang.Double.compare(particle.x, x) == 0 &&
                java.lang.Double.compare(particle.y, y) == 0 &&
                java.lang.Double.compare(particle.r, r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), x, y, r);
    }
}
