package metode ;


public class Biseksi {

public static void main(String[] args) {
Scanner s =new Scanner(System.in);
Double xr=0.0, Fxr=0.0;
System.out.printf("Masukkan Batas Bawah : ");
Double batasbawah = s.nextDouble();
System.out.printf("Masukan Batas Atas :");
Double batasatas = s.nextDouble();
System.out.printf("Maximum Iterasi : ");
Integer Maximumiterasi = s.nextInt();
System.out.printf("Galat : ");
Double galat = s.nextDouble();

Double fa = fungsi(batasbawah);
Double fb = fungsi(batasatas);

if (fa*fb>0)
{
System.out.printf("Tidak ada akar diantara â€œ+batasatas+â€� dan "+batasbawah);
}
else
{
int kondisi = 1;
int iterasi = 0;
while(kondisi == 1)
{
iterasi++;
xr = (batasatas+batasbawah)/2;
Fxr = fungsi(xr);
if((Math.abs(Fxr)<galat)|(iterasi>=Maximumiterasi))
{
kondisi=0;
}

{
if(fa*Fxr>0)
{
batasbawah = xr;
fa = Fxr;
}
else
{
batasatas=xr;
fb = Fxr;
}
}
}
System.out.println("Lokasi Akar di "+xr+" dengan "+Fxr);
}

}
static double fungsi (double z)
{
return Math.exp(-z)-z;
}
}