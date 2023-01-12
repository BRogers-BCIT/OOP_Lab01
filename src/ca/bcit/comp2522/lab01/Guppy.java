package ca.bcit.comp2522.lab01;

/**
 * Guppy.
 *
 * @author Braden Rogers
 * @version 202310
 */
public class Guppy {

    // Fill this class with your code.


    final static int FIRST_GENERATION = 0;
    final static int YOUNG_FISH_AGE_IN_WEEKS = 10;
    final static int MATURE_FISH_AGE_IN_WEEKS = 30;
    final static int MAXIMUM_AGE_IN_WEEKS = 50;
    final static double MINIMUM_WATER_VOLUME_ML = 250.0;
    final static String DEFAULT_GENUS = "Poecilia";
    final static String DEFAULT_SPECIES = "reticulata";
    final static double DEFAULT_HEALTH_COEFFICIENT = 0.5;
    final static double MINIMUM_HEALTH_COEFFICIENT = 0.0;
    final static double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    final private String genus;
    final private String species;
    private int ageInWeeks;
    final private int generationNumber;
    private boolean isAlive;
    final private boolean isFemale;
    private double healthCoefficient;
    final private int identificationNumber;
    private static int numberOfGuppiesBorn;

    public Guppy(){
        numberOfGuppiesBorn += 1;
        ageInWeeks = 0;
        generationNumber = 0;
        genus = DEFAULT_GENUS;
        species = DEFAULT_SPECIES;
        isFemale = true;
        isAlive = true;
        healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        identificationNumber = numberOfGuppiesBorn;
    }

    public Guppy(String newGenus, String newSpecies, int newAgeInWeeks,
                 boolean newIsFemale, int newGenerationNumber, double newHealthCoefficient){
        numberOfGuppiesBorn += 1;
        isAlive = true;

        if (newGenus != null && !newGenus.trim().equals("")) {
            newGenus = newGenus.toLowerCase().trim();
            newGenus = newGenus.substring(0, 1).toUpperCase() + newGenus.substring(1);
            genus = newGenus;
        } else{
            genus = DEFAULT_GENUS;
        }

        if (newSpecies != null && !newSpecies.trim().equals("")) {
            newSpecies = newSpecies.toLowerCase().trim();
            species = newSpecies;
        } else {
            species = DEFAULT_SPECIES;
        }


        ageInWeeks = Math.max(newAgeInWeeks, 0);
        isFemale = newIsFemale;
        generationNumber = Math.max(newGenerationNumber, 0);


        if (newHealthCoefficient < MINIMUM_HEALTH_COEFFICIENT){
            healthCoefficient = MINIMUM_HEALTH_COEFFICIENT;
            isAlive = false;
        } else {
            healthCoefficient = Math.min(newHealthCoefficient, MAXIMUM_HEALTH_COEFFICIENT);
        }

        identificationNumber = numberOfGuppiesBorn;
    }

    public String getGenus(){
        return genus;
    }

    public String getSpecies(){
        return species;
    }

    public int getAgeInWeeks(){
        if (ageInWeeks > MAXIMUM_AGE_IN_WEEKS){
            ageInWeeks = MAXIMUM_AGE_IN_WEEKS;
        }
        return ageInWeeks;
    }

    public boolean getIsFemale(){
        return isFemale;
    }

    public int getGenerationNumber(){
        return generationNumber;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public double getHealthCoefficient(){
        return healthCoefficient;
    }

    public int getIdentificationNumber(){
        return identificationNumber;
    }

    public static int getNumberOfGuppiesBorn(){
        return numberOfGuppiesBorn;
    }

    public void setAgeInWeeks(int newAgeInWeeks){
        if (isAlive && newAgeInWeeks >= 0
                && newAgeInWeeks <= MAXIMUM_AGE_IN_WEEKS)  {
            ageInWeeks = newAgeInWeeks;
        }
    }

    public void setIsAlive(boolean newIsAlive){
        if (!newIsAlive) {
            isAlive = false;
        }
    }

    public void setHealthCoefficient(double newHealthCoefficient){
        if (MINIMUM_HEALTH_COEFFICIENT <= newHealthCoefficient
                && newHealthCoefficient <= MAXIMUM_HEALTH_COEFFICIENT){
            healthCoefficient = newHealthCoefficient;
        }
    }

    public void incrementAge(){
        ageInWeeks += 1;
        if (ageInWeeks >  MAXIMUM_AGE_IN_WEEKS){
            isAlive = false;
        }
    }

    public double getVolumeNeeded(){
        if (!isAlive) {
            return 0.0;

        } else if (ageInWeeks < 10){
            return MINIMUM_WATER_VOLUME_ML;

        }else if (ageInWeeks < 31){
            return (MINIMUM_WATER_VOLUME_ML * ageInWeeks / YOUNG_FISH_AGE_IN_WEEKS);

        } else if (ageInWeeks < 51){
            return (MINIMUM_WATER_VOLUME_ML * 1.5);

        } else {
            return 0.0;
        }
    }

    public void changeHealthCoefficient(double delta){
        healthCoefficient += delta;

        if (healthCoefficient <= MINIMUM_HEALTH_COEFFICIENT){
            healthCoefficient = 0.0;
            isAlive = false;
        }

        if (healthCoefficient > MAXIMUM_HEALTH_COEFFICIENT){
            healthCoefficient = MAXIMUM_HEALTH_COEFFICIENT;
        }


    }

    @Override
    public String toString() {
        return "Guppy{" +
                "genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                ", ageInWeeks=" + ageInWeeks +
                ", isFemale=" + isFemale +
                ", generationNumber=" + generationNumber +
                ", isAlive=" + isAlive +
                ", healthCoefficient=" + healthCoefficient +
                ", identificationNumber=" + identificationNumber +
                '}';
    }
}
