import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Order class represents a customer order containing multiple parcels.
 * Each order has a unique ID, customer details, and a delivery constraint.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class Order
{
    private static int nextOrderID = 101;
    private int orderID;
    private ArrayList<Parcel> parcels;
    private Customer customer;
    private FlightCategory flightCategory;
    private double totalCost;
    private static final int DELIVERY_CONSTRAINT = 5;
    private LocalDateTime orderDateTime;

    /**
     * Default constructor that creates an empty order.
     */
    public Order()
    {
        this(null, new ArrayList<>());
    }

    /**
     * Parameterized constructor that creates an order with specified customer and parcels.
     *
     * @param customer the customer placing the order
     * @param parcels the list of parcels in the order
     */
    public Order(Customer customer, ArrayList<Parcel> parcels)
    {
        this.orderID = generateOrderID();
        this.customer = customer;
        this.parcels = new ArrayList<>();
        this.flightCategory = FlightCategory.STANDARD;
        this.totalCost = 0.0;
        this.orderDateTime = LocalDateTime.now();

        if (parcels != null)
        {
            for (Parcel parcel : parcels)
            {
                addParcel(parcel);
            }
        }
    }

    /**
     * Adds a parcel to the order if under the delivery constraint.
     *
     * @param parcel the parcel to add
     * @return true if the parcel was added, false otherwise
     */
    public boolean addParcel(Parcel parcel)
    {
        boolean canAddParcel;

        if (parcel == null || parcels.size() >= DELIVERY_CONSTRAINT)
        {
            canAddParcel = false;
        }
        else
        {
            parcels.add(parcel);
            updateOrderDetails();
            canAddParcel = true;
        }

        return canAddParcel;
    }

    /**
     * Checks if the order can accept more parcels.
     *
     * @return true if more parcels can be added
     */
    public boolean canAcceptMoreParcels()
    {
        boolean canAccept;

        canAccept = parcels.size() < DELIVERY_CONSTRAINT;
        return canAccept;
    }

    /**
     * Generates a unique order ID.
     *
     * @return a unique order ID integer
     */
    private int generateOrderID()
    {
        int newOrderID;

        newOrderID = nextOrderID;
        nextOrderID += 1;

        return newOrderID;
    }

    /**
     * Returns the customer.
     *
     * @return the customer
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * Returns the delivery constraint (maximum parcels per order).
     *
     * @return the delivery constraint
     */
    public int getDeliveryConstraint()
    {
        int constraint;

        constraint = DELIVERY_CONSTRAINT;
        return constraint;
    }

    /**
     * Returns the flight category.
     *
     * @return the flight category
     */
    public FlightCategory getFlightCategory()
    {
        return flightCategory;
    }

    /**
     * Returns the date and time when the order was created.
     *
     * @return formatted date/time string
     */
    public String getOrderDateTime()
    {
        DateTimeFormatter formatter;
        String formattedDateTime;

        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        formattedDateTime = orderDateTime.format(formatter);

        return formattedDateTime;
    }

    /**
     * Returns the order ID.
     *
     * @return the order ID
     */
    public int getOrderID()
    {
        return orderID;
    }

    /**
     * Returns the number of parcels in the order.
     *
     * @return the parcel count
     */
    public int getParcelCount()
    {
        int count;

        count = parcels.size();
        return count;
    }

    /**
     * Returns the list of parcels.
     *
     * @return the list of parcels
     */
    public ArrayList<Parcel> getParcels()
    {
        ArrayList<Parcel> copiedParcels;

        copiedParcels = new ArrayList<>(parcels);
        return copiedParcels;
    }

    /**
     * Returns the total cost.
     *
     * @return the total cost
     */
    public double getTotalCost()
    {
        return totalCost;
    }

    /**
     * Recalculates the flight category based on all parcels.
     * Hazardous takes priority over fragile, which takes priority over standard.
     */
    private void recalculateFlightCategory()
    {
        boolean hasHazardous = false;
        boolean hasFragile = false;

        for (Parcel parcel : parcels)
        {
            FlightCategory parcelCategory;

            parcelCategory = parcel.getClassification();

            if (parcelCategory == FlightCategory.HAZARDOUS)
            {
                hasHazardous = true;
            }
            else if (parcelCategory == FlightCategory.FRAGILE)
            {
                hasFragile = true;
            }
        }

        if (hasHazardous)
        {
            flightCategory = FlightCategory.HAZARDOUS;
        }
        else if (hasFragile)
        {
            flightCategory = FlightCategory.FRAGILE;
        }
        else
        {
            flightCategory = FlightCategory.STANDARD;
        }
    }

    /**
     * Sets the customer.
     *
     * @param customer the new customer
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    /**
     * Sets the flight category.
     *
     * @param flightCategory the new flight category
     */
    public void setFlightCategory(FlightCategory flightCategory)
    {
        this.flightCategory = flightCategory;
    }

    /**
     * Sets the order ID.
     *
     * @param orderID the new order ID
     */
    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    /**
     * Sets the list of parcels.
     *
     * @param parcels the new list of parcels
     */
    public void setParcels(ArrayList<Parcel> parcels)
    {
        this.parcels = parcels;
    }

    /**
     * Sets the total cost.
     *
     * @param totalCost the new total cost
     */
    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }

    /**
     * Returns a string representation of the order.
     *
     * @return a string describing the order
     */
    @Override
    public String toString()
    {
        String orderDetails;

        orderDetails = "\nORDER #" + orderID + "\n" +
                "Date/Time: " + getOrderDateTime() + "\n" +
                "Customer: " + customer.getCustomerName() + "\n" +
                "Contact: " + customer.getCustomerContactNumber() + "\n" +
                "Address: " + customer.getCustomerAddress() + "\n" +
                "Flight Category: " + flightCategory + "\n" +
                "Total Cost: $" + String.format("%.2f", totalCost) + "\n" +
                "Parcels (" + parcels.size() + "/" +
                DELIVERY_CONSTRAINT + "):\n";

        for (int i = 0; i < parcels.size(); i++)
        {
            orderDetails += (i + 1) + ". " + parcels.get(i) + "\n";
        }

        return orderDetails;
    }

    /**
     * Updates the order details including total cost and flight category.
     */
    private void updateOrderDetails()
    {
        totalCost = 0.0;

        for (Parcel parcel : parcels)
        {
            totalCost += parcel.calculatePrice();
        }

        recalculateFlightCategory();
    }
}