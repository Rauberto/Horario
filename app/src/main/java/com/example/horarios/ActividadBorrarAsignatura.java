package com.example.horarios;

import java.util.Collection;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import data.Asignatura;
import data.Horario;

public class ActividadBorrarAsignatura extends Activity {
	private String[] items_lista;
	private Asignatura[] lista_asignaturas;
	private ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elegir_asignatura);
		lista=(ListView)findViewById(R.id.lista);
		rellenarLista();
		
	    lista.setOnItemClickListener(new OnItemClickListener(){
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int posicion, long id) {
		    	borrarAsignatura(lista_asignaturas[posicion].getSiglas());
		    	rellenarLista();	
		    }
		}); 
	}
	
	/**
	 * Genera las listas locales a partir de la lista del horario y rellena la listView
	 */
	public void rellenarLista(){
		Collection<Asignatura> lista_entrada=Horario.getListaAsignaturas();
		items_lista=new String[lista_entrada.size()];
		lista_asignaturas=new Asignatura[lista_entrada.size()];
		Iterator<Asignatura> it=lista_entrada.iterator();
		
		for(int i=0;it.hasNext();i++){
			lista_asignaturas[i]=it.next();
			 items_lista[i]=lista_asignaturas[i].toString();
		}
		
	    ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items_lista);
	    lista.setAdapter(adapter);
	}
	
	/**
	 * Borra una Asignatura del horario a partir de sus siglas
	 * @param siglas Borra la asignatura del horario
	 */
	public void borrarAsignatura(String siglas){
		Horario.borrarAsignatura(siglas);
		Horario.guardarHorario(this);
	}
}
