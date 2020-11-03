import java.util.ArrayList;


/**
 * @author Ye Sol Lee
 * @version 1.0 9/12/2020
 * FirstClass holds passengers and has the capability to make and cancel reservations
 */
public class FirstClass {
	
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>(8);
	private int numAvailableSeats = passengers.size();
	
	/**
	 * Initializes the seats with passenger seats that are empty
	 */
	public FirstClass()
	{		
		for(int i =0; i<8;i++)
		{
			passengers.add(new Passenger("",""));
		}
	}
	
	/**
	 * Reserves a seat for the passenger
	 * @param p - the passenger trying to reserve a seat
	 * @return the location where the passenger will be seated
	 */
	public String reserveSeat(Passenger p)
	{
		int emptySeat = findIndividualEmptySeat(p);
		String seat="";
		if(emptySeat != -1)
		{
			passengers.remove(emptySeat);
			passengers.add(emptySeat, p); 
			seat = findSeatNum(emptySeat);
			p.setSeatNum(seat);		 
			numAvailableSeats--;
		}
		
		return seat;
	}
	
	/**
	 * Reserves seats for a group
	 * @param group - group of passengers trying to reserve seats
	 * @return the locations where the group members will be seated
	 */
	public String reserveSeat(ArrayList<String> group)
	{
		String seats="";
		ArrayList<Integer> emptySeats=findGroupEmptySeat(group);
		if(numAvailableSeats >= group.size() && emptySeats.size() != 0)
		{
			for(int i=0;i<emptySeats.size();i++)
			{
				Passenger person = new Passenger(group.get(i),"First");
				passengers.remove(emptySeats.get(i).intValue());
				passengers.add(emptySeats.get(i),person);
				seats = seats+findSeatNum(emptySeats.get(i))+" ";
				person.setSeatNum(findSeatNum(emptySeats.get(i)));
				person.setGroup(group.get(group.size()-1));
				numAvailableSeats--;
			}
		}
		
		return seats;
		
	}
	
