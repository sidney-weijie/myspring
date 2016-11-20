/**
 * 
 */
package com.sidney.generic;

/**
 * @author MyPC
 *
 */
public class PairTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String []words = {"Mary","sidney","ego","bigger","lab"};
		Pair<String> mm = ArrayAlg.minMax(words);
		System.out.println(mm.getFirst()  + "   "  + mm.getSecond());
	
		System.out.println(ArrayAlg.<String>getMiddle("123","456","789"));
	}

}

class ArrayAlg{
	public static Pair<String> minMax(String[]a){
		if( a == null || a.length == 0) return null;
		String min = a[0];
		String max = a[0];
		for(int i = 0; i< a.length;i++){
			if(min.compareTo(a[i])>0) min = a[i];
			if(max.compareTo(a[i])<0) max = a[i];
		}
		
		return new Pair<String>(min, max);
	}
	
	public static <T> T getMiddle(T ...a){
		return a[a.length/2];
	}
}