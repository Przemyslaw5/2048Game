package agh.iet.game.gui;

import agh.iet.game.Game;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ShowingWindows {

    private Visualization visualization;

    public ShowingWindows(Visualization visualization) {
        this.visualization = visualization;
    }

    public void loseMenu(){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5);
        visualization.timer.stop();

        Text text = new Text("Game over!");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text.setFill(Color.rgb(218,165,32));
        text.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(text);

        vBox.setSpacing(25);
        vBox.setAlignment(Pos.CENTER);

        Button buttonBackToMenu = new Button("Back to menu");
        buttonBackToMenu.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonBackToMenu.setLayoutX(40);
        buttonBackToMenu.setOnAction(actionEvent -> {
            showMenu();
        });

        vBox.getChildren().addAll(buttonBackToMenu);
        visualization.stage.setScene(scene2);
        visualization.stage.show();
    }

    public void continueGameMenu(){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5);
        visualization.timer.stop();

        Text text = new Text("You win!");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 100));
        text.setFill(Color.rgb(218,165,32));
        text.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(text);

        vBox.setSpacing(25);
        vBox.setAlignment(Pos.CENTER);

        Button buttonContinue = new Button("Continue");
        buttonContinue.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonContinue.setLayoutX(40);
        buttonContinue.setOnAction(actionEvent -> {
            visualization.doNotStopIfWin = true;
            visualization.stage.setScene(visualization.scene);
            visualization.stage.show();
            visualization.timer.start();
        });

        Button buttonBackToMenu = new Button("Back to menu");
        buttonBackToMenu.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonBackToMenu.setOnAction(actionEvent -> {
            showMenu();
        });

        Button buttonRestart = new Button("Restart");
        buttonRestart.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonRestart.setOnAction(actionEvent -> {
            visualization.game.restart();
            visualization.stage.setScene(visualization.scene);
            visualization.stage.show();
            visualization.timer.start();
        });

        vBox.getChildren().addAll(buttonContinue, buttonBackToMenu, buttonRestart);
        visualization.stage.setScene(scene2);
        visualization.stage.show();
    }

    public void showMenu(){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5);
        if(visualization.isUserPlay) visualization.timer.stop();


        Text text = new Text("Welcome in\n2048 game!");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setFill(Color.rgb(218,165,32));
        text.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(text);

        vBox.setSpacing(25);
        vBox.setAlignment(Pos.CENTER);

        Button buttonClassicGame = new Button("Classic game");
        buttonClassicGame.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonClassicGame.setOnAction(actionEvent -> {
            visualization.randomTurns = false;
            visualization.isUserPlay = true;
            visualization.game = new Game(2, 2048);
            visualization.stage.setScene(visualization.scene);
            visualization.stage.show();
            if(visualization.isUserPlay) visualization.timer.start();
        });

        Button buttonHarderVersion = new Button("Harder version");
        buttonHarderVersion.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonHarderVersion.setOnAction(actionEvent -> {
            visualization.randomTurns = false;
            visualization.isUserPlay = true;
            visualization.game = new Game(4, 4096);
            visualization.stage.setScene(visualization.scene);
            visualization.stage.show();
            if(visualization.isUserPlay) visualization.timer.start();
        });

        Button buttonToManyTurns = new Button("Too many terns");
        buttonToManyTurns.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonToManyTurns.setOnAction(actionEvent -> {
            visualization.randomTurns = true;
            visualization.isUserPlay = true;
            visualization.game = new Game(2, 2048);
            visualization.stage.setScene(visualization.scene);
            visualization.stage.show();
            if(visualization.isUserPlay) visualization.timer.start();
        });

        HBox hBox = new HBox();
        Button buttonQuit = new Button("Quit");
        buttonQuit.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonQuit.setOnAction(actionEvent -> {
            Platform.exit();
        });

        Button buttonHelp = new Button("Help");
        buttonHelp.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        buttonHelp.setOnAction(actionEvent -> {
            showHelp();
        });
        hBox.getChildren().addAll(buttonQuit, buttonHelp);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        vBox.getChildren().addAll(buttonClassicGame, buttonHarderVersion, buttonToManyTurns, hBox);
        visualization.stage.setScene(scene2);
        visualization.stage.show();
    }

    public void showHelp(){
        VBox vBox = new VBox();
        Scene scene2 = new Scene(vBox, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5, Visualization.BLOCK_SIZE * 4 + Visualization.GAP_SIZE * 5);
        visualization.timer.stop();

        Text text = new Text("Help");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
        text.setFill(Color.rgb(119, 110, 101));
        text.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(text);

        vBox.setAlignment(Pos.CENTER);

        VBox vBox1 = createHelpInstructions("Classic game", "Classic 2048 game. The player by pressed the arrows must combine the blocks into \nlarger and larger until he receives 2048.");

        VBox vBox2 = createHelpInstructions("Harder version", "A game in which blocks with values 2, 4 and 8 appear on the map. The goal of the \ngame is to obtain a block with a value of 4096.");

        VBox vBox3 = createHelpInstructions("Too many terns", "A game in which the board rotates every few moves, which can create a lot of \nconfusion.");

        vBox.getChildren().addAll(vBox1, vBox2, vBox3);
        vBox.setSpacing(40);

        Button buttonBackToMenu = new Button("Back to menu");
        buttonBackToMenu.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        buttonBackToMenu.setLayoutX(40);
        buttonBackToMenu.setOnAction(actionEvent -> {
            showMenu();
        });

        vBox.getChildren().addAll(buttonBackToMenu);
        visualization.stage.setScene(scene2);
        visualization.stage.show();
    }

    private VBox createHelpInstructions(String s, String s2) {
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setTranslateX(15);
        Text textTitle = new Text(s);
        textTitle.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25));
        textTitle.setTextAlignment(TextAlignment.LEFT);
        Text text = new Text(s2);
        text.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
        textTitle.setTextAlignment(TextAlignment.LEFT);
        vBox.getChildren().addAll(textTitle, text);
        return vBox;
    }
}

