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
import java.util.Arrays;
import java.util.*;

public class ClosestPoint{
	public static void main(String[] args) {
		Point[] points={new Point(1,0),new Point(0,0),new Point(2,2),new Point(4,4),new Point(0,2)};		
		Point[] res= bruteForce(points);
		String str= toString(res);
		System.out.println("Puntos mas cercanos");
		System.out.println(str);
	}
	//Fuerza bruta para encontrar dos puntos mas cercanos.
	/*
		Para n elementos en el peor caso es O(n^2)
	*/
	public static  Point[] bruteForce(Point[] points){
		int n= points.length;
		Point[] resPoint= new Point[2];
		if (points==null){
			throw new IllegalArgumentException("Arreglo Nulo");
		}
		if (n<2){
			throw new IllegalArgumentException("Necesita dos puntos (x,y), por lo menos");
		}
		else{ 
			double d= Double.POSITIVE_INFINITY; //constante infinito
			for(int i=0; i<n;i++){
				for(int j=i+1; j<n;j++){
					double res= distance(points[i],points[j]);				
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
	//sqrt ((x1-x2)^2 +(y1-y2)^2)
	public static double distance(Point a, Point b){
		double x= a.getX()-b.getX();
		double y= a.getY()-a.getY();
		return  Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) ; 
	}

	//to String personalizado para mostrar elementos de un arreglo
	//es este caso, elementos pares
	public static String toString(Point[] array){
		String str="";
		int n= array.length;
		for (int i=0;i<n;i++){
			str+="("+array[i].getX()+","+ array[i].getY() +")";		
		}
		return str;
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
	/*
		Version Divide and Conquer.

	*/
	public static Point[] divideAndConquer (Point[] points){
		//
		Point[] res= new Point[2];
		return res;	
	}
	//Ordena el arreglo por el componente X.
	public static void sortX(Point[] points){
		//Ordena bajo un nuevo comparador 
		Arrays.sort(points, new Comparator<Point>() {
    		//redefinicion de compareTo para Point
    		public int compare(Point x1, Point x2) {
        		int i=0;
        		if ( x1.getX() < x2.getX() ) //sola linea no necesita llaves
        			i= -1;
        	  	if ( x1.getX()>x2.getX() )
        			i= 1; 
        		if (x1.getX()== x2.getX())
        			i= 0;
        	
    			return i;
    		}
    	});
    }

	//Ordena el arreglo por el componente Y.
	public static void sortY(Point[] points){
		Arrays.sort(points, new Comparator<Point>() {
			public int compare(Point p1, Point p2){
				int i=0;
				if(p1.getY() < p2.getY())
					i=-1;	
				if(p1.getY() > p2.getY())
					i=1;
				if(p1.getY() == p2.getY())
					i=0;
				return i;
			}	
		});
	}



}