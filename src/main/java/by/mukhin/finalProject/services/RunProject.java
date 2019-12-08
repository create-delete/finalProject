package by.mukhin.finalProject.services;

/*
Какой то баг с записью в базу. Зацикливается while(true) в 19 строке. И выпадает в вечны IOException
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class RunProject {

    public void runProject(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choise = 0;
        Functions functions = new Functions();

        while (true) {
            PrintToTheConsole.printStartMessege();

            try {
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
                        break;
                    case 2:
                        functions.showAllDevices();
                        break;
                    case 3:
                        functions.addCoWorker(reader);
                        break;
                    case 4:
                        functions.addDevice(reader);
                        break;
                    case 5:
                        functions.changeOwnerToEquipment(reader);
                        break;
                    case 6:
                        functions.changeDepartmentToCoWorker(reader);
                        break;
                    case 7:
                        reader.close();
                        return;
                    default:
                        System.out.println("Incorrect input. Try again.");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                //System.out.println("Incorrect input. Try again.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