	/**
	 * Looks at the passenger's seat preference and looks for an empty seat
	 * @param p - passenger trying to find seat
	 * @return the index of the seat that is empty
	 */
	public int findIndividualEmptySeat(Passenger p)
	{
		
		for(int i=0; i<passengers.size();i++)
		{
			if(p.getSeatPreference().equalsIgnoreCase("W"))
			{
				if(passengers.get(i).getName().equals("") && ((i+1)%4 == 1||(i+1)%4 == 0))
				{
					return i;
				}
			}
			else if(p.getSeatPreference().equalsIgnoreCase("A"))
			{
				if(passengers.get(i).getName().equals("") && ((i+1)%4 == 2||(i+1)%4 == 3))
				{
					return i;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Finds the seats that are available for the group and stores them in an arraylist
	 * @param p - the names of the people in the group. Last string in p is the group name. 
	 * @return an arraylist containing the available seats that the group can be seated in. 
	 */
	public ArrayList<Integer> findGroupEmptySeat(ArrayList<String> p)
	{
		ArrayList<Integer> locations=new ArrayList<Integer>();

		int person=0;
		int i=0;
		while(person < p.size()-1)
		{
			while(i<passengers.size()-1)
			{
				if(passengers.get(i).getName().equals("") && passengers.get(i+1).getName().equals("") && (i+4)/4 == (i+5)/4)
				{
					locations.add(i);
					break;
				}
				else
					i++;
			}
			i++;
			person++;
		}
		
		return locations;
	}
	
	/**
	 * Finds the row and letter of the seat the passenger will be seated. 
	 * @param emptySeat - the index where there is an empty seat in the arraylist
	 * @return the seat number where the passenger will be seated. 
	 */
	public String findSeatNum(int emptySeat)
	{
		String seat="";
		if((emptySeat+1)%4 == 1)
			seat = (emptySeat+4)/4+"A";
		else if((emptySeat+1)%4 == 0)
			seat =  (emptySeat+4)/4+"D";
		else if((emptySeat+1)%4 == 2)
			seat = (emptySeat+4)/4+"B";
		else if((emptySeat+1)%4 == 3)
			seat = (emptySeat+4)/4+"C";
		
		
		return seat;
		
	}
	
	
	/**
	 * Checks if the passenger has made reservation in First Class.  
	 * @param name - name of the passenger to look for 
	 * @return true if the passenger has made a reservation in First Class and can be found in the system. 
	 */
	public boolean hasPassenger(String name)
	{
		for(int i=0; i<passengers.size();i++)
		{
			if(passengers.get(i).getName().equals(name))
				return true;
		}
		return false;
	}
	
	/**
	 * Checks if the group has made reservation in First Class.  
	 * @param group - name of the group to look for 
	 * @return true if the group has made reservations and can be found in the system. s
	 */
	public boolean hasGroup(String group)
	{
		boolean groupExist = false;
		for(Passenger p:passengers)
		{
			if(p.getGroup().equals(group))
				groupExist= true;
		}
		return groupExist;
	}
	
	/**
	 * Cancels reservation for an individual passenger
	 * @param name - name of person that wants to cancel
	 */
	public void cancelReservationIndividual(String name)
	{
		for(int i=0; i<passengers.size();i++)
		{
			if(passengers.get(i).getName().equals(name))
				passengers.set(i, new Passenger("",""));
		}
	}
	
	/**
	 * Cancels reservation for a group
	 * @param groupName - name of the group to look for when trying to cancel
	 */
	public void cancelReservationGroup(String groupName)
	{
		for(int i=0; i<passengers.size();i++)
		{
			if(passengers.get(i).getGroup().equals(groupName))
				passengers.set(i, new Passenger("",""));
		}
	}
	
	/**
	 * Prints the availability chart which shows the seats that are open
	 */
	public void printAvailability()
	{
		System.out.println("First");
		int row=0;
		int prevRow=0;
		for(int i=0; i<passengers.size();i++)
		{
			if(passengers.get(i).getName().equals(""))
			{
				row =(i+4)/4;
				if(row!= prevRow)
					System.out.println();
				System.out.print(findSeatNum(i)+" ");
			}
			prevRow = row;
		}
		
		System.out.println();
	}
	
	/**
	 * Prints out the manifest list which shows the seats that are occupied and the passengers seated in them 
	 */
	public void printManifest()
	{
		System.out.println("First");
		for(int i=0; i<passengers.size();i++)
		{
			if(!passengers.get(i).getName().equals(""))
			{
				System.out.println(findSeatNum(i)+": "+passengers.get(i).getName());
			}
		}
	}
	
	/**
	 * Formats the data in the arraylist of passengers for the CL34 file
	 * @return a string that represents all the reservation data
	 */
	public String passengersToString()
	{
		String data="";
		String passengerName ="";
		for(int i=0; i<passengers.size();i++)
		{
			passengerName = passengers.get(i).getName();
			if(!passengerName.equals(""))
			{
				data+=findSeatNum(i);
				if(passengers.get(i).getGroup().equals(""))
					data+=","+"I"+","+passengerName+"\n";
				else
					data+=","+"G"+","+passengers.get(i).getGroup()+","+passengerName+"\n";
			}
		}
		return data;
	}
	
	/**
	 * Initialize the arraylist with the people who have already made a reservation
	 * @param person - the data from one passenger, split into an arraylist
	 */
	public void restoreReservation(ArrayList<String> person)
	{
		int seatIndex=0;
		String seat = person.get(0);
		int row = Integer.parseInt(seat.substring(0, 1));
		Passenger p = new Passenger(person.get(person.size()-1), "First");
		if(seat.substring(1).equals("A"))
			seatIndex = (4*row)-4;
		else if(seat.substring(1).equals("B"))
			seatIndex = (4*row)-3;
		else if(seat.substring(1).equals("C"))
			seatIndex = (4*row)-2;
		else if(seat.substring(1).equals("D"))
			seatIndex = (4*row)-1;
		
		passengers.remove(seatIndex);
		passengers.add(seatIndex, p); 
		if(person.get(1).equals("G"))
		{
			p.setGroup(person.get(2));
		}
		
	}
	
}
