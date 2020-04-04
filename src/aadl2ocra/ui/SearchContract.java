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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(62, 71, 155, 24);
		contentPane.add(comboBox);
		initcomboBox(comboBox);
		JButton button = new JButton("查看详情");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().toString()==null)
					JOptionPane.showMessageDialog(null, "请选择一个契约！", "", JOptionPane.ERROR_MESSAGE);
				else {
					for(ContractUtils contract:contractList) {
						if(contract.getContractName().equals(comboBox.getSelectedItem().toString())) {
							Contract_Info contract_info = new Contract_Info(contract);
							contract_info.setVisible(true);
							break;
						}
					}

				}
			}
		});
		button.setBounds(280, 70, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(166, 188, 113, 27);
		contentPane.add(button_1);
	}
	public void initcomboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		for(ContractUtils contract:contractList) {
			comboBox.addItem(contract.getContractName());
		}
	}
}
