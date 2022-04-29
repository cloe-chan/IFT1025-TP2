package com.example.devoir2;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {
    //private Scene scene;
    //private Stage stage;
    private int score;
    private int vies;
    private FishHunt vue;
    private Image icon;
    private ArrayList<String> scores;
    private int niv;
    private ArrayList<Poissons> poissons;
    private Poissons poisson;
    private Balle balle;

    public Controller(FishHunt vue){
        this.vue = vue;
        setScore(0);
        setVies(3);
        setIcon(new Image("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\logo.png"));
        setScores(new ArrayList<>());
        setNiv(1);
    }

    public Balle getBalle() {
        return balle;
    }

    public void setBalle(Balle balle) {
        this.balle = balle;
    }

    public Poissons getPoisson() {
        return poisson;
    }

    public void setPoisson(Poissons poisson) {
        this.poisson = poisson;
    }

    public ArrayList<Poissons> getPoissons() {return poissons;}
    public void setPoissons(ArrayList<Poissons> poissons) {this.poissons = poissons;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}


    public int getVies() {return vies;}
    public void setVies(int vies) {this.vies = vies;}


    public Image getIcon() {return icon;}
    public void setIcon(Image icon) {this.icon = icon;}

    public ArrayList<String> getScores() {return scores;}
    public void setScores(ArrayList<String> scores) {this.scores = scores;}

    public int getNiv() {return niv;}
    public void setNiv(int niv) {this.niv = niv;}

    public void afficherNiveau(GraphicsContext context){
        vue.afficherNiveau(context, getNiv());
    }

    public void afficherMenu(GraphicsContext context){
        vue.afficherMenu(getIcon(), context);
    }

    public void afficherMeilleursScores(){
        vue.afficherMeilleursScores(getScores());
    }

    public void afficherMenuBoutons(GraphicsContext context){
        vue.afficherMenuBoutons(context);
    }

    public void afficherCible(){
        vue.afficherCible();
    }

    public void afficherJeu(Canvas board,GraphicsContext context){
        vue.afficherJeu(board, context);
    }

    public void afficherScore(GraphicsContext context){
        vue.afficherScore(context, getScore());
    }

    public void afficherVies(GraphicsContext context, int vies){
        vue.afficherVies(context, getVies());
    }

    public void lancerBalle(Canvas board, GraphicsContext context){
        vue.lancerBalle(board, context);
    }

    public void afficherPoissonsNormaux(Canvas board, GraphicsContext context){
        vue.afficherPoissonsNormaux(board, context, getNiv());
    }

    public void collisions(GraphicsContext context){
        Balle balle = getBalle();
        Poissons poisson = getPoisson();
        if (poisson.collision(balle.getX(),balle.getY())){
            setScore(getScore()+1);
            if (poisson.getDirection()==1){
                poisson.setX(-101);
            }
            else{
                poisson.setX(801);
            }
            setPoisson(poisson);

        }
    }
}