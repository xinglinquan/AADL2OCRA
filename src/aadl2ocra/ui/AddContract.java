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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddContract extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_assume;
	private JScrollPane scrollPane;
	public static ArrayList<String> atom = new ArrayList<String>();
	public static ArrayList<String> term = new ArrayList<String>();
	public static ArrayList<String> constraint = new ArrayList<String>();
	public static JTextField txf_name;
	public static JTextArea txa_assume;
	public static JTextArea txa_guarantee;
	public static JTextArea txa_refinedby;
	public static JTextArea txa_term;
	public static JTextArea txa_atom;
	public static JTextArea txa_constraint;
	private JComboBox<String> comboBox;
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
		
		setTitle("添加"+name+"的契约");
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
		
		txa_assume = new JTextArea();
		txa_assume.setEditable(false);
		scrollPane.setViewportView(txa_assume);
		
		JLabel lbl_refinedby = new JLabel("\u4FDD\u8BC1");
		lbl_refinedby.setBounds(39, 267, 72, 18);
		contentPane.add(lbl_refinedby);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(39, 298, 438, 168);
		contentPane.add(scrollPane_1);
		
		txa_guarantee = new JTextArea();
		txa_guarantee.setEditable(false);
		scrollPane_1.setViewportView(txa_guarantee);
		
		JLabel label_1 = new JLabel("\u6C42\u7CBE\u5206\u89E3");
		label_1.setBounds(39, 494, 72, 18);
		contentPane.add(label_1);
		
		comboBox = new JComboBox<String>();

		comboBox.setBounds(112, 493, 113, 21);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(39, 544, 438, 118);
		contentPane.add(scrollPane_2);
		
		txa_refinedby = new JTextArea();
		txa_refinedby.setEditable(false);
		scrollPane_2.setViewportView(txa_refinedby);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(530, 55, 397, 168);
		contentPane.add(scrollPane_3);
		
		txa_term = new JTextArea();
		txa_term.setEditable(false);
		scrollPane_3.setViewportView(txa_term);
		
		JLabel lbl_term = new JLabel("\u7B97\u672F\u8868\u8FBE\u5F0F\u5217\u8868");
		lbl_term.setBounds(530, 13, 113, 18);
		contentPane.add(lbl_term);
		
		JLabel lbl_atom = new JLabel("\u5E03\u5C14\u8868\u8FBE\u5F0F\u5217\u8868");
		lbl_atom.setBounds(530, 236, 113, 18);
		contentPane.add(lbl_atom);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(530, 272, 397, 168);
		contentPane.add(scrollPane_4);
		
		txa_atom = new JTextArea();
		txa_atom.setEditable(false);
		scrollPane_4.setViewportView(txa_atom);
		
		JLabel lbl_constraint = new JLabel("\u7EA6\u675F\u5217\u8868");
		lbl_constraint.setBounds(530, 453, 113, 18);
		contentPane.add(lbl_constraint);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(530, 494, 397, 168);
		contentPane.add(scrollPane_5);
		
		txa_constraint = new JTextArea();
		txa_constraint.setEditable(false);
		scrollPane_5.setViewportView(txa_constraint);
		init(portList,subList);
		
		JButton bt_addasumme = new JButton("\u6DFB\u52A0\u5185\u5BB9");
		bt_addasumme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAssume aa = new AddAssume();
				aa.setVisible(true);
			}
		});
		bt_addasumme.setBounds(239, 51, 113, 27);
		contentPane.add(bt_addasumme);
		
		JButton bt_addguarantee = new JButton("\u6DFB\u52A0\u5185\u5BB9");
		bt_addguarantee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGuarantee ag = new AddGuarantee();
				ag.setVisible(true);
			}
		});
		
		JButton bt_delassume = new JButton("\u6E05\u7A7A");
		bt_delassume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txa_assume.setText("");
			}
		});
		bt_delassume.setBounds(364, 51, 113, 27);
		contentPane.add(bt_delassume);
		bt_addguarantee.setBounds(239, 263, 113, 27);
		contentPane.add(bt_addguarantee);
		
		JButton bt_addsub = new JButton("\u6DFB\u52A0\u5B50\u5951\u7EA6");
		bt_addsub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString()!="")
					if(txa_refinedby.getText().toString().isEmpty())
						txa_refinedby.append(comboBox.getSelectedItem().toString());
					else
						txa_refinedby.append(","+comboBox.getSelectedItem().toString());
			}
		});
		
		JButton bt_delguarantee = new JButton("\u6E05\u7A7A");
		bt_delguarantee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txa_guarantee.setText("");
			}
		});
		bt_delguarantee.setBounds(364, 263, 113, 27);
		contentPane.add(bt_delguarantee);
		bt_addsub.setBounds(239, 490, 113, 27);
		contentPane.add(bt_addsub);
		
		JButton bt_addterm = new JButton("\u6DFB\u52A0\u7B97\u672F\u8868\u8FBE\u5F0F");
		bt_addterm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddTerm at = new AddTerm();
				at.setVisible(true);
			}
		});
		
		JButton bt_delrefinedby = new JButton("\u6E05\u7A7A");
		bt_delrefinedby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txa_refinedby.setText("");
			}
		});
		bt_delrefinedby.setBounds(366, 490, 113, 27);
		contentPane.add(bt_delrefinedby);
		bt_addterm.setBounds(663, 9, 137, 27);
		contentPane.add(bt_addterm);
		
		JButton bt_addatom = new JButton("\u6DFB\u52A0\u5E03\u5C14\u8868\u8FBE\u5F0F");
		bt_addatom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddAtom aa = new AddAtom();
				aa.setVisible(true);
			}
		});
		
		JButton bt_delterm = new JButton("\u6E05\u7A7A");
		bt_delterm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txa_term.setText("");
				term.clear();
				for(PortUtils port : portList) {
					term.add(port.getName());
					txa_term.append(port.getName()+"\n");
				}
			}
		});
		bt_delterm.setBounds(814, 9, 113, 27);
		contentPane.add(bt_delterm);
		bt_addatom.setBounds(663, 232, 137, 27);
		contentPane.add(bt_addatom);
		
		JButton bt_addconstraint = new JButton("\u6DFB\u52A0\u7EA6\u675F");
		bt_addconstraint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddConstraint ac = new AddConstraint();
				ac.setVisible(true);
			}
		});
		
		JButton bt_delatom = new JButton("\u6E05\u7A7A");
		bt_delatom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txa_atom.setText("");
				atom.clear();
				atom.add("TRUE");
				txa_atom.append("TRUE\n");
				atom.add("FALSE");
				txa_atom.append("FALSE\n");
				for(String temp : term) {
					atom.add(temp);
					txa_atom.append(temp+"\n");
				}
			}
		});
		bt_delatom.setBounds(814, 232, 113, 27);
		contentPane.add(bt_delatom);
		bt_addconstraint.setBounds(663, 449, 137, 27);
		contentPane.add(bt_addconstraint);
		JButton bt_addcontract = new JButton("\u6DFB\u52A0\u5951\u7EA6");
		bt_addcontract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txf_name.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "契约名称不能为空！", "", JOptionPane.ERROR_MESSAGE);
				if(txa_assume.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "假设不能为空！", "", JOptionPane.ERROR_MESSAGE);
				if(txa_guarantee.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "保证不能为空！", "", JOptionPane.ERROR_MESSAGE);
				StringBuilder contract =  new StringBuilder();
				String contractname = txf_name.getText().toString();
				String assume = txa_assume.getText().toString();
				String guarantee = txa_guarantee.getText().toString();
				String refinedby = "";
				if(!txa_refinedby.getText().toString().isEmpty())
					refinedby = "CONTRACT "+contractname+" REFINEDBY "+txa_refinedby.getText().toString();
				ContractUtils contractUtils = new ContractUtils(contractname,assume,guarantee,refinedby);
				ArrayList<ContractUtils> contractList;
				if(Start.ContractMap.containsKey(name)) {
					System.out.println(name+"yes");
					contractList = Start.ContractMap.get(name);
				}else {
					System.out.println(name+"no");
					contractList = new ArrayList<ContractUtils>();
				}
				contractList.add(contractUtils);
				Start.ContractMap.put(name, contractList);
				if(!findProperties(name))
					contract.append("\t\t"+"properties"+"\n");
				contract.append("\t\t\t"+"OCRA::Contract=>\"\n");
				for(ContractUtils contractutils : contractList) {
					contract.append("\t\t\t\t"+"CONTRACT "+contractutils.getContractName()+"\n");
					contract.append("\t\t\t\t"+"assume: "+contractutils.getAssume()+";\n");
					contract.append("\t\t\t\t"+"guarantee: "+contractutils.getGuarantee()+";\n");
				}
				contract.append("\t\t\t\t"+"\";\n");
				boolean judge=false;//判断是否需要RefinedBy
				for(ContractUtils contractutils : contractList) {
					if(!contractutils.getRefinedby().isEmpty()) {
						contract.append("\t\t\t"+"OCRA::RefinedBy=>\"\n");
						judge=true;
						break;
					}
				}
				if(judge) {
					for(ContractUtils contractutils : contractList) {
						if(!contractutils.getRefinedby().isEmpty()) {
							contract.append("\t\t\t\t"+contractutils.getRefinedby()+";\n");
						}
					}
					contract.append("\t\t\t\t"+"\";\n");
				}
				System.out.println("contract:"+contract.toString());
				removeAllcontract(name);
				try {
					insertContract(contract.toString(),name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txf_name.setText("");
				txa_assume.setText("");
				txa_guarantee.setText("");
				txa_refinedby.setText("");
				JOptionPane.showMessageDialog(null, "添加成功");
			}
		});
		
		JButton bt_delconstraint = new JButton("\u6E05\u7A7A");
		bt_delconstraint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txa_constraint.setText("");
				constraint.clear();
				for(String temp : atom) {
					constraint.add(temp);
					txa_constraint.append(temp+"\n");
				}
			}
		});
		bt_delconstraint.setBounds(814, 449, 113, 27);
		contentPane.add(bt_delconstraint);
		bt_addcontract.setBounds(364, 683, 113, 27);
		contentPane.add(bt_addcontract);
		
		JButton bt_delall = new JButton("\u5168\u90E8\u6E05\u7A7A");
		bt_delall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init(portList,subList);
			}
		});
		bt_delall.setBounds(530, 683, 113, 27);
		contentPane.add(bt_delall);

	}
	public void init(ArrayList<PortUtils> portList,ArrayList<String> subList) {
		term.clear();
		atom.clear();
		constraint.clear();
		txf_name.setText("");
		txa_assume.setText("");
		txa_guarantee.setText("");
		txa_refinedby.setText("");
		txa_term.setText("");
		txa_atom.setText("");
		txa_constraint.setText("");
		for(PortUtils port : portList) {
			term.add(port.getName());
			txa_term.append(port.getName()+"\n");
		}
		atom.add("TRUE");
		txa_atom.append("TRUE\n");
		atom.add("FALSE");
		txa_atom.append("FALSE\n");
		for(String temp : term) {
			atom.add(temp);
			txa_atom.append(temp+"\n");
		}
		for(String temp : atom) {
			constraint.add(temp);
			txa_constraint.append(temp+"\n");
		}
		comboBox.addItem("");
		for(String temp : subList)
			comboBox.addItem(temp);
	}
	public static boolean findProperties(String name) {
		String target1="implementation "+name+".impl";
		String target2="end "+name+".impl";
		int length = Start.aadlContentList.size();
		int start=0;
		int end=0;
		for(int i=0;i<length;i++)
		{
			if(Start.aadlContentList.get(i).indexOf(target1)!=-1)
			{
				start=i;
				break;
			}
		}
		for(int i=0;i<length;i++)
		{
			if(Start.aadlContentList.get(i).indexOf(target2)!=-1)
			{
				end=i;
				break;
			}
		}
		boolean judge=false;
		for(int i =start;i<end;i++) {
			if(Start.aadlContentList.get(i).indexOf("properties")!=-1) {
				judge=true;
				break;
			}
		}
		return judge;
	}
	public static void removeAllcontract(String name) {
		String target1="implementation "+name+".impl";
		String target2="end "+name+".impl";
		int length = Start.aadlContentList.size();
		System.out.println("before:"+length);
		int start=0;
		int end=0;
		int pos_pro=0;
		int pos_ocra=0;
		boolean judge=false;
		for(int i=0;i<length;i++){
			if(Start.aadlContentList.get(i).indexOf(target1)!=-1){
				start=i;
				break;
			}
		}
		for(int i=0;i<length;i++){
			if(Start.aadlContentList.get(i).indexOf(target2)!=-1){
				end=i;
				break;
			}
		}
		for(int i =start;i<=end;i++) {
			if(Start.aadlContentList.get(i).indexOf("properties")!=-1)
				pos_pro=i;
			if(Start.aadlContentList.get(i).indexOf("OCRA::Contract=>")!=-1)
				pos_ocra=i;
		}
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		System.out.println("pos_pro:"+pos_pro);
		System.out.println("pos_ocra:"+pos_ocra);
		if(pos_pro==(pos_ocra-1))
			judge=true;
		if(judge&&pos_pro!=0&&pos_ocra!=0) {
			for(int i =pos_pro+1;i<end;i++) {
				System.out.println("aaa");
				Start.aadlContentList.remove(i);
				end--;
				i--;
			}
		}
		else if(!judge&&pos_pro!=0&&pos_ocra!=0){
			for(int i =pos_ocra;i<end;i++) {
				System.out.println("bbbb");
				Start.aadlContentList.remove(Start.aadlContentList.get(i));
				end--;
				i--;
			}
		}
		System.out.println("after:"+Start.aadlContentList.size());
	}
	public static void insertContract(String contract,String name) throws IOException {
		String target = "end "+name+".impl";
		int length = Start.aadlContentList.size();
		for(int i=0;i<length;i++)
		{
			if(Start.aadlContentList.get(i).indexOf(target)!=-1)
			{
				System.out.println("Find! "+ target + " " + i);
				Start.aadlContentList.add(i, contract);
				break;
			}
		}
//		for(String s:aadlContentList)
//		{
//			System.out.println(s);
//		}
		System.out.println("file:"+Start.filePath);
		FileWriter fw;
		try {
			fw = new FileWriter(new File(Start.filePath));
			for(String s: Start.aadlContentList)
			{
				fw.write(s+"\n");
				//System.out.println(s);
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Start.aadlContentList.clear();
		Start.loadAADL2List();
	}
}
