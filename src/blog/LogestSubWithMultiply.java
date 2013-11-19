package blog;


public class LogestSubWithMultiply {

	public static void main(String[] args) {
		
		double[] a = new double[]{ -2.5,4,0,3,0.5,8,-1};
//		logestSubWithMultiply(a);
		int[] b = new int[]{2,0,0};// result 0
		biggestSubMinus1(b);
		b = new int[]{2,3,4};// without 2
		biggestSubMinus1(b);
		b = new int[]{2,3,4,-2};// without -2
		biggestSubMinus1(b);
		b = new int[]{2,3,4,-4,-3};// without 2
		biggestSubMinus1(b);
		b = new int[]{-2,-3};// without -3
		biggestSubMinus1(b);
		b = new int[]{-2,-3,-4};// without -2
		biggestSubMinus1(b);
		b = new int[]{2,3,4,0};// without 0
		biggestSubMinus1(b);
		b = new int[]{2,3,4,-2,0};// result 0
		biggestSubMinus1(b);
		b = new int[]{2,3,4,-4,-3,0};// without 0
		biggestSubMinus1(b);
		b = new int[]{-2,-3,0};// without 0
		biggestSubMinus1(b);
		b = new int[]{-2,-3,-4,0};// result 0
		biggestSubMinus1(b);
	}
	
	/**
	 * 最长乘积子串——DP
	 *  Max=max{a, Max[i-1]*a, Min[i-1]*a};
     *  Min=min{a, Max[i-1]*a, Min[i-1]*a};
	 * @param a
	 */
	public static void logestSubWithMultiply(double[] a) {
		double[] maxA = new double[a.length];
		double[] minA = new double[a.length];
		maxA[0] = a[0];
		minA[0] = a[0];
		double maxProduct = a[0];
		for (int i = 1;i < a.length;i++) {
			maxA[i] = max(max(a[i], a[i] * maxA[i-1]), a[i]*minA[i-1]);
			minA[i] = min(min(a[i], a[i] * maxA[i-1]), a[i]*minA[i-1]);
			maxProduct = max(maxProduct, maxA[i]);
		}
		System.out.println(maxProduct);
		printArr(maxA);
		printArr(minA);
	}
	
	/**
	 * 即给定一个长度为N的整数数组，只允许用乘法，不能用除法，计算任意（N-1）个数的组合中乘积最大的一组
	 * @param a
	 */
	public static void biggestSubMinus1(int[] a) {
		int minPositive = Integer.MAX_VALUE;
		int maxNegative = Integer.MIN_VALUE;
		int minNegative = -1;
		int zeroNum = 0;
		int posNum = 0;
		int negaNum = 0;
		for (int i = 0; i < a.length; i++) {
			int j = a[i];
			if (j == 0) {
				zeroNum++;
			} else if (j <= -1) {
				if (j > maxNegative)
					maxNegative = j;
				if (j < minNegative)
					minNegative = j;
				negaNum++;
			} else {
				if (j < minPositive) {
					minPositive = j;
				}
				posNum++;
			}
		}
		if (zeroNum == 2) {
			System.out.println("result: 0");
		} else if (zeroNum == 1) {
			if ((negaNum & 1) == 1)
				System.out.println("result: 0");
			else
				System.out.println("without 0");
		} else {
			if ((negaNum & 1) == 1) {
				System.out.println("without " + maxNegative);
			} else {
				if (posNum == 0) {
					System.out.println("without " + minNegative);
				} else {
					System.out.println("without " + minPositive);
				}
			}
		}
	}
	
	public static double max(double a, double b) {
		return Double.compare(a, b) >= 0 ? a : b; 
	}
	
	public static double min(double a, double b) {
		return Double.compare(a, b) < 0 ? a : b; 
	}
    public static void printArr(double[] all) {

		for (double j : all) {
			System.out.print(j + " ");
		}
		System.out.println();
    }

}
