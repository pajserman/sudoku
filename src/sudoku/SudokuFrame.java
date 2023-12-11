package sudoku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * A frame that acts as a interpreter between the SudokuBoard and the GUI
 */
public class SudokuFrame extends JFrame implements ActionListener {
	/**
	 * A button that triggers Solve
	 */
	private JButton solve;
	/**
	 * A button that triggers Clear
	 */
	private JButton clear;
	/**
	 * A SudokuBoard object
	 */
	private SudokuBoard board;
	/**
	 * 2D Array that contains the textinput boxes
	 */
	private MyTextField[][] boxes;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a SudokuFrame with a connected {@link SudokuBoard}.
	 * 
	 * @param board A Sudokuboard Object.
	 * 
	 * @see SudokuBoard
	 * 
	 */
	public SudokuFrame(SudokuBoard board) {
		SwingUtilities.invokeLater(() -> createWindow(board));
	}
	
	private void createWindow(SudokuBoard board) {
		this.board = board;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("Sudoku Solver");
		this.setIconImage(null);
		this.setSize(500, 600);
		
		loadComponents();
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void loadComponents() {
		matrix();
		controll();
	}

	private void matrix() {
		JPanel matrix = new JPanel();
		matrix.setPreferredSize(new Dimension(500, 500));

		// skapar en vektor med boxes som motsvarar boxes i API
		boxes = new MyTextField[9][9];

		// skapar nya textfält och lägger till dem i vektorn
		// varje ruta får ett index (första = 0 sista = 80)
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boxes[i][j] = new MyTextField(i * 9 + j);
			}
		}

		// lägger till all boxes i GUI
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				matrix.add(boxes[i][j]);
			}
		}

		matrix.setLayout(new GridLayout(9, 9, 5, 5));
		matrix.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.add(matrix, BorderLayout.CENTER);
	}

	private void controll() {
		JPanel control = new JPanel();
		control.setPreferredSize(new Dimension(500, 100));
		control.setLayout(new FlowLayout());

		clear = new MyButton("Clear");
		solve = new MyButton("Solve");

		clear.addActionListener(this);
		solve.addActionListener(this);

		control.add(clear);
		control.add(solve);

		this.add(control, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clear) {
			clearBoard();
		}
		if (e.getSource() == solve) {
			// checkValidInput garanterar att siffrorna som skickas till board är giltiga.
			if (checkValidInput()) {
				board.clearAll();
				int[][] numbers = getNumbersFromGUI();
				board.setGrid(numbers);
				if (board.solve()) {
					updateGUI();
				} else {
					String emoji = new String(Character.toChars(0x1F622));
					String message = "Det finns tyvärr ingen lösning " + emoji;
					JOptionPane.showMessageDialog(null, message, "No Solution", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}

	private int[][] getNumbersFromGUI() {
		int[][] ans = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int k = 0;
				// om lådan tom sätts den till 0
				if (boxes[i][j].getText().equals("")) {
					k = 0;
				} else {
					k = Integer.parseUnsignedInt(boxes[i][j].getText());
				}
				ans[i][j] = k;
			}
		}

		return ans;

	}

	private void clearBoard() {
		// rensar board
		board.clearAll();
		updateGUI();
	}

	/**
	 * Försöker läsa alla rutona som användaren fyllt i.
	 * 
	 * @return false om användaren försökt skriva in text eller annat felaktigt.
	 */
	private boolean checkValidInput() {
		// loopar igenom alla rutona och kollar så att
		// 1. värden större än 0 (0 räknas som tom), mindre än 10
		// 2. ingen text eller decimaltal

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					int k = 0;
					// om lådan tom sätts den till 0
					if (boxes[i][j].getText().equals("")) {
						k = 0;
					} else {
						k = Integer.parseUnsignedInt(boxes[i][j].getText());
					}

					if (k > 9 || k < 0) {
						System.err.println("oops!" + boxes[i][j].getText() + i + ":" + j);
						String message = "Felaktig input " + boxes[i][j].getText() + " [" + i + ":" + j + "]! Skriv in en siffra i intervallet 1-9 !";
						JOptionPane.showMessageDialog(null, message, "Error input", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				} catch (Exception e) {
					System.err.println("oops!" + boxes[i][j].getText() + i + ":" + j);
					String message = "Felaktig input " + boxes[i][j].getText() + " [" + i + ":" + j + "]! Skriv in en siffra i intervallet 1-9 !";
					JOptionPane.showMessageDialog(null, message, "Error input", JOptionPane.ERROR_MESSAGE);

					return false;
				}

			}
		}
		return true;

	}

	/**
	 * Uppdaterar alla boxes i GUI med de verkliga från SudokuBoard objektet
	 * 
	 */
	private void updateGUI() {
		int[][] boardNumbers = board.getGrid();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// om siffran är 0 så sätts rutan till tom
				if (boardNumbers[i][j] == 0) {
					boxes[i][j].setText(null);
					// boxes[i][j].setText("0");
				} else {
					boxes[i][j].setText(Integer.toString(boardNumbers[i][j]));
				}
			}
		}
	}

}
