package sudoku;

/**
 * A class that represents and solves a sudoku canvas
 */
public class SudokuBoard implements SudokuSolver {
	private int[][] boardNumbers = new int[9][9];
	private boolean[][] setByUser = new boolean[9][9];

	@Override
	public boolean solve() {
		if (isAllValid()) {
			return solveRec(-1, 0);
		} else {
			return false;
		}
	}

	private boolean solveRec(int row, int col) {
		col = col % 9;
		int colp = col + 1;
		if (col == 0) {
			row++;
		}

		// basfall
		if (row == 9) {
			return true;
		}

		// 2.
		if (setByUser[row][col]) {
			if (isValid(row, col)) {
				return solveRec(row, colp);
			} else {
				return false;
			}
		}

		// 1.
		int testing = 1;
		while (testing < 10) {
			setC(row, col, testing);

			if (isValid(row, col)) {
				if (solveRec(row, colp)) {
					return true;
				}
			}
			setC(row, col, 0);
			testing++;
		}
		return false;
	}

	@Override
	public void set(int row, int col, int digit) {
		boardNumbers[row][col] = digit;
		if(digit != 0) {			
			setByUser[row][col] = true;
		}

	}

	/**
	 * Put digit in row, col; but does <b>not</b> flag setByUser to true
	 * 
	 * @param row
	 * @param col
	 * @param digit
	 */
	private void setC(int row, int col, int digit) {
		boardNumbers[row][col] = digit;
		setByUser[row][col] = false;

	}

	@Override
	public int get(int row, int col) {
		return boardNumbers[row][col];
	}

	@Override
	public void clear(int row, int col) {
		boardNumbers[row][col] = 0;
		setByUser[row][col] = false;
	}

	@Override
	public void clearAll() {
		boardNumbers = new int[9][9];
		setByUser = new boolean[9][9];

	}

	@Override
	public boolean isValid(int row, int col) {
		int x = get(row, col);
		
		if(row > 9 || col > 9 || (x > 9 || x < 0)) {
			throw new IllegalArgumentException("Number is out of bounds");
		}

		// kollar rad
		for (int i = 0; i < 9; i++) {
			if (i != col && x == get(row, i)) {
				return false;
			}
		}

		// kollar column
		for (int i = 0; i < 9; i++) {
			if (i != row && x == get(i, col)) {
				return false;
			}
		}

		// kollar 3x3box
		// vilken rad/col börjar boxen?
		int r = row - (row % 3);
		int c = col - (col % 3);

		// loopar igenom boxen
		for (int i = r; i < (r + 3); i++) {
			for (int j = c; j < (c + 3); j++) {
				if (i != row && j != col && get(i, j) == x) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public boolean isAllValid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (setByUser[i][j]) {
					if (!isValid(i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void setGrid(int[][] m) {
		if(m.length != 9) {
			throw new IllegalArgumentException("matrix has wrong dimension");
		}
		
		for(int i = 0; i < 9; i++) {
			if(m[i].length != 9) {
				throw new IllegalArgumentException("matrix has wrong dimension");
			}
		}
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {	
				set(i, j,  m[i][j]);
			}

		}

	}

	@Override
	public int[][] getGrid() {
		return boardNumbers;
	}

	@SuppressWarnings("unused")
	private void sleeper(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prints how the current board looks.
	 * 
	 * @param ShowSetByUser set to true if setByUser matrix also should be printed
	 */
	public void print(boolean ShowSetByUser) {
		System.out.println();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(boardNumbers[i][j]);
				if (j % 3 == 2) {
					System.out.print(" | ");
				} else {
					System.out.print(" ");
				}
			}
			if (i % 3 == 2) {
				System.out.print("\n-----------------------");
			}
			System.out.print("\n");
		}

		if (ShowSetByUser) {
			System.out.print("\n");
			System.out.println("setByUser: ");
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(setByUser[i][j] + " ");
				}
				System.out.print("\n");
			}
		}
	}

}
