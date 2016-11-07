package src.test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.main.ReversedStringBuilder;

public class ReversedStringBuilderTest {

	@Test
	public void appendCharTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		for(char c='0'; c<='9'; c++) {
			rsb.append(c);
			sb.insert(0, c);
			
			assert(isCharSequenceEqual(rsb, sb));
		}
	}
	
	@Test
	public void appendCharArrayTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		char[] array1 = new char[]{'g'};
		char[] array2 = new char[]{'c', 'd','e','f'};
		char[] array3 = new char[]{'a','b'};
		rsb.append(array1);
		sb.append(array3);
		assert(isCharSequenceEqual(rsb, sb));
		rsb.append(array2);
		sb.append(array2);
		assert(isCharSequenceEqual(rsb, sb));
		rsb.append(array3);
		sb.append(array1);
		assert(isCharSequenceEqual(rsb, sb));
	}

	@Test
	public void appendCharSequenceTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s1 = "g";
		String s2 = "cdef";
		String s3 = "ab";
		rsb.append(s1);
		sb.append(s3);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s2);
		sb.append(s2);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s3);
		sb.append(s1);
		assert(isCharSequenceEqual(sb, rsb));
	}
	
	@Test
	public void appendCharSequenceWithIndexesTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s1 = "ui67";
		String s2 = "45kl";
		String s3 = "aa123b";
		rsb.append(s1, 2, 4);
		sb.append(s3, 2, 5);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s2, 0, 2);
		sb.append(s2, 0, 2);
		assert(isCharSequenceEqual(sb, rsb));
		rsb.append(s3, 2, 5);
		sb.append(s1, 2, 4);
		assert(isCharSequenceEqual(sb, rsb));
	}
	
	public boolean isCharSequenceEqual(CharSequence s1, CharSequence s2){
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
