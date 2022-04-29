package com.example.devoir2;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FishHunt extends Application {

    private VBox menu = new VBox();
    private Pane jeu = new Pane();
    private VBox lesScores = new VBox();
    private HBox inputs = new HBox();
    private Scene listeScores = new Scene(lesScores,640,480);
    private Scene sceneMenu = new Scene(menu,640,480);
    private Scene sceneJeu = new Scene(jeu,640,480);
    private Stage stage;
    private Controller controleur = new Controller(this);
    private Group Pane = new Group();
    private Text monScore;
    private ImageView poissonUn;
    private ImageView poissonDeux;
    private ImageView poissonTrois;
    private ScheduledExecutorService executor;
    private GraphicsContext context;
    private Label nomLabel;
    private TextField inputNom;
    private Button boutonAjouter;
    private ListView<String> list;
    private String nomJoueur;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        Canvas visuelJeu = new Canvas(640,480);
        context = visuelJeu.getGraphicsContext2D();

        controleur.afficherMenu(context);
        controleur.afficherMeilleursScores();
        controleur.afficherJeu(visuelJeu, context);

        /** Stage **/
        stage.setResizable(false);
        stage.setTitle("Fish Hunt");
        stage.setScene(sceneMenu);
        stage.show();
    }

    public void afficherMenu(Image icon, GraphicsContext context){
        ImageView imageView = new ImageView(icon);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(300);
        menu.getChildren().add(imageView);
        menu.setBackground(new Background(
                new BackgroundFill(
                        Color.DARKBLUE,
                        CornerRadii.EMPTY,
                        Insets.EMPTY)
        ));

        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(8);
        controleur.afficherMenuBoutons(context);
    }
    public void afficherMenuBoutons(GraphicsContext context){
        Button nouvellePartie = new Button("Nouvelle partie !");
        Button meilleurScore = new Button("Meilleurs Scores");

        menu.getChildren().add(nouvellePartie);
        menu.getChildren().add(meilleurScore);

        nouvellePartie.setOnAction((clicked) -> {
            stage.setScene(sceneJeu);
            controleur.afficherNiveau(context);
            controleur.afficherPoissonsNormaux(context);

        });
        meilleurScore.setOnAction((clicked) -> {
            stage.setScene(listeScores);
        });
    }
    public void afficherMeilleursScores(ArrayList<String> scores){
        Text titre = new Text("Meilleurs scores");
        titre.setFont(Font.font("serif",25));

        list = new ListView();
        list.getItems().setAll(scores);

        Button retourMenu = new Button("Menu");


        retourMenu.setOnMouseClicked((hello) -> {
            stage.setScene(sceneMenu);
        });

        lesScores.setAlignment(Pos.CENTER);
        lesScores.setSpacing(10);
        lesScores.setPadding(new Insets(30));
        lesScores.getChildren().add(titre);
        lesScores.getChildren().add(list);
        controleur.creerBoutonsMeilleursScores();
        lesScores.getChildren().add(retourMenu);
    }

    public void afficherNiveau(GraphicsContext context, int niv){
        String niveau = Integer.toString(niv);
        Text level = new Text("Level " + niveau);
        level.setFill(Color.WHITE);
        level.setFont(Font.font("purisa",120));
        level.setX(150); level.setY(240);
        jeu.getChildren().add(level);

        Timeline time = new Timeline(
                new KeyFrame(Duration.seconds(3), ev -> {
            jeu.getChildren().remove(level);
        }));
        time.playFromStart();
    };

    public void afficherCible(){
        Image cible = new Image("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\cible.png", 50,50, true, true);
        ImageView cibleView = new ImageView(cible);

        jeu.getChildren().add(cibleView);

        //cible suit le curseur
        jeu.setOnMouseMoved((event) -> {
            double w = cibleView.getBoundsInLocal().getWidth();
            double h = cibleView.getBoundsInLocal().getHeight();
            double x = event.getX() - w / 2;
            double y = event.getY() - h / 2;

            cibleView.setX(x);
            cibleView.setY(y);
        });
    }

    public void afficherJeu(Canvas visuelJeu, GraphicsContext context){
        jeu.setBackground(new Background( new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        context.setFill(Color.WHITE);
        controleur.afficherCible();
        controleur.setScore(0);
        controleur.afficherScore(context);
        controleur.afficherVies(context, controleur.getVies());
        controleur.lancerBalle(visuelJeu,context);
    }

    public void afficherScore(GraphicsContext context, int score){
        monScore = new Text(Integer.toString(score));
        monScore.setFill(Color.WHITE);
        monScore.setFont(Font.font("purisa",30));
        monScore.setX(315);
        monScore.setY(35);
        jeu.getChildren().add(monScore);

    }

    public void afficherVies(GraphicsContext context, int vies) {
        Image poissonScoreUn = new Image("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\00.png", 35, 35, true, true);
        Image poissonScoreDeux = new Image("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\00.png", 35, 35, true, true);
        Image poissonScoreTrois = new Image("C:\\Users\\15143\\IdeaProjects\\Devoir2\\src\\main\\resources\\com\\example\\devoir2\\00.png", 35, 35, true, true);

        poissonUn = new ImageView(poissonScoreUn);
        poissonDeux = new ImageView(poissonScoreDeux);
        poissonTrois = new ImageView(poissonScoreTrois);

        poissonUn.setX(265);
        poissonUn.setY(65);
        poissonDeux.setX(305);
        poissonDeux.setY(65);
        poissonTrois.setX(345);
        poissonTrois.setY(65);

        jeu.getChildren().addAll(poissonUn,poissonDeux,poissonTrois);
    }

    public void lancerBalle(Canvas visuelJeu,GraphicsContext context){
        visuelJeu.setOnMouseClicked((event) -> {

            context.setFill(Color.BLACK);
            Balle balle = new Balle(event.getX(),event.getY(),100,100);
            controleur.setBalle(balle);

            AnimationTimer timerBalle = new AnimationTimer() {
                private long lastTime = 0;
                @Override
                public void handle(long now) {
                    if (lastTime == 0) {
                        lastTime = now;
                        return;
                    }
                    double deltaTime = now - lastTime;

                    context.clearRect(balle.getX() - balle.getW() / 2,
                            balle.getY() - balle.getH() / 2,
                            balle.getW(),
                            balle.getH());

                    balle.update(deltaTime);

                    context.fillOval(
                            balle.getX() - balle.getW() / 2,
                            balle.getY() - balle.getH() / 2,
                            balle.getW(),
                            balle.getH());
                }
            };
            timerBalle.start();
            controleur.collisions(context, timerBalle);
            jeu.getChildren().remove(monScore);
            controleur.afficherScore(context);
        });

        // here pour que la cible soit par dessus
        jeu.getChildren().add(visuelJeu);
    }
    public void afficherPoissonsNormaux(GraphicsContext context, int niv){
        Runnable afficherPoisson = new Runnable() {
            @Override
            public void run() {
                PoissonsNormaux poisson = new PoissonsNormaux();
                Image poissonImage = poisson.getImage();

                controleur.setPoisson(poisson);
                poisson.generateVitesseHorizontal(niv);

                AnimationTimer timerPoisson = new AnimationTimer() {
                    // Garde trace du temps entre chaque appel
                    private long lastTime = 0;
                    // Position, vitesse et taille de la balle
                    // Note : x, y = centre de la balle

                    @Override
                    public void handle(long now) {
                        if (lastTime == 0) {
                            lastTime = now;
                            return;
                        }
                        // Calcul du temps écoulé (en secondes)
                        double deltaTime = (now - lastTime) * 1e-9;
                        poisson.update(deltaTime);

                        context.clearRect(0, 0, 640, 480);
                        if (controleur.getPoisson() != null){
                            context.drawImage(poissonImage, poisson.getX(), poisson.getY(), 100, 100);
                        }
                        lastTime = now;
                        controleur.sortieEcran(this,jeu,poissonUn, poissonDeux, poissonTrois);
                    }
                };
                timerPoisson.start();

            }
        };
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(afficherPoisson,3,3, TimeUnit.SECONDS);
    }
    public void afficherGameOver(){

        Text gameOver = new Text("GAME OVER");
        gameOver.setFill(Color.DARKRED);
        gameOver.setFont(Font.font("purisa",110));
        gameOver.setX(15); gameOver.setY(270);
        jeu.getChildren().add(gameOver);
        executor.shutdownNow();

        Timeline time = new Timeline(
                new KeyFrame(Duration.seconds(3), ev -> {
                    jeu.getChildren().remove(gameOver);
                    controleur.inputScore();
                    controleur.afficherBoutonsMeilleursScores();

                }));
        time.playFromStart();

    }
    public void inputScore(){
        stage.setScene(listeScores);

    }
    public void afficherBoutonsMeilleursScores(){
        nomLabel.setVisible(true);
        inputNom.setVisible(true);
        boutonAjouter.setVisible(true);
    }

    public void creerBoutonsMeilleursScores(ArrayList<String> scores){
        nomLabel = new Label("Nom : ");
        nomLabel.setVisible(false);

        inputNom = new TextField();
        inputNom.setVisible(false);

        boutonAjouter = new Button("Ajouter");
        boutonAjouter.setVisible(false);

        nomJoueur = inputNom.getText();
        inputs.getChildren().addAll(nomLabel,inputNom, boutonAjouter);
        inputs.setAlignment(Pos.CENTER);

        lesScores.getChildren().add(inputs);

        boutonAjouter.setOnAction((clicked) -> {
            stage.setScene(sceneMenu);
            nomLabel.setVisible(false);
            inputNom.setVisible(false);
            boutonAjouter.setVisible(false);
            controleur.updateMeilleursScores();
        });

    }

    public void updateMeilleurScores(ArrayList scores){
        if (controleur.getScores() != null){
            controleur.addScore(nomJoueur);
            list.getItems().removeAll();
            list.getItems().setAll(scores);
            System.out.println("on est entre dans updateScore"+list);
        }

    }

}
