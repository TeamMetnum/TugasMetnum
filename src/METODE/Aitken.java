package metode;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
public class Aitken {
    public static void main (String[]args){
        Scanner input=new Scanner(System.in);
        System.out.print("Masukkan f(x): "); //1. User menginput f(x)
        String fungsi=input.nextLine();
        System.out.print("Masukkan batas toleransi\t:");
        double toleransi = input.nextDouble();
        System.out.print("Masukkan maksimum iterasi (n): "); //3. User menginput n
        int n=input.nextInt();
        System.out.print("Masukkan nilai X0\t\t:");
        double X0 = input.nextDouble();
        Aitken(fungsi ,n, X0, toleransi);
    }
    
    public static void Aitken(String f, int n, double Xn, double tlrn)
    {
        double[]nilaiXn = new double[n];
        double Xn2=0;
        boolean aitken = true;
        
        System.out.println("Xn\t\tF(Xn)\t\tXn-F(Xn)");
        for(int i=0; i<n;i++){
            nilaiXn[i] = Xn;
            double selisihMutlak;
            
            if (Xn-MathParsing(f,Xn)>=0)selisihMutlak =Xn-MathParsing(f,Xn);
            else selisihMutlak =(Xn-MathParsing(f,Xn))*-1;
            Xn = MathParsing(f,Xn);
            
            System.out.println(nilaiXn[i]+"\t\t"+Xn+"\t\t"+selisihMutlak);
            if (selisihMutlak<= tlrn){
                Xn2 = nilaiXn[i]; 
                aitken = false; break;
            }
        }
        System.out.println();
        
        if (aitken){
            double deltaX0= nilaiXn[n-2]-nilaiXn[n-3];
            double deltaX1= nilaiXn[n-1]-nilaiXn[n-2];
            double delta2X0= deltaX1 - deltaX0;
            Xn2 = nilaiXn[n-1] - ((deltaX1*deltaX1)/delta2X0);
            
            System.out.println("Dengan metode aiken diperoleh solusi ="+Xn2);
        }
        else{
            System.out.println("Tanpa menggunakan metode aiken diperoleh solusi ="+Xn2);
        }
    
    }
    public static double MathParsing(String func, double var){
        Expression e = new ExpressionBuilder(func)
        .variables("x")
        .build()
        .setVariable("x", var);
        return e.evaluate();
    }
}
