/**
 * 
 */
package com.kaka.dailyrecord.pages;

//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import com.kaka.common.CommandList;
import com.kaka.common.UI;

/**
 * @author h_jia11
 *
 */
public class RegisterWindow extends JFrame implements WindowListener, ActionListener {

/**
	 * 
	 */
	private static final long serialVersionUID = -9198552551600520655L;
	
	/********Common Variables in this Form********************************************************/
	Font fTitle = new Font("TimesRoman", Font.BOLD, 18);
	Font fUsual = new Font("TimesRoman", Font.BOLD, 16);
	Border bRegister = BorderFactory.createLineBorder(Color.GRAY); 
	/*********************************************************************************************/
	
	RegisterWindow() {
		super();
		setTitle(CommandList.TOTAL_TITLE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 0, 0));
        
		initialFrame();
        
        addWindowListener(this);
        
        setVisible(true);
	}

	private void initialFrame() {
		// TODO Auto-generated method stub
		JPanel jp_Personal = createPersonalPanel();
		JPanel jp_Credential = createCredentialPanel();
		JPanel jp_SecurityControl = createSecurityControlPanel();
		
		//Set Global Font setting
		//UI.setUIFont(new FontUIResource(new Font("TimesRoman", Font.BOLD, 18)));

		this.setLayout(new GridLayout(3,1));
		
		this.add(jp_Personal);
		this.add(jp_Credential);
		this.add(jp_SecurityControl);
	}
	
	/**
	 * Create the Personal Information part in Register Window.
	 * @return JPanel, which includes fields for FirstName, LastName, Gender, and Age
	 */
	private JPanel createPersonalPanel() {
		JPanel jp_Personal = new JPanel(new GridLayout(4, 4, 0, 10));
		//Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
		//jp_Personal.setBorder(BorderFactory.createTitledBorder(bRegister, "Personal"));
		jp_Personal.setBorder(BorderFactory.createTitledBorder(bRegister, 
																"Personal", 
																TitledBorder.DEFAULT_JUSTIFICATION, 
																TitledBorder.DEFAULT_POSITION, 
																fTitle)
							  );
		//jp_Personal.setFont(new Font("TimesRoman", Font.BOLD, 18));
		
		//First Name
		//label
		JLabel jl_FirstName = new JLabel(CommandList.DR002_FIRSTNAME);
		jp_Personal.add(jl_FirstName);
		//input field
		JTextField jtf_FirstName = new JTextField();
		jp_Personal.add(jtf_FirstName);
		//place holder
		jp_Personal.add(new JLabel());
		//place holder
		jp_Personal.add(new JLabel());
		
		//Last Name
		//label
		JLabel jl_LastName = new JLabel(CommandList.DR002_LASTNAME);
		jp_Personal.add(jl_LastName);
		//input field
		JTextField jtf_LastName = new JTextField();
		jp_Personal.add(jtf_LastName);
		//place holder
		jp_Personal.add(new JLabel());
		//place holder
		jp_Personal.add(new JLabel());
		
		//Gender
		//label
		JLabel jl_Gender = new JLabel(CommandList.DR002_GENDER);
		jp_Personal.add(jl_Gender);
		//selectbox		
		String[] strGender = new String[] { CommandList.DR002_GENDER_FEMALE, CommandList.DR002_GENDER_MALE, CommandList.DR002_GENDER_OTHER };
		JComboBox<String> jcb_Gender = new JComboBox<String>(strGender);
		jp_Personal.add(jcb_Gender);
		//placeholder
		jp_Personal.add(new JLabel());
		//place holder
		jp_Personal.add(new JLabel());
		
		//Age
		//label
		JLabel jl_Age = new JLabel(CommandList.DR002_AGE);
		jp_Personal.add(jl_Age);
		//input field
		JTextField jtf_Age = new JTextField();
		jp_Personal.add(jtf_Age);
		//placeholder
		jp_Personal.add(new JLabel());
		//placeholder
		jp_Personal.add(new JLabel());
		
		//Setup Font
		//UI.setupFont(jp_Personal, new Font("TimesRoman", Font.BOLD, 16));
		UI.setupFont(jp_Personal, fUsual);
		
		return jp_Personal;
	}
	
	/**
	 * Create the Credential Information part in Register Window
	 * @return	JPanel, which includes fields for UserName, Password, Confirm PWD, and an instruction label for password.
	 */
	private JPanel createCredentialPanel() {
		JPanel jp_Credential = new JPanel(new GridLayout(4, 4, 0, 10));
		//Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
		//jp_Credential.setBorder(BorderFactory.createTitledBorder(bRegister, "Credential"));
		jp_Credential.setBorder(BorderFactory.createTitledBorder(bRegister, 
																  "Credential", 
																  TitledBorder.DEFAULT_JUSTIFICATION, 
																  TitledBorder.DEFAULT_POSITION, 
																  fTitle)
								);
		//jp_Credential.setFont(new Font("TimesRoman", Font.BOLD, 14));
		
		//UserName
		//label
		JLabel jl_UserName = new JLabel(CommandList.DR002_USERNAME);
		jp_Credential.add(jl_UserName);
		//input field
		JTextField jtf_UserName = new JTextField();
		jp_Credential.add(jtf_UserName);
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());
		
		//Password
		//label
		JLabel jl_Password = new JLabel(CommandList.DR002_PASSWORD);
		jp_Credential.add(jl_Password);
		//inputfield (masked)
		JPasswordField jpf_Password = new JPasswordField();
		jp_Credential.add(jpf_Password);
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());
		
		//Confrim Password
		//label
		JLabel jl_Confirm = new JLabel(CommandList.DR002_CONFIRMPASSWORD);
		jp_Credential.add(jl_Confirm);
		//inputfield (masked)
		JPasswordField jpf_Confirm = new JPasswordField();
		jp_Credential.add(jpf_Confirm);
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());
		
		//Password Instruction
		//placeholder
		jp_Credential.add(new JLabel());
		//instruction
		jp_Credential.add(new JLabel(CommandList.DR002_PASSWORDINFO));
		//placeholder
		jp_Credential.add(new JLabel());
		//placeholder
		jp_Credential.add(new JLabel());

		UI.setupFont(jp_Credential, fUsual);
		return jp_Credential;
	}
	
	/**
	 * Create the Security Information part in Register Window and the button panel
	 * @return	JPanel, which includes fields for Security Question and Answer. It also includes the buttons, SUBMIT, RESET, CANCEL.
	 */
	private JPanel createSecurityControlPanel() {
		JPanel jp_Security = new JPanel(new GridLayout(2, 2, 0, 10));
		//Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
		//jp_Security.setBorder(BorderFactory.createTitledBorder(bRegister, "Security"));
		jp_Security.setBorder(BorderFactory.createTitledBorder(bRegister, 
																"Security", 
																TitledBorder.DEFAULT_JUSTIFICATION, 
																TitledBorder.DEFAULT_POSITION, 
																fTitle)
							  );
		//jp_Security.setFont(new Font("TimesRoman", Font.BOLD, 14));
		
//		jp_Security.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill=GridBagConstraints.BOTH;
//		c.insets = new Insets(10, 0, 10, 0);
//		
//		JLabel jl_Question = new JLabel(CommandList.DR002_SECURITY_QUESTION);
//		c.gridx = 0;
//		c.gridy = 0;
//		jp_Security.add(jl_Question, c);
//		
//		JTextField jtf_Question = new JTextField();
//		c.gridx = 1;
//		c.gridy = 0;
//		c.gridwidth = 3;
//		jp_Security.add(jtf_Question, c);
//		
//		JLabel jl_Answer = new JLabel(CommandList.DR002_SECURITY_ANSWER);
//		c.gridx = 0;
//		c.gridy = 1;
//		jp_Security.add(jl_Answer, c);
//		
//		JTextField jtf_Answer = new JTextField();
//		c.gridx = 1;
//		c.gridy = 1;
//		c.gridwidth = 3;
//		c.weightx = 1;
//		jp_Security.add(jtf_Answer, c);
		
		//Security Question
		//Security Answer
		
		UI.setupFont(jp_Security, fUsual);
		return jp_Security;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		Set the initial size of login window;
		this.setSize(new Dimension(800,600));
		Dimension d_screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Set the window's location related to the screen size;
		this.setLocation(d_screenSize.width/2 - 450, d_screenSize.height/2 - 350);
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

}
