/**
 * 
 */
package com.kaka.common;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.*;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * @author jiah
 *
 */
public class UI {

	/**
	 * @param f
	 */
	public static void setUIFont(FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			
			if(value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				
				Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}
	
	/**
	 * @param c
	 * @param f
	 */
	public static void setupFont(Component c, Font f) {
		c.setFont(f);
		
		if(c instanceof Container) {
			for(Component child : ((Container) c).getComponents()) {
				setupFont( child, f);
			}
		}
	}
}
