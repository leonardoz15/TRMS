/**
 * 
 */
package TRMS.models;

/**
 * @author Zachary Leonardo
 *
 */
public class Employee {
	
	
	private int employeeId;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String address;
	
	private int reportsTo;
	
	private double funds;
	
	
	public Employee() {
		super();
	}

	/**
	 * @param employeeId
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param address
	 * @param reportsTo
	 * @param funds
	 */
	public Employee(int employeeId, String firstName, String lastName, String phoneNumber, String address,
			int reportsTo, double funds) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.reportsTo = reportsTo;
		this.funds = funds;
	}



	/**
	 * @param employeeID the unique ID of the employee.
	 * @param firstName the first name of the employee.
	 * @param lastName the last name of the employee.
	 * @param phoneNumber the phone number of the employee.
	 * @param address the address of the employee
	 * @param reportsTo the direct supervisor of the employee.
	 * @param funds the amount of funds available default to $1000
	 */

	public Employee(int employeeID, String firstName, String lastName, String phoneNumber, String address,
			int reportsTo) {
		super();
		this.employeeId = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.reportsTo = reportsTo;
		this.funds = 1000;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeId;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeId = employeeID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the reportsTo
	 */
	public int getReportsTo() {
		return reportsTo;
	}

	/**
	 * @param reportsTo the reportsTo to set
	 */
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	/**
	 * @return the funds
	 */
	public double getFunds() {
		return funds;
	}

	/**
	 * @param funds the funds to set
	 */
	public void setFunds(double funds) {
		this.funds = funds;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", reportsTo=" + reportsTo + ", funds="
				+ funds + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(funds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + reportsTo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (Double.doubleToLongBits(funds) != Double.doubleToLongBits(other.funds))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (reportsTo != other.reportsTo)
			return false;
		return true;
	}
	
}
