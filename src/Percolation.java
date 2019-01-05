import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] sites;
    private int beginSites;
    private int endSites;

    private int top = 0;
    private int bottom;

    private int openSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        sites = new boolean[n + 1][n + 1];
        beginSites = 1;
        endSites = n;
        openSites = 0;
        bottom = n * n + 1;

        weightedQuickUnionUF = new WeightedQuickUnionUF(bottom + 1);
    }

    public void open(int row, int col) {
        if(row <= 0 || col <=0 || row > endSites || col > endSites) {
            throw new IllegalArgumentException();
        }

        sites[row][col] = true;
        openSites++;

        if(row > beginSites && row < endSites && col > beginSites && col < endSites) {
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col + 1), convertToInt(row, col));
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row + 1, col), convertToInt(row, col));
            }
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col - 1), convertToInt(row, col));
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row - 1, col), convertToInt(row, col));
            }
        }

        if(row == beginSites && col == beginSites) {
            weightedQuickUnionUF.union(convertToInt(row, col), top);
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col + 1), convertToInt(row, col));
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row + 1, col), convertToInt(row, col));
            }
        }

        if(row == beginSites && col > beginSites && col < endSites) {
            weightedQuickUnionUF.union(convertToInt(row, col), top);
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col + 1), convertToInt(row, col));
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row + 1, col), convertToInt(row, col));
            }
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col - 1), convertToInt(row, col));
            }
        }

        if(row == beginSites && col == endSites) {
            weightedQuickUnionUF.union(convertToInt(row, col), top);
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col - 1), convertToInt(row, col));
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row + 1, col), convertToInt(row, col));
            }
        }

        if(row == endSites && col == endSites) {
            weightedQuickUnionUF.union(convertToInt(row, col), bottom);
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col - 1), convertToInt(row, col));
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row - 1, col), convertToInt(row, col));
            }
        }

        if(row == endSites && col == beginSites) {
            weightedQuickUnionUF.union(convertToInt(row, col), bottom);
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row - 1 , col), convertToInt(row, col));
            }
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col + 1), convertToInt(row, col));
            }
        }

        if(col == beginSites && row > beginSites && row < endSites) {
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row - 1, col), convertToInt(row, col));
            }
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row + 1 , col), convertToInt(row, col));
            }
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col + 1), convertToInt(row, col));
            }
        }

        if(col == endSites && row > beginSites && row < endSites) {
            if(isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row + 1, col), convertToInt(row, col));
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row - 1, col), convertToInt(row, col));
            }
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col - 1), convertToInt(row, col));
            }
        }

        if(row == endSites && col > beginSites && col < endSites) {
            weightedQuickUnionUF.union(convertToInt(row, col), bottom);
            if(isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col - 1), convertToInt(row, col));
            }
            if(isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(convertToInt(row, col + 1), convertToInt(row, col));
            }
            if(isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(convertToInt(row - 1, col), convertToInt(row, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if(row <= 0 || col <= 0 || row > endSites || col > endSites) {
            throw new IllegalArgumentException();
        }

        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if(row <= 0 || col <=0 || row > endSites || col > endSites) {
            throw new IllegalArgumentException();
        }

        return weightedQuickUnionUF.connected(top, convertToInt(row, col));
    }

    private int convertToInt(int row, int col) {
        return (row * endSites) + (col + 1) - endSites;
    }


    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(top, bottom);
    }

    public static void main(String[] args) {
        int size = 3;
        Percolation percolation = new Percolation(size);
        double throshold = 0;

        while (!percolation.percolates()) {

            int i = StdRandom.uniform(size) + 1;
            int j = StdRandom.uniform(size) + 1;
            System.out.println(i + " " + j);
            if(!percolation.isOpen(i , j)) {
                percolation.open(i, j);
            }
        }

        throshold = (double) percolation.numberOfOpenSites()/ (size * size);
        System.out.println(throshold);
    }

}
