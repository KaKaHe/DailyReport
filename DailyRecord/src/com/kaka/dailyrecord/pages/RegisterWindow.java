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

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.kaka.common.CommandList;

/**
 * @author h_jia11
 *
 */
public class RegisterWindow extends JFrame implements WindowListener, ActionListener {

/**
	 * 
	 */
	private static final long serialVersionUID = -9198552551600520655L;
	
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
		JPanel jp_Credential = createCredentialPanel();
		JPanel jp_Security = createSecurityPanel();
		JPanel jp_Personal = createPersonalPanel();
		
		this.add(jp_Credential, BorderLayout.CENTER);
		this.add(jp_Security, BorderLayout.CENTER);
		this.add(jp_Personal, BorderLayout.CENTER);
	}
	
	private JPanel createCredentialPanel() {
		JPanel jp_Credential = new JPanel(new GridLayout(4, 4, 0, 30));
		Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
		jp_Credential.setBorder(BorderFactory.createTitledBorder(bRegister, "Credential"));
		jp_Credential.setFont(new Font("TimesRoman", Font.BOLD, 14));
		
		return jp_Credential;
	}
	
	private JPanel createSecurityPanel() {
		JPanel jp_Security = new JPanel(new GridBagLayout());
		Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
		jp_Security.setBorder(BorderFactory.createTitledBorder(bRegister, "Credential"));
		jp_Security.setFont(new Font("TimesRoman", Font.BOLD, 14));
		
		return jp_Security;
	}
	
	private JPanel createPersonalPanel() {
		JPanel jp_Personal = new JPanel(new GridLayout(4, 4, 0, 30));
		Border bRegister = BorderFactory.createLineBorder(Color.GRAY);
		jp_Personal.setBorder(BorderFactory.createTitledBorder(bRegister, "Personal"));
		jp_Personal.setFont(new Font("TimesRoman", Font.BOLD, 14));
		
		JLabel jl_FirstName = new JLabel(CommandList.DR002_FIRSTNAME);
		JLabel jl_LastName = new JLabel(CommandList.DR002_LASTNAME);
		
		jp_Personal.add(jl_FirstName);
		
		return jp_Personal;
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
