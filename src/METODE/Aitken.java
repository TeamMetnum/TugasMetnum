package METODE;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
import static METODE.MathParsing.MathParsing;
import javax.swing.table.DefaultTableModel;

/*
Metode Aitken :: 080989999@instan---------------------
*/        

public class Aitken {
    String aitken_ops; //outputprocess
    public double[] nilaiXn;
    public double Xn2;
    public boolean aitken_status; // aitken apakah bukan,
    public int max_iterasi; // maximum iterasi
    
    // Aitken(S
    public Aitken(String f, int n, double Xn, double tlrn) {
        StringBuilder aitken_op = new StringBuilder();
        nilaiXn = new double[n];
        Xn2 = 0;
        aitken_status = true;
        max_iterasi = n;
        
        //aitken_op.append("Xn\t\tF(Xn)\t\tXn-F(Xn)\n");
        // Xn       F(Xn)       Xn-F(Xn)
        
        for( int i=0; i<n; i++) {
            nilaiXn[i] = Xn;
            double selisihMutlak;
            
            if ( Xn - MathParsing(f,Xn) >= 0 )
                selisihMutlak = Xn - MathParsing(f,Xn);
            else 
                selisihMutlak = ( Xn - MathParsing(f,Xn) ) * (-1);
            
            Xn = MathParsing(f,Xn);
            
            //aitken_op.append( nilaiXn[i] + "\t\t" + Xn + "\t\t" + selisihMutlak + "\n");
            // nilaiXn[i]       Xn         selisihmutlak
            DefaultTableModel model;
            model = (DefaultTableModel) METODE.gui.tProsesAitken.getModel();
                 model.addRow(new Object[]
                        {
                                nilaiXn[i],Xn, selisihMutlak
                        });
                 
            if (selisihMutlak <= tlrn){
                Xn2 = nilaiXn[i]; 
                aitken_status = false; 
                break;
            }
        }
        
        aitken_ops = aitken_op.toString();
     }
    
    public double hasil_aitken() {
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
    public String proses_aitken() {
        return aitken_ops;
        
    }
    public String solusi_aitken() {
        return String.valueOf(hasil_aitken());
    }
    
}

// nah ini test nyay > cek sini untuk liat caranya
/*class AitkenTest {
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
        
        Aitken ai = new Aitken(fungsi ,n, X0, toleransi);
        System.out.println(ai.proses_aitken());
        
    }
}*/

/*

catatan: (akhlUl ganteng)
> ttg output prosesnya spertinya perlu disesuaikan karna hasilnya agak aneh, 
terlalu gk kece kalo diliat sama kitanya (pengguna)
> penulisannya harus bener pas nulis f(x) nya. kalo nulis f(x) nya x^2-2 akan 
berbeda kalo nulisnya (x^2)-2
> ada yang gw ubah namanya, yang pasti itu metodenya ada :
        (constructornya)
        .hasil_aitken()
        .proses_aitken()
        .hapusdata()

*/
