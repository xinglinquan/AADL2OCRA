package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aadl2ocra.utils.ContractUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchContract extends JFrame {

	private JPanel contentPane;
	public static ArrayList<ContractUtils> contractList;
	private static JComboBox<String> cbb_Contract;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchContract frame = new SearchContract(null,null);
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
	public SearchContract(String name,ArrayList<ContractUtils> list) {
		setTitle(name+"的契约");
		contractList = list;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbb_Contract = new JComboBox<String>();
		cbb_Contract.setBounds(62, 71, 155, 24);
		contentPane.add(cbb_Contract);
		initcomboBox();
		JButton bt_Check = new JButton("\u67E5\u770B\u8BE6\u60C5");
		bt_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_Contract.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个契约！", "", JOptionPane.ERROR_MESSAGE);
				else {
					for(ContractUtils contract:contractList) {
						if(contract.getContractName().equals(cbb_Contract.getSelectedItem().toString())) {
							Contract_Info contract_info = new Contract_Info(name,contract);
							contract_info.setVisible(true);
							break;
						}
					}

				}
			}
		});
		bt_Check.setBounds(280, 70, 113, 27);
		contentPane.add(bt_Check);
		
		JButton bt_Exit = new JButton("\u9000\u51FA");
		bt_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bt_Exit.setBounds(166, 188, 113, 27);
		contentPane.add(bt_Exit);
	}
	public static void initcomboBox() {
		cbb_Contract.removeAllItems();
		for(ContractUtils contract:contractList) {
			cbb_Contract.addItem(contract.getContractName());
		}
	}
	public static void initcomboBox(ArrayList<ContractUtils> contracts) {
		cbb_Contract.removeAllItems();
		for(ContractUtils contract:contractList) {
			cbb_Contract.addItem(contract.getContractName());
		}
	}
}
