package by.mukhin.finalProject.services;import by.mukhin.finalProject.entities.Department;import by.mukhin.finalProject.entities.Equipment;import by.mukhin.finalProject.entities.Owner;import by.mukhin.finalProject.entities.Type;import by.mukhin.finalProject.exceptions.OwnerNotFoundException;import java.io.BufferedReader;import java.io.IOException;import java.sql.ResultSet;import java.sql.SQLException;import java.sql.Statement;import java.text.SimpleDateFormat;import java.util.ArrayList;import java.util.Date;import java.util.List;public class Functions {    public void showCoWorkers() throws SQLException {        List<Owner> owners = getOwners();        for (int i = 1; i <= 4; i++) {            System.out.println("Department: " + getDepartmentById(i));            for (int q = 0; q < owners.size(); q++) {                if (owners.get(q).getDepartment().equals(getDepartmentById(i))) {                    System.out.println(owners.get(q).getName());                }            }            System.out.println("--------------------------");        }    }    public void showAllDevices() throws SQLException {        int count = 1;        List<Equipment> equips = getEquipment();        List<Owner> owners = getOwners();        for (Owner own : owners) {            System.out.printf("%s from %s have:\n", own.getName(), own.getDepartment());            for (Equipment equipment : equips) {                if (equipment.getOwner().getId() == own.getId()) {                    System.out.println(count + ". " + equipment.getType());                    count++;                }            }            count = 1;            System.out.println();        }        System.out.println("No have owner:");        count = 1;        for (Equipment equipment : equips) {            if (equipment.getOwner() == null) {                System.out.println(count + ". " + equipment.getType());                count++;            }        }    }    public void addCoWorker(BufferedReader reader) throws SQLException {        Statement statement = SQLConnector.getConnection().createStatement();        int dep;        String name;        try {            System.out.print("Input name: ");            name = reader.readLine();            System.out.print("1. IT\n2. Active_sales\n3. buh\n4. Administration\nInput the department number:");            dep = Integer.parseInt(reader.readLine());            statement.execute("Insert into owner (name, id_department) value (\'" + name + "\', " + dep + ");");        } catch (IOException e) {            e.printStackTrace();        }    }    public void addDevice(BufferedReader reader) throws SQLException {        Statement statement = SQLConnector.getConnection().createStatement();        int type, owner, ownerID, lifeTime;        String strType;        List<Owner> owners;        Date date = new Date();        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        try {            System.out.print("Input type:\n1. Computer\n2. Phone\n3. Printer\n");            type = Integer.parseInt(reader.readLine());            owners = getOwners();            System.out.println("Who will be the owner of the equipment?");            for (int i = 0; i < owners.size(); i++) {                System.out.println((i + 1) + " " + owners.get(i).getName() + " from " + owners.get(i).getDepartment());            }            owner = Integer.parseInt(reader.readLine());            ownerID = owners.get(owner - 1).getId();            if (type == 1) {                lifeTime = 24;                strType = "computer";            } else if (type == 2) {                lifeTime = 12;                strType = "phone";            } else if (type == 3) {                lifeTime = 12;                strType = "printer";            } else throw new Exception("Incorrect input type");            statement.execute("insert into equipment (type, id_owner, purchase_date, lifetime_month) value " +                    "(\'" + strType + "\', " + ownerID + ", \'" + dateFormat.format(date) + "\', " + lifeTime + ");");        } catch (IOException e) {            e.printStackTrace();        } catch (Exception e) {            System.err.println(e);        }    }    public void changeOwnerToEquipment(BufferedReader reader) throws SQLException, IOException {        Statement statement = SQLConnector.getConnection().createStatement();        List<Equipment> equips = getEquipment();        List<Owner> owners = getOwners();        int inputEquip, newOwner, count = 1;        for (int i = 0; i < equips.size(); i++) {            System.out.println(count + ". " + equips.get(i).getType() + " owner " + equips.get(i).getOwner().getName());            count++;        }        System.out.print("Input number: ");        inputEquip = Integer.parseInt(reader.readLine());        count = 1;        for(int i = 0; i < owners.size(); i++){            System.out.println(count + ". " + owners.get(i).getName() + " from " + owners.get(i).getDepartment());            count++;        }        System.out.println("Who gets the equipment?");        newOwner = Integer.parseInt(reader.readLine());        statement.execute("UPDATE equipment SET id_owner=" + owners.get(newOwner-1).getId() + " WHERE id=" + equips.get(inputEquip-1).getId());    }    public void changeDepartmentToCoWorker(BufferedReader reader) {    }    ////////////////////////////////////////////////////////////////////////////////////////////////////////    private Owner getOwnerById(int id) {        Owner result = null;        List<Owner> owners = new ArrayList<>();        try {            owners = getOwners();        } catch (SQLException e) {            e.printStackTrace();        }        for (Owner o : owners) {            if (o.getId() == id) {                result = o;            }        }        if (result == null) {            throw new OwnerNotFoundException(id);        }        return result;    }    private Department getDepartmentById(int id) {        Department department;        switch (id) {            case 1:                department = Department.IT;                break;            case 2:                department = Department.ACTIVE_SALES;                break;            case 3:                department = Department.BUH;                break;            case 4:                department = Department.ADMINISTRATION;                break;            default:                System.out.println("There is no Department with this id");                department = null;        }        return department;    }    private Type getTypeByString(String type) {        Type result = null;        if (type.toLowerCase().equals("computer")) result = Type.COMPUTER;        if (type.toLowerCase().equals("phone")) result = Type.PRINTER;        if (type.toLowerCase().equals("printer")) result = Type.PHONE;        return result;    }    private List<Owner> getOwners() throws SQLException {        Statement statement = SQLConnector.getConnection().createStatement();        ResultSet resultSet = statement.executeQuery("select * from owner;");        List<Owner> result = new ArrayList<>();        while (resultSet.next()) {            int id = resultSet.getInt(1);            String name = resultSet.getString(2);            Department department = getDepartmentById(resultSet.getInt(3));            result.add(new Owner(id, name, department));        }        return result;    }    private List<Equipment> getEquipment() throws SQLException {        Statement statement = SQLConnector.getConnection().createStatement();        ResultSet resultSet = statement.executeQuery("select * from equipment;");        List<Equipment> result = new ArrayList<>();        while (resultSet.next()) {            int id = resultSet.getInt(1);            Type type = getTypeByString(resultSet.getString(2));            Owner owner = getOwnerById(resultSet.getInt(3));            Date date = resultSet.getDate(4);            int lifeTimeMonth = resultSet.getInt(5);            result.add(new Equipment(id, type, owner, date, lifeTimeMonth));        }        return result;    }}