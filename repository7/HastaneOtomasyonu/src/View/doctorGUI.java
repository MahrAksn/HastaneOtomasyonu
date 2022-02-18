package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.doctor;
import Model.wHour;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class doctorGUI extends JFrame {

	private JPanel contentPane;
	private static doctor doctor;
	private JTextField txt_day;
	private JTextField txt_mouth;
	private DefaultTableModel whourModel;
	Object[] whourData;
	private JTable whour_table;
//	private doctor doctor=new doctorGUI();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorGUI frame = new doctorGUI(doctor);
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
	public doctorGUI(doctor doctor) {

		whourModel = new DefaultTableModel();
		Object[] colWhourName = new Object[2];
		colWhourName[0] = "ID";
		colWhourName[1] = "Doctor ID";
		whourModel.setColumnIdentifiers(colWhourName);
		whourData = new Object[2];

		try {
			for (int i = 0; i < doctor.getWhourlist(doctor.getId()).size(); i++) {
				whourData[0] = doctor.getWhourlist(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourlist(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 716, 375);
		contentPane.add(scrollPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);

		JPanel w_panel = new JPanel();
		w_panel.setBackground(Color.WHITE);
		tabbedPane.addTab("New tab", null, w_panel, null);
		w_panel.setLayout(null);

		JComboBox select_wHour = new JComboBox();
		select_wHour.setFont(new Font("Arial", Font.BOLD, 15));
		select_wHour.setModel(new DefaultComboBoxModel(
				new String[] { "10:00", "10:30", "11:00", "11:30", "12:00", "13:30", "14:00", "14:30", "15:00" }));
		select_wHour.setBounds(248, 11, 117, 21);
		w_panel.add(select_wHour);

		JButton btn_addWhour = new JButton("ADD");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_day.getText().length() == 0 || txt_mouth.getText().length() == 0
						|| select_wHour.getSelectedItem() == "") {
					Helper.showMsg("Fill");
				} else {
					String selectHour = (String) select_wHour.getSelectedItem();
					String selectDay = txt_day.getText();
					String selectMouth = txt_mouth.getText();
					String wDate = selectHour + "-" + selectDay + "//" + selectMouth;

					try {
						int control = doctor.addDoctor(doctor.getId(), doctor.getName(), wDate);
						if (control == 1) {
							Helper.showMsg("succes");
							updateWhpurModel(doctor);
						} else
							Helper.showMsg("Lutfen gecerli bir saat dilimi giriniz.");

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btn_addWhour.setFont(new Font("Arial", Font.BOLD, 16));
		btn_addWhour.setBounds(375, 10, 85, 21);
		w_panel.add(btn_addWhour);

		JLabel lblNewLabel_1_1 = new JLabel("Day Date");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 11, 78, 21);
		w_panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Mouth Date");
		lblNewLabel_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 42, 78, 21);
		w_panel.add(lblNewLabel_1_1_1);

		txt_day = new JTextField();
		txt_day.setBounds(98, 13, 126, 19);
		w_panel.add(txt_day);
		txt_day.setColumns(10);

		txt_mouth = new JTextField();
		txt_mouth.setColumns(10);
		txt_mouth.setBounds(98, 44, 126, 19);
		w_panel.add(txt_mouth);

		JScrollPane w_scrallPane = new JScrollPane();
		w_scrallPane.setBounds(10, 86, 689, 250);
		w_panel.add(w_scrallPane);

		whour_table = new JTable(whourModel);
		w_scrallPane.setViewportView(whour_table);

		JButton btn_deleteWhour = new JButton("DELETE");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = whour_table.getSelectedRow();
				if (selRow >= 0) {
					String selID = whour_table.getModel().getValueAt(selRow, 0).toString();

					int selWID = Integer.parseInt(selID);
					try {
						boolean control = doctor.deleteWhur(selWID);

						if (control) {
							Helper.showMsg("succes");
							updateWhpurModel(doctor);
						} else
							Helper.showMsg("Tarih seciniz.");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_deleteWhour.setFont(new Font("Arial", Font.BOLD, 16));
		btn_deleteWhour.setBounds(589, 12, 110, 21);
		w_panel.add(btn_deleteWhour);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz, Sayin " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 238, 28);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Cikis Yap");
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnNewButton_1.setBounds(628, 12, 98, 28);
		contentPane.add(btnNewButton_1);

	}

	public void updateWhpurModel(doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) whour_table.getModel();
		clearModel.setRowCount(0);
		whourData = new Object[2];

		try {
			for (int i = 0; i < doctor.getWhourlist(doctor.getId()).size(); i++) {
				whourData[0] = doctor.getWhourlist(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourlist(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
