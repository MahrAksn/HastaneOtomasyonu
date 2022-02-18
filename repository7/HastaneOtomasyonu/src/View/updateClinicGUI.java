package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;

public class updateClinicGUI extends JFrame {

	private JPanel w_updateClinic;
	private JTextField txt_upClinic;
	private static Model.clinic clinic;
	Helper helper = new Helper();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateClinicGUI frame = new updateClinicGUI(clinic);
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
	public updateClinicGUI(Model.clinic clinic) {
		setEnabled(false);
		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 174, 173);
		w_updateClinic = new JPanel();
		w_updateClinic.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_updateClinic);
		w_updateClinic.setLayout(null);

		txt_upClinic = new JTextField();
		txt_upClinic.setColumns(10);
				txt_upClinic.setText(clinic.getName());

		txt_upClinic.setBounds(10, 42, 136, 19);
		w_updateClinic.add(txt_upClinic);


		JLabel lbl_polName = new JLabel("Poliklinik \u0130smi");
		lbl_polName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_polName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lbl_polName.setBounds(10, 10, 136, 22);
		w_updateClinic.add(lbl_polName);

		JButton btn_updateClinic = new JButton("Duzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (helper.confrim("sure")) {

					try {
						clinic.updateClinic(clinic.getId(), txt_upClinic.getText());
						helper.showMsg("Fill");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_updateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_updateClinic.setBounds(10, 70, 136, 59);
		w_updateClinic.add(btn_updateClinic);
	}
}
