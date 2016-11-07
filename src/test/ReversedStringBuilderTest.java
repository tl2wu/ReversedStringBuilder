package src.test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.main.ReversedStringBuilder;

public class ReversedStringBuilderTest {

	@Test
	public void appendCharTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		// append all 128 chars in ascii
		for(char c=0; c<128; c++) {
			rsb.append(c);
			sb.insert(0, c);
			assert(isCharSequenceEqual(rsb, sb));
		}
	}
	
	@Test
	public void appendCharArrayTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		char[] array1 = new char[]{};
		char[] array2 = new char[]{'b', 'c', 'd','e','f'};
		char[] array3 = new char[]{'a'};
		
		rsb.append(array1);
		sb.insert(0, array1);
		assert(isCharSequenceEqual(rsb, sb));
		rsb.append(array2);
		sb.insert(0, array2);
		assert(isCharSequenceEqual(rsb, sb));
		rsb.append(array3);
		sb.insert(0, array3);
		assert(isCharSequenceEqual(rsb, sb));
	}

	@Test
	public void appendCharSequenceTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s1 = "";
		String s2 = "bcdef";
		String s3 = "a";
		
		rsb.append(s1);
		sb.insert(0, s1);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s2);
		sb.insert(0, s2);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s3);
		sb.insert(0, s3);
		assert(isCharSequenceEqual(sb, rsb));
		String s4 = null;
		rsb.append(s4);
		sb.insert(0, s4);
		assert(isCharSequenceEqual(sb, rsb));
	}
	
	@Test
	public void appendCharSequenceWithIndexesTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s0 = "";
		String s1 = "ui67";
		String s2 = "45kl";
		String s3 = "aa123b";
		String s4 = "8";
		String s5 = "";
		
		rsb.append(s0, 0, 0);
		sb.append(s0);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s1, 2, 4);
		sb.insert(0, s1.substring(2, 4));
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s2, 0, 2);
		sb.insert(0, s2.substring(0, 2));
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s3, 2, 5);
		sb.insert(0, s3.substring(2, 5));
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s4, 0, 1);
		sb.insert(0, s4.substring(0, 1));
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s5, 0, 0);
		sb.insert(0, s5.substring(0, 0));
		assert(isCharSequenceEqual(sb, rsb));
	}
	
	@Test
	public void appendRandomlyTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s = "abcdefghijklmnopqrstuvwxyz";
		for(int i=0; i<s.length(); i++){
			rsb.append(s.charAt(i));
			sb.insert(0, s.charAt(i));
			assert(isCharSequenceEqual(sb, rsb));
			rsb.append(s.substring(0, i));
			sb.insert(0, s.substring(0, i));
			assert(isCharSequenceEqual(sb, rsb));
			rsb.append(s.substring(i, s.length()).toCharArray());
			sb.insert(0, s.substring(i, s.length()).toCharArray());
			assert(isCharSequenceEqual(sb, rsb));
		}
	}
	
	@Test
	public void substringANDsubSequenceTest(){
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		String s = "abcdefghijklmnopqrstuvwxyz";
		int len = s.length();
		rsb.append(s.charAt(25));
		rsb.append(s.substring(2, 25));
		rsb.append(s.substring(0, 2).toCharArray());
		assert(isCharSequenceEqual(s, rsb));
		for(int i=0; i<len; i++){
			// s[0...len-1]
			assertEquals(rsb.substring(i), s.substring(i));
			// s[0...i]
			assertEquals(rsb.substring(0, i+1), s.substring(0, i+1));
			assertEquals(rsb.subSequence(0, i+1), s.substring(0, i+1));
			// s[i...len-1]
			assertEquals(rsb.substring(i, len), s.substring(i, len));
			assertEquals(rsb.subSequence(i, len), s.substring(i, len));
		}
	}
	
	public boolean isCharSequenceEqual(CharSequence s1, CharSequence s2) {
		if(s1.length() != s2.length()){
			return false;
		}
		for(int i=0; i<s1.length(); i++){
			if(s1.charAt(i) != s2.charAt(i)){
				return false;
			}
		}
		return s1.toString().equals(s2.toString());
	}
}