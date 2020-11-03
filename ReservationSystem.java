import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ye Sol Lee
 * @version 1.0 9/12/2020
 * ReservationSystem manages user input, prints them into the console, and saves the data into a file
 *
 */
public class ReservationSystem {
	
	private static FirstClass firstC= new FirstClass();
	private static EconomyClass economyC = new EconomyClass();
	
	/**
	 * @param args command line argument 
	 */
	public static void main(String[] args) {
		
		try {
			 
			   File myFile = new File("reservations/CL34.txt");
			 
			   if (myFile.createNewFile())
			    System.out.println("File is created!");
			   else
			   {
			    System.out.println("File already exists.");
			    Scanner myReader = new Scanner(myFile);
			      while (myReader.hasNextLine()) 
			      {
			    	  String seat="";
			    	  String[] splitData = myReader.nextLine().split(",");
			    	  ArrayList<String> info = new ArrayList<String>();
			    	  for(int i=0;i<splitData.length;i++)
			    	  {
			    		  info.add(splitData[i]);
			    	  }
			    	  seat= splitData[0];
			    	  if(Integer.parseInt(seat.substring(0,seat.length()-1)) < 10)
			    	  {
			    		  firstC.restoreReservation(info);
			    	  }
			    	  else if(Integer.parseInt(seat.substring(0,seat.length()-1)) >= 10)
			    	  {
			    		  economyC.restoreReservation(info);
			    	  }
			    	  
			      }
			    
			   	}
			 
			  } 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Scanner sc= new Scanner(System.in);
		String action ="";
		while(!action.equalsIgnoreCase("Q"))
		{
			System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
			action = sc.nextLine();
			
			String name;
			String serviceC;
			String seatPreference;
			String groupName;
			
			if(action.equalsIgnoreCase("P"))
			{
				System.out.print("Name: ");
				name = sc.nextLine();
				System.out.print("Service Class: ");
				serviceC = sc.nextLine();
				System.out.print("Seat Preference: ");
				seatPreference = sc.nextLine();
				Passenger person = new Passenger(name, serviceC);
				person.setSeatPreference(seatPreference);
				if(serviceC.equals("First"))
				{
					String seat =firstC.reserveSeat(person);
					while(seat.equals(""))
					{
						System.out.println("Request failed.Try Again.");
						System.out.print("Name: ");
						name = sc.nextLine();
						System.out.print("Service Class: ");
						serviceC = sc.nextLine();
						System.out.print("Seat Preference: ");
						seatPreference = sc.nextLine();
						person = new Passenger(name, serviceC);
						person.setSeatPreference(seatPreference);

						seat =firstC.reserveSeat(person);
					}
					
					System.out.println("Your seat: "+seat);
				}
				else if(serviceC.equals("Economy"))
				{
					String seat =economyC.reserveSeat(person);
					while(seat.equals(""))
					{
						System.out.println("Request failed.Try Again.");
						System.out.print("Name: ");
						name = sc.nextLine();
						System.out.print("Service Class: ");
						serviceC = sc.nextLine();
						System.out.print("Seat Preference: ");
						seatPreference = sc.nextLine();
						person = new Passenger(name, serviceC);
						person.setSeatPreference(seatPreference);
						seat =economyC.reserveSeat(person);
					}

					System.out.println("Your seat: "+seat);
				}
					
			}
			
			else if(action.equalsIgnoreCase("G"))
			{
				System.out.print("Group Name: ");
				groupName = sc.nextLine();
				System.out.print("Names: ");
				name = sc.nextLine();
				System.out.print("Service Class: ");
				serviceC = sc.nextLine();
				String[] nameListSplit = name.split(",");
				ArrayList<String> nameList = new ArrayList<String>();
				for(int i=0;i<nameListSplit.length;i++)
				{
					nameList.add(nameListSplit[i]);
				}
				
				nameList.add(groupName);
				
				if(serviceC.equals("First"))
				{
					String seat =firstC.reserveSeat(nameList);
					if(seat.equals(""))
						System.out.println("Request Failed.");
					else
						System.out.println("Your seats: "+seat);
				}
				else if(serviceC.equals("Economy"))
				{
					String seat =economyC.reserveSeat(nameList);
					if(seat.equals(""))
						System.out.println("Request Failed.");
					else
						System.out.println("Your seats: "+seat);				}
				
			}
			
			else if(action.equalsIgnoreCase("C"))
			{
				System.out.print("Cancel [I]ndividual or [G]roup? ");
				String cancel = sc.nextLine();
				if(cancel.equalsIgnoreCase("I"))
				{
					System.out.print("Names: ");
					name = sc.nextLine();
					if(firstC.hasPassenger(name))
						firstC.cancelReservationIndividual(name);
					else if(economyC.hasPassenger(name))
						economyC.cancelReservationIndividual(name);
				}
				else if(cancel.equalsIgnoreCase("G"))
				{
					System.out.print("Group Name: ");
					groupName = sc.nextLine();
					if(firstC.hasGroup(groupName))
						firstC.cancelReservationGroup(groupName);
					else if(economyC.hasGroup(groupName))
						economyC.cancelReservationGroup(groupName);
				}
				
			}
			
			else if(action.equalsIgnoreCase("A"))
			{
				System.out.print("Service Class: ");
				serviceC = sc.nextLine();
				if(serviceC.equals("First"))
				{
					firstC.printAvailability();
				}
				else if(serviceC.equals("Economy"))
				{
					economyC.printAvailability();
				}
			}
			else if(action.equalsIgnoreCase("M"))
			{
				System.out.print("Service Class: ");
				serviceC = sc.nextLine();
				if(serviceC.equals("First"))
				{
					firstC.printManifest();
				}
				else if(serviceC.equals("Economy"))
				{
					economyC.printManifest();
				}
			}
			
		}
		if(action.equalsIgnoreCase("Q"))
		{
			try {
			      FileWriter myWriter = new FileWriter("reservations/CL34.txt");
			      myWriter.write(firstC.passengersToString());
			      myWriter.write(economyC.passengersToString());
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}

	}

}
