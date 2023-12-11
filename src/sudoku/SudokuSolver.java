package sudoku;

/**
 * Sets guides for what a SodukoSolver should have.
 */
public interface SudokuSolver {
	/**
	 * Solves the sudoku by setting the internal boxes to correct values.
	 * 
	 * @return true if the sudoku is solveable
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void set(int row, int col, int digit);

	/**
	 * Gets digit from row, column.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * 
	 * @return number at row, column
	 */
	int get(int row, int col);

	/**
	 * Clears box at (row,col) 
	 * 
	 * @param row   The row
	 * @param col   The column
	 */
	void clear(int row, int col);

	/**
	 * Clears all digits in the sudoku.
	 */
	void clearAll();

	/**
	 * Checks that the digit in the the box follows the rules.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 * @return true if the digit in the box row, col follows the sudoku rules.
	 */
	boolean isValid(int row, int col);

	/**
	 * Checks if the numbers placed by the user follows the rules.
	 * 
	 * @return false if the placed numbers breaks the rules.
	 */
	boolean isAllValid();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setGrid(int[][] m);

	/**
	 * Returns the current grid of the board.
	 * 
	 * @return 2D array of the board.
	 */
	int[][] getGrid();
}