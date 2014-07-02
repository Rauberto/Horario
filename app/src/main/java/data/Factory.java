package data;


import java.util.Collection;
import java.util.Iterator;

public final class Factory {
	
	/**
	 * Metodo que a partir de un String del QR genera un array de las asignaturas contenidas en el mismo
	 * @param cadena String generado por el QR
	 * @return Array de Asignaturas contenidas en dicho QR (sin informacion extra)
	 */
	public static Asignatura[] generarAsignaturasQr(String cadena){
		Asignatura[] salida=null;
		String[] parseado=cadena.split(String.valueOf(Constantes.TASIGNATURA));
		//String datosExtra=parseado[0];
		salida=new Asignatura[parseado.length-1];
		for(int i=1;i<parseado.length;i++){
			salida[i-1]=new Asignatura(parseado[i],Asignatura.QR);
		}

		return salida;
	}
	/**
	 * Metodo empleado para a partir de una Coleccion de Asignaturas generar un QR que las contenga
	 * @param asignaturas asignaturas que quieren ser serializadas a QR
	 * @return String QR resultante
	 */
	public static String generarQrAsignaturas(Collection<Asignatura> asignaturas){
		String cadena="inicioTrama";
		Iterator<Asignatura> it=asignaturas.iterator();
		while(it.hasNext()){
			cadena+=Constantes.TASIGNATURA+it.next().toQr();
		}
		return cadena;
	}
	
	/**
	 * Genera un String QR el cual son las Asignaturas introducidas como parametro de entrada serializadas
	 * @param asig Asignaturas a serializar
	 * @return String QR resultante
	 */
	public static String generarQrAsignaturas(Asignatura[] asig){
		String cadena="comand";
		for(int i=0;i<asig.length;i++){
			cadena+=Constantes.TASIGNATURA+asig[i].toQr();
		}
		return cadena;
	}

}
