public class SumaMinimaSecuencia {
	public static void main (String[] args){
		int[] array= new int[5];
		array[0]= 3;
		array[1]= 10;
		array[2]= 9;
		array[3]= 2;
		array[4]= 5;
		int res=sumaMinima(array,0,(array.length)-1);
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
				System.out.println("sumo left"+sumaMinLeft);
			}
		}
		//ver del medio a derecha la secuencia de suma minima
		int sumaMinRight=Integer.MAX_VALUE;
		suma=0;
		for (int i=middle+1;i<=right;i++){
			suma+=array[i];
			if(suma<sumaMinRight){
				sumaMinRight=suma;
				System.out.println("sumo right"+sumaMinRight);
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
			int valMax=Integer.MAX_VALUE;
			return (valMax);
		}else{
			int middle= (left+right)/2;
			return minValorDeTres(sumaMinima(array,left,middle),sumaMinima(array,middle+1,right),sumaDelMedio(array,left,middle,right));
		}
		
	}
	//Calcula el max de tres valores
	public static int minValorDeTres(int a, int b, int c){
		int min=0;
		if (a<b && a<c){
			min = a;
		}
		if (b<a && b<c){
			min = b;
		}
		if (c<a && c<b){
			min = c;
		}
		return min;
	}
}