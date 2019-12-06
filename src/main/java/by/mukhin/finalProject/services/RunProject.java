package by.mukhin.finalProject.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class RunProject {
    private int choise = 0;
    private Functions functions = new Functions();


    public void runProject(){
        while (true) {
            PrintToTheConsole.printStartMessege();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                choise = Integer.parseInt(reader.readLine());


            /*"1. Show all co-workers\n" +
              "2. Show all devices\n" +
              "3. Add co-worker\n" +
              "4. Add device\n" +
              "5. Switch device by co-worker\n" +
              "6. Switch department by co-worker");
               7. Exit*/
                switch (choise) {
                    case 1:
                        functions.showCoWorkers();
                        choise = 0;
                        break;
                    case 2:
                        functions.showAllDevices();
                        choise = 0;
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        return;
                    default:
                        System.out.println("Incorrect input. Try again.");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
        }
    }
}
