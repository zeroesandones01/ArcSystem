package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXPanel;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

import Database.pgSelect;
import Functions.FncGlobal;
import Functions.FncLookAndFeel;
import Functions.UserInfo;

public class ArcSystemLogin extends JDialog {



	private static final long serialVersionUID = 4816119369731914473L;

	private static JXPanel panMain;
	private static JPanel panPlaceholder; 

	private static JTextField txtUserName;
	private static JPasswordField txtPassword;

	private static JCheckBox chkRemember; 

	private static JButton btnLogin;
	private static JButton btnCancel; 

	private static Font font1 = FncLookAndFeel.systemFont_Plain.deriveFont(9f);
	private static Font font2 = FncLookAndFeel.systemFont_Bold.deriveFont(25f);
	private static Font font3 = FncLookAndFeel.systemFont_Plain.deriveFont(15f);
	private static Font font4 = FncLookAndFeel.systemFont_Bold.deriveFont(12f);
	private static Font font5 = FncLookAndFeel.systemFont_Bold.deriveFont(10f);

	private static Color color_coral = new Color(255, 127, 80);
	private static Color color_tomato = new Color(255, 99, 71);
	private static Color color_orangered = new Color(255, 69, 0);
	private static Color color_gold = new Color(255, 215, 0);
	private static Color color_orange = new Color(255, 165, 0);
	private static Color color_drakorange = new Color(255, 140, 0);
	public static Color systemColor = new Color(0, 132, 188);
	public static Color arcgreen = new Color(29, 191, 83);
	//public static Color arcgreen = new Color(29, 191, 83);
	
	private static String font_name = "SansSerif";
	private static Integer font_size = 12;

	private static JProgressBar progress; 

	private static JLabel lblSubTitle;
	private static JLabel lblTitle;

	private static Timer timer; 

	private static JFrame frame; 
	
	private static Date getCurrentDate = new Date();
	@SuppressWarnings("deprecation")
	private static int getCurrentYear = getCurrentDate.getYear() + 1900;

	public static void showLogin() {
		resetLookAndFeel();
		initialize();
		FncGlobal.initialize(false);
		//FncGlobal.initialize2();

		frame = new JFrame("Login"); 
		LoginGUI();
		
	}

