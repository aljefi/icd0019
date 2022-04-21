package gol;

public class Game {

    public boolean[][] marksAlive = new boolean[15][20];
    String kind;

    public void markAlive(int x, int y) {
        marksAlive[x][y] = true;
    }

    public boolean isAlive(int x, int y) {
        return marksAlive[x][y];
    }

    public void toggle(int x, int y) {
        marksAlive[x][y] = !marksAlive[x][y];
    }

    public Integer getNeighbourCount(int x, int y) {
        int neighbour = 0;
        neighbourHelper(x, y);

        if ((kind.equals("br") || kind.equals("b") || kind.equals("r") ||  kind.equals("nm"))
                && isAlive(x-1,y-1)) {
            System.out.println(1);
            neighbour += 1;
        }
        if (!kind.equals("tl") && !kind.equals("tr") && !kind.equals("t")
                && isAlive(x-1,y) ) {
            System.out.println(2);
            neighbour += 1;
        }
        if ((kind.equals("bl") || kind.equals("b") || kind.equals("l") || kind.equals("nm"))
                && isAlive(x-1,y+1)) {
            System.out.println(3);
            neighbour += 1;
        }
        if (!kind.equals("tl") && !kind.equals("bl") && !kind.equals("l")
                && isAlive(x,y-1)) {
            System.out.println(4);
            neighbour += 1;
        }
        if (!kind.equals("tr") && !kind.equals("br") && !kind.equals("r")
                && isAlive(x,y+1)) {
            System.out.println(5);
            neighbour += 1;
        }
        if ((kind.equals("tr") || kind.equals("t") || kind.equals("r") ||  kind.equals("nm"))
                && isAlive(x+1,y-1) ) {
            System.out.println(6);
            neighbour += 1;
        }
        if (!kind.equals("bl") && !kind.equals("br") && !kind.equals("b")
                && isAlive(x+1,y)) {
            System.out.println(7);
            neighbour += 1;
        }
        if ((kind.equals("tl") || kind.equals("t") || kind.equals("l") || kind.equals("nm"))
                && isAlive(x+1,y+1)) {
            System.out.println(8);
            neighbour += 1;
        }
        System.out.println("NH found!");
        return neighbour;
    }

    public void neighbourHelper(int x, int y) {
        if (x == 0 && y == 0) {
            kind = "tl";
        }
        else if (x == 15 && y == 0) {
            kind = "bl";
        }
        else if (x == 0 && y == 20) {
            kind = "tr";
        }
        else if (x == 15 && y == 20) {
            kind = "br";
        }
        else if (x == 0) {
            kind = "t";
        }
        else if (x == 15) {
            kind = "b";
        }
        else if (y == 0) {
            kind = "l";
        }
        else if (y == 20) {
            kind = "r";
        }
        else {
            kind = "nm";
        }
    }

    public void nextFrame() {
        boolean[][] marksAlive2 = new boolean[15][20];
        for (int i = 0; i < marksAlive.length -1; i++) {
            for (int j = 0; j < marksAlive[0].length -1 ; j++) {
                int neigCount = getNeighbourCount(i, j);
                boolean isAlive  = isAlive(i, j);
                marksAlive2[i][j] = nextState(isAlive, neigCount);
            }
        }
        marksAlive = marksAlive2;
    }

    public void clear() {
        marksAlive = new boolean[15][20];
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (isLiving) {
            if (neighborCount < 2) {
                isLiving = false;
            } else if (neighborCount > 3) {
                isLiving = false;
            }
        }
        else {
            if (neighborCount == 3) {
                isLiving = true;
            }
        }
        return isLiving;
    }
}
