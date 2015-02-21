package metode;
import java.util.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
        
public class Iterasi {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner read=new Scanner (System.in);
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
        fa= hasil (fx,a);
        fb= hasil (fx,b);
        System.out.println("hasil fungsi dari batas bawah anda(f("+a+"))  :"+fa);    
        System.out.println("hasil fungsi dari batas bawah anda(f("+b+"))  :"+fb);
        
        if (tes(fa,fb)== true){
            System.out.println ("batas anda dapat di gunakan sebagai dasar iterasi");
            System.out.println ("lanjutkan? 1.yes 2.NO");
                    int para = read.nextInt();
                    if (para == 1){
                        //finv = inverse (fx);
                        //System.out.println ("fungsi inverse anda:\n" +finv);
                        //tolong banget benerin fungsi inversenya, kalau ga bisa juga lama lama gw suruh user yang masukin
                        System.out.println ("batas mana yang mau anda gunakan?\n1.a (batas atas) 2.b (batas bawah)");
                        int pilih = read.nextInt();
                        switch (pilih){
                            case 1 : 
                                EP =iterasi (finv,a,maks,0,e);
                                System.out.println("nilai akar x anda adalah :" +EP);
                                break;
                            case 2 :
                                EP =iterasi (finv,b,maks,0,e);
                                System.out.println("nilai akar x anda adalah :" +EP);
                                break;
                                     }
                    }
                    else{
                        System.out.println ("terimakasih");
                    }
        }
        else {
        System.out.print("maaf batas anda tidak dapat digunakan sebagai dasar iterasi");
        if (tes2(fa,fb)==true)
                System.out.println(" karena salah satu di antara angka anda tersebut adalah akar persamaan");
            }
    }
    
    public static double hasil(String func, double var){
        Expression e = new ExpressionBuilder(func)
        .variables("x")
        .build()
        .setVariable("x", var);
        return e.evaluate();
    }
   
    public static boolean tes (double atas, double bawah){
        return atas*bawah<0;
        }
    
    public static boolean tes2 (double up, double down){
        return up*down==0;
        }
    
    public static double iterasi (String inv,double batas,int ulang,int k,double galat){
        k++;
        double HasilAkir = 0;
        double nilai = hasil (inv,batas);
        if (k==ulang|| Math.abs (batas-nilai)>galat) 
            HasilAkir=nilai;
        else{
             iterasi (inv,nilai,ulang,k,galat);}
        return HasilAkir;
        
    }
    
    public static String inverse (String a){
     String c="fuck you";
     return c;
    }
}
