package metode;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
public class Muller {
    String muller_op;
    double h0, h1, 
            d0, d1, 
            a, b, c, 
            h, 
            Fx0, Fx1, Fx2, 
            xhasil;
    
    public Muller(String function, double error, int n, double x0, double x1, double x2){
        xhasil = 0;
        StringBuilder muller_ops = new StringBuilder();
        muller_ops.append("Proses: ");
        muller_ops.append("n\tx0\tx1\tx2\tf(x2)");
        
        for(int i=0;i<n;i++){
            Fx0 = MathParsing(function,x0);
            Fx1 = MathParsing(function,x1);
            Fx2 = MathParsing(function,x2);
            
            h0 = x1 - x0;
            h1 = x2 - x1;
            
            d0 = (Fx1-Fx0)/(x1-x0);
            d1 = (Fx2-Fx1)/(x2-x1);
            
            a = (d1-d0)/(h1+h0);
            b = a*h1 + d1;
            c = Fx2;
            
            double D = Math.sqrt(b*b-4*a*c);
            if ( Math.abs(b-D) < Math.abs(b+D) ) 
                h = (-2*c) / (b+D);
            else h=(-2*c)/(b-D);
            
            xhasil=x2+h;
            double math=MathParsing(function,xhasil);
            muller_ops.append(
                    String.format( i+"\t%.6f\t%.6f\t%.6f\t%.6f\n", 
                            x0, x1, x2, MathParsing(function,xhasil)
                    ));
            
            if ( Math.abs(MathParsing(function,xhasil)) < error ) 
                break;
            else {
                x0 = x1;
                x1 = x2;
                x2 = xhasil;
            }
        }
        //System.out.printf("\nSolusi x: %.6f \n",xhasil);
        muller_op = muller_ops.toString();
    }
    
    public String proses_muller() {
        return muller_op;
    }
    
    public double hasil_muller() {
        return xhasil;
    }
    
    public double MathParsing(String func, double var){
        Expression e = new ExpressionBuilder(func)
        .variables("x")
        .build()
        .setVariable("x", var);
        return e.evaluate();
    }
}

class MullerTest {
    public static void main(String[] args) {
        double x0,x1,x2;
        Scanner input=new Scanner(System.in);
        System.out.print("Masukkan f(x): "); //1. User menginput f(x)
        String fungsi=input.nextLine();
        System.out.print("Masukkan maksimum toleransi error : "); //2. User menginput e
        double e=input.nextDouble();
        System.out.print("Masukkan maksimum iterasi (n): "); //3. User menginput n
        int n=input.nextInt();
        System.out.print("Masukkan nilai (x0) : "); //4. User menginput a
        x0=input.nextDouble();
        System.out.print("Masukkan nilai (x1): "); //5. User menginput b
        x1=input.nextDouble();
        System.out.print("Masukkan nilai (x2): "); //6. User menginput c
        x2=input.nextDouble();

        Muller mul = new Muller(fungsi,e,n,x0,x1,x2);
        System.out.println("hasil dengan muller = " + mul.hasil_muller());
        System.out.println(mul.proses_muller());
    }
}
