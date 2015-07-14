/*
 *  Copyright (C) 2013 Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/** 
 * ProgressBar.java
 *
 * Description:	    <Descripcion>
 * @author			Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 01/08/2013, 08:46:04 
 */


package ar.com.dcbarrientos;

import java.awt.EventQueue;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class ProgressBar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "https%3A%2F%2Fmywebsite%2Fdocs%2Fenglish%2Fsite%2Fmybook.do" +
	               "%3Frequest_type%3D%26type%3Dprivate";
		
		String urlDec = "";
		try {
			urlDec = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Sin codificar: \n\t" + url + "\nCodificada: \n\t"+ urlDec);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
