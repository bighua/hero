package threeStar;

import java.math.BigInteger;

public class FriendsGift {

	public static void main(String[] args) {
		BigInteger b100 = jc(100);
		BigInteger b51 = jc(51);
		BigInteger b49 = jc(49);
		
		System.out.println(b100.divide(b51).divide(b49));
	}
	
	public static String getGift(int n, int m) {
		String ret = "";
		int z = n - m;
		if (z == 1) return "0.00000000";

		m = n - m;
		BigInteger b0 = new BigInteger("1");
		BigInteger b1 = new BigInteger("1");
		BigInteger b2 = new BigInteger("2");
		BigInteger[][] c = new BigInteger[m+1][m];
		BigInteger[] jc = new BigInteger[m+1];
		jc[1] = b1;
		jc[2] = b2;
		BigInteger[] a = new BigInteger[m];
		a[1] = b0;
		a[2] = b1;
		
		int j = m / 2;
		for (int i = 3; i <= m; i++) {
			
		}
		return ret;
	}
	
	private static BigInteger jc(int k) {
		BigInteger bk = new BigInteger(String.valueOf(k));
		BigInteger ret = bk;
		BigInteger b1 = new BigInteger("1");
		
		while (bk.compareTo(b1) > 0) {
			bk = bk.subtract(b1);
			ret = ret.multiply(bk);
		}
		return ret;
	}
}
