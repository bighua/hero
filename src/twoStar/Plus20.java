package twoStar;


public class Plus20 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(calculate("9c", "de5h53hi0id437bbf796ih39bff86igeii10e8i2c983eg00c9ac1a678b80h0bf3a86cf9cb2h6e8830292cef88eg3ga312c"));
	}
	
	public static String calculate(String x, String y) {
		
		int num = '0' + 0;
		int c = 'a' + 0;
		
		StringBuilder sb = new StringBuilder();
		StringBuilder yb = new StringBuilder(y).reverse();
		StringBuilder xb = new StringBuilder(x).reverse();
		
		int len = yb.length();
		if (len < xb.length()) len = xb.length();
		int tmp = 0;
		int begin = 0;
		int xn = 0;
		int yn = 0;
		while (begin < len) {
			if (begin < xb.length()) {
				xn = xb.charAt(begin);
				if (xn < c) {
					xn -= num; 
				} else {
					xn -= c - 10;
				}
			}
			if (begin < yb.length()) {
				yn = yb.charAt(begin);
				if (yn < c) {
					yn -= num; 
				} else {
					yn -= c - 10;
				}
			}
			int sum = xn + yn + tmp;
			if (sum >= 20) {
				tmp = 1;
				sum -= 20;
			} else {
				tmp = 0;
			}
			if (sum < 10) {
				sb.append((char)(sum + num));
			} else {
				sb.append((char)(sum - 10 + c));
			}
			xn = 0;
			yn = 0;
			begin++;
		}
		if (tmp == 1) sb.append('1');
		
		return sb.reverse().toString();
	}

}
