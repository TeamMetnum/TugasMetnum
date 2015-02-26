package metode;
import java.util.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
public class Secant {
    public static void main(String[] args){
        Scanner data = new Scanner(System.in);
        System.out.print("Masukkan fungsi: ");
        String fungsi = data.nextLine();
        System.out.print("Toleransi error: ");
        double error = data.nextDouble();
        System.out.print("Maksimum iterasi: ");
        int iterasi = data.nextInt();
        System.out.print("Batas bawah: ");
        double batasbawah = data.nextDouble();
        System.out.print("Batas atas: ");
        double batasatas = data.nextDouble();
        SecantM hasilsecant = new SecantM(fungsi, error, iterasi,batasatas, batasbawah);
        System.out.println(hasilsecant.OutputProcess());
        System.out.println(hasilsecant.Output());
    }
}

class SecantM{
        String secant_ops;
        double akar;
        
    SecantM(String fx, double er, int n, double a, double b){
        StringBuilder secant_op = new StringBuilder();
        akar =0;
        double fa, fb, fc, c=0;
        System.out.println();
        secant_op.append("Proses pencarian akar:\n");
        secant_op.append("a\t\t\tb\t\t\tfa\t\t\tfb\t\t\tc\t\t\tfc\n");
        for(int i=0; i<n ; i++){
            fa = Fy(fx, a);
            fb = Fy(fx, b);
            c = (a*fb - b*fa)/(fb-fa);
            fc = Fy(fx, c);
            secant_op.append(a+"\t\t\t" +b+"\t\t\t"+fa+"\t\t\t"+fb+"\t\t\t"+c+"\t\t\t"+fc+"\n");
            if(Math.abs(fc)<er){
                break;
            }
            else {
                a = b; b = c;
            } 
            }
        akar = c;
        secant_ops = secant_op.toString();
    }

    
    String OutputProcess() {
        return secant_ops;
    }
    
    double Output(){
        return akar;
    }
    
    public double Fy(String function, double var){
        Expression e = new ExpressionBuilder(function)
        .variables("x", "e", "pi")
        .build()
        .setVariable("x", var)
        .setVariable("e", Math.E)
        .setVariable("pi", Math.PI);
        return e.evaluate();
    }
}

