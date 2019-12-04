package by.mukhin.finalProject.services;

public class IncorrectDBException extends RuntimeException {
    public IncorrectDBException(String db){
        System.err.println("Incorrect database entry: " + db);
    }
}
