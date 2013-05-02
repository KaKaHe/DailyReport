/**
 * 
 */
package com.kaka.dailyrecord;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

//import sun.awt.im.InputContext;
//import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.kaka.common.*;

/**
 * Screen ID: DR001
 * @author KaKa
 *
 */
public class Login extends JFrame implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3157110818330966194L;
	
	private static Login instance = null;
	private JTextField jtf_UserName = null;
	private JPasswordField jpf_Password = null;

	/**
	 * Constructor
	 */
	private Login(){
		super();
		setTitle(CommandList.TOTAL_TITLE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        
        initialFrame();
        
        addWindowListener(this);
        
        setVisible(true);
// ---- Debug Information -------------------------------------
//        setResizable(false);
// -------------------------------------------------------------------
	}
	
	public static Login getInstance() {
		if(instance == null) {
			instance = new Login();
		}
		return instance;
	}
	
	/**
	 * Initiate the login window.
	 */
	private void initialFrame() {
//		Initiate the main page
//		Create a main board for logging in.
		JPanel jp_Center = new JPanel(new GridLayout(5, 1, 0, 30));

//		jp_Center.setBorder(new LineBorder(new Color(200,200,200), 3));
//		Placeholder Panel
		jp_Center.add(new JPanel());
//		Center part, where shows the main functionalities, like login, register, deleting account, and getting password.
//		UserName part:
		jp_Center.add(createUserNamePanel());
//		PassWord part:
		jp_Center.add(createPasswordPanel());
//		Control part:
		jp_Center.add(createControlPanel());

		this.add(jp_Center, BorderLayout.CENTER);
		
	}
	
	/**
	 * Create UserName part of login window.
	 * @return JPanel, which includes a lable, a inputfield for inputing user name, and a "delete" button for deleting account.
	 */
	private JPanel createUserNamePanel() {
//		UserName part:
		JPanel jp_UserName = new JPanel(new GridLayout(1, 5, 10, 100));
//		jp_UserName.setBorder(new LineBorder(new Color(200,200,200), 3));
		jp_UserName.add(new Label());
//		Tag label
		JLabel jl_UserName = new JLabel(CommandList.DR001_USERNAME);
//		jl_UserName.setBorder(new LineBorder(new Color(200,200,200), 3));
		jl_UserName.setFont(new Font("TimesRoman",Font.BOLD,14));
		jl_UserName.setHorizontalAlignment(SwingConstants.CENTER);
		jl_UserName.setHorizontalTextPosition(SwingConstants.CENTER);
		jp_UserName.add(jl_UserName);
//		List the exist users;
//		DefaultListModel UserNameModel = new DefaultListModel();
//		JList list_UserName = new JList(UserNameModel);
//		list_UserName.setPreferredSize(new Dimension(200, 20));
//		jp_UserName.add(list_UserName);
//		JTextField jtf_UserName = new JTextField();
		jtf_UserName = new JTextField();
		jp_UserName.add(jtf_UserName);
//		Delete an exist Account;
		JButton jb_DelUser = new JButton(CommandList.DR001_DELETE);
		jb_DelUser.setFont(new Font("TimesRoman", Font.BOLD, 14));
		jp_UserName.add(jb_DelUser);
		jp_UserName.add(new Label());
		
		return jp_UserName;
	}
	
	/**
	 * Create Password part of login window.
	 * @return JPanel, which includes a label, a secret inputfield for inputing password, a "Forget" button to reset password.
	 */
	private JPanel createPasswordPanel() {
//		Password part:
		JPanel jp_Password = new JPanel(new GridLayout(1, 5, 10, 100));
		jp_Password.add(new Label());
		JLabel jl_Password = new JLabel(CommandList.DR001_PASSWORD);
		jl_Password.setFont(new Font("TimesRoman", Font.BOLD, 14));
		jl_Password.setHorizontalAlignment(SwingConstants.CENTER);
		jl_Password.setHorizontalTextPosition(SwingConstants.CENTER);
		jp_Password.add(jl_Password);
//		Password input field;
//		JPasswordField pw_Password = new JPasswordField();
		jpf_Password= new JPasswordField();
		jp_Password.add(jpf_Password);
//		To get back the forgotten password;
		JButton jb_ForgetPW = new JButton(CommandList.DR001_FORGET);
		jb_ForgetPW.setFont(new Font("TimesRoman", Font.BOLD, 14));
		jp_Password.add(jb_ForgetPW);
		jp_Password.add(new Label());
		
		return jp_Password;
	}

	/**
	 * Create Control buttons of login window.
	 * @return JPanel, which includes a "Login" button for logging in, a "Sign in" button for creating new account.
	 */
	private JPanel createControlPanel() {
		JPanel jp_Control = new JPanel(new GridLayout(1, 4, 15, 100));
		
		jp_Control.add(new JLabel());
		JButton jb_Login = new JButton(CommandList.DR001_LOGIN);
		jb_Login.setFont(new Font("TimesRoman", Font.BOLD, 14));
		jb_Login.addActionListener(this);
		jp_Control.add(jb_Login);
		JButton jb_Create = new JButton(CommandList.DR001_SIGNIN);
		jb_Create.setFont(new Font("TimesRoman", Font.BOLD, 14));
		jb_Create.addActionListener(this);
		jp_Control.add(jb_Create);
		jp_Control.add(new JLabel());
		
		return jp_Control;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		Set the initial size of login window;
		this.setSize(new Dimension(550,300));
		Dimension d_screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Set the window's location related to the screen size;
		this.setLocation(d_screenSize.width/2 - 300, d_screenSize.height/2 - 300);
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

//	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR001_LOGIN)) {
//			Button Login is clicked, action executed immidiately.
			String userName = jtf_UserName.getText();
			try {
//				String passWord = MD5.encrypt(jpf_Password.getDocument().getText(0, 4));
//				String passWord = "";
				Document passDoc = jpf_Password.getDocument();
				passDoc.getLength();
				String passWord = MD5.encrypt(passDoc.getText(0, passDoc.getLength()));
				
//				Validate the input Username and Password
				if(DataHandle.validatePassword(userName, passWord)) {
					
				} else {
//					if the input UserName or Password is invalidate.
					JOptionPane.showMessageDialog(this.getParent(), "User Name or Password is incorrect.");
					jtf_UserName.setText("");
					jpf_Password.setText("");
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR001_SIGNIN)) {
//			Button Sign in is clicked, the window browsers to Register Window
		} else if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR001_DELETE)) {
//			Button Delete is clicked, action of deleting currently input user is executed.
		} else if(arg0 != null && arg0.getActionCommand().equals(CommandList.DR001_FORGET)) {
//			Button Forget is clicked, which indicates that the user forgets his/her password, browse to password reset window.
		}
	}
}