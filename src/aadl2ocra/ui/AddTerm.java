package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aadl2ocra.utils.PortUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTerm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> cbb_term;
	private JComboBox<String> cbb_op;
	private JComboBox<String> cbb_term2;
	private JComboBox<String> cbb_term3;
	private JComboBox<String> cbb_op2;

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
		
		cbb_term = new JComboBox<String>();
		cbb_term.setBounds(43, 82, 129, 18);
		contentPane.add(cbb_term);
		
		cbb_op = new JComboBox<String>();
		cbb_op.setBounds(186, 82, 74, 18);
		contentPane.add(cbb_op);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_1.setBounds(284, 38, 101, 18);
		contentPane.add(label_1);
		
		cbb_term2 = new JComboBox<String>();
		cbb_term2.setBounds(274, 82, 129, 18);
		contentPane.add(cbb_term2);
		
		JLabel label_2 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_2.setBounds(43, 137, 93, 18);
		contentPane.add(label_2);
		
		cbb_term3 = new JComboBox<String>();
		cbb_term3.setBounds(43, 168, 129, 18);
		contentPane.add(cbb_term3);
		
		cbb_op2 = new JComboBox<String>();
		cbb_op2.setBounds(186, 168, 74, 18);
		contentPane.add(cbb_op2);
		
		JLabel label_3 = new JLabel("\u8F93\u5165\u5E38\u6570");
		label_3.setBounds(284, 137, 72, 18);
		contentPane.add(label_3);
		init();
		textField = new JTextField();
		textField.setBounds(274, 165, 129, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_term.getSelectedItem().toString()!=""&&cbb_term2.getSelectedItem().toString()!=""&&cbb_op.getSelectedItem().toString()!="") {
					String term = cbb_term.getSelectedItem().toString()+cbb_op.getSelectedItem().toString()+cbb_term2.getSelectedItem().toString();
					AddContract.term.add(term);
					AddContract.atom.add(term);
					AddContract.constraint.add(term);
					AddContract.txa_term.append(term+"\n");
					AddContract.txa_atom.append(term+"\n");
					AddContract.txa_constraint.append(term+"\n");
					cbb_term.addItem(term);
					cbb_term2.addItem(term);
					cbb_term3.addItem(term);
				}
				if(cbb_term3.getSelectedItem().toString()!=""&&textField.toString()!=""&&cbb_op2.getSelectedItem().toString()!="") {
					String term = cbb_term3.getSelectedItem().toString()+cbb_op2.getSelectedItem().toString()+textField.toString();
					AddContract.term.add(term);
					AddContract.atom.add(term);
					AddContract.constraint.add(term);
					AddContract.txa_term.append(term+"\n");
					AddContract.txa_atom.append(term+"\n");
					AddContract.txa_constraint.append(term+"\n");
					cbb_term.addItem(term);
					cbb_term2.addItem(term);
					cbb_term3.addItem(term);
				}
				cbb_term.setSelectedIndex(0);
				cbb_term2.setSelectedIndex(0);
				cbb_term3.setSelectedIndex(0);
				cbb_op.setSelectedIndex(0);
				cbb_op2.setSelectedIndex(0);
				JOptionPane.showMessageDialog(null, "Ìí¼Ó³É¹¦");
			}
		});
		button.setBounds(290, 213, 113, 27);
		contentPane.add(button);
	}
	public void init() {
		cbb_term.removeAllItems();
		cbb_term2.removeAllItems();
		cbb_term3.removeAllItems();
		cbb_term.addItem("");
		cbb_term2.addItem("");
		cbb_term3.addItem("");
		cbb_op.addItem("");
		cbb_op2.addItem("");
		for(String temp : AddContract.term) {
			cbb_term.addItem(temp);
			cbb_term2.addItem(temp);
			cbb_term3.addItem(temp);
		}
		cbb_op.addItem("+");
		cbb_op.addItem("-");
		cbb_op.addItem("*");
		cbb_op.addItem("/");
		cbb_op.addItem(" mod ");
		cbb_op.addItem("[]");
		cbb_op.addItem("<<");
		cbb_op.addItem(">>");
		cbb_op.addItem(" extend ");
		cbb_op.addItem(" resize ");
		cbb_op2.addItem("+");
		cbb_op2.addItem("-");
		cbb_op2.addItem("*");
		cbb_op2.addItem("/");
		cbb_op2.addItem(" mod ");
		cbb_op2.addItem("[]");
		cbb_op2.addItem("<<");
		cbb_op2.addItem(">>");
		cbb_op2.addItem(" extend ");
		cbb_op2.addItem(" resize ");
	}
}
