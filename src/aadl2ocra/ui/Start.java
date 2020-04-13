package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aadl2ocra.utils.ContractUtils;
import aadl2ocra.utils.PortUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start extends JFrame {

	private JPanel contentPane;
	public ArrayList<String> aadlContentList = new ArrayList<>();
	public String filePath = null;
	public static HashMap<String,ArrayList<ContractUtils>> ContractMap = new HashMap<String,ArrayList<ContractUtils>>();//建立组件和其契约的映射
	public static HashMap<String,ArrayList<PortUtils>> PortMap = new HashMap<String,ArrayList<PortUtils>>();//建立组件和其端口的映射
	public static HashMap<String,ArrayList<String>> SubContractMap = new HashMap<String,ArrayList<String>>();//建立组件和子组件契约的映射
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start(null);
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
	public Start(String s) {
		filePath =s;
		setTitle("\u8BF7\u9009\u62E9\u4E00\u4E2A\u7EC4\u4EF6");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			loadAADL2List();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		JComboBox<String> cbb_Component = new JComboBox<String>();
		initcomboBox(cbb_Component);
		for(int i =0;i<cbb_Component.getItemCount();i++) {
			String str = cbb_Component.getItemAt(i);
			searchContract(str);
		}
		for(int i =0;i<cbb_Component.getItemCount();i++) {
			String str = cbb_Component.getItemAt(i);
			searchRefinedBy(str);
		}
		for(int i =0;i<cbb_Component.getItemCount();i++) {
			String str = cbb_Component.getItemAt(i);
			searchPort(str);
		}
		for(int i =0;i<cbb_Component.getItemCount();i++) {
			String str = cbb_Component.getItemAt(i);
			searchSubContract(str);
		}
		cbb_Component.setBounds(14, 31, 152, 24);
		contentPane.add(cbb_Component);
		JButton bt_Check = new JButton("\u67E5\u770B\u5951\u7EA6");
		bt_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_Component.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个组件", "", JOptionPane.ERROR_MESSAGE);
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
		bt_Check.setBounds(209, 30, 113, 27);
		contentPane.add(bt_Check);
		
		JButton bt_Add = new JButton("\u6DFB\u52A0\u5951\u7EA6");
		bt_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_Component.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个组件", "", JOptionPane.ERROR_MESSAGE);
				else {
					ArrayList<ContractUtils> contractList;
					if(ContractMap.containsKey(cbb_Component.getSelectedItem().toString())) {
						contractList = ContractMap.get(cbb_Component.getSelectedItem().toString());
					}else {
						contractList = new ArrayList<ContractUtils>();
					}
					ArrayList<PortUtils> portList;
					if(PortMap.containsKey(cbb_Component.getSelectedItem().toString())) {
						portList = PortMap.get(cbb_Component.getSelectedItem().toString());
					}else {
						portList = new ArrayList<PortUtils>();
					}
					ArrayList<String> subList;
					if(SubContractMap.containsKey(cbb_Component.getSelectedItem().toString())) {
						subList = SubContractMap.get(cbb_Component.getSelectedItem().toString());
					}else {
						subList = new ArrayList<String>();
					}
					AddContract addContract = new AddContract(cbb_Component.getSelectedItem().toString(),contractList,portList,subList);
					addContract.setVisible(true);
				}
			}
		});
		bt_Add.setBounds(363, 30, 113, 27);
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
			if (temp.length==2 &&
			   (temp[0].equals(target1)|| temp[0].equals(target2) || 
				temp[0].equals(target3) || temp[0].equals(target4) ))
			{
				comboBox.addItem(temp[1]);
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
	public void searchContract(String str) {
		String target1="implementation "+str+".impl";
		String target2="end "+str+".impl";
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
				//System.out.println(j);
				//System.out.println(contract_end);
				if(!aadlContentList.get(j).contains(";")) {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace("guarantee:", " ");
					//System.out.println("test");
					guarantee.append(arr+"\n");
				}
				else {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace("guarantee:", " ");
					arr = arr.replace(";"," ");
					//System.out.println("test");
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
		String target1="implementation "+str+".impl";
		String target2="end "+str+".impl";
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
			//System.out.println(aadlContentList.get(i));
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
					//System.out.println("test");
					refinedby.append(arr+"\n");
				}
				else {
					String arr = replaceBlank(aadlContentList.get(j));
					arr = arr.replace(";","");
					//System.out.println("test");
					refinedby.append(arr);
					System.out.println("3:"+refinedby);
					i = j;
					break;
				}
			}
			if(!name.toString().isEmpty()) {
				ArrayList<ContractUtils> contractList = ContractMap.get(str);
				for(ContractUtils contractUtils : contractList) {
					if(contractUtils.getContractName().equals(name.toString())) {
						//System.out.println("test1");
						contractUtils.setRefinedby(refinedby.toString());
					}
				}
				ContractMap.put(str, contractList);
			}
		}
	}
	public void searchPort(String str) {
		String target1=str;
		String target2="end "+str;
		int length = aadlContentList.size();
		int start=0;
		int end=0;
		for(int i=0;i<length;i++){
			if(aadlContentList.get(i).indexOf("system "+target1)!=-1||aadlContentList.get(i).indexOf("process "+target1)!=-1||aadlContentList.get(i).indexOf("thread "+target1)!=-1){
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
		System.out.println("portstart:"+start);
		System.out.println("portend:"+end);
		ArrayList<PortUtils> portList;
		if(PortMap.containsKey(str))
			portList = PortMap.get(str);
		else
			portList = new ArrayList<PortUtils>();
		for(int i =start;i<end;i++){
			System.out.println(aadlContentList.get(i));
			if(aadlContentList.get(i).indexOf("in data port")!=-1){
				System.out.println("test");
				String s = replaceBlank(aadlContentList.get(i));
				String[] temp = s.split("\\s+");
				String name = temp[0].replace(":","");
				String dir = "in";
				String type = "data";
				String classifier;
				if(temp[temp.length-1].indexOf("port")!=-1)
					classifier =null;
				else 
					classifier = temp[temp.length-1].replace(";","");
				PortUtils port = new PortUtils(name,dir,type,classifier);
				portList.add(port);
				System.out.println(name+dir+type+classifier);
					
			}
			if(aadlContentList.get(i).indexOf("out data port")!=-1){
				String s = replaceBlank(aadlContentList.get(i));
				String[] temp = s.split("\\s+");
				String name = temp[0].replace(":","");
				String dir = "out";
				String type = "data";
				String classifier;
				if(temp[temp.length-1].indexOf("port")!=-1)
					classifier =null;
				else 
					classifier = temp[temp.length-1].replace(";","");
				PortUtils port = new PortUtils(name,dir,type,classifier);
				portList.add(port);
				System.out.println(name+dir+type+classifier);	
			}
			if(aadlContentList.get(i).indexOf("in event port")!=-1){
				String s = replaceBlank(aadlContentList.get(i));
				String[] temp = s.split("\\s+");
				String name = temp[0].replace(":","");
				String dir = "in";
				String type = "event";
				String classifier;
				if(temp[temp.length-1].indexOf("port")!=-1)
					classifier =null;
				else 
					classifier = temp[temp.length-1].replace(";","");
				PortUtils port = new PortUtils(name,dir,type,classifier);
				portList.add(port);
				System.out.println(name+dir+type+classifier);	
			}
			if(aadlContentList.get(i).indexOf("out event port")!=-1){
				String s = replaceBlank(aadlContentList.get(i));
				String[] temp = s.split("\\s+");
				String name = temp[0].replace(":","");
				String dir = "out";
				String type = "event";
				String classifier;
				if(temp[temp.length-1].indexOf("port")!=-1)
					classifier =null;
				else 
					classifier = temp[temp.length-1].replace(";","");
				PortUtils port = new PortUtils(name,dir,type,classifier);
				portList.add(port);
				System.out.println(name+dir+type+classifier);
					
			}
		}
		PortMap.put(str, portList);
	}
	public void searchSubContract(String str) {
		String target1="implementation "+str+".impl";
		String target2="end "+str+".impl";
		int length = aadlContentList.size();
		int start=0;
		int end=0;
		int sub_start=0;
		int sub_end=0;
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
		System.out.println("substart:"+start);
		System.out.println("subend:"+end);
		for(int i =start;i<end;i++){
			//System.out.println(aadlContentList.get(i));
			if(aadlContentList.get(i).indexOf("subcomponents")!=-1){
				sub_start=i;
			}
			if(aadlContentList.get(i).indexOf("connections")!=-1||aadlContentList.get(i).indexOf("properties")!=-1||i==end) {
				sub_end = i;
				break;
			}
		}
		ArrayList<String> subList;
		if(SubContractMap.containsKey(str))
			subList = SubContractMap.get(str);
		else
			subList = new ArrayList<String>();
		if(sub_start!=0) {
			for(int i = sub_start+1;i<sub_end;i++) {
				//System.out.println(sub_start);
				String name;
				String type;
				String s = aadlContentList.get(i);
				//System.out.println(s);
				s = replaceBlank(s);
				String[] temp = s.split("\\s+");
				name = temp[0].replace(":","");
				if(temp[temp.length-1].equals(";"))
					type = temp[temp.length-2].replace(".impl","");
				else
					type = temp[temp.length-1].replace(".impl;","");
				if(ContractMap.containsKey(type)) {
					ArrayList<ContractUtils> contractList = ContractMap.get(type);
					for(ContractUtils contract : contractList) {
						if(!contract.getContractName().isEmpty()&&contract.getContractName()!="") {
							subList.add(name+"."+contract.getContractName());
							System.out.println(name+"."+contract.getContractName());
						}
					}
				}
			}
		}
		SubContractMap.put(str, subList);
		
	}
}
