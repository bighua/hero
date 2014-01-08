package threeStar;

import java.util.LinkedList;

/**
 * 给定两个字符串，仅由小写字母组成，它们包含了相同字符。
 * 求把第一个字符串变成第二个字符串的最小操作次数，且每次操作只能对第一个字符串中的某个字符移动到此字符串中的开头。 
 * 例如给定两个字符串“abcd" "bcad" ，输出：2，
 * 因为需要操作2次才能把"abcd"变成“bcad" ，方法是：abcd->cabd->bcad。
 * @author zhuxh
 *
 */
public class GetNum {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	long b = System.currentTimeMillis();
        System.out.println(call(1000000, 3, 1));
        System.out.println(System.currentTimeMillis() - b);
//        System.out.println(call(10, 3, 2));
    }
    
    public static int call(int n, int x, int y) {
    	LinkedList<Integer> cir = new LinkedList<Integer>();
    	for (int i = 1; i <= n; i++) {
    		cir.add(i);
    	}
    	int c = n;
    	int index = 0; 
    	int pos = 0;
    	for (int i = 1; i < n; i++) {
    		if (i % 2 != 0) {
    			pos = (pos + x - 1) % c;
    			cir.remove(pos);
    			pos--;
    		} else {
    			pos = pos - ((y - 1) % c);
    			if (pos < 0) pos += c;
    			cir.remove(pos);
    		}
    		c--;
    	}
//    	System.out.println(index);
    	return cir.peek();
    }

}
