package sudoku;

/**
 * Runs the Sudoku GUI program.
 * 
 */
public class SudokuGame {

	/**
	 * Runs the Sudoku GUI program.
	 */
	public static void main(String[] args) {

		// Set System L&F
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (ClassNotFoundException | InstantiationException |
		// IllegalAccessException
		// | UnsupportedLookAndFeelException e) {
		// e.printStackTrace();
		// }

		SudokuBoard board = new SudokuBoard();
		// frame tar emot ett SudokuBoard objekt som motsvarar ett
		// sudokubrädes

		new SudokuFrame(board);

	}

}
