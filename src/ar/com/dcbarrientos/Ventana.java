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
 * Ventana.java
 *
 * Description:	    <Descripcion>
 * @author			Diego C. Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 01/08/2013, 08:46:04 
 */

package ar.com.dcbarrientos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JProgressBar progressBar;
	JLabel progressBarLabel;
	JButton btnBucle;
	JButton btnCancelar;
	
	Thread[] thread;
	Bucle[] bucles;
	List<Future<Integer>> listaCallables;
	
	int iBucles; //Indice de bucles
	int nBucles; //Numero de bucles (procesos)
	int nProcesosTerminados;
	ExecutorService exec;
	ExecutorService exec2;

	private int value = 0;
	private final int MINVALUE = 0;
	private final int MAXVALUE = 100;
	private final int NUMBER_THREADS = 1;
	private final String STRPROCESANDO = "Procesando...";
	private final String STRPAUSADO = "Pausado";
	private final String STRCANCELADO = "Cancelado";
	private JButton btnBucle2;
	/**
	 * Create the frame.
	 */
	public Ventana() {
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("ProgressBar");
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 11, 414, 28);
		progressBar.setMinimum(MINVALUE);
		progressBar.setMaximum(MAXVALUE);
		contentPane.add(progressBar);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(41, 50, 89, 23);
		contentPane.add(btnSiguiente);
		btnSiguiente.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				btnSiguienteMouseClicked(e);
			}
		});
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAnteriorMouseClicked(e);
			}
		});
		btnAnterior.setBounds(171, 50, 89, 23);
		contentPane.add(btnAnterior);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(301, 50, 89, 23);
		contentPane.add(btnReset);
		btnReset.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				btnResetMouseClicked(e);
			}
		});
		
		btnBucle = new JButton("Bucle");
		btnBucle.setBounds(41, 81, 89, 23);
		contentPane.add(btnBucle);
		btnBucle.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				btnBucleMouseClicked(e);
			}
		});
		
		progressBarLabel = new JLabel("New label");
		progressBarLabel.setBounds(10, 143, 414, 14);
		contentPane.add(progressBarLabel);
		
		JButton btnPausa = new JButton("Pausa");
		btnPausa.setBounds(171, 84, 89, 23);
		contentPane.add(btnPausa);
		btnPausa.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				btnPausaMouseClicked(e);
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(301, 84, 89, 23);
		btnCancelar.setEnabled(false);
		contentPane.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				btnCancelarMouseClicked(e);
			}
		});
		
		btnBucle2 = new JButton("Bucle2");
		btnBucle2.setBounds(41, 109, 89, 23);
		btnBucle2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				btnBucle2MouseClicked(e);
			}
		});
		
		contentPane.add(btnBucle2);
	}
	
	private void btnSiguienteMouseClicked(MouseEvent e){
		if(value <= MAXVALUE){
			value++;
			progressBar.setValue(value);
		}
	}
	
	private void btnAnteriorMouseClicked(MouseEvent e){
		if(value > MINVALUE){
			value--;
			progressBar.setValue(value);
		}
	}
	
	private void btnResetMouseClicked(MouseEvent e){
		value = 0;
		progressBar.setValue(value);
	}
	
	private void btnBucleMouseClicked(MouseEvent e){
		btnBucle.setEnabled(false);
		btnCancelar.setEnabled(true);
		progressBarLabel.setText(STRPROCESANDO);
		proceso();
	}
	
	private void proceso(){
		exec = Executors.newFixedThreadPool(NUMBER_THREADS);

		nBucles = 3;
		bucles = new Bucle[nBucles];
		
		for(iBucles = 0; iBucles < nBucles; iBucles++){
			bucles[iBucles] = new Bucle(progressBar);
			bucles[iBucles].setTitulo("Bucle" + (iBucles+1));
			exec.submit(bucles[iBucles]);
		}
		
		System.out.println("-------------------Fin-------------------");
		
	}
	
	private void btnCancelarMouseClicked(MouseEvent e){
		btnBucle.setEnabled(true);
		btnCancelar.setEnabled(false);
		progressBar.setValue(MINVALUE);
		this.progressBarLabel.setText(STRCANCELADO);
		exec.shutdownNow();
		
	}
	
	private void btnPausaMouseClicked(MouseEvent e){
		if(progressBarLabel.getText().equals(STRPROCESANDO))	
			progressBarLabel.setText(STRPAUSADO);
		else
			progressBarLabel.setText(STRPROCESANDO);
	}
	
	private void btnBucle2MouseClicked(MouseEvent e){
		exec = Executors.newFixedThreadPool(NUMBER_THREADS);
		nBucles = 2;
		listaCallables = new ArrayList<Future<Integer>>();
		
		System.out.println("Cargando threads");
		for(iBucles = 0; iBucles<nBucles; iBucles++){
			Callable<Integer> task = new BucleCallable("Bucle" + (iBucles+1), this);				
			Future<Integer> miSubmit = exec.submit(task);
			listaCallables.add(miSubmit);
		}
		System.out.println("Fin carga");
		
	}
	
	public JProgressBar getProgressBar(){
		return progressBar;
	}
	
	public boolean isPoolEmpty(){
		return (listaCallables.size()==nProcesosTerminados);
	}
	
	public void procesoTerminado(){
		nProcesosTerminados++;
		
		if(isPoolEmpty()){
			exec.shutdownNow();
			fin();
		}
	}
	
	public int getNProcess(){
		return Thread.activeCount();
	}
	
	public void fin(){
		System.out.println("Todos los procesos terminados");
	}
}
