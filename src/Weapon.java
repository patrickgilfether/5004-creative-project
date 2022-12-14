/**************************************************************************************************
Briggs Twitchell
 Dr. G
 Creative Lab
 10/24/2022
 Weapon.java
 **************************************************************************************************/

/*
 * Weapon - Represents an abstract weapon class
 *      Fields
 *      ------
 *          - durability
 *          - strength
 *          - user
 *          - type
 *      Methods
 *      ------
 *          - use
 *          - useSpecial
 *          - getUser
 *          - getDurability
 *          - getType
 *          - getDurabilityChange
 *          - getChanceOfBackfire
 *          - changeDurability
 *          - setUser
 */

import java.util.Random;
import java.lang.Math;

public abstract class Weapon {
    private double durability;
    private double strength;
    protected Character user;
    private WeaponType type;

    /* ********************************************************************************************
     CONSTRUCTOR METHODS
     *********************************************************************************************/
    /**
     * 2 argument constructor to instantiate a weapon with no character
     * @param strength double for the strength value of the weapon
     */
    public Weapon(double strength, WeaponType type){
        this.strength = strength;
        this.durability = Constants.WEAPON_STARTING_DURABILITY;
        this.type = type;
        this.user = null;
    }

    /**
     * 3 argument constructor to instantiate a weapon with a character
     * @param strength double for the strength value of the weapon
     * @param user Character object for the character weilding the weapon
     */
    public Weapon(double strength, WeaponType type, Character user){
        this.strength = strength;
        this.durability = Constants.WEAPON_STARTING_DURABILITY;
        this.type = type;
        this.user = user;
    }

    /* ********************************************************************************************
     METHODS INHERITED BY CHILD CLASSES
     *********************************************************************************************/
    /**
     * Uses the weapon on a character, returning a double for the damage caused
     * @return a double representing damage (sum of the weapon's user's strength and the weapon's strength)
     */
    public double use() {
        if (this.getUser() == null || this.durability<=0){
            return 0.0;
        }
        else{
            this.changeDurability(this.getDurabilityChange());
            return this.user.getStrength()+this.strength*0.1;
        }
    }

    /**
     * Returns a double for a potentially high amount of damage or no damage caused by the use of the weapon -- has a chance of causing damage to weapon user
     * @return a double for the damage caused by the weapon
     */
    public double useSpecial(){
        if (this.getUser() == null || this.durability<=0){
            return 0.0;
        }

        else{
            //decreases weapon durability
            this.changeDurability(-this.getDurabilityChange());

            //sets damage to a random double between the minimum
            Random r = new Random();
            double randDouble = r.nextDouble();
            double damage = Math.max(randDouble*(this.user.getStrength()+this.strength*0.1),Constants.WEAPON_MIN_DAMAGE)*Constants.SPECIAL_ATTACK_FACTOR;
            damage = Math.round(damage);

            //if special attack backfires, it incurs damage on the user
            if(randDouble<=this.getChanceOfBackfire()){
                this.user.takeDamage(damage);
                return 0.0;
            }

            //if special attack doesn't backfire, returns random damaged caused by the special attack
            else{
                return damage;
            }
        }
    }

    /**
     * Returns the character wielding the weapon
     * @return the character object wielding the weapon
     */
    public Character getUser(){
        return this.user;
    }

    /**
     * Returns a double for the weapon's durability
     */
    public double getDurability(){return this.durability;}

    /**
     * Returns a WeaponType enum representing the for the weapon's type (Melee, Ranged, or Magic)
     */
    public WeaponType getType(){return this.type;}

    /**
     * Increases or decreases weapon durability value for double passed in
     * @param amountChanged double for the amount that durability
     */
    private void changeDurability(double amountChanged){
        if(this.durability + amountChanged <0 ){
            return;
        }
        else{
            this.durability += amountChanged;
        }
    }

    /**
     * Changes the user equipping the weapon

     * @param user the character using the weapon

     */
    public void setUser(Character user){
        if(user != null){
            this.user = user;
        }
    }

    /**
     * Puts the details of the weapon in a string
     */
    @Override
    public String toString(){

        String weaponString = this.type+"\n\tStrength = "+this.strength +"\n\tDurability = "+this.durability;
        if(this.user != null){
            weaponString += "\n\tUser: "+ this.user.getName();
        }
        return weaponString;
    }

    /* ********************************************************************************************
     METHODS TO BE IMPLEMENTED BY CHILD CLASSES
     *********************************************************************************************/
    /**
     * Returns a double for the constant value of the change in the weapon's durability after it's used
     */
    public abstract double getDurabilityChange();

    /**
     * Returns a double for the percentage chance that the weapon will backfire and cause damage to the weapon user
     */
    public abstract double getChanceOfBackfire();

}


