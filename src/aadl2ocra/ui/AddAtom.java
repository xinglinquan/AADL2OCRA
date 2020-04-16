package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAtom extends JFrame {

	private JPanel contentPane;
	private JLabel lblTimeutil;
	private JComboBox<String> cbb_term;
	private JComboBox<String> cbb_op;
	private JComboBox<String> cbb_term2;
	private JComboBox<String> cbb_term3;
	private JComboBox<String> cbb_op2;
	private JComboBox<String> cbb_term4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAtom frame = new AddAtom();
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
	public AddAtom() {
		setTitle("\u6DFB\u52A0\u5E03\u5C14\u8868\u8FBE\u5F0F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 514, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label.setBounds(112, 25, 93, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_1.setBounds(359, 25, 93, 18);
		contentPane.add(label_1);
		
		cbb_term = new JComboBox<String>();
		cbb_term.setBounds(108, 69, 129, 18);
		contentPane.add(cbb_term);
		
		cbb_op = new JComboBox<String>();
		cbb_op.setBounds(251, 69, 74, 18);
		contentPane.add(cbb_op);
		
		cbb_term2 = new JComboBox<String>();
		cbb_term2.setBounds(339, 69, 129, 18);
		contentPane.add(cbb_term2);
		
		lblTimeutil = new JLabel("time_until");
		lblTimeutil.setBounds(14, 151, 86, 18);
		contentPane.add(lblTimeutil);
		
		JLabel label_2 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_2.setBounds(112, 114, 93, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u9009\u62E9\u8868\u8FBE\u5F0F");
		label_3.setBounds(359, 114, 93, 18);
		contentPane.add(label_3);
		
		cbb_term3 = new JComboBox<String>();
		cbb_term3.setBounds(108, 151, 129, 18);
		contentPane.add(cbb_term3);
		
		cbb_op2 = new JComboBox<String>();
		cbb_op2.setBounds(251, 151, 74, 18);
		contentPane.add(cbb_op2);
		
		cbb_term4 = new JComboBox<String>();
		cbb_term4.setBounds(339, 151, 129, 18);
		contentPane.add(cbb_term4);
		init();
		JButton bt_addatom = new JButton("\u6DFB\u52A0");
		bt_addatom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_term.getSelectedItem().toString()!=""&&cbb_term2.getSelectedItem().toString()!=""&&cbb_op.getSelectedItem().toString()!="") {
					String term = cbb_term.getSelectedItem().toString()+cbb_op.getSelectedItem().toString()+cbb_term2.getSelectedItem().toString();
					AddContract.atom.add(term);
					AddContract.constraint.add(term);
					AddContract.txa_atom.append(term+"\n");
					AddContract.txa_constraint.append(term+"\n");
				}
				if(cbb_term3.getSelectedItem().toString()!=""&&cbb_term4.getSelectedItem().toString()!=""&&cbb_op2.getSelectedItem().toString()!="") {
					String term = "time_until("+cbb_term3.getSelectedItem().toString()+")"+cbb_op2.getSelectedItem().toString()+cbb_term4.getSelectedItem().toString();
					AddContract.atom.add(term);
					AddContract.constraint.add(term);
					AddContract.txa_atom.append(term+"\n");
					AddContract.txa_constraint.append(term+"\n");
				}
				cbb_term.setSelectedIndex(0);
				cbb_term2.setSelectedIndex(0);
				cbb_term3.setSelectedIndex(0);
				cbb_term4.setSelectedIndex(0);
				cbb_op.setSelectedIndex(0);
				cbb_op2.setSelectedIndex(0);
				JOptionPane.showMessageDialog(null, "Ìí¼Ó³É¹¦");
			}
		});
		bt_addatom.setBounds(339, 196, 113, 27);
		contentPane.add(bt_addatom);
	}
	public void init() {
		cbb_term.removeAllItems();
		cbb_term2.removeAllItems();
		cbb_term3.removeAllItems();
		cbb_term4.removeAllItems();
		cbb_term.addItem("");
		cbb_term2.addItem("");
		cbb_term3.addItem("");
		cbb_term4.addItem("");
		cbb_op.addItem("");
		cbb_op2.addItem("");
		for(String temp : AddContract.term) {
			cbb_term.addItem(temp);
			cbb_term2.addItem(temp);
			cbb_term3.addItem(temp);
			cbb_term4.addItem(temp);
		}
		cbb_op.addItem("=");
		cbb_op.addItem("!=");
		cbb_op.addItem("<");
		cbb_op.addItem(">");
		cbb_op.addItem("<=");
		cbb_op.addItem(">=");
		cbb_op2.addItem("=");
		cbb_op2.addItem("!=");
		cbb_op2.addItem("<");
		cbb_op2.addItem(">");
		cbb_op2.addItem("<=");
		cbb_op2.addItem(">=");
	}
}
