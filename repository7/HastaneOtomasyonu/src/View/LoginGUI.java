package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.Helper;
import Model.Bashekim;
import Model.doctor;
import Model.hasta;
import View.BashekimGUI;

public class LoginGUI extends JFrame {

	private JPanel w_Pane;
	private JTextField txt_tcNo;
	private JTextField txt_sifre;
	private JTextField txt_docTc;
	private JTextField txt_docPass;
	private DbConnection conn = new DbConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setForeground(Color.WHITE);
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 410);
		w_Pane = new JPanel();
		w_Pane.setBackground(Color.WHITE);
		w_Pane.setForeground(Color.WHITE);
		w_Pane.setToolTipText("Hastane Otomasyonu");
		w_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_Pane);
		w_Pane.setLayout(null);

		String path = "C:/Users/inspi/eclipse-workspace/HastaneOtomasyonu/src/medcine.png";

//		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource(path)));
//		lbl_logo.setBounds(149, 10, 139, 148);
//		w_Pane.add(lbl_logo);

		JTabbedPane w_TabPane = new JTabbedPane(JTabbedPane.TOP);
		w_TabPane.setBounds(20, 219, 397, 153);
		w_Pane.add(w_TabPane);

		JPanel w_Docpanel = new JPanel();
		w_Docpanel.setForeground(new Color(0, 0, 0));
		w_Docpanel.setBackground(Color.WHITE);
		w_TabPane.addTab("Hasta Girisi", null, w_Docpanel, null);
		w_Docpanel.setLayout(null);

		JLabel lbl_tcNo = new JLabel("T.C Numarasi : ");
		lbl_tcNo.setBounds(10, 10, 167, 30);
		w_Docpanel.add(lbl_tcNo);
		lbl_tcNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));

		JLabel lbl_Sifre = new JLabel("Sifre  : ");
		lbl_Sifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lbl_Sifre.setBounds(10, 50, 167, 30);
		w_Docpanel.add(lbl_Sifre);

		txt_tcNo = new JTextField();
		txt_tcNo.setFont(new Font("Arial", Font.BOLD, 18));
		txt_tcNo.setBounds(162, 10, 192, 30);
		w_Docpanel.add(txt_tcNo);
		txt_tcNo.setColumns(10);

		txt_sifre = new JTextField();
		txt_sifre.setFont(new Font("Arial", Font.BOLD, 18));
		txt_sifre.setColumns(10);
		txt_sifre.setBounds(162, 50, 192, 30);
		w_Docpanel.add(txt_sifre);

		JButton btn_kayitOl = new JButton("Kayit Ol");
		btn_kayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();

			}
		});
		btn_kayitOl.setFont(new Font("Arial", Font.BOLD, 13));
		btn_kayitOl.setBackground(Color.WHITE);
		btn_kayitOl.setBounds(162, 81, 85, 35);
		w_Docpanel.add(btn_kayitOl);

		JButton btn_girisYap = new JButton("Giris Yap");
		btn_girisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_sifre.getText().length() == 0 || txt_tcNo.getText().length() == 0) {
					Helper.showMsg("Fill");
				} else {
					;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM USER");
						while (rs.next()) {
							if (txt_sifre.getText().equals(rs.getString("password"))
									|| txt_tcNo.getText().equals(rs.getString("tcno"))) {

								try {
									if (txt_sifre.getText().equals(rs.getString("password"))
											|| txt_tcNo.getText().equals(rs.getString("tcno"))) {
										if (rs.getString("type").equals("hasta")) {
											hasta hasta1 = new hasta();
											hasta1.setId(rs.getInt("id"));
											hasta1.setPassword(rs.getString("password"));
											hasta1.setTcno(rs.getString("tcno"));
											hasta1.setName(rs.getString("name"));
											hasta1.setType(rs.getString("type"));
											HastaGUI hGUI;
											hGUI = new HastaGUI(hasta1);
											hGUI.setVisible(true);
											dispose();
										}

									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_girisYap.setBackground(Color.WHITE);
		btn_girisYap.setFont(new Font("Arial", Font.BOLD, 13));
		btn_girisYap.setBounds(250, 81, 104, 35);
		w_Docpanel.add(btn_girisYap);

		JPanel w_DoktorLogin = new JPanel();
		w_DoktorLogin.setBackground(Color.WHITE);
		w_TabPane.addTab("Doktor Giris", null, w_DoktorLogin, null);
		w_DoktorLogin.setLayout(null);

		JPanel w_Docpanel_1 = new JPanel();
		w_Docpanel_1.setLayout(null);
		w_Docpanel_1.setForeground(Color.BLACK);
		w_Docpanel_1.setBackground(Color.WHITE);
		w_Docpanel_1.setBounds(0, 0, 392, 126);
		w_DoktorLogin.add(w_Docpanel_1);

		JLabel lbl_tcNo_1 = new JLabel("T.C Numarasi : ");
		lbl_tcNo_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lbl_tcNo_1.setBounds(10, 10, 167, 30);
		w_Docpanel_1.add(lbl_tcNo_1);

		JLabel lbl_Sifre_1 = new JLabel("Sifre  : ");
		lbl_Sifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lbl_Sifre_1.setBounds(10, 50, 167, 30);
		w_Docpanel_1.add(lbl_Sifre_1);

		txt_docTc = new JTextField();
		txt_docTc.setFont(new Font("Arial", Font.BOLD, 18));
		txt_docTc.setColumns(10);
		txt_docTc.setBounds(162, 10, 192, 30);
		w_Docpanel_1.add(txt_docTc);

		txt_docPass = new JTextField();
		txt_docPass.setFont(new Font("Arial", Font.BOLD, 18));
		txt_docPass.setColumns(10);
		txt_docPass.setBounds(162, 50, 192, 30);
		w_Docpanel_1.add(txt_docPass);

		JButton btn_girisYap_1 = new JButton("Giris Yap");
		btn_girisYap_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_docTc.getText().length() == 0 || txt_docPass.getText().length() == 0) {
					Helper.showMsg("Fill");
				} else {

					try {

						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM USER");
						while (rs.next()) {
							if (txt_docTc.getText().equals(rs.getString("tcno"))
									|| txt_docPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("doctor")) {
									doctor doc = new doctor();
									doc.setId(rs.getInt("id"));
									doc.setPassword(rs.getString("password"));
									doc.setTcno(rs.getString("tcno"));
									doc.setName(rs.getString("name"));
									doc.setType(rs.getString("type"));
									doctorGUI dGUI = new doctorGUI(doc);
									dGUI.setVisible(true);
									dispose();

								}
								if (rs.getString("type").equals("basHekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setPassword(rs.getString("password"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									System.out.println(bhekim.getName());
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (Exception e1) {
					}

				}
			}

		});
		btn_girisYap_1.setBackground(Color.WHITE);
		btn_girisYap_1.setFont(new Font("Arial", Font.BOLD, 13));
		btn_girisYap_1.setBounds(162, 81, 192, 35);
		w_Docpanel_1.add(btn_girisYap_1);
	}

}