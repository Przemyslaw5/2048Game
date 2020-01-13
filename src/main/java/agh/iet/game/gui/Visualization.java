package agh.iet.game.gui;

import agh.iet.game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Visualization extends Application {

    public static double BLOCK_SIZE = 106.25;
    public static double GAP_SIZE = 15;
    private static double STATISTICS_SIZE = 190;
    public boolean isUserPlay = false;
    public boolean doNotStopIfWin = false;
    public boolean randomTurns;
    private int counterToTurn = 4;
    private int best = 0;
    private Random random = new Random();


    private Pane root = new Pane();
    public AnimationTimer timer;
    public Game game;

    public Stage stage = new Stage();
    public Scene scene;
    private VBox vBox = new VBox();
    private ShowingWindows showingWindows;

    @Override
    public void start(Stage primaryStage) throws Exception{

        game = new Game(2, 2048);

        Statistics statistics = new Statistics();

        Pane statisticsPane = statistics.statisticsPane(BLOCK_SIZE, GAP_SIZE, STATISTICS_SIZE, game);

        vBox.getChildren().addAll(statisticsPane, root);

        scene = new Scene(vBox, BLOCK_SIZE * 4 + GAP_SIZE * 5, BLOCK_SIZE * 4 + GAP_SIZE * 5 + STATISTICS_SIZE);
        stage.setTitle("2048");

        showingWindows = new ShowingWindows(this);

        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(!game.canMove()){
                    showingWindows.loseMenu();
                }

                switch (event.getCode()) {
                    case UP:     game.up(); break;
                    case DOWN:   game.down(); break;
                    case LEFT:   game.left(); break;
                    case RIGHT:  game.right(); break;
                    case ESCAPE: showingWindows.showMenu(); break;
                }
                if(randomTurns){
                    if(counterToTurn == 0){
                        game.randomTurn();
                        counterToTurn = random.nextInt(10) + 1;
                    }
                    counterToTurn--;
                }
            }
        });

        showingWindows.showMenu();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.getChildren().clear();
                DrawMap.draw(root, game, BLOCK_SIZE, GAP_SIZE);
                best = Math.max(best, game.score);
                statistics.update(game, best);
                if(game.isWin && !doNotStopIfWin) // Osoba wygrala i albo gra dalej albo wraca do menu
                    showingWindows.continueGameMenu();
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
