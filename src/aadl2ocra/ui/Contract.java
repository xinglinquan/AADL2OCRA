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

public class Contract extends JFrame {

	private JPanel contentPane;
	public ArrayList<String> aadlContentList = new ArrayList<>();
	public String filePath = null;
	private JTextField textField;
	HashMap<String,String[]> map = new HashMap<String,String[]>();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(24, 13, 189, 24);
		try {
			loadAADL2List();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initcomboBox(comboBox);
		contentPane.add(comboBox);
		JLabel lblNewLabel = new JLabel("ContractName");
		lblNewLabel.setBounds(24, 63, 107, 18);
		contentPane.add(lblNewLabel);
		textField = new JTextField();
		textField.setBounds(132, 60, 286, 24);
		JLabel lblAssume = new JLabel("Assume");
		lblAssume.setBounds(24, 94, 72, 18);
		contentPane.add(lblAssume);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(24, 125, 394, 84);
		contentPane.add(textArea);
		
		JLabel lblGuarantee = new JLabel("Guarantee");
		lblGuarantee.setBounds(24, 238, 72, 18);
		contentPane.add(lblGuarantee);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(24, 269, 394, 84);
		contentPane.add(textArea_1);
		
		JLabel lblRefined = new JLabel("Refined By");
		lblRefined.setBounds(24, 366, 89, 18);
		contentPane.add(lblRefined);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(24, 397, 394, 66);
		contentPane.add(textArea_2);
		
		JButton button = new JButton("查看契约");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个组件！", "", JOptionPane.ERROR_MESSAGE);
				if(map.containsKey(comboBox.getSelectedItem().toString())){
					System.out.println("11111");
					String[] temp = map.get(comboBox.getSelectedItem().toString());
					textField.setText(temp[0]);
					textArea.setText(temp[1]);
					textArea_1.setText(temp[2]);
				}
				if(map2.containsKey(comboBox.getSelectedItem().toString())){
					String temp = map2.get(comboBox.getSelectedItem().toString());
					textArea_2.setText(temp);
				}
			}
		});
		button.setBounds(305, 13, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("添加契约");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个组件！", "", JOptionPane.ERROR_MESSAGE);
				if(textField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "契约名不能为空！", "", JOptionPane.ERROR_MESSAGE);
				if(textArea.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Assume不能为空！", "", JOptionPane.ERROR_MESSAGE);
				if(textArea_1.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Guarantee不能为空！", "", JOptionPane.ERROR_MESSAGE);
				String contract = null;
				if(!findProperties(comboBox))					
					contract ="\t\t"+"properties"+"\n";
				contract+="\t\t\t"+"OCRA::Contract=>\""+"CONTRACT "+textField.getText().toString()+"\n"
						+"\t\t\t\t"+"assume: "+textArea.getText().toString()+";\n"
						+"\t\t\t\t"+"guarantee: "+textArea_1.getText().toString()+";\";\n";
				String[] temp= {textField.getText().toString(),textArea.getText().toString(),textArea_1.getText().toString()};
				map.put(comboBox.getSelectedItem().toString(), temp);
				if(!textArea_2.getText().isEmpty()) {
					contract+="\t\t\t"+"OCRA::RefinedBy=>\""+"CONTRACT "+textField.getText().toString()
								+" REFINEDBY "+textArea_2.getText().toString()+";\";\n";
					map2.put(comboBox.getSelectedItem().toString(), textArea_2.getText().toString());
				}
				try {
					insertContract(contract,comboBox);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(155, 488, 113, 27);
		contentPane.add(button_1);
		contentPane.add(textField);
		textField.setColumns(10);
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
	public void searchContract(JComboBox<String> comboBox) {
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
		String name =null;
		String assume=null;
		String guarantee=null;
		String refinedby=null;
		for(int i =start;i<end;i++){
			String s = aadlContentList.get(i);
			s = replaceBlank(s);
			String[] temp = s.split("\\s+");
			if(temp[0].contains("OCRA::Contract=>")) {
				name=temp[1];
			}
			if(temp[0].contains("assume:")) {
				assume=s.substring(7, s.length()-3);
			}
			if(temp[0].contains("guarantee:")) {
				guarantee=s.substring(10, s.length()-3);
			}
			if(temp[0].contains("OCRA::RefinedBy=>")) {
				name=s.substring(18, s.length()-3);
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
