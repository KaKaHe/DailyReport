/**
 * 
 */
package com.kaka.dailyrecord.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
		JPanel jp_Personal = createPersonalPanel();
		
		this.add(jp_Personal, BorderLayout.CENTER);
	}
	
	private JPanel createPersonalPanel() {
		JPanel jp_Personal = new JPanel(new GridLayout(4, 4, 0, 30));
		
		JLabel jl_FirstName = new JLabel();
		
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

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
