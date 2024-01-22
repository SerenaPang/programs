package interfaces;

//a country with a name and area
public class Country implements Measurable{
	private String name;
	private double area;
	
	//construct a country
	public Country(String aName, double anArea) {
		name = aName;
		area = anArea;
	}
	
	//gets the country name
	public String getName() {
		return name;
	}
	
	//gets the area of the country
	public double getArea() {
		return area;
	}

	@Override
	public double getMeasure() {
		return area;
	}

}
