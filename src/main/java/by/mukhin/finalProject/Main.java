package by.mukhin.finalProject;

import by.mukhin.finalProject.services.Functions;
import by.mukhin.finalProject.services.RunProject;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        RunProject runProject = new RunProject();

        runProject.runProject();
    }
}
