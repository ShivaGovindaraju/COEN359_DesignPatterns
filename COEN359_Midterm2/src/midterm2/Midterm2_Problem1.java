package midterm2;

import java.util.*; import java.io.*;

interface Building {
	//Proxy:Subject
	public void addVisitor(String visitorName);
	public void showCurrentVisitors();
}

class ScientificResearchBuilding implements Building {
	//Proxy:RealSubject
	int building_num;
	List<String> visitor_list;

	public ScientificResearchBuilding(int building_num) {
		this.building_num = building_num;
		this.visitor_list = new ArrayList<String>();
	}
	
	public void addVisitor(String visitorName) {
		System.out.println("-Adding Visitor " + visitorName + " to Scientific Research Building " + this.building_num);
		this.visitor_list.add(visitorName);
	}
	
	public void showCurrentVisitors() {
		System.out.println("-Displaying Current Visitors of Scientific Research Building " + this.building_num);
		for (String visitor: this.visitor_list) {
			System.out.println(visitor);
		}
	}
}

class AdvancedResearchBuilding implements Building {
	//Proxy:Proxy
	int building_num;
	ScientificResearchBuilding srb;
	
	public AdvancedResearchBuilding(int building_num) {
		this.building_num = building_num;
		this.srb = new ScientificResearchBuilding(building_num);
	}
	
	public void addVisitor(String visitorName) {
		//This is where the security check is enforced to limit access to the building
		System.out.println("Adding Visitor " + visitorName + " to Advanced Research Building " + this.building_num);
		if (this.srb.visitor_list.size() < 3) {
			this.srb.addVisitor(visitorName);
		} else {
			System.out.println("Security Check Failed. Advanced Research Building " + this.building_num + " already has 3 Visitors.");
		}
	}
	
	public void showCurrentVisitors() {
		System.out.println("Displayng Current Visitors of Advanced Reseach Building " + this.building_num);
		this.srb.showCurrentVisitors();
	}
}

public class Midterm2_Problem1 {

	public Midterm2_Problem1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		System.out.println("---COEN 359 Midterm 2 Problem 1 - Shiva Govindaraju ---");
		//To test the implementation, we shall create an AdvancedResearchBuilding and attempt to add 5 Visitors to it.
		//The first 3 Visitors should be properly added. The final 2 will fail to be added.
		//We shall display the current visitors of the ARB after attempting to add all 5, proving that only 3 visitors are there.
		System.out.println("\n--Initiliaizing new AdvancedResearchBuilding (building number 12345)...");
		Building arb = new AdvancedResearchBuilding(12345);
		System.out.println("\n--Attempt to Add Five (5) Visitors to the Advanced ResarchBuilding...");
		arb.addVisitor("Abraham Alpha");
		arb.addVisitor("Betty Beta");
		arb.addVisitor("Chris Charley");
		arb.addVisitor("David Delta");
		arb.addVisitor("Emma Epsilon");
		System.out.println("\n--Display Current Visitors in Advanced Research Building to prove security check operation...");
		arb.showCurrentVisitors();
	}

}
