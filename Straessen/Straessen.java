/* Multiplicacion de matrices de Straessen :
 Dadas A * B se obtiene la matriz C de la siguiente manera.
 A y B son de tamaño 2^n.

       C   =          A       *   B
|c0    c01|      |a00 a01  |     | b00  b01  |
|	      |  =   |         | *   |           |
|c10  c11 |      | a10  a11|     | b10   b11 |

C es igual a  =| c00=m1+m4-m5+m7    c01=m3+m5      |
		       |                                   |
		       |  c10=  m2+m4     c11=m1+m3-m2+m6  |

Donde:
m 1 = (a 00 + a 11 ) * (b 00 + b 11 ),
m 2 = (a 10 + a 11 ) *b 00 ,
m 3 = a 00 * (b 01 − b 11 ),
m 4 = a 11 * (b 10 − b 00 ),
m 5 = (a 00 + a 01 ) * b 11 ,
m 6 = (a 10 − a 00 ) ∗ (b 00 + b 01 ),
m 7 = (a 01 − a 11 ) ∗ (b 10 + b 11 )


﻿tiempo de ejecución es de T(n) = 7·T(n/2) + O(n^2), es decir, de T(n) = O(n^log27) = O(n^2.81). 

*/
import java.util.*;

public class Straessen{

	public static void main(String[] args){
		//Generar dos matrices de tamaño 2^n
		Random r= new Random(); 
		int n=8;//tamaño de la forma n^2
		int[][] a= new int[n][n];
		int[][] b= new int[n][n];
		for(int i=0;i <a.length;i++){
			for(int j=0;j<a.length;j++){
				a[i][j]= r.nextInt(31);	
				b[i][j]= r.nextInt(31);
			}
		}
		String str=toString(a);
		String str1=toString(b);
		System.out.println("Matriz A");
		System.out.println(str);
		System.out.println("");
		System.out.println("Matriz B");
		System.out.println(str1);
		System.out.println("");

		//aplicar straesen a esas dos matrices,
		int[][] result= new int[n][n]; 
		result= straessen(a,b); 
		//resultado aplicar toString para mostrar matriz resultante
		str= toString(result);
		System.out.println("Resultado");
		System.out.println(str);
	}

	public static int[][] straessen (int[][] a,int[][] b){
		
		if(a ==null || b== null){
			throw new IllegalArgumentException("straessen.Null Array");
		}
		//ver el caso si ambas matrices no son de la forma 2^n.
		//-----------------------------------------------------		
		//tomo el tamaño de cualquiera de las dos matrices ya que son 2^n ambas.
		int n= a.length;  
		int[][] result=new int[n][n]; //matriz de resultado de tamaño n.
		if (n==1){ //Si ambas matrices tienen solo un elemento, realizo la operacion directa
			result[0][0]= (a[0][0])* (b[0][0]);
		}
		else{
			//Crear las matrices : a00,a01,a10,a11 y respectivamente para b : b00,b01,b10,b11
			//de tamaño n/2
			//para las A
			int[][] a00 = new int[n/2][n/2];
			int[][] a01 = new int[n/2][n/2];
			int[][] a10 = new int[n/2][n/2];
			int[][] a11 = new int[n/2][n/2];
			//Para las B
			int[][] b00 = new int[n/2][n/2];
			int[][] b01 = new int[n/2][n/2];
			int[][] b10 = new int[n/2][n/2];
			int[][] b11 = new int[n/2][n/2];
			
			//Dividir = Cargar cada matriz con su correspondiente de la matriz original		
			//cargar A's
			dividir(a,a00,0,0); //el primer cuadrado superior izquierdo
			dividir(a,a01,0,n/2);// el cuadrado superior derecho
			dividir(a,a10,n/2,0);//cuadrado inferior izquierdo
			dividir(a,a11,n/2,n/2);//cuadrado inferior derecho
			//cargar B's
			dividir(b,b00,0,0);
			dividir(b,b01,0,n/2);
			dividir(b,b10,n/2,0);
			dividir(b,b11,n/2,n/2);

			//Conquistar : 
			//crear los resultados m1,m2,m3,m4,m5,m6,m7 y hacer operaciones de sumar y restar matrices cuadradas
			
			//lo recursivo en este caso va a ser la llamada nuevamente a "straessen"
			//que va a hacer la multiplicacion.
			int[][] m1= straessen( suma(a00,a11), suma(b00,b11) );
			int[][] m2= straessen ( suma(a10,a11), b00 );
			int[][] m3= straessen (a00, resta(b01,b11) );
			int[][] m4= straessen (a11, resta(b10,b00) );
			int[][] m5= straessen (suma(a00,a01), b11 );
			int[][] m6= straessen (resta(a10,a00),suma(b10,b11));
			int[][] m7= straessen (resta(a01,a11),resta(b10,b11));
			
			//copiar a cada C00,C01,C10,C11 su correspondiente resultado
			int[][] c00=  resta(suma(m1,m4),suma(m5,m7));
			int[][] c01= suma(m3,m5);
			int[][] c10= suma(m2,m4);
			int[][] c11= resta(suma(m1,m3),suma(m2,m6));
			
			//Combinar:
			//Volcar a result = C
			copiar(result,c00,0,0);
			copiar(result,c01,0,n/2);
			copiar(result,c10,n/2,0);
			copiar(result,c11,n/2,n/2);
		}
		return result;			
	}
	//Dividir copia los elementos desde un inicio fila y end columna hasta a.length que es igual a (array.length)/2.
	public static void dividir (int[][] array, int[][] a,int inicio, int fin){ //Dos matrices y copiar de array a "a" desde inicio a fin.
		for (int i=0, fila=inicio; i< a.length ; i++,fila++){
			for(int j=0, col=fin ; j<a.length;j++,col++){
				a[i][j]=array[fila][col];
			}
		} 	
	}
	//Suma los elementos de cada matriz n^2
	public static int[][] suma(int[][] a,int[][] b) {
		int n= a.length; 
		int[][] result= new int[n][n];
		for (int i=0;i<n;i++ ){
			for (int j=0; j<n; j++ ){
				result[i][j]=a[i][j]+b[i][j];	
			}
		}
		return result;
	}

	//Resta los elementos de cada matriz.
	public static int[][] resta(int[][] a,int[][] b) {
		int n = a.length; 
		int[][] result= new int[n][n];
		for (int i=0;i<n;i++ ){
			for (int j=0; j<n; j++ ){
				result[i][j]=a[i][j]-b[i][j];	
			}
		}
		return result;
	}
	//copiar los elementos de una matriz a otra.(igual que el metodo "divide" 
	//pero para que no se mezclen conceptos, se hace otro metodo.)
	public static void copiar(int[][] result, int[][] array, int begin, int end){
		for(int i=0,fila=begin; i<array.length;i++,fila++){
			for(int j=0,col=end; j<array.length;j++,col++){
				result[i][j]=array[fila][col];
			}
		}
	}
	
	public static String toString(int[][] array){
		int n= array.length;
		String str = "\n\t  ------------------------------------ \n\t";
		for(int i=0;i<n;i++ ){
			for(int j=0;j<n;j++ ){
				str= str + " | "+array[i][j];	
			}
			str = str + " | \n\t  ------------------------------------ \n\t";
		}
		return str;
	} 

}
