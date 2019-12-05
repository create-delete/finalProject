package by.mukhin.finalProject.services;

// Проверить как работает функция вывода всех девайсов.
// Проверить как работает исключение OwnerNotFoundException


import by.mukhin.finalProject.entities.Department;
import by.mukhin.finalProject.entities.Equipment;
import by.mukhin.finalProject.entities.Owner;
import by.mukhin.finalProject.entities.Type;
import by.mukhin.finalProject.exceptions.OwnerNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

        List<Owner> owners;

        owners = getOwners();

        for (int i = 1; i <= 4; i++) {
            System.out.println("Department: " + getDepartmentById(i));
            for (int q = 0; q < owners.size(); q++) {
                if (owners.get(q).getDepartment().equals(getDepartmentById(i))) {
                    System.out.println(owners.get(q).getName());
                }
            }
            System.out.println("--------------------------");
        }
    }

    public void showAllDevices() throws SQLException {
        resultSet = statement.executeQuery("select * from equipment");
        int count = 1;

        List<Equipment> equips = new ArrayList<>();
        List<Owner> owners = getOwners();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            Type type = getTypeByString(resultSet.getString(2));
            Owner owner = getOwnerById(resultSet.getInt(3));
            Date date = resultSet.getDate(4);
            int lifeTimeMonth = resultSet.getInt(5);

            equips.add(new Equipment(id, type, owner, date, lifeTimeMonth));
        }


        for(Owner own : owners) {
            System.out.printf("%s from %s have:\n", own.getName(), own.getDepartment());
            for(Equipment equipment : equips) {
                if(equipment.getOwner().equals(own)){
                    System.out.println(count + ". " + equipment.getType());
                }
            }
            count = 1;
            System.out.println();
        }

        System.out.println("No have owner:");
        count = 1;
        for(Equipment equipment : equips) {
            if(equipment.getOwner()== null){
                System.out.println(count + ". " + equipment.getType());
            }
        }
    }

    private Owner getOwnerById(int id) {
        Owner result = null;
        List<Owner> owners = new ArrayList<>();

        try {
            owners = getOwners();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Owner o : owners) {
            if (o.getId() == id){
                result = o;
            }
        }

        if(result == null) {
            throw new OwnerNotFoundException(id);
        }

        return result;
    }

    private Department getDepartmentById(int id) {
        Department department;
        switch (id) {
            case 1:
                department = Department.IT;
                break;
            case 2:
                department = Department.ACTIVE_SALES;
                break;
            case 3:
                department = Department.BUH;
                break;
            case 4:
                department = Department.ADMINISTRATION;
                break;
            default:
                System.out.println("There is no Department with this id");
                department = null;
        }
        return department;
    }

    private Type getTypeByString(String type) {
        Type result = null;
        if (type.toLowerCase().equals("computer")) result = Type.COMPUTER;
        if (type.toLowerCase().equals("phone")) result = Type.PRINTER;
        if (type.toLowerCase().equals("printer")) result = Type.PHONE;
        return result;
    }

    private List<Owner> getOwners() throws SQLException {
        resultSet = statement.executeQuery("select * from owner");

        List<Owner> result = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Department department = getDepartmentById(resultSet.getInt(3));

            result.add(new Owner(id, name, department));
        }

        return result;
    }
}
