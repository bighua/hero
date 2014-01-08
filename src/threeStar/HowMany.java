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
//        System.out.println(howmany2(2,3,1,1000000000));//999999999
//        System.out.println(howmany("010111"));
        char[] input = new char[10000];
        int i = 0;
        input[i++] = 'b';
        input[i++] = 'i';
        input[i++] = 'n';
        input[i++] = 'g';
        input[i++] = 'g';
        while (i++ < input.length - 4) {
        	input[i] = 'g';
        }
        input[i++] = 'i';
        input[i++] = 'n';
        input[i++] = 'g';
        long b = System.currentTimeMillis();
        System.out.println(howmany_bing("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiinnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnngggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"));
        System.out.println(System.currentTimeMillis() - b);
    }
    
    /**
     * 给定整数区间[a,b]和整数区间[x,y]，你可以使用任意多次a,b之间的整数做加法，可以凑出多少个[x,y]区间内的整数？ 
     * 输入 a,b,x,y，其中1<= a < b <= 1000000000,  1 <= x < y <= 1000000000。 
     * 输出： 用[a,b]内的整数做任意多次加法，可以得到多少个[x,y]内的整数。 
     * 例如a = 8, b = 10, x = 3 , y = 20 我们可以得到 [3..20]之间的整数 8, 9, 10, 16 （ 8 + 8）, 17（8 + 9）, 18（9 + 9）, 19（9 + 10）, 20（10 + 10），因此输出8。
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
    
    /**
     * 给定一个字符串，长度不超过100，其中只包含字符0和1,并且字符0和1出现的次数都是偶数。
     * 你可以把字符串任意切分，把切分后的字符串任意分给两个人，
     * 让两个人得到的0的总个数相等，得到的1的总个数也相等。 
     * 例如，输入串是010111,我们可以把串切位01, 011,和1， 把第1段和第3段放在一起分给一个人，
     * 第二段分给另外一个人，这样每个人都得到了1个0和两个1。我们要做的是让切分的次数尽可能少。 
     * @param s
     * @return
     */
    public static int howmany(String s) {
    	int result = 2;
    	char[] cs = s.toCharArray();
    	int[] a0 = new int[cs.length + 1];
    	int[] a1 = new int[cs.length + 1];
    	for (int i = 0; i < cs.length; i++) {
    		if (cs[i] == '0') {
    			a0[i+1] = a0[i] + 1;
    			a1[i+1] = a1[i];
    		} else {
    			a1[i+1] = a1[i] + 1;
    			a0[i+1] = a0[i];
    		}
    	}
    	
    	int num = cs.length;
    	if (a0[num/2] == a0[num] / 2 && a1[num / 2] == a1[num] / 2) result = 1;
    	return result;
    }
    
    public static int howmany_bing(String s) {
    	char[] cs = s.toCharArray();
    	int start = 0;
    	int end = cs.length - 1;
    	while (cs[start] != 'b') {
    		start++;
    		if (start == cs.length) return 0;
    	}
    	while (cs[end] != 'g') {
    		end--;
    		if (end < 0) return 0;
    	}
    	int g_num = 0;
    	int ng_num = 0;
    	int ing_num = 0;
    	int bing_num = 0;
    	int mod = 1000000007;
    	while (end >= start) {
    		if (cs[end] == 'g') g_num++;
    		else if (cs[end] == 'n') {
    			ng_num += g_num % mod;
    			ng_num %= mod;
    		}
    		else if (cs[end] == 'i') {
    			ing_num += ng_num % mod;
    			ing_num %= mod;
    		}
    		else if (cs[end] == 'b') {
    			bing_num += ing_num % mod;
    			bing_num %= mod;
    		}
    		end--;
    	}
    	return bing_num;
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
