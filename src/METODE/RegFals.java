package METODE;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import static METODE.MathParsing.MathParsing;
import javax.swing.JOptionPane;

public class RegFals {
        double Fa,Fb,Fx,a,b,x = 0;
        //Scanner input=new Scanner(System.in);
        //System.out.print("Masukkan f(x): "); //1. User menginput f(x)
        //String fungsi=input.nextLine();
        //System.out.print("Masukkan maksimum toleransi error : "); //2. User menginput e
        //double e=input.nextDouble();
        //System.out.print("Masukkan maksimum iterasi (n): "); //3. User menginput n
        //int n=input.nextInt();
        public RegFals(String fungsi,double e,int n){
        while(true){
            a=Double.parseDouble(JOptionPane.showInputDialog("Masukkan batas bawah (a) : "));
            b=Double.parseDouble(JOptionPane.showInputDialog("Masukkan batas atas (b): "));
            if(MathParsing(fungsi,a)*MathParsing(fungsi,b)<=0) break; //6. Jika Fa*Fb<=0 berarti a<=solusi<=b
            if(MathParsing(fungsi,a)*MathParsing(fungsi,b)>0) 
                JOptionPane.showMessageDialog(null, "Pilih interval a<solusi<b");
        }
            Fa=MathParsing(fungsi,a);
            Fb=MathParsing(fungsi,b);
                for(int i=0;i<n;i++){
                    /*if (i==0){
                        System.out.println("| i |a\t\t\t|b\t\t|x\t\t|Fa\t\t|Fb\t\t|Fx\t\t|");
                    }*/
                        x=(a*Fb-b*Fa)/(Fb-Fa);
                        Fx=MathParsing(fungsi,x);
            /*System.out.print("| "+(i+1)+" | ");
            System.out.print(String.format("%.8f", a)+"\t|");
            System.out.print(String.format("%.8f", b)+"\t|");
            System.out.print(String.format("%.8f", x)+"\t|");
            System.out.print(String.format("%.8f", Fa)+"\t|");
            System.out.print(String.format("%.8f", Fb)+"\t|");
            System.out.print(String.format("%.8f", Fx)+"\t|");
            System.out.println();*/
                        DefaultTableModel model;
            model = (DefaultTableModel) METODE.gui.tProsesRF.getModel();
                 model.addRow(new Object[]
                        {
                                i+1, a, b,x, Fa, Fb, Fx
                        });
                 
                   if(Math.abs(Fx)<e) break;
                         if(Fx*Fa<0){
                            b=x;
                            Fb=Fx;
                        } else{
                            a=x;
                            Fa=Fx;
                        }
                    }
        
                //System.out.println("Solusi x: "+x);
        }
        
    public String hasil_RF() {
        return String.valueOf(x);
    }}