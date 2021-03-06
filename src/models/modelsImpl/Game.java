package models.modelsImpl;

public class Game {
    private Bird bird;
    private Tubes[] tubes;
    private int height = 20, width = 15;
    public static final String ANSI_RESET = "\u001B[0m", ANSI_RED = "\u001B[31m", ANSI_YELLOW = "\u001B[33m";

    public Game() {
        this.tubes = new Tubes[10];
        this.tubes[0] = new Tubes(10, 2, 10, 10, false);
        this.tubes[1] = new Tubes(6, 2, 10, 0, true);
        this.tubes[2] = new Tubes(8, 2, 16, 12, false);
        this.tubes[3] = new Tubes(8, 2, 16, 0, true);
        this.tubes[4] = new Tubes(12, 2, 22, 8, false);
        this.tubes[5] = new Tubes(4, 2, 22, 0, true);
        this.tubes[6] = new Tubes(10, 2, 28, 12, false);
        this.tubes[7] = new Tubes(6, 2, 28, 0, true);
        this.tubes[8] = new Tubes(8, 2, 34, 12, false);
        this.tubes[9] = new Tubes(8, 2, 34, 0, true);

        this.bird = new Bird(2, 3);
    }

    public Bird getBird() {
        return bird;
    }

    public Tubes[] getTubes() {
        return tubes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTubeIndex(int x, int y) {
        for (int i = 0; i < this.tubes.length; i++) {

            if (x == this.tubes[i].getPositionX()
                    && y >= this.tubes[i].getPositionY()
                    && y < this.tubes[i].getPositionY() + this.tubes[i].getHeight()
                    || x == (this.tubes[i].getPositionX() + this.tubes[i].getWidth())
                    && y < this.tubes[i].getPositionY() + this.tubes[i].getHeight()
                    && y >= this.tubes[i].getPositionY()
            ) {
                return i;
            }

        }
        return -1;
    }

    public String toString() {
        return printGame();
    }

    public String printGame() {
        String result = "", oneBorder = "|", onePixelX = " ", onePixelY = "\n";

        result += "_________________" + onePixelY;
        for (int y = 0; y < height; y++) {
            //Building Game Borders
            result += oneBorder;
            for (int x = 0; x < width; x++) {
                //Building Tubes Borders
                int tubeIndex = getTubeIndex(x, y);
                if (tubeIndex > -1) {
                    result += ANSI_RED + oneBorder + ANSI_RESET;
                } else if (x == this.bird.getPositionX() && y == this.bird.getPositionY()) {
                    // bird
                    result += ANSI_YELLOW + "O" + ANSI_RESET;
                } else {
                    // space
                    result += onePixelX;
                }
            }
            result += oneBorder + onePixelY;
        }
        result += "|_______________|";
        return result;
    }

    public void updateTubes() {
        int lastPositionX = getLastPositionX();
        for (int i = 0; i < this.tubes.length; i++) {
            if (this.tubes[i].getPositionX() < -4) {
                this.tubes[i].setPositionX(lastPositionX + 7);
            }
        }
    }

    private int getLastPositionX() {
        int X = 0;
        for (int i = 0; i < this.tubes.length; i++) {

            if (this.tubes[i].getPositionX() >= X) {
                X = this.tubes[i].getPositionX();
            }
        }
        return X;
    }
}
