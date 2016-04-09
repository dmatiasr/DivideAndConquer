public class Inversion{
	public static void main (String[] args){
		double[] array= new double[5];
		array[0]= 3;
		array[1]= 10;
		array[2]= 2;
		array[3]= 9;
		array[4]= 5;
		
		//cantidad de inversion lo lleva count.
		int count=0;
		//calculo de inversiones de un arreglo.
		System.out.println("Resultado de inversion "+inversion(array,count));		
	}
	/*
	Cuanta la cantidad de inversiones de un arreglo
	*/
	public static int inversion( double[] array, int count ){		
		int begin= 0;
		int end= array.length;
		//copia de elementos del array
		double[] array1 = copyArray(array,begin,(end/2));
		double[] array2 = copyArray(array,(end/2),end);
		//arreglos nulos y directamente corta aca.
		if (array1==null && array2==null){
			throw new IllegalArgumentException ("ARREGLOS NULOS");
		}
		//Caso base con un unico elemento ambos arreglos.
		if (array1.length==1 && array2.length==1 ){ 
			//si array[0]>array2[0]
			if (array1[array1.length-1]>array2[array2.length-1]){  
				System.out.println("Compara caso base "+array1[0]+" con "+array2[0] );
				count=count+1;
				System.out.println("Y count CASO BASE "+count);
				//return count;
			}
		}
		if (array1.length>1 || array2.length>1) {	
			//count no esta contando el caso base que trae de la recursion
			count+=contarInversion(array1,array2,count);
			System.out.println("count"+count);
			inversion(array1,count);
			inversion(array2,count);			
			return count;
			//System.out.println("inversion(array1,count)"+inversion(array1,count));
			//System.out.println("inversion(array2,count)"+inversion(array2,count));			
		}
		
		System.out.println("count del return final es--------> "+count);
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
		 			count=count+1;
		 			System.out.println("Y COUNT ES "+count);
		 		}else{
		 			System.out.println("Y COUNT NO SUMA "+count);
		 		}

		 	}
		 }
		 //System.out.println("return de contarInversion "+count);
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