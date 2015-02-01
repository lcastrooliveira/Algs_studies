
public class PercolationStats {

	private double results[];
	private double mean;
	private double std;
	private int T;
	private int openSites = 0;

	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) throws IllegalArgumentException {
		if(N <= 0 || T <= 0) throw new IllegalArgumentException();
		this.T = T;
		results = new double[T];
		for(int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			openSites = 0;
			while(!p.percolates()) {
				int x = StdRandom.uniform(1, N+1);
				int y = StdRandom.uniform(1, N+1);
				if(!p.isOpen(x, y)) {
					p.open(x,y);
					openSites++;
				}
			}
			results[i] = ((double)openSites/(N*N));
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		mean = StdStats.mean(results);
		return mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		std = StdStats.stddev(results);
		return std;
	}

	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return (mean - ((1.96*Math.sqrt(std))/Math.sqrt(T)));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return (mean + ((1.96*Math.sqrt(std))/Math.sqrt(T)));
	}

	// test client
	public static void main(String[] args) {
	}
}