package gol;

public class Game {

    public boolean[][] marksAlive = new boolean[15][20];

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
        String kind = neighbourHelper(x, y);

        neighbour = getNeighbour(x, y, neighbour, kind);
        System.out.println("NH found!");
        System.out.println("N:" + neighbour);
        return neighbour;
    }

    private int getNeighbour(int x, int y, int neighbour, String kind) {
        neighbour = getNeighbour1(x, y, neighbour, kind);
        neighbour = getNeighbour2(x, y, neighbour, kind);
        neighbour = getNeighbour3(x, y, neighbour, kind);
        neighbour = getNeighbour4(x, y, neighbour, kind);
        return neighbour;
    }

    private int getNeighbour4(int x, int y, int neighbour, String kind) {
        if (!kind.equals("bl") && !kind.equals("br") && !kind.equals("b")
                && isAlive(x +1, y)) {
            System.out.println(7);
            neighbour += 1;
        }
        if ((kind.equals("tl") || kind.equals("t") || kind.equals("l") || kind.equals("nm"))
                && isAlive(x +1, y +1)) {
            System.out.println(8);
            neighbour += 1;
        }
        return neighbour;
    }

    private int getNeighbour3(int x, int y, int neighbour, String kind) {
        if (!kind.equals("tr") && !kind.equals("br") && !kind.equals("r")
                && isAlive(x, y +1)) {
            System.out.println(5);
            neighbour += 1;
        }
        if ((kind.equals("tr") || kind.equals("t") || kind.equals("r") ||  kind.equals("nm"))
                && isAlive(x +1, y -1) ) {
            System.out.println(6);
            neighbour += 1;
        }
        return neighbour;
    }

    private int getNeighbour2(int x, int y, int neighbour, String kind) {
        if ((kind.equals("bl") || kind.equals("b") || kind.equals("l") || kind.equals("nm"))
                && isAlive(x -1, y +1)) {
            System.out.println(3);
            neighbour += 1;
        }
        if (!kind.equals("tl") && !kind.equals("bl") && !kind.equals("l")
                && isAlive(x, y -1)) {
            System.out.println(4);
            neighbour += 1;
        }
        return neighbour;
    }

    private int getNeighbour1(int x, int y, int neighbour, String kind) {
        if ((kind.equals("br") || kind.equals("b") || kind.equals("r") ||  kind.equals("nm"))
                && isAlive(x -1, y -1)) {
            System.out.println(1);
            neighbour += 1;
        }
        if (!kind.equals("tl") && !kind.equals("tr") && !kind.equals("t")
                && isAlive(x -1, y) ) {
            System.out.println(2);
            neighbour += 1;
        }
        return neighbour;
    }

    public String neighbourHelper(int x, int y) {
        // tl - top left
        // bl - bottom left
        // tr - top right
        // br - bottom right
        // t - top
        // b - bottom
        // l - left
        // r - right
        // nm - not matter
        if (x == 0 && y == 0) {
            return "tl";
        }
        else if (x == 15 && y == 0) {
            return "bl";
        }
        else if (x == 0 && y == 20) {
            return "tr";
        }
        else if (x == 15 && y == 20) {
            return "br";
        }
        else if (x == 0) {
            return "t";
        }
        else if (x == 15) {
            return "b";
        }
        else if (y == 0) {
            return "l";
        }
        else if (y == 20) {
            return "r";
        }
        return "nm";
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
