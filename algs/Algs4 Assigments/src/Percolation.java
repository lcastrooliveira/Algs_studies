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
		
		grid[0] = 1;
		grid[N*N+1] = 1;
		
		for(int i = 1; i <= N; i++) {
			uf.union(0, i);
		}
		
		for(int i = (N-1)*N+1; i <= (N-1)*N+N; i++) {
			uf.union(N*N+1, i);
		}
		
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) throws IndexOutOfBoundsException  {
		if(!validateIndex(i)) throw new IndexOutOfBoundsException("row index i out of bounds");
		if(!validateIndex(j)) throw new IndexOutOfBoundsException("row index j out of bounds");
		if(N > 1) {
			grid[xyTo1D(i, j)] = 1;
			
			//Up
			if(i - 1 >= 1 && isOpen(i-1, j)) {
				uf.union(xyTo1D(i, j), xyTo1D(i-1, j));
			}
			
			//Left
			if(j - 1 >= 1 && isOpen(i, j-1)) {
				uf.union(xyTo1D(i, j), xyTo1D(i, j-1));
			}
			
			//Right
			if(j + 1 <= N && isOpen(i, j+1)) {
				uf.union(xyTo1D(i, j), xyTo1D(i, j+1));
			}
			
			//Down
			if(i + 1 <= N && isOpen(i+1, j)) {
				uf.union(xyTo1D(i, j), xyTo1D(i+1, j));
			}
		} else {
			uf.union(0, 1);
			uf.union(2, 1);
		}
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if(!validateIndex(i)) throw new IndexOutOfBoundsException("row index i out of bounds");
		if(!validateIndex(j)) throw new IndexOutOfBoundsException("row index j out of bounds");
		if(grid[xyTo1D(i, j)] == 1) return true;
		else return false;
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
		if(!validateIndex(i)) throw new IndexOutOfBoundsException("row index i out of bounds");
		if(!validateIndex(j)) throw new IndexOutOfBoundsException("row index j out of bounds");
		if(isOpen(i, j)) return uf.connected(0, xyTo1D(i, j));
		else return false;
	}
	
	// does the system percolate?
	public boolean percolates() {
		return uf.connected(0, N*N+1);
	}
	
	private boolean validateIndex(int index) {
		if (index <= 0 || index > N) return false; 
		else return true;
	}
	
	private int xyTo1D(int x, int y) {
		return (x-1)*N+y;
	}
	
	// test client (optional)
	public static void main(String[] args) {}
}