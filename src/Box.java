import java.util.ArrayList;

/**
 * The Box class represents a box parcel that can contain multiple items.
 * A box has a maximum capacity of 5 items.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class Box extends Parcel
{
    private ArrayList<ItemType> items;
    private static final int MAXIMUM_NUMBER_OF_ITEMS = 5;

    /**
     * Default constructor that creates an empty Box.
     */
    public Box()
    {
        this(new ArrayList<>());
    }

    /**
     * Parameterized constructor that creates a Box with specified items.
     *
     * @param items the list of items to include in the box
     */
    public Box(ArrayList<ItemType> items)
    {
        this.items = new ArrayList<>(items);
    }

    /**
     * Adds an item to the box if capacity allows.
     *
     * @param item the item to add
     * @return true if item was added, false if box is full
     */
    public boolean addItem(ItemType item)
    {
        boolean canAddItem;

        if (items.size() >= MAXIMUM_NUMBER_OF_ITEMS)
        {
            canAddItem = false;
        }
        else
        {
            items.add(item);
            canAddItem = true;
        }

        return canAddItem;
    }

    /**
     * Calculates the total price of the box including base price and item costs.
     *
     * @return the total price of the box
     */
    @Override
    public double calculatePrice()
    {
        double basePrice = 11.50;
        double totalItemCost = 0.0;
        double finalPrice;

        for (ItemType currentItem : items)
        {
            totalItemCost += currentItem.getCost();
        }

        finalPrice = basePrice + totalItemCost;
        return finalPrice;
    }

    /**
     * Determines the flight category of the box based on its contents.
     * Hazardous items take priority over fragile, which takes priority over standard.
     *
     * @return the flight category of the box
     */
    @Override
    public FlightCategory getClassification()
    {
        FlightCategory highestPriority = FlightCategory.STANDARD;
        boolean hasHazardous = false;
        boolean hasFragile = false;

        for (ItemType currentItem : items)
        {
            FlightCategory itemCategory = currentItem.getClassification();

            if (itemCategory == FlightCategory.HAZARDOUS)
            {
                hasHazardous = true;
            }
            else if (itemCategory == FlightCategory.FRAGILE)
            {
                hasFragile = true;
            }
        }

        if (hasHazardous)
        {
            highestPriority = FlightCategory.HAZARDOUS;
        }
        else if (hasFragile)
        {
            highestPriority = FlightCategory.FRAGILE;
        }

        return highestPriority;
    }

    /**
     * Returns the list of items in the box.
     *
     * @return the list of items
     */
    public ArrayList<ItemType> getItems()
    {
        ArrayList<ItemType> copiedItems;

        copiedItems = new ArrayList<>(items);
        return copiedItems;
    }

    /**
     * Sets the list of items in the box.
     *
     * @param items the new list of items
     */
    public void setItems(ArrayList<ItemType> items)
    {
        this.items = items;
    }

    /**
     * Returns a string representation of the box.
     *
     * @return a string describing the box
     */
    @Override
    public String toString()
    {
        String message = "Box with " + items.size() +
                " item(s) - " + getClassification();
        return message;
    }
}