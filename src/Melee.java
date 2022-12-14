/**************************************************************************************************
 Briggs Twitchell
 Dr. G
 Creative Lab
 10/24/2022
 Melee.java
 **************************************************************************************************/

public class Melee extends Weapon{

    private double durability;
    private double strength;
    private Character user;

    /* ********************************************************************************************
     CONSTRUCTOR METHODS
     *********************************************************************************************/
    /**
     * No argument constructor to instantiate weapon without a character
     */
    public Melee(){
        super(Constants.MELEE_STRENGTH, WeaponType.MELEE);
    }
    /**
     * Single argument constructor to instantiate a weapon equipped by a character
     */
    public Melee(Character user){
        super(Constants.MELEE_STRENGTH, WeaponType.MELEE,user);
    }

    /* ********************************************************************************************
     METHODS TO BE IMPLEMENTED BY CHILD
     *********************************************************************************************/
    @Override
    public double getDurabilityChange() {
        return Constants.MELEE_USE_DURABILITY_CHANGE;
    }

    @Override
    public double getChanceOfBackfire() {
        return Constants.CHANCE_MELEE_BACKFIRE;
    }

}
