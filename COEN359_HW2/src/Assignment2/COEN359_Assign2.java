package Assignment2;

import java.util.*; import java.io.*;

interface ProjectProduct {
	public String getProjectDetails();
}

enum ProjectStatus { Active, Canceled, Complete }

class Project implements ProjectProduct {
	String projectID;
	ProjectStatus status;
	List<Task> tasks;
	int numCompleted;
	
	public Project(String projectID) {
		this.projectID = projectID;
		this.tasks = new ArrayList<Task>();
		this.numCompleted = 0;
	}

	/**
	 * @return the numCompleted
	 */
	public int getNumCompleted() {
		return numCompleted;
	}

	/**
	 * @param numCompleted the numCompleted to set
	 */
	public void setNumCompleted(int numCompleted) {
		this.numCompleted = numCompleted;
	}

	/**
	 * @return the status
	 */
	public ProjectStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * @param task
	 */
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	/**
	 * @param taskID
	 * @return the Task corresponding to taskID
	 */
	public Task findTask(String taskID) {
		for (Task t: this.tasks) {
			if (t.getTaskID().equalsIgnoreCase(taskID)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * @return String with Project Details
	 */
	public String getProjectDetails() {
		String out = "--Project ID: " + this.getProjectID() + "\n";
		out += "--No. of Tasks: " + this.getTasks().size() + "\n\n";
		for (Task task : this.getTasks()) {
			out += task.getTaskDetails() + "\n";
		}
		return out;
	}
}

class Task {
	String taskID;
	String projectID;
	String startDate;
	String endDate;
	List<Employee> employees;
	
	public Task (String taskID, String projectID) {
		this.taskID = taskID;
		this.projectID = projectID;
		this.startDate = "";
		this.endDate = "";
		this.employees = new ArrayList<Employee>();
	}

	/**
	 * @return the taskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * Adds Employess to Task's employee list
	 * @param employee
	 */
	public void addEmployeeToTask(Employee employee) {
		this.employees.add(employee);
	}
	
	/**
	 * @return whether Task is complete or not
	 */
	public boolean isComplete() {
		if (this.endDate != "") {
				return true;
		}
		return false;
	}
	
	/**
	 * @return number of Staff Employees assigned to Task.
	 */
	public int getNumStaff() {
		int staff = 0;
		for (Employee emp: this.getEmployees()) {
			if (emp.getStatus() == EmployeeStatus.Staff) {
				staff += 1;
			}
		}
		return staff;
	}
	
	/**
	 * @return number of Contract Employees assigned to Task.
	 */
	public int getNumContract() {
		int contract = 0;
		for (Employee emp: this.getEmployees()) {
			if (emp.getStatus() == EmployeeStatus.Contract) {
				contract += 1;
			}
		}
		return contract;
	}
	
	/**
	 * @return String with Task Details.
	 */
	public String getTaskDetails() {
		String out = "Task ID: " + this.getTaskID() + "\n";
		out += "Start Date: " + this.getStartDate() + "\n";
		out += "Completed Date: " + this.getEndDate() + "\n";
		out += "No. of Staff Employees: " + this.getNumStaff() + "\n";
		out += "No. of Contract Employees: " + this.getNumContract() + "\n";
		out += "Employees assigned to Task:\n";
		for (Employee emp: this.getEmployees()) {
			out += emp.getName() + ",";
		}
		out += "\n";
		return out;
	}
}

enum EmployeeStatus { Staff, Contract }

class Employee {
	String taskID;
	String name;
	String email;
	EmployeeStatus status;
	
	public Employee (String taskID, String name, String email) {
		this.taskID = taskID;
		this.name = name;
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public EmployeeStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	/**
	 * @return the taskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
}

class InputCompilerFactory {
	public List<Project> generateProjects() { return null; }
	public void populateProjectsList() {}
}

class TextInputCompilerFactory extends InputCompilerFactory {
	List<Project> projects;
	
	public TextInputCompilerFactory() {
		this.projects = new ArrayList<Project>();
	}
	
	/**
	 * Generates and returns a List<Project> from the input text file.
	 */
	public List<Project> generateProjects(){
		this.populateProjectsList();
		return this.projects;
	}
	
	/**
	 * Populates the projects field with a List<Project> based on the input text file.
	 */
	public void populateProjectsList() {
		try {
			File input = new File("src/Assignment2/input.txt");
			Scanner insc = new Scanner(input);
			while (insc.hasNextLine()) {
				String line = insc.nextLine();
				
				if (line.charAt(0) != '-') {
					//Input file line is NOT a comment. Do something.
					String[] lparam = line.split("\\s*,\\s*");
					int param_num = lparam.length;
					if (param_num == 2) {
						this.cancelProject(lparam[0]);
					} else if (param_num == 3) {
						this.startTask(lparam[0], lparam[1], lparam[2]);
					} else if (param_num == 4) {
						this.endTask(lparam[0], lparam[1], lparam[2], lparam[3]);
					} else if (param_num == 5) {
						this.addTaskEmployee(lparam[0], lparam[1], lparam[2], lparam[3], lparam[4]);
					}
				}
			}
			insc.close();
		} catch (FileNotFoundException e) {
			System.out.println("An Error Occured...");
			e.printStackTrace();
		}
	}
	
	/**
	 * @param projectID
	 * @return Project corresponding to projectID
	 */
	public Project findProject(String projectID) {
		for (Project p: this.projects) {
			if (p.getProjectID().equalsIgnoreCase(projectID)) {
				return p;
			}
		}
		//return null;
		this.addProject(projectID);
		return this.findProject(projectID);
	}
	
	/**
	 * Adds new Project with the appropriate project ID.
	 * @param projectID
	 */
	public void addProject(String projectID) {
		this.projects.add(new Project(projectID));
	}
	
	/**
	 * Cancels the Project with the appropriate project ID.
	 * @param projectID
	 */
	public void cancelProject(String projectID) {
		Project p = this.findProject(projectID);
		p.setStatus(ProjectStatus.Canceled);
	}
	
	/**
	 * Stats a Task within the appropriate Project.
	 * @param taskID
	 * @param projectID
	 * @param startDate
	 */
	public void startTask(String taskID, String projectID, String startDate) {
		Project proj = this.findProject(projectID);
		Task task = proj.findTask(taskID);
		if (task == null) {
			proj.addTask(new Task(taskID, projectID));
			task = proj.findTask(taskID);
		}
		task.setStartDate(startDate);
		if (task.getEndDate() == "") {
			proj.setStatus(ProjectStatus.Active);
		}
	}
	
	/**
	 * Ends a Task wtihin the appropriate Project
	 * @param taskID
	 * @param projectID
	 * @param startDate
	 * @param endDate
	 */
	public void endTask(String taskID, String projectID, String startDate, String endDate) {
		Project proj = this.findProject(projectID);
		Task task = proj.findTask(taskID);
		if (task == null) {
			proj.addTask(new Task(taskID, projectID));
			task = proj.findTask(taskID);
		}
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		proj.setNumCompleted(proj.getNumCompleted() + 1);
		if (proj.getNumCompleted() == proj.getTasks().size()) {
			proj.setStatus(ProjectStatus.Complete);
		}
	}
	
	/**
	 * Adds an Employee to a specific Task.
	 * @param taskID
	 * @param projectID
	 * @param name
	 * @param email
	 * @param empStatus
	 */
	public void addTaskEmployee(String taskID, String projectID, String name, String email, String empStatus) {
		Project proj = this.findProject(projectID);
		Task task = proj.findTask(taskID);
		if (task == null) {
			proj.addTask(new Task(taskID, projectID));
			task = proj.findTask(taskID);
		}
		Employee emp = new Employee(taskID, name, email);
		if (empStatus.equalsIgnoreCase("contract")) {
			emp.setStatus(EmployeeStatus.Contract);
		} else {
			emp.setStatus(EmployeeStatus.Staff);
		}
		task.addEmployeeToTask(emp);
	}
}

class XMLInputCompilerFactory extends InputCompilerFactory {
	public List<Project> generateProjects() {
		return null;
	}
	public void populateProjectsList() {}
}

class RDBMSInputCompilerFactory extends InputCompilerFactory {
	public List<Project> generateProjects() {
		return null;
	}
	public void populateProjectsList() {}
}

class OutputGenerator {
	public void getProjectList(List<Project> projects) {}
	public void makeNewOutputFile() {}
	public void writeToOutput() {}
}

class TextOutputGenerator extends OutputGenerator {
	List<Project> projects;
	boolean appendOutput;
	
	public TextOutputGenerator(){
		this.projects = new ArrayList<Project>();
		this.appendOutput = true;
	}

	public TextOutputGenerator(List<Project> projects) {
		this.projects = projects;
	}
	
	/**
	 * Sets the TextOutputGenerator projects field with the given List<Project>
	 * @param projects
	 */
	public void getProjectList(List<Project> projects) {
		this.projects = projects;
	}
	
	/**
	 * Checks is output.txt already exists or not
	 * If it exists, the TextOutputGenerator is configured to overwrite it.
	 * If it does not exist, it is created.
	 */
	public void makeNewOutputFile() {
		try {
			File output = new File("src/Assignment2/output.txt");
			if (output.createNewFile()) {
				System.out.println("Made New Output File.");
			} else {
				System.out.println("Output file already exists. Will overwrite it.");
				this.appendOutput = false;
			}
		} catch (IOException e) {
			System.out.println("An Error Occured...");
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes the List<Project> member projects and parses it for data to generate a report that is written to output.txt
	 */
	public void writeToOutput() {
		try {
			Writer outwriter = new FileWriter("src/Assignment2/output.txt", this.appendOutput);
			outwriter.write("Sigma Report Generator Output File\n");
			
			outwriter.write(String.format("No. of Projects Canceled: %d\n", this.getNumProjectsCanceled()));
			outwriter.write(String.format("No. of Projects Completed: %d\n", this.getNumProjectsCompleted()));
			outwriter.write(String.format("No. of Projects Active: %d\n\n", this.getNumProjectsActive()));
			outwriter.write(String.format("Completed Projects Details:\n\n%s\n", this.getCompletedProjectDetails()));
			
			outwriter.close();
		} catch (IOException e) {
			System.out.println("An Error Occured...");
			e.printStackTrace();
		}
	}
	
	/**
	 * @return number of canceled projects
	 */
	public int getNumProjectsCanceled() {
		int canceled = 0;
		for (Project proj : this.projects) {
			if (proj.getStatus() == ProjectStatus.Canceled) {
				canceled = canceled + 1;
			}
		}
		return canceled;
	}
	
	/**
	 * @return number of completed projects
	 */
	public int getNumProjectsCompleted() {
		int complete = 0;
		for (Project proj : this.projects) {
			if (proj.getStatus() == ProjectStatus.Complete) {
				complete = complete + 1;
			}
		}
		return complete;
	}
	
	/**
	 * @return number of active projects
	 */
	public int getNumProjectsActive() {
		int active = 0;
		for (Project proj : this.projects) {
			if (proj.getStatus() == ProjectStatus.Active) {
				active = active + 1;
			}
		}
		return active;
	}
	
	/**
	 * @return String with the details of all completed projects
	 */
	public String getCompletedProjectDetails() {
		String out = "";
		for (Project proj : this.projects) {
			if (proj.getStatus() == ProjectStatus.Complete) {
				out += proj.getProjectDetails();
			}
		}
		return out;
	}
}

class XMLOutputGenerator extends OutputGenerator {
	public void getProjectList(List<Project> projects) {}
	public void makeNewOutputFile() {}
	public void writeToOutput() {}
}

class RDBMSOutputGenerator extends OutputGenerator {
	public void getProjectList(List<Project> projects) {}
	public void makeNewOutputFile() {}
	public void writeToOutput() {}
}

enum DataFormatType { TEXT, XML, RDBMS }

class SigmaReportGenerator {
	DataFormatType reportType;
	InputCompilerFactory icf;
	OutputGenerator ogf;
	
	public SigmaReportGenerator (DataFormatType reportType) {
		this.reportType = reportType;
		this.determineReportFormat();
	}
	
	/**
	 * Method for determining which Concrete Creator Factory to use when generating the Project List, as well as which Report Generator to use.
	 */
	void determineReportFormat() {
		switch(this.reportType) {
		case TEXT: 
			icf = new TextInputCompilerFactory();
			ogf = new TextOutputGenerator();
			break;
		case XML:
			icf = new XMLInputCompilerFactory();
			ogf = new XMLOutputGenerator();
			break;
		case RDBMS:
			icf = new RDBMSInputCompilerFactory();
			ogf = new RDBMSOutputGenerator();
			break;
		default:
			icf = new TextInputCompilerFactory();
			ogf = new TextOutputGenerator();
			break;
		}
	}
	
	/**
	 * Takes the appropriate Factory & Report Generator to generate Project objects from the input and then compile a report for output
	 */
	void generateReport() {
		if (icf == null) {
			this.determineReportFormat();
		}
		List<Project> projects = this.icf.generateProjects();
		this.ogf.getProjectList(projects);
		this.ogf.makeNewOutputFile();
		this.ogf.writeToOutput();
	}
}


public class COEN359_Assign2 {

	public static void main(String[] args) {
		System.out.println("---COEN 359 Assignment 2 ---\n---Shiva Govindaraju; 30 April 2021---");
		SigmaReportGenerator srg = new SigmaReportGenerator(DataFormatType.TEXT);
		srg.generateReport();
		System.out.println("Sigma Report Generated. Please see output file for summarized project report.");
	}

}
