package RADSUnpacker;

import RADSUnpacker.hexReader.Data;
import RADSUnpacker.hexReader.HexReader;

import java.io.IOException;

/**
 * Created by philipp on 10.11.2014.
 */
public class WWise {

    String engine;
    long version;
    long fileAmount;

     public WWise() throws IOException {

         HexReader reader = new HexReader("C:\\Riot Games\\League of Legends\\RADS\\projects\\lol_game_client_en_gb\\managedfiles\\0.0.0.230\\DATA\\Sounds\\Wwise\\VO\\en_US\\Characters\\Soraka\\Skins\\Skin04\\Soraka_Skin04_VO_audio.wpk");
         engine = reader.readString(4);
         version = reader.readUint(4,Data.LITTLE_ENDIAN);
         fileAmount = reader.readUint(4,Data.LITTLE_ENDIAN);
         System.out.println("i");

         for(int i = 0; i < fileAmount ; i++)
         {
             long offset = reader.readUint(4,Data.LITTLE_ENDIAN);
             reader.seek((int)offset);

             offset = reader.readUint(4,Data.LITTLE_ENDIAN);
             System.out.println("Filesize: " + reader.readUint(4,Data.LITTLE_ENDIAN));
             long stringlen = reader.readUint(4,Data.LITTLE_ENDIAN);
             System.out.println("Name: " + reader.readString((int)stringlen));
             reader.seek((int)offset);

         }

     }


    public static void main(String[] args) throws IOException {
        new WWise();
    }



}
