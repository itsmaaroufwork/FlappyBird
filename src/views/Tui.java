package views;
import controllers.Controller;
import util.observer.IObserver;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tui implements IObserver {
    Controller controller;
    boolean gameOver=false;

    public Tui(Controller controller) {
        this.controller = controller;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void update() {

    }
    public void run() {
        while(!gameOver) {
            printGame();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.controller.changeBirdPosition();
            this.controller.changeTubesPositions();
        }
    }

    public void scanInput(){
        Scanner myObj = new Scanner(System.in);
            String  a = myObj.next();
            System.out.println(a);
            if (a == "a") {
                controller.goUp();
            }

        myObj.close();
    }

    private void printGame() {
        System.out.println(this.controller.getGame());
    }
}