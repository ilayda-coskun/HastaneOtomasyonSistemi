package GuiPackage;

import java.sql.*;
import DatabaseConnection.*;
import DatabaseData.Bashekim;
import DatabaseData.Doctor;
import DatabaseData.Hasta;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class LoginFrame extends JFrame {
	private JTextField fldhastausername;
	private JPasswordField fldhastapassword;
	private JTextField flddoctorname;
	private JPasswordField flddoctorpass;

	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setResizable(false);
		getContentPane().setBackground(new Color(255, 245, 238));
		setTitle("Hastane Otomasyon Sistemi");
		getContentPane().setEnabled(false);
		setBounds(100, 100, 520, 518);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel Label1 = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		Label1.setBounds(46, 135, 416, 63);
		Label1.setFont(new Font("Century Gothic", Font.BOLD, 21));
		Label1.setBackground(new Color(255, 255, 255));
		Label1.setForeground(new Color(0, 139, 139));
		getContentPane().add(Label1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 139, 139));
		tabbedPane.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tabbedPane.setBounds(10, 217, 476, 232);
		getContentPane().add(tabbedPane);

		JPanel hastaGiris = new JPanel();
		hastaGiris.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Hasta Giriþi", null, hastaGiris, null);
		hastaGiris.setLayout(null);

		JLabel fldHastausername = new JLabel("TC Kimlik No");
		fldHastausername.setForeground(new Color(0, 139, 139));
		fldHastausername.setBounds(57, 29, 109, 30);
		hastaGiris.add(fldHastausername);
		fldHastausername.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JLabel fldHastapassword = new JLabel("Parola");
		fldHastapassword.setForeground(new Color(0, 139, 139));
		fldHastapassword.setBounds(57, 69, 78, 30);
		hastaGiris.add(fldHastapassword);
		fldHastapassword.setFont(new Font("Century Gothic", Font.BOLD, 15));

		fldhastausername = new JTextField();
		fldhastausername.setForeground(new Color(0, 139, 139));
		fldhastausername.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		fldhastausername.setBounds(219, 23, 169, 30);
		hastaGiris.add(fldhastausername);
		fldhastausername.setColumns(10);

		fldhastapassword = new JPasswordField();
		fldhastapassword.setForeground(new Color(0, 139, 139));
		fldhastapassword.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		fldhastapassword.setBounds(219, 71, 169, 30);
		hastaGiris.add(fldhastapassword);

		JButton btnHastalogin = new JButton("Giri\u015F Yap");
		btnHastalogin.setForeground(new Color(255, 245, 238));
		btnHastalogin.setBackground(new Color(0, 139, 139));
		btnHastalogin.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnHastalogin.setBounds(240, 136, 131, 47);
		hastaGiris.add(btnHastalogin);

		JButton btnKayitOl = new JButton("Kay\u0131t Ol");
		btnKayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame rFrame = new RegisterFrame();
				rFrame.setVisible(true);
				dispose();
			}
		});
		btnKayitOl.setForeground(new Color(255, 245, 238));
		btnKayitOl.setBackground(new Color(0, 139, 139));
		btnKayitOl.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnKayitOl.setBounds(80, 136, 131, 47);
		hastaGiris.add(btnKayitOl);

		JPanel doktorGiris = new JPanel();
		doktorGiris.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Giriþi", null, doktorGiris, null);
		doktorGiris.setLayout(null);

		JLabel fldDoctorusername = new JLabel("TC Kimlik No");
		fldDoctorusername.setForeground(new Color(0, 139, 139));
		fldDoctorusername.setFont(new Font("Century Gothic", Font.BOLD, 15));
		fldDoctorusername.setBounds(57, 29, 128, 30);
		doktorGiris.add(fldDoctorusername);

		flddoctorname = new JTextField();
		flddoctorname.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		flddoctorname.setForeground(new Color(0, 139, 139));
		flddoctorname.setColumns(10);
		flddoctorname.setBounds(219, 23, 169, 30);
		doktorGiris.add(flddoctorname);

		flddoctorpass = new JPasswordField();
		flddoctorpass.setForeground(new Color(0, 139, 139));
		flddoctorpass.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		flddoctorpass.setBounds(219, 71, 169, 30);
		doktorGiris.add(flddoctorpass);

		JLabel fldDoctorpassword = new JLabel("Parola");
		fldDoctorpassword.setForeground(new Color(0, 139, 139));
		fldDoctorpassword.setFont(new Font("Century Gothic", Font.BOLD, 15));
		fldDoctorpassword.setBounds(57, 69, 78, 30);
		doktorGiris.add(fldDoctorpassword);

		JButton btnDoctorlogin = new JButton("Giri\u015F Yap");
		btnDoctorlogin.setForeground(new Color(255, 245, 238));
		btnDoctorlogin.setBackground(new Color(0, 139, 139));
		btnDoctorlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flddoctorname.getText().length() == 0 || flddoctorpass.getText().length() == 0) {
					Mesaj.showMsg("fill");

				} else {
					boolean key = true;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (flddoctorname.getText().equals(rs.getString("UserName"))
									&& flddoctorpass.getText().equals(rs.getString("UserPass"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setUserName(rs.getString("UserName"));
									bhekim.setUserPass(rs.getString("UserPass"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimFrame bGUI = new BashekimFrame(bhekim);
									bGUI.setVisible(true);
									dispose();
									key = false;
								}
								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setUserName(rs.getString("UserName"));
									doctor.setUserPass(rs.getString("UserPass"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorFrame dGUI = new DoctorFrame(doctor);
									dGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (key) {
						Mesaj.showMsg("Böyle bir kayýt bulunamadý !");
					}
				}
			}
		});
		btnDoctorlogin.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnDoctorlogin.setBounds(160, 136, 131, 47);
		doktorGiris.add(btnDoctorlogin);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hospital.png")));
		lbl_logo.setBounds(193, 27, 117, 98);
		getContentPane().add(lbl_logo);
		btnHastalogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fldhastausername.getText().length() == 0 || fldhastapassword.getText().length() == 0) {
					Mesaj.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fldhastausername.getText().equals(rs.getString("UserName"))
									&& fldhastapassword.getText().equals(rs.getString("UserPass"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setUserName(rs.getString("UserName"));
									hasta.setUserPass(rs.getString("UserPass"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaFrame hGUI = new HastaFrame(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (key) {
						Mesaj.showMsg("Böyle bir hasta bulunamadý lütfen kayýt olunuz !");
					}
				}
			}
		});

	}
}
