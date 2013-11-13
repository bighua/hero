package threeStar;

public class HowMany {

	public static void main(String[] args) {

		System.out.println(howmany(8,10,3,100));
	}
	
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
			if (a >= x) {
				nums += b - max + 1;
				max = b + 1;
			}
			i++;
			a = i * s;
			b = i * e;
		}
		System.out.println(System.currentTimeMillis() - ms + "ms");
		return nums;
	}
	
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
}
