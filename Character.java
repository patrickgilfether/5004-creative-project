/*
 * Author : Keegan Moseley
 * Date : Oct 19, 2022
 * 5004 Creative Lab
 * 
 * This file is the abstract Character class
 */

import java.util.Random;


/**
 * Abstract class for Character objects.
 * 
 * @author Keegan Moseley
 *
 */
public abstract class Character {
	//the character's name
	protected String name;
	//the character's health points
	protected double hitPoints;
	//the character's strength statistic
	protected double strength;
	//boolean representing if the character is alive. Every character starts off as alive.
	protected boolean alive = true;
	//field for the character to equip a weapon to
	protected Weapon weapon = null;
	
	/**
	 * Constructor for a Character object.
	 * 
	 * @param inputName, String for the Character's name field.
	 * @param inputHitPoints, double value for the Character's hit points
	 * @param inputStrength, double value for the Character's strength field.
	 * @throws IllegalArgumentException if the input argument for 
	 * 		   hitPoints is less than or equal to zero
	 * 		   It is also thrown if the input argument for strength is less one.
	 */
	public Character(String inputName, double inputHitPoints, double inputStrength) throws IllegalArgumentException {
		this.name = inputName;
		
		//the hit points must be greater than 0.
		if (inputHitPoints <= 0) {
			throw new IllegalArgumentException("A character's hitpoints must be greater than zero.");
		}
		else {
			this.hitPoints = inputHitPoints;
		}
		
		//the strength field must also be greater than one.
		if (inputStrength < 1) {
			throw new IllegalArgumentException("A character's strength must be greater than or equal to one.");
		}
		else {
			this.strength = inputStrength;
		}
	}
	
	/**
	 * Have the character take a turn. 
	 * The child class type determines what actions can be taken in this method.
	 * 
	 * @param opponent, a Character object
	 * @return double, how much damage the opponent took.
	 */
	abstract public double takeTurn(Character opponent);
	
	/**
	 * This method represents a character attacking another character without a Weapon object.
	 * The damage dealt is returned. The damage value is a random value between 1 and the character's strength.
	 * 
	 * @param opponent, the Character object taking damage
	 * @return double, the amount of damage the targeted Character took.
	 */
	public double basicAttack(Character opponent) {
		
		Random randObj = new Random();
		
		//produces a random double between 0.0 and 1.0
		double randDouble = randObj.nextDouble();
		
		//find how much damage the targeted character should take
		double damageValue = 1 * (this.strength * randDouble);
		//if randDouble is less than 1, need to increase the variable's value 1.
		if (damageValue < 1) {
			damageValue = 1;
		}
		
		//decrease the opponent's hitPoints
		opponent.takeDamage(damageValue);
		
		return damageValue;
	}
	
	/**
	 * This method represents attacking an opponent with the Character's weapon.
	 * 
	 * @param opponent, a Character object
	 * @return double, the amount of damage done.
	 */
	public double attackWithWeapon(Character opponent) {
		
		//TODO ensure the weapon's use() returns 0 if there is no durability left
		
		//return damage of weapon
		double damage = this.weapon.use();
		
		//decrease the opponent's hit points
		opponent.takeDamage(damage);
		
		return damage;
	}
	
	/**
	 * This method equips the character with a Weapon object
	 * If the weapon is magical, set the weapon's hero field as this Character.
	 * 
	 * @param weapon, a Weapon object
	 */
	public void equipWeapon(Weapon weapon) {
		
		this.weapon = weapon;
		
		//set the weapon's previous user's weapon field to null
		if (weapon.getUser() != null) {
			weapon.getUser().weaponFieldNull();
		}
		
		this.weapon.setUser(this);
		
	}
	
	/**
	 * This method represents a Character taking damage. The input damage must not be less than zero.
	 * The Character's hit points are decreased by the input amount. If their hit points drop to zero or under,
	 * their alive field is changed to false.
	 * 
	 * @param damage, a double
	 * @throws IllegalArgumentException if the input double is less than zero
	 */
	public void takeDamage(double damage) throws IllegalArgumentException{
		//ensure the input is not less than zero
		if (damage < 0) {
			throw new IllegalArgumentException("A character cannot take negative damage.");
		}
		
		//decrease this Character's hit points
		this.hitPoints -= damage;
		
		//check if the Character is alive
		if (this.hitPoints <= 0) {
			this.alive = false;
			this.hitPoints = 0;
		}
		
		return;
	}
	
	/**
	 * Getter for the Character's name field
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter for the character's hit points field.
	 * 
	 * @return double
	 */
	public double getHitPoints() {
		return this.hitPoints;
	}
	
	/**
	 * Getter for the character's strength field.
	 * 
	 * @return double
	 */
	public double getStrength() {
		return this.strength;
	}
	
	/**
	 * Getter for the character's alive field. If it is true, they are alive.
	 * 
	 * @return boolean
	 */
	public boolean getAlive() {
		return this.alive;
	}
	
	/**
	 * Getter for the character's Weapon field.
	 * 
	 * @return Weapon
	 */
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	/**
	 * Method to set the weapon field to null
	 */
	public void weaponFieldNull() {
		this.weapon = null;
	}
}
