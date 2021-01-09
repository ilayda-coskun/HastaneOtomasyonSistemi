package GuiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import DatabaseConnection.Mesaj;
import DatabaseData.Clinic;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fldClinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicFrame frame = new UpdateClinicFrame(clinic);
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
	public UpdateClinicFrame(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fldClinicName = new JTextField();
		fldClinicName.setForeground(new Color(0, 128, 128));
		fldClinicName.setColumns(10);
		fldClinicName.setBounds(21, 34, 165, 25);
		fldClinicName.setText(clinic.getName());
		contentPane.add(fldClinicName);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Ad\u0131");
		lblPoliklinikAd.setForeground(new Color(0, 139, 139));
		lblPoliklinikAd.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPoliklinikAd.setBounds(21, 10, 110, 25);
		contentPane.add(lblPoliklinikAd);

		JButton btnUpdateClinic = new JButton("D\u00FCzenle");
		btnUpdateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Mesaj.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fldClinicName.getText());
						Mesaj.showMsg("success");
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnUpdateClinic.setForeground(new Color(255, 245, 238));
		btnUpdateClinic.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnUpdateClinic.setBackground(new Color(0, 139, 139));
		btnUpdateClinic.setBounds(21, 62, 165, 33);
		contentPane.add(btnUpdateClinic);
	}
}
