
package metode;
import java.util.Scanner;
public class NewtonRhapson {
	public static void main (String []args) {
		Scanner Edhi = new Scanner(System.in);
		System.out.println ("Pilih jenis fungsi:\na. ax + b\nb. ax^2 + bx + c\nc. ax^3 + bx^2 + cx + d");
		double[] A = new double[4];
		double[] dA = new double[3];
		System.out.print ("Pilihan = ");
		String pilihan = Edhi.nextLine();
		int Suku = 0;
		if (pilihan.equals("a")) Suku = 2;
		if (pilihan.equals("b")) Suku = 3;
		if (pilihan.equals("c")) Suku = 4;
		for (int i = 0; i < Suku; i++) {
			System.out.print ("koefisien-" + (i + 1) + " = ");
			A[i] = Edhi.nextDouble();
		}
		dA = Turunan(A, Suku);
		if (Suku == 2) {
			System.out.println ("f(x) = " + A[0] + "x + " + A[1]);
			System.out.println ("f'(x) = " + dA[0]);
		}
		if (Suku == 3) {
			System.out.println ("f(x) = " + A[0] + "x^2 + " + A[1] + "x +" + A[2]);
			System.out.println ("f'(x) = " + dA[0] + "x + " + dA[1]);
		}
		if (Suku == 4) {
			System.out.println ("f(x) = " + A[0] + "x^3 + " + A[1] + "x^2 + " + A[2] + "x + " + A[3]);
			System.out.println ("f'(x) = " + dA[0] + "x^2 + " + dA[1] + "x + " + A[2]);
		}
		System.out.print ("\nIterasi maximum = ");
		int Iterasi = Edhi.nextInt();
		System.out.print ("Error = ");
		double Galat = Edhi.nextDouble();
		System.out.print ("Nilai X awal = ");
		double x_0 = Edhi.nextDouble();
		double Akar = Square(A, dA, Galat, Iterasi, x_0, Suku);
		System.out.println ("\n\nAkarnya adalah : " + Akar);
	}
	
	public static double[] Turunan(double[] A, int Suku) {
		double[] dA = new double[3];
		if (Suku == 2) {
			dA[0] = A[0];
		}
		if (Suku == 3) {
			dA[0] = 2 * A[0];
			dA[1] = A[1];
		}
		if (Suku == 4) {
			dA[0] = 3 * A[0];
			dA[1] = 2 * A[1];
			dA[2] = A[2];
		}
		return dA;
	}
	
	public static double Square(double[] A, double[] dA, double Galat, int Iterasi, double x_0, int Suku) {
		double x_1 = 0, fx = 0, dfx = 0, W = 0, GI = 0;
		boolean Ketersediaan = false;
		System.out.println ("i\tXn\tf(Xn)\tf'(Xn)\tf(x)/f'(x)\tXn+1");
		if (Suku == 2) {
			for (int i = 0; i <= Iterasi; i++) {
				if (i != 0) x_0 = x_1;
				fx = (A[0] * x_0) + A[1];
				dfx = dA[0];
				W = fx / dfx;
				x_1 = x_0 - W;
				if (x_1 < 0) x_1 = x_1 * (-1);
				System.out.println (i + "\t" + x_0 + "\t" + fx + "\t" + dfx + "\t" + W + "\t\t" + x_1);
				GI = x_1 - x_0;
				if (GI < 0) GI = GI * (-1);
				if (GI <= Galat) break;
				if (i == Iterasi) Ketersediaan = true;
			}
		}
		if (Suku == 3) {
			for (int i = 0; i <= Iterasi; i++) {
				if (i != 0) x_0 = x_1;
				fx = (A[0] * (x_0 * x_0)) + (A[1] * x_0) + A[2];
				dfx = (dA[0] * x_0) + dA[1];
				W = fx / dfx;
				x_1 = x_0 - W;
				if (x_1 < 0) x_1 = x_1 * (-1);
				System.out.println (i + "\t" + x_0 + "\t" + fx + "\t" + dfx + "\t" + W + "\t\t" + x_1);
				GI = x_1 - x_0;
				if (GI < 0) GI = GI * (-1);
				if (GI <= Galat) break;
				if (i == Iterasi) Ketersediaan = true;
			}
		}
		if (Suku == 4) {
			for (int i = 0; i <= Iterasi; i++) {
				if (i != 0) x_0 = x_1;
				fx = (A[0] * (x_0 * x_0 * x_0)) + (A[1] * (x_0 * x_0)) + (A[2] * x_0) + A[3];
				dfx = (dA[0] * (x_0 * x_0)) + (dA[1] * x_0) + dA[2];
				W = fx / dfx;
				x_1 = x_0 - W;
				if (x_1 < 0) x_1 = x_1 * (-1);
				System.out.println (i + "\t" + x_0 + "\t" + fx + "\t" + dfx + "\t" + W + "\t\t" + x_1);
				GI = x_1 - x_0;
				if (GI < 0) GI = GI * (-1);
				if (GI <= Galat) break;
				if (i == Iterasi) Ketersediaan = true;
			}
		}
		if (Ketersediaan) System.out.println ("\n\n\nTidak ada/belum ditemukan akar. Akar dibawah ini tidaklah pasti");
		return x_1;
	}
}