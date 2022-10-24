import java.util.Random;
import java.lang.Math;

public class Ranged extends Weapon {

  private double durability;
  private double strength;
  private Character user;

  /*********************************************************************************************
   * CONSTRUCTOR METHODS
   *********************************************************************************************/
  /** No argument constructor to instantiate weapon without a character */
  public Ranged() {
    super(Constants.RANGED_STRENGTH);
  }
  /** Single argument constructor to instantiate a weapon equipped by a character */
  public Ranged(Character user) {
    super(Constants.RANGED_STRENGTH, user);
  }

  /*********************************************************************************************
   * METHODS TO BE IMPLEMENTED BY CHILD
   *********************************************************************************************/
  @Override
  public double getDurabilityChange() {
    return Constants.RANGED_USE_DURABILITY_CHANGE;
  }

  @Override
  public double getChanceOfBackfire() {
    return Constants.CHANCE_RANGED_BACKFIRE;
  }
}