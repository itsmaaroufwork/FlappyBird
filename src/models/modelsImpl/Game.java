package models.modelsImpl;

public class Game {
    private Bird bird;
    private Tubes[] tubes;
    private int height = 10, width = 30;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Game() {
        this.tubes = new Tubes[10];
        this.tubes[0] = new Tubes(5, 3, 10, 5, false);
        this.tubes[1] = new Tubes(3, 3, 10, 0, true);
        this.tubes[2] = new Tubes(4, 3, 17, 6, false);
        this.tubes[3] = new Tubes(4, 3, 17, 0, true);
        this.tubes[4] = new Tubes(7, 3, 24, 3, false);
        this.tubes[5] = new Tubes(2, 3, 24, 0, true);
        this.tubes[6] = new Tubes(6, 3, 31, 4, false);
        this.tubes[7] = new Tubes(3, 3, 31, 0, true);
        this.tubes[8] = new Tubes(5, 3, 38, 5, false);
        this.tubes[9] = new Tubes(4, 3, 38, 0, true);

        this.bird = new Bird(2,5);
    }

    public Tubes[] getTubes() {
        return tubes;
    }

    public Bird getBird() {
        return bird;
    }


    public int getTubeIndex(int x, int y){
        for (int i = 0; i < this.tubes.length; i++) {

            if (x==this.tubes[i].getPositionX()
                    && y>=this.tubes[i].getPositionY()
                    && y<this.tubes[i].getPositionY()+this.tubes[i].getHeight()
                    || x==(this.tubes[i].getPositionX() +this.tubes[i].getWidth() )
                    && y<this.tubes[i].getPositionY()+this.tubes[i].getHeight()
                    && y>=this.tubes[i].getPositionY()
            ) {
                return i;
            }

        }
        return -1;
    }
    public String toString(){
        return printGame();
    }

    public String printGame(){
        String result = "", oneBorder = "|", onePixelX = " ", onePixelY = "\n";

        result += "|------------------------------|"+onePixelY;
        for (int y = 0; y < height; y++) {
            //Building Game Borders
            result += oneBorder;
            for (int x = 0; x < width; x++) {
                //Building Tubes Borders
                int tubeIndex = getTubeIndex(x, y);
                if (tubeIndex >-1) {
                    result +=  ANSI_RED + oneBorder + ANSI_RESET;
                }else if (x == this.bird.getPositionX() && y == this.bird.getPositionY() ) {
                    // bird
                    result += ANSI_YELLOW +"O"+ ANSI_RESET;
                }else {
                    // space
                    result += onePixelX ;
                }
            }
            result += oneBorder+onePixelY;
        }
        result += "|------------------------------|";
        return result;
    }

    public void updateTubes() {
        int lastPositionX = getLastPositionX();
        for (int i = 0; i <this.tubes.length ; i++) {
            System.out.print(this.tubes[i].getPositionX() + " ");
            if (this.tubes[i].getPositionX() < -4) {
                this.tubes[i].setPositionX(lastPositionX + 7);
            }
        }
    }

    private int getLastPositionX() {
        int X = 0;
        for (int i = 0; i <this.tubes.length ; i++) {
            if (this.tubes[i].getPositionX()>=X) {
                X = this.tubes[i].getPositionX();
            }
        }
        return X;
    }
}