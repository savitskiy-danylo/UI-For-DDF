package GachiCore.GameHandlers.Floors.Base;

import GachiCore.GameHandlers.Floors.FirstFloor;
import GachiCore.GameHandlers.Floors.SecondFloor;

import java.util.HashMap;
import java.util.Map;

public class FloorsDictionary {
    private final Map<Integer, FloorBuilder> floors = new HashMap<>();
    {
        floors.put(1, new FirstFloor());
        floors.put(2, new SecondFloor());

    }
    private static final FloorsDictionary instance = new FloorsDictionary();
    private FloorsDictionary(){}
    public static FloorsDictionary getInstance(){ return instance; }
    public FloorBuilder getFloor(int numberOfFloor){
        return floors.getOrDefault(numberOfFloor, null);
    }
}
