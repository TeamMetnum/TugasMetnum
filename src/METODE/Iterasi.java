package metode;

import java.util.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
        
public class Iterasi {
    StringBuilder iterasi_op = new StringBuilder(""); //output proses sebagai string
    String fx,
            finv;
    double fa,  //fx batas atas
            fb, //fx batas bawah
            a,  //x batas atas
            b,  //x batas bawah
            EP, //hasil akhir iterasi 
                //-->> lebih baik gunakan yang string (hasil_iterasi()) 
                //karena hasilnya kadang tidak bisa didapatkan dalam angka
            e;  //error
    int maks; //jumlah iterasi maksimum
    
    // dipakai di gui
    // dalam iterasi, finv maksudnya adalah ketika fx=0 diubah menjadi x=finv(x)
    public Iterasi(String fx_input, String finv_input, double a_input, double b_input, double error_input, int maks_input) {
        fx = fx_input;
        finv = finv_input;
        a = a_input;
        b = b_input;
        e = error_input;
        maks = maks_input;
        
        fa = hasil(fx,a);
        fb = hasil(fx,b);
    }
    
    // dipakai di gui
    public String hasil_iterasi(int pilih_input) { 
        //pilihannya dibuat terserah mau pakai (batas atas == 1) atau (batas bawah = 2)
        String hasil_sementara = "";
        if (tes(fa,fb) == true){
            int pilih = pilih_input;
            switch (pilih){
                case 1 : 
                    iterasi_op.append("no\t\tx\t\tfinv(x)\n");
                    // no(k)    x   finv(x)
                    EP = cari_iterasi (finv,a,maks,0,e);
                    hasil_sementara += "nilai akar x anda adalah dengan batas yang dipakai " + a + " : " + EP;
                    break;
                case 2 :
                    iterasi_op.append("no\t\tx\t\tfinv(x)\n");
                    // no(k)    x   finv(x)
                    EP = cari_iterasi (finv,b,maks,0,e);
                    hasil_sementara += "nilai akar x anda adalah dengan batas yang dipakai " + b + " : " + EP;
                    break;
            }
        } else {
            hasil_sementara += "maaf batas anda tidak dapat digunakan sebagai dasar iterasi\n";
            if (tes2(fa,fb) == true)
                hasil_sementara += " karena salah satu di antara angka anda tersebut adalah akar persamaan";
        }
        return hasil_sementara;
    }
    
    // dipakai di gui
    public String proses_iterasi() {
        String n = iterasi_op.toString();
        iterasi_op.setLength(0); // set length of buffer to 0
        iterasi_op.trimToSize(); // trim the underlying buffer
        return n;
        
    }
    
    // dipakai di gui
    public void hapusdata() {
        iterasi_op.setLength(0); // set length of buffer to 0
        iterasi_op.trimToSize(); // trim the underlying buffer
        fx = "";    finv = ""; 
        fa = 0;     fb = 0;
        a = 0;      b = 0;
        EP = 0;     e = 0;
        maks = 0;
    }
    
    public double cari_iterasi(String inv, double batas, int ulang, int k, double galat) {
        k++;
        double hasilakhir = 0;
        double nilai = hasil (inv,batas);
        iterasi_op.append(k + "\t\t" + batas + "\t\t" + nilai + "\n");
        
        if ( k == ulang || Math.abs(batas-nilai) <= galat) 
            hasilakhir = nilai;
        else {
             cari_iterasi(inv, nilai, ulang, k, galat);
        }
        
        return hasilakhir;
    }
      
    public double hasil(String func, double var) { // <-- ini adalah MathParsingnya
        Expression e = new ExpressionBuilder(func)
        .variables("x")
        .build()
        .setVariable("x", var);
        return e.evaluate();
    }
   
    public boolean tes(double atas, double bawah) {
        return atas*bawah < 0;
    }
    
    public boolean tes2(double up, double down) {
        return up*down == 0;
    }   
}

class IterasiTest {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        double fa,fb,a,b,EP,e=0;
        int maks;
        
        System.out.println("===============================");
        System.out.println (" MODULE ITERASI ");
        System.out.println("===============================");
        System.out.println ("silahkan masukan fungsi anda :");
        String fx=read.nextLine();
        System.out.println ("silahkan masukan fungsi g(x) anda yang merupakan x pada f(x)=0 :");
        String finv=read.nextLine();
        System.out.println ("batas atas anda (a) :");
        a = read.nextDouble ();
        System.out.println ("batas bawah anda (b):");
        b = read.nextDouble ();
        System.out.println("masukan error yang anda mau");
        e = read.nextDouble();
        System.out.println ("masukan jumlah maksimal iterasi yang anda mau:");
        maks = read.nextInt ();
                
        Iterasi iter = new Iterasi(fx, finv, a, b, e, maks);
        System.out.println(iter.hasil_iterasi(1));
        System.out.println(iter.proses_iterasi());
        
        System.out.println(iter.hasil_iterasi(2));
        System.out.println(iter.proses_iterasi());
        
        iter.hapusdata();
    }
}


/*

catatan: (akhlUl ganteng)
> ttg output prosesnya spertinya perlu disesuaikan karna hasilnya agak aneh, 
terlalu gk kece kalo diliat sama kitanya (pengguna)
> penulisannya harus bener pas nulis f(x) nya. kalo nulis f(x) nya x^2-2 akan 
berbeda kalo nulisnya (x^2)-2
> tolong diperhatikan ya type nya (hehehe),
> masih ada bugna !!!!!, tolong diingetin lagi ya
> list method yang gw kira bakal dipake di gui nya :
        (constructornya)
        .hasil_iterasi()
        .proses_iterasi()
        .hapusdata()
*/