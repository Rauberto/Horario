package data;

import java.util.ArrayList;
import java.util.Iterator;

import data.Constantes.Aulas;
import data.Constantes.Horas;

public class Asignatura {
    public static final int QR=1;
    public static final int PARSEO=0;

    private String nombre;
    private String siglas;
    private ArrayList<HoraAsignatura> horas;
    private boolean completa;//indica si tenemos toda la informacion de la asignatura descargada de internet
    //private char codigoCarrera;

    /**
     * Constructor estandar
     */
    public Asignatura(String nombre, String siglas) {
        super();
        this.nombre = nombre;
        //this.codigoCarrera=codigoCarrera;
        this.siglas = siglas;
        horas=new ArrayList<HoraAsignatura>();
    }

    /**
     * Constructor a partir de un QR o un String en el cual este el objeto serializado
     * @param cadena String QR o String de Serializacion
     * @param tipo Indica el tipo de dato introducido en cadena, QR o PARSEO respectivamente
     */
    public Asignatura(String cadena,int tipo){
        String[] temp;
        //this.codigoCarrera=codigoCarrera;
        switch(tipo){
            case PARSEO:
                break;

            case QR:
                temp=cadena.split(String.valueOf(Constantes.THORAS));
                siglas=temp[0];
                horas=new ArrayList<HoraAsignatura>();
                nombre=siglas;
                for(int i=0;i<temp[1].length()-2;i=i+3){
                    horas.add(new HoraAsignatura(this,temp[1].charAt(i),
                            Horas.getHora(temp[1].charAt(i+1)),
                            Aulas.getAula(temp[1].charAt(i+2))));
                }
                completa=false;
                break;
        }


    }
    /**
     * Serializa el objeto a un String usado para el QR
     * @return String QR resultante de serializar el objeto
     */
    public String toQr(){
        String cadena="";
        Iterator<HoraAsignatura> it=horas.listIterator();
        HoraAsignatura temp;
        cadena=siglas+Constantes.THORAS;
        while(it.hasNext()){
            temp=it.next();
            cadena+=temp.toQr();
        }

        return cadena;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getSiglas() {
        return siglas;
    }
    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
    public void anniadirHora(HoraAsignatura hora){
        horas.add(hora);
    }
    public ArrayList<HoraAsignatura> getHoras(){
        return horas;
    }

    /**
     * Serializa la clase a un string
     */
    public String serialize(){//no usado actualmente, cambialo para que pueda guardar la clase con los datos extras que no entran en el QR
        String cadena=null;
        cadena=nombre+Constantes.SEPARADOR_NOMBRE+siglas+Constantes.SEPARADOR_SIGLAS;
        Iterator<HoraAsignatura> it=horas.iterator();
        HoraAsignatura temp;
        while(it.hasNext()){
            temp=(HoraAsignatura)it.next();
            cadena+=temp.toParse()+Constantes.SEPARADOR_HORARIO;
        }
        return cadena;
    }
    /**
     * Genera un String con las siglas de la asignatura y su nombre
     */
    public String toString(){
        return "("+siglas+") "+nombre;
    }
}
