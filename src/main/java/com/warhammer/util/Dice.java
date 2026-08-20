package com.warhammer.util;

import java.util.Random;
/**
 * Represents a variable sided dice.
 */
public class Dice {
    static Random r = new Random();
    public Dice(){
    }
    public static int roll(int sides){
        return r.nextInt(sides)+1;
    }
}
