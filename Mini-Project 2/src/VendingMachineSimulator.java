import java.io.*;

/***
 * This class is the entry point of running the vending machine.
 * It loads inventory and change from specified files.
 */

public class VendingMachineSimulator
{

    /***
     * Entry point to run simulator.
     * @param args no arguments are used
     */
    public static void main(String[] args)
    {
        // instantiate change and inventory before try so they're available
        // anywhere in main
        Change[] change = null;
        InventoryItem[] inventory = null;

        try
        {
            // attempts to load inital change state from file
            change = loadVMChange();
        }
        catch(IOException e)
        {
            // if this fails quit program because change is required
            System.out.println("Could not load change!");
            e.printStackTrace();
            System.exit(-1);
        }

        try
        {
            // attempts to load initial inventory from file
            inventory = loadVMInventory();
        }
        catch(IOException e)
        {
            // if this fails quit program because inventory is required
            System.out.println("Could not load inventory!");
            e.printStackTrace();
            System.exit(-1);
        }
        // instantiate vending machine with initial state values
        VendingMachine vendingMachine = new VendingMachine(change, inventory);
        // run simulation until user exits program
        vendingMachine.run();
    }

    /***
     * loads initial change state from specific file
     * @return array of all change items
     * @throws IOException if fails so we can immediately exit
     */
    private static Change[] loadVMChange() throws IOException
    {
        File changeSrc = new File("Resources/Currencies.txt");
        // count number of lines to determine how many different
        // change values are defined by this state
        Change[] change = new Change[countLinesInFile(changeSrc)];
        // open readers
        FileReader reader = new FileReader(changeSrc.getAbsolutePath());
        BufferedReader buffReader = new BufferedReader(reader);
        String line;
        int index = 0;
        while((line = buffReader.readLine()) != null)
        {
            // split each line by spaces
            String[] currencyArgs = line.split("\\s+");
            // convert values to proper type and create necessary objects
            double value = Double.valueOf(currencyArgs[0]);
            Material material = new Material(currencyArgs[1]);
            int quantity = Integer.valueOf(currencyArgs[3]);
            // add new change object as defined by this line in the file
            change[index++] = new Change(new Currency(value, material, currencyArgs[2]), quantity);
        }
        // close readers and return our change array
        buffReader.close();
        reader.close();
        return change;
    }

    /***
     * loads initial inventory state from specific file
     * @return array of inventory items for this vending machine
     * @throws IOException if read fails so we can quit immediately
     */
    private static InventoryItem[] loadVMInventory() throws IOException
    {
        File inventorySrc = new File("Resources/Inventory.txt");
        // count lines in file to know how many items there will be
        InventoryItem[] inventory =
                new InventoryItem[countLinesInFile(inventorySrc)];
        // open readers
        FileReader reader = new FileReader(inventorySrc.getAbsolutePath());
        BufferedReader buffReader = new BufferedReader(reader);
        String line;
        int index = 0;
        while((line = buffReader.readLine()) != null)
        {
            // split line by spaces
            String[] inventoryArgs = line.split("\\s+");
            // use values to create objects and convert to useful type
            Packaging packaging = new Packaging(inventoryArgs[1]);
            double price = Double.valueOf(inventoryArgs[2]);
            int quantity = Integer.valueOf(inventoryArgs[3]);
            // create new inventory item and corresponding product from line
            inventory[index++] = new InventoryItem(
                    new Product(inventoryArgs[0], packaging, price), quantity);
        }
        // close readers and return inventory
        buffReader.close();
        reader.close();
        return inventory;
    }

    /***
     * reads through a file and returns number of lines in it
     * @param file File to count lines in
     * @return number of lines in file
     * @throws IOException if read fails
     */
    private static int countLinesInFile(File file) throws IOException
    {
        // open readers for file
        FileReader reader = new FileReader(file.getAbsolutePath());
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        //noinspection StatementWithEmptyBody
        while((lineNumberReader.readLine()) != null)
        {
            // do nothing, just counting
        }
        // save number of lines to return after closing readers
        int numLines = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        reader.close();
        return numLines;
    }
}
