package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aadl2ocra.utils.PortUtils;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddTerm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTerm frame = new AddTerm();
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
	public AddTerm() {
		setTitle("\u6DFB\u52A0\u7B97\u672F\u8868\u8FBE\u5F0F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label.setBounds(43, 38, 93, 18);
		contentPane.add(label);
		
		JComboBox<String> cbb_term = new JComboBox<String>();
		cbb_term.setBounds(43, 82, 129, 18);
		contentPane.add(cbb_term);
		
		JComboBox<String> cbb_op = new JComboBox<String>();
		cbb_op.setBounds(186, 82, 74, 18);
		contentPane.add(cbb_op);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_1.setBounds(284, 38, 101, 18);
		contentPane.add(label_1);
		
		JComboBox<String> cbb_term2 = new JComboBox<String>();
		cbb_term2.setBounds(274, 82, 129, 18);
		contentPane.add(cbb_term2);
		
		JLabel label_2 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_2.setBounds(43, 137, 93, 18);
		contentPane.add(label_2);
		
		JComboBox<String> cbb_term3 = new JComboBox<String>();
		cbb_term3.setBounds(43, 168, 129, 18);
		contentPane.add(cbb_term3);
		
		JComboBox<String> cbb_op2 = new JComboBox<String>();
		cbb_op2.setBounds(186, 168, 74, 18);
		contentPane.add(cbb_op2);
		
		JLabel label_3 = new JLabel("\u8F93\u5165\u5E38\u6570");
		label_3.setBounds(284, 137, 72, 18);
		contentPane.add(label_3);
		init(cbb_term,cbb_term2,cbb_term3,cbb_op,cbb_op2);
		textField = new JTextField();
		textField.setBounds(274, 165, 129, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setBounds(290, 213, 113, 27);
		contentPane.add(button);
	}
	public void init(JComboBox<String> comboBox,JComboBox<String> comboBox1,JComboBox<String> comboBox2,JComboBox<String> comboBox3,JComboBox<String> comboBox4) {
		comboBox.removeAllItems();
		comboBox1.removeAllItems();
		comboBox2.removeAllItems();
		for(String temp : AddContract.term) {
			comboBox.addItem(temp);
			comboBox1.addItem(temp);
			comboBox2.addItem(temp);
		}
		comboBox3.addItem("+");
		comboBox3.addItem("-");
		comboBox3.addItem("*");
		comboBox3.addItem("/");
		comboBox4.addItem("+");
		comboBox4.addItem("-");
		comboBox4.addItem("*");
		comboBox4.addItem("/");
	}
}
