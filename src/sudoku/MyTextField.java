package sudoku;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

/**
 * A custom JTextField for the sudoku GUI that knows which color it should be.
 * 
 */
public class MyTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	/**
	 * Creates a textfield that knows its color based on index.
	 * @param index The index.
	 */
	public MyTextField(int index) {
//		 super(Integer.toString(index));
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setFont(new Font("Consolas", Font.BOLD, 16));
		this.setOpaque(true);
		this.setBorder(null);

		// färgar rutorna
		int c = index % 9;
		int r = index / 9;
		boolean coloredBox1 = (c < 3 || (c > 5)) && (r < 3 || (r > 5));
		boolean coloredBox2 = ((c > 2 && c < 6) && (r > 2 && r < 6));
		if ((coloredBox1 || coloredBox2)) {
			this.setBackground(Color.orange);
		}
	}

}
