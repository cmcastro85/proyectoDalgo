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
			cp += r[i]*r[j];
			i++;
			j--;
		}
		return cp;
	}
}