	private static void LoginGUI() {
		panMain = new JXPanel(new BorderLayout(0, 0));
		frame.add(panMain);
		frame.setSize(new Dimension(600, 400));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		{
			{
				Banner panLeft = new Banner(new BorderLayout(5, 5), Color.WHITE, arcgreen);
				panMain.add(panLeft, BorderLayout.CENTER); 
				{
					{
						JPanel panPage = new JPanel(new BorderLayout(5, 5)); 
						panLeft.add(panPage, BorderLayout.PAGE_START); 
						panPage.setPreferredSize(new Dimension(0, 90));
						panPage.setOpaque(false);
						{
							{
								JLabel lblTitle = new JLabel("ARCSYSTEM"); 
								panPage.add(lblTitle, BorderLayout.CENTER); 
								lblTitle.setHorizontalAlignment(JLabel.CENTER);
								lblTitle.setVerticalAlignment(JLabel.BOTTOM);
								lblTitle.setFont(font2);
								lblTitle.setForeground(Color.BLACK);
								lblTitle.setOpaque(false);
								lblTitle.setFont(new Font("DejaVu Sans", Font.BOLD, 25));
							}
//							{
//								JLabel lblTitle = new JLabel("Version 1"); 
//								panPage.add(lblTitle, BorderLayout.PAGE_END); 
//								lblTitle.setHorizontalAlignment(JLabel.CENTER);
//								lblTitle.setVerticalAlignment(JLabel.TOP);
//								lblTitle.setFont(font3);
//								lblTitle.setForeground(Color.WHITE);
//								lblTitle.setOpaque(false);
//								lblTitle.setPreferredSize(new Dimension(0, 40));
//							}
						}
					}
//					{
//						JPanel panWest = new JPanel(new BorderLayout(5,5));
//						panLeft.add(panWest, BorderLayout.LINE_START);
//						panWest.setPreferredSize(new Dimension(50,0));
//						panWest.setOpaque(false);
//						
//					}
//					{
//						JPanel panEast = new JPanel(new BorderLayout(5,5));
//						panLeft.add(panEast, BorderLayout.LINE_END);
//						panEast.setPreferredSize(new Dimension(50,0));
//						panEast.setOpaque(false);
//						
//					}
					{
						//ImageIcon image = new ImageIcon(panMain.getClass().getClassLoader().getResource("Images/clipart.png"));
						ImageIcon image = new ImageIcon(panMain.getClass().getClassLoader().getResource("Images/arclogologin.png"));
						
						JLabel lblIcon = new JLabel(image); 
						panLeft.add(lblIcon, BorderLayout.CENTER); 
						
					}
					{
						JLabel lblCopyright = new JLabel(String.format("ACERLAND REALTY CORPORATION © %s", getCurrentYear)); 
						panLeft.add(lblCopyright, BorderLayout.PAGE_END); 
						lblCopyright.setPreferredSize(new Dimension(0, 15));
						lblCopyright.setForeground(Color.BLACK);
						lblCopyright.setHorizontalAlignment(JLabel.CENTER);
						lblCopyright.setFont(font1);
					}
				}
			}
			{
				JXPanel panRight = new JXPanel(new BorderLayout(0, 0)); 
				panMain.add(panRight, BorderLayout.EAST);
				panRight.setPreferredSize(new Dimension(250, 0));
				panRight.setBackground(arcgreen);
				{
					{
						JXPanel panPage = new JXPanel(new BorderLayout(5, 5)); 
						panRight.add(panPage, BorderLayout.PAGE_START); 
						panPage.setPreferredSize(new Dimension(0, 90));
						panPage.setOpaque(false);
						{
							{
								JLabel lblTitle = new JLabel("Sign In"); 
								panPage.add(lblTitle, BorderLayout.CENTER); 
								lblTitle.setHorizontalAlignment(JLabel.CENTER);
								lblTitle.setVerticalAlignment(JLabel.BOTTOM);
								lblTitle.setFont(font2);
								lblTitle.setForeground(Color.BLACK);
								lblTitle.setOpaque(false);
							}
							{
								panPlaceholder = new JPanel(new BorderLayout(5, 5)); 
								panPage.add(panPlaceholder, BorderLayout.PAGE_END);
								panPlaceholder.setPreferredSize(new Dimension(0, 40));
								{
									lblSubTitle = new JLabel(""); 
									panPage.add(lblSubTitle, BorderLayout.PAGE_END); 
									lblSubTitle.setHorizontalAlignment(JLabel.CENTER);
									lblSubTitle.setVerticalAlignment(JLabel.TOP);
									lblSubTitle.setFont(font3);
									lblSubTitle.setForeground(Color.DARK_GRAY);
									lblSubTitle.setOpaque(false); 
								}
							}
						}
					}
					{
						JXPanel panLine = new JXPanel(new BorderLayout(5, 5)); 
						panRight.add(panLine, BorderLayout.LINE_START); 
						panLine.setPreferredSize(new Dimension(30, 0));
						panLine.setOpaque(false);
					}
					{
						JXPanel panCenter = new JXPanel(new GridLayout(5, 1, 1, 1)); 
						panRight.add(panCenter, BorderLayout.CENTER);
						panCenter.setOpaque(false);
						{
							{
								JLabel lblUsername = new JLabel("Username"); 
								panCenter.add(lblUsername); 
								lblUsername.setFont(font4);
							}
							{
								txtUserName = new JTextField("Input username"); 
								panCenter.add(txtUserName); 
								txtUserName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
								txtUserName.setBackground(null);
								txtUserName.setForeground(Color.GRAY);
								txtUserName.addFocusListener(focus);
								txtUserName.setName("username");
								txtUserName.setOpaque(false);
								txtUserName.setFocusable(true);
								txtUserName.setHorizontalAlignment(JTextField.CENTER);
								txtUserName.addKeyListener(new KeyListener() {
									
									@Override
									public void keyTyped(KeyEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void keyReleased(KeyEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void keyPressed(KeyEvent e) {
										// TODO Auto-generated method stub
										if(e.getKeyCode()==10) {
											txtPassword.requestFocus();
										}
										
									}
								});
								

							}
							{
								JLabel lblPassword = new JLabel("Password"); 
								panCenter.add(lblPassword); 
								lblPassword.setFont(font4); 
							}
							{
								txtPassword = new JPasswordField("Input password"); 
								panCenter.add(txtPassword); 
								txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
								txtPassword.setBackground(null);
								txtPassword.setForeground(Color.GRAY);
								txtPassword.addFocusListener(focus);
								txtPassword.setName("password");
								txtPassword.setOpaque(false);
								txtPassword.setHorizontalAlignment(JPasswordField.CENTER);
								txtPassword.addKeyListener(new KeyListener() {
									
									@Override
									public void keyTyped(KeyEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void keyReleased(KeyEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void keyPressed(KeyEvent e) {
										if(e.getKeyCode()==10) {
											log();
										}
										
									}
								});
								
								
							}
//							{
//								chkRemember = new JCheckBox("Remember login"); 
//								panCenter.add(chkRemember);
//								chkRemember.setHorizontalAlignment(JCheckBox.RIGHT);
//								chkRemember.setSelected(false);
//								chkRemember.setFocusable(false);
//								chkRemember.setOpaque(false);
//								chkRemember.setFont(font5);
//								chkRemember.setEnabled(false);
//							}
						}
					}
					{
						JXPanel panLineEnd = new JXPanel(new BorderLayout(5, 5)); 
						panRight.add(panLineEnd, BorderLayout.LINE_END); 
						panLineEnd.setPreferredSize(new Dimension(30, 0));
						panLineEnd.setOpaque(false);
					}
					{
						JXPanel panEnd = new JXPanel(new BorderLayout(0, 0)); 
						panRight.add(panEnd, BorderLayout.PAGE_END); 
						panEnd.setPreferredSize(new Dimension(0, 100));
						panEnd.setOpaque(false);
						{
							{
								JXPanel panButtonPage = new JXPanel(new BorderLayout(5, 5)); 
								panEnd.add(panButtonPage, BorderLayout.PAGE_START); 
								panButtonPage.setPreferredSize(new Dimension(0, 30));
								panButtonPage.setOpaque(false);
							}
							{
								JXPanel panButtonLine = new JXPanel(new BorderLayout(5, 5)); 
								panEnd.add(panButtonLine, BorderLayout.LINE_START); 
								panButtonLine.setPreferredSize(new Dimension(20, 0));
								panButtonLine.setOpaque(false);
							}
							{
								JXPanel panButtonCenter = new JXPanel(new GridLayout(1, 3, 5, 5)); 
								panEnd.add(panButtonCenter, BorderLayout.CENTER); 
								panButtonCenter.setOpaque(false);
								{
									{
										btnLogin = new JButton("Login"); 
										panButtonCenter.add(btnLogin); 
										btnLogin.setEnabled(true);
										btnLogin.setFocusPainted(false);
										btnLogin.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												log();
											}
										});
									}
									{
										panButtonCenter.add(new JLabel("")); 
									}
									{
										btnCancel = new JButton("Cancel"); 
										panButtonCenter.add(btnCancel); 
										btnCancel.setEnabled(true);
										btnCancel.setFocusPainted(false);
										btnCancel.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												System.exit(0);
											}
										});
									}
								}
							}
							{
								JXPanel panButtonLine = new JXPanel(new BorderLayout(5, 5)); 
								panEnd.add(panButtonLine, BorderLayout.LINE_END); 
								panButtonLine.setPreferredSize(new Dimension(20, 0));
								panButtonLine.setOpaque(false);
							}
							{
								JXPanel panButtonEnd = new JXPanel(new BorderLayout(5, 5)); 
								panEnd.add(panButtonEnd, BorderLayout.PAGE_END); 
								panButtonEnd.setPreferredSize(new Dimension(0, 40));
								panButtonEnd.setOpaque(false);
								panButtonEnd.setBorder(new EmptyBorder(10, 20, 10, 20));
								{
									progress = new JProgressBar(); 
									panButtonEnd.add(progress, BorderLayout.CENTER); 
									progress.setMaximum(10);
									progress.setValue(progress.getMaximum());
									progress.setVisible(false);
									progress.setStringPainted(true);
									progress.setString("Please wait...");
									progress.setIndeterminate(true);
								}
							}
						}
					}
				}
			}
		}
	}

