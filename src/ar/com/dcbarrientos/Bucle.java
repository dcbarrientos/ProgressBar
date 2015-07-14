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
 * Bucle.java
 *
 * Description:	    <Descripcion>
 * @author			Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 01/08/2013, 08:46:04 
 */


package ar.com.dcbarrientos;

import javax.swing.JProgressBar;

/**
 * @author Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class Bucle implements Runnable{
	private JProgressBar progressBar;
	private boolean pausado;
	private boolean cancelado;
	private String titulo;
	
	public Bucle(JProgressBar progressBar){
		this.progressBar = progressBar;
		pausado = false;
		cancelado = false;
	}
	
	@Override
	public void run() {
		int i = progressBar.getMinimum();
		
		System.out.println("Iniciando " + titulo);
		while(i<=progressBar.getMaximum() && !cancelado){
			if(Thread.currentThread().isInterrupted()){
				System.out.println(titulo + " cancelado.");
				cancelado = true;
			}else{
			
				progressBar.setValue(i);
				
				System.out.println(titulo + ": " + i);
				for(int p = 0; p< 100000000; p++){
						
				}
				
				while(pausado){
					try {
						synchronized(this){
							wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i++;
			}
		}
		
		System.out.println("Finalizando " + titulo);
	}
	
	public void cancelar(){
		cancelado = true;
	}
	
	public void pausar() {
		pausado = !pausado;
		if(!pausado){
			synchronized(this){
				notify();
			}
		}
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	//Linea agregada para verificar el funcionamiento de GitHub.
}
