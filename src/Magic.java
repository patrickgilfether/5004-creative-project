
/**************************************************************************************************
 Briggs Twitchell
 Dr. G
 Creative Lab
 10/24/2022
 Magic.java
 **************************************************************************************************/
public class Magic extends Weapon {

    private double durability;
    private double strength;
    private Hero user;

    /* ********************************************************************************************
     * CONSTRUCTOR METHODS
     *********************************************************************************************/
    /** No argument constructor to instantiate weapon without a character */
    public Magic() {
        super(Constants.MAGIC_STRENGTH,WeaponType.MAGIC);
    }
    /** Single argument constructor to instantiate a weapon equipped by a character */
    public Magic(Hero user) {
        super(Constants.MAGIC_STRENGTH, WeaponType.MAGIC,user);
    }

    /* ********************************************************************************************
     * METHODS TO BE IMPLEMENTED BY CHILD
     *********************************************************************************************/
    @Override
    public double getDurabilityChange() {
        return Constants.MAGIC_USE_DURABILITY_CHANGE;
    }

    @Override
    public double getChanceOfBackfire() {
        return Constants.CHANCE_MAGIC_BACKFIRE;
    }

    /**
     * Implements Weapons version of useSpecial but returns nothing if mana is <= 0
     * @return a double for the damage inflicted
     */
    @Override
    public double useSpecial(){
        if(this.user.getMana()<=0){
            return 0.0;
    } else {
          this.user.decreaseMana(Constants.MANA_USE_DECREASE);
          return super.useSpecial();
        }
    }
}