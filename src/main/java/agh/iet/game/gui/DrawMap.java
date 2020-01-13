package agh.iet.game.gui;

import agh.iet.game.Game;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DrawMap {

    public static void draw(Pane pane, Game game, double blockSize, double gapSize){

        Rectangle rectangle = new Rectangle(blockSize * 4 + gapSize * 5, blockSize * 4 + gapSize * 5);
        rectangle.setFill(Color.rgb(187, 173, 160));
        rectangle.setArcWidth(10.0);
        rectangle.setArcHeight(10.0);
        pane.getChildren().add(rectangle);




        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Rectangle square = new Rectangle(blockSize, blockSize);
                square.setFill(game.setBackgroundColorOfSquare(i, j));
                square.setArcWidth(10.0);
                square.setArcHeight(10.0);

                Text text = game.setTextProperties(i, j);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(square, text);

                stackPane.setLayoutX((j + 1) * gapSize + j * blockSize);
                stackPane.setLayoutY((i + 1) * gapSize + i * blockSize);

                pane.getChildren().add(stackPane);
            }
        }

    }
}

