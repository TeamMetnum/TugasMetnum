package metode;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import static METODE.MathParsing.MathParsing;
/*
Metode Biseksi :: 60%
*/   

public class Biseksi {
    String biseksi_ops; //outputprosess biseksi
    public double Fa, //fx batas atas
            Fb, //fx batas bawah
            Fx, //fx saat x
            a, //x batas bwh
            b, //x batas ats
            hx, //hasil akhir biseksi
            e, //maksimumm toleransi error
            x = 0;
    int n; // maksimum iterasi
    String fungsi; //fungsi fx
    boolean atengahb; //apakah a =< solusi =< b
    
    public Biseksi(String fungsi_input, double error_input, int maks_input, 
            double a_input, double b_input ) {
        //StringBuilder biseksi_op = new StringBuilder();
        
        String fungsi = fungsi_input;
        double e = error_input;
        n = maks_input;
        a = a_input;
        b = b_input;
        
        Fa = MathParsing(fungsi,a);
        //System.out.println("fa = "+ Fa);
        Fb = MathParsing(fungsi,b);
        
        for (int i=0; i<n; i++) {
            //if(i == 0) {
                //biseksi_op.append("i\t\ta\t\t|b\t\t|x\t\t|Fa\t\t|Fb\t\t|Fx\n");
                // i    a   b   x   F(a)    F(b)    F(x)
            //}
            
            x = (b+a)/2;
            Fx = MathParsing(fungsi,x);
            DefaultTableModel model;
            model = (DefaultTableModel) METODE.gui.tProsesBiseksi.getModel();
                 model.addRow(new Object[]
                        {
                                i, a, b,x, Fa, Fb, Fx
                        });
                 
            if( Math.abs(Fx) < e ) 
                break;
            
            if( Fx * Fa < 0) {
                b = x;
                Fb = Fx;
            } else {
                a = x;
                Fa = Fx;
            }
        
                 
            
        }
        //biseksi_ops = biseksi_op.toString();
        // solusi nya adalah x yang dihasilkan
        hx = x;
       
                
    }
    
    public String hasil_biseksi() {
        return String.valueOf(hx);
    }
    
    public String proses_biseksi() {
        return biseksi_ops;
    }
    
    public void hapusdata() {
        biseksi_ops = "";
        Fa = 0;        Fb = 0;        Fx = 0;        n = 0;
        a = 0;         b = 0;         hx = 0;         e = 0;
        fungsi = "";
        atengahb = false;
    }
    
    public boolean antara_ab() {
        if( MathParsing(fungsi, a) * MathParsing(fungsi,b) <= 0) 
            return true; 
        else return false;
    }
    
}

/*class BiseksiTest {
    public static void main(String[] args) {
        double Fa, Fb, Fx, a, b, x = 0;
        Scanner input = new Scanner(System.in);
            //1. User menginput f(x)
        System.out.print("Masukkan f(x): "); 
        String fungsi = input.nextLine();
            //2. User menginput e
        System.out.print("Masukkan maksimum toleransi error : "); 
        double e = input.nextDouble();
            //3. User menginput n
        System.out.print("Masukkan maksimum iterasi (n): "); 
        int n = input.nextInt();
            //4. User menginput a
        System.out.print("Masukkan batas bawah (a) : "); 
        a = input.nextDouble();
            //5. User menginput b
        System.out.print("Masukkan batas atas (b): "); 
        b = input.nextDouble();
        
        Biseksi bi = new Biseksi(fungsi, e, n, a, b);
        System.out.println("hasil biseksi adalah " + bi.hasil_biseksi());
        System.out.println(bi.proses_biseksi());
    }
}*/

/*
catatan: (karom)
> kodingnya udh gw beautify dlu, biar caem isinya :D
> dah ada update terbaru dari si aji, jadi tinggal edit dikit


*/

