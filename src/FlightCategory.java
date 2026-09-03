/**
 * The {@code FlightCategory} enum represents the three possible flight
 * classifications for an order or parcel in the drone dispatch system.
 * <p>
 * The categories are ordered by priority:
 * <ul>
 *     <li>HAZARDOUS - Highest priority (contains hazardous materials)</li>
 *     <li>FRAGILE - Medium priority (contains fragile items)</li>
 *     <li>STANDARD - Lowest priority (no special handling required)</li>
 * </ul>
 * </p>
 * <p>
 * When determining the flight category for an order, the highest priority
 * category among all parcels is used. For example, if any parcel is hazardous,
 * the entire order is classified as HAZARDOUS.
 * </p>
 *
 * @author Yun Pei En
 * @version 1.0
 */
public enum FlightCategory
{
    /**
     * Hazardous flight category.
     * Applied to orders containing hazardous materials such as liquids or electronics.
     * This is the highest priority category.
     */
    HAZARDOUS,

    /**
     * Fragile flight category.
     * Applied to orders containing fragile items such as glassware or perishables.
     * This is the medium priority category.
     */
    FRAGILE,

    /**
     * Standard flight category.
     * Applied to orders with no hazardous or fragile items.
     * This is the lowest priority category.
     */
    STANDARD
}