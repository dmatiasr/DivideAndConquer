import java.util.*;
public class SumaMinimaSecuencia {
	public static void main (String[] args){
		LinkedList<Integer> lista=new LinkedList<Integer> ();
		int[] array= new int[5];
		array[0]=-3;
		array[1]=10;
		array[2]=-9;
		array[3]=3;
		array[4]=-5;
		int res = sumaMinima(array,0,(array.length)-1);
		System.out.println("Suma "+ res);
	}	
	public static int sumaDelMedio(int[] array, int left, int middle, int right){
		//ver desde el medio para izquierda la secuencia de suma minima
		int sumaMinLeft=Integer.MAX_VALUE; //le inicializo en infinito positivo
		int suma=0;
		for (int i=middle; i>=left;i--){
			suma+=array[i];
			if(suma<sumaMinLeft){
				sumaMinLeft=suma;
			}
		}
		//ver del medio a derecha la secuencia de suma minima
		int sumaMinRight=Integer.MAX_VALUE;
		suma=0;
		for (int i=middle+1;i<=right;i++){
			suma+=array[i];
			if(suma<sumaMinRight){
				sumaMinRight=suma;
			}
		}
		return sumaMinLeft+sumaMinRight;			
	}
	//retorna la suma minima de una subsecuencia
	public static int sumaMinima (int[] array, int left, int right){
		if (array==null){
			throw new IllegalArgumentException ("Null Array");
		}
		//caso base si tiene un solo elemento el arreglo
		if(left==right){
			return (0);
		}else{
			int middle= (left+right)/2;
			return minValorDeTres(sumaMinima(array,left,middle),sumaMinima(array,middle+1,right),sumaDelMedio(array,left,middle,right));
		}
		
	}
	//Calcula el min de tres valores
	public static int minValorDeTres(int a, int b, int c){
		int min=0;
		if(a==0||b==0){
			min = c;
		}else{
			if (a<=b && a<=c){
				min = a;
			}
			if (b<=a && b<=c){
				min = b;
			}
			if (c<=a && c<=b){
				min = c;
			}
		}
		return min;
	}
}