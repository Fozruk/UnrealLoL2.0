package RADSUnpacker.hexReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HexWriter extends FileOutputStream {
	
	public HexWriter(String file) throws FileNotFoundException
	{
		super(file);
	}
	
	public void writeUint(long uint,Data datatype) throws IOException
	{
		if(datatype == Data.LITTLE_ENDIAN)
		{
			this.write(longToByteArray(uint));
		}else
		{
			
		}
	}
	
	 private static byte[] longToByteArray(long input)
	 {
	 byte[] retVal = new byte[4];
	 retVal[0] = (byte)(input & 0xFF);
	 retVal[1] = (byte)((input >> 8) & 0xFF);
	 retVal[2] = (byte)((input >> 16) & 0xFF);
	 retVal[3] = (byte)((input >> 24) & 0xFF);
	 return retVal;
	 }
	

}
