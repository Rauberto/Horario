package data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;


public final class Horario {
    private static LinkedHashMap<String,Asignatura> asignaturas;
    public static Casilla[] lunes;
    public static Casilla[] martes;
    public static Casilla[] miercoles;
    public static Casilla[] jueves;
    public static Casilla[] viernes;
    public static Casilla vacia;
    public static final int NUMERO_HORAS=28;
    private static boolean cargado=false;


    public static class Casilla{
        public String dato;
        public int estado;
        public static final int VACIA=0;
        public static final int OCUPADA=1;
        public static final int COLISION=2;

        public String getDato(){
            return dato;
        }
    }

    /**
     * Inicializa las variables usadas en la clase
     */
    public static void iniciar(){
        if(!cargado){
            asignaturas=new LinkedHashMap<String,Asignatura>();
            lunes=new Casilla[NUMERO_HORAS];
            martes=new Casilla[NUMERO_HORAS];
            miercoles=new Casilla[NUMERO_HORAS];
            jueves=new Casilla[NUMERO_HORAS];
            viernes=new Casilla[NUMERO_HORAS];
            vacia=new Casilla();
            limpiarHorario();
            cargado=true;
        }
    }

    /**
     * Limpia los arrays correspondientes a los diferentes dias del horario
     */
    private static void limpiarHorario(){
        for(int i=0;i<NUMERO_HORAS;i++){
            lunes[i]=new Casilla();
            lunes[i].estado=0;
            lunes[i].dato="";
            martes[i]=new Casilla();
            martes[i].estado=0;
            martes[i].dato="";
            miercoles[i]=new Casilla();
            miercoles[i].estado=0;
            miercoles[i].dato="";
            jueves[i]=new Casilla();
            jueves[i].estado=0;
            jueves[i].dato="";
            viernes[i]=new Casilla();
            viernes[i].estado=0;
            viernes[i].dato="";
        }
    }

    /**
     * Devuelve el array de asignaturas
     */
    public static Collection<Asignatura> getListaAsignaturas(){
        return asignaturas.values();
    }

    /**
     * Añade una asignatura a nuestro horario en caso de no estar ya en el mismo.
     * @param asignatura Es la asignatura que queremos agregar al horario
     * @return True si se agrego False si ya existia y nos e agrego
     */
    public static boolean anniadirAsignatura(Asignatura asignatura){
        if(!asignaturas.containsKey(asignatura.getSiglas())){
            asignaturas.put(asignatura.getSiglas(), asignatura);
            return true;
        }
        return false;
    }

    /**
     * Borra la asignatura del horario
     * @param siglas de la asignatura a borrar
     */
    public static void borrarAsignatura(String siglas){
        asignaturas.remove(siglas);
    }

    /**
     * Rellena los arrays de los diferentes dias a partir de las asignaturas que estan en
     * el horario.
     */
    public static void generarHorario(){
        Asignatura asignatura;
        Iterator<HoraAsignatura> it_horas;
        HoraAsignatura horaAsig;
        Casilla[] dia=null;
        //limpio el horario antes de volverlo a generar para evitar duplicados
        limpiarHorario();
        Collection<Asignatura> temp=asignaturas.values();
        Iterator<Asignatura> it=temp.iterator();
        //recorro las asignaturas
        while(it.hasNext()){
            asignatura=it.next();
            it_horas=asignatura.getHoras().iterator();
            //para cada asignatura recorro sus horas
            while(it_horas.hasNext()){
                horaAsig=it_horas.next();

                switch(horaAsig.getDia()){
                    case 'L':dia=lunes;
                        break;
                    case 'M':dia=martes;
                        break;
                    case 'X':dia=miercoles;
                        break;
                    case 'J':dia=jueves;
                        break;
                    case 'V':dia=viernes;
                        break;
                }

                //relleno la primera media hora
                if(dia[horaAsig.getHora().getIndice()].estado>Casilla.VACIA){
                    dia[horaAsig.getHora().getIndice()].dato+="\n";
                }
                dia[horaAsig.getHora().getIndice()].dato+=asignatura.getSiglas()+" "+horaAsig.getAula();
                dia[horaAsig.getHora().getIndice()].estado++;

                if(!Constantes.ASIGNATURASHORA){
                    //relleno la segunda media hora
                    if(dia[horaAsig.getHora().getIndice()+1].estado>Casilla.VACIA){
                        dia[horaAsig.getHora().getIndice()+1].dato+="\n";
                    }
                    dia[horaAsig.getHora().getIndice()+1].dato+=asignatura.getSiglas()+" "+horaAsig.getAula();
                    dia[horaAsig.getHora().getIndice()+1].estado++;
                }

            }
        }
    }

    /**
     * Graba los datos del horario en un fichero
     */
    public static void guardarHorario(Context con){
        FileOutputStream fos;
        try {
            fos = con.openFileOutput("Datos.txt",Context.MODE_PRIVATE);
            String cadena=Factory.generarQrAsignaturas(asignaturas.values());
            fos.write(cadena.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("Mi Aplicación",e.getMessage(),e);
        } catch (IOException e) {
            Log.e("Mi Aplicación",e.getMessage(),e);
        }
    }

    /**
     * Carga los datos del horario guardados en un fichero
     */
    public static void cargarHorario(Context con){
        try{
            FileInputStream f = con.openFileInput("Datos.txt");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(f));
            String cadena=entrada.readLine();
            String temp=entrada.readLine();
            try{
                while(temp!=null){
                    cadena+=temp;
                    temp=entrada.readLine();
                }
            }catch(Exception e){}

            Asignatura[] asignaturasLeidas=Factory.generarAsignaturasQr(cadena);

            for(int i=0;i<asignaturasLeidas.length;i++){
                asignaturas.put(asignaturasLeidas[i].getSiglas(),asignaturasLeidas[i]);
            }
        }catch(Exception e){}

    }

    //Metodos viejos con sharedPrefences no usados pues fallaba con la nueva forma de parsear las tramas

	/*
	 * Cargan los datos del horario guardados en un fichero
	 */
	/*public static void cargarHorario(Context context){
		SharedPreferences fichero = context.getSharedPreferences("ficheroHorario",Context.MODE_PRIVATE);

		if (fichero!=null){
			String valorLeido = fichero.getString("datos","-1");
			Toast.makeText(context, "Cargado:"+valorLeido, Toast.LENGTH_LONG).show();
			if(valorLeido.equals("-1")){
				return;
			}

			Asignatura[] asignaturasLeidas=Factory.generarAsignaturasQr(valorLeido);

			for(int i=0;i<asignaturasLeidas.length;i++){
				asignaturas.put(asignaturasLeidas[i].getSiglas(),asignaturasLeidas[i]);
			}
		}

	}

	/*
	 * Graba los datos del horario en un fichero
	 */
	/*public static void guardarHorario(Context con){
		SharedPreferences fichero = con.getSharedPreferences("ficheroHorario", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = fichero.edit();

		String cadena=Factory.generarQrAsignaturas(asignaturas.values());
		editor.putString("datos", "cacafuti");
		editor.commit();

	}*/
}
