package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemSubcomponent;
import org.osate.aadl2.instance.SystemInstance;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

public class MainFrame2 extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame2 frame = new MainFrame2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	HashMap<String, String> map1=new HashMap<>();//组件到契约的映射
	HashMap<String, String> map2=new LinkedHashMap();//组件到组件类型的映射
	/**
	 * Create the frame.
	 */
	public MainFrame2(SystemImplementation sysimpl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println("test0");
		//analyse(sysimpl);
		System.out.println("test1");
		String[] columnNames =  {"组件名称", "组件类型"};  
		Object[][] obj = new Object[map2.size()][2];
		int i =0;
		for(String key:map2.keySet())
		{
			obj[i][0]=key;
			obj[i][1]=map2.get(key);
			i++;
		}
		
		JTable table = new JTable(obj, columnNames);  
		int colunms = table.getColumnCount(); 
		TableColumn column = null;
	    for(i = 0; i < colunms; i++)  
	    {  
	         column = table.getColumnModel().getColumn(i);  
	    }
		//table = new JTable();
		//table.setBounds(14, 52, 404, 165);

	    JLabel label = new JLabel("\u7EC4\u4EF6\u4FE1\u606F");
		label.setBounds(179, 13, 72, 18);
		contentPane.add(label);
		JButton button = new JButton("\u67E5\u770B\u5951\u7EA6");
		button.setBounds(14, 430, 113, 27);
		contentPane.add(button);
		
		JButton button_2 = new JButton("\u9A8C\u8BC1\u5951\u7EA6");
		button_2.setBounds(305, 430, 113, 27);
		contentPane.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 32, 404, 332);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
	}
	void analyse(SystemImplementation sysimpl) {
		map2.put(sysimpl.getName(), "system");

		if(sysimpl.getOwnedSystemSubcomponents().size()>0) {
			for(SystemSubcomponent systemsubcomponent : sysimpl.getOwnedSystemSubcomponents()){
				analyse((SystemImplementation)systemsubcomponent);
			}
		}
		if(sysimpl.getAllSubcomponents().size()>0) {
			for(Subcomponent component:sysimpl.getAllSubcomponents()) {
				map2.put(component.getName(), component.getSubcomponentType().getFullName());
			}
		}
	}
}
