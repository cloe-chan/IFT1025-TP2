package com.example.devoir2;

public class Balle {
    private double x, y;
    private double w, h;
    public Balle(double w, double h)  {
        setX(x);
        setY(y);

    }
    public Balle(double x, double y, double w, double h)  {
        setW(w);
        setH(h);
        setX(x);
        setY(y);

    }
    public void setW(double w) {this.w = w;}
    public void setH(double h) { this.h = h;}
    public void setX(double x) { this.x = x;}
    public void setY(double y) { this.y = y;}

    public double getW() {return this.w;}
    public double getH() {return this.h;}
    public double getX() {return this.x;}
    public double getY() {return this.y;}

    public void update(double dt) {
        setW(getW() - dt * 300 * 1e-9);
        setH(getH() - dt * 300 * 1e-9);
    }

}