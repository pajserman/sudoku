package sudoku;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Custom button for the sudoku GUI.
 */
public class MyButton extends JButton {
	/**
	 * Creates a JButton with custom design and title.
	 * @param name The title of the button.
	 */
	public MyButton(String name) {
		super(name);
		this.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createLineBorder(Color.WHITE, 20)));

	}
}
