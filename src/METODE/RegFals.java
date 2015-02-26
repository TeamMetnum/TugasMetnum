package metode;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
public class RegFals {
    public static void main(String[] args) {
        double Fa,Fb,Fx,a,b,x = 0;
        Scanner input=new Scanner(System.in);
        System.out.print("Masukkan f(x): "); //1. User menginput f(x)
        String fungsi=input.nextLine();
        System.out.print("Masukkan maksimum toleransi error : "); //2. User menginput e
        double e=input.nextDouble();
        System.out.print("Masukkan maksimum iterasi (n): "); //3. User menginput n
        int n=input.nextInt();
        while(true){
            System.out.print("Masukkan batas bawah (a) : "); //4. User menginput a
            a=input.nextDouble();
            System.out.print("Masukkan batas atas (b): "); //5. User menginput b
            b=input.nextDouble();
            if(MathParsing(fungsi,a)*MathParsing(fungsi,b)<=0) break; //6. Jika Fa*Fb<=0 berarti a<=solusi<=b
            if(MathParsing(fungsi,a)*MathParsing(fungsi,b)>0) System.out.println("Pilih interval a<solusi<b");
        }
        Fa=MathParsing(fungsi,a);
        Fb=MathParsing(fungsi,b);
        for(int i=0;i<n;i++){
            if (i==0){
                System.out.println("| i |a\t\t\t|b\t\t|x\t\t|Fa\t\t|Fb\t\t|Fx\t\t|");
            }
            x=(a*Fb-b*Fa)/(Fb-Fa);
            Fx=MathParsing(fungsi,x);
            System.out.print("| "+(i+1)+" | ");
            System.out.print(String.format("%.8f", a)+"\t|");
            System.out.print(String.format("%.8f", b)+"\t|");
            System.out.print(String.format("%.8f", x)+"\t|");
            System.out.print(String.format("%.8f", Fa)+"\t|");
            System.out.print(String.format("%.8f", Fb)+"\t|");
            System.out.print(String.format("%.8f", Fx)+"\t|");
            System.out.println();
            if(Math.abs(Fx)<e) break;
            if(Fx*Fa<0){
                b=x;
                Fb=Fx;
            } else{
                a=x;
                Fa=Fx;
            }
        }
        System.out.println("Solusi x: "+x);
    }
   public static double MathParsing(String func, double var){
        Expression a = new ExpressionBuilder(func)
        .variables("x","pi", "e")
        .build()
        .setVariable("x", var)
        .setVariable("pi", Math.PI)
        .setVariable("e", Math.E);
        return a.evaluate();
    }
}