package test.test04;

import java.util.HashMap;
import java.util.Map;

public class Test01_변수 {
	public static void main(String[] args) {
		int a =1;
		String b = "test";
		Map<String, String> c = new HashMap<>();
		c.put("key1", "value1");
		System.out.println();
		System.out.println();
		System.out.println();
		test(a, b, c);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	public static void test(int a, String b, Map<String, String> c) {
		a = 2;
		b = "test2";
		c = new HashMap<>();
		c.put("key1", "value2");
	}
}
