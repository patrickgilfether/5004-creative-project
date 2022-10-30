/*
 * Author : Keegan Moseley
 * Date : Oct 19, 2022
 * 5004 Creative Lab
 * 
 * This file is the Hero class
 */

import java.util.Scanner;

/**
 * This class represents a Hero Character. It is a child of the 
 * Character class.
 * 
 * @author Keegan Moseley
 *
 */
public class Hero extends Character{
	//field for the Hero's mana, limits how much magic they can perform
	private double mana = 0;
	
	/**
	 * Constructor for a Hero object.
	 * 
	 * @param name, the hero's name
	 * @param hP, the hero's health points
	 * @param strength, the hero's strength
	 * @param mana, the hero's mana.
	 * @throws IllegalArgumentException if the input hP is equal to or less than zero.
	 * 			Also thrown if the input strength is less than one.
	 * 			Also thrown if the mana is less than zero.
	 */
	public Hero(String name, double hP, double strength, double mana) throws IllegalArgumentException{
		//construct the hero
		super(name, hP, strength);
		
		//check the mana input
		if (mana < 0) {
			throw new IllegalArgumentException("You cannot create a Hero with negative mana.");
		}
		//set the mana field
		this.mana = mana;
	}


	/**
	 * This method allows the user to select an attack for their Character to make.
	 * 
	 * @parameter opponent, Character object to be targeted
	 * @return double, how much damage was taken by the opponent
	 */
	@Override
	public double takeTurn(Character opponent) {
		//how much damage was taken
		double damage = 0;
		
		//Accept the user input
		Scanner userSelection = new Scanner(System.in);
		
		//while loop to validate use input
		boolean flag = true;
		while (flag) {
			try {
				//print options to the user
				System.out.printf("Select 1 for a basic attack.\n");
				System.out.printf("Select 2 for a weapon attack.\n");
				System.out.printf("Select 3 for a special attack.\n");
				System.out.printf("Your selection :\n");
				
				int input = userSelection.nextInt();
				
				//select the correct attack, based on their input
				switch(input) {
					case 1:
						this.basicAttack(opponent);
						flag = false;
						break;
					case 2:
						this.attackWithWeapon(opponent);
						flag = false;
						break;
					case 3:
						this.specialAttackWithWeapon(opponent);
						flag = false;
						break;
					default:
						//the user did not enter a valid number
						throw new IllegalArgumentException(); 
				}
			}
			//process the user's invalid input
			catch (IllegalArgumentException e) {
				System.out.printf("You must enter an integer between 1 - 3.\n");
			}
		}
		
		userSelection.close();
		return damage;
	}	
	
	
	
	/**
	 * If the hero used a magic weapon, decrease their mana.
	 * 
	 * @param amount, how much mana was used
	 * @throws IllegalArgumentException if the input amount to decrease the mana was negative
	 */
	public void decreaseMana(double amount) throws IllegalArgumentException{
		if (amount < 0) {
			throw new IllegalArgumentException("You cannot decrease a Character's mana via a negative input.");
		}
		
		this.mana -= amount;
	}
	
	/**
	 * This method represents the attacking an opponent with the weapon's
	 * special attack.
	 * 
	 * @param opponent, the Character to take damage
	 * @return double, how much damage was inflicted
	 */
	public double specialAttackWithWeapon(Character opponent) {
		
		double damage = this.weapon.useSpecial();
		
		//TODO ensure this all works with the specific implementation of the weapon classes
		
		opponent.takeDamage(damage);
		
		return damage;
	}
	
	/**
	 * Getter for the hero's mana field.
	 * 
	 * @return double, how much mana the hero has.
	 */
	public double getMana() {
		return this.mana;
	}

}
