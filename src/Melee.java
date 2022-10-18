import java.util.Random;
import java.lang.Math;

public class Melee extends Weapon{

    private double durability;
    private double strength;
    private Character user;

    public Melee(double strength, double durability, Character user){
        super(strength, durability, user);
    }


    /**
     * Uses the weapon on a character, returning a random double between 1 and the weapon's character's strength
     * @return a random double between 1 and the weapon's character's strength
     */
    public double use(){
        if (this.getUser() == null){
            return 0.0;
        }
        else{
            Random r = new Random();
            return Math.max(r.nextDouble()*this.user.getStrength(),1.0);
        }
    }

    /**
     * Returns a double for a potentially high amount of damage or no damage caused by the use of the weapon
     * @return a double for the damage caused by the weapon
     */
    public double useSpecial(){
        if (this.getUser() == null){
            return 0.0;
        }
        else{
            Random r = new Random();
            double randDouble = r.nextDouble();

            //if special attack backfires, causes damage to attacker
            if(randDouble<=Constants.CHANCE_MELEE_BACKFIRE){
                this.user.takeDamage(Math.max(randDouble*this.user.getStrength(),1.0));
                return 0.0;
            }

            //if special attack doesn't backfire, returns damaged caused by attack
            else{
                return Math.max(randDouble*this.user.getStrength(),1.0)*Constants.SPECIAL_ATTACK_FACTOR;
            }
        }
    }
}
