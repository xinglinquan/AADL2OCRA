package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import aadl2ocra.utils.ContractUtils;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Result extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<String> resultList;
	JComboBox<String> comboBox = new JComboBox<String>();
	private List<String> componentList = new ArrayList<String>();
	private List<String[]> result = new ArrayList<String[]>();
	HashMap<String,ArrayList<String>> CounterMap = new HashMap<String,ArrayList<String>>();
	String[] columnNames =
		{ "契约名", "所属组件", "所含子契约", "验证结果" };
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result(null);
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
	public Result(List<String> list) {
		resultList = list;
		findcomponent();
		setTitle("\u9A8C\u8BC1\u7ED3\u679C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 34, 754, 400);
		contentPane.add(scrollPane);
		Object[][] obj = new Object[result.size()][4];
		for(int i =0;i<result.size();i++) {
			obj[i][0]=result.get(i)[0];
			obj[i][1]=result.get(i)[1];
			obj[i][2]=result.get(i)[2];
			boolean judge=false;
			int num = comboBox.getItemCount();
			for(int j =0;j<num;j++) {
				if(comboBox.getItemAt(j).toString().equals(result.get(i)[1]+"."+result.get(i)[0])) {
					judge=true;
					break;
				}
			}
			if(judge)
				obj[i][3]="失败";
			else
				obj[i][3]="成功";
		}
		table = new JTable(obj, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(147);
		table.getColumnModel().getColumn(1).setPreferredWidth(147);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		
		comboBox.setBounds(14, 486, 198, 29);
		contentPane.add(comboBox);
		
		JButton bt_checkcounter = new JButton("\u67E5\u770B\u53CD\u4F8B");
		bt_checkcounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> list = CounterMap.get(comboBox.getSelectedItem().toString());
				Counter_example ce = new Counter_example(list);
				ce.setVisible(true);
			}
		});
		bt_checkcounter.setBounds(274, 487, 120, 27);
		contentPane.add(bt_checkcounter);
		
		JButton bt_exit = new JButton("\u9000\u51FA");
		bt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		bt_exit.setBounds(655, 487, 113, 27);
		contentPane.add(bt_exit);
	}
	public void findcomponent() {
		for(String s : resultList) {
			if(s.indexOf("Checking refinement of component:")!=-1) {
				String[] temp = s.split("\\s+");
				componentList.add(temp[temp.length-1]);
				System.out.println("component is "+temp[temp.length-1]);
			}
		}
		for(String s : componentList) {
			findcontract(s);
			findcounter(s);
		}
	}
	public void findcontract(String name) {
		System.out.println("name is "+name);
		int start=-1;
		int end=-1;
		for(int i =0;i<resultList.size();i++) {
			if(resultList.get(i).indexOf("Checking refinement of component: "+name)!=-1) {
				start=i;
				System.out.println("start is "+start);
			}
			if((resultList.get(i).indexOf("Checking refinement of component: ")!=-1)&&i>start&&start!=-1) {
				end=i;
				System.out.println("end is "+end);
				break;
			}
		}
		if(start!=-1&&end!=-1) {
			for(int i =start;i<end;i++) {
				if(resultList.get(i).indexOf("  Checking \"CONTRACT")!=-1) {
					String[] temp = resultList.get(i).split("\\s+");
					String contractname = temp[3];
					System.out.println("contractname is "+ contractname);
					int j = resultList.get(i).indexOf("REFINEDBY");
					String sub = resultList.get(i).substring(j+9,resultList.get(i).length()-2);
					System.out.println("sub is "+ sub);
					String[] strs = {contractname,name,sub};
					result.add(strs);
				}
			}
		}
	}
	public void findcounter(String name) {
		System.out.println("name is "+name);
		int start=-1;
		int end=-1;
		for(int i =0;i<resultList.size();i++) {
			if(resultList.get(i).indexOf("Checking refinement of component: "+name)!=-1) {
				start=i;
				System.out.println("start is "+start);
			}
			if((resultList.get(i).indexOf("Checking refinement of component: ")!=-1)&&i>start) {
				end=i;
				System.out.println("end is "+end);
				break;
			}
		}
		if(start!=-1&&end!=-1) {
			for(int i =start;i<end;i++) {
				if(resultList.get(i).indexOf("Trace Type: Counterexample")!=-1) {
					String contractname=null;
					int counter_start=i;
					int counter_end=i;
					for(int j=i;j<end;j++) {
						if(resultList.get(j).indexOf("[NOT OK")!=-1) {
							counter_end=j;
							break;
						}
					}
					System.out.println("counter_start is "+counter_start);
					System.out.println("counter_end is "+counter_end);
					ArrayList<String> counter = new ArrayList<String>();
					for(int j = counter_start;j<=counter_end;j++) {
						counter.add(resultList.get(j));
						System.out.println(resultList.get(j));
					}
					for(int j =i;j>=start;j--) {
						if(resultList.get(j).indexOf("  Checking \"CONTRACT")!=-1) {
							String[] temp = resultList.get(j).split("\\s+");
							contractname = temp[3];
							System.out.println("contractname is "+ contractname);
							break;
						}
					}
					comboBox.addItem(name+"."+contractname);
					CounterMap.put(name+"."+contractname, counter);
				}
			}
		}
	}
}
