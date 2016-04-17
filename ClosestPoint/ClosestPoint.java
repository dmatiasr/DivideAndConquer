/*
	Clase que implementa los dos puntos mas cercanos de un plano.
 	Por Divide and Conquer:
 		. Ordenar puntos por X
 		. Caso base para 2 o 3 puntos, (fuerza bruta)
 		. Mas de 3 puntos : calculo el minimo de la izquierda y el minimo de la derecha
 		  (comparar ambos y dejar el minimo = Min1)
 		. Tener en cuenta los puntos cercanos a la raya vertical divisoria de la mitad.
 		. Se ordena por eje Y, esta nueva lista.
 		. Obtener los puntos de distancia minima comparando con Min1 obtenido.
 		. Para cada punto del lado izquierdo del borde de la raya vertical, tengo
 		6 posibilidades de encontrar el minimo. (Rectangulo con cada esquina un punto y al medio)
 		(ver teorico). 6n  ---> 0 (n)

	Tiempo de ejecucion O (n log n)
*/
import java.io.*;
import java.awt.Point;


public class ClosestPoint{
	public static void main(String[] args) {
			
	}
	//Fuerza bruta para encontrar dos puntos mas cercanos.
	public static  Point[] bruteForce(Point[] points){
		int n= points.length;
		Point[] resPoint= new Point[1];
		if (n<2){
			throw new IllegalArgumentException("Necesita dos puntos (x,y), por lo menos");
		}
		else{ 
			double d= Double.POSITIVE_INFINITY; //constante infinito
			for(int i=0; i<n;i++){
				for(int j=i+1; j<n;j++){
					double res= distance(p[i],p[j]);				
					if (res<d){//seteo nuevos puntos cercanos
						d=res;
						resPoint[0]=points[i]; //guardo en arreglo auxiliar los puntos
						resPoint[1]=points[j]; //con distancia minima.
					}
				}
			}
		}
		return resPoint;
	}
	public static double distance(Point a, Point b){
		double x= a.getX()-b.getX();
		double y= a.getY()-a.getY();
		return  Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) ;
	}

	//Minimo de dos double.
	public static double min (double a,double b){
		if (a<b){
			return a;
		}
		else{
			return b;
		}	
	}



}