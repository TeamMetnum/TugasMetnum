
package metode;
import java.util.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class NewtonRhapson {
	public static void main (String args []) {
		Scanner insert = new Scanner (System.in);
		int iterasi; //menunjukan banyaknya pengulangan yang di inginkan
		double start, e, Fx, Gx, Hx;
		double xn = 0;
		System.out.println ("Masukan fungsi, f(x) = ");
		String fungsi1 = insert.nextLine();
		System.out.println ("Masukan turunan dari fungsi f(x), f'(x) = ");
		String fungsi2 = insert.nextLine();
		System.out.println ("Masukan banyaknya iterasi yang diinginkan : ");
		iterasi = insert.nextInt();
		System.out.println ("Masukan maksimum  toleransi error : ");
		e = insert.nextDouble();
		System.out.println ("Masukan nilai awal pendekatan x0 : ");
		start = insert.nextDouble();
		
		for (int i=0 ; i < iterasi ; i++) {
			Fx = MathParsing (fungsi1, start);
			Gx = MathParsing (fungsi2, start);
			/*if (Fx/Gx==tak hingga) {
				System.out.println ("turunan fungsi f(x) tidak terdefinisi di x ="+start);
				break;
			}*/
			xn = ((start*Gx)-Fx)/Gx;
			Hx = MathParsing (fungsi1, xn);
			if (Math.abs(Hx)<e) {
				break;
			} else { 
				start = xn;
			}
			
		} System.out.println ("Solusi x : "+xn);
	}
	
	public static double MathParsing (String func, double var) {
		Expression e = new ExpressionBuilder (func)
		.variables("x","e")
		.build()
		.setVariable ("x",var)
		.setVariable ("e", Math.E);
		
		return e.evaluate();
	}
        }
