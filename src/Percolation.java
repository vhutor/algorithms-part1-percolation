import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] sites;
    private int begin = 1;
    private int top, bottom;
    private int end;
    private int size;
    private int openSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        sites = new boolean[n + 1][n + 1];
        for (int i = begin; i < n + 1; i++) {
            for (int j = begin; j < n + 1; j++) {
                sites[i][j] = false;
            }
        }
        size = n * n;
        end = n;
        openSites = 0;

        top = 0;
        bottom = size + 2;

        weightedQuickUnionUF = new WeightedQuickUnionUF(size + 2);
    }

    public void open(int row, int col) {
        if(row <= 0 || col <=0 || row > end || col > end) {
            throw new IllegalArgumentException();
        }

        sites[row][col] = true;

        if(row > begin && row < end && col > begin && col < end) {
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(row, col + 1);
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(row + 1, col);
            }
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(row, col - 1);
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(row, col);
            }
        }

        if(row == begin && col == begin) {
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(row, col + 1);
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(row + 1, col);
            }
        }

        if(row == begin && col > begin && col < end) {
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(row, col + 1);
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(row + 1, col);
            }
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(row, col - 1);
            }
        }

        if(row == begin && col == end) {
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(row, col - 1);
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(row + 1, col);
            }
        }

        if(row == end && begin == end) {
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(row, col - 1);
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(row - 1, col);
            }
        }

        if(row == end && col == begin) {
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(row - 1 , col);
            }
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(row, col + 1);
            }
        }

        if(col == begin && row > begin && row < end) {
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(row - 1, col);
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(row + 1 , col);
            }
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(row, col + 1);
            }
        }

        if(col == end && row > begin && row < end) {
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(row + 1, col);
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(row - 1, col);
            }
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(row, col - 1);
            }
        }

        if(row == end && col > begin && col < end) {
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(row, col - 1);
            }
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(row, col + 1);
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(row - 1, col);
            }
        }

    }

    public boolean isOpen(int row, int col) {
        if(row <= 0 || col <= 0 || row > end || col > end) {
            throw new IllegalArgumentException();
        }

        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if(row <= 0 || col <=0 || row > end || col > end) {
            throw new IllegalArgumentException();
        }

        return weightedQuickUnionUF.connected(begin, convertToInt(row, col));
    }

    private int convertToInt(int row, int col) {
        return (row * size) + col - size;
    }


    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(begin, end);
    }

    public static void main(String[] args) {
        int size = 10;
        Percolation percolation = new Percolation(size);
        double throshold = 0;

        while (!percolation.percolates()) {
            percolation.open(StdRandom.uniform(size), StdRandom.uniform(size));
        }

        throshold = percolation.numberOfOpenSites()/ (size * size);
        System.out.println(throshold);
    }

}
