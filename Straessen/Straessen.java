/* Multiplicacion de matrices de Straessen :
 Dadas A * B se obtiene la matriz C de la siguiente manera.
 A y B son de tamaño 2^n.

       C   =          A       *   B
|c0    c01|      |a00 a01  |     | b00  b01  |
|	      |  =   |         | *   |           |
|c10  c11 |      | a10  a11|     | b10   b11 |

C es igual a  =| m1+m4-m5+m7    m3+m5             |
		       |                                  |
		       |        m2+m4        m1+m3-m2+m6  |

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
public class Straessen{

	public static void main(String[] args){


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
			result= a[0][0]* b[0][0];
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
			//cargar As
			dividir(a,a00,0,0); //el primer cuadrado superior izquierdo
			dividir(a,a01,0,n/2);// el cuadrado superior derecho
			dividir(a,a10,n/2,0);//cuadrado inferior izquierdo
			dividir(a,a11,n/2,n/2);//cuadrado inferior derecho
			//cargar Bs
			dividir(b,b00,0,0);
			dividir(b,b01,0,n/2);
			dividir(b,b10,n/2,0);
			dividir(b,b11,n/2,n/2);
		}
		return result;			
	}
	//Dividir copia los elementos desde un inicio fila y end columna hasta a.length que es igual a (array.lenght)/2.
	public static void dividir (int[][] array, int[][] a,int inicio, int fin){ //Dos matrices y copiar de array a "a" desde inicio a fin.
		for (int i=0, fila=inicio; i< a.length ; i++,begin++){
			for(int j=0, col=fin ; j<a.length;j++,end++){
				a[i][j]=array[fila][col];
			}
		} 	
	}		

}