package blog;


public class AllPermutation {

	private static int count = 0;
    public static void main(String[] args) {
    	
    	int[] all = new int[]{1,2,3,4,5};
//    	calcAllPermutation_R(all,0,5);
//    	System.out.println("total:" + count);
    	calcAllPermutation_cab(all,5);
    }
    
    /**
     * 从集合中依次选出每一个元素，作为排列的第一个元素，然后对剩余的元素进行全排列，如此递归处理，从而得到所有元素的全排列
     * @param all
     * @param first
     * @param num
     */
    public static void calcAllPermutation_R(int[] all, int first, int num) {
    	if (first + 1 == num) {
    		printArr(all);
    		count++;
    		return;
    	}
    	for (int i = first; i < num; i++) {
    		swap(all,first,i);
    		calcAllPermutation_R(all, first + 1,num);
    		swap(all,first,i);
    	}
    }
    
    /**
     * 字典序排列
     * @param a
     * @param b
     * @param x
     * @param y
     * @return
     */
    public static void calcAllPermutation_cab(int[] all, int num)  
    {  
    	printArr(all);
    	int count1 = 1;
    	while(true) {
    		int i = num -2;
    		for (; i >=0; i--) {
    			if (all[i] < all[i+1]) break;
    		}
    		if (i < 0) break;
    		int k = 0;
    		for (k = num -1; k >= i; k--) {
    			if (all[k] > all[i]) break;
    		}

    		swap(all,k,i);
    		reverse(all, i+1, num-1);
    		printArr(all);
    		count1++;
    	}
    	System.out.println("cab sort total:" + count1);
    } 
    
    public static void swap(int[] all, int f, int t) {

		int tmp = all[f];
		all[f] = all[t];
		all[t] = tmp;
    }
    
    public static void reverse(int[] all, int s, int e) {
    	while (s < e) {
    		swap(all, s++, e--);
    	}
    }
    
    public static void printArr(int[] all) {

		for (int j : all) {
			System.out.print(j + "-");
		}
		System.out.println();
    }
}
