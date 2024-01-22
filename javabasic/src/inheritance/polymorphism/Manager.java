package inheritance.polymorphism;
/**
 * A manager is a salaried employee who also receives a bonus
 * */
public class Manager extends SalariedEmployee{
	private double weeklyBonus;
	
	/**
	 * constructs a manager with a given name, annual salary and weekly bonus
	 * */
	public Manager(String name, double salary, double bonus) {
		super(name, salary);
		weeklyBonus = bonus;
	}
	
	public double weeklyPay(int hoursWorked) {
		return super.weeklyPay(hoursWorked) + weeklyBonus;
	}	
}
