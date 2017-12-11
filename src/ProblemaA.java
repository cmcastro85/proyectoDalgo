import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author camilo
 * Autores: Camilo Martinez 201531652
 *          Juan David Zambrano - 20532524
 */
public class ProblemaA {

	private double[] r;
	
	private int n;
	
	private double c;
	
	private double d;
	
	public ProblemaA(double a, double b, double c, double d, int n){
		r = new double[n];
		r[0] = a;
		r[1] = b;
		this.n= n;
		this.c = c;
		this.d = d;
		
	}
	
	public void r(){
		for(int i = 2; i<n; i++){
			r[i] = c*r[i-1] + d*r[i-2]; 
		}
	}
	
	public int cp(){
		int cp = 0;
		int i =0;
		int j =n-1;
		while(i<=j){
			if(i != j) cp += r[i]*r[j]*2;
			else cp += r[i]*r[j];
			i++;
			j--;
		}
		return cp;
	}
	
	public static void main(String[] args) throws Exception{
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
			)
				{ 
					String line = br.readLine();
					
					while(line!=null && line.length()>0){
						final String [] dataStr = line.split(" ");
						ProblemaA a = new ProblemaA(Double.parseDouble(dataStr[1]),
								Double.parseDouble(dataStr[2]),Double.parseDouble(dataStr[3]),
										Double.parseDouble(dataStr[4]),Integer.parseInt(dataStr[0]));
						a.r();
						double round = Math.round(a.cp() * 1000);
						round = round/1000;
						System.out.println(round);
						line = br.readLine();
					}
				}
	}
}
