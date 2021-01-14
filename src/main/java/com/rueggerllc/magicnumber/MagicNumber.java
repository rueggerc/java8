package com.rueggerllc.magicnumber;

public class MagicNumber {
	
	private String value;
	
	public MagicNumber(String value) {
		this.value = value;
	}
	
	public boolean isMagicNumber() {
		return isMagicNumber(value);
	}
	
	private boolean isMagicNumber(String value) {
		System.out.println("CHECK=" + value);
		if (value.length() == 1) {
			if (value.equals("1")) {
				return true;
			} else {
				return false;
			}
		} else {
			String nextValue = getSum(value);
			return isMagicNumber(nextValue);
		}
	}
	
	public void getDigits(int n) {
		while (n > 9) {
			int digit = (n % 10);
			System.out.println(digit);
			n = n / 10;
		}
		System.out.println(n);
	}
	
	
	public void getDigits(String n) {
		for (int i = 0; i < n.length(); i++) {
			int digit = Integer.parseInt(""+value.charAt(i));
			System.out.println(digit);
		}
	}
	
	
	
	private String getSum(String value) {
		int sum = 0;
		for (int i = 0; i < value.length(); i++) {
			int digit = Integer.parseInt(""+value.charAt(i));
			sum += digit;
		}
		System.out.println("SUM="+sum);
		return String.valueOf(sum);
	}
	
	
	public static void main(String[] args) {
		
		String foo = "ab3";
		int x = Integer.parseInt(""+foo.charAt(2));
		
		MagicNumber magic = new MagicNumber("50113");
		magic.getDigits("50113");
		// System.out.println(magic.isMagicNumber());
	}
	
	
}
