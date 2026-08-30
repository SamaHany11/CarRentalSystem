package org.FirstTest;
import java.util.List;

public class LuxuryCar extends Car{
    private boolean chauffeur;
    private List<String> premiumFeatures;

    public LuxuryCar(String id,String brand,String model,int year,double basePrice,
                     boolean chauffeur,List<String> premiumFeatures){
        super(id,brand,model,year,basePrice);
        this.chauffeur =chauffeur;
        this.premiumFeatures =premiumFeatures;
    }

    public boolean isChauffeurAvailable(){
        return chauffeur;
    }

    public List<String> getPremiumFeatures(){
        return premiumFeatures;
    }

    @Override
    public double calculatePrice(int days){
        double total=getBasePrice()*days;
        double surcharge=total*0.40;
        return total+surcharge;
    }

    @Override
    public String getDescription(){
        return "Luxury Car: " +getBrand()+ " " +getModel()+ " (" +getYear()
                +"), Chauffeur Available: "+chauffeur
                +", Features: "+premiumFeatures;
    }
}
