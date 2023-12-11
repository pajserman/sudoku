package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tester for debugging
 */
class Tester {
	private SudokuBoard board;
	int m[][];

	@BeforeEach
	void setup() {
		board = new SudokuBoard();
		m = new int[][] { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 }, { 1, 0, 2, 5, 0, 0, 0, 0, 0 },

				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 }, { 6, 0, 0, 0, 0, 0, 0, 2, 8 },

				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 0, 0, 3, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
	}

	@AfterEach
	void tearDown() {
		board = null;
	}

	/**
	 * testar get(), set(), clearAll()
	 */
	@Test
	void testManipulation() {
		// sätter in siffror set()
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board.set(i, j, i);
			}
		}

		// kollar så att de sattes in get()
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(i, board.get(i, j));
			}
		}

		// kollar så att de tas bort clearAll()
		board.clearAll();

		// kollar så att allt blev noll
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(0, board.get(i, j));
			}
		}

	}

	@Test
	void testSolve() {
		// löser ett tomt soduko
		board.clearAll();
		board.solve();
		
		//korrekt lösning för ett tomt board
		int[][] n = {
				{ 1, 2, 3,  4, 5, 6,  7, 8, 9 },
				{ 4, 5, 6,  7, 8, 9,  1, 2, 3 },
				{ 7, 8, 9,  1, 2, 3,  4, 5, 6 }, 

				{ 2, 1, 4,  3, 6, 5,  8, 9, 7 },
				{ 3, 6, 5,  8, 9, 7,  2, 1, 4 },
				{ 8, 9, 7,  2, 1, 4,  3, 6, 5 },

				{ 5, 3, 1,  6, 4, 2,  9, 7, 8 },
				{ 6, 4, 2,  9, 7, 8,  5, 3, 1 },
				{ 9, 7, 8,  5, 3, 1,  6, 4, 2 }};
		
		//testar lösa tomt
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(n[i][j], board.get(i, j));
			}
		}
		
		//testar exempelfallet
		board.clearAll();
		board.setGrid(m);
		board.solve();
		
		//förväntad lösning
		int[][] o = {
				{ 5, 4, 8,  1, 7, 9,  3, 6, 2 },
				{ 3, 7, 6,  8, 2, 4,  9, 1, 5 },
				{ 1, 9, 2,  5, 6, 3,  8, 7, 4 }, 

				{ 7, 8, 4,  2, 1, 6,  5, 9, 3},
				{ 2, 5, 9,  3, 8, 7,  6, 4, 1 },
				{ 6, 3, 1,  9, 4, 5,  7, 2, 8 },

				{ 4, 1, 5,  6, 9, 8,  2, 3, 7 },
				{ 8, 6, 7,  4, 3, 2,  1, 5, 9 },
				{ 9, 2, 3,  7, 5, 1,  4, 8, 6}};
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(o[i][j], board.get(i, j));
			}
		}
		
		//testar olösligt sudoku

		
		//testar samma rad
		board.clearAll();
		board.set(0, 0, 1);
		board.set(0, 1, 1);
		assertFalse(board.solve());
		
		//testar samma column
		board.clearAll();
		board.set(0, 0, 1);
		board.set(1, 0, 1);
		assertFalse(board.solve());
		
		//testar samma 3x3 box
		board.clearAll();
		board.set(0, 0, 1);
		board.set(1, 1, 1);
		assertFalse(board.solve());
		
	}

	@Test // testar automatiskt isValid() också
	void testIsAllValid() {
		// sätter board som i bilden
		board.setGrid(m);

		// kollar om den är giltig
		assertTrue(board.isAllValid(), "Brädet bör vara giltigt");

		// ändrar layout till ogitigt i rad
		board.set(0, 0, 8);
		assertFalse(board.isAllValid(), "Brädet bör vara ogiltigt");

		// ändrar ogiltigt i column
		board.setGrid(m);
		board.set(0, 0, 1);
		assertFalse(board.isAllValid(), "Brädet bör vara ogiltigt");

		// ändrar ogiltigt i 3x3 box
		board.setGrid(m);
		board.set(0, 0, 2);
		assertFalse(board.isAllValid(), "Brädet bör vara ogiltigt");
	}

	@Test
	void TestGetGrid() {
		board.setGrid(m);
		int[][] n = board.getGrid();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(m[i][j], n[i][j]);
			}
		}
	}

	@Test
	void TestSetGrid() {
		board.setGrid(m);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(m[i][j], board.get(i, j));
			}
		}
	}

}
