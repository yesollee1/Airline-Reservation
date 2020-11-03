
/**
 * @author Ye Sol Lee
 * @version 1.0 9/12/2020
 * A passenger has information such as their service class, and their seat number. 
 */
public class Passenger {
	
	private String name="";
	private String serviceClass="";
	private String seatPreference="";
	private String seatNum="";
	private String group="";
	
	/**
	 * Creates passenger who can make reservations
	 * @param name - name of the passenger
	 * @param serviceClass - the service class they want to be seated in 
	 */
	public Passenger(String name, String serviceClass) {
		this.name = name;
		this.serviceClass = serviceClass;
	}
	
	/**
	 * Retrieve the name of the passenger
	 * @return the name of the passenger
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Retrieve the service class of passenger
	 * @return the service class of passenger
	 */
	public String getServiceClass()
	{
		return serviceClass;
	}
	
	/**
	 * Retrieve the seat preference of passenger
	 * @return the seat preference of passenger
	 */
	public String getSeatPreference()
	{
		return seatPreference; 
	}
	
	/**
	 * Retrieves the seat number of the passenger
	 * @return the seat number the passenger is seated in 
	 */
	public String getSeatNum()
	{
		return seatNum; 
	}
	
	/**
	 * Retrieves the group of passenger
	 * @return the group the passenger is associated with 
	 */
	public String getGroup()
	{
		return group;
	}
	
	/**
	 * Assigns the seat number to this passenger
	 * @param seat - the seat number 
	 */
	public void setSeatNum(String seat)
	{
		this.seatNum = seat;
	}
	
	/**
	 * Assigns the group to this passenger
	 * @param group - associates passenger with this group
	 */
	public void setGroup(String group)
	{
		this.group = group;
	}
	
	/**
	 * Sets the seat preference for this passenger
	 * @param seatPreference - the seat preference of the passenger
	 */
	public void setSeatPreference(String seatPreference)
	{
		this.seatPreference = seatPreference;
	}
	
	
	
	
	
	

}
