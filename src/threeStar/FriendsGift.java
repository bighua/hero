package threeStar;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FriendsGift {

	public static void main(String[] args) {
//		BigInteger b100 = jc(100);
//		BigInteger b51 = jc(51);
//		BigInteger b49 = jc(49);
//		
//		System.out.println(b100.divide(b51).divide(b49));
		long b = System.currentTimeMillis();
		System.out.println(getGift(99, 0));
		System.out.println(System.currentTimeMillis() - b);
	}
	
	public static String getGift(int n, int m) {
		String ret = "";
		Integer z = m;
		m = n - z;
		if (m == 1) return "0.00000000";
		BigInteger b0 = new BigInteger("0");
		BigInteger b1 = new BigInteger("1");
		BigInteger b2 = new BigInteger("2");
		BigInteger[] jc = new BigInteger[n+1];
		jc[0] = b1;
		jc[1] = b1;
		jc[2] = b2;
		BigInteger[] a = new BigInteger[m+1];
		if (m == 0) {
			a[0] = b1;
		} else {
		BigInteger[][] c = new BigInteger[m+1][];
		c[2] = new BigInteger[3];
		c[2][0] = b1;
		c[2][1] = b2;
		c[2][2] = b1;
		a[1] = b0;
		a[2] = b1;
		
		// a[3]..a[m]
		for (Integer i = 3; i <= m; i++) {
			jc[i] = jc[i-1].multiply(new BigInteger(i.toString()));
			BigInteger tmp = b1;
			Integer j = i / 2;
			c[i] = new BigInteger[j+1];
			if (i % 2 == 0) {
				if (j - 1 == 0) {
					c[i][j] = new BigInteger(i.toString());
				} else {
					// c[i][j] = c[i-1][j-1]*i/j
					c[i][j] = c[i-1][j-1].multiply(
						new BigInteger(i.toString())).divide(
							new BigInteger(j.toString()));
				}
				tmp = tmp.add(c[i][j].multiply(a[j]));
				j -= 1;
			}
			for (Integer k = 1; k <= j; k++) {
				if (k - 1 == 0) {
					c[i][k] = new BigInteger(i.toString());
				} else {
					// c[i][k] = c[i-1][k-1]*i/k
					c[i][k] = c[i-1][k-1].multiply(new BigInteger(i.toString())).divide(new BigInteger(k.toString()));
				}
				// c[i][k] * (a[i-k] + a[k])
				tmp = tmp.add(c[i][k].multiply(a[i-k].add(a[k])));
			}
			a[i] = jc[i].subtract(tmp);
		}
		}
		// a[m]/m!(n-m)!
		Integer t = m + 1;
		while (t <= z) {
			jc[t] = jc[t-1].multiply(new BigInteger(t.toString()));
			t++;
		}
		BigDecimal u = new BigDecimal(a[m]);
		BigDecimal d = new BigDecimal(jc[m].multiply(jc[z]));
		
		ret = u.divide(d, 8, RoundingMode.HALF_UP).toPlainString();
		return ret;
	}
}
