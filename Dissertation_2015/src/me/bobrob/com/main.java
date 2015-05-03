package me.bobrob.com;

//import encrypt;
//import zip_folder;
//import decrypt;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.jdbc.StringUtils;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main {
	//version 0.9.1 
	private JFrame frmDragonShield;
	
	private JPanel panel_1;
	private JPanel panel_2_1;
	private JPanel panel_2_2;
	private JPanel panel_4_1;
	private JPanel panel_4_2;
	private JPanel panel_5_1;
	private ButtonGroup bg = new ButtonGroup();
	
	int ExtraLargeFont = 24, createPasswordStrengthInt = 0;
	static private final String newline = "\n";
	JFileChooser fc;
	JTextArea textAreaLogOffline, textAreaLogOnlineStudent;
	String password, password_decrypt, salt;
	static String file_locationString, file_locationString_zip, file_locationString_use, text_username_login, text_password_login;
	private JTextField textField;
	private JTextField textField_1;
	boolean loggedIn = false, checkIfTeacher = false;
	static int user_ID, course_ID;
	ArrayList<String> listCourseData = new ArrayList<String>();
	ArrayList<String> listStudentsOnCourse= new ArrayList<String>();
	String[] result;
	JList list_create_course;
	
	DefaultListModel listModel_createCourse = new DefaultListModel();
	DefaultListModel listModel_students_on_course = new DefaultListModel();
	DefaultListModel listModel_list_all_students = new DefaultListModel();
	DefaultListModel listModel_list_all_course_student_on = new DefaultListModel();
	DefaultListModel listModel_list_all_course_details = new DefaultListModel();
	DefaultListModel listModel_URL_student = new DefaultListModel();
	DefaultListModel listModel_URL_teacher = new DefaultListModel();
	
	String StringCourseSelected, StringStudetOnCourseSelected, StringListAllStudentsSelected, StringListAllCoursesStudentOnSelected, teacherName, Stringurlgetstudent, Stringurlgetteacher, StringListURLStudentSelected, StringListURLTeacherSelected;
	
	String passwordEncryptStudent, saltEncryptStudent;
	
	String PATH = "G:\\test\\";
	String FILENAME = "share.zip";
	String SvDPDFURL;
	
	String text_username_create, text_password_create, status_create_account;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1; //create account
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_course_name;
	private JTextField textField_course_start_year;
	private JTextField textField_course_start_month;
	private JTextField textField_course_start_day;
	private JTextField textField_course_end_day;
	private JTextField textField_course_end_month;
	private JTextField textField_course_end_year;
	private JTextField textField_course_description;
	private JTextField textField_5;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frmDragonShield.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		fc = new JFileChooser();
		
		frmDragonShield = new JFrame();
		frmDragonShield.setResizable(false);
		frmDragonShield.setIconImage(Toolkit.getDefaultToolkit().getImage(main.class.getResource("/Resources/icon.png")));
		frmDragonShield.setTitle("Dragon Shield");
		frmDragonShield.setBounds(100, 100, 750, 500);
		frmDragonShield.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDragonShield.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panel_1 = new JPanel();
		frmDragonShield.getContentPane().add(panel_1, "name_14031741198094");
		panel_1.setLayout(null);
		panel_1.setVisible(true);
		
		final JPanel panel_2_1 = new JPanel();
		frmDragonShield.getContentPane().add(panel_2_1, "name_14041840754753");
		panel_2_1.setLayout(null);
		
		final TextArea textAreaLogOffline = new TextArea();
		textAreaLogOffline.setBounds(317, 0, 417, 451);
		panel_2_1.add(textAreaLogOffline);
		panel_2_1.setVisible(false);
		
		final JPanel panel_2_2 = new JPanel();
		frmDragonShield.getContentPane().add(panel_2_2, "name_14045389899093");
		panel_2_2.setLayout(null);
		panel_2_2.setVisible(false);
		
		final JPanel panel_4_1 = new JPanel(); //login
		frmDragonShield.getContentPane().add(panel_4_1, "name_30724388318863");
		panel_4_1.setLayout(null);
		panel_4_1.setVisible(false);
		
		final JPanel panel_4_2 = new JPanel(); //create account
		frmDragonShield.getContentPane().add(panel_4_2, "name_30696802079397");
		panel_4_2.setLayout(null);
		panel_4_2.setVisible(false);
		
		final JPanel panel_5_1 = new JPanel();
		frmDragonShield.getContentPane().add(panel_5_1, "name_43859920447664");
		panel_5_1.setLayout(null);
		panel_5_1.setVisible(false);
		
		final TextArea textAreaLogOnlineStudent = new TextArea();
		textAreaLogOnlineStudent.setBounds(483, 0, 261, 450);
		panel_5_1.add(textAreaLogOnlineStudent);
		
		final JPanel panel_5_2 = new JPanel();
		frmDragonShield.getContentPane().add(panel_5_2, "name_103133044331624");
		panel_5_2.setLayout(null);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Student");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(201, 223, 109, 23);
		panel_4_2.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);
		
		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Teacher");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNewRadioButton_1.setBounds(324, 223, 109, 23);
		panel_4_2.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);
		
		final TextArea textAreaLogOnlineTeacher = new TextArea();
		textAreaLogOnlineTeacher.setBounds(483, 0, 261, 450);
		panel_5_2.add(textAreaLogOnlineTeacher);
		
		final JPanel panel_6_4 = new JPanel();
		frmDragonShield.getContentPane().add(panel_6_4, "name_342088022921560");
		panel_6_4.setLayout(null);
		
		final JPanel panel_6_3 = new JPanel();
		frmDragonShield.getContentPane().add(panel_6_3, "name_340421893746479");
		panel_6_3.setLayout(null);
		
		final JPanel panel_6_2 = new JPanel();
		frmDragonShield.getContentPane().add(panel_6_2, "name_339992021741598");
		panel_6_2.setLayout(null);
		
		final JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		frmDragonShield.getContentPane().add(panel_6_1, "name_544266599739935");
		
		

		//JList for all courses for specific teacher
		final JList list_create_course = new JList(listModel_createCourse);
		list_create_course.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringCourseSelected = list_create_course.getSelectedValue().toString();
				listCourseData.clear();
				listModel_students_on_course.removeAllElements();
				getDataCourse(StringCourseSelected);
				displayUsersOnCourse();
				
				textField_course_name.setText(listCourseData.get(0));  
				textField_course_start_year.setText(listCourseData.get(2)); 
				textField_course_start_month.setText(listCourseData.get(3)); 
				textField_course_start_day.setText(listCourseData.get(4)); 
				textField_course_end_year.setText(listCourseData.get(5)); 
				textField_course_end_month.setText(listCourseData.get(6)); 
				textField_course_end_day.setText(listCourseData.get(7)); 
				textField_course_description.setText(listCourseData.get(1)); 
			}
		});
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(42, 72, 317, 100);
		panel_6_4.add(scrollPane_3);
		scrollPane_3.setViewportView(list_create_course);
		
		
		//JList for all students on specific course 
		final JList list_students_on_course = new JList(listModel_students_on_course);
		list_students_on_course.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				StringStudetOnCourseSelected = list_students_on_course.getSelectedValue().toString();
				listStudentsOnCourse.clear();
			}
		});
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(419, 72, 294, 100);
		panel_6_4.add(scrollPane_4);
		scrollPane_4.setViewportView(list_students_on_course);
		
		//Jlist to display all students for 6_4 //done
		final JList list_students_all_students = new JList(listModel_list_all_students);
		list_students_all_students.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				StringListAllStudentsSelected = list_students_all_students.getSelectedValue().toString();
				findUserID(StringListAllStudentsSelected);
			}
		});
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(419, 271, 294, 100);
		panel_6_4.add(scrollPane_5);
		scrollPane_5.setViewportView(list_students_all_students);
		
		
		
		
		final JList listURLStudent = new JList(listModel_URL_student);
		listURLStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringListURLStudentSelected = listURLStudent.getSelectedValue().toString();
			}
		});
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(68, 135, 300, 100);
		panel_6_3.add(scrollPane_2);
		scrollPane_2.setViewportView(listURLStudent);
		
			
		final JList listURLTeacher = new JList(listModel_URL_teacher);
		listURLTeacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringListURLTeacherSelected = listURLTeacher.getSelectedValue().toString();
			}
		});
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(68, 135, 300, 100);
		panel_6_1.add(scrollPane_6);
		scrollPane_6.setViewportView(listURLTeacher);
		
			
		final JList list_student_list_of_courses = new JList(listModel_list_all_course_student_on);
		list_student_list_of_courses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listModel_list_all_course_details.removeAllElements();
				listCourseData.clear();
				StringListAllCoursesStudentOnSelected = list_student_list_of_courses.getSelectedValue().toString();
				getDataCourse(StringListAllCoursesStudentOnSelected);
				getTeacherName();
				
				
				String start_date = listCourseData.get(2) + "-" + listCourseData.get(3) + "-" + listCourseData.get(4);
				String end_date = listCourseData.get(5) + "-" + listCourseData.get(6) + "-" + listCourseData.get(7);
				
				listModel_list_all_course_details.addElement("Name: " + listCourseData.get(0));
				listModel_list_all_course_details.addElement("Description: " + listCourseData.get(1));
				listModel_list_all_course_details.addElement("Start date: " + start_date);
				listModel_list_all_course_details.addElement("End date: " + end_date); 
				listModel_list_all_course_details.addElement("Teacher name: " + teacherName);
				
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(61, 148, 200, 100);
		panel_6_2.add(scrollPane_1);
		scrollPane_1.setViewportView(list_student_list_of_courses);
		
		
		JList list_student_course_details = new JList(listModel_list_all_course_details);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(279, 148, 300, 150);
		panel_6_2.add(scrollPane);
		scrollPane.setViewportView(list_student_course_details);
		
		JButton btnNewButton_2 = new JButton("Encrypt");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        	int returnVal = fc.showOpenDialog(fc);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	//System.out.println(returnVal);
	            	File file_location = fc.getSelectedFile();
	                textAreaLogOffline.append("Opening: " + file_location.getName() + newline);
	                file_locationString = file_location.toString();
	                password = JOptionPane.showInputDialog ("Enter password: "); 
	                file_locationString = file_locationString.replace("\\", "/"); 
	                //if  folder
	                File f = new File(file_locationString);
	                if(f.isDirectory() && returnVal == 0 && password != null){
	                	file_locationString_use = file_locationString + ".zip";
	                	try {
	                		zip_folder.zipFolder(file_locationString, file_locationString_use);
	                		textAreaLogOffline.append("Turning into .zip: " + file_locationString + newline);
	                		
	        				encrypt.encryptOffline(password, file_locationString_use);
	        				textAreaLogOffline.append("Encrypting : " + file_locationString + newline);
	        				Path path = Paths.get(file_locationString_use);	
	        				try {
	        				    Files.delete(path);
	        				} catch (NoSuchFileException x) {
	        				    System.err.format("%s: no such" + " file or directory%n", file_locationString_use);
	        				} catch (DirectoryNotEmptyException x) {
	        				    System.err.format("%s not empty%n", file_locationString_use);
	        				} catch (IOException x) {
	        				    // File permission problems are caught here.
	        				    System.err.println(x);
	        				}
	        				textAreaLogOffline.append("Deleted : " + file_locationString + newline);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        			}	
	                //if  file
	                }else if(f.isFile() && returnVal == 0 && password != null){
	                	try {
	                		
	        				encrypt.encryptOffline(password, file_locationString);
	        				textAreaLogOffline.append("Encrypting : " + file_locationString + newline);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				//System.out.println(e);
	        			}
	                }
	            }
	            else{
	            	try{
	            		textAreaLogOffline.append("File not selected. "  + newline);
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            }
		}//end of encrypt button for offline
		
		
		
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(73, 95, 180, 80);
		panel_2_1.add(btnNewButton_2);
		
		
		//start of decrypt for offline
		JButton btnNewButton_3 = new JButton("Decrypt");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				//start of descrypt for online
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        	int returnVal = fc.showOpenDialog(fc);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	File file_location = fc.getSelectedFile();
	                textAreaLogOffline.append("Opening: " + file_location.getName() + newline);
	                file_locationString = file_location.toString();
	            }
	            password = null;
	        	try {
	        		if(returnVal == 0 && password == null){
	        			password = JOptionPane.showInputDialog ("Enter password: "); 
	        			file_locationString = file_locationString.replace("\\", "/");
                		
	        			decrypt.decryptOffline(password, file_locationString);
						textAreaLogOffline.append("Decrypcting : " + file_locationString + newline);
	        		}
	        		else{
	        			textAreaLogOffline.append("File not selected. "  + newline);
	        		}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//end of decrypt for offline
			
			
			
			
		});
		btnNewButton_3.setBounds(73, 226, 180, 80);
		panel_2_1.add(btnNewButton_3);
		
		
		
		
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_4_2.setVisible(true);
				panel_2_2.setVisible(false);
			}
		});
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateAccount.setBounds(270, 250, 180, 80);
		panel_2_2.add(btnCreateAccount);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_4_1.setVisible(true);
				panel_2_2.setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(270, 110, 180, 80);
		panel_2_2.add(btnLogin);
		panel_2_2.setVisible(false);
		
		
		
		
		
		JButton btnNewButton = new JButton("Offline");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2_1.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(270, 110, 180, 80);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Online");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2_2.setVisible(true);
				panel_1.setVisible(false);
				panel_2_1.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(270, 250, 180, 80);
		panel_1.add(btnNewButton_1);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(88, 107, 103, 35);
		panel_4_2.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(88, 167, 108, 35);
		panel_4_2.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 107, 236, 35);
		panel_4_2.add(textField);
		textField.setColumns(10);
		
		
		
		
		
		JButton btnNewButton_4 = new JButton("Create account");
		btnNewButton_4.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
	
				if(rdbtnNewRadioButton.isSelected()){
					status_create_account = "student";
				}
				else if(rdbtnNewRadioButton_1.isSelected()){
					status_create_account = "teacher";
				}
				else{
					status_create_account = "error";
				}
				

	            try {
	                text_username_create = textField.getText();
	                text_password_create = passwordField_1.getText();
	                if(!StringUtils.isNullOrEmpty(text_username_create) && !StringUtils.isNullOrEmpty(text_password_create) && !status_create_account.equals("error") && salt != null){
	                    String url = "jdbc:mysql://194.81.104.22/db12407404";
	                    String driver = "com.mysql.jdbc.Driver";
	                    String userName = "s12407404"; 
	                    String password = "simple";
	                	Statement stmt = null;
	                    ResultSet rs = null;
	                    String query = "SELECT count(*) username FROM account_002 WHERE username = '"+text_username_create+"'";    // error handling
	                    try {
	                        Class.forName(driver).newInstance();
	                        Connection conn = DriverManager.getConnection(url,userName,password);
	                        stmt = conn.prepareStatement(query);
	                        rs = stmt.executeQuery(query);
	                        while(rs.next()){
	                            int rowCount = rs.getInt(1); 
	                            if(rowCount != 0){
	                                JOptionPane.showMessageDialog(null, "Sorry the username "+ text_username_create +" has already been taken. Please try again.");
	                            }
	                            else{
	                                mysql(); //submits the insert query
	                            }
	                        }
	                        conn.close();
	                        
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                    panel_4_1.setVisible(true);
	    	    		panel_4_2.setVisible(false);
	                }
	                else{
	                    JOptionPane.showMessageDialog(null, "ERROR: missing details");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(201, 354, 177, 52);
		panel_4_2.add(btnNewButton_4);
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setValue(createPasswordStrengthInt);
		progressBar.setMaximum(40);
		progressBar.setBounds(468, 171, 146, 35);
		panel_4_2.add(progressBar);
		
		JLabel lblPasswordStrength = new JLabel("Password strength");
		lblPasswordStrength.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordStrength.setBounds(468, 217, 146, 35);
		panel_4_2.add(lblPasswordStrength);
		
		passwordField_1 = new JPasswordField(); //create password input 
		passwordField_1.setBounds(201, 170, 236, 35);
		panel_4_2.add(passwordField_1);
		
		final JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(635, 180, 55, 22);
		panel_4_2.add(lblNewLabel_1);
		
		JLabel lblCreateAccount = new JLabel("Create account");
		lblCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCreateAccount.setBounds(88, 32, 133, 28);
		panel_4_2.add(lblCreateAccount);
		
		JButton btnAddSalt = new JButton("Add salt");
		btnAddSalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				
				for(int i = 0; i < 25; i++){
		            PointerInfo a = MouseInfo.getPointerInfo();
		            Point b = a.getLocation();
		            int x = (int) b.getX();
		            int y = (int) b.getY();
		            try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		            PointerInfo a1 = MouseInfo.getPointerInfo();
		            Point b1 = a1.getLocation();
		            int x1 = (int) b1.getX();
		            int y1 = (int) b1.getY();
		            if(x1 == x && y1 == y){
		                i = i - 1;
		            }
		            else{
		            	salt = salt + y + "" + x;
		            	textField_2.setText(salt);
		            }
		        }
			}
		});
		btnAddSalt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddSalt.setBounds(201, 286, 133, 35);
		panel_4_2.add(btnAddSalt);
		
		textField_2 = new JTextField();
		textField_2.setBounds(372, 286, 162, 35);
		panel_4_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblmoveTheMouse = new JLabel("(Move the mouse)");
		lblmoveTheMouse.setBounds(223, 329, 103, 14);
		panel_4_2.add(lblmoveTheMouse);
		
		
		passwordField_1.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent de) {
                @SuppressWarnings("deprecation")
				String passCheck = passwordField_1.getText();
                createPasswordStrengthInt = password_checker.checkPw(passCheck);
                progressBar.setValue(createPasswordStrengthInt);
                
                String createPasswordStrengthString = Integer.toString(createPasswordStrengthInt); //converts int to string 
                lblNewLabel_1.setText(createPasswordStrengthString); //inputs password strength scoring (make into percentage?)
            }

            public void removeUpdate(DocumentEvent de) {
                @SuppressWarnings("deprecation")
				String passCheck = passwordField_1.getText();
                createPasswordStrengthInt = password_checker.checkPw(passCheck);
                progressBar.setValue(createPasswordStrengthInt);
                
                String createPasswordStrengthString = Integer.toString(createPasswordStrengthInt); //converts int to string 
                lblNewLabel_1.setText(createPasswordStrengthString); //inputs password strength scoring (make into percentage?)
            }

            public void changedUpdate(DocumentEvent de) {
            	//Plain text components don't fire these events.
            }
        });
		
		JLabel label = new JLabel("Username:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(88, 107, 103, 35);
		panel_4_1.add(label);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(88, 167, 108, 35);
		panel_4_1.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(201, 107, 236, 35);
		panel_4_1.add(textField_3);
		
		JButton btnLogin_1 = new JButton("Login");
		btnLogin_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//when login button is pressed //mysql stuff
				if(loggedIn == false){ //checks to see if login is correct 
		    		try {
		    			text_username_login = textField_3.getText(); //gets the username 
		    			text_password_login = passwordField.getText(); //gets the password
		            	if(!StringUtils.isNullOrEmpty(text_username_login) 
		            			&& !StringUtils.isNullOrEmpty(text_password_login)){ //makes sure they are not null
		            		String url = "jdbc:mysql://194.81.104.22/db12407404"; //connects to databse
		            		String driver = "com.mysql.jdbc.Driver"; //driver for mysql
		            		String userName = "s12407404";  //username to connect to db
		            		String password = "simple"; //password for db
		                    Statement stmt = null;
		                    ResultSet rs = null;  //
		                    String query = "SELECT count(*) FROM account_002 WHERE password = '"+text_password_login+"' " +
		                    		"AND username = '"+text_username_login+"'";    // error handling
		            		try {
		            			Class.forName(driver).newInstance();
		            			Connection conn = DriverManager.getConnection(url,userName,password);
		                        stmt = conn.prepareStatement(query);
		                        rs = stmt.executeQuery(query);
		                        while(rs.next()){
		                        	int rowCount = rs.getInt(1); 
		                        	if(rowCount == 0){
		                            	JOptionPane.showMessageDialog(null, "Sorry your username "+ text_username_login +
		                            			" was not on our database. Please try again.");
		                            }
		                            else{
		                            	loggedIn = true;
		                            	checkTeacher();
		                            	if(checkIfTeacher == true){
		                            		panel_5_2.setVisible(true);
				                	    	panel_4_1.setVisible(false);
				                	    	textAreaLogOnlineTeacher.append("You have logged in as a teacher. Welcome " 
				                	    	+ text_username_login  + newline);
		                            	}
		                            	else{
		                            		panel_5_1.setVisible(true);
				                	    	panel_4_1.setVisible(false);
				                	    	textAreaLogOnlineStudent.append("You have logged in as a student. Welcome " 
				                	    	+ text_username_login  + newline);
		                            	}
		                            }
		                        }
		            			conn.close();
		            		} catch (Exception e) {
		            			e.printStackTrace();
		            		}	
		            	}
		            	else{
		            		JOptionPane.showMessageDialog(null, "ERROR: missing details");
		            	}
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}
		        }
			}
		});
		btnLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin_1.setBounds(200, 250, 177, 52);
		panel_4_1.add(btnLogin_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 170, 236, 35);
		panel_4_1.add(passwordField);
		
		
		JLabel lblStudentHomepage = new JLabel("Student: Homepage");
		lblStudentHomepage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStudentHomepage.setBounds(62, 45, 185, 37);
		panel_5_1.add(lblStudentHomepage);
		
		JButton ButtonStudentCourse = new JButton("Course details");
		ButtonStudentCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel_list_all_course_student_on.removeAllElements();
				listModel_list_all_course_details.removeAllElements();
				findUserID(text_username_login);
				showCoursesStudent();
				panel_6_2.setVisible(true);
				panel_5_1.setVisible(false);
				user_ID = 0;
			}
		});
		ButtonStudentCourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ButtonStudentCourse.setBounds(62, 129, 200, 80);
		panel_5_1.add(ButtonStudentCourse);
		
		JButton ButtonStudentUrl = new JButton("URL");
		ButtonStudentUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("tfgwgwe");
				findUserID(text_username_login);
				System.out.println(user_ID);
				addURLShow(listModel_URL_student);
				panel_6_3.setVisible(true);
				panel_5_1.setVisible(false);
			}
		});
		ButtonStudentUrl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ButtonStudentUrl.setBounds(62, 241, 200, 79);
		panel_5_1.add(ButtonStudentUrl);
		
		JButton ButtonStudentEncrypt = new JButton("Encrypt");
		ButtonStudentEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//encrypt for online student
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        	int returnVal = fc.showOpenDialog(fc);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	//System.out.println(returnVal);
	            	File file_location = fc.getSelectedFile();
	                textAreaLogOffline.append("Opening: " + file_location.getName() + newline);
	                file_locationString = file_location.toString();
	                //password = JOptionPane.showInputDialog ("Enter password: "); 
	                file_locationString = file_locationString.replace("\\", "/"); 
	                //if  folder
	                File f = new File(file_locationString);
	                if(f.isDirectory() && returnVal == 0){
	                	file_locationString_use = file_locationString + ".zip";
	                	try {
	                		zip_folder.zipFolder(file_locationString, file_locationString_use);
	                		textAreaLogOffline.append("Turning into .zip: " + file_locationString + newline);
	                		
	                		//method for getting the salt login user = user_ID
	                		findUserID(text_username_login);
	                		getSaltAndPasswordStudent();
	                		System.out.println(passwordEncryptStudent);
	                		System.out.println(saltEncryptStudent);
	                		
	        				encrypt.encryptOnlineStudent(passwordEncryptStudent, file_locationString_use, saltEncryptStudent);
	        				textAreaLogOffline.append("Encrypting : " + file_locationString + newline);
	        				Path path = Paths.get(file_locationString_use);	
	        				try {
	        				    Files.delete(path);
	        				} catch (NoSuchFileException x) {
	        				    System.err.format("%s: no such" + " file or directory%n", file_locationString_use);
	        				} catch (DirectoryNotEmptyException x) {
	        				    System.err.format("%s not empty%n", file_locationString_use);
	        				} catch (IOException x) {
	        				    // File permission problems are caught here.
	        				    System.err.println(x);
	        				}
	        				textAreaLogOffline.append("Deleted : " + file_locationString + newline);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				//System.out.println(e);
	        			}	
	                //if  file
	                }else if(f.isFile() && returnVal == 0){
	                	//file_locationString_use = file_locationString;
	                	try {
	                		findUserID(text_username_login);
	                		getSaltAndPasswordStudent();
	                		System.out.println(passwordEncryptStudent);
	                		System.out.println(saltEncryptStudent);
	                		
	        				encrypt.encryptOnlineStudent(passwordEncryptStudent, file_locationString, saltEncryptStudent);
	        				textAreaLogOffline.append("Encrypting : " + file_locationString + newline);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				//System.out.println(e);
	        			}
	                }
	            }
	            else{
	            	try{
	            		textAreaLogOffline.append("File not selected. "  + newline);
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            }
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		ButtonStudentEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ButtonStudentEncrypt.setBounds(282, 129, 180, 80);
		panel_5_1.add(ButtonStudentEncrypt);
		
		
		
		
		
		
		//descrypt for student online
		JButton ButtonStudentDecrypt = new JButton("Decrypt");
		ButtonStudentDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				


	    		findUserID(text_username_login);
	    		getURLDecrypt();
	    		System.out.println(SvDPDFURL);
	    		
	    		//choose file location to save file
				String file_locationString_output = null;
				final JFileChooser fc = new JFileChooser();
				fc.setSelectedFile(new File("test"));
				//Create a file chooser
				int returnVal = fc.showSaveDialog(fc);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file_location = fc.getSelectedFile();
		            file_locationString_output = file_location.toString();
		            file_locationString_output = file_locationString_output.replace("\\", "/");
		        }

	    				
	    		File file = new File(file_locationString_output);
	    		URL url = null;
	    		try {
					url = new URL(SvDPDFURL);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				try {
					FileUtils.copyURLToFile(url, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        			
	        	findUserID(text_username_login);
	        	getSaltAndPasswordStudent();
                System.out.println(saltEncryptStudent);
	        }
	        		
				
			//end of decrypt for online
			
		});
		ButtonStudentDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ButtonStudentDecrypt.setBounds(282, 241, 180, 80);
		panel_5_1.add(ButtonStudentDecrypt);
		
		
		
		
		
		
		JButton ButtonTeacherStudent = new JButton("Course details");
		ButtonTeacherStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findUserID(text_username_login);
				UpdateListOfCourse();
				getDataAllStudents();
				panel_6_4.setVisible(true);
    	    	panel_5_2.setVisible(false);
    	    	user_ID = 0;
			}
		});
		ButtonTeacherStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ButtonTeacherStudent.setBounds(62, 129, 200, 80);
		panel_5_2.add(ButtonTeacherStudent);
		
		JButton ButtonTeacherUrl = new JButton("URL");
		ButtonTeacherUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_6_1.setVisible(true);
				panel_5_2.setVisible(false);
			}
		});
		ButtonTeacherUrl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ButtonTeacherUrl.setBounds(62, 241, 200, 79);
		panel_5_2.add(ButtonTeacherUrl);
		
		JButton ButtonTeacherDecrypt = new JButton("Decrypt");
		ButtonTeacherDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ButtonTeacherDecrypt.setBounds(282, 241, 180, 80);
		panel_5_2.add(ButtonTeacherDecrypt);
		
		JButton TeacherButtonEncrypt = new JButton("Encrypt");
		TeacherButtonEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//encrypt for online teacher
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        	int returnVal = fc.showOpenDialog(fc);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	//System.out.println(returnVal);
	            	File file_location = fc.getSelectedFile();
	                textAreaLogOffline.append("Opening: " + file_location.getName() + newline);
	                file_locationString = file_location.toString();
	                //password = JOptionPane.showInputDialog ("Enter password: "); 
	                file_locationString = file_locationString.replace("\\", "/"); 
	                //if  folder
	                File f = new File(file_locationString);
	                if(f.isDirectory() && returnVal == 0){
	                	file_locationString_use = file_locationString + ".zip";
	                	try {
	                		zip_folder.zipFolder(file_locationString, file_locationString_use);
	                		textAreaLogOffline.append("Turning into .zip: " + file_locationString + newline);
	                		
	                		//method for getting the salt login user = user_ID
	                		findUserID(text_username_login);
	                		getSaltAndPasswordStudent();
	                		System.out.println(passwordEncryptStudent);
	                		System.out.println(saltEncryptStudent);
	                		
	        				encrypt.encryptOnlineStudent(passwordEncryptStudent, file_locationString_use, saltEncryptStudent);
	        				textAreaLogOffline.append("Encrypting : " + file_locationString + newline);
	        				Path path = Paths.get(file_locationString_use);	
	        				try {
	        				    Files.delete(path);
	        				} catch (NoSuchFileException x) {
	        				    System.err.format("%s: no such" + " file or directory%n", file_locationString_use);
	        				} catch (DirectoryNotEmptyException x) {
	        				    System.err.format("%s not empty%n", file_locationString_use);
	        				} catch (IOException x) {
	        				    // File permission problems are caught here.
	        				    System.err.println(x);
	        				}
	        				textAreaLogOffline.append("Deleted : " + file_locationString + newline);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				//System.out.println(e);
	        			}	
	                //if  file
	                }else if(f.isFile() && returnVal == 0){
	                	//file_locationString_use = file_locationString;
	                	try {
	                		findUserID(text_username_login);
	                		getSaltAndPasswordStudent();
	                		System.out.println(passwordEncryptStudent);
	                		System.out.println(saltEncryptStudent);
	                		
	        				encrypt.encryptOnlineStudent(passwordEncryptStudent, file_locationString, saltEncryptStudent);
	        				textAreaLogOffline.append("Encrypting : " + file_locationString + newline);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				//System.out.println(e);
	        			}
	                }
	            }
	            else{
	            	try{
	            		textAreaLogOffline.append("File not selected. "  + newline);
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            }
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		TeacherButtonEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TeacherButtonEncrypt.setBounds(282, 129, 180, 80);
		panel_5_2.add(TeacherButtonEncrypt);
		
		JLabel lblTeacherHomepage = new JLabel("Teacher: Homepage");
		lblTeacherHomepage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherHomepage.setBounds(62, 45, 185, 37);
		panel_5_2.add(lblTeacherHomepage);
		
		JLabel lblStudentCourseDetails = new JLabel("Student: Course details");
		lblStudentCourseDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStudentCourseDetails.setBounds(62, 52, 230, 37);
		panel_6_2.add(lblStudentCourseDetails);
		
		JButton btnRemoveFromCourse = new JButton("Remove from course");
		btnRemoveFromCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findCourseID(); //gets user_ID from the selected JList
				//System.out.println(course_ID);
				findUserID(text_username_login);
				RemoveDataStudentOnCourse(); //removes student from course
			}
		});
		btnRemoveFromCourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRemoveFromCourse.setBounds(62, 259, 199, 43);
		panel_6_2.add(btnRemoveFromCourse);
		
		JButton button_1 = new JButton("Return to homepage");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listCourseData.clear();
				listModel_list_all_course_details.removeAllElements();
				StringStudetOnCourseSelected = null;
				StringListAllCoursesStudentOnSelected = null;
				panel_5_1.setVisible(true);
				panel_6_2.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(62, 360, 200, 43);
		panel_6_2.add(button_1);
		
		JLabel lblListOfCourses = new JLabel("List of courses:");
		lblListOfCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListOfCourses.setBounds(62, 115, 142, 29);
		panel_6_2.add(lblListOfCourses);
		
		JLabel lblCourseDetails = new JLabel("Course details:");
		lblCourseDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCourseDetails.setBounds(279, 115, 142, 29);
		panel_6_2.add(lblCourseDetails);

		JButton button_2 = new JButton("Return to homepage"); //student returnto homepage on url page
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel_URL_student.removeAllElements();
				textField_4.setText(null);
				panel_5_1.setVisible(true);
				panel_6_3.setVisible(false);
				StringListURLStudentSelected = null;
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBounds(68, 333, 200, 43);
		panel_6_3.add(button_2);
		
		JLabel lblListOfUrls = new JLabel("List of URL's:");
		lblListOfUrls.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListOfUrls.setBounds(68, 100, 142, 29);
		panel_6_3.add(lblListOfUrls);
		
		JLabel lblStudentUrl = new JLabel("Student: URL");
		lblStudentUrl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStudentUrl.setBounds(68, 52, 230, 37);
		panel_6_3.add(lblStudentUrl);
		

		
		

		JButton button_3 = new JButton("Remove");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeLink(StringListURLStudentSelected);
				
				listModel_URL_student.removeAllElements();
				findUserID(text_username_login);
				addURLShow(listModel_URL_student);
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_3.setBounds(391, 192, 142, 43);
		panel_6_3.add(button_3);
		
		JButton btnAdd = new JButton("Add"); //student
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stringurlgetstudent =  textField_4.getText();
				findUserID(text_username_login);
				addURL(Stringurlgetstudent);
				listModel_URL_student.removeAllElements();
				findUserID(text_username_login);
				addURLShow(listModel_URL_student);
				textField_4.setText(null);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBounds(543, 192, 142, 43);
		panel_6_3.add(btnAdd);
		
		
		
		JButton button_8 = new JButton("Add"); //teacher
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stringurlgetteacher =  textField_5.getText();
				findUserID(text_username_login);
				addURL(Stringurlgetteacher);
				listModel_URL_teacher.removeAllElements();
				findUserID(text_username_login);
				addURLShow(listModel_URL_teacher);
				textField_5.setText(null);
			}
		});
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_8.setBounds(543, 192, 142, 43);
		panel_6_1.add(button_8);
		
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(391, 135, 294, 43);
		panel_6_3.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblTeacherStudentDetails = new JLabel("Teacher: Course and student details");
		lblTeacherStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherStudentDetails.setBounds(149, 10, 339, 37);
		panel_6_4.add(lblTeacherStudentDetails);
		
		JLabel lblListOfYour = new JLabel("Select course:");
		lblListOfYour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListOfYour.setBounds(42, 42, 178, 29);
		panel_6_4.add(lblListOfYour);
		
		JButton button_5 = new JButton("Remove");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findCourseID(); //gets course_ID from the selected JList
				findUserID(StringStudetOnCourseSelected);
				RemoveDataStudentOnCourse(); //removes student from course
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_5.setBounds(419, 183, 142, 43);
		panel_6_4.add(button_5);
		
		JButton button_6 = new JButton("Return to homepage");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel_createCourse.removeAllElements();
				listModel_list_all_students.removeAllElements();
				listModel_students_on_course.removeAllElements();
				
				panel_5_2.setVisible(true);
				panel_6_4.setVisible(false);
				
				StringCourseSelected = null;
				StringListAllStudentsSelected = null;
				
				textField_course_name.setText("");
				textField_course_start_year.setText("");
				textField_course_start_month.setText("");
				textField_course_start_day.setText("");
				textField_course_end_year.setText("");
				textField_course_end_month.setText("");
				textField_course_end_day.setText("");
				textField_course_description.setText("");
			}
		});
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_6.setBounds(419, 382, 200, 43);
		panel_6_4.add(button_6);
		
		JLabel lblListOfStudents = new JLabel("List of students and teachers on course:");
		lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListOfStudents.setBounds(419, 42, 320, 29);
		panel_6_4.add(lblListOfStudents);
		
		
		
		
		
		JButton button_7 = new JButton("Add");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findUserID(StringListAllStudentsSelected);
				insertDataCourseStudent();
				user_ID = 0;
			}
		});
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_7.setBounds(571, 183, 142, 43);
		panel_6_4.add(button_7);
		
		//adds create course details
		JButton button_create_course_add = new JButton("Add");
		button_create_course_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findUserID(text_username_login);
				create_course_add_details();
				user_ID = 0;
			}

			
		});
		button_create_course_add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_create_course_add.setBounds(259, 382, 100, 43);
		panel_6_4.add(button_create_course_add);
		
		textField_course_name = new JTextField();
		textField_course_name.setColumns(10);
		textField_course_name.setBounds(192, 183, 167, 30);
		panel_6_4.add(textField_course_name);
		
		JButton button_create_course_remove = new JButton("Remove");
		button_create_course_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveDataCourse();
			}
		});
		button_create_course_remove.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_create_course_remove.setBounds(42, 382, 100, 43);
		panel_6_4.add(button_create_course_remove);
		
		JButton button_create_course_modify = new JButton("Modify");
		button_create_course_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifyCourseData();
			}
		});
		button_create_course_modify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_create_course_modify.setBounds(149, 382, 100, 43);
		panel_6_4.add(button_create_course_modify);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(41, 185, 71, 22);
		panel_6_4.add(lblName);
		
		JLabel lblStartData = new JLabel("Start date:");
		lblStartData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStartData.setBounds(42, 224, 100, 22);
		panel_6_4.add(lblStartData);
		
		textField_course_start_year = new JTextField();
		textField_course_start_year.setColumns(10);
		textField_course_start_year.setBounds(192, 222, 49, 30);
		panel_6_4.add(textField_course_start_year);
		
		JLabel lblYyyy = new JLabel("YYYY");
		lblYyyy.setBounds(202, 255, 34, 14);
		panel_6_4.add(lblYyyy);
		
		textField_course_start_month = new JTextField();
		textField_course_start_month.setColumns(10);
		textField_course_start_month.setBounds(251, 222, 49, 30);
		panel_6_4.add(textField_course_start_month);
		
		JLabel lblMm = new JLabel("MM");
		lblMm.setBounds(266, 255, 34, 14);
		panel_6_4.add(lblMm);
		
		textField_course_start_day = new JTextField();
		textField_course_start_day.setColumns(10);
		textField_course_start_day.setBounds(310, 222, 49, 30);
		panel_6_4.add(textField_course_start_day);
		
		JLabel lblDd = new JLabel("DD");
		lblDd.setBounds(325, 255, 34, 14);
		panel_6_4.add(lblDd);
		
		JLabel label_2 = new JLabel("DD");
		label_2.setBounds(325, 316, 34, 14);
		panel_6_4.add(label_2);
		
		textField_course_end_day = new JTextField();
		textField_course_end_day.setColumns(10);
		textField_course_end_day.setBounds(310, 280, 49, 30);
		panel_6_4.add(textField_course_end_day);
		
		JLabel label_3 = new JLabel("MM");
		label_3.setBounds(266, 316, 34, 14);
		panel_6_4.add(label_3);
		
		textField_course_end_month = new JTextField();
		textField_course_end_month.setColumns(10);
		textField_course_end_month.setBounds(251, 280, 49, 30);
		panel_6_4.add(textField_course_end_month);
		
		textField_course_end_year = new JTextField();
		textField_course_end_year.setColumns(10);
		textField_course_end_year.setBounds(192, 280, 49, 30);
		panel_6_4.add(textField_course_end_year);
		
		JLabel label_4 = new JLabel("YYYY");
		label_4.setBounds(202, 316, 34, 14);
		panel_6_4.add(label_4);
		
		JLabel lblEndData = new JLabel("End data:");
		lblEndData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEndData.setBounds(42, 282, 100, 22);
		panel_6_4.add(lblEndData);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescription.setBounds(42, 346, 100, 22);
		panel_6_4.add(lblDescription);
		
		textField_course_description = new JTextField();
		textField_course_description.setColumns(10);
		textField_course_description.setBounds(192, 341, 167, 30);
		panel_6_4.add(textField_course_description);
		

		
		
		JLabel lblListOfAll = new JLabel("List of all students:");
		lblListOfAll.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListOfAll.setBounds(419, 231, 230, 29);
		panel_6_4.add(lblListOfAll);
		
		
		
		JButton button = new JButton("Return to homepage");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel_URL_student.removeAllElements();
				textField_5.setText(null);
				panel_5_2.setVisible(true);
				panel_6_1.setVisible(false);
				StringListURLTeacherSelected = null;
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(68, 333, 200, 43);
		panel_6_1.add(button);
		
		JLabel label_5 = new JLabel("List of URL's:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(68, 100, 142, 29);
		panel_6_1.add(label_5);
		
		JLabel lblTeacherUrl = new JLabel("Teacher: URL");
		lblTeacherUrl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherUrl.setBounds(68, 52, 230, 37);
		panel_6_1.add(lblTeacherUrl);
		
		
		
		
		
		JButton button_4 = new JButton("Remove");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeLink(StringListURLTeacherSelected);
				
				listModel_URL_teacher.removeAllElements();
				findUserID(text_username_login);
				addURLShow(listModel_URL_teacher);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_4.setBounds(391, 192, 142, 43);
		panel_6_1.add(button_4);
		
		
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(391, 135, 294, 43);
		panel_6_1.add(textField_5);
		
		JMenuBar menuBar = new JMenuBar();
		frmDragonShield.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		

		
		JMenuItem mntmNewMenuItem = new JMenuItem("Back to main menu and logout");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_3.setText("");  
				passwordField.setText("");  
				textField.setText("");  
				passwordField_1.setText(""); 
				textField_2.setText("");
				bg.clearSelection(); 
				
				textField_course_name.setText("");
				textField_course_start_year.setText("");
				textField_course_start_month.setText("");
				textField_course_start_day.setText("");
				textField_course_end_year.setText("");
				textField_course_end_month.setText("");
				textField_course_end_day.setText("");
				textField_course_description.setText("");
				
				loggedIn = false;
				checkIfTeacher = false;
				listModel_createCourse.removeAllElements();
				listModel_list_all_students.removeAllElements();
				listModel_students_on_course.removeAllElements();
				listModel_list_all_course_student_on.removeAllElements();
				listModel_list_all_course_details.removeAllElements();
				listModel_URL_student.removeAllElements();
				listModel_URL_teacher.removeAllElements();
				StringCourseSelected = null;
				StringListAllStudentsSelected = null;
				StringListAllCoursesStudentOnSelected = null;
				StringListURLStudentSelected = null;
				StringListURLTeacherSelected = null;
				salt = null;
				saltEncryptStudent = null;
				passwordEncryptStudent = null;
				
				PATH = null;
				FILENAME = null;
				SvDPDFURL = null;
				
				panel_1.setVisible(true);
				panel_2_1.setVisible(false);
				panel_2_2.setVisible(false);
				panel_4_1.setVisible(false);
				panel_4_2.setVisible(false);
				panel_5_1.setVisible(false);
				panel_5_2.setVisible(false);
				panel_6_2.setVisible(false);
				panel_6_3.setVisible(false);
				panel_6_4.setVisible(false);
			}
		});
		mnFile.add(mntmNewMenuItem);
	}
	
	
	
	
	
	public void mysql(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
		String query = "INSERT INTO account_002 (username, password, occupation, salt)"  
        +"VALUES ('"+text_username_create+"', '"+text_password_create+"', " +
        		"'"+status_create_account+"', '"+salt+"')";		
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url,userName,password);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Thank you registering," 
            +" please login. " + text_username_create);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	
	
	public void findUserID(String username){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select user_ID from account_002 where username = '"+username+"'";		
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query);
        	while(rs.next()){
               	user_ID = rs.getInt(1);                
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	
	public void findCourseID(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select course_ID from course where name = '"+StringListAllCoursesStudentOnSelected+"'";		
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query);
        	while(rs.next()){
               	course_ID = rs.getInt(1);                
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	
	
	
	public void mysqlAddTeacher(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";		
        Statement stmt = null;
		String query = "INSERT INTO "+status_create_account+" (account_002_user_ID) VALUES ('"+user_ID+"')";		
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url,userName,password);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate(query);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void checkTeacher() {
    	String url = "jdbc:mysql://194.81.104.22/db12407404";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "s12407404"; 
        String password = "simple";
        String teacher = "teacher";
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT count(*) FROM account_002 WHERE username = '"+text_username_login+"' AND occupation = '"+teacher+"'"; 
        try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query);
        	while(rs.next()){
               	int rowCount = rs.getInt(1); 
               	if(rowCount == 0){
               		//JOptionPane.showMessageDialog(null, "Sorry your 
               		//username and password do not match");
               	}
               	else{
                	loggedIn = true;
                	JOptionPane.showMessageDialog(null, "You have logged in as a teacher, you have special privileges.");
                	checkIfTeacher = true;
                	frmDragonShield.revalidate(); 
                    frmDragonShield.repaint();
               	}
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	private void create_course_add_details() {
		String[] arrayCreateCourse = new String[8];
		arrayCreateCourse[0]	= textField_course_name.getText();
		arrayCreateCourse[1]	= textField_course_start_year.getText();
		arrayCreateCourse[2] 	= textField_course_start_month.getText();
		arrayCreateCourse[3] 	= textField_course_start_day.getText();
		arrayCreateCourse[4] 	= textField_course_end_year.getText();
		arrayCreateCourse[5] 	= textField_course_end_month.getText();
		arrayCreateCourse[6] 	= textField_course_end_day.getText();
		arrayCreateCourse[7] 	= textField_course_description.getText();
		for(int i = 0; i < arrayCreateCourse.length; i++){
			if(!StringUtils.isNullOrEmpty(arrayCreateCourse[i])){
				//insert into database 
				String url = "jdbc:mysql://194.81.104.22/db12407404";
				String driver = "com.mysql.jdbc.Driver";
				String userName = "s12407404"; 
				String password = "simple";
		        Statement stmt = null;
		        
		        String startData = arrayCreateCourse[1] + "-" + arrayCreateCourse[2] + "-" + arrayCreateCourse[3];
		        String endData = arrayCreateCourse[4] + "-" + arrayCreateCourse[5] + "-" + arrayCreateCourse[6];
				
				String query = "INSERT INTO course (name, description, start_date, end_date, account_002_user_ID) VALUES ('"+arrayCreateCourse[0]+"', '"+arrayCreateCourse[7]+"', '"+startData+"', '"+endData+"', '"+user_ID+"')";		
				try {
					Class.forName(driver).newInstance();
					Connection conn = DriverManager.getConnection(url,userName,password);
		            stmt = conn.prepareStatement(query);
		            stmt.executeUpdate(query);
		            JOptionPane.showMessageDialog(null, "Added the course: " + arrayCreateCourse[0] + " to the database.");
					conn.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "error");
				}
				break;
			}
			else{
				JOptionPane.showMessageDialog(null, "Error: Missing details.");
			}
		}
		listModel_createCourse.removeAllElements();
		UpdateListOfCourse();
		StringCourseSelected = null;
		StringListAllStudentsSelected = null;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public void UpdateListOfCourse(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select name from course where account_002_user_ID = '"+user_ID+"'";		
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		listModel_createCourse.addElement(rs.getString("name"));
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	
	
	public void getDataCourse(String StringCourseSelectedThisOne){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select course_ID, name, description, YEAR(start_date) AS year_start, month(start_date) as month_start, day(start_date) as day_start, year(end_date) as year_end, month(end_date) as month_end, day(end_date) as day_end, account_002_user_ID  from course where name = '"+StringCourseSelectedThisOne+"'";	
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);	
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query);       
        	while(rs.next()){
        		listCourseData.add(rs.getString("name")); 
        		listCourseData.add(rs.getString("description")); 
        		listCourseData.add(rs.getString("year_start")); 
        		listCourseData.add(rs.getString("month_start")); 
        		listCourseData.add(rs.getString("day_start")); 
        		listCourseData.add(rs.getString("year_end")); 
        		listCourseData.add(rs.getString("month_end")); 
        		listCourseData.add(rs.getString("day_end"));
        		listCourseData.add(rs.getString("account_002_user_ID"));
        		course_ID = rs.getInt("course_ID");
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	
	
	@SuppressWarnings("unchecked")
	public void getDataAllStudents(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select username from account_002 where occupation = 'student'";
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);	
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		listModel_list_all_students.addElement(rs.getString("username"));
        	}
        	conn.close();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error.");
        	//e.printStackTrace();
        }
    }
	
	
	
	

	
	
	
	
	public void RemoveDataCourse(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        if(StringCourseSelected == null){
        	JOptionPane.showMessageDialog(null, "No course selected, please try again.");
        }
        else{
        	String query = "delete from course where name = '"+StringCourseSelected+"'";		
    		try {
            	Class.forName(driver).newInstance();
            	Connection conn = DriverManager.getConnection(url,userName,password);	
            	stmt = conn.prepareStatement(query);
            	stmt.execute(query);
            	conn.close();
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Must remove all students from course before you delete the course.");
            	//e.printStackTrace();
            }
    		listModel_createCourse.removeAllElements();
    		findUserID(text_username_login);
    		UpdateListOfCourse();
    		StringCourseSelected = null;
    		user_ID = 0;
        }
    }
	
	
	
	
	
	public void RemoveDataStudentOnCourse(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        if(StringStudetOnCourseSelected == null && StringListAllCoursesStudentOnSelected == null){
        	JOptionPane.showMessageDialog(null, "Nothing selected, please try again.");
        }
        else{
        	String query = "delete from course_has_account_002 where course_course_ID = '"+course_ID+"' AND account_002_user_ID = '"+user_ID+"'";		
    		try {
            	Class.forName(driver).newInstance();
            	Connection conn = DriverManager.getConnection(url,userName,password);	
            	stmt = conn.prepareStatement(query);
            	stmt.execute(query);
            	conn.close();
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Error.");
            	//e.printStackTrace();
            }
    		listModel_list_all_course_student_on.removeAllElements();
    		listModel_list_all_course_details.removeAllElements();
    		listModel_students_on_course.removeAllElements();
    		showCoursesStudent();
       		displayUsersOnCourse();
       		user_ID = 0;
       		StringStudetOnCourseSelected = null;
       		StringListAllCoursesStudentOnSelected = null;
        }
    }
	
	
	
	
	
	
	public void ModifyCourseData(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        //ResultSet rs = null; 
        if(StringCourseSelected == null){
        	JOptionPane.showMessageDialog(null, "No course selected, please try again.");
        }
        else{
        	
        	String[] arrayCreateCourse = new String[8];
        	arrayCreateCourse[0]	= textField_course_name.getText();
    		arrayCreateCourse[1]	= textField_course_start_year.getText();
    		arrayCreateCourse[2] 	= textField_course_start_month.getText();
    		arrayCreateCourse[3] 	= textField_course_start_day.getText();
    		arrayCreateCourse[4] 	= textField_course_end_year.getText();
    		arrayCreateCourse[5] 	= textField_course_end_month.getText();
    		arrayCreateCourse[6] 	= textField_course_end_day.getText();
    		arrayCreateCourse[7] 	= textField_course_description.getText();
    		
    		String startData = arrayCreateCourse[1] + "-" + arrayCreateCourse[2] + "-" + arrayCreateCourse[3];
	        String endData = arrayCreateCourse[4] + "-" + arrayCreateCourse[5] + "-" + arrayCreateCourse[6];
    		
    		
        	String query = "update course set name='"+arrayCreateCourse[0]+"', description='"+arrayCreateCourse[7]+"', start_date='"+startData+"', end_date='"+endData+"' where name = '"+StringCourseSelected+"'";	
        	//"update course set name='"+arrayCreateCourse[0]+"', description='"+arrayCreateCourse[7]+"', start_date='"+startData+"', end_date='"+endData+"' where name = '"+StringCourseSelected+"'"
    		try {
            	Class.forName(driver).newInstance();
            	Connection conn = DriverManager.getConnection(url,userName,password);	
            	stmt = conn.prepareStatement(query);
            	stmt.execute(query);
            	conn.close();
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Error, please try again.");
            	//e.printStackTrace();
            }
    		listModel_createCourse.removeAllElements();
    		//listCourse.clear();
    		UpdateListOfCourse();
    		StringCourseSelected = null;
        }
    }
	
	
	
	
	public void insertDataCourseStudent(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
      
		String query = "INSERT INTO course_has_account_002  (course_course_ID, account_002_user_ID) VALUES ('"+course_ID+"', '"+user_ID+"')";		
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url,userName,password);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate(query);
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, please try again.");
			//e.printStackTrace();
		}
		listModel_students_on_course.removeAllElements();
		displayUsersOnCourse();
		user_ID = 0;
		StringStudetOnCourseSelected = null;
    }
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public void displayUsersOnCourse(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
        
        String query = "select a.username from account_002 a, course c, course_has_account_002 h " +
        		"where a.user_ID = h.account_002_user_ID  " +
        		"AND c.course_ID = h.course_course_ID AND c.name = '"+StringCourseSelected+"' ";
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);	
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		listModel_students_on_course.addElement(rs.getString("username"));
        	}
        	conn.close();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error.");
        	//e.printStackTrace();
        }
    }
	
	
	
	
	@SuppressWarnings("unchecked")
	public void showCoursesStudent(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
        String query = "select c.name from account_002 a, course c, course_has_account_002 h " +
        		"where a.user_ID = h.account_002_user_ID  " +
        		"AND c.course_ID = h.course_course_ID AND a.user_ID = '"+user_ID+"'";
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);	
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		listModel_list_all_course_student_on.addElement(rs.getString("name"));
        	}
        	conn.close();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error.");
        	//e.printStackTrace();
        }
    }
	
	
	@SuppressWarnings("unchecked")
	public void getTeacherName(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
        String query = "select username from account_002 natural join course where user_ID = account_002_user_ID AND course_ID = '"+course_ID+"'";
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);	
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		teacherName = rs.getString("username");
        	}
        	conn.close();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error.");
        	//e.printStackTrace();
        }
    }
	
	
	
	
	
	public void addURL(String urlGet){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        
        if(urlGet.isEmpty()){
        	System.out.println("error null");
        }
        else{
        	urlGet = urlGet.substring(0,urlGet.length()-1) + "1";
        	String query = "INSERT INTO link  (account_002_user_ID, url) VALUES ('"+user_ID+"', '"+urlGet+"')";		
    		try {
    			Class.forName(driver).newInstance();
    			Connection conn = DriverManager.getConnection(url,userName,password);
                stmt = conn.prepareStatement(query);
                stmt.executeUpdate(query);
    			conn.close();
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Error, please try again.");
    			//e.printStackTrace();
    		}
        }
		//listModel_URL_student.removeAllElements();
		//listModel_URL_teacher.removeAllElements();
		//displayUsersOnCourse(); //replace???????????????
		user_ID = 0;
		urlGet = null;
		StringStudetOnCourseSelected = null;
    }
	
	
	
	@SuppressWarnings("unchecked")
	public void addURLShow(DefaultListModel urlModel){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
        
        String query = "select url from link where account_002_user_ID = '"+user_ID+"' ";
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);	
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		urlModel.addElement(rs.getString("url"));
        	}
        	conn.close();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Error.");
        	//e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	public void removeLink(String URLLINK){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        	String query = "delete from link where url = '"+URLLINK+"' ";		
    		try {
            	Class.forName(driver).newInstance();
            	Connection conn = DriverManager.getConnection(url,userName,password);	
            	stmt = conn.prepareStatement(query);
            	stmt.execute(query);
            	conn.close();
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Error.");
            }
       		StringListURLStudentSelected = null;
       		URLLINK = null;
    		StringStudetOnCourseSelected = null;
        }
    
	
	
	
	
	@SuppressWarnings("unchecked")
	public void getSaltAndPasswordStudent(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select salt, password from account_002 where user_ID = '"+user_ID+"'";		
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		passwordEncryptStudent = rs.getString("password");
        		saltEncryptStudent = rs.getString("salt");
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		user_ID = 0;
    }
	
	
	
	@SuppressWarnings("unchecked")
	public void getURLDecrypt(){
		String url = "jdbc:mysql://194.81.104.22/db12407404";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "s12407404"; 
		String password = "simple";
        Statement stmt = null;
        ResultSet rs = null;
		String query = "select url from link l, course_has_account_002 h, course c, account_002 a " +
				"where a.user_ID = h.account_002_user_ID AND h.course_course_ID = c.course_ID  " +
				"AND c.account_002_user_ID = l.account_002_user_ID AND a.user_ID = '"+user_ID+"'";//update query later
		try {
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url,userName,password);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery(query); 
        	while(rs.next()){
        		SvDPDFURL = rs.getString("url");
        	}
        	conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		user_ID = 0;
    }
	
	
	
	
}
