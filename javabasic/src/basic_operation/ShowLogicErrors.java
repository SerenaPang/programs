package basic_operation;

public class ShowLogicErrors {
	/**
	 * 逻辑错误是程序可以被运行但是想要的结果不是预期的结果
	 * 9/5 的结果是整数的1，但是正确的结果是有小数的所以不准确
	 * 应该精确到 9.0 / 5
	 * 
	 * */
	public static void main(String[] args) {
		System.out.println("Celsius 35 is Fahrenheit degree");
		System.out.println((9/5)*35 + 32);
	}

}
