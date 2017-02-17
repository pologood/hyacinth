package org.hyacinth.examples;

import org.hyacinth.examples.proxy.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		System.out.println(format("2-4A0r7-4k",9));
		StringBuilder sb = new StringBuilder();
	    sb.append(1 + "," + "" + "," + 1);
		System.out.println(sb.toString().split(",").length);
		String str = "a,b,c,,";
		String[] ary = (str + "," + 1).split(",");
		//预期大于3，结果是3
		System.out.println(ary.length - 1);
	}
	
	public static String format(String S, int K) {
		String pureS = S.replaceAll("-", "").toUpperCase();
		int length = pureS.length();
		if (K >= length) return pureS;
		int left = length % K;
		StringBuffer sb = new StringBuffer();
		if (left != 0) {
			sb.append(pureS.substring(0, left)).append("-");
		}
		for (int i = left; i < length - K; i += K) {
			sb.append(pureS.substring(i, i+K)).append("-");
		}
		sb.append(pureS.substring(length - K, length));
		return sb.toString();
	}
	
    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-')
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        return sb.reverse().toString().toUpperCase();
    }
    
}
