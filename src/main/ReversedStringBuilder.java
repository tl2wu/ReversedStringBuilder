package src.main;
import java.util.Arrays;
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

	public int capacity() {
		return value.length;
	}
	
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

	public void setCharAt(int index, char ch) {
		if((index < 0) || (index >= length())) {
			throw new StringIndexOutOfBoundsException(index);
		}
		value[value.length - count + index] = ch;
	}
	
	public ReversedStringBuilder append(char c) {
		ensureCountIncrease(1);
		value[value.length - (++count)] = c;
		return this;
	}
	
	public ReversedStringBuilder append(char[] s) {
		ensureCountIncrease(s.length);
		count += s.length;
		System.arraycopy(s, 0, value, value.length-count, s.length);
		return this;
	}
	
	public ReversedStringBuilder append(char[] s, int start, int end) {
		boundsChecking(start, end, s.length);
		int appendLength = end - start;
		ensureCountIncrease(appendLength);
		
		count += appendLength;
		System.arraycopy(s, start, value, value.length-count, appendLength);
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
		
		boundsChecking(start, end, s.length());
		ensureCountIncrease(end - start);
		for(int i=end-1; i>=start; i--){
			value[value.length - (++count)] = s.charAt(i);
		}
		return this;
	}

	public ReversedStringBuilder append(Object obj) {
		return append(String.valueOf(obj));
	}

	public void trimToSize() {
		if (count < value.length) {
			value = Arrays.copyOfRange(value, value.length-count, value.length);
		}
	}
	
	public void setLength(int newLength) {
		if(newLength < 0) {
			throw new StringIndexOutOfBoundsException(newLength);
		}
		ensureCapacity(newLength);
		if(count < newLength) {
			System.arraycopy(value, value.length-count, value, value.length-newLength, count);
			Arrays.fill(value, value.length-newLength+count, value.length, '\0');
		} else {
			System.arraycopy(value, value.length-count, value, value.length-newLength, newLength);
		}
		count = newLength;
	}

	public ReversedStringBuilder insert(int offset, char c) {
		if ((offset < 0) || (offset > length())) {
			throw new StringIndexOutOfBoundsException(offset);
		}
		
		ensureCountIncrease(1);
		System.arraycopy(value, value.length-count, value, value.length-count-1, offset+1);
		value[offset] = c;
		count++;
		return this;
	}
	
	public ReversedStringBuilder insert(int offset, char[] s) {
		if ((offset < 0) || (offset > length())) {
			throw new StringIndexOutOfBoundsException(offset);
		}
		
		ensureCountIncrease(s.length);
		System.arraycopy(value, value.length-count, value, value.length-count-s.length, offset+1);
		count += s.length;
		System.arraycopy(s, 0, value, value.length+offset, s.length);
		return this;
	}
	
	public ReversedStringBuilder insert(int offset, char[] s, int start, int end) {
		if ((offset < 0) || (offset > length())) {
			throw new StringIndexOutOfBoundsException(offset);
		}
		
		ensureCountIncrease(s.length);
		System.arraycopy(value, value.length-count, value, value.length-count-s.length, offset+1);
		count += s.length;
		System.arraycopy(s, 0, value, value.length+offset, s.length);
		return this;
	}
	
	public ReversedStringBuilder insert(int dstOffset, CharSequence s) {
		if ((dstOffset < 0) || (dstOffset > length())) {
			throw new StringIndexOutOfBoundsException(dstOffset);
		}
		if(s == null) {
			s = "null";
		}
		
		ensureCountIncrease(s.length());
		System.arraycopy(value, value.length-count, value, value.length-count-s.length(), dstOffset+1);
		for(int i=0; i<s.length(); i++) {
			value[dstOffset++] = s.charAt(i);
		}
		count += s.length();
		return this;
	}
	
	public ReversedStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
		if ((dstOffset < 0) || (dstOffset > length())) {
			throw new StringIndexOutOfBoundsException(dstOffset);
		}
		if(s == null) {
			s = "null";
		}
		boundsChecking(start, end, s.length());
		
		int insertlength = end - start;
		ensureCountIncrease(insertlength);
		System.arraycopy(value, value.length-count, value, value.length-count-insertlength, insertlength);
		for(int i=start ; i<end; i++) {
			value[dstOffset++] = s.charAt(i);
		}
		count += insertlength;
		return this;
	}
	
	public ReversedStringBuilder clone() {
		ReversedStringBuilder clonedSb = new ReversedStringBuilder(this.length());
		clonedSb.append(this.value, value.length-count, value.length);
		return clonedSb;
	}
	
	/*
	private void arraycopyForward(char[] src, int srcPos, char[] dest, int destPos, int length) {
		for(int i=0; i<length; i++){
			 dest[destPos+i] = src[srcPos+i];
		}
	}
	
	private void arraycopyBackward(char[] src, int srcPos, char[] dest, int destPos, int length) {
		for(int i=length-1; i>=0; i--){
			dest[destPos+i] = src[srcPos+i];
		}
	}
	*/
	
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