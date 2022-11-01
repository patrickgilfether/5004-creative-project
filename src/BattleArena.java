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
            System.out.println("\n\t"+ i +": " + weapons[i-1]);
        }
    }

    public void selectWeapon(Hero hero) {
        displayWeapons();
        Scanner s = new Scanner(System.in);
        //while loop to validate use input
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("\nEnter the number of the weapon you choose:");
                int input = s.nextInt();


                //select the correct action, based on their input

                switch (input) {
                    case 1:
                        hero.equipWeapon(weapons[0]);
                        flag = false;
                        break;
                    case 2:
                        hero.equipWeapon(weapons[1]);
                        flag = false;
                        break;
                    case 3:
                        hero.equipWeapon(weapons[2]);
                        flag = false;
                        break;
                    case 4:
                        hero.equipWeapon(weapons[3]);
                        flag = false;
                        break;
                }
            } catch (IllegalArgumentException e) {                //process the user's invalid input
                System.out.printf("\tYou must enter an integer between 1 - 4.\n");
            }

            System.out.println();
        }
    }

//    private Character[] init_Combatants(){
//        Hero hero = heroes[r.nextInt(heroes.length)];        //select random hero
//        BadGuy badGuy = badGuys[r.nextInt(badGuys.length)];    //select random badguy
//        Character[] combatants = {hero, badGuy};         //prompt user to select hero weapon
//        selectWeapon(hero);                             // display / equip weapon
//        return combatants;
//    }

    public void displayCombatants(Character[] combatants) {
        for (Character c : combatants) {
            System.out.println(c);
        }
    }

    private void turn(Character[] combatants) throws InterruptedException {
        Thread.currentThread().sleep(2000);
        System.out.println();
        displayCombatants(combatants);
        System.out.println();

        System.out.println(combatants[0].getName() +"'s turn:");
        combatants[0].takeTurn(combatants[1]);

        Thread.currentThread().sleep(2000);

        System.out.println();
        System.out.println(combatants[1].getName() +"'s turn:");
        Thread.currentThread().sleep(1000);
        combatants[1].takeTurn(combatants[0]);
//        System.out.println();
    }

    public void battle() throws InterruptedException {

        System.out.println("\n" +
                "______  ___ _____ _____ _      _____    ___  ______ _____ _   _   ___  \n" +
                "| ___ \\/ _ \\_   _|_   _| |    |  ___|  / _ \\ | ___ \\  ___| \\ | | / _ \\ \n" +
                "| |_/ / /_\\ \\| |   | | | |    | |__   / /_\\ \\| |_/ / |__ |  \\| |/ /_\\ \\\n" +
                "| ___ \\  _  || |   | | | |    |  __|  |  _  ||    /|  __|| . ` ||  _  |\n" +
                "| |_/ / | | || |   | | | |____| |___  | | | || |\\ \\| |___| |\\  || | | |\n" +
                "\\____/\\_| |_/\\_/   \\_/ \\_____/\\____/  \\_| |_/\\_| \\_\\____/\\_| \\_/\\_| |_/\n" +
                "                                                                       \n" +
                "                                                                       \n");
        System.out.println("WELCOME TO THE BATTLE ARENA!\n");
        //init battle
        Hero hero = heroes[r.nextInt(heroes.length)];        //select random hero
        BadGuy badGuy = badGuys[r.nextInt(badGuys.length)];    //select random badguy
        System.out.println("YOUR HERO: " + hero.getName() + " will be fighting the BAD GUY: " + badGuy.getName() +"\n");

        System.out.println("CHOOSE YOUR WEAPON!\n");
        Character[] combatants = {hero, badGuy};         //prompt user to select hero weapon
        selectWeapon(hero);



        System.out.println(" (                           )                                 (                          (       )  \n" +
                " )\\ )      *   )    *   ) ( /(         (    (      *   )  *   ))\\ )         (      (      )\\ ) ( /(  \n" +
                "(()/( (  ` )  /(  ` )  /( )\\())(     ( )\\   )\\   ` )  /(` )  /(()/( (     ( )\\ (   )\\ )  (()/( )\\()) \n" +
                " /(_)))\\  ( )(_))  ( )(_)|(_)\\ )\\    )((_|(((_)(  ( )(_))( )(_))(_)))\\    )((_))\\ (()/(   /(_)|(_)\\  \n" +
                "(_)) ((_)(_(_())  (_(_()) _((_|(_)  ((_)_ )\\ _ )\\(_(_())(_(_()|_)) ((_)  ((_)_((_) /(_))_(_))  _((_) \n" +
                "| |  | __|_   _|  |_   _|| || | __|  | _ )(_)_\\(_)_   _||_   _| |  | __|  | _ ) __(_)) __|_ _|| \\| | \n" +
                "| |__| _|  | |      | |  | __ | _|   | _ \\ / _ \\   | |    | | | |__| _|   | _ \\ _|  | (_ || | | .` | \n" +
                "|____|___| |_|      |_|  |_||_|___|  |___//_/ \\_\\  |_|    |_| |____|___|  |___/___|  \\___|___||_|\\_| ");
        while (hero.getAlive() && badGuy.getAlive()){
            turn(combatants);
        }
        if (hero.getAlive()){
            System.out.println();
            System.out.println("__  _________ _____  __       ___       _______________   __\n" +
                    "_ \\/ /__  __ \\__  / / /       __ |     / /____  _/___  | / /\n" +
                    "__  / _  / / /_  / / /        __ | /| / /  __  /  __   |/ / \n" +
                    "_  /  / /_/ / / /_/ /         __ |/ |/ /  __/ /   _  /|  /  \n" +
                    "/_/   \\____/  \\____/          ____/|__/   /___/   /_/ |_/   ");
            System.out.println( hero + " won!");
        }
        else{
            System.out.println();
            System.out.println("▓██   ██▓ ▒█████   █    ██     ██▓     ▒█████    ██████ ▓█████ \n" +
                    " ▒██  ██▒▒██▒  ██▒ ██  ▓██▒   ▓██▒    ▒██▒  ██▒▒██    ▒ ▓█   ▀ \n" +
                    "  ▒██ ██░▒██░  ██▒▓██  ▒██░   ▒██░    ▒██░  ██▒░ ▓██▄   ▒███   \n" +
                    "  ░ ▐██▓░▒██   ██░▓▓█  ░██░   ▒██░    ▒██   ██░  ▒   ██▒▒▓█  ▄ \n" +
                    "  ░ ██▒▓░░ ████▓▒░▒▒█████▓    ░██████▒░ ████▓▒░▒██████▒▒░▒████▒\n" +
                    "   ██▒▒▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒    ░ ▒░▓  ░░ ▒░▒░▒░ ▒ ▒▓▒ ▒ ░░░ ▒░ ░\n" +
                    " ▓██ ░▒░   ░ ▒ ▒░ ░░▒░ ░ ░    ░ ░ ▒  ░  ░ ▒ ▒░ ░ ░▒  ░ ░ ░ ░  ░\n" +
                    " ▒ ▒ ░░  ░ ░ ░ ▒   ░░░ ░ ░      ░ ░   ░ ░ ░ ▒  ░  ░  ░     ░   \n" +
                    " ░ ░         ░ ░     ░            ░  ░    ░ ░        ░     ░  ░\n" +
                    " ░ ░                                                           ");
            System.out.println( hero + " has been vanquished. " + badGuy + " won!" );
        }
        System.out.println();
        System.out.println();
        System.out.println("GAME OVER");
    }

}
