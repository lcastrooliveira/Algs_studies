//Created by Xindula in 01/02/2015

public class Percolation {
	
	private int N;
	private WeightedQuickUnionUF uf;
	private int grid[];
	
	// create N-by-N grid, with all sites blocked 
	public Percolation(int N) throws IllegalArgumentException {
		if(N <= 0) throw new IllegalArgumentException();
		this.N = N;
		
		uf = new WeightedQuickUnionUF((N*N)+2);
		grid = new int[N*N+2];
		
		grid[0] = 0;
		grid[N*N-1] = 0;
		for(int i = 1; i < grid.length; i++) {
			grid[i] = -1;
		}
		
		for(int i = 1; i <= N; i++) {
			uf.union(0, i);
			
		}
		
		for(int i = (N-1)*N+1; i <= (N-1)*N+N; i++) {
			uf.union(N*N+1, i);
		}
		
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		validateIndex(i);
		validateIndex(j);
		grid[xyTo1D(i, j)] = 0;
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		validateIndex(i);
		validateIndex(j);
		if(grid[xyTo1D(i, j)] == 0) return true;
		else return false;
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		validateIndex(i);
		validateIndex(j);
		return uf.connected(0, xyTo1D(i, j));
	}
	
	// does the system percolate?
	public boolean percolates() {
		return false;
	}
	
	private boolean validateIndex(int index) throws IndexOutOfBoundsException {
		if (index <= 0 || index > N*N) throw new IndexOutOfBoundsException("row index i out of bounds");
		else return true;
	}
	
	private int xyTo1D(int x, int y) {
		return (x-1)*N+y;
	}

	// test client (optional)
	public static void main(String[] args) {
		
	}
}