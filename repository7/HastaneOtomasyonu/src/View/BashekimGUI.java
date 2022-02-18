package View;

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
import Model.Bashekim;
import Model.clinic;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	static clinic clnic = new clinic();
	static Bashekim bashekim = new Bashekim();
	private JPanel w_Pane;
	private JTextField txt_adSoyad;
	private JTextField txt_tcno;
	private JTextField txt_pass;
	private JTextField txt_userID;
	private JTable table_doctro;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField txt_polName;
	private JTable clinic_table;
	private JTable table_worker;
	private DefaultTableModel clinicModel = null;
	private Object[] cilinicData = null;

	private JPopupMenu clinicMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	// DoctorModel
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctorumnName = new Object[4];
		colDoctorumnName[0] = "ID";
		colDoctorumnName[1] = "Name";
		colDoctorumnName[2] = "TC NO";
		colDoctorumnName[3] = "Password";
		doctorModel.setColumnIdentifiers(colDoctorumnName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorlist().size(); i++) {
			doctorData[0] = bashekim.getDoctorlist().get(i).getId();
			doctorData[1] = bashekim.getDoctorlist().get(i).getName();
			doctorData[2] = bashekim.getDoctorlist().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorlist().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		// WorkerModel
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "id";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Yonetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 507);
		w_Pane = new JPanel();
		w_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_Pane);
		w_Pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz, Sayin " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 4, 238, 28);
		w_Pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Cikis Yap");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnNewButton.setBounds(628, 6, 108, 28);
		w_Pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 99, 726, 370);
		w_Pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yonetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("T.C NO");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(540, 71, 120, 22);
		w_doctor.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Ad Soyad");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(540, 10, 120, 22);
		w_doctor.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Sifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(540, 112, 120, 22);
		w_doctor.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Kullanici ID");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(540, 244, 120, 22);
		w_doctor.add(lblNewLabel_1_3);

		txt_adSoyad = new JTextField();
		txt_adSoyad.setBounds(540, 42, 136, 19);
		w_doctor.add(txt_adSoyad);
		txt_adSoyad.setColumns(10);

		txt_tcno = new JTextField();
		txt_tcno.setColumns(10);
		txt_tcno.setBounds(540, 91, 136, 19);
		w_doctor.add(txt_tcno);

		txt_pass = new JTextField();
		txt_pass.setColumns(10);
		txt_pass.setBounds(540, 132, 136, 19);
		w_doctor.add(txt_pass);

		txt_userID = new JTextField();
		txt_userID.setBackground(Color.WHITE);
		txt_userID.setEnabled(false);
		txt_userID.setColumns(10);
		txt_userID.setBounds(540, 263, 136, 19);
		w_doctor.add(txt_userID);

		JButton btn_ekle = new JButton("ADD");
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_adSoyad.getText().length() == 0 || txt_tcno.getText().length() == 0
						|| txt_pass.getText().length() == 0) {
					Helper.showMsg("Fill");
				} else {
					boolean control = bashekim.addDoctor(txt_tcno.getText(), txt_pass.getText(), txt_adSoyad.getText());
					if (control) {
						try {
							Helper.showMsg("succes");
							txt_adSoyad.setText("");
							txt_pass.setText("");
							txt_tcno.setText("");
							updateDoctorModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_ekle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_ekle.setBounds(540, 175, 136, 59);
		w_doctor.add(btn_ekle);

		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_userID.getText().length() == 0) {
					Helper.showMsg("Lutfen gecerli bir doktor seciniz..");
				} else {
					if (Helper.confrim("sure")) {
						int selectID = Integer.parseInt(txt_userID.getText());

						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("succes");
								txt_userID.setText("");
								updateDoctorModel();
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}

				}
			}
		});
		btn_delete.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_delete.setBounds(540, 292, 136, 43);
		w_doctor.add(btn_delete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 530, 333);
		w_doctor.add(scrollPane);

		table_doctro = new JTable(doctorModel);
		scrollPane.setViewportView(table_doctro);

		clinicModel = new DefaultTableModel();
		Object[] clinicColName = new Object[2];
		clinicColName[0] = "ID";
		clinicColName[1] = "Name";
		clinicModel.setColumnIdentifiers(clinicColName);

		Object[] clinicData = new Object[2];
		for (int i = 0; i < clnic.getClinicList().size(); i++) {

			clinicData[0] = clnic.getClinicList().get(i).getId();
			clinicData[1] = clnic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		JPanel clinic_panel = new JPanel();
		clinic_panel.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, clinic_panel, null);
		clinic_panel.setLayout(null);

		JLabel lbl_pName = new JLabel("Poliklinik \u0130smi");
		lbl_pName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lbl_pName.setBounds(287, 10, 136, 22);
		clinic_panel.add(lbl_pName);
		JButton btn_clinicAdd = new JButton("ADD");
		btn_clinicAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (txt_polName.getText().length() == 0 || clnic.getClinicList().size() == 0) {
						Helper.showMsg("FÝll");
					} else {
						boolean control = clnic.clinicAdd(txt_polName.getText());
						if (control) {
							Helper.showMsg("succes");
							txt_polName.setText("");
							updateClinicModel();
						} else
							Helper.showMsg("Istenmeyen bir durum olustu.Lutfen tekrar deneyiniz.. ");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String selectName = txt_polName.getText();
			}
		});

		txt_polName = new JTextField();
		txt_polName.setColumns(10);
		txt_polName.setBounds(287, 42, 136, 19);
		clinic_panel.add(txt_polName);

		btn_clinicAdd.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_clinicAdd.setBounds(287, 71, 136, 31);
		clinic_panel.add(btn_clinicAdd);

		JScrollPane clinic_scroll = new JScrollPane();
		clinic_scroll.setBounds(10, 10, 267, 323);
		clinic_panel.add(clinic_scroll);
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Guncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int selecedId = Integer
							.parseInt(clinic_table.getValueAt(clinic_table.getSelectedRow(), 0).toString());
					clinic selectClinic = clnic.getFetch(selecedId);
					updateClinicGUI updateGI = new updateClinicGUI(selectClinic);
					updateGI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					updateGI.setVisible(true);
					updateGI.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							try {
								updateClinicModel();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confrim("sure")) {
					int selId = Integer.parseInt(clinic_table.getValueAt(clinic_table.getSelectedRow(), 0).toString());

					try {
						if (clnic.deleteClinic(selId)) {
							Helper.showMsg("succes");
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}
				}
			}
		});

		clinic_table = new JTable(clinicModel);
		clinic_scroll.setViewportView(clinic_table);
		clinic_table.setComponentPopupMenu(clinicMenu);
		clinic_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int selectRow = clinic_table.rowAtPoint(point);
				clinic_table.setRowSelectionInterval(selectRow, selectRow);
				super.mouseClicked(e);
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(433, 10, 278, 323);
		clinic_panel.add(scrollPane_1);

		table_worker = new JTable();
		scrollPane_1.setViewportView(table_worker);

		JComboBox<item> cmb_doctor = new JComboBox();
		cmb_doctor.setForeground(Color.WHITE);
		cmb_doctor.setFont(new Font("Arial", Font.BOLD, 16));
		cmb_doctor.setBounds(288, 244, 135, 31);
		clinic_panel.add(cmb_doctor);

		JButton btn_doctorAdd = new JButton("EKLE");
		btn_doctorAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = clinic_table.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = clinic_table.getValueAt(clinic_table.getSelectedRow(), 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					item selUserID = (item) cmb_doctor.getSelectedItem();

					boolean control = bashekim.addWorker(selUserID.getKey(), selClinicID);
					if (control) {
						Helper.showMsg("succes");

					} else
						Helper.showMsg("ERROR");
				} else
					Helper.showMsg("Bir Poliklinik seciniz..");
			}
		});
		btn_doctorAdd.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_doctorAdd.setBounds(287, 285, 136, 31);
		clinic_panel.add(btn_doctorAdd);

		JButton btn_sec = new JButton("SELECT");
		btn_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selClinicRow = clinic_table.getSelectedRow();
				if (selClinicRow >= 0) {
					String selclinic = clinic_table.getValueAt(selClinicRow, 0).toString();
					int selClinicID = Integer.parseInt(selclinic);
					DefaultTableModel cleariModel = (DefaultTableModel) table_worker.getModel();
					cleariModel.setRowCount(selClinicID);

					try {
						for (int i = 0; i < bashekim.getClinicDoctor(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctor(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctor(selClinicID).get(i).getTcno();
//							workerData[1] = bashekim.getDoctorlist().get(i).getName();
							//workerData[1] = bashekim.getClinicDoctor(selClinicID).get(i).getName();

							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Poliklinik Seciniz..");
				}
				table_worker.setModel(workerModel);
			}
		});
		btn_sec.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_sec.setBounds(287, 157, 136, 31);
		clinic_panel.add(btn_sec);

		JLabel lbl_pName_1 = new JLabel("Poliklinik \u0130smi");
		lbl_pName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pName_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lbl_pName_1.setBounds(287, 128, 136, 22);
		clinic_panel.add(lbl_pName_1);

		for (int i = 0; i < bashekim.getDoctorlist().size(); i++) {
			cmb_doctor.addItem(
					new item(bashekim.getDoctorlist().get(i).getId(), (bashekim.getDoctorlist().get(i).getName())));
		}
		cmb_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			item item = (item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		table_doctro.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txt_userID.setText(table_doctro.getValueAt(table_doctro.getSelectedRow(), 0).toString());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		table_doctro.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selecedID = Integer
							.parseInt(table_doctro.getValueAt(table_doctro.getSelectedRow(), 0).toString());
					String selecedName = table_doctro.getValueAt(table_doctro.getSelectedRow(), 1).toString();
					String selecedtc = table_doctro.getValueAt(table_doctro.getSelectedRow(), 2).toString();
					String selecedPass = table_doctro.getValueAt(table_doctro.getSelectedRow(), 3).toString();
					try {
						boolean result = bashekim.updateDoctor(selecedID, selecedtc, selecedPass, selecedName);
						if (result) {
							// Helper.showMsg("succes");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	public void updateClinicModel() throws SQLException {

		DefaultTableModel clearClinicModel = (DefaultTableModel) clinic_table.getModel();
		clearClinicModel.setRowCount(0);
		cilinicData = new Object[2];
		try {
			for (int i = 0; i < clnic.getClinicList().size(); i++) {

				cilinicData[0] = clnic.getClinicList().get(i).getId();
				cilinicData[1] = clnic.getClinicList().get(i).getName();
				clinicModel.addRow(cilinicData);
			}
		} catch (SQLException e) {

		}
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctro.getModel();
		clearModel.setRowCount(0);
		doctorData = new Object[4];

		for (int i = 0; i < bashekim.getDoctorlist().size(); i++) {
			doctorData[0] = bashekim.getDoctorlist().get(i).getId();
			doctorData[1] = bashekim.getDoctorlist().get(i).getName();
			doctorData[2] = bashekim.getDoctorlist().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorlist().get(i).getPassword();
			clearModel.addRow(doctorData);

		}
	}
}
