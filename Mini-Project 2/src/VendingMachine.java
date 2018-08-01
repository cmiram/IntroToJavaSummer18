import java.util.Arrays;
import java.util.Scanner;

/***
 * this class defines the vending machine
 */

public class VendingMachine
{
    // array of change in this machine
    private Change[] change;

    // array of items sold in this machine
    private InventoryItem[] inventory;

    // scanner to read in inputs from users
    private Scanner input = new Scanner(System.in);

    /***
     * constructor for this vending machine
     * @param change array of money used by this machine
     * @param inventory array of items sold by this machine
     */
    public VendingMachine(Change[] change, InventoryItem[] inventory)
    {
        this.change = sortDescendingOrder(change);
        this.inventory = inventory;
    }

    /***
     * runs the machine by prompting the user for input
     */
    public void run()
    {
        // set choice to enter loop and immediately display options
        int menuChoice = 1;
        // loop until user decides to exit
        while(menuChoice != 5)
        {
            // handle menu choice of user
            handleMenuChoice(menuChoice);
            System.out.print("\nCommand: ");
            // grab users choice
            menuChoice = input.nextInt();
        }
        // say goodbye to user
        System.out.println("Thank you for using IPUG Inc. vending machine");
        System.out.println("Have a great day!");
    }

    /***
     * sorts array of change from highest to lowest value
     * @param change array of change used by vending machine
     * @return sorted list of change
     */
    private Change[] sortDescendingOrder(Change[] change)
    {
        // first sort by array ascending by built-in functionality
        Arrays.sort(change);
        // loop to reverse order of array
        for(int i=0; i<change.length/2; i++)
        {
            Change tmp = change[i];
            change[i] = change[change.length - i - 1];
            change[change.length - i - 1] = tmp;
        }
        return change;
    }

    /***
     * handle user's menu choice
     * @param choice integer input of user's choice
     */
    private void handleMenuChoice(int choice)
    {
        switch(choice)
        {
        case 1:
            listCommands();
            break;
        case 2:
            displayInventory();
            break;
        case 3:
            displayChange();
            break;
        case 4:
            purchaseItem();
            break;
        default:
            // unknown option chosen so prompt user
            System.out.println("Unknown option chosen. Please choose another");
        }
    }

    /***
     * writes out list of available options for user
     */
    private void listCommands()
    {
        System.out.println("Available Commands\n");
        System.out.println("1: Show Commands");
        System.out.println("2: Display Inventory");
        System.out.println("3: Display Change");
        System.out.println("4: Purchase an Item");
        System.out.println("5: Exit");
    }

    /***
     * loops over inventory options and writes them out to user
     */
    private void displayInventory()
    {
        for(int i = 0; i < inventory.length; i++)
        {
            System.out.println("Purchase ID: " + i + " " + inventory[i].toString());
        }
    }

    /***
     * loops over change options and writes them out to user
     */
    private void displayChange()
    {
        for(Change c : change)
        {
            System.out.println(c.toString());
        }
    }

