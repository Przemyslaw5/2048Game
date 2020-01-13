package agh.iet.game;

import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.util.LinkedList;
import java.util.Random;

public class Game {

    public Block[][] blocks;
    public int score;
    private int blockAmount;
    private int amountOfNumbersToStart = 1;
    public boolean isWin;
    private final int goal;
    private Random random = new Random();

    public Game(int amountOfNumbersToStart, int goal) {
        blocks = new Block[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j] = new Block(0);
            }
        }
        this.score = 0;
        this.isWin = false;
        addRandomBlockOnMap();
        addRandomBlockOnMap();
        this.blockAmount = 2;
        this.amountOfNumbersToStart = amountOfNumbersToStart;
        this.goal = goal;
    }

    public void restart(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j].setValue(0);
            }
        }
        this.score = 0;
        this.isWin = false;
        addRandomBlockOnMap();
        addRandomBlockOnMap();
        this.blockAmount = 2;
    }

    public void addRandomBlockOnMap() {

        int number = random.nextInt(amountOfNumbersToStart);
        int value;

        if(number == 0)
            value = 2;
        else if(number == 1)
            value = 4;
        else if(number == 2)
            value = 8;
        else
            value = 16;

        int x, y;
        do {
            x = random.nextInt(4);
            y = random.nextInt(4);
        } while (blocks[x][y].getValue() != 0);
        blocks[x][y].setValue(value);
    }

    public boolean canMove() {
        if (this.blockAmount <= 15) return true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i < 3 && blocks[i][j].equals(blocks[i + 1][j])) || (j < 3 && blocks[i][j].equals(blocks[i][j + 1])))
                    return true;
            }
        }
        return false;
    }

    public void swap(int w1, int k1, int w2, int k2) {
        Block temp = blocks[w1][k1];
        blocks[w1][k1] = blocks[w2][k2];
        blocks[w2][k2] = temp;
    }

    public void rotateRight() {
        Block[][] temp = new Block[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[j][3 - i] = blocks[i][j];
            }
        }
        blocks = temp;
    }

    public void rotateLeft() {
        Block[][] temp = new Block[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[3 - j][i] = blocks[i][j];
            }
        }
        blocks = temp;
    }

    public void rotateDouble() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                swap(i, j, 3 - i, 3 - j);
            }
        }
    }


    public void left() {
        int blockAmountBeforeMove = this.blockAmount;
        boolean isMove = false, flag;
        for (int i = 0; i < 4; i++) {
            LinkedList<Block> tempBlocks = new LinkedList<>();
            flag = false;
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j].getValue() != 0) {
                    tempBlocks.add(new Block(blocks[i][j].getValue()));
                    blocks[i][j].setValue(0);
                    if (flag) isMove = true; // Wystąpiło 0 wcześniej a teraz jakaś liczba, czyli było przsuniecie
                    flag = false;
                } else  // Wystąpiło 0
                    flag = true;
            }
            LinkedList<Block> addToTable = new LinkedList<>();
            for (int j = 0; j < 4; j++) {
                if (tempBlocks.size() >= j + 2 && tempBlocks.get(j).equals(tempBlocks.get(j + 1))) {
                    if(tempBlocks.get(j).getValue() == goal / 2) isWin = true;
                    addToTable.add(new Block(tempBlocks.get(j).getValue() * 2));
                    this.blockAmount--;
                    this.score += tempBlocks.get(j).getValue() * 2;
                    j++;
                } else if (tempBlocks.size() >= j + 1)
                    addToTable.add(new Block(tempBlocks.get(j).getValue()));
            }

            for (int j = 0; j < addToTable.size(); j++) {
                blocks[i][j] = addToTable.get(j);
            }
        }
        if (isMove || blockAmountBeforeMove != this.blockAmount) {
            addRandomBlockOnMap();
            this.blockAmount++;
        }
    }

    public void up() {
        rotateLeft();
        left();
        rotateRight();
    }

    public void right() {
        rotateDouble();
        left();
        rotateDouble();
    }

    public void down() {
        rotateRight();
        left();
        rotateLeft();
    }

    public void randomTurn(){
        int turn = random.nextInt(3);
        if(turn == 0) rotateLeft();
        else if(turn == 1) rotateDouble();
        else rotateRight();
    }

    public Color setBackgroundColorOfSquare(int i, int j){
        int value = blocks[i][j].getValue();
        if(value == 0) return Color.rgb(204, 192, 179);
        if(value == 2) return Color.rgb(238, 228, 218);
        if(value == 4) return Color.rgb(237, 224, 200);
        if(value == 8) return Color.rgb(242, 177, 121);
        if(value == 16) return Color.rgb(245, 149, 99);
        if(value == 32) return Color.rgb(246, 124, 95);
        if(value == 64) return Color.rgb(246, 94, 59);
        if(value == 128) return Color.rgb(237, 207, 114);
        if(value == 256) return Color.rgb(237, 204, 97);
        if(value == 512) return Color.rgb(237, 200, 80);
        if(value == 1024) return Color.rgb(237, 197, 63);
        if(value == 2048) return Color.rgb(237, 194, 46);
        return Color.rgb(60, 58, 50);
    }

    public Color setColorFont(int value){
        if(value < 8) return Color.rgb(119, 110, 101);
        return Color.rgb(249, 246, 242);
    }

    public int setFontSize(int value){
        if(value < 128) return 55;
        if(value < 1024) return 45;
        return 35;
    }

    public Text setTextProperties(int i, int j){
        int value = blocks[i][j].getValue();
        int fontSize = setFontSize(value);
        Color color = setColorFont(value);
        Text text = new Text(getNumberToBlock(value));

        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, fontSize));

        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setFill(color);
        return text;
    }

    public String getNumberToBlock(int value){
        if(value == 0) return "";
        return Integer.toString(value);
    }
}