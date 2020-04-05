package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import aadl2ocra.utils.ContractUtils;
import javax.swing.JScrollPane;
public class Contract extends JFrame {

	private JPanel contentPane;
	public ArrayList<String> aadlContentList = new ArrayList<>();
	public String filePath = null;
	private JTextField txf_Name;
	HashMap<String,ArrayList<ContractUtils>> ContractMap = new HashMap<String,ArrayList<ContractUtils>>();//组件到其契约的映射
	HashMap<String,String> map2 = new HashMap<String,String>();
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contract frame = new Contract(null);
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
	public Contract(String s) {
		filePath =s;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> cbb_Component = new JComboBox<String>();
		cbb_Component.setBounds(24, 13, 189, 24);
		try {
			loadAADL2List();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initcomboBox(cbb_Component);
		contentPane.add(cbb_Component);
		JButton bt_Check = new JButton("查看契约");
		bt_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_Component.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个组件！", "", JOptionPane.ERROR_MESSAGE);
				else {
					ArrayList<ContractUtils> contractList;
					if(ContractMap.containsKey(cbb_Component.getSelectedItem().toString())) {
						contractList = ContractMap.get(cbb_Component.getSelectedItem().toString());
					}else {
						contractList = new ArrayList<ContractUtils>();
					}
					SearchContract searchContract = new SearchContract(cbb_Component.getSelectedItem().toString(),contractList);
					searchContract.setVisible(true);
				}
			}
		});
		bt_Check.setBounds(305, 13, 113, 27);
		contentPane.add(bt_Check);
		JLabel lbl_ContractName = new JLabel("契约名称");
		lbl_ContractName.setBounds(24, 63, 107, 18);
		contentPane.add(lbl_ContractName);
		txf_Name = new JTextField();
		txf_Name.setBounds(132, 60, 286, 24);
		contentPane.add(txf_Name);
		txf_Name.setColumns(10);
		JLabel lbl_Assume = new JLabel("假设");
		lbl_Assume.setBounds(24, 94, 72, 18);
		contentPane.add(lbl_Assume);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 125, 394, 84);
		contentPane.add(scrollPane);
		
		JTextArea txa_Assume = new JTextArea();
		scrollPane.setViewportView(txa_Assume);
		JLabel lbl_Guarantee = new JLabel("保证");
		lbl_Guarantee.setBounds(24, 238, 72, 18);
		contentPane.add(lbl_Guarantee);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 269, 394, 84);
		contentPane.add(scrollPane_1);
		
		JTextArea txa_Guarantee = new JTextArea();
		scrollPane_1.setViewportView(txa_Guarantee);
		
		JLabel lbl_Refined = new JLabel("求精分解");
		lbl_Refined.setBounds(24, 366, 89, 18);
		contentPane.add(lbl_Refined);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 389, 394, 84);
		contentPane.add(scrollPane_2);
		
		JTextArea txa_Refined = new JTextArea();
		scrollPane_2.setViewportView(txa_Refined);
		
		for(int i =0;i<cbb_Component.getItemCount();i++) {
			String str = cbb_Component.getItemAt(i);
			searchContract(str);
		}
		for(int i =0;i<cbb_Component.getItemCount();i++) {
			String str = cbb_Component.getItemAt(i);
			searchRefinedBy(str);
		}	
		
		JButton bt_Add = new JButton("添加契约");
		bt_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_Component.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个组件！", "", JOptionPane.ERROR_MESSAGE);
				if(txf_Name.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "契约名不能为空！", "", JOptionPane.ERROR_MESSAGE);
				if(txa_Assume.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Assume不能为空！", "", JOptionPane.ERROR_MESSAGE);
				if(txa_Guarantee.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Guarantee不能为空！", "", JOptionPane.ERROR_MESSAGE);
				StringBuilder contract =  new StringBuilder();
				String name = txf_Name.getText().toString();
				String assume = txa_Assume.getText().toString();
				String guarantee = txa_Guarantee.getText().toString();
				String refinedby = "";
				if(!txa_Refined.getText().toString().isEmpty())
					refinedby = "CONTRACT "+name+" REFINEDBY "+txa_Refined.getText().toString();
				ContractUtils contractUtils = new ContractUtils(name,assume,guarantee,refinedby);
				ArrayList<ContractUtils> contractList;
				if(ContractMap.containsKey(cbb_Component.getSelectedItem().toString())) {
					contractList = ContractMap.get(cbb_Component.getSelectedItem().toString());
				}else {
					contractList = new ArrayList<ContractUtils>();
				}
				contractList.add(contractUtils);
				ContractMap.put(cbb_Component.getSelectedItem().toString(), contractList);
				if(!findProperties(cbb_Component))
					contract.append("\t\t"+"properties"+"\n");
				contract.append("\t\t\t"+"OCRA::Contract=>\"\n");
				for(ContractUtils contractutils : contractList) {
					contract.append("\t\t\t\t"+"CONTRACT "+contractutils.getContractName()+"\n");
					contract.append("\t\t\t\t"+"assume: "+contractutils.getAssume()+";\n");
					contract.append("\t\t\t\t"+"guarantee: "+contractutils.getGuarantee()+";\n");
				}
				contract.append("\t\t\t\t"+"\";\n");
				boolean judge=false;//判断是否需要写RefinedBy
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
				System.out.println(contract.toString());
				removeAllcontract(cbb_Component);
				try {
					insertContract(contract.toString(),cbb_Component);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txf_Name.setText("");
				txa_Assume.setText("");
				txa_Guarantee.setText("");
				txa_Refined.setText("");
				JOptionPane.showMessageDialog(null, "添加成功！");
			}
		});
		bt_Add.setBounds(155, 521, 113, 27);
		contentPane.add(bt_Add);
		

	}
	public void loadAADL2List() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		while((line = br.readLine())!=null)
		{
			if(line.equals("") || Pattern.matches("\\s+", line) || line.indexOf("--")!=-1)
				continue;
			aadlContentList.add(line);
			//System.out.println(line);
		}
		br.close();
	}
	public void removeAllcontract(JComboBox<String> comboBox) {
		String target1="implementation "+(String)comboBox.getSelectedItem();
		String target2="end "+(String)comboBox.getSelectedItem();
		int length = aadlContentList.size();
		System.out.println("before:"+length);
		int start=0;
		int end=0;
		int pos_pro=0;
		int pos_ocra=0;
		boolean judge=false;
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf(target1)!=-1){
				start=i;
				break;
			}
		}
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf(target2)!=-1){
				end=i;
				break;
			}
		}
		for(int i =start;i<=end;i++) {
			if(aadlContentList.get(i).indexOf("properties")!=-1)
				pos_pro=i;
			if(aadlContentList.get(i).indexOf("OCRA::Contract=>")!=-1)
				pos_ocra=i;
		}
		System.out.println("pos_pro:"+pos_pro);
		System.out.println("pos_ocra:"+pos_ocra);
		if(pos_pro==(pos_ocra-1))
			judge=true;
		if(judge&&pos_pro!=0&&pos_ocra!=0) {
			for(int i =pos_pro+1;i<end;i++) {
				System.out.println("aaa");
				aadlContentList.remove(i);
				end--;
				i--;
			}
		}
		else if(!judge&&pos_pro!=0&&pos_ocra!=0){
			for(int i =pos_ocra;i<end;i++) {
				System.out.println("bbbb");
				aadlContentList.remove(aadlContentList.get(i));
				end--;
				i--;
			}
		}
		System.out.println("after:"+aadlContentList.size());
	}
	public void searchContract(String str) {
		String target1="implementation "+str;
		String target2="end "+str;
		int length = aadlContentList.size();
		int start=0;
		int end=0;
		int contract_start=0;
		int contract_end=0;
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf(target1)!=-1){
				start=i;
				break;
			}
		}
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf(target2)!=-1){
				end=i;
				break;
			}
		}

		for(int i =start;i<end;i++){
			if(aadlContentList.get(i).indexOf("OCRA::Contract")!=-1){
				contract_start=i;
			}
			if(aadlContentList.get(i).indexOf("\";")!=-1) {
				contract_end=i;
				break;
			}
		}
		for(int i = contract_start+1;i<contract_end;i++) {
			StringBuilder name =new StringBuilder();
			StringBuilder assume = new StringBuilder();
			StringBuilder guarantee= new StringBuilder();
			String s = aadlContentList.get(i);
			s = replaceBlank(s);
			String[] temp = s.split("\\s+");
			name.append(temp[1]);
			System.out.println("0:"+name);
			for(int j = i+1;j<contract_end;j++) {
				if(!aadlContentList.get(j).contains(";")) {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace("assume:", "");
					assume.append(arr+"\n");
				}
				else {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace("assume:", "");
					arr = arr.replace(";","");
					assume.append(arr);
					System.out.println("1:"+assume);
					i = j;
					break;
				}
			}
			for(int j = i+1;j<contract_end;j++) {
				System.out.println(j);
				System.out.println(contract_end);
				if(!aadlContentList.get(j).contains(";")) {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace("guarantee:", " ");
					System.out.println("test");
					guarantee.append(arr+"\n");
				}
				else {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace("guarantee:", " ");
					arr = arr.replace(";"," ");
					System.out.println("test");
					guarantee.append(arr);
					System.out.println("2:"+guarantee);
					i=j;
					break;
				}
			}
			if(!name.toString().isEmpty()) {
				ContractUtils contractUtils = new ContractUtils(name.toString(),assume.toString(),guarantee.toString(),"");
				ArrayList<ContractUtils> contractList;
				if(ContractMap.containsKey(str)) {
					contractList = ContractMap.get(str);
				}else {
					contractList = new ArrayList<ContractUtils>();
				}
				contractList.add(contractUtils);
				ContractMap.put(str, contractList);
			}
		}
	}
	public void searchRefinedBy(String str) {
		String target1="implementation "+str;
		String target2="end "+str;
		int length = aadlContentList.size();
		int start=0;
		int end=0;
		int refinedby_start=0;
		int refinedby_end=0;
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf(target1)!=-1){
				start=i;
				break;
			}
		}
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf(target2)!=-1){
				end=i;
				break;
			}
		}
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		boolean judge=false;
		for(int i =start;i<end;i++){
			System.out.println(aadlContentList.get(i));
			if(aadlContentList.get(i).indexOf("OCRA::RefinedBy")!=-1){
				refinedby_start=i;
			}
			if(aadlContentList.get(i).indexOf("\";")!=-1&&judge==true) {
				refinedby_end = i;
				break;
			}
			if(aadlContentList.get(i).indexOf("\";")!=-1&&judge==false) {
				judge = true;
			}
		}
		for(int i = refinedby_start+1;i<refinedby_end;i++) {
			System.out.println(refinedby_start);
			StringBuilder name =new StringBuilder();
			StringBuilder refinedby = new StringBuilder();
			String s = aadlContentList.get(i);
			System.out.println(s);
			s = replaceBlank(s);
			String[] temp = s.split("\\s+");
			name.append(temp[1]);
			System.out.println("0:"+name);
			for(int j = i;j<refinedby_end;j++) {
				if(!aadlContentList.get(j).contains(";")) {
					String arr = replaceBlank(aadlContentList.get(j));
					System.out.println("test");
					refinedby.append(arr+"\n");
				}
				else {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace(";","");
					System.out.println("test");
					refinedby.append(arr);
					System.out.println("1:"+refinedby);
					i = j;
					break;
				}
			}
			if(!name.toString().isEmpty()) {
				ArrayList<ContractUtils> contractList = ContractMap.get(str);
				for(ContractUtils contractUtils : contractList) {
					if(contractUtils.getContractName().equals(name.toString())) {
						System.out.println("test1");
						contractUtils.setRefinedby(refinedby.toString());
					}
				}
				ContractMap.put(str, contractList);
			}
		}
	}
	public void initcomboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		System.err.println("initPropBox()");
		String target1 = "system";
		String target2 = "subprogram";
		String target3 = "thread";
		String target4 = "process";
		for (String s: aadlContentList)
		{
			s = replaceBlank(s);
			String[] temp = s.split("\\s+");
			if (temp.length==3 &&(temp[1].equals("implementation"))&&
			   (temp[0].equals(target1)|| temp[0].equals(target2) || 
				temp[0].equals(target3) || temp[0].equals(target4) ))
			{
				comboBox.addItem(temp[2]);
			}
		}
		
	}
	private static String replaceBlank(String str)
	{
		  Pattern pt=Pattern.compile("^\\s*|\\s*$");
		  Matcher mt=pt.matcher(str);
		  str=mt.replaceAll("");
		  return str;
	}
	public boolean findProperties(JComboBox<String> comboBox) {
		String target1="implementation "+(String)comboBox.getSelectedItem();
		String target2="end "+(String)comboBox.getSelectedItem();
		int length = aadlContentList.size();
		int start=0;
		int end=0;
		for(int i=0;i<length;i++)
		{
			if(aadlContentList.get(i).indexOf(target1)!=-1)
			{
				start=i;
				break;
			}
		}
		for(int i=0;i<length;i++)
		{
			if(aadlContentList.get(i).indexOf(target2)!=-1)
			{
				end=i;
				break;
			}
		}
		boolean judge=false;
		for(int i =start;i<end;i++) {
			if(aadlContentList.get(i).indexOf("properties")!=-1) {
				judge=true;
				break;
			}
		}
		return judge;
	}
	public void insertContract(String contract,JComboBox<String> comboBox) throws IOException {
		String target = "end "+(String)comboBox.getSelectedItem();
		int length = aadlContentList.size();
		for(int i=0;i<length;i++)
		{
			if(aadlContentList.get(i).indexOf(target)!=-1)
			{
				System.out.println("Find! "+ target + " " + i);
				aadlContentList.add(i, contract);
				break;
			}
		}
//		for(String s:aadlContentList)
//		{
//			System.out.println(s);
//		}
		FileWriter fw;
		try {
			fw = new FileWriter(new File(filePath));
			for(String s: aadlContentList)
			{
				fw.write(s+"\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aadlContentList.clear();
		loadAADL2List();
	}
}
