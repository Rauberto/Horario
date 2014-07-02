package com.example.horarios;



import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import data.Asignatura;
import data.Factory;
import data.Horario;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Horario.iniciar();
		Horario.cargarHorario(this);
		/*Asignatura asig=new Asignatura("Programacion Dispositivos Moviles","PDM");
		asig.anniadirHora(new HoraAsignatura(asig,'L',Horas.H930,Aulas.A2_4));
		asig.anniadirHora(new HoraAsignatura(asig,'M',Horas.H1030,Aulas.A2_1));
		asig.anniadirHora(new HoraAsignatura(asig,'L',Horas.H1030,Aulas.A2_4));
		asig.anniadirHora(new HoraAsignatura(asig,'M',Horas.H1130,Aulas.A2_1));
		Horario.anniadirAsignatura(asig);
		asig=new Asignatura("Programacion de Sistemas Empotrados y de Tiempo Real ","PSETR");
		asig.anniadirHora(new HoraAsignatura(asig,'M',Horas.H1030,Aulas.A1_7));
		asig.anniadirHora(new HoraAsignatura(asig,'M',Horas.H1130,Aulas.A1_7));
		asig.anniadirHora(new HoraAsignatura(asig,'M',Horas.H1230,Aulas.A3_1));
		asig.anniadirHora(new HoraAsignatura(asig,'M',Horas.H1330,Aulas.A3_1));
		Horario.anniadirAsignatura(asig);*/
	}
	/**
	 * Lanza la Actividad de horario
	 * @param view
	 */
	public void botonHorario(View view){
		Intent intent=new Intent(this,ActividadHorario.class);
		this.startActivity(intent);
	}
	/**
	 * Lanza la actividad para borrar una asignatura del horario
	 * @param view
	 */
	public void botonBorrar(View view){
		Intent intent=new Intent(this,ActividadBorrarAsignatura.class);
		this.startActivity(intent);
	}
	/**
	 * Lanza la Actividad para Crear QR de una asignatura
	 * @param view
	 */
	public void crearQr(View view){
		Intent intent=new Intent(this,ActividadGenerarQr.class);
		this.startActivity(intent);
	}
	/**
	 * Genera un QR con todo el contenido del horario. Es llamado desde el Layout
	 */
	public void exportarHorario(View view){
		Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
		intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
		intent.putExtra("ENCODE_DATA",Factory.generarQrAsignaturas(Horario.getListaAsignaturas()));
		try {
			startActivityForResult(intent,0);
		} catch (ActivityNotFoundException e) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.zxing.client.android")));
		}
	}
	/**
	 * Metodo llamado al recibir los datos de una lectura de QR, en el se buscan las asignaturas
	 * que puedan existir y se agregan al horario
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            String contenido = intent.getStringExtra("SCAN_RESULT");
	            //String formato = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            //Toast.makeText(this, contenido, Toast.LENGTH_LONG).show();
	            Asignatura[] asig=Factory.generarAsignaturasQr(contenido);
	            for(int i=0;i<asig.length;i++){
	            	Horario.anniadirAsignatura(asig[i]);
	            }
	            Horario.guardarHorario(this);
	            
	        } else if (resultCode == RESULT_CANCELED) {
	            // Si se cancelo la captura.
	        }
	    }
	}
	
	/**
	 * Lanza una actividad para leer un codigo QR
	 * @param view
	 */
	public void leerQr(View view){
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		try {
			startActivityForResult(intent,0);
		} catch (ActivityNotFoundException e) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.zxing.client.android")));
		}
	}
}
