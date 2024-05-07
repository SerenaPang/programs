package org.corejava.ch9.processinginputoutput;

public class Data {
	private int id; // 4 bytes
	private long zip;// 8 bytes
	private int num;// 4 bytes
	String name;//4 bytes
	
	public Data(int id, long zip, int num, String name) {
		this.id = id;
		this.zip = zip;
		this.num = num;
	    this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		 return "Data [id= " + id + ", zip= " + zip + ", num= " + num + "]" + ", name= " + name + "]";
		//return "Data [id=" + id + ", zip=" + zip + ", num=" + num + "]";
	}
}
