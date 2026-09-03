import java.util.ArrayList;

/**
 * The DroneDispatchManager class coordinates the drone dispatch system.
 * It handles the main workflow and delegates order management to OrderManager.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class DroneDispatchManager
{
    private final ConsoleIO console;
    private final ParcelMenuHelper parcelMenuHelper;
    private final OrderManager orderManager;
    private int nextCustomerID;

    private static final int MAXIMUM_BOX_ITEMS = 5;

    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MINIMUM_ADDRESS_LENGTH = 10;

    private static final int MINIMUM_ITEM_CHOICE = 1;
    private static final int MAXIMUM_ITEM_CHOICE = 7;

    private static final int MINIMUM_ENVELOPE_FEATURE = 1;
    private static final int MAXIMUM_ENVELOPE_FEATURE = 4;

    private static final int MINIMUM_MAIN_MENU_OPTION = 1;
    private static final int MAXIMUM_MAIN_MENU_OPTION = 6;

    private static final int MINIMUM_UPDATE_OPTION = 1;
    private static final int MAXIMUM_UPDATE_OPTION = 5;

    private static final int MINIMUM_PARCEL_UPDATE_OPTION = 1;
    private static final int MAXIMUM_PARCEL_UPDATE_OPTION = 3;

    /**
     * Default constructor that creates a manager with default console.
     */
    public DroneDispatchManager()
    {
        this(new ConsoleIO());
    }

    /**
     * Parameterized constructor that creates a manager with specified console.
     *
     * @param console the console IO handler
     */
    public DroneDispatchManager(ConsoleIO console)
    {
        this.console = console;
        this.parcelMenuHelper = new ParcelMenuHelper(console);
        this.orderManager = new OrderManager();
        this.nextCustomerID = 1;
    }

    /**
     * Adds a box to the specified order.
     *
     * @param order the order to add the box to
     */
    private void addBoxToOrder(Order order)
    {
        Box box = new Box();
        boolean addingMoreItems = true;

        console.display("\nAdding Box...");
        console.display("A box can contain up to " +
                MAXIMUM_BOX_ITEMS + " items.");

        while (addingMoreItems && box.getItems().size() < MAXIMUM_BOX_ITEMS)
        {
            console.display("\nCurrent items in box: " +
                    box.getItems().size() + "/" + MAXIMUM_BOX_ITEMS);
            parcelMenuHelper.displayItemTypes();

            int itemChoice;
            boolean validChoice = false;

            itemChoice = console.getValidIntInput(
                    "Select item to add (" +
                            MINIMUM_ITEM_CHOICE + "-" +
                            MAXIMUM_ITEM_CHOICE + "): ");

            if (itemChoice >= MINIMUM_ITEM_CHOICE
                    && itemChoice <= MAXIMUM_ITEM_CHOICE)
            {
                addingMoreItems =
                        processBoxItemChoice(box, itemChoice, addingMoreItems);
            }
            else
            {
                console.display("Invalid choice. Please select " +
                        MINIMUM_ITEM_CHOICE + "-" +
                        MAXIMUM_ITEM_CHOICE + " only.");
            }
        }

        addOrCancelBox(order, box);
    }

    /**
     * Adds an envelope to the specified order.
     *
     * @param order the order to add the envelope to
     */
    private void addEnvelopeToOrder(Order order)
    {
        console.display("\nAdding Envelope...\n");

        boolean validChoice = false;

        while (!validChoice)
        {
            parcelMenuHelper.displayEnvelopeFeatures();

            int featureChoice = console.getValidIntInput(
                    "Select envelope feature (" +
                            MINIMUM_ENVELOPE_FEATURE + "-" +
                            MAXIMUM_ENVELOPE_FEATURE + "): ");

            EnvelopeFeature feature =
                    parcelMenuHelper.getEnvelopeFeatureByChoice(featureChoice);

            if (feature != null)
            {
                Envelope envelope = new Envelope(feature);
                order.addParcel(envelope);

                console.display("Envelope added to order. Cost: $" +
                        String.format("%.2f", envelope.calculatePrice()));

                validChoice = true;
            }
            else
            {
                console.display("Invalid choice. Please select " +
                        MINIMUM_ENVELOPE_FEATURE + "-" +
                        MAXIMUM_ENVELOPE_FEATURE + ".\n");
            }
        }
    }

    /**
     * Adds a new order to the system.
     */
    private void addNewOrder()
    {
        console.lineBreak();
        console.display("ADD A NEW ORDER");

        Customer customer = createNewCustomer();
        Order order = new Order(customer, new ArrayList<>());

        boolean orderHasParcels = addParcelsToOrder(order);

        if (orderHasParcels)
        {
            orderManager.addOrder(order);
            console.display("\nOrder added successfully!");
            console.display(order.toString());
        }
        else
        {
            console.display("Order must contain at least one parcel. " +
                    "Order not added.");
        }

        console.lineBreak();
    }

    /**
     * Adds the box to the order or cancels if empty.
     *
     * @param order the order to add the box to
     * @param box the box to add
     */
    private void addOrCancelBox(Order order, Box box)
    {
        if (!box.getItems().isEmpty())
        {
            order.addParcel(box);
            console.display("Box added to order. Cost: $" +
                    String.format("%.2f", box.calculatePrice()));
        }
        else
        {
            console.display("No items added. Box not added to order.");
        }
    }

    /**
     * Adds a parcel to an existing order.
     *
     * @param order the order to add the parcel to
     * @return true if a parcel was added
     */
    private boolean addParcelToExistingOrder(Order order)
    {
        boolean parcelAdded = false;

        if (order.canAcceptMoreParcels())
        {
            parcelMenuHelper.displayParcelTypeMenuForUpdate();

            int parcelChoice = console.getValidIntInput(
                    "Enter choice (" +
                            MINIMUM_PARCEL_UPDATE_OPTION + "-" +
                            MAXIMUM_PARCEL_UPDATE_OPTION + "): ");

            switch (parcelChoice)
            {
                case 1:
                    addBoxToOrder(order);
                    parcelAdded = true;
                    break;

                case 2:
                    addEnvelopeToOrder(order);
                    parcelAdded = true;
                    break;

                default:
                    console.display("Invalid choice. No parcel added.");
                    break;
            }
        }
        else
        {
            console.display("Cannot add more parcels. " +
                    "Maximum parcel limit reached.");
        }

        return parcelAdded;
    }

    /**
     * Adds parcels to an order based on user input.
     *
     * @param order the order to add parcels to
     * @return true if at least one parcel was added
     */
    private boolean addParcelsToOrder(Order order)
    {
        boolean addingParcels;
        boolean hasAtLeastOneParcel;

        addingParcels = true;

        while (addingParcels &&
                order.getParcelCount() < order.getDeliveryConstraint())
        {
            console.display("\nCurrent parcels: " + order.getParcelCount() +
                    "/" + order.getDeliveryConstraint());

            parcelMenuHelper.displayParcelSelectionMenu();

            int choice;
            boolean canContinue;

            choice = console.getValidIntInput("Enter choice (" +
                    MINIMUM_PARCEL_UPDATE_OPTION + "-" +
                    MAXIMUM_PARCEL_UPDATE_OPTION + "): ");
            canContinue = processParcelSelection(order, choice);

            addingParcels = canContinue;

            if (!order.canAcceptMoreParcels())
            {
                console.display
                        ("\nMaximum parcel limit reached for this order.");
                addingParcels = false;
            }
        }

        if (order.getParcelCount() > 0)
        {
            hasAtLeastOneParcel = true;
        }
        else
        {
            hasAtLeastOneParcel = false;
        }

        return hasAtLeastOneParcel;
    }

    /**
     * Asks the user if they want to add another item to the box.
     *
     * @param addingMoreItems current flag state
     * @return updated flag state
     */
    private boolean checkToAddAnotherItem(boolean addingMoreItems)
    {
        boolean validResponse = false;
        boolean stillAdding = addingMoreItems;

        while (!validResponse)
        {
            String continueChoice =
                    console.getStringInput("Add another item? (y/n): ");

            switch (continueChoice.toLowerCase())
            {
                case "y":
                    validResponse = true;
                    break;

                case "n":
                    stillAdding = false;
                    validResponse = true;
                    break;

                default:
                    console.display("Invalid input. " +
                            "Please enter 'y' or 'n'.\n");
                    break;
            }
        }

        return stillAdding;
    }

    /**
     * Creates a new customer from user input.
     *
     * @return the created customer
     */
    private Customer createNewCustomer()
    {
        String customerName = console.getValidStringByMinLength
                ("Enter customer name: ", MINIMUM_NAME_LENGTH);
        String contactNumber = console.getValidContactNumber
                ("Enter customer contact number: ");
        String address = console.getValidStringByMinLength
                ("Enter delivery address: ", MINIMUM_ADDRESS_LENGTH);

        Customer customer = new Customer(nextCustomerID, customerName,
                contactNumber, address);
        nextCustomerID += 1;

        return customer;
    }

    /**
     * Delivers the oldest order (FIFO) and removes it from the list.
     */
    private void deliverOrder()
    {
        console.lineBreak();
        console.display("DELIVER AN ORDER");

        if (orderManager.isEmpty())
        {
            console.display("No orders to deliver. The queue is empty.");
        }
        else
        {
            Order deliveredOrder = orderManager.deliverOrder();
            console.display("Order delivered successfully!");
            console.display(deliveredOrder.toString());
            console.display("\nRemaining orders in queue: " +
                    orderManager.size());
        }

        console.lineBreak();
    }

    /**
     * Displays the main menu options.
     */
    public void displayMenu()
    {
        String menu = """
                      Choose an option:
                      1. Add a new order
                      2. Deliver an order
                      3. Print all orders
                      4. Search for an order
                      5. Update an order
                      6. Exit the program
                      """;

        console.display(menu);
    }

    /**
     * Handles adding an item to a box.
     *
     * @param box the box to add the item to
     * @param selectedItem the item to add
     */
    private void handleAddItemToBox(Box box, ItemType selectedItem)
    {
        if (box.addItem(selectedItem))
        {
            console.display("Added: " + selectedItem);
        }
        else
        {
            console.display("Box is full. Cannot add " + selectedItem);
        }
    }

    /**
     * Handles the finish option when adding items to a box.
     *
     * @param box the box being filled
     * @return true if still adding items, false if finished
     */
    private boolean handleFinishBox(Box box)
    {
        boolean result;

        if (box.getItems().isEmpty())
        {
            console.display("Box must contain at least one item. " +
                    "Please add an item.");
            result = true;
        }
        else
        {
            result = false;
        }

        return result;
    }

    /**
     * Prints all orders currently in the system.
     */
    private void printAllOrders()
    {
        console.lineBreak();
        console.display("ALL ORDERS");
        console.display("Total orders: " + orderManager.size());
        orderManager.printAllOrders(console);
        console.lineBreak();
    }

    /**
     * Processes the user's item choice for a box.
     *
     * @param box the box being filled
     * @param itemChoice the user's menu choice
     * @param addingMoreItems current flag state
     * @return updated addingMoreItems flag
     */
    private boolean processBoxItemChoice(Box box, int itemChoice,
                                         boolean addingMoreItems)
    {
        boolean stillAdding = addingMoreItems;

        if (itemChoice == MAXIMUM_ITEM_CHOICE)
        {
            stillAdding = handleFinishBox(box);
        }
        else
        {
            ItemType selectedItem =
                    parcelMenuHelper.getItemTypeByChoice(itemChoice);
            handleAddItemToBox(box, selectedItem);

            if (box.getItems().size() < MAXIMUM_BOX_ITEMS)
            {
                stillAdding = checkToAddAnotherItem(stillAdding);
            }
        }

        return stillAdding;
    }

    /**
     * Processes the user's parcel type selection.
     *
     * @param order the order being built
     * @param choice the user's menu choice
     * @return true if still adding parcels, false if finished
     */
    private boolean processParcelSelection(Order order, int choice)
    {
        boolean stillAdding = true;

        switch (choice)
        {
            case 1:
                addBoxToOrder(order);
                break;

            case 2:
                addEnvelopeToOrder(order);
                break;

            case 3:
                stillAdding = false;
                break;

            default:
                console.display("Invalid choice. Please try again.");
                break;
        }

        return stillAdding;
    }

    /**
     * Processes the user's update option choices.
     *
     * @param orderToUpdate the order to update
     * @return true if an update was performed
     */
    private boolean processUpdateOptions(Order orderToUpdate)
    {
        parcelMenuHelper.displayUpdateOrderMenu();

        int choice = console.getValidIntInput("Enter choice (" +
                MINIMUM_UPDATE_OPTION + "-" + MAXIMUM_UPDATE_OPTION + "): ");
        boolean updatePerformed = false;

        switch (choice)
        {
            case 1:
                updateCustomerName(orderToUpdate);
                updatePerformed = true;
                break;

            case 2:
                updateCustomerContact(orderToUpdate);
                updatePerformed = true;
                break;

            case 3:
                updateCustomerAddress(orderToUpdate);
                updatePerformed = true;
                break;

            case 4:
                updatePerformed = addParcelToExistingOrder(orderToUpdate);
                break;

            case 5:
                console.display("Update cancelled.");
                break;

            default:
                console.display("Invalid choice. Update cancelled.");
                break;
        }

        return updatePerformed;
    }

    /**
     * Runs the main program loop.
     */
    public void run()
    {
        int option;
        boolean exitProgram = false;

        console.display("Welcome to the Drone Dispatch Management System!\n");

        while (!exitProgram)
        {
            displayMenu();

            try
            {
                option = console.getValidIntInput(
                        "Please enter your choice (" +
                                MINIMUM_MAIN_MENU_OPTION + "-" +
                                MAXIMUM_MAIN_MENU_OPTION + "): ");

                switch (option)
                {
                    case 1:
                        addNewOrder();
                        break;

                    case 2:
                        deliverOrder();
                        break;

                    case 3:
                        printAllOrders();
                        break;

                    case 4:
                        searchOrder();
                        break;

                    case 5:
                        updateOrder();
                        break;

                    case 6:
                        console.display("\nThank you for using the " +
                                "Drone Dispatch System. Goodbye!");
                        exitProgram = true;
                        break;

                    default:
                        console.display("Invalid choice. " +
                                "Please enter a number between " +
                                MINIMUM_MAIN_MENU_OPTION + " and " +
                                MAXIMUM_MAIN_MENU_OPTION + ".\n");
                        break;
                }
            }
            catch (NumberFormatException exception)
            {
                console.display("Invalid input. " +
                        "Please enter a valid number.\n");
            }
        }

        console.close();
    }

    /**
     * Searches for and displays an order by its ID.
     */
    private void searchOrder()
    {
        boolean hasOrders = true;

        if (orderManager.isEmpty())
        {
            console.display("No orders in system. Nothing to search.\n");
            hasOrders = false;
        }

        if (hasOrders)
        {
            console.lineBreak();
            console.display("SEARCH FOR AN ORDER" +
                    "(Enter the number only, e.g., 101)");

            int searchID = console.getValidIntInput("Enter Order ID to search: ");
            Order foundOrder = orderManager.findOrderByID(searchID);

            if (foundOrder != null)
            {
                console.display("\nOrder found:");
                console.display(foundOrder.toString());
            }
            else
            {
                console.display("Order not found.");
            }

            console.lineBreak();
        }
    }

    /**
     * Returns a string representation of the DroneDispatchManager.
     *
     * @return a string describing the manager
     */
    @Override
    public String toString()
    {
        String message;

        message = "DroneDispatchManager with " +
                orderManager.size() + " active orders";
        return message;
    }

    /**
     * Updates the customer address for an order.
     *
     * @param order the order to update
     */
    private void updateCustomerAddress(Order order)
    {
        String newAddress =
                console.getValidStringInput("Enter new delivery address: ");
        order.getCustomer().setCustomerAddress(newAddress);
        console.display("Delivery address updated successfully!");
    }

    /**
     * Updates the customer contact number for an order.
     *
     * @param order the order to update
     */
    private void updateCustomerContact(Order order)
    {
        String newContact =
                console.getValidStringInput("Enter new contact number: ");
        order.getCustomer().setCustomerContactNumber(newContact);
        console.display("Contact number updated successfully!");
    }

    /**
     * Updates the customer name for an order.
     *
     * @param order the order to update
     */
    private void updateCustomerName(Order order)
    {
        String newName =
                console.getValidStringInput("Enter new customer name: ");
        order.getCustomer().setCustomerName(newName);
        console.display("Customer name updated successfully!");
    }

    /**
     * Updates an existing order.
     */
    private void updateOrder()
    {
        boolean hasOrders = true;

        if (orderManager.isEmpty())
        {
            console.display("No orders in system. Nothing to update.\n");
            hasOrders = false;
        }

        if (hasOrders)
        {
            console.lineBreak();
            console.display("UPDATE AN ORDER" +
                    "(Enter the number only, e.g., 101)");

            int searchID = console.getValidIntInput("Enter Order ID to search: ");
            Order orderToUpdate = orderManager.findOrderByID(searchID);

            if (orderToUpdate == null)
            {
                console.display("Order not found. Update cancelled.");
            }
            else
            {
                boolean updatePerformed = processUpdateOptions(orderToUpdate);

                if (updatePerformed)
                {
                    console.display("\nOrder updated successfully!");
                    console.display(orderToUpdate.toString());
                }
            }

            console.lineBreak();
        }
    }
}