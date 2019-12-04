package by.mukhin.finalProject.entities;

public enum Department {
    IT("IT"),
    ACTIVE_SALES("Active_sales"),
    BUH("buh"),
    ADMINISTRATION("Administration");

    private String value;

    private Department(String dep){
        this.value = dep;
    }

    public String getValue(){
        return value;
    }

    public String toString() {
        return this.getValue();
    }
}
