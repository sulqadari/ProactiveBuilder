package ru.mos.beeline.proactive.builder;

import java.util.ArrayList;

public class Utils
{
    public static String getLength(String str)
    {
        String len = String.format("%02X", (str.toUpperCase().replaceAll("\\s+", "").length() / 2));
        return len;
    }

    public static String getLength(int value)
    {
        String len = String.format("%04X", value);
        return len;
    }

    public static short findTlv(final byte targetVal, byte[] tlv, final short off, final short len)
    {
        byte currTag = (byte)0;
        short offset = (short)0;
        try
        {
            for(short i = (short)0; i < len; ++i)
            {
                currTag = tlv[off + i];
                if (targetVal == currTag)
                {
                    offset = (short)(off + i);
                    break;
                }

                i++;
                i += (short)(tlv[off + i] & (short)0x00FF);
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.printf("Error:\ncurrTag: %02X\noffset: %d\noff: %d\nlen: %d\n", currTag, offset, off, len);
        }
        return offset;
    }

    public static void appendTlv(final byte tag, byte[] dataBuff, final short dataOff, final short dataLen)
    {
        short tlvOff = (short)0;
        short tlvLen = (short)0;

        tlvOff = findTlv(tag, dataBuff, dataOff, dataLen);

        if ((short)0 == tlvOff)    // if tag not found.
        {
            return;
        }

        tlvOff++;           // shift to the length field
        //  get the length value and shift to the data field
        tlvLen = (short)(dataBuff[tlvOff++] & (short)0x00FF);

        switch(tag)
        {
            // this is the main case which shall be performed any way.
            case (byte)0x81:    // COMMAND DETAILS
            {
                assertLength(tlvLen, (short)3);
                //proHdlr.init(dataBuff[tlvOff], dataBuff[(short)(tlvOff + (short)1)], dataBuff[(short)(tlvOff + (short)2)]);
            }break;
            case (byte)0x04:   // DURATION
            case (byte)0x1E:   // ICON IDENTIFIER
            {
                //proHdlr.appendTLV(tag, dataBuff[tlvOff], dataBuff[(short)(tlvOff + (short)1)]);
            }break;
            case (byte)0x2B:    // IMMEDIATE RESPONSE
            case (byte)0x50:    // TEXT ATTRIBUTE
            case (byte)0xB1:    // URL
            {
                if ((byte)0x2B == tag)  // zero-out offset and length in case we deal with Immediate response DO(2B 00)
                    tlvOff = tlvLen = (short)0;
                
                //proHdlr.appendTLV(tag, dataBuff, tlvOff, tlvLen);
            }break;
            case (byte)0x68:    // FRAME IDENTIFIER/DEFAULT FRAME IDENTIFIER (as optional part of SET FRAMES command)
            case (byte)0xE8:    // FRAME IDENTIFIER (COMPREHENSION REQUIRED)
            {
                assertLength(tlvLen, (short)1);
                //proHdlr.appendTLV(tag, dataBuff[tlvOff]);
            }break;
            case (byte)0x66:    // FRAME LAYOUT
            case (byte)0xE6:    // FRAME LAYOUT (COMPREHENSION REQUIRED)
            case (byte)0x8D:    // TEXT STRING
            case (byte)0x0F:    // ITEM
            case (byte)0x8F:    // ITEM (COMPREHENSION REQUIRED)
            {
                //proHdlr.appendTLV(tag, dataBuff[tlvOff], dataBuff, (short)(tlvOff + (short)1), (short)(tlvLen - (short)1));
            }break;
            default:
                throw new RuntimeException("ERROR: unknown tag!");
        }
    }

    private static void assertLength(final short len1, final short len2)
    {
        if (len1 != len2) // strictly 3 bytes of payload.
            throw new RuntimeException("ERROR: length mismatch!");
    }

    public static byte[] toByteArray(String input) throws StringIndexOutOfBoundsException
	{
		input	= input.toUpperCase().replaceAll("\\s+", "");
		int len		= input.length();					
		
		if (len % 2 != 0)
		    throw new StringIndexOutOfBoundsException("ERROR: The length of the input string is odd.");
		
		byte[] buff = new byte[len/2];
		int bufIdx = 0, i = 0;							
		char msn = '0', lsn = '0';							
		byte compiledByte = (byte)0x00;					

		while(i < len)
		{
			msn = input.charAt(i); i++;
			lsn = input.charAt(i); i++;
			compiledByte = (byte)((Character.digit(msn, 16) << 4) | (Character.digit(lsn, 16)));
			buff[bufIdx]	= compiledByte;				
			bufIdx++;
		}
		
		return buff;
	}

    public static String hexify(byte[] bytes)
	{
		ArrayList<String> bytesToString = new ArrayList<String>(bytes.length);
		for(byte b: bytes)
		{
			bytesToString.add(String.format("%02X", b));
		}
		return String.join("", bytesToString);
	}
    

}
