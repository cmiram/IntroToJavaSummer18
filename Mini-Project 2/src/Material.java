public class Material {
    
    private String type;
    
    public Material(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return type;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Material)) {
            return false;
        }
        Material other = (Material) o;
        return other.getType().equals(type);
    }
}