//	public static void main (String[] args) {
//		initialize();
//		FncGlobal.initialize2();
//		display(); 
//	}
	
	private static void log() {
		
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() throws Exception {
				disableControls(true); 
				progress.setValue(0);
				startProgress(); 

				if (!checkInput()) {
					JOptionPane.showMessageDialog(null, "Invalid username or password!", "Login", JOptionPane.WARNING_MESSAGE);
					disableControls(false);
					purge();
				} else {
					char[] password = txtPassword.getPassword(); 
					System.out.printf("password:%s", password);

					try {
						authenticate(txtUserName.getText(), password, "Summit - ArcSystem Live");
						//authenticate(txtUserName.getText(), password, "JPolicy-Offline");
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} 

//					Arrays.fill(password, '0');
//					txtPassword.setText("");
//
//					disableControls(false);
//					purgeFrame(frame); 
				}

//				FncLookAndFeel.initialize();
//				FncGlobal.initialize(false);
//
//				purge(); 
				return null;
			}

		}; 
		sw.execute();
	}

	private static FocusListener focus = new FocusListener() {

		public void focusLost(FocusEvent arg0) {
			if (((JTextField) arg0.getSource()).getText().equals("")) {
				if (((JTextField) arg0.getSource()).getName().equals("username")) {
					((JTextField) arg0.getSource()).setText("Input username");
				} else {
					((JTextField) arg0.getSource()).setText("Input password");
				}

				((JTextField) arg0.getSource()).setForeground(Color.GRAY);
			}
		}

		public void focusGained(FocusEvent arg0) {
			System.out.println();

			if (((JTextField) arg0.getSource()).getText().equals("")) {
				if (((JTextField) arg0.getSource()).getName().equals("username")) {
					((JTextField) arg0.getSource()).setText("Input username");
				} else {
					((JTextField) arg0.getSource()).setText("Input password");
				}

				((JTextField) arg0.getSource()).setForeground(Color.GRAY);
			} else if (((JTextField) arg0.getSource()).getText().equals("Input username") || ((JTextField) arg0.getSource()).getText().equals("Input password")) {
				((JTextField) arg0.getSource()).setText("");
				((JTextField) arg0.getSource()).setForeground(Color.BLACK);
			} else {
				((JTextField) arg0.getSource()).setForeground(Color.BLACK);
			}
		}
	};

	private static Boolean checkInput() {
		if (txtUserName.getText().equals("") || txtPassword.getPassword().equals("") || txtUserName.getText().equals("Input username") || txtPassword.getPassword().equals("Input password")) {
			return false;                              
		} else {
			return true;
		}
	}

	public static void initialize() {
		String windowString_Light = "128 64 0"; 
		String windowString_Dark = "128 64 0"; 
		String windowForeground = "40 40 40";

		Font systemFont_Bold = new Font(font_name, Font.BOLD, font_size); 

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			Properties props = FncGlobal.properties; 
			props.put("logoString", "");

			props.put("controlColor", windowString_Light);
			props.put("controlColorLight", windowString_Light);
			props.put("controlColorDark", windowString_Dark);

			props.put("rolloverColor", windowString_Light); 
			props.put("rolloverColorLight", windowString_Light); 
			props.put("rolloverColorDark", windowString_Dark);

			props.put("menuColorLight", "219 218 216");
			props.put("menuColorDark", "209 208 206");

			props.put("menuSelectionBackgroundColor", windowString_Light);
			props.put("menuSelectionBackgroundColorLight", windowString_Light);
			props.put("menuSelectionBackgroundColorDark", windowString_Light);

			props.put("menuBackgroundColor", "219 218 216");

			props.put("selectionForegroundColor", "255 255 255");
			props.put("selectionBackgroundColor", windowString_Dark);

//			props.put("windowTitleForegroundColor", "255 255 255");
//			props.put("windowTitleBackgroundColor", windowString_Light); 
//			props.put("windowTitleColorLight", windowString_Light); 
//			props.put("windowTitleColorDark", windowString_Dark); 
//			props.put("windowBorderColor", windowString_Dark);
			
			props.put("windowTitleForegroundColor", "191, 191, 191");
			props.put("windowTitleBackgroundColor", "191, 191, 191"); 
			props.put("windowTitleColorLight", "191, 191, 191"); 
			props.put("windowTitleColorDark", "191, 191, 191"); 
			props.put("windowBorderColor", "191, 191, 191");
			

//			props.put("windowInactiveTitleColorLight", windowString_Light);
//			props.put("windowInactiveTitleColorDark", windowString_Dark);
//			props.put("windowInactiveBorderColor", windowString_Dark);
			
			props.put("windowInactiveTitleColorLight", "191, 191, 191");
			props.put("windowInactiveTitleColorDark", "191, 191, 191");
			props.put("windowInactiveBorderColor", "191, 191, 191");

			props.put("controlTextFont", String.format("%s %s %s", font_name, "PLAIN", font_size));
			props.put("systemTextFont", String.format("%s %s %s", font_name, "PLAIN", font_size));
			props.put("userTextFont", String.format("%s %s %s", font_name, "PLAIN", font_size));
			props.put("menuTextFont", String.format("%s %s %s", font_name, "PLAIN", font_size));
			props.put("windowTitleFont", String.format("%s %s %s", font_name, "BOLD", font_size));
			props.put("subTextFont", String.format("%s %s %s", font_name, "PLAIN", font_size));
			props.put("foregroundColor", windowForeground);

			AcrylLookAndFeel.setCurrentTheme(props);                              
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			
			UIManager.put("Button.font", systemFont_Bold);
			UIManager.put("Button.foreground",systemColor);

			Image iconInternalFrame = FncLookAndFeel.iconSystem;
			iconInternalFrame = iconInternalFrame.getScaledInstance(19, 19, Image.SCALE_DEFAULT);
			UIManager.put("InternalFrame.icon", new ImageIcon(iconInternalFrame));
			UIManager.getLookAndFeelDefaults().put("defaultFont", systemFont_Bold);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void resetLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean authenticate(String username, char[] password, String server) throws Exception {
		boolean isSuccessful = login(username, new String(password), server);

		System.out.println("2nd Authentication");

		if (username.equals("postgres")) {
			isSuccessful = false;
			JOptionPane.showMessageDialog(null, "User does not exist", "Log-in", JOptionPane.WARNING_MESSAGE);
		}

		if (isSuccessful) {
			UserInfo.initializeEmployeeInfo(username);

			try {
				Home.Home_ArcSystem home = new Home.Home_ArcSystem(FncGlobal.ORIGINAL_TITLE+" - "+ username + "@"  +server);

				FncGlobal.homeMDI = home;
				//FncGlobal.search = new _JSearch(FncGlobal.homeMDI, "Search");

				home.setLocationRelativeTo(null);
				home.setVisible(true);

				String strSQL = "SELECT sp_audit_log_details('"+UserInfo.EmployeeCode+"', true)";

				pgSelect db = new pgSelect();
				db.select(strSQL);
				
				Arrays.fill(password, '0');
				txtPassword.setText("");

				disableControls(false);
				purgeFrame(frame); 
				
				FncLookAndFeel.initialize();
				//FncGlobal.initialize(false);

				purge(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSuccessful;
	}

	private static boolean login(String username, String password, String server) {
		try {
			FncGlobal.server = server;

			FncGlobal.connectionUsername = username;
			FncGlobal.connectionPassword = password;
			FncGlobal.connectionURL = FncGlobal.mapURL.get(server);
			
			FncGlobal.connectionDriver = "org.postgresql.Driver";

			Class.forName(FncGlobal.connectionDriver);
			FncGlobal.connection = DriverManager.getConnection(FncGlobal.mapURL.get(server), username, password);
		
			//FncGlobal.connection = DriverManager.getConnection("jdbc:postgresql://192.168.10.17:5432/acerland?ApplicationName=ArcSystem", username, password);

			
			pgSelect db = new pgSelect();
			db.select("SELECT current_schema(), inet_server_addr();");
			System.out.printf("Current Schema: %s%n", db.getResult()[0][0]);

			String schema_date = ((String) db.getResult()[0][0]).replace("openhouse_", "");
			Object ip_address = db.getResult()[0][1];

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			try {
				Date sDate = formatter.parse(schema_date);

				FncGlobal.ORIGINAL_TITLE = String.format("%s @ %s (%s)", FncGlobal.ORIGINAL_TITLE, FncGlobal.server, new SimpleDateFormat("yyyy-MM-dd").format(sDate));
			} catch (ParseException e) {
				FncGlobal.ORIGINAL_TITLE = String.format("%s @ %s (%s)", FncGlobal.ORIGINAL_TITLE, FncGlobal.server, ip_address);
			}

			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Username/password doesn't exist!", "Login", JOptionPane.WARNING_MESSAGE);
			disableControls(false);
			purge();

			return false;
		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void disableControls(boolean blnRev) {
		txtUserName.setEnabled(!blnRev);
		txtPassword.setEnabled(!blnRev);
		btnLogin.setEnabled(!blnRev);
		btnCancel.setEnabled(!blnRev);
	}

	private static void startProgress() {
		TimerTask devtmr = new TimerTask() {

			public void run() {
				progress.setVisible(true);
				updateProgress(); 
			}
		};

		timer = new Timer();
		long delay = 0;
		long intevalPeriod = 1000; 

		timer.scheduleAtFixedRate(devtmr, delay, intevalPeriod);
	}

	private static void purge() {
		progress.setValue(progress.getMaximum());
		progress.setVisible(false);

		timer.cancel();
		timer.purge(); 
	}

	private static void updateProgress() {
		if (progress.getValue()==progress.getMaximum()) {
			progress.setValue(0);
		} else {
			progress.setValue(progress.getValue()+1);
		}
	}

	private static void purgeFrame(JFrame frm) {
		frm.setVisible(false);
		frm.dispose();
	}

}
