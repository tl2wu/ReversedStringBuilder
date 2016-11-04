import java.lang.RuntimeException;

public class ReversedStringBuilder 
	implements Appendable, CharSequence 
{
	
	private char[] value;
	
	private int count = 0; 

	public ReversedStringBuilder(int capacity) {
		value = new char[capacity];
	}

	public ReversedStringBuilder() {
		this(16);
	}

	public ReversedStringBuilder(CharSequence seq) {
		this(seq.length() + 16);
		append(seq);
	}

	public char charAt(int index) {
		if ((index < 0) || (index >= count)) {
			throw new StringIndexOutOfBoundsException(index);
		}
		return value[value.length - count + index];
	}

	/*
	public default IntStream chars() {
		return this.toString().chars();
	}

	public default IntStream codePoints() {
		return this.toString().codePoints();
	}
	*/

	public int length() {
		return count;
	}

	public CharSequence subSequence(int start, int end) {
		return substring(start, end);
	}

	public String substring(int start) {
		return substring(start, count);
	}

	public String substring(int start, int end) {
		boundsChecking(start, end, count);
		return new String(value, value.length-count+start, end-start);
	}

	public String toString() {
		return new String(value, value.length-count, count);
	}

	public ReversedStringBuilder append(char c) {
		ensureCountIncrease(1);
		value[value.length - (++count)] = c;
		return this;
	}

	public ReversedStringBuilder append(CharSequence s) {
		if(s == null){
			s = "null";
		}
		return this.append(s, 0, s.length());	
	}

	public ReversedStringBuilder append(CharSequence s, int start, int end) {
		if (s == null) {
			s = "null";
		}
		int len = end - start;
		boundsChecking(start, end, len);
		ensureCountIncrease(len);
		for(int i=1; i<=s.length(); i++){
			value[value.length - (++count)] = s.charAt(s.length() - i);
		}
		return this;
	}

	public ReversedStringBuilder append(Object obj) {
		return append(String.valueOf(obj));
	}

	private void boundsChecking(int start, int end, int len) {
		if ((start < 0) || (start > end) || (end > len)) {
			throw new IndexOutOfBoundsException("start " + start + ", end" + end + ", len " + len);
		}
	}

	private void ensureCountIncrease(int increase) {
		if (Integer.MAX_VALUE-count < increase) {
			throw new RuntimeException("Exceeds Maximum Capacity");
		}
		int minimumCapacity = increase + count;
		ensureCapacity(minimumCapacity);
	}

	private void ensureCapacity(int minimumCapacity) {
		if (minimumCapacity > value.length) {
			expandCapacity(minimumCapacity);
		}
	}

	private void expandCapacity(int minimumCapacity) {
		int newCapacity = (value.length + 1) * 2;
		if (newCapacity < 0) {
			newCapacity = Integer.MAX_VALUE;
		} else if (minimumCapacity > newCapacity) {
			newCapacity = minimumCapacity;
		}

		char[] newValue = new char[newCapacity];
		for(int i=1; i<=count; i++) {
			newValue[newValue.length - i] = value[value.length - i];
		}
		value = newValue;
	}
}
