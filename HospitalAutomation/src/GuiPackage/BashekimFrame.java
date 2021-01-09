package GuiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import DatabaseData.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import DatabaseConnection.*;
import javax.swing.JComboBox;

public class BashekimFrame extends JFrame {

	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fldDoctorName;
	private JTextField fldDoctorTcno;
	private JTextField fldDoctorPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fldClinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	private JTextField txtDoctorName;

	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimFrame frame = new BashekimFrame(bashekim);
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
	public BashekimFrame(Bashekim bashekim) throws SQLException, ClassNotFoundException {

		// Doctor Model
		doctorModel = new DefaultTableModel();

		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getUserName();
			doctorData[3] = bashekim.getDoctorList().get(i).getUserPass();
			doctorModel.addRow(doctorData);
		}

		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adý";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		// Worker Model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Otomasyon Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 485);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 245, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn " + bashekim.getName());
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setBounds(10, 10, 256, 39);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setForeground(new Color(0, 139, 139));
		w_tab.setFont(new Font("Century Gothic", Font.BOLD, 15));
		w_tab.setBounds(10, 59, 726, 366);
		w_pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1.setBounds(423, 39, 145, 25);
		w_doctor.add(lblNewLabel_1);

		fldDoctorName = new JTextField();
		fldDoctorName.setBounds(423, 63, 145, 25);
		w_doctor.add(fldDoctorName);
		fldDoctorName.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("TC No");
		lblNewLabel_1_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(423, 98, 145, 25);
		w_doctor.add(lblNewLabel_1_1);

		fldDoctorTcno = new JTextField();
		fldDoctorTcno.setColumns(10);
		fldDoctorTcno.setBounds(423, 122, 145, 25);
		w_doctor.add(fldDoctorTcno);

		JLabel lblNewLabel_1_2 = new JLabel("Þifre");
		lblNewLabel_1_2.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(423, 164, 145, 25);
		w_doctor.add(lblNewLabel_1_2);

		fldDoctorPass = new JTextField();
		fldDoctorPass.setColumns(10);
		fldDoctorPass.setBounds(423, 188, 145, 25);
		w_doctor.add(fldDoctorPass);

		JButton btnAddDoctor = new JButton("Ekle");
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fldDoctorName.getText().length() == 0 || fldDoctorPass.getText().length() == 0
						|| fldDoctorTcno.getText().length() == 0) {
					Mesaj.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fldDoctorTcno.getText(), fldDoctorPass.getText(),
								fldDoctorName.getText());
						if (control) {
							Mesaj.showMsg("success");
							fldDoctorName.setText(null);
							fldDoctorTcno.setText(null);
							fldDoctorPass.setText(null);
							updateDoctorModel();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddDoctor.setForeground(new Color(255, 245, 238));
		btnAddDoctor.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnAddDoctor.setBackground(new Color(0, 139, 139));
		btnAddDoctor.setBounds(423, 223, 145, 25);
		w_doctor.add(btnAddDoctor);

		JLabel lblNewLabel_1_2_1 = new JLabel("Kullanýcý Id");
		lblNewLabel_1_2_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_2_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_2_1.setBounds(586, 39, 125, 25);
		w_doctor.add(lblNewLabel_1_2_1);

		fld_doctorID = new JTextField();
		fld_doctorID.setBackground(new Color(255, 245, 238));
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(586, 63, 125, 25);
		w_doctor.add(fld_doctorID);

		JButton btnDelDoctor = new JButton("Sil");
		btnDelDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Mesaj.showMsg("Lütfen geçerli bir doktor seçiniz!");
				} else {
					if (Mesaj.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Mesaj.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDelDoctor.setForeground(new Color(255, 245, 238));
		btnDelDoctor.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnDelDoctor.setBackground(new Color(0, 139, 139));
		btnDelDoctor.setBounds(586, 98, 125, 25);
		w_doctor.add(btnDelDoctor);

		JScrollPane w_scrolldoctor = new JScrollPane();
		w_scrolldoctor.setBounds(10, 10, 403, 312);
		w_doctor.add(w_scrolldoctor);

		table_doctor = new JTable(doctorModel);
		w_scrolldoctor.setViewportView(table_doctor);

		JButton btnExitDoctor = new JButton("Çýkýþ");
		btnExitDoctor.setBounds(641, 288, 70, 27);
		w_doctor.add(btnExitDoctor);
		btnExitDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnExitDoctor.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExitDoctor.setBackground(new Color(0, 139, 139));
		btnExitDoctor.setForeground(new Color(255, 245, 238));

		JLabel lblNewLabel_1_3 = new JLabel("Doktor Ad\u0131");
		lblNewLabel_1_3.setForeground(new Color(0, 139, 139));
		lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(586, 164, 96, 25);
		w_doctor.add(lblNewLabel_1_3);

		txtDoctorName = new JTextField();
		txtDoctorName.setColumns(10);
		txtDoctorName.setBounds(586, 188, 125, 25);
		w_doctor.add(txtDoctorName);

		JButton btnDoctorListele = new JButton("Listele");
		btnDoctorListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
				clearModel.setRowCount(0);
				try {
					for (int i = 0; i < bashekim.getDoctorListele(txtDoctorName.getText()).size(); i++) {
						doctorData[0] = bashekim.getDoctorListele(txtDoctorName.getText()).get(i).getId();
						doctorData[1] = bashekim.getDoctorListele(txtDoctorName.getText()).get(i).getName();
						doctorData[2] = bashekim.getDoctorListele(txtDoctorName.getText()).get(i).getUserName();
						doctorData[3] = bashekim.getDoctorListele(txtDoctorName.getText()).get(i).getUserPass();

						doctorModel.addRow(doctorData);
					}
					table_doctor.setModel(doctorModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDoctorListele.setForeground(new Color(255, 245, 238));
		btnDoctorListele.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnDoctorListele.setBackground(new Color(0, 139, 139));
		btnDoctorListele.setBounds(586, 223, 125, 25);
		w_doctor.add(btnDoctorListele);

		JLabel lblNewLabel_1_4 = new JLabel("DOKTOR EKLE");
		lblNewLabel_1_4.setBackground(Color.WHITE);
		lblNewLabel_1_4.setForeground(new Color(233, 150, 122));
		lblNewLabel_1_4.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(423, 10, 108, 25);
		w_doctor.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_4_1 = new JLabel("DOKTOR S\u0130L");
		lblNewLabel_1_4_1.setForeground(new Color(233, 150, 122));
		lblNewLabel_1_4_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblNewLabel_1_4_1.setBounds(586, 10, 108, 25);
		w_doctor.add(lblNewLabel_1_4_1);

		JLabel lblNewLabel_1_4_1_1 = new JLabel("DOKTOR L\u0130STELE");
		lblNewLabel_1_4_1_1.setForeground(new Color(233, 150, 122));
		lblNewLabel_1_4_1_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblNewLabel_1_4_1_1.setBounds(586, 133, 125, 25);
		w_doctor.add(lblNewLabel_1_4_1_1);

		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollclinic = new JScrollPane();
		w_scrollclinic.setBounds(10, 10, 260, 247);
		w_clinic.add(w_scrollclinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selId = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selId);
				UpdateClinicFrame updateFrame = new UpdateClinicFrame(selectClinic);
				updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateFrame.setVisible(true);
				updateFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Mesaj.confirm("sure")) {
					int selId = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selId)) {
							Mesaj.showMsg("success");
							updateClinicModel();
						} else {
							Mesaj.showMsg("error");
						}
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});

		w_scrollclinic.setViewportView(table_clinic);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adý");
		lblPoliklinikAd.setForeground(new Color(0, 139, 139));
		lblPoliklinikAd.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPoliklinikAd.setBounds(10, 265, 110, 25);
		w_clinic.add(lblPoliklinikAd);

		fldClinicName = new JTextField();
		fldClinicName.setColumns(10);
		fldClinicName.setBounds(10, 288, 165, 25);
		w_clinic.add(fldClinicName);

		JButton btnAddClinic = new JButton("Ekle");
		btnAddClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fldClinicName.getText().length() == 0) {
					Mesaj.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fldClinicName.getText())) {
							Mesaj.showMsg("success");
							fldClinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnAddClinic.setForeground(new Color(255, 245, 238));
		btnAddClinic.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnAddClinic.setBackground(new Color(0, 139, 139));
		btnAddClinic.setBounds(185, 288, 74, 25);
		w_clinic.add(btnAddClinic);

		JScrollPane w_scrollworker = new JScrollPane();
		w_scrollworker.setBounds(453, 10, 258, 247);
		w_clinic.add(w_scrollworker);

		table_worker = new JTable();
		w_scrollworker.setViewportView(table_worker);

		JComboBox selectDoctor = new JComboBox();
		selectDoctor.setBounds(280, 151, 165, 33);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			selectDoctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		selectDoctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			// System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(selectDoctor);

		JButton btnAddWorker = new JButton("Ekle");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) selectDoctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Mesaj.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);

						} else {
							Mesaj.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Mesaj.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btnAddWorker.setForeground(new Color(255, 245, 238));
		btnAddWorker.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnAddWorker.setBackground(new Color(0, 139, 139));
		btnAddWorker.setBounds(280, 194, 165, 33);
		w_clinic.add(btnAddWorker);

		JLabel lblPoliklinikAd_1 = new JLabel("Poliklinik Ad\u0131");
		lblPoliklinikAd_1.setForeground(new Color(0, 139, 139));
		lblPoliklinikAd_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPoliklinikAd_1.setBounds(280, 26, 110, 25);
		w_clinic.add(lblPoliklinikAd_1);

		JButton btnWorkerSelect = new JButton("Se\u00E7");
		btnWorkerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Mesaj.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btnWorkerSelect.setForeground(new Color(255, 245, 238));
		btnWorkerSelect.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnWorkerSelect.setBackground(new Color(0, 139, 139));
		btnWorkerSelect.setBounds(280, 55, 165, 33);
		w_clinic.add(btnWorkerSelect);

		JLabel lblDoktorAd = new JLabel("Doktor Ad\u0131");
		lblDoktorAd.setForeground(new Color(0, 139, 139));
		lblDoktorAd.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblDoktorAd.setBounds(280, 122, 110, 25);
		w_clinic.add(lblDoktorAd);

		JButton btnExitClinic = new JButton("\u00C7\u0131k\u0131\u015F");
		btnExitClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnExitClinic.setForeground(new Color(255, 245, 238));
		btnExitClinic.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExitClinic.setBackground(new Color(0, 139, 139));
		btnExitClinic.setBounds(641, 288, 70, 27);
		w_clinic.add(btnExitClinic);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getUserName();
			doctorData[3] = bashekim.getDoctorList().get(i).getUserPass();

			doctorModel.addRow(doctorData);
		}
	}

	public void updateClinicModel() throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

	}
}
