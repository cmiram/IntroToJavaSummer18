import java.util.Scanner;

public class VendingMachine {
    
    private Change[] change;
    
    private InventoryItem[] inventory;
    
    private Scanner input = new Scanner(System.in);
    
    public VendingMachine(Change[] change, InventoryItem[] inventory) {
        this.change = change;
        this.inventory = inventory;
    }
    
    public void run() {
        int menuChoice = 1;
        while(menuChoice != 5) {
            handleMenuChoice(menuChoice);
            System.out.print("\nCommand: ");
            menuChoice = input.nextInt();
        }
        System.out.println("Thank you for using IPUG Inc. vending machine");
        System.out.println("Have a great day!");
    }
    
    private void handleMenuChoice(int choice) {
        switch(choice) {
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
                throw new IllegalArgumentException("Unknown Menu Option");
        }
    }
    
    private void listCommands() {
        System.out.println("Available Commands\n");
        System.out.println("1: Show Commands");
        System.out.println("2: Display Inventory");
        System.out.println("3: Display Change");
        System.out.println("4: Purchase an Item");
        System.out.println("5: Exit");
    }
    
    private void displayInventory() {
        for(int i = 0; i < inventory.length; i++) {
            System.out.println("Purchase ID: " + i + " " + inventory[i].toString());
        }
    }
    
    private void displayChange() {
        for(Change c : change) {
            System.out.println(c.toString());
        }
    }
    
    private void purchaseItem() {
        int purchaseId = -1;
        while(purchaseId < 0 || purchaseId > inventory.length) {
            System.out.print("Enter Purchase ID for item to buy: ");
            purchaseId = input.nextInt();
            if(purchaseId < 0 || purchaseId > inventory.length) {
                System.out.println("Invalid ID. Please try again.");
            }
        }
        Product toPurchase = inventory[purchaseId].getProduct();
        System.out.println("Total Cost: " + toPurchase.getPrice());
        
        int currencyInputs = -1;
        while(currencyInputs < 1) {
            System.out.print("How many currency items to input: ");
            currencyInputs = input.nextInt();
            if(currencyInputs < 1) {
                System.out.println("Currency input must be greater than 0");
            }
        }
    
        for(int i = 0; i < change.length; i++) {
            System.out.println("Input ID: " + i + " " + change[i].getCurrency().toString());
        }
    
        Currency[] currInput = new Currency[currencyInputs];
        double totalInput = 0;
        int currencyID = -1;
        System.out.println("\nPlease Enter IDs of your currency input");
        for(int i = 0; i < currencyInputs; i++) {
            while(currencyID < 0 || currencyID > change.length) {
                System.out.print("ID: ");
                currencyID = input.nextInt();
                if(currencyID < 0 || currencyID > change.length) {
                    System.out.println("Invalid ID. Pleast try again.");
                }
            }
            currInput[i] = change[currencyID].getCurrency();
            totalInput += currInput[i].getValue();
        }
        if(totalInput < toPurchase.getPrice()) {
            System.out.println("Not enough currency given.");
            System.out.println("You gave " + totalInput + ", but " + toPurchase.getPrice() + " is required.");
            System.out.println("Returning your money...");
            return;
        }
    
        double changeDue = totalInput - toPurchase.getPrice();
        System.out.println("Change Due: " + changeDue);
        if(changeDue > 0) {
            Currency[] changeReturned = calculateChangeToReturn(changeDue);
            if(changeReturned == null) {
                System.out.println("Inadequate change available.");
                System.out.println("Please try again with different inputs.");
                System.out.println("Returning your money...");
                return;
            }
        }
        addCustomerInputCurrency(currInput);
        System.out.println("Here is your " + toPurchase.getName() + "!");
        System.out.println("Enjoy!!");
    }
    
    private int[] calculateChangeToReturn(double changeDue) {
        int[] currsReturned = new int[change.length];
        
    }
    
    private void addCustomerInputCurrency(Currency[] input) {
        for(Currency currency : input) {
            int index = 0;
            while(!currency.equals(change[index].getCurrency())) {
                index++;
            }
            change[index].setQuantity(change[index].getQuantity()+1);
        }
    }
    
    public Change[] getChange() {
        return change;
    }
    
    public void setChange(Change[] change) {
        this.change = change;
    }
    
    public InventoryItem[] getInventory() {
        return inventory;
    }
    
    public void setInventory(InventoryItem[] inventory) {
        this.inventory = inventory;
    }
}
