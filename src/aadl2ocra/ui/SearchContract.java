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
	public ArrayList<ContractUtils> contractList;
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
		
		JComboBox cbb_Contract = new JComboBox();
		cbb_Contract.setBounds(62, 71, 155, 24);
		contentPane.add(cbb_Contract);
		initcomboBox(cbb_Contract);
		JButton bt_Check = new JButton("查看详情");
		bt_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbb_Contract.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个契约！", "", JOptionPane.ERROR_MESSAGE);
				else {
					for(ContractUtils contract:contractList) {
						if(contract.getContractName().equals(cbb_Contract.getSelectedItem().toString())) {
							Contract_Info contract_info = new Contract_Info(contract);
							contract_info.setVisible(true);
							break;
						}
					}

				}
			}
		});
		bt_Check.setBounds(280, 70, 113, 27);
		contentPane.add(bt_Check);
		
		JButton bt_Exit = new JButton("退出");
		bt_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bt_Exit.setBounds(166, 188, 113, 27);
		contentPane.add(bt_Exit);
	}
	public void initcomboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		for(ContractUtils contract:contractList) {
			comboBox.addItem(contract.getContractName());
		}
	}
}
