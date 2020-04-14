package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aadl2ocra.utils.ContractUtils;
import aadl2ocra.utils.PortUtils;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddContract extends JFrame {

	private JPanel contentPane;
	private JTextField txf_name;
	private JLabel lbl_assume;
	private JScrollPane scrollPane;
	public static ArrayList<String> atom = new ArrayList<String>();
	public static ArrayList<String> term = new ArrayList<String>();
	public static ArrayList<String> constraint = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContract frame = new AddContract(null,null,null,null);
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
	public AddContract(String name,ArrayList<ContractUtils> contractList,ArrayList<PortUtils> portList,ArrayList<String> subList) {
		init(portList);
		setTitle("Ìí¼Ó"+name+"µÄÆõÔ¼");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_name = new JLabel("\u5951\u7EA6\u540D\u79F0");
		lbl_name.setBounds(39, 13, 72, 18);
		contentPane.add(lbl_name);
		
		txf_name = new JTextField();
		txf_name.setBounds(112, 10, 365, 24);
		contentPane.add(txf_name);
		txf_name.setColumns(10);
		
		lbl_assume = new JLabel("\u5047\u8BBE");
		lbl_assume.setBounds(39, 55, 72, 18);
		contentPane.add(lbl_assume);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 86, 438, 168);
		contentPane.add(scrollPane);
		
		JTextArea txa_assume = new JTextArea();
		txa_assume.setEditable(false);
		scrollPane.setViewportView(txa_assume);
		
		JLabel lbl_refinedby = new JLabel("\u4FDD\u8BC1");
		lbl_refinedby.setBounds(39, 267, 72, 18);
		contentPane.add(lbl_refinedby);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(39, 298, 438, 168);
		contentPane.add(scrollPane_1);
		
		JTextArea txa_guarantee = new JTextArea();
		txa_guarantee.setEditable(false);
		scrollPane_1.setViewportView(txa_guarantee);
		
		JLabel label_1 = new JLabel("\u6C42\u7CBE\u5206\u89E3");
		label_1.setBounds(39, 494, 72, 18);
		contentPane.add(label_1);
		
		JButton bt_addasumme = new JButton("\u6DFB\u52A0\u7EC6\u8282");
		bt_addasumme.setBounds(112, 51, 113, 27);
		contentPane.add(bt_addasumme);
		
		JButton bt_addguarantee = new JButton("\u6DFB\u52A0\u7EC6\u8282");
		bt_addguarantee.setBounds(112, 263, 113, 27);
		contentPane.add(bt_addguarantee);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		for(String temp : subList)
			comboBox.addItem(temp);
		comboBox.setBounds(112, 493, 113, 21);
		contentPane.add(comboBox);
		
		JButton bt_addsub = new JButton("\u6DFB\u52A0\u5B50\u5951\u7EA6");
		bt_addsub.setBounds(261, 490, 113, 27);
		contentPane.add(bt_addsub);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(39, 544, 438, 118);
		contentPane.add(scrollPane_2);
		
		JTextArea txa_refinedby = new JTextArea();
		txa_refinedby.setEditable(false);
		scrollPane_2.setViewportView(txa_refinedby);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(530, 55, 397, 168);
		contentPane.add(scrollPane_3);
		
		JTextArea txa_term = new JTextArea();
		txa_term.setEditable(false);
		scrollPane_3.setViewportView(txa_term);
		
		JLabel lbl_term = new JLabel("\u7B97\u672F\u8868\u8FBE\u5F0F\u5217\u8868");
		lbl_term.setBounds(530, 13, 113, 18);
		contentPane.add(lbl_term);
		
		JButton bt_addterm = new JButton("\u6DFB\u52A0\u7B97\u672F\u8868\u8FBE\u5F0F");
		bt_addterm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddTerm at = new AddTerm();
				at.setVisible(true);
			}
		});
		bt_addterm.setBounds(790, 9, 137, 27);
		contentPane.add(bt_addterm);
		
		JLabel lbl_atom = new JLabel("\u5E03\u5C14\u8868\u8FBE\u5F0F\u5217\u8868");
		lbl_atom.setBounds(530, 236, 113, 18);
		contentPane.add(lbl_atom);
		
		JButton bt_addatom = new JButton("\u6DFB\u52A0\u5E03\u5C14\u8868\u8FBE\u5F0F");
		bt_addatom.setBounds(790, 232, 137, 27);
		contentPane.add(bt_addatom);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(530, 272, 397, 168);
		contentPane.add(scrollPane_4);
		
		JTextArea txa_atom = new JTextArea();
		txa_atom.setEditable(false);
		scrollPane_4.setViewportView(txa_atom);
		
		JLabel lbl_constraint = new JLabel("\u7EA6\u675F\u5217\u8868");
		lbl_constraint.setBounds(530, 453, 113, 18);
		contentPane.add(lbl_constraint);
		
		JButton bt_addconstraint = new JButton("\u6DFB\u52A0\u7EA6\u675F");
		bt_addconstraint.setBounds(790, 449, 137, 27);
		contentPane.add(bt_addconstraint);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(530, 494, 397, 168);
		contentPane.add(scrollPane_5);
		
		JTextArea txa_constraint = new JTextArea();
		txa_constraint.setEditable(false);
		scrollPane_5.setViewportView(txa_constraint);
		
		JButton bt_addcontract = new JButton("\u6DFB\u52A0\u5951\u7EA6");
		bt_addcontract.setBounds(442, 683, 113, 27);
		contentPane.add(bt_addcontract);
	}
	public void init(ArrayList<PortUtils> portList) {
		term.clear();
		atom.clear();
		constraint.clear();
		for(PortUtils port : portList) {
			term.add(port.getName());
		}
		atom.add("TRUE");
		atom.add("FALSE");
	}
}
