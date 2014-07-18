package RADSUnpacker; /**
 * Created with IntelliJ IDEA. User: philipp.hentschel Date: 03.07.14 Time: 11:08 To change this template use File |
 * Settings | File Templates.
 */

import RADSSoundPatcher.Misc.ArrayUtils;

import java.util.List;

/**
 * Little Endian Word ( ͡° ͜ʖ ͡°), grundlegendse Dateneinheit in dem Filearchiv, die in die Entsprechenden Listen einsortiert werden muss
 */
public class LittleEndian {

    private byte[] rawContent;


    public LittleEndian(byte word[]) {
        this.rawContent = word.clone();
    }

    public LittleEndian(List<Byte> filebyteList, int offset)
    {
        rawContent = new byte[]{filebyteList.get(offset),filebyteList.get(offset+1),filebyteList.get(offset+2),filebyteList.get(offset+3)};
    }

    public LittleEndian(byte word[], int offset, int length)
    {
        rawContent = new byte[length];
        int counter = 0;
        for(int i = offset; i < offset + length ; i++)
        {
            rawContent[counter] = word[i];
            counter++;
        }
    }

    public long getContent() {
        return byteArrayToLong(rawContent,0,this.rawContent.length);
    }

    public long getContentBigEndian()
    {
        long value = 0;
        for (int i = 0; i < rawContent.length; i++)
        {
            value = (value << 8) + (rawContent[i] & 0xff);
        }
        return value;
    }

    public String getString()
    {
        StringBuffer strb = new StringBuffer();
        for (int i = 0; i < this.rawContent.length ; i++) {
            strb.append((char) ((int) (rawContent[i]) & 0xFF));
        }
        return strb.toString();
    }

    public String getHexString()
    {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[rawContent.length * 2];
        for ( int j = 0; j < rawContent.length; j++ ) {
            int v = rawContent[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public byte[] getRawContent() { return rawContent;}

    public void setContent(long content) {
        this.rawContent = ArrayUtils.longToByteArray(content);
    }

    private long byteArrayToLong(byte[] array, int start, int laenge) {
        long value = 0;
        int starter = 0;
        for (int i = start; i < start + laenge; i++, starter++) {
            // Durch 0xFF werden die Werte so umgewandelt, das sie die richtigen Werte anzeigen, so als währen sie
            // unsigned, den Bitshift benötigt man, damit die Daten in der richtigen Reihenfolge angezeigt werden
            value += (array[i] & 0xFFL) << (8 * starter);
        }
        return value;
    }




    public static void main(String[]args)
    {
        LittleEndian word = new LittleEndian(new byte[]{1,2,3,4});
        long test = word.getContent();
        word.setContent(test);
    }
}
