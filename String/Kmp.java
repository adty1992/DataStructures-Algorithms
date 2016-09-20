public class Kmp {
	public static void main(String[] args) {
		String src = "kfflfabcdjjlj";
		String target = "abcd";
			System.out.println("match : " + violentMatch(src, target));
	}

	//暴力匹配
	private static int violentMatch(String srcStr, String targetStr) {
		if (srcStr == null || targetStr == null) {
			return -1;
		}

		int srcLen = srcStr.length();
		int targetLen = targetStr.length();

		char[] srcChars = srcStr.toCharArray();
		char[] targetChars = targetStr.toCharArray();

		int matchCount = 0;//匹配成功的次数

		for (int j = 0; j < srcLen; j++) {
			if (srcChars[j] == targetChars[matchCount]) {
				matchCount++;
			}else {//一旦发现有不匹配的马上将匹配成功次数置0，重新计数匹配成功次数
				matchCount = 0;
			}
			if (matchCount == targetLen) {//当匹配成功的次数等于目标字符数组的长度时，即匹配成功
				return j - matchCount + 1;
			}
		}
		return -1;
	}
}