package blog;

public class PerfectShuffle {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    /**
     * 
     * 2 * m = 3^k - 1
     * 3^k <= 2n < 3^(k+1)
     * 
     * @param arr
     * @param n
     */
    public static void perfectShuffle2(int[] arr, int n) {
        
        int m = 1;
        while (m < n) {
            int k = 1;
            int s = 1;
            int n2 = 2 * n;
            for (; n2 / m > 3; k++,m *= 3);
            m /= 2;
            right_rotate(arr, n-m, m, n+m);
            for (int i = 0; i < k; i++, s*=3) {
            	cycleLeader(arr, s, 2 * m + 1);
            }
        }
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
    public static void right_rotate(int[] a,int num,int from, int to) {
        reverse(a, from, from + num);
        reverse(a, to - num + 1, to);
        reverse(a, from, to);
    }
    
    /**
     *  (2 * i) % (2 * n + 1)
     * @param arr
     * @param k
     * @param n
     */
    public static void cycleLeader(int[] arr, int from, int mod) {
        int last = arr[from];
        while (from != mod) {
            int tmp = arr[mod];
            arr[mod] = last;
            last = tmp;
        }
        arr[from] = last;
    }

}
