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
		/*System.out.println("VALOR DEL ULTIMO "+array[array.length-1]);
		int begin= 0;
		int end= array.length;
		double[] array1 = copyArray(array,begin,(end/2));
		double[] array2 = copyArray(array,(end/2),end);
		System.out.println("PRIMER ARREGLO CARGADO CON");
		for (int i=0; i<array1.length;i++){
			System.out.println(""+array1[i]);
		}
		System.out.println("SEGUNDO ARREGLO CARGADO CON");
		for (int i=0; i<array2.length;i++){
			System.out.println(""+array2[i]);
		}
	*/	
	}
	public static int contarInversion(double[] array,int count){
		int begin= 0;
		int end= array.length;
		double[] array1 = copyArray(array,begin,(end/2));
		double[] array2 = copyArray(array,(end/2),end);
		if (array1!=null&&array2!=null){ 
			for (int i=0;i<array2.length;i++){
				if (array1[array1.length-1] > array2[i]){
					count++;
				}
			}
			count+=contarInversion(array1,count)+contarInversion(array1,count);
	 }
		return count;
	}
	

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