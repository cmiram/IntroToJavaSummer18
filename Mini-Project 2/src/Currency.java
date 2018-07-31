public class Currency implements Comparable<Currency> {
    
    private double value;
    
    private Material material;
    
    private String denomination;
    
    public Currency(double value, Material material, String denomination) {
        this.value = value;
        this.material = material;
        this.denomination = denomination;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public void setMaterial(Material material) {
        this.material = material;
    }
    
    public String getDenomination() {
        return denomination;
    }
    
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    
    @Override
    public String toString() {
        return "Value: " + value + " " + denomination + " Material: " + material.toString();
    }
    
    @Override
    public int compareTo(Currency other) {
        return Double.compare(value, other.getValue());
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) o;
        return other.value == value && other.material.equals(material) && other.denomination.equals(denomination);
    }
}
