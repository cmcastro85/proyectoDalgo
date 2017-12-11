import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * 
 * @author camilo
 * Autores: Camilo Martinez 201531652
 *          Juan David Zambrano - 20532524
 */
public class ProblemaC {

	public static void main(String[] args){

		ArrayList<Cadena> cadenas;
		try {
			//File f = new File("./data/testC.in");
			InputStreamReader is= new InputStreamReader(System.in);
			//FileReader is = new FileReader(f);
			BufferedReader br = new BufferedReader(is);

			String line = br.readLine();
			
			while(line != null){
				int n = Integer.parseInt(line.split(" ")[0]);
				int k = Integer.parseInt(line.split(" ")[1]);
				cadenas = new ArrayList<>();
				for(int i = 0; i < n; i++){
					Cadena cad = new Cadena(br.readLine());
					cadenas.add(cad);
				}
				ProblemaC prob = new ProblemaC(n, k, cadenas);
				prob.execute();
				
				line = br.readLine();
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	

	public static class Cadena{
		char[] caracteres;
		String cadena;
		int pos;

		public Cadena(String s){
			cadena = s;
			caracteres = s.toCharArray();
			pos = 0;
		}

		public boolean finish(){
			return pos == caracteres.length;
		}
		
		public String toString(){
			return cadena;
		}

		public void reset() {
			pos = 0;
			
		}
		
		public boolean avanzar(char a){
			if(a==caracteres[pos]){
				pos++;
				return true;
			}
			pos = 0;
			return false;
		}

		public char act() {
			return caracteres[pos];
		}

		public int faltan() {
			return (caracteres.length-pos);
		}
	}

	ArrayList<Cadena> cadenas;

	int n;

	int k;

	public ProblemaC(int pn, int pk, ArrayList<Cadena> cad){
		n = pn;
		k = pk;
		cadenas = cad;
	}
	
	public void execute() {
		resetCounters();
		String min = probarCadenas(0);
		//System.out.println("Resultado 0: " + min);
		resetCounters();
		for(int i = 1; i < cadenas.size(); i++){
			String a = probarCadenas(i);
			//System.out.println("Resultado " + i + ": " + a);
			if(a.length() < min.length() && a != ""){
				min = a;
			}
			resetCounters();
		}
		
		System.out.println(min);
	}
	
	public String probarCadenas(int inic){
		//System.out.println(cadenas);
		String r = "";
		boolean[] check = new boolean[cadenas.size()];
		int[] backPos = new int[cadenas.size()];
		for(int a:backPos){
			a = 0;
			//System.out.println(a);
			
		}
		int i = 0;
		int act = inic;
		while(!checkAllDone() && i < 10){
			i++;
			
			char actual = cadenas.get(act).act();
			//System.out.println("cadena actual: " + cadenas.get(act));
			//System.out.println(actual);
			for(int j = 0; j < cadenas.size(); j++){
				
				if(!cadenas.get(j).finish() && j != act)
					cadenas.get(j).avanzar(actual);
			}
			cadenas.get(act).avanzar(actual);
		    //System.out.println(cadenas.get(act).avanzar(actual));
			if(cadenas.get(act).finish())
				//System.out.println("nuevaCadena");
				act = getNextPosible();
			
			r+= ""+actual;
		}
		
		
		
		return r;
	}
	
	private int getNextPosible() {
		int min = 100000;
		int posmin = 0;
		int j = 0;
		for(Cadena c:cadenas){
			if(!c.finish()){
				//System.out.println(c + " falatan: "+ c.faltan());
				min = c.faltan()<min?c.faltan():min;
				posmin = j;
			}
			j++;
		}
		//System.out.println("minimo: " + cadenas.get(posmin));
		return posmin;
	}

	public boolean checkAllDone(){
		for(Cadena c:cadenas){
			if(!c.finish()) return false;
		}
		return true;
	}
	
	public void resetCounters(){
		for(Cadena c:cadenas){
			c.reset();
		}
	}

}
