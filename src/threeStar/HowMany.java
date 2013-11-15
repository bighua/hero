package threeStar;

public class HowMany {

    public static void main(String[] args) {

//        System.out.println(howmany(99999999,100000000,100000001,1000000000));//63
//        System.out.println(howmany(8,10,1,2));//0
//        System.out.println(howmany(8,10,7,8));//1
//        System.out.println(howmany(8,10,11,30));//12
//        System.out.println(howmany(8,9,15,16));//1
//        System.out.println(howmany(8,10,17,18));//2
//        System.out.println(howmany(8,10,8,21));//8
//        System.out.println(howmany(8,10,17,18));//2
//        System.out.println(howmany2(100000000, 100000001, 1, 1000000000));//4
//        System.out.println(howmany(2,5,8,11));//4
//        System.out.println(howmany(2,3,1,1000000000));//999999999
//        System.out.println(howmany1(2,3,1,1000000000));//999999999
        System.out.println(howmany2(2,3,1,1000000000));//999999999
    }
    
    /**
     * my solution
     * @param a
     * @param b
     * @param x
     * @param y
     * @return
     */
    public static int howmany(int a,int b,int x,int y) {
        int nums = 0;
        long ms = System.currentTimeMillis();
        if (a == 1) return y - x + 1;
        int max = x;
        int i = 1;
        int s = a;
        int e = b;
        while(true) {
            if (a > y) break;
            if (a > max) max = a;
            if (b >= y) {
                nums += y - max + 1;
                break;
            }
            if (b >= x) {
                nums += b - max + 1;
                max = b + 1;
            }
            i++;
            a = i * s;
            b = i * e;
        }
        System.out.println((System.currentTimeMillis() - ms) + "ms");
        return nums;
    }
    
    /**
     * O(n) from hero group's Leon_a's solution
     * @param a
     * @param b
     * @param x
     * @param y
     * @return
     */
    public static  int howmany1(int a,int b,int x,int y)  
    {  
        long ms = System.currentTimeMillis();
        int count = 0;  
        int i = 0;  
        while (i * b + 1 <= y) {  
            int c = i * b + 1;  
            int d = (i + 1) * a;  
            int max = c > x ? c : x;  
            int min = d > y + 1 ? y + 1 : d;  
            if (min - max > 0) {  
                count += (min - max);  
            }  
            i++;  
        }  
        System.out.println((System.currentTimeMillis() - ms) + "ms");
        return y - x - count + 1;  
    } 
    
    /**
     * near to O(1) from hero group's Leon_a's solution
     * @param a
     * @param b
     * @param x
     * @param y
     * @return
     */
    public static  int howmany2(int a,int b,int x,int y)  
    {  
        long ms = System.currentTimeMillis();
        int j = (a - 1) / (b - a) > y / b ? y / b : (a - 1) / (b - a);
        int count = 0;
        int i = 0;
        
        while (i <= j) {
            int c = i * b + 1;
            int d = (i + 1) * a - 1;
            int max = c > x ? c : x;
            int min = d > y ? y : d;
            if (min - max + 1 > 0) {
                count += (min - max + 1);
            }
            
            i++;
        }
        System.out.println((System.currentTimeMillis() - ms) + "ms");
        return y - x - count + 1;

    } 
    
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
