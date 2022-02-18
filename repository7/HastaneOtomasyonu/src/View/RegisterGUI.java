package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.hasta;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_adSoyad;
	private JLabel lblNewLabel_1;
	private JTextField txt_tcNo;
	private JLabel lbl_Sifre;
	private JPasswordField txt_Pass;
	private Helper helper = new Helper();
	private hasta hasta1 = new hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yonetim Sistemi");
		setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txt_adSoyad = new JTextField();
		txt_adSoyad.setColumns(10);
		txt_adSoyad.setBounds(25, 42, 192, 27);
		contentPane.add(txt_adSoyad);

		JLabel lblNewLabel_1_1 = new JLabel("Ad Soyad");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(61, 10, 120, 22);
		contentPane.add(lblNewLabel_1_1);

		lblNewLabel_1 = new JLabel("T.C Numara");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1.setBounds(61, 79, 120, 22);
		contentPane.add(lblNewLabel_1);

		txt_tcNo = new JTextField();
		txt_tcNo.setColumns(10);
		txt_tcNo.setBounds(25, 113, 192, 27);
		contentPane.add(txt_tcNo);

		lbl_Sifre = new JLabel("Sifre");
		lbl_Sifre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Sifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lbl_Sifre.setBounds(61, 153, 120, 22);
		contentPane.add(lbl_Sifre);

		txt_Pass = new JPasswordField();
		txt_Pass.setBounds(25, 185, 192, 27);
		contentPane.add(txt_Pass);

		JButton btn_kayitOl = new JButton("Kayit Ol");
		btn_kayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_tcNo.getText().length() == 0 || txt_adSoyad.getText().length() == 0
						|| txt_Pass.getText().length() == 0) {
					helper.showMsg("Fill");
				} else {
					try {
						Boolean control = hasta1.register(txt_adSoyad.getText(), txt_tcNo.getText(),
								txt_Pass.getText());
						if (control) {
							helper.showMsg("succes");
							LoginGUI loginGUI = new LoginGUI();
							loginGUI.setVisible(true);
							dispose();
						} else
							helper.showMsg("error");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_kayitOl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_kayitOl.setBounds(25, 222, 192, 33);
		contentPane.add(btn_kayitOl);

		JButton btn_geriDon = new JButton("Geri Don");
		btn_geriDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_geriDon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		btn_geriDon.setBounds(25, 259, 192, 33);
		contentPane.add(btn_geriDon);
	}
}
