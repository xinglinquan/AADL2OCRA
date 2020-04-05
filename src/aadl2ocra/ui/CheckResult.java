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

public class CheckResult extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckResult frame = new CheckResult(null,null);
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
	public CheckResult(List<String> result,List<String> error) {
		setTitle("验证结果");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("验证信息");
		label.setBounds(260, 13, 89, 40);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 66, 560, 630);
		contentPane.add(scrollPane);
		
		JTextArea txa_result = new JTextArea();
		txa_result.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txa_result.setEditable(false);
		scrollPane.setViewportView(txa_result);
		for(int i =18;i<result.size();i++) {
			txa_result.append(result.get(i)+"\n");
		}
		
		JLabel label_1 = new JLabel("错误信息");
		label_1.setBounds(861, 19, 89, 29);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("退出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(608, 66, 560, 630);
		contentPane.add(scrollPane_1);
		
		JTextArea txa_error = new JTextArea();
		txa_error.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txa_error.setEditable(false);
		scrollPane_1.setViewportView(txa_error);
		for(int i =0;i<error.size();i++) {
			txa_error.append(error.get(i)+"\n");
		}
		btnNewButton.setBounds(535, 713, 113, 27);
		contentPane.add(btnNewButton);		
	}
}
