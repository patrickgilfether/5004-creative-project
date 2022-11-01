import java.util.Random;

/**************************************************************************************************
 Briggs Twitchell
 Dr. G
 Creative Lab
 10/24/2022
 PoolFloaty.java
 **************************************************************************************************/

public class PoolFloaty extends Weapon {

    private double durability;
    private double strength;
    private Character user;

    /* ********************************************************************************************
     CONSTRUCTOR METHODS
     *********************************************************************************************/
    /**
     * No argument constructor to instantiate weapon without a character
     */
    public PoolFloaty(){
        super(Constants.POOLFLOATY_STRENGTH, WeaponType.POOLFLOATY);
    }
    /**
     * Single argument constructor to instantiate a weapon equipped by a character
     */
    public PoolFloaty(Character user){
        super(Constants.POOLFLOATY_STRENGTH, WeaponType.POOLFLOATY,user);
        this.user = user;
    }

    /* ********************************************************************************************
     METHODS TO BE IMPLEMENTED BY CHILD
     *********************************************************************************************/
    @Override
    public double getDurabilityChange() {
        return Constants.POOLFLOATY_USE_DURABILITY_CHANGE;
    }

    @Override
    public double getChanceOfBackfire() {
        return Constants.CHANCE_POOLFLOATY_BACKFIRE;
    }

    /**
     * Returns a double for a potentially deadly amount of damage or no damage caused by the use of the weapon
     * @return a double for the damage caused by the weapon
     */
    @Override
    public double useSpecial(){
        if (this.getUser() == null){
            return 0.0;
        }

        else{

            //sets damage to 10000
            Random r = new Random();
            double randDouble = r.nextDouble();
            double damage = 10000.0;
            damage = Math.round(damage);

            //weapon has a high chance of doing nothing, and a small chance of doing deadly damage
            if(randDouble<=this.getChanceOfBackfire()){
                this.getUser().takeDamage(0);
                return 0.0;
            }

            else{
                return damage;
            }
        }
    }

}
