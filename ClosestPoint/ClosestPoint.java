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
		System.out.println("Puntos mas cercanos por Fuerza Bruta : ");
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
		// para caso base aplicar algoritmo de fuerza bruta.
		int n= points.length;
		Point[] res= new Point[2]; //dos elementos que van a ser los puntos de distancia minima
		if (n<=3){  //Si tiene 3 elementos
			return bruteForce(points);	
		}
		else{
			//aplicar divide and conquer
			sortX(points); //ordena por componente X
			//Divide en dos sub arreglos
			Point[] left = copyArray(points,0,n/2 );
			Point[] right= copyArray(points,(n/2),n);
			//Obtiene los dos minimos de cada lado de la raya vertical
			Point[] minLeft= divideAndConquer(left);
			Point[] minRight= divideAndConquer(right);
			//resultado en arreglo de dos posiciones, una posicion para cada punto.
			double distLeft= distance(minLeft[0],minLeft[1]);
			double distRight= distance(minRight[0],minRight[1]);
			//obtengo el menor entre la parte izq y derecha
			double dist=0;
			if (distLeft<=distRight){
				res=minLeft;
				dist=distLeft;
			}else{
				res=minRight;
				dist=distRight;
			}
			//ver cerca de la raya vertical:
			//para cada punto cerca de la raya del lado izquierdo
			//basta comparar con 6 del lado derecho.
			//ordeno el arreglo por componente Y
			double x= points[(n/2)-1].getX(); //obtengo el X cercano a la vertical
			//de ese x obtener todos los y
			//luego ordenarlos por coordenada y 
			LinkedList pointsByY=new LinkedList();
			for(int k=0 ; k<points.length; k++){
				if (points[k].getX()==x){
					if(Math.abs(points[k].getX()-x ) < dist){ //solo meto a la lista los
						pointsByY.add(points[k]);			//que |points.x -x |<dist
					}
				}
			}
			//Por comodidad paso todos los elementos de la lista a un arreglo
			//nuevamente.
			Point[] pointsY= new Point[pointsByY.size()];
			int q=0;
			while( !(pointsByY.isEmpty()) ) {
				 	pointsY[q]= (Point)pointsByY.poll();
					q++;
			}
			//ordenar el nuevo arreglo en base a las y
			sortY(pointsY);
			//obtengo distancia minima comparando con 6 puntos como maximo y
			//comparo la distancia obtenida con la distancia minima entre left y right
			double dMinsY= Math.pow(dist,2);
			for(int i=0;i<pointsY.length-1;i++){
				for(int j=i+1; j<pointsY.length  ;j++){
					double powMod= pow2Module(pointsY[j].getY(),pointsY[i].getY());
					if (powMod < dMinsY)  {
						break; //corte si no se da esta propiedad, en condicion de ciclo tirar error.
					}
					//dminsq ← min((S[k].x − S[i].x)^ 2 + (S[k].y − S[i].y)^ 2 , dminsq)
					double aux= pow2Module(points[j].getX(),points[i].getX() )+pow2Module(points[j].getY(),points[i].getY()); 
					dMinsY=	min(aux,dMinsY);//obtengo el minimo	
					//comparar el resultado minimo obtenido aqui con el resultado de la parte minima izq o der.
				}
			}

		}
		return res;	
	}
	//(S[k].y − S[i].y)^2 < dminsq
	public static double pow2Module(double a,double b){
		return Math.pow((a-b),2);
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
 
	public static Point[] copyArray(Point[] array,int begin,int end){
    	int cota= end-begin;
    	Point[] aux= new Point[cota];
    	for (int i=0;i<cota;i++,begin++){
    		aux[i]=array[begin];
    	}
    	return aux;
	}


}