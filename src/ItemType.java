/**
 * The {@code ItemType} enum represents the different types of items that can
 * be placed inside a box parcel.
 * <p>
 * Each item type has an associated cost and flight classification based on the
 * sensitivity priority rules:
 * </p>
 * <p>
 * <b>Priority Logic:</b>
 * <ul>
 *     <li>HAZARDOUS (Priority 1 - Highest): Electronics, Liquids</li>
 *     <li>FRAGILE (Priority 2): Glassware, Perishables</li>
 *     <li>STANDARD (Priority 3 - Lowest): Clothes, Documents</li>
 * </ul>
 * </p>
 * <p>
 * When a box contains multiple items, the highest priority classification
 * is assigned to the entire box.
 * </p>
 *
 * @author Yun Pei En
 * @version 1.0
 */
public enum ItemType
{
    /**
     * Clothes item.
     * Classification: STANDARD
     * Cost: $2.00
     */
    CLOTHES(2.00, FlightCategory.STANDARD),

    /**
     * Documents item.
     * Classification: STANDARD
     * Cost: $2.00
     */
    DOCUMENTS(2.00, FlightCategory.STANDARD),

    /**
     * Liquids item.
     * Classification: HAZARDOUS
     * Cost: $2.50
     */
    LIQUIDS(2.50, FlightCategory.HAZARDOUS),

    /**
     * Glassware item.
     * Classification: FRAGILE
     * Cost: $2.00
     */
    GLASSWARE(2.00, FlightCategory.FRAGILE),

    /**
     * Electronics item.
     * Classification: HAZARDOUS
     * Cost: $3.50
     */
    ELECTRONICS(3.50, FlightCategory.HAZARDOUS),

    /**
     * Perishables item.
     * Classification: FRAGILE
     * Cost: $2.00
     */
    PERISHABLES(2.00, FlightCategory.FRAGILE);

    private double cost;
    private FlightCategory classification;

    /**
     * Constructs an ItemType with the specified cost and classification.
     *
     * @param cost the cost of this item type in dollars
     * @param classification the flight category for this item type
     */
    private ItemType(double cost, FlightCategory classification)
    {
        this.cost = cost;
        this.classification = classification;
    }

    /**
     * Returns the flight classification of this item type.
     *
     * @return the flight category (HAZARDOUS, FRAGILE, or STANDARD)
     */
    public FlightCategory getClassification()
    {
        return classification;
    }

    /**
     * Returns the cost of this item type.
     *
     * @return the cost in dollars
     */
    public double getCost()
    {
        return cost;
    }
}