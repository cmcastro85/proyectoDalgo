import java.io.BufferedReader;

import java.io.InputStreamReader;

/**
 * 
 * @author camilo
 * Autores: Camilo Martinez 201531652
 *          Juan David Zambrano
 */
public class ProblemaA {

	private double[] r;
	
	private int n;
	
	private double c;
	
	private double d;
	
	public ProblemaA(double a, double b, double c, double d, int n){
		r = new double[n+1];
		r[0] = a;
		r[1] = b;
		this.n= n;
		this.c = c;
		this.d = d;
	}
	
	public void r(){
		for(int i = 2; i<=n; i++){
			r[i] = c*r[i-2] + d*r[i-1];
		}
	}
	
	public double cp(){
		double cp = 0;
		int i =0;
		int j =n;
		while(i<=n && j>=0){
			cp += r[i]*r[j];
			System.out.println(cp + " cp " +r[i]+" i "+r[j]+" r");
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
						System.out.println(round+"........"+"\br");
						line = br.readLine();
					}
				}
	}
}
