import java.util.ArrayList;

/**
 * Manages the collection of orders.
 * Handles adding, delivering, searching, and printing orders.
 * Separates order management from UI and business logic.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class OrderManager
{
    private ArrayList<Order> orders;

    /**
     * Default constructor that creates an empty order manager.
     */
    public OrderManager()
    {
        this.orders = new ArrayList<>();
    }

    /**
     * Parameterized constructor that creates an order manager with existing orders.
     *
     * @param orders the existing list of orders
     */
    public OrderManager(ArrayList<Order> orders)
    {
        this.orders = orders;
    }

    /**
     * Adds an order to the list.
     *
     * @param order the order to add
     */
    public void addOrder(Order order)
    {
        orders.add(order);
    }

    /**
     * Delivers the oldest order (FIFO) and removes it from the list.
     *
     * @return the delivered order, or null if list is empty
     */
    public Order deliverOrder()
    {
        Order deliveredOrder = null;

        if (!orders.isEmpty())
        {
            deliveredOrder = orders.remove(0);
        }

        return deliveredOrder;
    }

    /**
     * Finds an order by its ID.
     *
     * @param searchID the order ID to search for
     * @return the found order, or null if not found
     */
    public Order findOrderByID(int searchID)
    {
        Order foundOrder = null;

        for (Order order : orders)
        {
            if (order.getOrderID() == searchID)
            {
                foundOrder = order;
            }
        }

        return foundOrder;
    }

    /**
     * Returns the list of orders.
     *
     * @return the list of orders
     */
    public ArrayList<Order> getOrders()
    {
        ArrayList<Order> copiedOrders;

        copiedOrders = new ArrayList<>(orders);
        return copiedOrders;
    }

    /**
     * Checks if the order list is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        boolean empty;

        empty = orders.isEmpty();
        return empty;
    }

    /**
     * Prints all orders in the system.
     *
     * @param console the console IO handler for display
     */
    public void printAllOrders(ConsoleIO console)
    {
        if (orders.isEmpty())
        {
            console.display("No orders in the system.");
        }
        else
        {
            for (int i = 0; i < orders.size(); i++)
            {
                console.display(orders.get(i).toString());
            }
        }
    }

    /**
     * Sets the list of orders.
     *
     * @param orders the new list of orders
     */
    public void setOrders(ArrayList<Order> orders)
    {
        this.orders = orders;
    }

    /**
     * Returns the number of orders in the list.
     *
     * @return the order count
     */
    public int size()
    {
        int orderCount;

        orderCount = orders.size();
        return orderCount;
    }

    /**
     * Returns a string representation of the OrderManager.
     *
     * @return a string describing the order manager
     */
    @Override
    public String toString()
    {
        String message;

        message = "OrderManager containing " +
                orders.size() + " orders";
        return message;
    }
}