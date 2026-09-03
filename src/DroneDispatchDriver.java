/**
 * The main driver class for the Drone Dispatch Management System.
 * This class contains the entry point of the application.
 *
 * @author Yun Pei En
 * @version 1.0
 */
public class DroneDispatchDriver
{
    /**
     * Default constructor for DroneDispatchDriver.
     */
    public DroneDispatchDriver()
    {
        // Empty constructor as required by coding standards.
    }

    /**
     * The main method that starts the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        DroneDispatchManager droneDispatchManager = new DroneDispatchManager();
        droneDispatchManager.run();
    }
}