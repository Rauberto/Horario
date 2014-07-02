package com.example.horarios;

import java.util.Collection;
import java.util.Iterator;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import data.Asignatura;
import data.Factory;
import data.Horario;

public class ActividadGenerarQr extends Activity {
    private String[] items_lista;
    private Asignatura[] lista_asignaturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elegir_asignatura);
        //Extraigo la lista de asignaturas del Horario
        Collection<Asignatura> lista_entrada=Horario.getListaAsignaturas();
        //Inicializo la lista a mostrar en el listView
        items_lista=new String[lista_entrada.size()];
        //Inicializo la lista asociada a la lista del lisview con las asignaturas
        lista_asignaturas=new Asignatura[lista_entrada.size()];

        //Relleno las listas
        Iterator<Asignatura> it=lista_entrada.iterator();
        for(int i=0;it.hasNext();i++){
            lista_asignaturas[i]=it.next();
            items_lista[i]=lista_asignaturas[i].toString();
        }
        //Relleno el ListView con los item de la lista
        ListView lista=(ListView)findViewById(R.id.lista);
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items_lista);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int posicion, long id) {
                crearQr(lista_asignaturas[posicion]);

            }
        });
    }
    /**
     * Lanza una actividad que creara un codigo QR de la asgnatura
     * @param asig Asignatura de la cual queremos generar el QR
     */
    public void crearQr(Asignatura asig){
        Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
        intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
        Asignatura[] as={asig};
        intent.putExtra("ENCODE_DATA",Factory.generarQrAsignaturas(as));
        try {
            startActivityForResult(intent,0);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.zxing.client.android")));
        }
    }
}

