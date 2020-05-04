package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class ErrorResult extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorResult frame = new ErrorResult(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ErrorResult(List<String> error) {
		setTitle("\u9519\u8BEF\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 507, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u9519\u8BEF\u4FE1\u606F");
		label_1.setBounds(203, 13, 89, 29);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 59, 463, 365);
		contentPane.add(scrollPane_1);
		
		JTextArea txa_error = new JTextArea();
		txa_error.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txa_error.setEditable(false);
		scrollPane_1.setViewportView(txa_error);
		for(int i =0;i<error.size();i++) {
			txa_error.append(error.get(i)+"\n");
		}
		btnNewButton.setBounds(179, 461, 113, 27);
		contentPane.add(btnNewButton);		
	}
}
