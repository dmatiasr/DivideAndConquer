public class Inversion{
	
	public static void main (String[] args){
		double[] array= new double[5];
		
		array[0]= 3;
		array[1]= 10;
		array[2]= 2;
		array[3]= 9;
		array[4]= 5;
		
		int count=0;
		System.out.println("RESULTADO"+contarInversion(array,count));
		
	}
	public static void inversion( double array ){
		int begin= 0;
		int end= array.length;
		double[] array1 = copyArray(array,begin,(end/2));
		double[] array2 = copyArray(array,(end/2),end);
		int count=0;
		inversion(array1);
		inversion(array2);
		int result= contarInversion(array1,count)+contarInversion(array2,count)
	}

	public static int contarInversion(double[] array1,int count){
		if (array1==null || array1.length=1){
			return 0
		}else 
			if (array1[0]>array1[1]){
				count++;
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
    		System.out.println("LOAD ||| "+aux[i]);
    		i++;
    		begin++;

    	}
    	return aux;
	}
}