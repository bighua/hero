package twoStar;

/**
 * 一个理想随机数发生器R，给定参数正整数x，它可以均匀随机产生一个闭区间[0,x]之间的实数（注意是实数,每个实数出现的概率相同）R(x)。
 * 现在给定3个正整数a,b,c，我们使用a,b产生两个随机实数R(a), R(b)，问R(a) + R(b) <= c的概率有多大？
 *  |
 * c|
 *  |\
 * b|_\_ _ _ d(a+b)
 *  |  \    |
 *  |   \   |
 *  | _ _\_ | _ _ _ _
 * 0     c  a
 * 直线x+y=c与矩形b0ad的交集面积/矩形b0ad的面积
 * @author Administrator
 *
 */
public class IdealRandom {

	public static void main(String[] args) {
		System.out.println(calculate(3,4,5));
	}
	
	public static String calculate(int a, int b, int c) {

		int p = 2 * a * b;
		int s = 1;
		if (a + b <= c) {
			return "1/1";
		} else if (c <= a && c <= b) {
			s = c * c;
		} else if (c > a && c > b) {
			s = a + b - c;
			s = p - s * s;
		} else if (c <= a) {
			s = (c + c - b) * b;
		} else {
			s = (c + c - a) * a;
		}
		int d = gcd(s, p);
		return String.valueOf(s/d) + "/" + String.valueOf(p/d);
	}
	
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

}
