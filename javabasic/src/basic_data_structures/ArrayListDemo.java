package basic_data_structures;
/**
 * This program demonstrates the array list implememntation
 * */
public class ArrayListDemo {
	public static void main(String[] args) {
		ArrayList staff = new ArrayList();
		staff.addLast("Diana");
		staff.addLast("Harry");
		for (int i = 1; i <= 10; i++) {
			staff.addLast("Tom" + i);
		}
		staff.add(0,"Juliet");
		staff.remove(1);
		for (int i = 0; i <staff.size(); i++) {
			System.out.println(staff.get(i));
		}
	}
}
