import javafx.geometry.Point2D;

public class PartImage {
    private boolean[][] pixels;
    private boolean[][] visited;
    private int rows;
    private int cols;

    public PartImage(int r, int c) {
        rows = r;
        cols = c;
        visited = new boolean[r][c];
        pixels = new boolean[r][c];
    }

    public PartImage(int rw, int cl, byte[][] data) {
        this(rw, cl);
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                pixels[r][c] = data[r][c] == 1;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean getPixel(int r, int c) {
        return pixels[r][c];
    }

    // You will re-write the 5 methods below
    public void print() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                if (pixels[r][c]) {
                    System.out.print("*");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    public Point2D findStart() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                if (pixels[r][c]) {
                    return new Point2D(r, c);
                }
            }
        }
        return null;
    }

    public int partSize() {
        int count = 0;
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                if (pixels[r][c]) {
                    count ++;
                }
            }
        }
        return count;
    }

    private void expandFrom(int r, int c) {
        if (r < 0 | r >= getRows() | c < 0 | c >= getCols()) {
            return;
        }
        if (!pixels[r][c]) {
            return;
        }
        this.pixels[r][c] = false;
        {
            expandFrom(r + 1, c);
            expandFrom(r - 1, c);
            expandFrom(r, c + 1);
            expandFrom(r, c - 1);
        }
    }

    private int perimeterOf(int r, int c) {
        int count = 0;
        if (r < 0 | r >= getRows() | c < 0 | c >= getCols()) {
            return 0;
        }
        if (pixels[r][c]) {
            visited[r][c] = pixels[r][c];
        }
        if (pixels[r][c] != visited[r][c]) {
            if (!pixels[r + 1][c]) {
                count++;
            }
            if (!pixels[r - 1][c]) {
                count++;
            }
            if (!pixels[r][c + 1]) {
                count++;
            }
            if (!pixels[r][c - 1]) {
                count++;
            }
            if (!pixels[r][c]) {
                count++;
            }
        }
        return 0; //count + perimeterOf(r + 1, c) + perimeterOf(r - 1, c) + perimeterOf(r, c + 1) + perimeterOf(r, c - 1);
    }


    public boolean isBroken(){
        Point2D p = findStart();
        expandFrom((int)p.getX(), (int)p.getY());
        return (partSize() != 0);
    }

    public int perimeter() {
        Point2D p = findStart();
        return perimeterOf((int)p.getX(), (int)p.getY());
    }

    public static PartImage exampleA() {
        byte[][] pix = {{0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,0,0,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleB() {
        byte[][] pix = {{1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,1,1,1,1,1,1,0,0,0},
                {0,1,0,1,0,0,1,1,1,1},
                {0,1,0,1,0,0,1,0,0,0},
                {0,1,0,1,0,0,1,0,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleC() {
        byte[][] pix = {{1,1,1,0,0,0,1,0,0,0},
                {1,1,1,1,0,0,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1},
                {0,1,1,1,0,0,1,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {1,0,0,0,1,1,0,1,1,1},
                {1,1,0,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,1,1,0,1,1,1,1,1},
                {0,0,1,0,0,0,1,1,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleD() {
        byte[][] pix = {{1,0,1,0,1,0,1,1,0,0},
                {1,0,1,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,1,1},
                {1,0,1,1,1,1,1,1,1,0},
                {1,0,0,1,0,0,1,0,0,0},
                {1,1,0,0,0,1,1,0,0,1},
                {0,1,0,0,0,0,0,0,1,1},
                {0,1,0,1,0,0,0,0,0,0},
                {0,0,0,1,1,1,0,0,0,0},
                {0,0,0,0,0,1,1,0,0,0}};
        return new PartImage(10,10, pix);
    }
}