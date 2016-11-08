package src.test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.main.ReversedStringBuilder;

public class ReversedStringBuilderTest {
	
	public static final String enAlphabet = "abcdefghijklmnopqrstuvwxyz";

	@Test
	public void appendCharTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		// append all 128 chars in ascii
		for(char c=0; c<128; c++) {
			rsb.append(c);
			sb.insert(0, c);
			assertTrue(isCharSequenceEqual(rsb, sb));
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
		assertTrue(isCharSequenceEqual(rsb, sb));
		rsb.append(array2);
		sb.insert(0, array2);
		assertTrue(isCharSequenceEqual(rsb, sb));
		rsb.append(array3);
		sb.insert(0, array3);
		assertTrue(isCharSequenceEqual(rsb, sb));
	}
	
	@Test
	public void appendCharArrayWithIndexesTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s0 = "";
		String s1 = "ui67";
		String s2 = "45kl";
		String s3 = "aa123b";
		String s4 = "8";
		String s5 = "";
		
		rsb.append(s0.toCharArray(), 0, 0);
		sb.append(s0);
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s1.toCharArray(), 2, 4);
		sb.insert(0, s1.substring(2, 4));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s2.toCharArray(), 0, 2);
		sb.insert(0, s2.substring(0, 2));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s3.toCharArray(), 2, 5);
		sb.insert(0, s3.substring(2, 5));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s4.toCharArray(), 0, 1);
		sb.insert(0, s4.substring(0, 1));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s5.toCharArray(), 0, 0);
		sb.insert(0, s5.substring(0, 0));
		assertTrue(isCharSequenceEqual(sb, rsb));
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
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s2);
		sb.insert(0, s2);
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s3);
		sb.insert(0, s3);
		assertTrue(isCharSequenceEqual(sb, rsb));
		String s4 = null;
		rsb.append(s4);
		sb.insert(0, s4);
		assertTrue(isCharSequenceEqual(sb, rsb));
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
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s1, 2, 4);
		sb.insert(0, s1.substring(2, 4));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s2, 0, 2);
		sb.insert(0, s2.substring(0, 2));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s3, 2, 5);
		sb.insert(0, s3.substring(2, 5));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s4, 0, 1);
		sb.insert(0, s4.substring(0, 1));
		assertTrue(isCharSequenceEqual(sb, rsb));
		rsb.append(s5, 0, 0);
		sb.insert(0, s5.substring(0, 0));
		assertTrue(isCharSequenceEqual(sb, rsb));
	}
	
	@Test
	public void appendRandomlyTest() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		StringBuilder sb = new StringBuilder();
		String s = enAlphabet;
		for(int i=0; i<s.length(); i++){
			rsb.append(s.charAt(i));
			sb.insert(0, s.charAt(i));
			assertTrue(isCharSequenceEqual(sb, rsb));
			rsb.append(s.substring(0, i));
			sb.insert(0, s.substring(0, i));
			assertTrue(isCharSequenceEqual(sb, rsb));
			rsb.append(s.substring(i, s.length()).toCharArray());
			sb.insert(0, s.substring(i, s.length()).toCharArray());
			assertTrue(isCharSequenceEqual(sb, rsb));
		}
	}
	
	@Test
	public void substringANDsubSequenceTest(){
		ReversedStringBuilder rsb = getEnAlphabetReversedStringBuilder();
		StringBuilder s = new StringBuilder(enAlphabet);
		int len = enAlphabet.length();
		assertTrue(isCharSequenceEqual(s, rsb));
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
	
	@Test
	public void setCharAtTest() {
		ReversedStringBuilder rsb = getEnAlphabetReversedStringBuilder();
		StringBuilder sb = new StringBuilder(enAlphabet);
		int len = enAlphabet.length();
		
		// test success setCharAt cases
		char c='1';
		for(int i=0; i<len; i++,c++) {
			rsb.setCharAt(i, c);
			sb.setCharAt(i, c);
			assertTrue(isCharSequenceEqual(rsb, sb));
		}

		// test fail setCharAt cases, make sure exceptions are thrown
		boolean thrown = false;
		try{
			rsb.setCharAt(-1, 'a');
		} catch (StringIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try{
			rsb.setCharAt(len, 'a');
		} catch (StringIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void setLengthTest() {
		// test success setLength() cases
		for(int newLength=0; newLength<=enAlphabet.length(); newLength++) {
			ReversedStringBuilder rsb = getEnAlphabetReversedStringBuilder();
			StringBuilder sb = new StringBuilder(enAlphabet);
			rsb.setLength(newLength);
			sb.setLength(newLength);
			assertTrue(isCharSequenceEqual(rsb, sb));
		}
		
		// test failed setLength() cases, make sure exceptions are thrown
		ReversedStringBuilder rsb = getEnAlphabetReversedStringBuilder();
		boolean thrown = false;
		try{
			rsb.setLength(-1);
		} catch (StringIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
		rsb = getEnAlphabetReversedStringBuilder();
		thrown = false;
		try{
			rsb.setCharAt(rsb.length(), 'a');
		} catch (StringIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void cloneTest() {
		ReversedStringBuilder rsb = getEnAlphabetReversedStringBuilder();
		ReversedStringBuilder rsbCloned = rsb.clone();
		assertTrue(isCharSequenceEqual(rsb, rsbCloned));
		
		rsb = new ReversedStringBuilder();
		rsbCloned = rsb.clone();
		assertTrue(isCharSequenceEqual(rsb, rsbCloned));
		
		rsb = new ReversedStringBuilder("");
		rsbCloned = rsb.clone();
		assertTrue(isCharSequenceEqual(rsb, rsbCloned));
	}
	
	@Test
	public void enAlphabetTest() {
		ReversedStringBuilder rsb = getEnAlphabetReversedStringBuilder();
		assertTrue(isCharSequenceEqual(rsb, enAlphabet));
	}
	
	public static ReversedStringBuilder getEnAlphabetReversedStringBuilder() {
		ReversedStringBuilder rsb = new ReversedStringBuilder();
		int len = enAlphabet.length();
		rsb.append(enAlphabet.charAt(len-1));
		rsb.append(enAlphabet.substring(2, len-1));
		rsb.append(enAlphabet.substring(0, 2).toCharArray());
		
		return rsb;
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