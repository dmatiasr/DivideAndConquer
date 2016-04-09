public class Inversion{
	
	public static void main (String[] args){
		double[] array= new double[5];
		array[0]= 3;
		array[1]= 10;
		array[2]= 2;
		array[3]= 9;
		array[4]= 5;
		int count=0;
		System.out.println(inversion(array,count));		
	}
	/*
	Cuanta la cantidad de inversiones de una lista
	*/
	public static int inversion( double[] array, int count ){		
		int begin= 0;
		int end= array.length;
		//copia de elementos del array
		double[] array1 = copyArray(array,begin,(end/2));
		double[] array2 = copyArray(array,(end/2),end);
		if (array1==null && array2==null){
			throw new IllegalArgumentException ("ARREGLOS NULOS");
		}
		if (array1.length==1 && array2.length==1 ){
			if (array1[array1.length-1]>array2[array2.length-1]){
				count++;
			}
		}
		if (array1.length>1 || array2.length>1) {	
			inversion(array1,count);
			inversion(array2,count);
			count+=contarInversion(array1,array2,count);
		}
 		return count;
    }
	/*
	cuenta cuantos elementos de la primer lista son mayores a lo de la segunda lista
	*/
	public static int contarInversion(double[] array1, double[] array2, int count){
		for (int i=0;i<array1.length;i++){
			for (int j=0;j<array2.length ;j++ ) {
				System.out.println("COMPARA "+array1[i]+" Con "+array2[j]);
		 		if(array1[i]>array2[j]){
		 			count++;
		 		}
		 	}
		 }
		 return count;
		}	
	//Copia los elementos de un array a otro.
	public static double[] copyArray(double[] array,int begin,int end){
    	int cota= end-begin;
    	double[] aux= new double[cota];
    	int i=0;
    	while (i<cota){
    		aux[i]=array[begin];
    		//System.out.println(aux[i]);
    		i++;
    		begin++;

    	}
    	return aux;
	}
}