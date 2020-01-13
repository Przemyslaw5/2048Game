package agh.iet.game.gui;

import agh.iet.game.Game;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Statistics {

    private Text text1 = new Text();
    private Text text2 = new Text();

    public Pane statisticsPane(double BLOCK_SIZE, double GAP_SIZE, double STATISTICS_SIZE, Game game) {

        Pane statistics = new Pane();

        HBox hBox = new HBox();

        StackPane stackPane = new StackPane();
        Rectangle rectangleName = new Rectangle(2 * BLOCK_SIZE + 2 * GAP_SIZE, STATISTICS_SIZE);
        rectangleName.setFill(Color.rgb(244,244,244));
        Text text = new Text("2048");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text.setFill(Color.rgb(119, 110, 101));
        text.setTextAlignment(TextAlignment.RIGHT);
        stackPane.getChildren().addAll(rectangleName, text);

        Pane scoreAndBestPane = new Pane();
        Rectangle score = new Rectangle(2 * BLOCK_SIZE + 3 * GAP_SIZE, STATISTICS_SIZE);
        score.setFill(Color.rgb(244,244,244));
        scoreAndBestPane.getChildren().add(score);


        Pane scoreAndBestTempPane = new Pane();

        StackPane stackPane1 = new StackPane();
        Rectangle scoreRectangle = new Rectangle(120, 60);
        setProperties(game, stackPane1, scoreRectangle, text1, "SCORE\n");
        stackPane1.getChildren().addAll(scoreRectangle, text1);

        StackPane stackPane2 = new StackPane();
        Rectangle bestRectangle = new Rectangle(120, 60);
        bestRectangle.setX(130);
        bestRectangle.setY(50);
        setProperties(game, stackPane2, bestRectangle, text2, "BEST\n");
        stackPane2.setLayoutX(130);
        stackPane2.getChildren().addAll(bestRectangle, text2);

        scoreAndBestTempPane.getChildren().addAll(stackPane1, stackPane2);
        scoreAndBestPane.getChildren().add(scoreAndBestTempPane);

        hBox.getChildren().addAll(stackPane, scoreAndBestPane);

        statistics.getChildren().add(hBox);

        return statistics;
    }

    private void setProperties(Game game, StackPane stackPane2, Rectangle bestRectangle, Text text2, String s) {
        bestRectangle.setArcWidth(10.0);
        bestRectangle.setArcHeight(10.0);
        bestRectangle.setFill(Color.rgb(187, 173, 160));
        text2.setText(s + game.score);
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text2.setTextAlignment(TextAlignment.CENTER);
        stackPane2.setLayoutY(50);
    }

    public void update(Game game, int best){
        text1.setText("SCORE\n" + game.score);
        text2.setText("BEST\n" + best);
    }

}