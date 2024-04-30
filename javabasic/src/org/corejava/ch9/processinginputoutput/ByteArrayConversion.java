package org.corejava.ch9.processinginputoutput;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteArrayConversion {
	/**
	 * test the conversion method
	 */
	public void test(Data data) {
		int id = data.getId();
		long zip = data.getZip();
		// String name = data.getName();
		// convert the id to a byte array
		byte[] byteArrayId = intToByteArray(id);
		int byteId = byteArrayToint(byteArrayId);
		System.out.println("Id: " + byteId);
		System.out.println("byteId len: " + byteArrayId.length);

		// convert string to byte array
		String name = "hi";
		byte[] byteArrayName = stringToByteArray(name);
		System.out.println("byteArrayName len: " + byteArrayName.length); // prints "11"
		String s = byteArrayToString(byteArrayName);
		System.out.println("name: " + s);

		// convert long to byte array
		byte[] byteArrayZip = longToBytes(zip);
		long lng = bytesToLong(byteArrayZip);
		System.out.println("zip: " + lng);
		System.out.println("byteArrayZip len: " + byteArrayZip.length);
	}

	/**
	 * convert a long to byte array
	 */
	private byte[] longToBytes(long lng) {
		byte[] b = new byte[] { (byte) lng, (byte) (lng >> 8), (byte) (lng >> 16), (byte) (lng >> 24),
				(byte) (lng >> 32), (byte) (lng >> 40), (byte) (lng >> 48), (byte) (lng >> 56) };
		return b;
	}

	/**
	 * Convert a string to a byte array
	 */
	private byte[] stringToByteArray(String string) {
		// Check encoded sizes
		byte[] byteArray;
		try {
			byteArray = string.getBytes("UTF-8");

			// System.out.println(byteArray.length); // prints "11"
			return byteArray;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * convert an integer number to a 4 byte array
	 */
	private byte[] intToByteArray(int value) {
//		for (byte b : byteArrayId) {
//			System.out.format("0x%x ", b);
//		}
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
		// or use byte buffer
		// return ByteBuffer.allocate(4).putInt(yourInt).array();
	}

	/**
	 * convert a byte array to long
	 */
	private long bytesToLong(byte[] bytes) {
		long l = ((long) bytes[7] << 56) | ((long) bytes[6] & 0xff) << 48 | ((long) bytes[5] & 0xff) << 40
				| ((long) bytes[4] & 0xff) << 32 | ((long) bytes[3] & 0xff) << 24 | ((long) bytes[2] & 0xff) << 16
				| ((long) bytes[1] & 0xff) << 8 | ((long) bytes[0] & 0xff);
		return l;
	}

	/**
	 * convert a byte array to a string
	 */
	private String byteArrayToString(byte[] byteArray) {
		String str = new String(byteArray, StandardCharsets.UTF_8);
		// String str = new String(byteArray, 0, 3, StandardCharsets.UTF_8);
		return str;
	}

	/**
	 * convert a byte array to a integer number
	 */
	private int byteArrayToint(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	/**
	 * convert a byte array to a integer number. packing an array of 4 bytes to an
	 * int, big endian, minimal parentheses operator precedence: <<, &, | when
	 * operators of equal precedence (here bitwise OR) appear in the same
	 * expression, they are evaluated from left to right
	 */
	private int fromByteArray(byte[] bytes) {
		return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
	}

	public static void main(String args[]) {
		ByteArrayConversion byteArrayConversion = new ByteArrayConversion();

	}
}
