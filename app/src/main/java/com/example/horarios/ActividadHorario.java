package com.example.horarios;

import android.app.Activity;
import android.os.Bundle;

import data.Horario;

public class ActividadHorario extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Horario.generarHorario();
		TableMainLayout este=new TableMainLayout(this);
		setContentView(este);
	}
}
