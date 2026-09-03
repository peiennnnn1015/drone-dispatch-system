/**
 * The {@code EnvelopeFeature} enum represents the different types of features
 * that an envelope parcel can have.
 * <p>
 * Each feature has an associated cost and flight classification:
 * <ul>
 *     <li>TAMPER_EVIDENT - Hazardous feature with $6.80 cost</li>
 *     <li>WATERPROOF - Hazardous feature with $5.20 cost</li>
 *     <li>CONFIDENTIAL - Fragile feature with $5.20 cost</li>
 *     <li>STANDARD - Standard feature with $4.00 cost</li>
 * </ul>
 * </p>
 *
 * @author Yun Pei En
 * @version 1.0
 */
public enum EnvelopeFeature
{
    /**
     * Tamper-evident envelope feature.
     * Classification: HAZARDOUS
     * Cost: $6.80
     */
    TAMPER_EVIDENT(6.80, FlightCategory.HAZARDOUS),

    /**
     * Waterproof envelope feature.
     * Classification: HAZARDOUS
     * Cost: $5.20
     */
    WATERPROOF(5.20, FlightCategory.HAZARDOUS),

    /**
     * Confidential envelope feature.
     * Classification: FRAGILE
     * Cost: $5.20
     */
    CONFIDENTIAL(5.20, FlightCategory.FRAGILE),

    /**
     * Standard envelope feature.
     * Classification: STANDARD
     * Cost: $4.00
     */
    STANDARD(4.00, FlightCategory.STANDARD);

    private double cost;
    private FlightCategory classification;

    /**
     * Constructs an EnvelopeFeature with the specified cost and classification.
     *
     * @param cost the additional cost for this envelope feature
     * @param classification the flight category for this envelope feature
     */
    private EnvelopeFeature(double cost, FlightCategory classification)
    {
        this.cost = cost;
        this.classification = classification;
    }

    /**
     * Returns the flight classification of this envelope feature.
     *
     * @return the flight category (HAZARDOUS, FRAGILE, or STANDARD)
     */
    public FlightCategory getClassification()
    {
        return classification;
    }

    /**
     * Returns the additional cost of this envelope feature.
     *
     * @return the cost in dollars
     */
    public double getCost()
    {
        return cost;
    }
}