package data;

public class Constantes {
	public enum Horas {
		H800((char)0x01,0),
		H830((char)0x02,1),
		H900((char)0x03,2),
		H930((char)0x04,3),
		H1000((char)0x05,4),
		H1030((char)0x06,5),
		H1100((char)0x07,6),
		H1130((char)0x08,7),
		H1200((char)0x09,8),
		H1230((char)0x0B,9),//no se usa 0A pues es el '\r' y da problemas
		H1300((char)0x0C,10),
		H1330((char)0x0E,11),
		H1400((char)0x0F,12),//no se usa el 0D pues es el '\n' y genera problemas luego
		H1430((char)0x10,13),
		H1500((char)0x11,14),
		H1530((char)0x12,15),
		H1600((char)0x13,16),
		H1630((char)0x14,17),
		H1700((char)0x15,18),
		H1730((char)0x16,19),
		H1800((char)0x17,20),
		H1830((char)0x18,21),
		H1900((char)0x19,22),
		H1930((char)0x1A,23),
		H2000((char)0x1B,24),
		H2030((char)0x1C,25),
		H2100((char)0x1D,26),
		H2130((char)0x1E,27),
		H2200((char)0x1F,28),
		H2230((char)0x20,29);
		
		Horas(char codigo,int indice){
	        this.codigo=codigo;
	        this.indice=indice;
	    }
	    public static Horas getHora(char codigo){
	    	switch(codigo){
	    	case 0x01: return H800;
	    	case 0x02: return H830;
	    	case 0x03: return H900;
	    	case 0x04: return H930;
	    	case 0x05: return H1000;
	    	case 0x06: return H1030;
	    	case 0x07: return H1100;
	    	case 0x08: return H1130;
	    	case 0x09: return H1200;
	    	case 0x0B: return H1230;
	    	case 0x0C: return H1300;
	    	case 0x0E: return H1330;
	    	case 0x0F: return H1400;
	    	case 0x10: return H1430;
	    	case 0x11: return H1500;
	    	case 0x12: return H1530;
	    	case 0x13: return H1600;
	    	case 0x14: return H1630;
	    	case 0x15: return H1700;
	    	case 0x16: return H1730;
	    	case 0x17: return H1800;
	    	case 0x18: return H1830;
	    	case 0x19: return H1900;
	    	case 0x1A: return H1930;
	    	case 0x1B: return H2000;
	    	case 0x1C: return H2030;
	    	case 0x1D: return H2100;
	    	case 0x1E: return H2130;
	    	case 0x1F: return H2200;
	    	case 0x20: return H2230;
	    	}
	    	return null;
	    }
	    private char codigo;
	    private int indice;
	    
	    public int getIndice(){
	        return indice;
	    }
	    public char getCodigo(){
	    	return codigo;
	    }
	}
	
	public enum Aulas {
	    A0_1((char)0x01,"0.1"),
	    A0_2((char)0x02,"0.2"),
	    A0_3((char)0x03,"0.3"),
	    A0_4((char)0x04,"0.4"),
	    A0_5((char)0x05,"0.5"),
	    A0_6((char)0x06,"0.6"),
	    A0_7((char)0x07,"0.7"),
	    A0_8((char)0x08,"0.8"),
	    A0_9((char)0x09,"0.9"),
	    A0_10((char)0x29,"0.10"),
	    A0_11((char)0x0B,"0.11"),
	    A0_12((char)0x0C,"0.12"),
	    A1_0((char)0x2A,"1.0"),
	    A1_1((char)0x0E,"1.1"),
	    A1_2((char)0x0F,"1.2"),
	    A1_3((char)0x10,"1.3"),
	    A1_4((char)0x11,"1.4"),
	    A1_5((char)0x12,"1.5"),
	    A1_6((char)0x13,"1.6"),
	    A1_7((char)0x14,"1.7"),
	    A1_8((char)0x15,"1.8"),
	    A2_0((char)0x16,"2.0"),
	    A2_1((char)0x17,"2.1"),
	    A2_2((char)0x18,"2.2"),
	    A2_3((char)0x19,"2.3"),
	    A2_4((char)0x1A,"2.4"),
	    A2_5((char)0x1B,"2.5"),
	    A2_6((char)0x1C,"2.6"),
	    A2_7((char)0x1D,"2.7"),
	    A2_8((char)0x1E,"2.8"),
	    A3_0((char)0x20,"3.0"),
	    A3_1((char)0x21,"3.1"),
	    A3_2((char)0x22,"3.2"),
	    A3_3((char)0x23,"3.3"),
	    A3_4((char)0x24,"3.4"),
	    A3_5((char)0x25,"3.5"),
	    A3_6((char)0x26,"3.6"),
	    A3_7((char)0x27,"3.7"),
	    A3_8((char)0x28,"3.8");
	     
	    Aulas(char codigo,String aula){
	        this.codigo=codigo;
	        this.aula=aula;
	    }
	    public static Aulas getAula(char codigo){
	    	switch(codigo){
	    		case 0x01: return A0_1;
	    		case 0x02: return A0_2;
	    		case 0x03: return A0_3;
	    		case 0x04: return A0_4;
	    		case 0x05: return A0_5;
	    		case 0x06: return A0_6;
	    		case 0x07: return A0_7;
	    		case 0x08: return A0_8;
	    		case 0x09: return A0_9;
	    		case 0x29: return A0_10;
	    		case 0x0B: return A0_11;
	    		case 0x0C: return A0_12;
	    		case 0x2A: return A1_0;
	    		case 0x0E: return A1_1;
	    		case 0x0F: return A1_2;
	    		case 0x10: return A1_3;
	    		case 0x11: return A1_4;
	    		case 0x12: return A1_5;
	    		case 0x13: return A1_6;
	    		case 0x14: return A1_7;
	    		case 0x15: return A1_8;
	    		case 0x16: return A2_0;
	    		case 0x17: return A2_1;
	    		case 0x18: return A2_2;
	    		case 0x19: return A2_3;
	    		case 0x1A: return A2_4;
	    		case 0x1B: return A2_5;
	    		case 0x1C: return A2_6;
	    		case 0x1D: return A2_7;
	    		case 0x1E: return A2_8;
	    		case 0x20: return A3_0;
	    		case 0x21: return A3_1;
	    		case 0x22: return A3_2;
	    		case 0x23: return A3_3;
	    		case 0x24: return A3_4;
	    		case 0x25: return A3_5;
	    		case 0x26: return A3_6;
	    		case 0x27: return A3_7;
	    		case 0x28: return A3_8;
	    		
	    	}
			return null;
	    }
	     
	    private char codigo;
	    private String aula;
	    
	    public String getAula(){
	        return aula;
	    }
	    public char getCodigo(){
	    	return codigo;
	    }

	}
	
	
	public static final char TASIGNATURA=(char)0xFF;
	public static final char THORAS=(char)0xFE;
	
	public static int HORA_INICIO_HORARIO=8;
	public static boolean INICIO_MEDIA_HORA=false;
	public static boolean ASIGNATURASHORA=false;
	

	public static String SEPARADOR_NOMBRE="#";
	public static String SEPARADOR_SIGLAS="@";
	public static String SEPARADOR_HORARIO="%";
}
