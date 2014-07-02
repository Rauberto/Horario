package data;

import data.Constantes.Aulas;
import data.Constantes.Horas;

public class HoraAsignatura {
    private Aulas aula;//nos indica en que aula tiene lugar dicha hora
    private Asignatura asignatura;
    private char dia;
    private Horas hora;//indica la hora codificada

    public static final String SEPARADOR_HORAS="-";

    /**
     * Serializa la clase a un string
     */
    public String toParse(){//usalo para guardar los datos extra
        String cadena=null;
        //cadena=dia+horaInicio.toString()+SEPARADOR_HORAS+horaFin;
        return cadena;
    }

    /**
     * Parsea para un QR
     * @return string que representa esta clase en el QR
     */
    public String toQr(){
        String cadena=null;
        cadena=String.valueOf(dia)+String.valueOf(hora.getCodigo())+String.valueOf(aula.getCodigo());
        return cadena;
    }

    /**
     * Constructor en el que se introduce la clase serializada
     * @param tipo Indica si el string que metemos es de tipo QR reducido
     * o un QR extenso
     */
    public HoraAsignatura(String cadena){
        dia=cadena.charAt(0);
        hora=Horas.getHora(cadena.charAt(1));
        aula=Aulas.getAula(cadena.charAt(2));
    }

    public HoraAsignatura(Asignatura asignatura,char dia,Horas hora,Aulas aula){
        this.asignatura=asignatura;
        this.dia=dia;
        this.hora=hora;
        this.aula=aula;
    }


    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
    public char getDia() {
        return dia;
    }
    public Horas getHora(){
        return hora;
    }
    public String getAula(){
        return aula.getAula();
    }

}
