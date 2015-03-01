
package METODE;
import java.util.*;
import static METODE.MathParsing.MathParsing;
import javax.swing.table.DefaultTableModel;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;



public class NewtonRhapson {
    double start, e, Fx, Gx, Hx;
    double xn = 0;
    /*
    public static void main (String args []) {
        Scanner insert = new Scanner (System.in);
        int iterasi; //menunjukan banyaknya pengulangan yang di inginkan
        System.out.println ("Masukan fungsi, f(x) = ");
        String fungsi1 = insert.nextLine();
        System.out.println ("Masukan turunan dari fungsi f(x), f'(x) = ");
        String fungsi2 = insert.nextLine();
        System.out.println ("Masukan banyaknya iterasi yang diinginkan : ");
        iterasi = insert.nextInt();
        System.out.println ("Masukan maksimum  toleransi error : ");
        e = insert.nextDouble();
        System.out.println ("Masukan nilai awal pendekatan x0 : ");
        start = insert.nextDouble();
    }*/
      public NewtonRhapson(String fungsi1,String fungsi2,int iterasi,double e,double start){
       
          for (int i=0 ; i < iterasi ; i++) {
            Fx = MathParsing (fungsi1, start);
            Gx = MathParsing (fungsi2, start);
            try{
                double test=Fx/Gx;
            }
            catch(ArithmeticException c){
                METODE.gui.alertNR.setText("turunan fungsi f(x) tidak terdefinisi di x ="+start);
                break;
            }
            
            xn = ((start*Gx)-Fx)/Gx;
                
            Hx = MathParsing (fungsi1, xn);
            DefaultTableModel model;
            model = (DefaultTableModel) METODE.gui.tProsesNR.getModel();
                 model.addRow(new Object[]
                        {
                                i+1, start, xn,Fx, Hx
                        });
                 
            if (Math.abs(Hx)<e) {
                break;
            } else { 
                start = xn;
            }

        }
            //System.out.println ("Solusi x : "+xn);
    }
      public String hasil_NR() {
            return String.valueOf(xn);
            }
}
