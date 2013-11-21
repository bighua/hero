package blog;

import util.Utility;

public class WordMatch {

	public static void main(String[] args) {
		wordMatch("dda");
		wordMatch("ddfii");
		wordMatch("qypawjljgxxj");
		wordMatch("bubloen");
		wordMatch("kfcirlmooqypcd");
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

}
