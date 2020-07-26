package za.co.jmc.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class ExtendedDataInputStreamTest {
    private static ExtendedDataInputStream createExtendedDataInputStream(byte[] buffer){
        return new ExtendedDataInputStream(new ByteArrayInputStream(buffer));
    }

    @Test
    public void readLittleEndianShort() {
        final byte[] allZeroes = new byte[]{0x00, 0x00};
        final byte[] allOnes = new byte[]{(byte)0xFF, (byte)0xFF};
        final byte[] ordered = new byte[]{(byte)0x34, (byte)0x12};
        try{
            assertEquals("All zeroes", 0, createExtendedDataInputStream(allZeroes).readLittleEndianShort());
            assertEquals("All ones", -1, createExtendedDataInputStream(allOnes).readLittleEndianShort());
            assertEquals("Byte order", 0x1234, createExtendedDataInputStream(ordered).readLittleEndianShort());
        } catch (IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void readLittleEndianInt() {
        final byte[] allZeroes = new byte[]{0x00, 0x00, 0x00, 0x00};
        final byte[] allOnes = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
        final byte[] ordered = new byte[]{(byte)0x78, (byte)0x56, (byte)0x34, (byte)0x12};
        try{
            assertEquals("All zeroes", 0, createExtendedDataInputStream(allZeroes).readLittleEndianInt());
            assertEquals("All ones", -1, createExtendedDataInputStream(allOnes).readLittleEndianInt());
            assertEquals("Byte order", 0x12345678, createExtendedDataInputStream(ordered).readLittleEndianInt());
        } catch (IOException e){
            fail(e.getMessage());
        }
    }
}