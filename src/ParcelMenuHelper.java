/**
 * Helper class for displaying parcel options and converting user choices.
 * Separates UI display logic from business logic.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class ParcelMenuHelper
{
    private final ConsoleIO console;

    /**
     * Default constructor that creates a ParcelUI with a default ConsoleIO.
     */
    public ParcelMenuHelper()
    {
        this(new ConsoleIO());
    }

    /**
     * Constructs a ParcelDisplayHelper with the specified console.
     *
     * @param console the console IO handler
     */
    public ParcelMenuHelper(ConsoleIO console)
    {
        this.console = console;
    }

    /**
     * Displays the available envelope features.
     */
    public void displayEnvelopeFeatures()
    {
        String menu = """
                      Available envelope features:
                      1. Tamper-evident ($6.80) - Hazardous
                      2. Waterproof ($5.20) - Hazardous
                      3. Confidential ($5.20) - Fragile
                      4. Standard ($4.00) - Standard
                      """;

        console.display(menu);
    }

    /**
     * Displays the available item types for boxes.
     */
    public void displayItemTypes()
    {
        String menu = """
                      Available items:
                      1. Clothes ($2.00) - Standard
                      2. Documents ($2.00) - Standard
                      3. Liquids ($2.50) - Hazardous
                      4. Glassware ($2.00) - Fragile
                      5. Electronics ($3.50) - Hazardous
                      6. Perishables ($2.00) - Fragile
                      7. Finish adding items to this box
                      """;

        console.display(menu);
    }

    /**
     * Displays the parcel selection menu for adding to an order.
     */
    public void displayParcelSelectionMenu()
    {
        String menu = """
                      Select parcel type:
                      1. Box
                      2. Envelope
                      3. Finish adding parcels
                      """;

        console.display(menu);
    }

    /**
     * Displays the parcel type menu for updating an order.
     */
    public void displayParcelTypeMenuForUpdate()
    {
        String menu = """
                      Select parcel type to add:
                      1. Box
                      2. Envelope
                      """;

        console.display(menu);
    }

    /**
     * Displays the update order options menu.
     */
    public void displayUpdateOrderMenu()
    {
        String menu = """
                      \nOrder found. What would you like to update?:
                      1. Customer name
                      2. Customer contact number
                      3. Customer address
                      4. Add a new parcel
                      5. Cancel update
                      """;

        console.display(menu);
    }

    /**
     * Returns the EnvelopeFeature corresponding to the user's choice.
     *
     * @param choice the user's menu choice
     * @return the selected EnvelopeFeature, or null if invalid
     */
    public EnvelopeFeature getEnvelopeFeatureByChoice(int choice)
    {
        EnvelopeFeature selectedFeature = null;

        switch (choice)
        {
            case 1:
                selectedFeature = EnvelopeFeature.TAMPER_EVIDENT;
                break;

            case 2:
                selectedFeature = EnvelopeFeature.WATERPROOF;
                break;

            case 3:
                selectedFeature = EnvelopeFeature.CONFIDENTIAL;
                break;

            case 4:
                selectedFeature = EnvelopeFeature.STANDARD;
                break;

            default:
                break;
        }

        return selectedFeature;
    }

    /**
     * Returns the ItemType corresponding to the user's choice.
     *
     * @param choice the user's menu choice
     * @return the selected ItemType, or null if invalid
     */
    public ItemType getItemTypeByChoice(int choice)
    {
        ItemType selectedItem = null;

        switch (choice)
        {
            case 1:
                selectedItem = ItemType.CLOTHES;
                break;

            case 2:
                selectedItem = ItemType.DOCUMENTS;
                break;

            case 3:
                selectedItem = ItemType.LIQUIDS;
                break;

            case 4:
                selectedItem = ItemType.GLASSWARE;
                break;

            case 5:
                selectedItem = ItemType.ELECTRONICS;
                break;

            case 6:
                selectedItem = ItemType.PERISHABLES;
                break;

            case 7:
                console.display("Finishing box...");
                break;

            default:
                break;
        }

        return selectedItem;
    }
}