package com.example.devoir2;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class PoissonsNormaux extends Poissons{
    private double hauteurInitiale;
    private Color couleur;
    private int frequence;
    private double vitesseHorizontale;
    private int FREQ = 3;
    private ArrayList<String> poissonsNormaux = new ArrayList<>();

    public PoissonsNormaux(){

        setGravite(100.0);
        setVitesseVerticale(-1*generateVitesseVerticale());
        setHauteurInitiale(generateHauteurInitiale());
        setY(getHauteurInitiale());
        setFrequence(getFREQ());
        setColor(newColor());
        remplirLiens();
        choisirImage(getPoissonsNormaux());
        colorie(getImage());
        setDirection();
        // à faire dans le controleur:
        // setVitesseHorizontale(niv);
    }


    public void choisirImage(ArrayList<String> poissonsNormaux) {
        int imageChoisi = (int) Math.floor(Math.random()*8);
        Image image = new Image(poissonsNormaux.get(imageChoisi), getLargeur(),getLongueur(),true,true);
        setImage(image);
    }

    public void generateVitesseHorizontal(int niv){ setVitesseHorizontale(100 * pow(niv,1.0/3.0) + 200);}

    public double generateVitesseVerticale(){
        double max = 200.0;
        double min = 100.0;
        return  (Math.random()*(max-min+1)+min);
    }

    public double generateHauteurInitiale() {
        double max = (4.0/5.0)*480.0;
        double min = (1.0/5.0)*480.0;
        return (Math.random()*(max-min+1))+min;
    }

    public int getFREQ() {return FREQ;}

    public ArrayList<String> getPoissonsNormaux() {return poissonsNormaux;}

    public void setPoissonsNormaux(ArrayList<String> poissonsNormaux) {this.poissonsNormaux = poissonsNormaux;}


    public void remplirLiens(){
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\00.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\01.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\02.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\03.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\04.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\05.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\06.png");
        poissonsNormaux.add("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\07.png");
    }


}
