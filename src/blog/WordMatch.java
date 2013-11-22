package blog;

import util.Utility;

public class WordMatch {

	public static void main(String[] args) {
//		wordMatch("dda");
//		wordMatch("ddfii");
//		wordMatch("qypawjljgxxj");
//		wordMatch("bubloen");
//		wordMatch("kfcirlmooqypcd");
		linearLeast("dda");
		linearLeast("ddfii");
		linearLeast("gtwwl");
		linearLeast("qypawjljgxxz");
		linearLeast("bubloen");
		linearLeast("kfcirlmooqypcd");
		linearLeast("zzlsdjgzmcheued");
		linearLeast("nitbsidspfhbmh");
	}
	
	public static int wordMatch(String word) {
		char[] s = word.toCharArray();
		int len = s.length;
		int sub[] = new int[len];
		for (int i = 0; i < len ; i++) {
			sub[i] = 1;
		}
		int max = 0;
		for (int i = 1; i < s.length; i++) {
			if (s[max] == s[i]) {
				sub[i] = sub[max];
				max = i;
			} else if (s[max] < s[i]) {
				sub[i] = sub[max] + 1;
				max = i;
			} else {
				if (sub[max] == 1) {
					max = i;
				} else {
					int j = i-1;
					while (j >= 0) {
						if (s[i] > s[j]) {
							if (sub[j] + 1 >= sub[max]) {
								sub[i] = sub[j] + 1;
								max = i;
								break;
							}
							if (sub[j] + 1 > sub[i]) {
								sub[i] = sub[j] + 1;
							}
						}
						j--;
					}
				}
			}
		}
		System.out.println("the length of the word is " + len);
		Utility.printCharArr(s);
		Utility.printArr(sub);
		return len - sub[max] % 2 == 0 ? 0 : 1;
	}

	public static int linearLeast(String word) {
		char[] s = word.toCharArray();
		int len = s.length;
		int sub[] = new int[len];
		for (int i = 0; i < len ; i++) {
			sub[i] = 1;
		}
		int max = 0;
		for (int i = 1; i < s.length; i++) {
			if (s[max] >= s[i]) {
				sub[i] = sub[max] + 1;
				max = i;
			} else {
				int j = i-1;
				while (j >= 0) {
					if (s[j] >= s[i]) {
						if (sub[j] + 1 >= sub[max]) {
							sub[i] = sub[j] + 1;
							max = i;
							break;
						}
						if (sub[j] + 1 > sub[i]) {
							sub[i] = sub[j] + 1;
						}
					}
					j--;
				}
			}
		}
		System.out.println("the length of the word is " + len + ", the max index is " + max);
		Utility.printCharArr(s);
		Utility.printArr(sub);
		int result = 0;
		if ((len & 1) == 1) {
			result = sub[max] < (len + 4) / 2 ? 1 : 0;
		} else {
			result = sub[max] < (len + 2) / 2 ? 0 : 1;
		}
		System.out.println(result);
		return result;
	}
}
