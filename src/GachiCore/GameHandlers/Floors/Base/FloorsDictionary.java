package GachiCore.GameHandlers.Floors.Base;

import GachiCore.GameHandlers.Floors.*;

import java.util.HashMap;
import java.util.Map;

public class FloorsDictionary {
    private final Map<Integer, FloorBuilder> floors = new HashMap<>();
    {
        floors.put(1, new FirstFloor());
        floors.put(2, new SecondFloor());
        floors.put(3, new ThirdFloor());
        floors.put(4, new FourthFloor());
        floors.put(5, new FifthFloor());
        floors.put(6, new SixthFloor());
        floors.put(7, new SeventhFloor());
        floors.put(8, new SeventhFloor());
        floors.put(9, new SeventhFloor());
    }
    private static final FloorsDictionary instance = new FloorsDictionary();
    private FloorsDictionary(){}
    public static FloorsDictionary getInstance(){ return instance; }
    public FloorBuilder getFloor(int numberOfFloor){
        return floors.getOrDefault(numberOfFloor, null);
    }
}


/*
money=1000;
items=M4A4,LatexCostume,Truth,LargeHealPotion,LargeHealPotion,LargeHealPotion;
armor=LatexCostume;
weapon=M4A4;
gachiPower=100;
floor=1;

* */