    /***
     * attempts to purchase item by ensuring it is available, the user
     * provides enough money, and change is available if needed
     */
    private void purchaseItem()
    {
        // set to -1 to enter loop
        int purchaseId = -1;
        // loop until valid item id is selected
        while(purchaseId < 0 || purchaseId > inventory.length)
        {
            System.out.print("Enter Purchase ID for item to buy: ");
            purchaseId = input.nextInt();
            if(purchaseId < 0 || purchaseId > inventory.length)
            {
                System.out.println("Invalid ID. Please try again.");
            }
        }

        // if selected item has no quantity return immediately
        if(inventory[purchaseId].getQuantity() == 0)
        {
            System.out.println("Sorry that item is sold out!");
            return;
        }

        // store product chosen for ease of use later
        Product toPurchase = inventory[purchaseId].getProduct();
        System.out.println("Total Cost: " + toPurchase.getPrice());

        // set to -1 to enter loop
        int currencyInputs = -1;
        // loop until user enters positive number of currency inputs
        while(currencyInputs < 1)
        {
            System.out.print("How many currency items to input: ");
            currencyInputs = input.nextInt();
            if(currencyInputs < 1)
            {
                System.out.println("Currency input must be greater than 0");
            }
        }

        // list off currency available for user to enter
        for(int i = 0; i < change.length; i++)
        {
            System.out.println("Input ID: " + i + " " + change[i].getCurrency().toString());
        }

        // array that maps to change items entered by the user
        int[] currInputIndexed = new int[change.length];
        double totalInput = 0;
        int currencyID = -1;
        System.out.println("\nPlease Enter ID of your currency inputs");
        // loops over number of currency inputs
        for(int i = 0; i < currencyInputs; i++)
        {
            // loop until user enters valid change id
            while(currencyID < 0 || currencyID > change.length)
            {
                System.out.print("ID: ");
                currencyID = input.nextInt();
                if(currencyID < 0 || currencyID > change.length)
                {
                    System.out.println("Invalid ID. Pleast try again.");
                }
            }
            // store off index of entered currency and total amount input
            currInputIndexed[currencyID] += 1;
            totalInput += change[currencyID].getCurrency().getValue();
            currencyID = -1;
        }

        // if entered currency is less than price immediately exit
        if(totalInput < toPurchase.getPrice())
        {
            System.out.println("Not enough currency given.");
            System.out.println("You gave " + totalInput + ", but " + toPurchase.getPrice() + " is required.");
            System.out.println("Returning your money...");
            return;
        }

        // calculate change due
        double changeDue = totalInput - toPurchase.getPrice();
        System.out.println("Change Due: " + changeDue);
        int[] changeReturnedIndexed = new int[change.length];
        // if change is due calculate currency items to use
        if(changeDue > 0)
        {
            changeReturnedIndexed = calculateChangeToReturn(changeDue);
            // if available change cannot be totaled to change due
            // we must cancel the transaction
            if(changeReturnedIndexed == null)
            {
                System.out.println("Inadequate change available.");
                System.out.println("Please try again with different inputs.");
                System.out.println("Returning your money...");
                return;
            }
        }
        // apply change returned and input
        applyChangeChanges(changeReturnedIndexed, currInputIndexed);
        // decrement quantity of item sold
        inventory[purchaseId].setQuantity(
                inventory[purchaseId].getQuantity()-1);
        System.out.println("Here is your " + toPurchase.getName() + "!");
        System.out.println("Enjoy!!");
    }

    /***
     * calculates the specific change items to return to user
     * @param changeDue total change due
     * @return array of change items indexed by amount to change by
     */
    private int[] calculateChangeToReturn(double changeDue)
    {
        int[] toReturnAsChange = new int[change.length];
        // start index at 0 being the highest valued currency
        int index = 0;
        // so long as change is due and haven't gone over array length
        // continue trying to calculate change
        while(changeDue > 0 && index < change.length)
        {
            // as long as adding currency return item would not go over
            // change amount continue adding it to amount to return
            while(changeDue - change[index].getCurrency().getValue() >= 0
                    && change[index].getQuantity() -
                    toReturnAsChange[index] - 1 >= 0)
            {
                // increment amount to return of this type by one
                toReturnAsChange[index] += 1;
                // delete amount added as change from amount due
                changeDue -= change[index].getCurrency().getValue();
            }
            // increment index if more of this type would go over amount due
            index++;
        }
        // if can't create perfect change return null so we can cancel purchase
        return changeDue == 0 ? toReturnAsChange : null;
    }

    /***
     * applies changes made by purchase and change returned to user
     * @param changeReturnedIndexed index of change items returned
     * @param currInputIndexed index of change items input by user
     */
    private void applyChangeChanges(int[] changeReturnedIndexed,
                                    int[] currInputIndexed)
    {
        // loop over change items in vending machine and apply modifications
        for(int i=0; i<change.length; i++)
        {
            change[i].setQuantity(
                    change[i].getQuantity() + currInputIndexed[i]
                            - changeReturnedIndexed[i]);
        }
    }

    /***
     * get the array of change items used in this machine
     * @return array of Change objects
     */
    public Change[] getChange()
    {
        return change;
    }

    /***
     * sets the change items used in this machine
     * @param change array of Change objects
     */
    public void setChange(Change[] change)
    {
        this.change = change;
    }

    /***
     * gets the items sold in this machine
     * @return the array of items sold
     */
    public InventoryItem[] getInventory()
    {
        return inventory;
    }

    /***
     * sets the items sold by this machine
     * @param inventory array of items to sell
     */
    public void setInventory(InventoryItem[] inventory)
    {
        this.inventory = inventory;
    }
}
