package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(null);
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
	HashMap<String, Integer> map1=new HashMap<>();//记录组件和契约行数的map映射
	HashMap<String, String> map2=new LinkedHashMap();//记录组件和组件类型行数的map映射
	public MainFrame(String s) {
		setTitle("AADL\u6A21\u578B\u6C42\u7CBE\u9A8C\u8BC1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println("test0");
		split(s);
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
		
		JLabel label = new JLabel("\u7EC4\u4EF6\u4FE1\u606F");
		label.setBounds(173, 11, 72, 18);
		contentPane.add(label);
		
		JButton btncontract = new JButton("\u67E5\u770B\u5951\u7EA6");
		btncontract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btncontract.setBounds(14, 413, 105, 27);
		contentPane.add(btncontract);
		
		JButton btncontract_1 = new JButton("\u9A8C\u8BC1\u5951\u7EA6");
		btncontract_1.setBounds(158, 413, 105, 27);
		contentPane.add(btncontract_1);
		
		JButton btncontract_1_1 = new JButton("\u6DFB\u52A0\u5951\u7EA6");
		btncontract_1_1.setBounds(313, 413, 105, 27);
		contentPane.add(btncontract_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 32, 404, 332);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
	}
	
	ArrayList<String> split(String filename){
		ArrayList<String> str = new ArrayList<String>();
		File file=new File(filename);  
		BufferedReader reader=null;  
        String temp=null;  
        int line=1;  
        try{  
        	reader=new BufferedReader(new FileReader(file));  
            while((temp=reader.readLine())!=null){  
               // System.out.println("第"+line+"行:"+temp);

            	String []arrayStr=temp.split("\\s+");
                for(int i =0;i<arrayStr.length;i++) {
                	String componentname = null;
                	if(arrayStr[i].equals("system")&&(!arrayStr[i+1].equals("implementation"))&&(arrayStr[i+1].indexOf(".impl")==-1)) {
                		if(arrayStr[i+1].indexOf(";")!=-1) {
                			arrayStr[i+1]=arrayStr[i+1].substring(0, arrayStr[i+1].length() -1);
                		}
                		if(!map2.containsKey(arrayStr[i+1])) {
                			componentname = arrayStr[i+1];
                			map2.put(componentname, "system");
                		}
                	}
                	if(arrayStr[i].indexOf("OCRA")!=-1)
                		map1.put(componentname, line);
                }
                line++;  
            }  
    }  
    catch(Exception e){  
        e.printStackTrace();  
    }  
    finally{  
        if(reader!=null){  
            try{  
                reader.close();  
            }  
            catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
    }  
        
		return null;
	}
}
