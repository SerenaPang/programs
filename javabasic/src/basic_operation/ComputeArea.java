package basic_operation;
/**
 * 声明变量的数据类型，变量名应该描述性让人一看就知道是什么
 * 实数是小数（浮点数）用double表示
 * 求半径
 * */
public class ComputeArea {
	public static void main(String[] args) {
		double radius;
		double area;
		//PI 是常量
		final double PI = 3.14159;
		
		radius = 20;
		//area = radius * radius * 3.14159;
		area = radius * radius * PI;
		
		System.out.println("The area for the circle of radius " + radius + " is " + area);
	}
}
