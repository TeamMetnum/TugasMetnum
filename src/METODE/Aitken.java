package metode;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;

/*
Metode Aitken :: 080989999@instan---------------------
*/        

public class Aitken {
    String aitken_ops; //outputprocess
    double[] nilaiXn;
    double Xn2;
    boolean aitken_status; // aitken apakah bukan,
    int max_iterasi; // maximum iterasi
    
    // Aitken(S
    public Aitken(String f, int n, double Xn, double tlrn) {
        StringBuilder aitken_op = new StringBuilder();
        nilaiXn = new double[n];
        Xn2 = 0;
        aitken_status = true;
        max_iterasi = n;
        
        aitken_op.append("Xn\t\tF(Xn)\t\tXn-F(Xn)\n");
        // Xn       F(Xn)       Xn-F(Xn)
        
        for( int i=0; i<n; i++) {
            nilaiXn[i] = Xn;
            double selisihMutlak;
            
            if ( Xn - MathParsing(f,Xn) >= 0 )
                selisihMutlak = Xn - MathParsing(f,Xn);
            else 
                selisihMutlak = ( Xn - MathParsing(f,Xn) ) * (-1);
            
            Xn = MathParsing(f,Xn);
            
            aitken_op.append( nilaiXn[i] + "\t\t" + Xn + "\t\t" + selisihMutlak + "\n");
            // nilaiXn[i]       Xn         selisihmutlak
            
            if (selisihMutlak <= tlrn){
                Xn2 = nilaiXn[i]; 
                aitken_status = false; 
                break;
            }
        }
        
        aitken_ops = aitken_op.toString();
     }
    
    // output akan menghasilkan nilai aitken output
    double Output() {
        if (aitken_status){
            double deltaX0 = nilaiXn[max_iterasi-2] - nilaiXn[max_iterasi-3];
            double deltaX1 = nilaiXn[max_iterasi-1] - nilaiXn[max_iterasi-2];
            double delta2X0 = deltaX1 - deltaX0;
            Xn2 = nilaiXn[max_iterasi-1] - ((deltaX1*deltaX1)/delta2X0);
            
            // Dengan menggunakan metode aiken diperoleh solusi berikut
            return Xn2;
        }
        else{
            // Tanpa menggunakan metode aiken diperoleh solusi berikut
            return Xn2;
        }
    }
    
    // output prosesnya disini sebagai string
    String OutputProcess() {
        return aitken_ops;
    }
    
    // bawaan sononya
    double MathParsing(String func, double var) {
        Expression e = new ExpressionBuilder(func)
        .variables("x")
        .build()
        .setVariable("x", var);
        return e.evaluate();
    }
}

// nah ini test nyay > cek sini untuk liat caranya
class AitkenTest {
    public static void main (String[]args) {
        Scanner input=new Scanner(System.in);
        System.out.print("Masukkan f(x): "); //1. User menginput f(x)
        String fungsi=input.nextLine();
        System.out.print("Masukkan batas toleransi\t:");
        double toleransi = input.nextDouble();
        System.out.print("Masukkan maksimum iterasi (n): "); //3. User menginput n
        int n=input.nextInt();
        System.out.print("Masukkan nilai X0\t\t:");
        double X0 = input.nextDouble();
        
        Aitken hasilAitken = new Aitken(fungsi ,n, X0, toleransi);
        //System.out.println(hasilAitken.Output());
        
    }
}

/*

catatan: (akhlUl ganteng)
ttg output prosesnya spertinya perlu disesuaikan karna hasilnya agak aneh, 
terlalu gk kece kalo diliat sama kitanya (pengguna)
penulisannya harus bener pas nulis f(x) nya. kalo nulis f(x) nya x^2-2 akan 
berbeda kalo nulisnya (x^2)-2


*/
