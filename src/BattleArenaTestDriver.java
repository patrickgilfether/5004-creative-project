public class BattleArenaTestDriver {
    public static void main(String[] args){
        //declarations
        Hero hero1;
        Hero hero2;
        BadGuy badGuy1;
        BadGuy badGuy2;
        Hero[] heroes = new Hero[2];
        BadGuy[] badGuys= new BadGuy[2];
        Weapon[] weapons = new Weapon[3];
        Weapon melee1;
        Weapon magic1;
        Weapon ranged1;
        BattleArena battle1;

        //init
        hero1 = new Hero("Joe", 50, 10, 10);
        hero2 = new Hero("Moe", 50, 10, 10);
        heroes[0] = hero1;
        heroes[1] = hero2;

        badGuy1 = new BadGuy("Shmoe", 50, 10);
        badGuy2 = new BadGuy("Curly", 50, 10);
        badGuys[0] = badGuy1;
        badGuys[1] = badGuy2;

        melee1 = new Melee();
        magic1 = new Magic();
        ranged1 = new Ranged();
        weapons[0] = melee1;
        weapons[1] = magic1;
        weapons[2] = ranged1;

        battle1 = new BattleArena(heroes,badGuys,weapons);

        //battle
        battle1.battle();
    }
}
