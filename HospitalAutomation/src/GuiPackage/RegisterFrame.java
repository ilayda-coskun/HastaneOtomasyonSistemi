package GuiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DatabaseConnection.Mesaj;
import DatabaseData.Hasta;
import DatabaseData.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {

	private JPanel w_pane;
	private JTextField fldName;
	private JTextField fldTcno;
	private JPasswordField fldPass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setResizable(false);
		setTitle("Hastane Otomasyon Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 245, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 10, 70, 25);
		w_pane.add(lblNewLabel_1);
		
		fldName = new JTextField();
		fldName.setColumns(10);
		fldName.setBounds(10, 34, 266, 25);
		w_pane.add(fldName);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 69, 70, 25);
		w_pane.add(lblNewLabel_1_1);
		
		fldTcno = new JTextField();
		fldTcno.setColumns(10);
		fldTcno.setBounds(10, 93, 266, 25);
		w_pane.add(fldTcno);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 128, 70, 25);
		w_pane.add(lblNewLabel_1_1_1);
		
		fldPass = new JPasswordField();
		fldPass.setBounds(10, 154, 266, 25);
		w_pane.add(fldPass);
		
		JButton btnRegister = new JButton("Kay\u0131t Ol");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fldTcno.getText().length() == 0 || fldPass.getText().length() == 0 || fldName.getText().length() == 0) {
					Mesaj.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fldTcno.getText(), fldPass.getText(), fldName.getText());
						if(control) {
							Mesaj.showMsg("success");
							LoginFrame login = new LoginFrame();
							login.setVisible(true);
							dispose();
						}else {
							Mesaj.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnRegister.setForeground(new Color(255, 245, 238));
		btnRegister.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnRegister.setBackground(new Color(0, 139, 139));
		btnRegister.setBounds(10, 207, 266, 33);
		w_pane.add(btnRegister);
		
		JButton btnBackto = new JButton("Geri D\u00F6n");
		btnBackto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnBackto.setForeground(new Color(255, 245, 238));
		btnBackto.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnBackto.setBackground(new Color(0, 139, 139));
		btnBackto.setBounds(10, 250, 266, 33);
		w_pane.add(btnBackto);
	}
}
