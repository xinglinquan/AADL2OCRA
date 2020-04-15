package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddConstraint extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbb_op;
	private JComboBox<String> cbb_con2;
	private JComboBox<String> cbb_con1;
	private JComboBox<String> cbb_op2;
	private JComboBox<String> cbb_con3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddConstraint frame = new AddConstraint();
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
	public AddConstraint() {
		setTitle("\u6DFB\u52A0\u7EA6\u675F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1_1 = new JLabel("\u9009\u62E9\u7EA6\u675F");
		label_1_1.setBounds(44, 31, 72, 18);
		contentPane.add(label_1_1);
		
		JLabel label = new JLabel("\u9009\u62E9\u8FD0\u7B97\u7B26");
		label.setBounds(177, 31, 95, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u7EA6\u675F");
		label_1.setBounds(299, 31, 72, 18);
		contentPane.add(label_1);
		
		cbb_con1 = new JComboBox<String>();
		cbb_con1.setBounds(44, 62, 119, 18);
		contentPane.add(cbb_con1);
		
		cbb_op = new JComboBox<String>();
		cbb_op.setBounds(177, 62, 95, 18);
		contentPane.add(cbb_op);
		
		cbb_con2 = new JComboBox<String>();
		cbb_con2.setBounds(299, 62, 119, 18);
		contentPane.add(cbb_con2);
		
		JLabel label_2 = new JLabel("\u9009\u62E9\u8FD0\u7B97\u7B26");
		label_2.setBounds(177, 115, 95, 18);
		contentPane.add(label_2);
		
		JLabel label_1_2 = new JLabel("\u9009\u62E9\u7EA6\u675F");
		label_1_2.setBounds(299, 115, 72, 18);
		contentPane.add(label_1_2);
		
		cbb_op2 = new JComboBox<String>();
		cbb_op2.setBounds(177, 159, 95, 18);
		contentPane.add(cbb_op2);
		
		cbb_con3 = new JComboBox<String>();
		cbb_con3.setBounds(299, 159, 119, 18);
		contentPane.add(cbb_con3);
		init();
		JButton bt_add = new JButton("\u6DFB\u52A0");
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbb_con1.getSelectedItem().toString()!=""&&cbb_con2.getSelectedItem().toString()!=""&&cbb_op.getSelectedItem().toString()!="") {
					String constraint = cbb_con1.getSelectedItem().toString()+cbb_op.getSelectedItem().toString()+cbb_con2.getSelectedItem().toString();
					AddContract.constraint.add(constraint);
					AddContract.txa_constraint.append(constraint+"\n");
					cbb_con1.addItem(constraint);
					cbb_con2.addItem(constraint);
					cbb_con3.addItem(constraint);
				}
				if(cbb_con3.getSelectedItem().toString()!=""&&cbb_op2.getSelectedItem().toString()!="") {
					String constraint = cbb_op2.getSelectedItem().toString()+"("+cbb_con3.getSelectedItem().toString()+")";
					AddContract.constraint.add(constraint);
					AddContract.txa_constraint.append(constraint+"\n");
					cbb_con1.addItem(constraint);
					cbb_con2.addItem(constraint);
					cbb_con3.addItem(constraint);
				}
			}
		});
		bt_add.setBounds(305, 202, 113, 27);
		contentPane.add(bt_add);
	}
	public void init() {
		cbb_op.removeAllItems();
		cbb_con2.removeAllItems();
		cbb_con1.removeAllItems();
		cbb_op2.removeAllItems();
		cbb_con3.removeAllItems();
		cbb_con1.addItem("");
		cbb_con2.addItem("");
		cbb_con3.addItem("");
		cbb_op.addItem("");
		cbb_op2.addItem("");
		for(String temp : AddContract.constraint) {
			cbb_con1.addItem(temp);
			cbb_con2.addItem(temp);
			cbb_con3.addItem(temp);
		}
		cbb_op.addItem(" and ");
		cbb_op.addItem(" xor ");
		cbb_op.addItem(" implies ");
		cbb_op.addItem(" iff ");
		cbb_op.addItem(" until ");
		cbb_op.addItem(" releases ");
		cbb_op.addItem(" since ");
		cbb_op.addItem(" triggered ");
		cbb_op2.addItem("not ");
		cbb_op2.addItem("always ");
		cbb_op2.addItem("never ");
		cbb_op2.addItem("in the future ");
		cbb_op2.addItem("then");
		cbb_op2.addItem("historically ");
		cbb_op2.addItem("in the past ");
		cbb_op2.addItem("previously ");
	}
}
