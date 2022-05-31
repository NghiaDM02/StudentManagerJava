package studentmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Function
 */
public class Functions {

	final Scanner sc = new Scanner(System.in);
	private Student student = new Student();
	private Date d = new Date();
	private List<Student> students = new ArrayList<Student>();
	private List<Student> othS = new ArrayList<Student>();
	final private String table = "+" + repeatChar("-", 6) + "+" + repeatChar("-", 10) + "+" + repeatChar("-", 20) + "+" + repeatChar("-", 12) + "+" + repeatChar("-", 15) + "+" + repeatChar("-", 7) + "+";
	final private String title = "|" + centerPad("Order", " ", 6) + "|" + centerPad("Id", " ", 10) + "|" + centerPad("Name", " ", 20) + "|" + centerPad("Date", " ", 12) + "|" + centerPad("Address", " ", 15)+ "|" + centerPad("Score", " ", 7) + "|"; 
	final private String filePath = "data.txt";
	StudentSave ss = new StudentSave(filePath);


	public Functions() {
		students = ss.read(students);
	}

	void menu() {
		System.out.println("\n===============Student manager===============");
		System.out.println("1. Add new student");
		System.out.println("2. Print student's information");
		System.out.println("3. Edit student's information");
		System.out.println("4. Delete a student");
		System.out.println("5. Save list student's information to file");
		System.out.println("6. Sort list by score");
		System.out.println("7. Sort list by name");
		System.out.println("0. Exit");

		short choose;

		do {
			try {
				System.out.print("Enter functions from 0 -> 7: ");
				choose = sc.nextShort();

				if (choose < 0 || choose > 7) {
					throw new Exception();
				}

				break;
				
			} catch (Exception e) {
				System.out.println("Input invalid! Please enter again");
			} finally {
				sc.nextLine();
			}
		} while (true);

		switch (choose) {
			case 1:
				students.add(studentInfo());
				menu();
				break;
			case 2:
				printStudent(students);
				menu();
				break;
			case 3:
				editStudent();
				menu();
				break;
			case 4:
				deleteStudent();
				menu();
				break;
			case 5:
				saveInfo();
				menu();
				break;
			case 6:
				students = sortByScore(students, student);
				menu();
				break;
			case 7:
				students = sortByName(students, student);
				menu();
				break;
			default:
				System.out.println("Thanks for using\nExit!");
				break;
		}

	}

	String repeatChar(String c, int n) {
		return c.repeat(n);
	}

	String centerPad(String s, String c, int a) {
		int n = a-s.length();
		return repeatChar(c, n/2) + s + repeatChar(c, n-(n/2));
	}

	void searchByName(String str) {
		othS.clear();
		for (int i = 0; i < students.size(); i++) {
			if (str.equalsIgnoreCase(students.get(i).getName())) {
				othS.add(students.get(i));
			}
		}

		printStudent(othS);
	}

	void searchById(String str) {
		othS.clear();
		for (int i = 0; i < students.size(); i++) {
			if (str.equalsIgnoreCase(students.get(i).getId())) {
				othS.add(students.get(i));
			}
		}

		printStudent(othS);
	}

	Student studentInfo() {
		System.out.print("Id: ");
		String id = sc.nextLine();
		student.setId(id);

		System.out.print("Name: ");
		String name = sc.nextLine();
		student.setName(name);

		System.out.print("Day: " );
		int day = sc.nextInt();
		d.setDay(day);

		System.out.print("Month: ");
		int month = sc.nextInt();
		d.setMonth(month);

		System.out.print("year: ");
		int year = sc.nextInt();
		d.setYear(year);
		sc.nextLine();
		student.setDate(d);

		System.out.print("Address: ");
		String address = sc.nextLine();
		student.setAddress(address);

		System.out.print("Score: ");
		float score = sc.nextFloat();
		student.setScore(score);

		return student;

	}

