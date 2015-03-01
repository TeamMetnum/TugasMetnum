package METODE;
import java.util.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import javax.swing.table.DefaultTableModel;
import static METODE.MathParsing.MathParsing;
public class Secant {
    String secant_ops;
        double akar;
    /*public static void main(String[] args){
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
    }*/
        
    public Secant(String fx, double er, int n, double a, double b){
        StringBuilder secant_op = new StringBuilder();
        akar =0;
        double fa, fb, fc, c=0;
        System.out.println();
        //secant_op.append("Proses pencarian akar:\n");
        //secant_op.append("a\t\t\tb\t\t\tfa\t\t\tfb\t\t\tc\t\t\tfc\n");
        for(int i=0; i<n ; i++){
            fa = MathParsing(fx, a);
            fb = MathParsing(fx, b);
            c = (a*fb - b*fa)/(fb-fa);
            fc = MathParsing(fx, c);
            //secant_op.append(a+"\t\t\t" +b+"\t\t\t"+fa+"\t\t\t"+fb+"\t\t\t"+c+"\t\t\t"+fc+"\n");
            DefaultTableModel model;
            model = (DefaultTableModel) METODE.gui.tProsesSecant.getModel();
                 model.addRow(new Object[]
                        {
                                a, b, fa,fb, c,fc
                        });
                 
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
    
    public String hasil_Secant(){
         return String.valueOf(akar);
    }
    
    
}

