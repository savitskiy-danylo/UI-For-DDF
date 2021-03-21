package GachiCore.GameHandlers;

import java.io.*;

public class SaveHandler {
    private static final SaveHandler instance = new SaveHandler();
    private final File fileSave = new File("Save.txt");

    private SaveHandler(){ }

    public static SaveHandler getInstance() {
        return instance;
    }

    public void save(){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileSave, false), "Cp1251"))){
            writer.write(getSave());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        //TODO LOAD
    }

    public String getSave(){
        return "SAVE";//TODO SAVE
    }
}
