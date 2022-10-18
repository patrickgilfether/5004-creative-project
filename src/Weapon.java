
public abstract class Weapon {
    private double durability;
    private double strength;
    private Character user;


    /**
     * 2 argument constructor to instantiate a weapon with no character
     * @param strength double for the strength value of the weapon
     * @param durability double for the durability value of the weapon
     */
    public Weapon(double strength, double durability){
        this.strength = strength;
        this.durability = durability;
        this.user = null;
    }

    /**
     * 3 argument constructor to instantiate a weapon with a character
     * @param strength double for the strength value of the weapon
     * @param durability double for the durability value of the weapon
     * @param user Character object for the character weilding the weapon
     */
    public Weapon(double strength, double durability, Character user){
    this.strength = strength;
    this.durability = durability;
    this.user = user;
    }

    /**
     * Returns a double value for the damage caused by the using of the weapon
     * @return a double for the damage caused by the weapon
     */
    public abstract double use();

    /**
     * Returns a double for a potentially high amount of damage or no damage caused by the use of the weapon
     * @return a double for the damage caused by the weapon
     */
    public abstract double useSpecial();

    /**
     * Returns the character wielding the weapon
     * @return the character object wielding the weapon
     */
    public Character getUser(){
        return this.user;
    }
}
