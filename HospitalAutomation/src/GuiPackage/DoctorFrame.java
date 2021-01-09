package GuiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DatabaseData.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import DatabaseConnection.Mesaj;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorFrame extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorFrame frame = new DoctorFrame(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public DoctorFrame(Doctor doctor) throws SQLException {

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}

		setResizable(false);
		setTitle("Hastane Otomasyon Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 485);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 245, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n " + doctor.getName());
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 256, 39);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setForeground(new Color(0, 139, 139));
		w_tab.setFont(new Font("Century Gothic", Font.BOLD, 15));
		w_tab.setBounds(10, 59, 726, 366);
		w_pane.add(w_tab);

		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Çalýþma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(581, 40, 130, 20);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(
				new String[] { "9.00", "9.20", "9.40", "10.00", "10.20", "10.40", "11.00", "11.20", "11.40", "12.00",
						"13.00", "13.20", "13.40", "14.00", "14.20", "14.40", "15.00", "15.20" }));
		select_time.setBounds(581, 70, 130, 20);
		w_whour.add(select_time);

		JButton btnAddWhour = new JButton("Ekle");
		btnAddWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (date.length() == 0) {
					Mesaj.showMsg("Lütfen geçerli bir tarih giriniz !");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Mesaj.showMsg("success");
							updatewhourModel(doctor);
						} else {
							Mesaj.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnAddWhour.setForeground(new Color(255, 245, 238));
		btnAddWhour.setFont(new Font("Century Gothic", Font.BOLD, 11));
		btnAddWhour.setBackground(new Color(0, 139, 139));
		btnAddWhour.setBounds(581, 100, 130, 27);
		w_whour.add(btnAddWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 10, 561, 312);
		w_whour.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);

		JButton btnDeleteWhour = new JButton("Sil");
		btnDeleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					try {
						boolean control = doctor.deleteWhour(selID);
						if (control) {
							Mesaj.showMsg("success");
							updatewhourModel(doctor);
						} else {
							Mesaj.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Mesaj.showMsg("Lütfen bir tarih seçiniz !");
				}
			}
		});
		btnDeleteWhour.setForeground(new Color(255, 245, 238));
		btnDeleteWhour.setFont(new Font("Century Gothic", Font.BOLD, 11));
		btnDeleteWhour.setBackground(new Color(0, 139, 139));
		btnDeleteWhour.setBounds(581, 154, 130, 27);
		w_whour.add(btnDeleteWhour);

		JButton btnExitDoctor = new JButton("\u00C7\u0131k\u0131\u015F");
		btnExitDoctor.setBounds(641, 295, 70, 27);
		w_whour.add(btnExitDoctor);
		btnExitDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnExitDoctor.setForeground(new Color(255, 245, 238));
		btnExitDoctor.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExitDoctor.setBackground(new Color(0, 139, 139));
	}

	public void updatewhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
}
