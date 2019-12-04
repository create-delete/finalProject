package by.mukhin.finalProject.entities;

import lombok.Getter;

import java.util.Date;
@Getter
public class Equipment {
    private final int id;
    private final Type type;
    private Owner owner;
    private final Date purchaseDate;
    private int lifeTimeMonth;

    public Equipment(int id, Type type, Owner owner, Date purchaseDate, int lifeTimeMonth) {
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.purchaseDate = purchaseDate;
        this.lifeTimeMonth = lifeTimeMonth;
    }

    public void repair(boolean to1, boolean to2){
        if(to2){
            this.lifeTimeMonth += 6;
        }
        if(to1){
            this.lifeTimeMonth += 12;
        }
    }

    public void switchOwner(Owner newOwner){
        if(isUsable(new Date())){
            this.owner = newOwner;
        } else{
            System.out.println( "The equipment is in non-working or close to non-working condition.\n" +
                                "Repair it before transferring. Spend TO1 to extend the life of\n" +
                                "12 months or TO2 to extend the life of 6 months (use method repair).");
        }
    }

    public boolean isUsable(Date date){
        long monthSincePurchase = (date.getTime() - purchaseDate.getTime()) * 1000 * 60 * 60 * 24 * 30;

        if((monthSincePurchase - lifeTimeMonth) > 0){
            return true;
        } else {
            return false;
        }
    }
}
