package by.mukhin.finalProject.services;
 // ЗАКОНЧИТЬ НАДО UPDATE DATA
import by.mukhin.finalProject.entities.Department;
import by.mukhin.finalProject.entities.Owner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Functions {

    private static Statement statement;
    private ResultSet resultSet;
    private static BufferedReader reader;

    static {
        try {
            statement = SQLConnector.getConnection().createStatement();
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SQLException e) {
            System.err.println("Initialize exception");
            e.printStackTrace();
        }
    }

    public void showCoWorkers() throws SQLException {
        resultSet = statement.executeQuery("select * from owner");

        List<Owner> owners = new ArrayList<>();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Department department = getDepartmentById(resultSet.getInt(3));

            owners.add(new Owner(id, name, department));
        }

        for(int i = 1; i <=4; i++){
            System.out.println("Department: " + getDepartmentById(i));
            for(int q = 0; q < owners.size(); q ++) {
                if (owners.get(q).getDepartment().equals(getDepartmentById(i))) {
                    System.out.println(owners.get(q).getName());
                }
            }
            System.out.println("--------------------------");
        }
    }

    private Department getDepartmentById(int id){
        Department department;
        switch (id){
            case 1: department = Department.IT; break;
            case 2: department = Department.ACTIVE_SALES; break;
            case 3: department = Department.BUH; break;
            case 4: department = Department.ADMINISTRATION; break;
            default:
                System.out.println("There is no Department with this id");
                department = null;
        }
        return department;
    }
}
