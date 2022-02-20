package oo.hide;


public class PointSet {
    Point[] points;
    int pointCount = 0;

    public PointSet(int capacity) {
        points = new Point[capacity];
    }

    public PointSet() {
        this(2);
    }

    public void add(Point point) {
        if (!contains(point)) {
            try {
                points[pointCount] = point;
                pointCount++;
            } catch (ArrayIndexOutOfBoundsException e) {
                Point[] temp = new Point[pointCount];
                for (int i = 0; i < points.length; i++) {
                    temp[i] = points[i];
                }
                points = new Point[pointCount * 2];
                points[pointCount] = point;
                for (int i = 0; i < temp.length; i++) {
                    points[i] = temp[i];
                }
                pointCount++;
            }
        }
//         sort
//        for (int i = 0; i < points.length; i++) {
//            for (int j = 0; j < points.length; j++) {
//                Point temp;
//                System.out.println(Integer.parseInt(String.valueOf(points[j])));
//                System.out.println(points[i]);
//                if (Integer.parseInt(String.valueOf(points[j]))<Integer.parseInt(String.valueOf(points[i]))){
//                    temp=points[i];
//                    points[i] = points[j];
//                    points[j] = temp;
//                }
//            }
//        }
    }

    public int size() {
        return pointCount;
    }

    public boolean contains(Point point) {
        for (Point p : points) {
            if (p == null && point == null) {
                return true;
            } else if (p == null || point == null) {
                return false;
            } else if (p.toString().equals(point.toString())) {
                return true;
            }
        }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet ret = new PointSet();
        for (Point point : this.points) {
            if (!other.contains(point)) {
                ret.add(point);
            }
        }
        return ret;
    }

    public PointSet intersect(PointSet other) {
        PointSet ret = new PointSet();
        for (Point point : this.points) {
            if (other.contains(point)) {
                ret.add(point);
            }
        }
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        PointSet other = (PointSet) obj;
        if (this.points != null && other.points != null) {
            for (Point point : this.points) {
                if (!other.contains(point)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            if (point != null) {
                ret.append(point);
                if (i + 1 != points.length && points[i + 1] != null) {
                    ret.append(", ");
                }
            }
        }
        return ret.toString();
    }
}
