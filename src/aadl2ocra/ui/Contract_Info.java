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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
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
					Contract_Info frame = new Contract_Info(null,null);
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
	public Contract_Info(String name,ContractUtils contract) {
		setTitle(contract.getContractName()+"的详情");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ContractName = new JLabel("\u5951\u7EA6\u540D\u79F0");
		lbl_ContractName.setBounds(37, 48, 72, 18);
		contentPane.add(lbl_ContractName);
		
		txf_Name = new JTextField();
		txf_Name.setEditable(false);
		txf_Name.setBounds(176, 45, 242, 24);
		txf_Name.setText(replaceBlank(contract.getContractName().toString()));
		contentPane.add(txf_Name);
		txf_Name.setColumns(10);
		
		JLabel lbl_Assume = new JLabel("\u5047\u8BBE");
		lbl_Assume.setBounds(37, 81, 72, 18);
		contentPane.add(lbl_Assume);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 112, 381, 98);
		contentPane.add(scrollPane);
		
		JTextArea txa_Assume = new JTextArea();
		txa_Assume.setEditable(false);
		txa_Assume.setText(replaceBlank(contract.getAssume().toString()));
		scrollPane.setViewportView(txa_Assume);
		
		JLabel lbl_Guarantee = new JLabel("\u4FDD\u8BC1");
		lbl_Guarantee.setBounds(37, 223, 72, 18);
		contentPane.add(lbl_Guarantee);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(37, 258, 381, 98);
		contentPane.add(scrollPane_1);
		
		JTextArea txa_Guarantee = new JTextArea();
		txa_Guarantee.setEditable(false);
		txa_Guarantee.setText(replaceBlank(contract.getGuarantee().toString()));
		scrollPane_1.setViewportView(txa_Guarantee);
		
		JLabel lbl_Refined = new JLabel("\u6C42\u7CBE\u5206\u89E3");
		lbl_Refined.setBounds(37, 396, 72, 18);
		contentPane.add(lbl_Refined);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(37, 427, 381, 78);
		contentPane.add(scrollPane_2);
		
		JTextArea txa_Refined = new JTextArea();
		txa_Refined.setEditable(false);
		if(!contract.getRefinedby().toString().isEmpty())
			txa_Refined.setText(replaceBlank(dealrefinedby(contract.getRefinedby().toString())));
		scrollPane_2.setViewportView(txa_Refined);
		
		JButton bt_Exit = new JButton("\u9000\u51FA");
		bt_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton bt_delete = new JButton("\u5220\u9664\u8BE5\u5951\u7EA6");
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*for(int i =0;i<contractList.size();i++) {
					if(contractList.get(i).getContractName().equals(contract.getContractName())) {
						index=i;
						break;
					}
				}*/
				SearchContract.contractList.remove(contract);
				Start.ContractMap.put(name, SearchContract.contractList);
				StringBuilder contracts =  new StringBuilder();
				if(!AddContract.findProperties(name))
					contracts.append("\t\t"+"properties"+"\n");
				contracts.append("\t\t\t"+"OCRA::Contract=>\"\n");
				for(ContractUtils contractutils : SearchContract.contractList) {
					contracts.append("\t\t\t\t"+"CONTRACT "+contractutils.getContractName()+"\n");
					contracts.append("\t\t\t\t"+"assume: "+contractutils.getAssume()+";\n");
					contracts.append("\t\t\t\t"+"guarantee: "+contractutils.getGuarantee()+";\n");
				}
				contracts.append("\t\t\t\t"+"\";\n");
				boolean judge=false;//判断是否需要RefinedBy
				for(ContractUtils contractutils : SearchContract.contractList) {
					if(!contractutils.getRefinedby().isEmpty()) {
						contracts.append("\t\t\t"+"OCRA::RefinedBy=>\"\n");
						judge=true;
						break;
					}
				}
				if(judge) {
					for(ContractUtils contractutils : SearchContract.contractList) {
						if(!contractutils.getRefinedby().isEmpty()) {
							contracts.append("\t\t\t\t"+contractutils.getRefinedby()+";\n");
						}
					}
					contracts.append("\t\t\t\t"+"\";\n");
				}
				System.out.println("contract:"+contracts.toString());
				AddContract.removeAllcontract(name);
				try {
					AddContract.insertContract(contracts.toString(),name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SearchContract.initcomboBox();
				JOptionPane.showMessageDialog(null, "删除成功");
				dispose();
			}
		});
		bt_delete.setBounds(160, 528, 113, 27);
		contentPane.add(bt_delete);
		bt_Exit.setBounds(160, 568, 113, 27);
		contentPane.add(bt_Exit);
		
	}
	private static String replaceBlank(String str)
	{
		  Pattern pt=Pattern.compile("^\\s*|\\s*$");
		  Matcher mt=pt.matcher(str);
		  str=mt.replaceAll("");
		  return str;
	}
	private static String dealrefinedby(String str) {
		int start = str.indexOf("REFINEDBY");
		return str.substring(start+9, str.length());
	}
}
