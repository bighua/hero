package blog;

import util.Uitility;

public class PerfectShuffle {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	int n = 9;
    	int l = n*2+1;
        int[] a = new int[l];
        for (int i = 1; i < l; i++) {
        	if (i <= n) {
        		a[i] = 2*i - 1;
        	} else {
        		a[i] = 2*(i-n);
        	}
        }
        shuffle(a, n);
        Uitility.printArr(a);
    }
    
    public static void shuffle(int[] a,int n) {  
        int i,t,n2 = n * 2;  
        perfectShuffle2(a,n);  
        for (i = 2; i <= n2; i += 2) {  
            t = a[i - 1];  
            a[i - 1] = a[i];  
            a[i] = t;  
              
        }  
    }
    
    /**
     * 输入数组　A[1..2 * n]
     * step 1 找到 2 * m = 3^k - 1 使得 3^k <= 2 * n < 3^(k +1)
     * step 2 把a[m + 1..n + m]那部分循环移m位
     * step 3 对每个i = 0,1,2..k - 1，3^i是个圈的头部，做cycle_leader算法，数组长度为m，所以对2 * m + 1取模。
     * step 4 对数组的后面部分A[2 * m + 1.. 2 * n]继续使用本算法, 这相当于n减小了m。
     * @param arr
     * @param n
     */
    public static void perfectShuffle2(int[] arr, int n) {
        
        int index = 0;
        while (n > 1) {
            int m = 1;
            int k = 0;
            int n2 = 2 * n;
            for (; n2 / m >= 3; k++,m *= 3);
            m /= 2;
            right_rotate(arr, m, index + m, n);
            for (int i = 0, s= 1; i < k; i++, s*=3) {
                cycleLeader(arr, index, s, 2 * m + 1);
            }
            n -= m;
            index += m * 2;
        }
        index++;
        // n = 1
        int t = arr[index];
        arr[index] = arr[index+1];
        arr[index+1] = t;
    }
    
    public static void reverse(int[] a,int from,int to) {  
        int t;  
        for (; from < to; ++from, --to) {  
            t = a[from];  
            a[from] = a[to];  
            a[to] = t;  
        }  
          
    }  
      
    //循环右移num位 时间复杂度O(n)  
    public static void right_rotate(int[] a,int num,int from, int n) {
        reverse(a, from + 1, from + n - num);
        reverse(a, from + n - num + 1, from + n);
        reverse(a, from + 1, from + n);
    }
    
    /**
     * i -> (2 * i) % (2 * n + 1)
     * @param arr
     * @param k
     * @param n
     */
    public static void cycleLeader(int[] arr, int from,int index, int mod) {
        int last = arr[from+index];
        for (int i = (2 * index) % mod;i != index;i = (2 * i) % mod) {
            int tmp = arr[from + i];
            arr[from+i] = last;
            last = tmp;
        }
        arr[from+index] = last;
    }

}
