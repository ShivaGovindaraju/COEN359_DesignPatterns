package assignment3;

import java.util.*;

interface LabResource {
    //Composite:Component
	public double getAnnualCost();
	public void addResource(LabResource resource);
	public void removeResource(LabResource resource);
	public void showResourceDetails(int currentyear);
}

class Workstation implements LabResource {
    //Composite:Leaf
	String resourceId;
	int modelyear;
	String status;
	double annualcost;
	
	public Workstation(String resourceId, int modelyear, String status, double annualcost) {
		this.resourceId = resourceId;
		this.modelyear = modelyear;
		this.status = status;
		this.annualcost = annualcost;
	}
	
	public double getAnnualCost() {
		return this.annualcost;
	}
	
    //not implemented in Leaf
	public void addResource(LabResource resource) {}
	public void removeResource(LabResource resource) {}
	
	public void showResourceDetails(int currentyear) {
		if (currentyear > this.modelyear) {
			this.status = "To Be Replaced";
		}
		System.out.println("WorkStation ResourceID: " + this.resourceId
				+ ", Model Year: " + this.modelyear + ", Annual Maintenace Cost: $"
				+ this.annualcost + ", Status: " + this.status);
	}
}

class Printer implements LabResource {
    //Component:Leaf
	String resourceId;
	int modelyear;
	String status;
	double annualcost;
	
	public Printer(String resourceId, int modelyear, String status, double annualcost) {
		this.resourceId = resourceId;
		this.modelyear = modelyear;
		this.status = status;
		this.annualcost = annualcost;
	}
	
	public double getAnnualCost() {
		return this.annualcost;
	}
	
    //not implemented in Leaf
	public void addResource(LabResource resource) {}
	public void removeResource(LabResource resource) {}
	
	public void showResourceDetails(int currentyear) {
		if (currentyear > this.modelyear) {
			this.status = "To Be Replaced";
		}
		System.out.println("Printer ResourceID: " + this.resourceId
				+ ", Model Year: " + this.modelyear + ", Annual Maintenace Cost: $"
				+ this.annualcost + ", Status: " + this.status);
	}
}

class ComputerCluster implements LabResource {
	//Composite:Composite
    String resourceId;
	double annualcost;
	List<LabResource> resources;
	
	public ComputerCluster(String resourceId) {
		this.resourceId = resourceId;
		this.annualcost = 0.0;
		this.resources = new ArrayList<LabResource>();
		this.getAnnualCost();
	}
	
	public double getAnnualCost() {
		double cost = 0.0;
		for (LabResource res: this.resources) {
			cost += res.getAnnualCost();
		}
		return cost;
	}
	
	public void addResource(LabResource resource) {
		this.resources.add(resource);
		this.annualcost = this.getAnnualCost();
	}
	
	public void removeResource(LabResource resource) {
		this.resources.remove(resource);
		this.annualcost = this.getAnnualCost();
	}
	
	public void showResourceDetails(int currentyear) {
		System.out.println("ComputerCluster Resource ID: " + this.resourceId + ", Cumulative Annual Maintenance Cost: $" + this.annualcost);
		System.out.println("--");
		for (LabResource res: resources) {
			res.showResourceDetails(currentyear);
		}
		System.out.println("--");
	}
}

public class COEN_Assign3 {
    //Composite:Client
	public static void main(String[] args) {
		System.out.println("---COEN359 Spring 2021 Assignment 3 - Shiva Govindaraju---");
		System.out.println("Building Lab Resources to test Composite Pattern...");
		LabResource wrkstn1 = new Workstation("Workstation1", 2015, "", 15.50);
		LabResource wrkstn2 = new Workstation("Workstation2", 2019, "", 15.0);
		LabResource print1 = new Printer("Printer1", 2018, "", 10.25);
		LabResource print2 = new Printer("Printer2", 2020, "", 9.50);
		LabResource clusterA = new ComputerCluster("ClusterA");
		LabResource clusterB = new ComputerCluster("ClusterB");
		clusterA.addResource(wrkstn1);
		clusterA.addResource(wrkstn2);
		clusterA.addResource(print1);
		clusterB.addResource(print2);
		clusterB.addResource(clusterA);
		System.out.println("Resources instantiated.");
		System.out.println("Demonstrating cluster navigation by displaying all details.");
		System.out.println("Declaring current year to be 2019 for testing status-checks.");
		System.out.println("Begining Test...\n");
		clusterB.showResourceDetails(2019);
		System.out.println("\nTest Complete.");
	}
}
