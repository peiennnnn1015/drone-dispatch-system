/**
 * The abstract Parcel class represents a generic parcel in the dispatch system.
 * All specific parcel types (Box, Envelope) must extend this class.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public abstract class Parcel
{
    /**
     * Default constructor for Parcel.
     */
    public Parcel()
    {
        // Empty constructor as required by coding standards.
    }

    /**
     * Calculates the price of the parcel.
     *
     * @return the calculated price
     */
    public abstract double calculatePrice();

    /**
     * Determines the flight category of the parcel.
     *
     * @return the flight category
     */
    public abstract FlightCategory getClassification();

    /**
     * Returns a string representation of the parcel.
     *
     * @return a string describing the parcel
     */
    @Override
    public String toString()
    {
        String message;

        message = "Parcel";
        return message;
    }
}