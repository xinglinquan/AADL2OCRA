package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class WaitMessage extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WaitMessage dialog = new WaitMessage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WaitMessage() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("\u9A8C\u8BC1\u4E2D");
		setVisible(true);
		setBounds(100, 100, 450, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u6B63\u5728\u9A8C\u8BC1\uFF0C\u8BF7\u7A0D\u7B49......");
		label.setBounds(132, 13, 228, 65);
		contentPanel.add(label);
	}
}
