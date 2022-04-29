package com.example.devoir2;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Poissons {

    // vers gauche = 1, vers droite = 0
    private int direction;
    private double gravite;
    private double vitesseVerticale;
    private double vitesseHorizontale;
    private double hauteurInitiale;
    private int frequence;
    private Image image;
    private Color couleur;
    private double x;
    private double y;
    private double largeur;
    private double longueur;
    private boolean touche;
    private boolean sortie;

    public Poissons (){
        setLargeur(100);
        setLongueur(100);
        setTouche(false);
        setSortie(false);
    }

    public double getY() {return y;}
    public void setY(double y) {this.y = y;}

    public double getX() {return x;}
    public void setX(double x) {this.x = x;}

    public double getLargeur() {return largeur;}
    public void setLargeur(double largeur) {this.largeur = largeur;}

    public double getLongueur() {return longueur;}
    public void setLongueur(double longueur) {this.longueur = longueur;}

    public int getDirection(){return direction;}
    public void setDirection(){
        // number betweeen one and zero
        direction = (int) Math.round(Math.random());

        if (direction == 1){
            inverser(getImage());
            // mouvement commence hors de l'ecran

            setX(640);
        }
        else{
            setX(0);
        }
    }

    public double getGravite() {return gravite;}
    public void setGravite(double gravite) {this.gravite = gravite;}

    public double getVitesseHorizontale() {return vitesseHorizontale;}
    public void setVitesseHorizontale(double vitesseHorizontale) {this.vitesseHorizontale = vitesseHorizontale;}

    public double getVitesseVerticale() {return vitesseVerticale;}
    public void setVitesseVerticale(double vitesseVerticale) {this.vitesseVerticale = vitesseVerticale;}

    public double getHauteurInitiale() {return hauteurInitiale;}
    public void setHauteurInitiale(double hauteurInitiale) {this.hauteurInitiale = hauteurInitiale;}

    public void colorie(Image img) {
            setImage(ImageHelpers.colorize(img, getCouleur()));
        ;}

    public int getFrequence() {return frequence;}
    public void setFrequence(int frequence) {this.frequence = frequence;}

    public Image getImage() {return image;}
    public void setImage(Image image) {this.image = image;}

    public void inverser(Image img){
        setImage(ImageHelpers.flop(img));
    }

    public Color getCouleur() {return couleur;}
    public void setColor(Color couleur){this.couleur = couleur;}

    public boolean estTouche() {return touche;}
    public void setTouche(boolean touche) {this.touche = touche;}

    public void setSortie(boolean sortie) {this.sortie = sortie;}
    public boolean estSortie(){
        return (getX() > 640 && getDirection() == 0) || ((getX() + 100 < 0) && getDirection()==1);
    }

    public Color newColor() {
        int max = 255;
        int min = 0;
        int r =  (int) (Math.random()*(max-min+1)+min);
        int g = (int) (Math.random()*(max-min+1)+min);
        int b = (int) (Math.random()*(max-min+1)+min);

        Color randomColor = Color.rgb(r,g,b);
        return randomColor;
    }
    public void update(double delta){
        setVitesseVerticale(getVitesseVerticale() + (delta*getGravite()) );
        if (getDirection() !=1){
            setX(getX() + (delta * getVitesseHorizontale()));
        }
        else{
            setX(getX() - (delta * getVitesseHorizontale()));
        }

        setY(getY() + (delta * getVitesseVerticale()));

    }

    public boolean collision(double x, double y){
        // x et y endroits du clic
        if ( getX() <= x && x <= getX() + getLargeur() && getY() <= y && y <= getY() + getLongueur()  ){
            return true;
        }
        else{
            return false;
        }
    }


}

