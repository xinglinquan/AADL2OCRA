package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.JScrollPane;

public class Contract_Info extends JFrame {

	private JPanel contentPane;
	private JTextField txf_Name;

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
		setBounds(100, 100, 450, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ContractName = new JLabel("契约名称");
		lbl_ContractName.setBounds(37, 48, 72, 18);
		contentPane.add(lbl_ContractName);
		
		txf_Name = new JTextField();
		txf_Name.setEditable(false);
		txf_Name.setBounds(176, 45, 242, 24);
		txf_Name.setText(replaceBlank(contract.getContractName().toString()));
		contentPane.add(txf_Name);
		txf_Name.setColumns(10);
		
		JLabel lbl_Assume = new JLabel("假设");
		lbl_Assume.setBounds(37, 81, 72, 18);
		contentPane.add(lbl_Assume);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 112, 381, 98);
		contentPane.add(scrollPane);
		
		JTextArea txa_Assume = new JTextArea();
		txa_Assume.setEditable(false);
		txa_Assume.setText(replaceBlank(contract.getAssume().toString()));
		scrollPane.setViewportView(txa_Assume);
		
		JLabel lbl_Guarantee = new JLabel("保证");
		lbl_Guarantee.setBounds(37, 223, 72, 18);
		contentPane.add(lbl_Guarantee);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(37, 258, 381, 98);
		contentPane.add(scrollPane_1);
		
		JTextArea txa_Guarantee = new JTextArea();
		txa_Guarantee.setEditable(false);
		txa_Guarantee.setText(replaceBlank(contract.getGuarantee().toString()));
		scrollPane_1.setViewportView(txa_Guarantee);
		
		JLabel lbl_Refined = new JLabel("求精分解");
		lbl_Refined.setBounds(37, 396, 72, 18);
		contentPane.add(lbl_Refined);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(37, 427, 381, 78);
		contentPane.add(scrollPane_2);
		
		JTextArea txa_Refined = new JTextArea();
		txa_Refined.setEditable(false);
		txa_Refined.setText(replaceBlank(contract.getRefinedby().toString()));
		scrollPane_2.setViewportView(txa_Refined);
		
		JButton bt_Exit = new JButton("退出");
		bt_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bt_Exit.setBounds(160, 532, 113, 27);
		contentPane.add(bt_Exit);
		
	}
	private static String replaceBlank(String str)
	{
		  Pattern pt=Pattern.compile("^\\s*|\\s*$");
		  Matcher mt=pt.matcher(str);
		  str=mt.replaceAll("");
		  return str;
	}
}
