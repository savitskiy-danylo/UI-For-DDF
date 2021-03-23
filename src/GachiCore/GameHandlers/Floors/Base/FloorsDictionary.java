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
        floors.put(5, new FourthFloor());
        floors.put(6, new FourthFloor());
        floors.put(7, new SeventhFloor());
    }
    private static final FloorsDictionary instance = new FloorsDictionary();
    private FloorsDictionary(){}
    public static FloorsDictionary getInstance(){ return instance; }
    public FloorBuilder getFloor(int numberOfFloor){
        return floors.getOrDefault(numberOfFloor, null);
    }
}
