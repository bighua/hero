package blog;

public class StringWildcharMatcher {

	public static void main(String[] args) {

		myFind("abcadefg", "ac");// 0
		myFind("abcadefg", "a?");//ab ad 2
		myFind("abcadefg", "a?c");//abc 1
		myFind("abcadefg", "a??c");// 0
		myFind("abcadefg", "a*");// abcadefg 1
		myFind("abcadefg", "a**");// abcadefg 1
		myFind("abcadefg", "a*c");// abc 1
		myFind("abadefcg", "a*c");// abadefc 1
		myFind("abcadecg", "a**c");// abcadec 1
		myFind("abcadecg", "a**cg");// abcadecg 1
		myFind("abcadecg", "a**cf");// 0
		myFind("abcadecg", "a*c*");// abcadecg
		myFind("abcadecg", "a*c*g");// abcadecg
		myFind("abcadecg", "a*c*f");// 0
	}
	
	public static void myFind(String s, String pattern) {
		char[] cs = s.toCharArray();
		char[] cp = pattern.toCharArray();
		int sl = cs.length;
		int pl = cp.length;
		int sIndex = 0;
		int pIndex = 0;
		boolean match = true;
		int count = 0;
		StringBuilder matcher = new StringBuilder();
		while (sIndex < sl) {
			if (pIndex < pl) {
				char c = cs[sIndex];
				// *
				if (cp[pIndex] == '*') {
					pIndex++;
					if (pIndex == pl) {
						while (sIndex < sl) {
							matcher.append(cs[sIndex++]);
						}
					} else {
						if (cp[pIndex] != '*') {
							int se = sl - 1;
							while (sIndex <= se && cp[pIndex] != cs[se]) se--;
							if (sIndex > se) {
								match = false;
							} else {
								while (sIndex <= se) {
									matcher.append(cs[sIndex++]);
								}
								pIndex++;
							}
						}
					}
				// ?
				} else if (cp[pIndex] == '?') {
					if (sIndex + 1 == sl) {
						match = false;
					}
					sIndex++;
					pIndex++;
					matcher.append(c);
				} else if (cp[pIndex] == c) {
					sIndex++;
					pIndex++;
					matcher.append(c);
				} else {
					sIndex++;
					pIndex = 0;
					matcher = new StringBuilder();
				}
			} else {
				System.out.println(matcher);
				matcher = new StringBuilder();
				pIndex = 0;
				count++;
			}
		}
		if (match && matcher.length() != 0) {
			count++;
			System.out.println(matcher);
		}
		System.out.println("the number of matched String:" + count);
	}

}
