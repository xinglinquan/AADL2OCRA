package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aadl2ocra.utils.ContractUtils;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Contract_Info extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contract_Info frame = new Contract_Info(null);
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
	public Contract_Info(ContractUtils contract) {
		setTitle(contract.getContractName()+"的详情");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("契约名称");
		label.setBounds(37, 48, 72, 18);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(176, 45, 242, 24);
		textField.setText(contract.getContractName());
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("假设");
		label_1.setBounds(37, 81, 72, 18);
		contentPane.add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(37, 112, 381, 98);
		textArea.setText(contract.getAssume());
		contentPane.add(textArea);
		
		JLabel label_2 = new JLabel("保证");
		label_2.setBounds(37, 223, 72, 18);
		contentPane.add(label_2);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(37, 260, 381, 123);
		textArea_1.setText(contract.getGuarantee());
		contentPane.add(textArea_1);
		
		JLabel label_3 = new JLabel("求精分解");
		label_3.setBounds(37, 396, 72, 18);
		contentPane.add(label_3);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(37, 427, 381, 78);
		textArea_2.setText(contract.getRefinedby());
		contentPane.add(textArea_2);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(160, 532, 113, 27);
		contentPane.add(button_1);
		
	}
}
