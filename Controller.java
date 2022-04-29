package com.example.devoir2;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

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
    private ScheduledExecutorService executor;
    private Scores scoreHandler;


    public Controller(FishHunt vue) {
        this.vue = vue;
        setVies(3);
        setIcon(new Image("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\logo.png"));
        setScores(new ArrayList<>());
        setNiv(1);
        setScoreHandler(new Scores());

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

    public ArrayList<Poissons> getPoissons() {
        return poissons;
    }

    public void setPoissons(ArrayList<Poissons> poissons) {
        this.poissons = poissons;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getVies() {
        return vies;
    }

    public void setVies(int vies) {
        this.vies = vies;
    }


    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public ArrayList<String> getScores() {
        return scores;
    }

    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }
    //public void addScore(ArrayList<String> scores, int score){
    //  if (scores.size() <10){
    // add to the right place
    //  scores.add(Integer.toString(score));
    //scores.s
    // }}


    public Scores getScoreHandler() {
        return scoreHandler;
    }

    public void setScoreHandler(Scores scoreHandler) {
        this.scoreHandler = scoreHandler;
    }

    public int getNiv() {
        return niv;
    }

    public void setNiv(int niv) {
        this.niv = niv;
    }

    public void afficherNiveau(GraphicsContext context) {
        vue.afficherNiveau(context, getNiv());
    }

    public void afficherMenu(GraphicsContext context) {
        vue.afficherMenu(getIcon(), context);
    }

    public void afficherMeilleursScores() {
        vue.afficherMeilleursScores(getScores());
    }

    public void afficherMenuBoutons(GraphicsContext context) {
        vue.afficherMenuBoutons(context);
    }

    public void afficherCible() {
        vue.afficherCible();
    }

    public void afficherJeu(Canvas board, GraphicsContext context) {
        vue.afficherJeu(board, context);
    }

    public void afficherScore(GraphicsContext context) {
        vue.afficherScore(context, getScore());
    }

    public void afficherVies(GraphicsContext context, int vies) {
        vue.afficherVies(context, getVies());
    }

    public void lancerBalle(Canvas board, GraphicsContext context) {
        vue.lancerBalle(board, context);
    }

    public void afficherPoissonsNormaux(GraphicsContext context) {
        vue.afficherPoissonsNormaux(context, getNiv());
    }

    public void collisions(GraphicsContext context, AnimationTimer timerBalle) {
        Balle balle = getBalle();
        Poissons poisson = getPoisson();
        if (poisson != null) {

            if (poisson.collision(balle.getX(), balle.getY())) {
                setScore(getScore() + 1);
                poisson.setTouche(true);
                poisson.setSortie(true);

                if (poisson.getDirection() == 0) {
                    poisson.setX(641);
                } else {
                    poisson.setX(-100);
                }
                timerBalle.stop();
            }
            if (getScore() % 5 == 0) {
                if (getScore() / 5 == getNiv()) {
                    setNiv(getNiv() + 1);
                    afficherNiveau(context);
                }
            }
        }
    }

    public void sortieEcran(AnimationTimer timerPoisson, Pane jeu, ImageView poissonUn, ImageView poissonDeux, ImageView poissonTrois) {
        // dimension plus grande pour permettre a l'animation d'etre plus joli

        // poisson est sortie et on ne l'a pas touche
        if (!poisson.estTouche() && poisson.estSortie()) {
            setVies(vies - 1);

            if (getVies() == 2) {
                jeu.getChildren().remove(poissonTrois);
            } else if (getVies() == 1) {
                jeu.getChildren().remove(poissonDeux);
            } else if (getVies() == 0) {
                jeu.getChildren().remove(poissonUn);
                afficherGameOver();
            }
            timerPoisson.stop();
        }

        // poisson est sortie et on l'a touche
        if (poisson.estTouche() && poisson.estSortie()) {
            timerPoisson.stop();
        }
    }

    public void afficherGameOver() {
        vue.afficherGameOver();
    }

    public void inputScore() {
        vue.inputScore();
    }

    public void addScore(String nom) {
        getScoreHandler().addScore(getScore(),nom);
        setScores(getScoreHandler().getDisplayString());
    }

    public void afficherBoutonsMeilleursScores() {
        vue.afficherBoutonsMeilleursScores();
    }

    public void creerBoutonsMeilleursScores() {
        vue.creerBoutonsMeilleursScores(scores);
    }

    public void updateMeilleursScores(){
        vue.updateMeilleurScores(scores);
    }
}
