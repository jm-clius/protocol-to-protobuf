package za.co.jmc.pcap;

import za.co.jmc.util.ExtendedDataInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Reads a .pcap-packet and provide access to headers and data as byte[]
 *
 */
public class PcapPacket {
    private final int tsSec; // timestamp seconds
    private final int tsUsec; // timestamp microseconds
    private final int inclLen; // number of octets of packet saved in file
    private final int origLen; // actual length of packet
    private final byte[] data;

    public PcapPacket(boolean isLittleEndian, ExtendedDataInputStream extendedDataInputStream) throws IOException{
        if (isLittleEndian){
            tsSec = extendedDataInputStream.readLittleEndianInt();
            tsUsec = extendedDataInputStream.readLittleEndianInt();
            inclLen = extendedDataInputStream.readLittleEndianInt();
            origLen = extendedDataInputStream.readLittleEndianInt();
        } else{
            tsSec = extendedDataInputStream.readInt();
            tsUsec = extendedDataInputStream.readInt();
            inclLen = extendedDataInputStream.readInt();
            origLen = extendedDataInputStream.readInt();
        }
        data = new byte[inclLen];

        extendedDataInputStream.readFully(data, 0, inclLen);
    }

    public int getTsSec() {
        return tsSec;
    }

    public int getInclLen() {
        return inclLen;
    }

    public int getOrigLen() {
        return origLen;
    }

    /**
     * @return the pcap packet data as a <code>byte[]</code>
     */
    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PcapPacket{" +
                "tsSec=" + tsSec +
                ", tsUsec=" + tsUsec +
                ", inclLen=" + inclLen +
                ", origLen=" + origLen +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
