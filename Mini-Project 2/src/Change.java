public class Change {
    
    private Currency currency;
    
    private int quantity;
    
    public Change(Currency currency, int quantity) {
        this.currency = currency;
        this.quantity = quantity;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return currency.toString() + " Quantity: " + quantity;
    }
}
