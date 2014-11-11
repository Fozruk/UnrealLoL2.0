package RADSUnpacker.hexReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HexReader {

	FileInputStream reader;
	String file;

	public HexReader(String file) throws FileNotFoundException {
		reader = new FileInputStream(file);
		this.file = file;
	}

	public long readUint(int length, Data data) throws IOException {
		byte word[] = new byte[length];
		reader.read(word, 0, length);

		if (data == Data.LITTLE_ENDIAN) {
			return byteArrayToLong(word, 0, length);

		} else {
			return 0;
		}
	}
	
	public String readString(int length) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int ch = 0;
		while(length > 0 && (ch = reader.read()) != -1){
		    builder.append((char)ch);
		    --length;
		}
		return builder.toString();
	}
	
	public void seek(int offset) throws IOException
	{
		reader = new FileInputStream(file);
		reader.skip(offset);
	}
	
	public void skip(int length) throws IOException
	{
		reader.skip(length);
	}
		
	public void reset() throws IOException
	{
		reader = new FileInputStream(file);
	}

		
    private long byteArrayToLong(byte[] array, int start, int laenge) {
        long value = 0;
        int starter = 0;
        for(int i = start; i < start+laenge; i++, starter++)
        {
            //Durch 0xFF werden die Werte so umgewandelt, das sie die richtigen Werte anzeigen, so als währen sie unsigned, den Bitshift benötigt man, damit die Daten in der richtigen Reihenfolge angezeigt werden
            value += ( array[i] & 0xFFL) << (8*starter);
        }
        return value;
    }

}