	void printStudent(List<Student> st) {
		System.out.println(table);
		System.out.println(title);
		System.out.println(table);
		for (int i = 0; i < st.size(); i++) {
			System.out.println("|" + centerPad(String.valueOf(i+1), " ", 6) + "|" + centerPad(st.get(i).getId(), " ", 10) + "|" + centerPad(students.get(i).getName(), " ", 20) + "|" + centerPad(students.get(i).getDate().toString(), " ", 12)
			+ "|" + centerPad(st.get(i).getAddress(), " ", 15) + "|" + centerPad(st.get(i).scoreToString(), " ", 7) + "|");
		}

		System.out.println(table);

	}

	void editStudent() {
		System.out.println("1. Search by name");
		System.out.println("2. Search by Id");

		int choose = sc.nextInt();
		sc.nextLine();

		switch (choose) {
			case 1:
				System.out.print("Enter name: ");
				String str = sc.nextLine();
				searchByName(str);
				if (othS.size() > 0) {
					System.out.print("Choose an order: ");
					int order = sc.nextInt();
					sc.nextLine();
					
					if (order-1 < othS.size()) {
						for (int i = 0; i < students.size(); i++) {
							if (othS.get(order-1).getId().equals(students.get(i).getId())) {
								students.set(i, studentInfo());
							}
						}
					}
				}
				break;

			case 2:
				System.out.print("Enter id: ");
				str = sc.nextLine();
				searchById(str);
				if (othS.size() > 0) {
					System.out.print("Choose an order: ");
					int order = sc.nextInt();
					sc.nextLine();
					
					if (order-1 < othS.size()) {
						for (int i = 0; i < students.size(); i++) {
							if (othS.get(order-1).getId().equals(students.get(i).getId())) {
								students.set(i, studentInfo());
							}
						}
					}
				}
				break;
		}

	}

	void deleteStudent() {
		System.out.println("1. Search by name");
		System.out.println("2. Search by Id");

		int choose = sc.nextInt();
		sc.nextLine();

		if (choose == 1) {
			System.out.print("Enter name: ");
				String str = sc.nextLine();
				searchByName(str);
				if (othS.size() > 0) {
					System.out.print("Choose an order: ");
					int order = sc.nextInt();
					sc.nextLine();
					
					if (order-1 < othS.size()) {
						for (int i = 0; i < students.size(); i++) {
							if (othS.get(order-1).getName().equals(students.get(i).getName())) {
								students.remove(i);
							}
						}
					}
				}
		}
		if (choose == 2) {
			System.out.print("Enter name: ");
				String str = sc.nextLine();
				searchByName(str);
				if (othS.size() > 0) {
					System.out.print("Choose an order: ");
					int order = sc.nextInt();
					sc.nextLine();
					
					if (order-1 < othS.size()) {
						for (int i = 0; i < students.size(); i++) {
							if (othS.get(order-1).getId().equals(students.get(i).getId())) {
								students.remove(i);
							}
						}
					}
				}
		}
	}

	void saveInfo() {

		File f = new File(filePath);

		if (!f.exists()) {
			System.out.println("File does not exist\nCreate new file");
			try {
				if (f.createNewFile()) {
					System.out.println("Successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ss.write(students);
		System.out.println("");

	}

	List<Student> sortByScore(List<Student> sts, Student st) {
		for (int i = 0; i < sts.size()-1; i++) {
			int min = i;
			for (int j = i+1; j < sts.size(); j++) {
				if (sts.get(min).getScore() > sts.get(j).getScore()) {
					min = j;
				}
			}

			st = sts.get(i);
			sts.set(i, sts.get(min));
			sts.set(min, st);
		}
		System.out.println("Successfully");


		return sts;
	}

	List<Student> sortByName(List<Student> sts, Student st) {
		for (int i = 0; i < sts.size()-1; i++) {
			int min = i;
			for (int j = i+1; j < sts.size(); j++) {
				if (sts.get(min).getName().compareTo(sts.get(j).getName()) > 0) {
					min = j;
				}
			}

			Collections.swap(sts, i, min);
		}
		System.out.println("Successfully");


		return sts;
	}	
}
