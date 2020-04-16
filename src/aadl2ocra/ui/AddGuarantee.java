package aadl2ocra.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGuarantee extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGuarantee frame = new AddGuarantee();
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
	public AddGuarantee() {
		setTitle("\u6DFB\u52A0\u7EC6\u8282");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u9009\u62E9\u7EA6\u675F");
		label.setBounds(166, 37, 72, 18);
		contentPane.add(label);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(58, 68, 304, 18);
		contentPane.add(comboBox);
		init();
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString()!="") {
					AddContract.txa_guarantee.append(comboBox.getSelectedItem().toString());
					comboBox.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "添加成功");
				}
				else
					JOptionPane.showMessageDialog(null, "内容不能为空！", "", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setBounds(305, 121, 113, 27);
		contentPane.add(button);
	}
	public void init() {
		comboBox.removeAllItems();
		comboBox.addItem("");
		for(String temp : AddContract.constraint)
			comboBox.addItem(temp);
	}
}
