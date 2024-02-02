/**
 * @author Mike Cordon - mrcordon@dmacc.edu
 * CIS 175 - Spring 2024
 * Feb 1, 2024
 */

import java.util.List;
import java.util.Scanner;

import controller.DoggieHelper;
import model.Doggies;
/**
 * This program contains the main and menu functions for the dog
 * database project
 */
public class StartProgram {

		static Scanner in = new Scanner(System.in); // allows user input
		static DoggieHelper dh = new DoggieHelper();  // instantiate object from controller program.

		/**
		 * method to add a dog to the database from user input
		 */
		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the dog's name: ");
			String name = in.nextLine();
			System.out.print("Enter the dog's breed: ");
			String breed = in.nextLine();
			System.out.print("Enter the dog's color: ");
			String color = in.nextLine();
			Doggies toAdd = new Doggies(name, breed, color);
			dh.insertItem(toAdd);
		}

		
		/**
		 * method to delete a row from the dog database
		 * with user input
		 */
		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the name to delete: ");
			String name = in.nextLine();
			System.out.print("Enter the breed to delete: ");
			String breed = in.nextLine();
			System.out.print("Enter the color to delete: ");
			String color = in.nextLine();
			Doggies toDelete = new Doggies(name, breed, color);
			dh.deleteItem(toDelete);
		}

		
		/**
		 * method to edit an item in the database base on user selected
		 * search materials
		 */
		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by name");
			System.out.println("2 : Search by breed");
			System.out.println("3 : Search by color");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Doggies> foundItems;
			if (searchBy == 1) {  // search by name
				System.out.print("Enter the dog's name: ");
				String name = in.nextLine();
				foundItems = dh.searchForDogByName(name);
				
			} else if(searchBy == 2) {  // search by breed
				System.out.print("Enter the dog's breed: ");
				String breed = in.nextLine();
				foundItems = dh.searchForDogByBreed(breed);

			} else {  // search by color
				System.out.print("Enter the dog's color: ");
				String color = in.nextLine();
				foundItems = dh.searchForDogByColor(color);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (Doggies d : foundItems) {
					System.out.println(d.getId() + " : " + d.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Doggies toEdit = dh.searchForDogById(idToEdit);
				// update by selection menu
				System.out.println("Retrieved " + toEdit.getBreed() + " from " + toEdit.getName());
				System.out.println("1 : Update Name");
				System.out.println("2 : Update Breed");
				System.out.println("3 : Update Color");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {  // update name
					System.out.print("New dog name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {  // update breed
					System.out.print("New dog breed: ");
					String newBreed = in.nextLine();
					toEdit.setBreed(newBreed);
				} else if (update == 3) {  // update color
					System.out.print("New dog color: ");
					String newColor = in.nextLine();
					toEdit.setBreed(newColor);
				}

				dh.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();  // calls main menu

		}
		
		
		/**
		 * method that is the main menu for the dog database progam
		 */
		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the 'good boy' dog database! ---");
			while (goAgain) {
				System.out.println("");
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a dog");
				System.out.println("*  2 -- Edit a dog");
				System.out.println("*  3 -- Delete a dog");
				System.out.println("*  4 -- View the list of dogs");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					dh.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		
		/**
		 * method to view the items in the database
		 */
		private static void viewTheList() {
			List<Doggies> allItems = dh.showAllItems();
			System.out.println("");
			for(Doggies singleDog: allItems) {
				System.out.println(singleDog.returnDogDetails());
			}
			System.out.println("");

		}

	}