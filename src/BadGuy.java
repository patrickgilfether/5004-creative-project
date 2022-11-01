/*
 * Author : Keegan Moseley
 * Date : Oct 19, 2022
 * 5004 Creative Lab
 * 
 * This file is the BadGuy class
 */

import java.util.Random;  

/**
 * This class represents a Bad Guy. It is a child of the Character class.
 * 
 * @author Keegan Moseley
 *
 */
public class BadGuy extends Character{
	//static array of possible phrases for the Bad Guy to say
	private static String[] phrases = {"Why I oughta!", "You smell like a foot!", "That armor is sooo last season!"};
	
	
	/**
	 * Constructor for a Hero object.
	 * 
	 * @param name, the hero's name
	 * @param hP, the hero's health points. Must be larger than zero.
	 * @param strength, the hero's strength. Must not be less than one.
	 */
	public BadGuy(String name, double hP, double strength) {
		super(name, hP, strength);
	}

	/**
	 * This method has the BadGuy attack a character. They will attack with their weapon if their
	 * weapon has enough durability & it exists. Otherwise, they will perform a basic attack.
	 * 
	 * @parameter opponent, a Character object to take damage
	 * @return damage, how much damage the opponent took
	 */
	@Override
	public double takeTurn(Character opponent) throws InterruptedException {
		
		double damage;
		Thread.currentThread().sleep(1000);
		System.out.println("\t" + this.name + " says: " + talkTrash());
		Thread.currentThread().sleep(1000);
		
		//if the bad guy's weapon exists and isn't out of durability, weapon attack
		if (this.getWeapon() != null && this.getWeapon().getDurability() > 0) {
			damage = this.attackWithWeapon(opponent);
		}
		//attack with hands
		damage = this.basicAttack(opponent);
		
		return damage;
	}
	
	
	//TODO make the badGuy only do basic attacks. No weapon equipped
	
	/**
	 * Method to return a random selection of one of the bad guy's phrases
	 * 
	 * @return String
	 */
	public String talkTrash() {
		//get the length of the array of phrases. So, if we add more don't have to update the implementation.
		int len = phrases.length;
		
		//get a random integer between 0 and the max index of the phrases array
		Random random = new Random();
		//get an integer between 0 and len - 1
		int choice = random.nextInt(len);
		
		//return the random phrase
		return phrases[choice];
	}

	@Override
	public String toString() {
		return "Bad Guy:  " + name +
				", HP: " + String.format("%.1f",hitPoints);
	}
}
