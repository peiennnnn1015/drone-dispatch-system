/**
 * The Customer class represents a customer who places an order.
 * Each customer has a unique ID, name, contact number, and address.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class Customer
{
    private int customerID;
    private String customerName;
    private String customerContactNumber;
    private String customerAddress;

    /**
     * Default constructor that creates a customer with unknown values.
     */
    public Customer()
    {
        this(0, "Unknown", "Unknown", "Unknown");
    }

    /**
     * Parameterized constructor that creates a customer with specified details.
     *
     * @param customerID the unique identifier for the customer
     * @param customerName the name of the customer
     * @param customerContactNumber the contact number of the customer
     * @param customerAddress the delivery address of the customer
     */
    public Customer(int customerID, String customerName,
                    String customerContactNumber, String customerAddress)
    {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerContactNumber = customerContactNumber;
        this.customerAddress = customerAddress;
    }

    /**
     * Returns the customer address.
     *
     * @return the customer address
     */
    public String getCustomerAddress()
    {
        return customerAddress;
    }

    /**
     * Returns the customer contact number.
     *
     * @return the customer contact number
     */
    public String getCustomerContactNumber()
    {
        return customerContactNumber;
    }

    /**
     * Returns the customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerID()
    {
        return customerID;
    }

    /**
     * Returns the customer name.
     *
     * @return the customer name
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Sets the customer address.
     *
     * @param customerAddress the new address
     */
    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    /**
     * Sets the customer contact number.
     *
     * @param customerContactNumber the new contact number
     */
    public void setCustomerContactNumber(String customerContactNumber)
    {
        this.customerContactNumber = customerContactNumber;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerID the new customer ID
     */
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName the new customer name
     */
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return a string describing the customer
     */
    @Override
    public String toString()
    {
        String message = "Customer #" + customerID + ": " + customerName +
                " (" + customerContactNumber + ") - " + customerAddress;
        return message;
    }
}