/**************************************************************************************************
 Briggs Twitchell
 Dr. G
 Creative Lab
 10/24/2022
 Ranged.java
 **************************************************************************************************/

public class Ranged extends Weapon {

  private double durability;
  private double strength;
  private Character user;

  /* ********************************************************************************************
   * CONSTRUCTOR METHODS
   *********************************************************************************************/
  /** No argument constructor to instantiate weapon without a character */
  public Ranged() {
    super(Constants.RANGED_STRENGTH,WeaponType.RANGED);
  }
  /** Single argument constructor to instantiate a weapon equipped by a character */
  public Ranged(Character user) {
    super(Constants.RANGED_STRENGTH, WeaponType.RANGED,user);
  }

  /* ********************************************************************************************
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