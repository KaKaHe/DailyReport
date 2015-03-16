package com.kaka.dailyrecord.pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import javax.swing.text.Document;

import com.kaka.common.CommandList;
import com.kaka.common.MD5;
import com.kaka.common.UI;

/**
 * Screen ID: DR002
 * @author KaKa
 *
 */
public class RegisterWindow extends JFrame implements WindowListener, ActionListener {

	private static final long serialVersionUID = -9198552551600520655L;
	
	/********Global Variables in this Form********************************************************/
	Font fTitle = new Font("TimesRoman", Font.BOLD, 18);
	Font fUsual = new Font("TimesRoman", Font.BOLD, 16);
	Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
	ArrayList<String> arlValues = new ArrayList<String>();
	/*********************************************************************************************/
	
	RegisterWindow() {
		super();
		setTitle(CommandList.TOTAL_TITLE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 0, 0));
        
		initialFrame();
        
        addWindowListener(this);
        
        setVisible(true);
	}

	/**
	 * Initialize the main frame
	 */
	private void initialFrame() {
		// TODO Auto-generated method stub
		JPanel jp_Personal = createPersonalPanel();
		JPanel jp_Credential = createCredentialPanel();
		JPanel jp_Security = createSecurityPanel();
		JPanel jp_Control = createControlPanel();
		
		//Set Global Font setting
		//UI.setUIFont(new FontUIResource(new Font("TimesRoman", Font.BOLD, 18)));

		//this.setLayout(new GridLayout(3,1));
		
		this.add(jp_Personal);
		this.add(jp_Credential);
		this.add(jp_Security);
		this.add(jp_Control);
	}
	
	/**
	 * Create the Personal Information part in Register Window.
	 * @return JPanel, which includes fields for FirstName, LastName, Gender, and Age.
	 */
	private JPanel createPersonalPanel() {
		JPanel jp_Personal = new JPanel(new GridLayout(4, 4, 0, 10));
		jp_Personal.setName("Personal");
		jp_Personal.setBorder(BorderFactory.createTitledBorder(bRegister, 
																CommandList.DR002_PERSONAL_PANEL, //"Personal", 
																TitledBorder.DEFAULT_JUSTIFICATION, 
																TitledBorder.DEFAULT_POSITION, 
																fTitle)
							  );
		
		//First Name
		//place holder
		jp_Personal.add(new JLabel());
		//label
		JLabel jl_FirstName = new JLabel(CommandList.DR002_FIRSTNAME);
		jp_Personal.add(jl_FirstName);
		//input field
		JTextField jtf_FirstName = new JTextField();
		jtf_FirstName.setName(CommandList.DR002_FIRSTNAME);
		jp_Personal.add(jtf_FirstName);
		//place holder
		jp_Personal.add(new JLabel());
		
		//Last Name
		//place holder
		jp_Personal.add(new JLabel());
		//label
		JLabel jl_LastName = new JLabel(CommandList.DR002_LASTNAME);
		jp_Personal.add(jl_LastName);
		//input field
		JTextField jtf_LastName = new JTextField();
		jtf_LastName.setName(CommandList.DR002_LASTNAME);
		jp_Personal.add(jtf_LastName);
		//place holder
		jp_Personal.add(new JLabel());
		
		//Gender
		//placeholder
		jp_Personal.add(new JLabel());
		//label
		JLabel jl_Gender = new JLabel(CommandList.DR002_GENDER);
		jp_Personal.add(jl_Gender);
		//selectbox		
		String[] strGender = new String[] { CommandList.DR002_GENDER_FEMALE, CommandList.DR002_GENDER_MALE, CommandList.DR002_GENDER_OTHER };
		JComboBox<String> jcb_Gender = new JComboBox<String>(strGender);
		jcb_Gender.setName(CommandList.DR002_GENDER);
		jp_Personal.add(jcb_Gender);
		//place holder
		jp_Personal.add(new JLabel());
		
		//Age
		//placeholder
		jp_Personal.add(new JLabel());
		//label
		JLabel jl_Age = new JLabel(CommandList.DR002_AGE);
		jp_Personal.add(jl_Age);
		//input field
		JTextField jtf_Age = new JTextField();
		jtf_Age.setName(CommandList.DR002_AGE);
		jp_Personal.add(jtf_Age);
		//placeholder
		jp_Personal.add(new JLabel());
		
		//Setup Font
		UI.setupFont(jp_Personal, fUsual);
		
		return jp_Personal;
	}
	
	/**
	 * Create the Credential Information part in Register Window.
	 * @return	JPanel, which includes fields for UserName, Password, Confirm PWD, and an instruction label for password.
	 */
	private JPanel createCredentialPanel() {
		JPanel jp_Credential = new JPanel(new GridLayout(4, 4, 0, 10));
		jp_Credential.setName("Credential");
		jp_Credential.setBorder(BorderFactory.createTitledBorder(bRegister, 
																  CommandList.DR002_CREDENTIAL_PANEL, //"Credential", 
																  TitledBorder.DEFAULT_JUSTIFICATION, 
																  TitledBorder.DEFAULT_POSITION, 
																  fTitle)
								);
		
		//UserName
		//placeholder
		jp_Credential.add(new JLabel());
		//label
		JLabel jl_UserName = new JLabel(CommandList.DR002_USERNAME);
		jp_Credential.add(jl_UserName);
		//input field
		JTextField jtf_UserName = new JTextField();
		jtf_UserName.setName(CommandList.DR002_USERNAME);
		jp_Credential.add(jtf_UserName);
		//placeholder
		jp_Credential.add(new JLabel());
		
		//Password
		//placeholder
		jp_Credential.add(new JLabel());
		//label
		JLabel jl_Password = new JLabel(CommandList.DR002_PASSWORD);
		jp_Credential.add(jl_Password);
		//inputfield (masked)
		JPasswordField jpf_Password = new JPasswordField();
		jpf_Password.setName(CommandList.DR002_PASSWORD);
		jp_Credential.add(jpf_Password);
		//Instruction
		JLabel jl_Instruction = new JLabel(CommandList.DR002_PASSWORDINFO);
		jp_Credential.add(jl_Instruction);
		
		//Confrim Password
		//placeholder
		jp_Credential.add(new JLabel());
		//label
		JLabel jl_Confirm = new JLabel(CommandList.DR002_CONFIRMPASSWORD);
		jp_Credential.add(jl_Confirm);
		//inputfield (masked)
		JPasswordField jpf_Confirm = new JPasswordField();
		jpf_Confirm.setName(CommandList.DR002_CONFIRMPASSWORD);
		jp_Credential.add(jpf_Confirm);
		//placeholder
		jp_Credential.add(new JLabel());
		
		//Password Instruction
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());

		//Set Font
		UI.setupFont(jp_Credential, fUsual);
		return jp_Credential;
	}
	
	/**
	 * Create the Security Information part in Register Window.
	 * @return	JPanel, which includes fields for Security Question and Answer.
	 */
	private JPanel createSecurityPanel() {		
		JPanel jp_Security = new JPanel(new GridLayout(1, 2, 0, 0));
		JPanel jp_SecurityL = new JPanel(new GridLayout(4, 2, 0, 10));
		JPanel jp_SecurityT = new JPanel(new GridLayout(4, 1, 0, 10));
		jp_Security.setName("Security");
		jp_Security.setBorder(BorderFactory.createTitledBorder(bRegister, 
																CommandList.DR002_SECURITY_PANEL, //"Security", 
																TitledBorder.DEFAULT_JUSTIFICATION, 
																TitledBorder.DEFAULT_POSITION, 
																fTitle)
								);
		
		//Security Question
		//placeholder
		jp_SecurityL.add(new JLabel());
		//label
		JLabel jl_Question = new JLabel(CommandList.DR002_SECURITY_QUESTION);
		jp_SecurityL.add(jl_Question);
		//input field
		JTextField jtf_Question = new JTextField();
		jtf_Question.setName(CommandList.DR002_SECURITY_QUESTION);
		jp_SecurityT.add(jtf_Question);
		
		//Security Answer
		//placeholder
		jp_SecurityL.add(new JLabel());
		//label
		JLabel jl_Answer = new JLabel(CommandList.DR002_SECURITY_ANSWER);
		jp_SecurityL.add(jl_Answer);
		//input field
		JTextField jtf_Answer = new JTextField();
		jtf_Answer.setName(CommandList.DR002_SECURITY_ANSWER);
		jp_SecurityT.add(jtf_Answer);
		
		//placeholder first row
		jp_SecurityL.add(new JLabel());
		jp_SecurityL.add(new JLabel());
		jp_SecurityT.add(new JLabel());
		
		//placeholder second row
		jp_SecurityL.add(new JLabel());
		jp_SecurityL.add(new JLabel());
		jp_SecurityT.add(new JLabel());
		
		//Assemble
		jp_Security.add(jp_SecurityL);
		jp_Security.add(jp_SecurityT);
		
		//Set Font
		UI.setupFont(jp_Security, fUsual);
		return jp_Security;
	}
	
	/**
	 * Create the control part in Register Window.
	 * @return	JPanel, which includes 3 buttons, Submit, Reset, and Cancel.
	 */
	private JPanel createControlPanel() {
		JPanel jp_Button = new JPanel(new GridLayout(3, 5, 15, 30));
		jp_Button.setName(CommandList.DR002_CONTROL_PANEL);
		
		//first row
		//placeholders
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		
		//second row
		//placeholder
		jp_Button.add(new JLabel());
		//Submit
		JButton jb_Submit = new JButton(CommandList.DR002_SUBMIT);
		jb_Submit.addActionListener(this);
		jp_Button.add(jb_Submit);
		//Reset
//		JButton jb_Reset = new JButton(CommandList.DR002_RESET);
//		jb_Reset.addActionListener(this);
//		jp_Button.add(jb_Reset);
		jp_Button.add(new JLabel());
		//Cancel
		JButton jb_Cancel = new JButton(CommandList.DR002_CANCEL);
		jb_Cancel.addActionListener(this);
		jp_Button.add(jb_Cancel);
		//placeholder
		jp_Button.add(new JLabel());
		
		//third row
		//placeholders
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());
		jp_Button.add(new JLabel());

		UI.setupFont(jp_Button, fUsual);
		return jp_Button;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR002_SUBMIT)) {
			//After clicking on "Submit" Button
			/**********1. Validate the input fields****************************/
			//Get the JPanel object which is including all components
			Object jcRoot = ((JButton)arg0.getSource()).getParent().getParent();
			//Pass the root container to validation function
			Boolean valiResult = validateInput(jcRoot);

			/**********2. Get all values after validation**********************/
			if(valiResult) {
				//if all the inputs are ok, it needs to check if the user name is available.
			} else {
				//if there are error in inputs, show error message of the problems.
			}
			/**********3. Update data file*************************************/
			
//		} else if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR002_RESET)) {
//			
		} else if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR002_CANCEL)) {
			this.dispose();
			Login login = Login.getInstance();
			login.setVisible(true);
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		Set the initial size of login window;
		this.setSize(new Dimension(850,800));
		Dimension d_screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Set the window's location related to the screen size;
		this.setLocation(d_screenSize.width/2 - 450, d_screenSize.height/2 - 450);
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param container
	 * @return
	 */
	private Boolean validateInput(Object container) {
		
		if(container instanceof JPanel) {
			Component[] root = ((JPanel) container).getComponents();
			Component[] ends;
			JPanel jpChild;
			String strValue = null;
			Boolean bResult = true;
			arlValues.clear();
			
			for(int ite = 0; ite < root.length; ite ++) {
				if(root[ite] instanceof JPanel) {
					jpChild = (JPanel)root[ite];
					if(jpChild.getName().equals(CommandList.DR002_CONTROL_PANEL)) {
						continue;
					} else if(jpChild.getName().equals(CommandList.DR002_PERSONAL_PANEL)) {
						ends = jpChild.getComponents();
						
						for(int it = 0; it < ends.length; it ++) {
							if(ends[it] instanceof JTextField) {
								switch(ends[it].getName()) {
									case CommandList.DR002_FIRSTNAME:
										strValue = ((JTextField)ends[it]).getText();
										if(!strValue.matches("^[a-zA-z]+([ '-][a-zA-Z]+)*$")) {
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
										}
										arlValues.add(0, strValue);
										break;
									case CommandList.DR002_LASTNAME:
										strValue = ((JTextField)ends[it]).getText();
										if(!strValue.matches("^[a-zA-z]+([ '-][a-zA-Z]+)*$")) {
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
										}
										arlValues.add(1, strValue);
										break;
									case CommandList.DR002_AGE:
										strValue = ((JTextField)ends[it]).getText();
										if(!strValue.matches("^[1-9][0-9]?$")) {
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
										}
										arlValues.add(3, strValue);
										break;
									default:
								}
							} else if (ends[it] instanceof JComboBox) {
								if(ends[it].getName().equals(CommandList.DR002_GENDER)) {
									strValue = ((JComboBox<?>)ends[it]).getSelectedItem().toString();
									arlValues.add(2, strValue);
								}
							}
						}
					} else if(jpChild.getName().equals(CommandList.DR002_CREDENTIAL_PANEL)) {
						ends = jpChild.getComponents();
						
						for(int it = 0; it < ends.length - 1; it ++) {
							if(ends[it] instanceof JTextField) {
								switch(ends[it].getName()) {
									case CommandList.DR002_USERNAME:
										strValue = ((JTextField)ends[it]).getText();
										if(!strValue.matches("")) {
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
										}
										arlValues.add(10, strValue);
										break;
									case CommandList.DR002_PASSWORD:
										Document passDoc = ((JPasswordField)ends[it]).getDocument();
										try {
											strValue = passDoc.getText(0, passDoc.getLength());
											if(!strValue.matches("^[a-zA-Z0-9&*_@.#]{8,16}$")) {
												bResult &= false;
												strValue = "Error";
												ends[it].setBackground(Color.YELLOW);
											} else {
												passDoc = ((JPasswordField)ends[it + 1]).getDocument();
												if(!passDoc.getText(0, passDoc.getLength()).equals(strValue)) {
													bResult &= false;
													strValue = "Error";
													ends[it].setBackground(Color.YELLOW);
													ends[it + 1].setBackground(Color.YELLOW);
												}
											}
											arlValues.add(11, strValue.equals("Error") ? strValue : MD5.encrypt(strValue));
										} catch (Exception e) {
											// TODO Auto-generated catch block
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
											arlValues.add(11, strValue);
										}
										break;
//									case CommandList.DR002_CONFIRMPASSWORD:
//										break;
									default:
								}
							}
						}
					} else if(jpChild.getName().equals(CommandList.DR002_SECURITY_PANEL)) {
						ends = jpChild.getComponents();
						
						for(int it = 0; it < ends.length; it ++) {
							if(ends[it] instanceof JTextField) {
								switch(ends[it].getName()) {
									case CommandList.DR002_SECURITY_QUESTION:
										strValue = ((JTextField)ends[it]).getText();
										if(strValue.equals("")) {
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
										}
										arlValues.add(31, strValue);
										break;
									case CommandList.DR002_SECURITY_ANSWER:
										strValue = ((JTextField)ends[it]).getText();
										if(strValue.equals("")) {
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
										}
										try {
											arlValues.add(32, strValue.equals("Error") ? strValue : MD5.encrypt(strValue));
										} catch (NoSuchAlgorithmException
												| UnsupportedEncodingException e) {
											// TODO Auto-generated catch block
											bResult &= false;
											strValue = "Error";
											ends[it].setBackground(Color.YELLOW);
											arlValues.add(11, strValue);
										}
										break;
									default:
								}
							}
						}
					}
				}
			}
			return bResult;
		}
		
		return false;
	}

}
