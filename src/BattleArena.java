import java.util.Random;
import java.util.Scanner;

/**
 * Patrick Gilfether, Briggs Twitchell, Keegan Moseley
 * CS5004
 * Creative Project
 * Battle Arena class file
 */
public class BattleArena {
    /*
    A Battle arena has
        -heroes
        -badGuys
        -weapons
        -random
     A Battle arena does:
        +displayCombatants()
        +displayWeapons()
        +selectWeapons()
        +Battle(Character x, Character Y)
        -turn()
     */
    private Hero[] heroes ;
    private BadGuy[] badGuys;
    private Weapon[] weapons;
    private Random r;

    public BattleArena( Hero[] heroes, BadGuy[] badGuys, Weapon[] weapons) {
        r = new Random();
        this.heroes = heroes;
        this.badGuys = badGuys;
        this.weapons = weapons;
    }

    public void displayWeapons(){
        System.out.println("WEAPON RACK:");
        for (int i = 1; i<= weapons.length; i++){
            System.out.println(i +": " + weapons[i-1]);
        }
    }

    public void selectWeapon(Hero hero){
        Scanner s = new Scanner(System.in);

        displayWeapons();
        System.out.println("Enter the number of the weapon you choose:");
        int input = s.nextInt();
        Weapon choice = weapons[input-1];
        hero.equipWeapon(choice);
    }

    private Character[] init_Combatants(){
        hero = heroes[r.nextInt(heroes.length)];        //select random hero
        badGuy = badGuys[r.nextInt(badGuys.length)];    //select random badguy
        Character[] combatants = {hero, badGuy};         //prompt user to select hero weapon
        selectWeapon(hero);                             // display / equip weapon
        return combatants;
    }

    public void displayCombatants(Character[] combatants) {
        for (Character c : combatants) {
            System.out.println(c);
        }
    }

    private void turn(Character[] combatants){
        displayCombatants(combatants);
        for (Character c :combatants){
            c.takeTurn();
        }
    }

    public void battle(Character goodGuy, Character badGuy){

        //init battle
        Character[] combatants = init_Combatants();
        //select combatants

        while (goodGuy.getHitPoints()>0 && badGuy.getHitPoints()>0){
            turn(combatants);
        }
        if (goodGuy.getHitPoints()>0){
            System.out.println( goodGuy + " won!");
        }
        if (goodGuy.getHitPoints()>0){
            System.out.println( goodGuy + " has been vanquished. " + badGuy + " won!" );
        }
        System.out.println("GAME OVER");
    }



}
