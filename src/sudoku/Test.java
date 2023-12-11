package sudoku;
/**
 * Test class used for debugging
 */
public class Test {
	
	
	public static void main(String[] args) {
		SudokuBoard board = new SudokuBoard();

		// sätter enligt bilden
		setBoard(board);
		
		board.print(false);

		board.solve();
		
		board.print(false);

	}

	private static void setBoard(SudokuBoard board) {
		// rad 0
		board.set(0, 2, 8);
		board.set(0, 5, 9);
		board.set(0, 7, 6);
		board.set(0, 8, 2);

		// rad 1
		board.set(1, 8, 5);

		// rad 2
		board.set(2, 0, 1);
		board.set(2, 2, 2);
		board.set(2, 3, 5);

		// rad 3
		board.set(3, 3, 2);
		board.set(3, 4, 1);
		board.set(3, 7, 9);

		// rad 4
		board.set(4, 1, 5);
		board.set(4, 6, 6);

		// rad 5
		board.set(5, 0, 6);
		board.set(5, 7, 2);
		board.set(5, 8, 8);

		// rad 6
		board.set(6, 0, 4);
		board.set(6, 1, 1);
		board.set(6, 3, 6);
		board.set(6, 5, 8);

		// rad 7
		board.set(7, 0, 8);
		board.set(7, 1, 6);
		board.set(7, 4, 3);
		board.set(7, 6, 1);

		// rad 8
		board.set(8, 6, 4);
	}

}
