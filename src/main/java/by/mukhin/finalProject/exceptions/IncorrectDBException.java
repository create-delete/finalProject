package by.mukhin.finalProject.exceptions;

public class IncorrectDBException extends RuntimeException {
    public IncorrectDBException(String db){
        System.err.println("Incorrect database entry: " + db);
    }
}
