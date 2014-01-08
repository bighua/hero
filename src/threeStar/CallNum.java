package threeStar;

/**
 * 给定两个字符串，仅由小写字母组成，它们包含了相同字符。
 * 求把第一个字符串变成第二个字符串的最小操作次数，且每次操作只能对第一个字符串中的某个字符移动到此字符串中的开头。 
 * 例如给定两个字符串“abcd" "bcad" ，输出：2，
 * 因为需要操作2次才能把"abcd"变成“bcad" ，方法是：abcd->cabd->bcad。
 * @author zhuxh
 *
 */
public class CallNum {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getNumber("abcdefg", "gebcadf"));
    }
    
    public static int getNumber(String a,String b) {
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int j = bs.length - 1;
        for (int i = as.length - 1; i >= 0; i--) {
            if (as[i] == bs[j]) j--;
        }
        return j + 1;
    }

}
