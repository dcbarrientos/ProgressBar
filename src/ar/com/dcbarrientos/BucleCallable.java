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
 * BucleCallable.java
 *
 * Description:	    <Descripcion>
 * @author			Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 06/08/2013, 09:26:43 
 */

package ar.com.dcbarrientos;

import java.util.concurrent.Callable;


/**
 * @author Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class BucleCallable implements Callable<Integer>{
	private Ventana ventana;
	private boolean cancelado = false;
	private String titulo = "";
	private final int SUCCESS = 1;
	//private final int ERROR = -1;
	private final int CANCEL = 0;
	
	public BucleCallable(String titulo, Ventana ventana){
		this.ventana = ventana;
		this.titulo = titulo;
	}
	
	@Override
	public Integer call(){
		int status = 0;  //status=-1->Error;status=0->Cancelado
		
		int i = ventana.getProgressBar().getMinimum();
		
		System.out.println("Iniciando " + titulo);
		while(i<=ventana.getProgressBar().getMaximum() && !cancelado){
			if(Thread.currentThread().isInterrupted()){
				System.out.println(titulo + " cancelado.");
				cancelado = true;
				status = CANCEL;
			}else{
			
				ventana.getProgressBar().setValue(i);
				
				for(int p = 0; p< 100000000; p++){
						
				}
				
				i++;
			}
		}
		status = SUCCESS;
		System.out.println("Finalizando " + titulo);
		
		ventana.procesoTerminado();

		return status;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo(String titulo){
		return titulo;
	}
}
