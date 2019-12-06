package by.mukhin.finalProject.exceptions;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(int id){
        System.err.printf("Owner with id %d not found\n\n", id);
        printStackTrace();
        return; //End the application
    }
}
