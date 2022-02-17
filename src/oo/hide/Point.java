package oo.hide;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" +
                this.x +
                ", " +
                this.y +
                ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.toString().equals(obj.toString())) {
            return true;
        }
        return false;
    }
}
