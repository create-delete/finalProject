package by.mukhin.finalProject.entities;

import lombok.Getter;

import java.util.Date;

public class Part {

    @Getter
    private final int id;

    private int cost;
    @Getter
    private String description;
    private Equipment equipment;

    public Part(int id, int cost, String description, Equipment equipment) {
        this.id = id;
        this.cost = cost;
        this.description = description;
        this.equipment = equipment;
    }

    public int getCost() {
        Date date = new Date();
        cost = 0;
        if (equipment.isUsable(date)) {
            long monthSincePurchase = (date.getTime() - equipment.getPurchaseDate().getTime()) * 1000 * 60 * 60 * 24 * 30;
            double perchentUse = monthSincePurchase/equipment.getLifeTimeMonth() * 100;

            if(perchentUse < 100){
                cost = (int) (cost - (cost*perchentUse));
            }
        }
        return cost;
    }
}
