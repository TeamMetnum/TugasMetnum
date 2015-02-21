package metode;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
public class Secant {
    public static void main(String[] args) {
        double Fa,Fb,Fx,a,b,x = 0;
        Scanner input=new Scanner(System.in);
        System.out.print("Masukkan f(x): "); //1. User menginput f(x)
        String fungsi=input.nextLine();
        System.out.print("Masukkan maksimum toleransi error : "); //2. User menginput e
        double e=input.nextDouble();
        System.out.print("Masukkan maksimum iterasi (n): "); //3. User menginput n
        int n=input.nextInt();
        System.out.print("Masukkan batas bawah (a) : "); //4. User menginput a
        a=input.nextDouble();
        System.out.print("Masukkan batas atas (b): "); //5. User menginput b
        b=input.nextDouble();
                
        for(int i=0;i<n;i++){
            Fa=MathParsing(fungsi,a);
            Fb=MathParsing(fungsi,b);
            x=(a*Fb-b*Fa)/(Fb-Fa);
            Fx=MathParsing(fungsi,x);
            if(Math.abs(Fx)<e){
                break;
            }
            else{
                a = b; b = x;
            }
        }
        System.out.println("Solusi x: "+x);
    }
    public static double MathParsing(String func, double var){
        Expression e = new ExpressionBuilder(func)
        .variables("x","pi","e")
        .build()
        .setVariable("x", var)
        .setVariable("pi", Math.PI)
        .setVariable("e", Math.E);

        return e.evaluate();
    }
}
