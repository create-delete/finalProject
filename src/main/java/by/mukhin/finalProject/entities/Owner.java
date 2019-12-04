package by.mukhin.finalProject.entities;

import lombok.Getter;

@Getter
public class Owner {

    private final int id;
    private String name;
    private Department department;


    public Owner(int id, String name, Department department)
    {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public void switchDepartment(Department department){
        this.department = department;
    }
}
