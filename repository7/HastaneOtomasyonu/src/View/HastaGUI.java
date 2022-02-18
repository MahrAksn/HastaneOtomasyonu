package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.item;
import Model.Appointment;
import Model.clinic;
import Model.doctor;
import Model.hasta;
import Model.wHour;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {
	private JPanel contentPane;
	private static hasta hasta1 = new hasta();
	private clinic clinic1 = new clinic();
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_doctor;
	private JTable table_wHour;
	private DefaultTableModel whourModel = null;
	private Object[] whourData = new Object[2];

	private int selDoctorID = 0;
	private String selDoctorName = null;
	wHour whour = new wHour();
	private JTable table_appoint;

	private DefaultTableModel appointModel ;
	private Object[] appointData = new Object[3];
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta1);
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

	public HastaGUI(hasta hasta1) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colNameHasta = new Object[2];

		colNameHasta[0] = "Doctor ID";
		colNameHasta[1] = "Doctor Adi";
		doctorModel.setColumnIdentifiers(colNameHasta);

		Object[] colHastaData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhourName = new Object[2];

		colWhourName[0] = "ID";
		colWhourName[1] = "TARIH";
		whourModel.setColumnIdentifiers(colWhourName);

		Object[] colWhourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppointName = new Object[3];

		colAppointName[0] = "ID";
		colAppointName[1] = "DOCTOR";
		colAppointName[2] = "TARIH";

		appointModel.setColumnIdentifiers(colAppointName);

		Object[] colAppData = new Object[3];
		for (int i = 0; i < appoint.getHastalist(hasta1.getId()).size(); i++) {
			colAppData[0] = appoint.getHastalist(hasta1.getId()).get(i).getId();
			colAppData[1] = appoint.getHastalist(hasta1.getId()).get(i).getDoctorName();
			colAppData[2] = appoint.getHastalist(hasta1.getId()).get(i).getAppDate();
			appointModel.addRow(colAppData);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz, Sayin " + hasta1.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 238, 28);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Cikis Yap");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnNewButton.setBounds(628, 12, 108, 28);
		contentPane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 89, 716, 474);
		contentPane.add(w_tab);

		JPanel panel = new JPanel();
		w_tab.addTab("Randevu Sistemi", null, panel, null);
		panel.setLayout(null);

		JScrollPane w_docScroll = new JScrollPane();
		w_docScroll.setBounds(10, 38, 250, 386);
		panel.add(w_docScroll);

		table_doctor = new JTable(doctorModel);
		w_docScroll.setViewportView(table_doctor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 15, 147, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Poliklinik Adi");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(300, 38, 101, 13);
		panel.add(lblNewLabel_1_1);

		JComboBox select_poliklinik = new JComboBox();
		select_poliklinik.setBounds(288, 59, 132, 34);
		select_poliklinik.addItem("---Poliklinik Seciniz--");
		for (int i = 0; i < clinic1.getClinicList().size(); i++) {
			select_poliklinik.addItem(
					new item(clinic1.getClinicList().get(i).getId(), clinic1.getClinicList().get(i).getName()));
		}
		select_poliklinik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_poliklinik.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					item item1 = (item) c.getSelectedItem();
					// System.out.println(item1.getKey() + " - " + item1.getValue());
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						doctorData = new Object[2];
						for (int i = 0; i < clinic1.getClinicDoctor(item1.getKey()).size(); i++) {
							doctorData[0] = clinic1.getClinicDoctor(item1.getKey()).get(i).getId();
							doctorData[1] = clinic1.getClinicDoctor(item1.getKey()).get(i).getTcno();
							doctorModel.addRow(doctorData);

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);

				}
			}

		});
		wHour whour = new wHour();
		panel.add(select_poliklinik);

		JScrollPane w_scrollWdate = new JScrollPane();
		w_scrollWdate.setBounds(443, 38, 230, 378);
		panel.add(w_scrollWdate);

		table_wHour = new JTable(whourModel);
		w_scrollWdate.setViewportView(table_wHour);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_wHour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWhourlist(id).size(); i++) {

							whourData[0] = whour.getWhourlist(id).get(i).getId();
							whourData[1] = whour.getWhourlist(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_wHour.setModel(whourModel);
					selDoctorID = id;
					selDoctorName = (String) table_doctor.getModel().getValueAt(row, 1);
				} else {
					Helper.showMsg("Poliklinik veya Doktor seciniz..");
				}

			}
		});
		btnSelect.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnSelect.setBounds(288, 108, 132, 42);
		panel.add(btnSelect);

		JButton btnRandevuAl = new JButton("RANDEVU AL");
		btnRandevuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = table_wHour.getSelectedRow();
				if (selectRow >= 0) {

					try {
						String wDate = (String) table_wHour.getModel().getValueAt(selectRow, 1).toString();
						boolean control = hasta1.addApp(selDoctorID, selDoctorName, hasta1.getId(), hasta1.getName(),
								wDate);
						if (control) {
							Helper.showMsg("succes");
							hasta1.updateStatus(selDoctorID, wDate);
							updateWhour(selDoctorID);
//							updateAppoint(hasta1.getId());

						} else {
							Helper.showMsg("Bir randevu tarihi seciniz..");
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnRandevuAl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnRandevuAl.setBounds(288, 219, 132, 42);
		panel.add(btnRandevuAl);

		JLabel lblNewLabel_1_1_1 = new JLabel("Randevu");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(300, 196, 101, 13);
		panel.add(lblNewLabel_1_1_1);

		JPanel w_appoint = new JPanel();
		w_tab.addTab("RANDEVULARIM", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane scroll_appoint = new JScrollPane();
		scroll_appoint.setBounds(10, 10, 691, 427);
		w_appoint.add(scroll_appoint);

		table_appoint = new JTable(appointModel);
		scroll_appoint.setViewportView(table_appoint);
	}

	public void updateWhour(int doctorID) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_wHour.getModel();
		clearModel.setColumnCount(0);
		for (int i = 0; i < whour.getWhourlist(doctorID).size(); i++) {
			whourData[0] = whour.getWhourlist(doctorID).get(i).getId();
			whourData[1] = whour.getWhourlist(doctorID).get(i).getWdate();
			whourModel.addRow(whourData);
		}

	}

	public void updateAppoint(int hastaID) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setColumnCount(0);
		for (int i = 0; i < appoint.getHastalist(hastaID).size(); i++) {
			appointData[0] = appoint.getHastalist(hastaID).get(i).getId();
			appointData[1] = appoint.getHastalist(hastaID).get(i).getDoctorName();
			appointData[2] = appoint.getHastalist(hastaID).get(i).getAppDate();
			appointModel.addRow(appointData);
		}

	}

}
