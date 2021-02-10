package controllers;
import models.modelsImpl.Game;
import models.modelsImpl.Messages;
import util.observer.Observable;

public class Controller extends Observable{
    Game game;
    String statusMessage;


    public Controller(Game game) {
        this.game = game;
        this.statusMessage = Messages.WELCOME_MESSAGE;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public Game getGame() {
        return game;
    }

    public String getGameSting() {
        return this.game.toString() ;

    }

    public void changeBirdPosition(){
        this.game.getBird().setPositionY(this.game.getBird().getPositionY()+1);
        this.game.info();
    }

    public void goUp() {
        this.game.getBird().setPositionY(this.game.getBird().getPositionY()-2);
    }

    public void changeTubesPositions() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            this.game.getTubes()[i].setPositionX(this.game.getTubes()[i].getPositionX() - 1);
        }
        this.game.updateTubes();
    }
    public boolean gameOverTest() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            if ((this.game.getBird().getPositionX() == this.game.getTubes()[i].getPositionX())
            && (this.game.getBird().getPositionY()  == this.game.getTubes()[i].getPositionY()))
            {
                this.statusMessage = Messages.GameOver;
                return true;
            }
        }
        return false;
    }
    int Score = 0;
    public void gameScore() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            if (this.game.getBird().getPositionX() == this.game.getTubes()[i].getPositionX()+this.game.getTubes()[i].getWidth())
            {
                Score++;
                this.statusMessage = Messages.GameScore(Score);
            }
        }
    }
}

