import java.io.*;

public class VendingMachineSimulator {
    
    public static void main(String[] args) {
        Change[] change = null;
        InventoryItem[] inventory = null;
        
        try {
            change = loadVMChange();
        } catch(IOException e) {
            System.out.println("Could not load change!");
            e.printStackTrace();
            System.exit(-1);
        }
    
        try {
            inventory = loadVMInventory();
        } catch(IOException e) {
            System.out.println("Could not load inventory!");
            e.printStackTrace();
            System.exit(-1);
        }
        VendingMachine vendingMachine = new VendingMachine(change, inventory);
        vendingMachine.run();
    }
    
    private static Change[] loadVMChange() throws IOException {
        File changeSrc = new File("../Resources/Currencies.txt");
        Change[] change = new Change[countLinesInFile(changeSrc)];
        FileReader reader = new FileReader(changeSrc.getAbsolutePath());
        BufferedReader buffReader = new BufferedReader(reader);
        String line;
        int index = 0;
        while((line = buffReader.readLine()) != null) {
            String[] currencyArgs = line.split("\\s+");
            double value = Double.valueOf(currencyArgs[0]);
            Material material = new Material(currencyArgs[1]);
            int quantity = Integer.valueOf(currencyArgs[3]);
            change[index++] = new Change(new Currency(value, material, currencyArgs[2]), quantity);
        }
        buffReader.close();
        reader.close();
        return change;
    }
    
    private static InventoryItem[] loadVMInventory() throws IOException {
        File inventorySrc = new File("../Resources/Inventory.txt");
        InventoryItem[] inventory = new InventoryItem[countLinesInFile(inventorySrc)];
        FileReader reader = new FileReader(inventorySrc.getAbsolutePath());
        BufferedReader buffReader = new BufferedReader(reader);
        String line;
        int index = 0;
        while((line = buffReader.readLine()) != null) {
            String[] inventoryArgs = line.split("\\s+");
            Packaging packaging = new Packaging(inventoryArgs[1]);
            double price = Double.valueOf(inventoryArgs[2]);
            int quantity = Integer.valueOf(inventoryArgs[3]);
            inventory[index++] = new InventoryItem(new Product(inventoryArgs[0], packaging, price), quantity);
        }
        buffReader.close();
        reader.close();
        return inventory;
    }
    
    private static int countLinesInFile(File file) throws IOException {
        FileReader reader = new FileReader(file.getAbsolutePath());
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        int numLines = lineNumberReader.getLineNumber();
        //noinspection StatementWithEmptyBody
        while((lineNumberReader.readLine()) != null) {
            // do nothing, just counting
        }
        lineNumberReader.close();
        reader.close();
        return numLines;
    }
    
}
