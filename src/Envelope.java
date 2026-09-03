/**
 * The Envelope class represents an envelope parcel with a single feature.
 * Envelopes have a base price plus an additional cost based on their feature.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class Envelope extends Parcel
{
    private EnvelopeFeature envelopeFeature;

    /**
     * Default constructor that creates a standard envelope.
     */
    public Envelope()
    {
        this(EnvelopeFeature.STANDARD);
    }

    /**
     * Parameterized constructor that creates an envelope with a specific feature.
     *
     * @param envelopeFeature the feature of the envelope
     */
    public Envelope(EnvelopeFeature envelopeFeature)
    {
        this.envelopeFeature = envelopeFeature;
    }

    /**
     * Calculates the total price of the envelope including base price and feature cost.
     *
     * @return the total price of the envelope
     */
    @Override
    public double calculatePrice()
    {
        double basePrice = 11.50;
        double finalPrice;

        finalPrice = basePrice + envelopeFeature.getCost();
        return finalPrice;
    }

    /**
     * Determines the flight category of the envelope based on its feature.
     *
     * @return the flight category of the envelope
     */
    @Override
    public FlightCategory getClassification()
    {
        FlightCategory classification;

        classification = envelopeFeature.getClassification();
        return classification;
    }

    /**
     * Returns the envelope feature.
     *
     * @return the envelope feature
     */
    public EnvelopeFeature getEnvelopeFeature()
    {
        return envelopeFeature;
    }

    /**
     * Sets the envelope feature.
     *
     * @param envelopeFeature the new envelope feature
     */
    public void setEnvelopeFeature(EnvelopeFeature envelopeFeature)
    {
        this.envelopeFeature = envelopeFeature;
    }

    /**
     * Returns a string representation of the envelope.
     *
     * @return a string describing the envelope
     */
    @Override
    public String toString()
    {
        String message = "Envelope (" + envelopeFeature + ") - " +
                getClassification();
        return message;
    }
}