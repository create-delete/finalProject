package by.mukhin.finalProject;

import by.mukhin.finalProject.services.Functions;
import by.mukhin.finalProject.services.RunProject;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        RunProject runProject = new RunProject();
        char a = 50,a1 = 48, a2 = 49, a3 = 57, a4 =45,a5 =56,a6 =53, a7 =57,a8 =45,a9 =49;
        System.out.println(a + " " + a1 + " "  + a2 + " " + a3 + " "  + a4  + " "+ a5  + " " + a6  + " " + a7  + " " + a8  + " " + a9);
        runProject.runProject();
    }
}
