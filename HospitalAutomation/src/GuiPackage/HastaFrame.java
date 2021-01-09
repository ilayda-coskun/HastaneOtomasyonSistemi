package GuiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DatabaseConnection.Mesaj;
import DatabaseConnection.Item;
import DatabaseData.Randevu;
import DatabaseData.Clinic;
import DatabaseData.Hasta;
import DatabaseData.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaFrame extends JFrame {

	private JPanel w_pane;
	public static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Randevu randevu = new Randevu();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaFrame frame = new HastaFrame(hasta);
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
	 * @throws ClassNotFoundException
	 */
	public HastaFrame(Hasta hasta) throws ClassNotFoundException, SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for (int i = 0; i < randevu.getHastaList(hasta.getId()).size(); i++) {
			appointData[0] = randevu.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = randevu.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2] = randevu.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}

		setTitle("Hastane Otomasyon Sistemi");
		setTitle("Hastane Otomasyon Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 485);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 245, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n " + hasta.getName());
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 256, 39);
		w_pane.add(lblNewLabel);

		JButton btnExitHasta = new JButton("\u00C7\u0131k\u0131\u015F");
		btnExitHasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnExitHasta.setForeground(new Color(255, 245, 238));
		btnExitHasta.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExitHasta.setBackground(new Color(0, 139, 139));
		btnExitHasta.setBounds(627, 18, 109, 27);
		w_pane.add(btnExitHasta);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setForeground(new Color(0, 139, 139));
		w_tab.setFont(new Font("Century Gothic", Font.BOLD, 15));
		w_tab.setBounds(10, 59, 726, 366);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 30, 257, 221);
		w_appointment.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 10, 108, 21);
		w_appointment.add(lblNewLabel_1);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Ad\u0131");
		lblPoliklinikAd.setForeground(new Color(0, 139, 139));
		lblPoliklinikAd.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPoliklinikAd.setBounds(277, 8, 110, 25);
		w_appointment.add(lblPoliklinikAd);

		JComboBox selectClinic = new JComboBox();
		selectClinic.setBounds(277, 34, 154, 25);
		selectClinic.addItem("--Poliklinik Seç--");
		for (int i = 0; i < clinic.getList().size(); i++) {
			selectClinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		selectClinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectClinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(selectClinic);

		JLabel lblPoliklinikAd_1 = new JLabel("Doktor Se\u00E7");
		lblPoliklinikAd_1.setForeground(new Color(0, 139, 139));
		lblPoliklinikAd_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPoliklinikAd_1.setBounds(10, 261, 110, 25);
		w_appointment.add(lblPoliklinikAd_1);

		JButton btnSelDoctor = new JButton("Se\u00E7");
		btnSelDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();

				} else {
					Mesaj.showMsg("Lütfen bir doktor seçiniz !");
				}
			}
		});
		btnSelDoctor.setForeground(new Color(255, 245, 238));
		btnSelDoctor.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnSelDoctor.setBackground(new Color(0, 139, 139));
		btnSelDoctor.setBounds(10, 289, 165, 33);
		w_appointment.add(btnSelDoctor);

		JLabel lblNewLabel_1_1 = new JLabel("Randevu G\u00FCn ve Saatleri");
		lblNewLabel_1_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(452, 10, 178, 21);
		w_appointment.add(lblNewLabel_1_1);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(452, 30, 257, 221);
		w_appointment.add(w_scrollWhour);

		table_whour = new JTable();
		w_scrollWhour.setViewportView(table_whour);

		JLabel lblPoliklinikAd_1_1 = new JLabel("Randevu");
		lblPoliklinikAd_1_1.setForeground(new Color(0, 139, 139));
		lblPoliklinikAd_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPoliklinikAd_1_1.setBounds(452, 261, 110, 25);
		w_appointment.add(lblPoliklinikAd_1_1);

		JButton btnAddRandevu = new JButton("Randevu Al");
		btnAddRandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addRandevu(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getName(), date);
						if (control) {
							Mesaj.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						} else {
							Mesaj.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Mesaj.showMsg("Lütfen geçerli bir tarih giriniz!");
				}
			}
		});
		btnAddRandevu.setForeground(new Color(255, 245, 238));
		btnAddRandevu.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnAddRandevu.setBackground(new Color(0, 139, 139));
		btnAddRandevu.setBounds(451, 289, 165, 33);
		w_appointment.add(btnAddRandevu);

		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(Color.WHITE);
		w_tab.addTab("Randevularým", null, w_randevu, null);
		w_randevu.setLayout(null);

		JScrollPane w_scroolRandevu = new JScrollPane();
		w_scroolRandevu.setBounds(10, 10, 701, 312);
		w_randevu.add(w_scroolRandevu);

		table_appoint = new JTable(appointModel);
		w_scroolRandevu.setViewportView(table_appoint);
	}

	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}

	public void updateAppointModel(int hasta_id) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < randevu.getHastaList(hasta_id).size(); i++) {
			appointData[0] = randevu.getHastaList(hasta_id).get(i).getId();
			appointData[1] = randevu.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2] = randevu.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
