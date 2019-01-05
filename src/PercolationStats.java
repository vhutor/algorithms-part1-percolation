import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int trials;
    private double[] xs;
    private Percolation percolation;

    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        xs = new double[n];
        this.trials = trials;
        percolation = new Percolation(n);

        for (int index = 0; index < trials; index++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;

                if(!percolation.isOpen(i , j)) {
                    percolation.open(i, j);
                }
            }
            xs[index] = (double) percolation.numberOfOpenSites()/ (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(xs);
    }

    public double stddev() {
        return StdStats.stddev(xs);
    }

    public double confidenceLo() {
        return ( mean() - (1.96*stddev()) ) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return ( mean() + (1.96*stddev()) ) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);
        String confidence = percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi();
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + confidence);

    }


}
