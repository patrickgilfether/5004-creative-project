import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;

public class CharacterTest {
	Hero hero1;
	Hero hero2;
	BadGuy badGuy1;
	Weapon weapon;
	
	@Before
	public void setUp(){
		hero1 = new Hero("Joe", 10, 10, 10);
		badGuy1 = new BadGuy("Shmoe", 50, 10);
		hero2 = new Hero("Moe", 10, 10, 10);
		
		//TODO implement weapon constructor. Need to know the parameters
		weapon = new Melee();
	}

	@Test
	/**
	 * Test Hero constructor & basic getters
	 */
	public void heroConstructorTest() {
		
		
		assertEquals("Joe", hero1.getName());
		assertEquals(10.0, hero1.getHitPoints(), 0.0001);
		assertEquals(10.0, hero1.getStrength(), 0.001);
		assertEquals(10.0, hero1.getMana(), 0.0001);
		assertEquals(null, hero1.getWeapon());
	}
	
	@Test
	/**
	 * Test for the BadGuy constructor & basic getters
	 */
	public void BadGuyConstructorTest() {
		assertEquals("Shmoe", badGuy1.getName());
		assertEquals(50, badGuy1.getHitPoints(), 0.001);
		assertEquals(10, badGuy1.getStrength(), 0.001);
		assertEquals(null, badGuy1.getWeapon());
	}

	@Test (expected = IllegalArgumentException.class)
	/**
	 * Test for inputting the incorrect values 
	 * in the constructor throwing exceptions
	 */
	public void FailedConstructorTest() {
		//hp fail
		Hero heroFail1 = new Hero("Fail", 0, 1, 10);
		//Strength fail
		Hero heroFail2 = new Hero("Fail", 1, 0, 0);
		//Mana fail
		Hero heroFail3 = new Hero("Fail", 1, 1, -1);
		
		//hp fail
		BadGuy badFail1 = new BadGuy("Fail", -1, 1);
		//Strength fail
		BadGuy badFail2 = new BadGuy("Fail", 5, -1);
	}
	
	/**
	 * Test equipping a weapon, and the weapon being re-equipped
	 */
	@Test
	public void equipWeaponTest() {
		//equip the hero with the weapon
		hero1.equipWeapon(weapon);
		
		//check the weapon's user
		assertEquals(hero1, weapon.getUser());
		
		assertNotNull(hero1.getWeapon());
		
		//equip the other hero with a weapon
		hero2.equipWeapon(weapon);
		
		//check the weapon's user
		assertEquals(hero2, weapon.getUser());
		
		//ensure the first hero doesn't have that weapon.
		assertNull(hero1.getWeapon());
		
		//TODO may need to do more, b/c different kinds of weapons
	}
	
	/**
	 * Test the basic attack
	 */
	@Test
	public void testBasicAttack() {
		//this hero should always do 1 damage
		Hero weakHero = new Hero("weak", 10, 1, 0);
		//badguy to absorb all the attacks
		BadGuy invincible = new BadGuy("Bad Guy", 1000000, 10);
		
		//fuzzy test
		for (int i = 1; i <= 100; i++) {
			//get the value of the target's health
			double prevBadGuyHealth = invincible.getHitPoints();
			
			//have three characters attack the target
			double weakDamage = weakHero.basicAttack(invincible);
			double badGuyDamage = badGuy1.basicAttack(invincible);
			double heroDamage = hero1.basicAttack(invincible);
			
			//ensure the target took an accurate amount of damage
			double totalDam = weakDamage + badGuyDamage + heroDamage;
			double expectedHealth = prevBadGuyHealth - totalDam;
			assertEquals(expectedHealth, invincible.getHitPoints(), 0.0001);
			
			//assert the weak hero did 1 damage
			assertTrue(weakDamage == 1);
			//assert the bad guy did a valid amount of damage
			assertTrue(badGuyDamage >= 1 && badGuyDamage <= badGuy1.getStrength());
			//assert the hero did a valid amount of damage
			assertTrue(heroDamage >= 1 && heroDamage <= hero1.getStrength());
			
		}
	}
	
	/**
	 * Test attacking with a weapon
	 */
	@Test
	public void testWeaponAttack() {
		//give the hero a weapon
		hero1.equipWeapon(weapon);
		
		//save the Bad Guy's health
		double prevBadGuyHealth = badGuy1.getHitPoints();
		
		//have the hero attack the Bad Guy
		double damage = hero1.attackWithWeapon(badGuy1);
		
		//TODO right now just using enum. getDamage would be useful for better tests.
		//ensure the damage of the attack is the damage of the weapon
		//assertEquals(weapon.getDamage(), damage, 0.0001);
		
		//get the expected health post-attack of the bad guy
		double expectedHealth = prevBadGuyHealth - damage;
		
		//compare the expected health and the actual health
		assertEquals(badGuy1.getHitPoints(), expectedHealth, 0.0001);
		
		//TODO may need more, since dif kinds of weapons
	}
	
	@Test
	/**
	 * Test damaging, then killing, a character
	 * 
	 */
	public void testTakeDamAndAlive() {
		//the hero has 10 health
		hero1.takeDamage(5);
		assertEquals(hero1.getHitPoints(), 5, 0.0001);
		assertTrue(hero1.getAlive());
		
		//this should kill the hero
		hero1.takeDamage(5);
		assertEquals(hero1.getHitPoints(), 0, 0.00001);
		assertFalse(hero1.getAlive());
	}
	
	/**
	 * Test inputing an exception to the takeDamage method
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testDamageException() {
		hero1.takeDamage(-1);
	}
	
	@Test
	/**
	 * Test decreasing a hero's mana.
	 * 
	 */
	public void testDecreaseMana() {
		hero1.decreaseMana(6);
	
		assertEquals(4, hero1.getMana(), 0.0001);
	}
	
	/**
	 * Test inputing an exception to the decreaseMana method
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testManaException() {
		hero1.decreaseMana(-1);
	}
	
	//TODO need to add take turns and phrases tests. Prob can only do takeTurn for BadGuy
}
