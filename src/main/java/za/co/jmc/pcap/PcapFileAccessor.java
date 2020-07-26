package za.co.jmc.pcap;

import za.co.jmc.util.ExtendedDataInputStream;

import java.io.*;
import java.util.Optional;

/**
 * Reads a .pcap-file and provides method calls to read file on a per-packet
 * basis.
 *
 */
public class PcapFileAccessor {
    private static final long FORWARD_MAGIC_NUMBER = 0xa1b2c3d4L;   // Indicates file is in Big Endian
    private static final long SWAPPED_MAGIC_NUMBER = 0xd4c3b2a1L; // Indicates file is in Little Endian
    private static final int READ_BUFFER_SIZE = 65535; //128kB
    private final File pcapFile;
    private final ExtendedDataInputStream extendedDataInputStream;

    // File properties
    private final long magicNumber;
    private final boolean isLittleEndian;
    private int versionMajor; // major version number
    private int versionMinor; // minor version number
    private int thisZone; // GMT to local correction
    private int sigFigs; // accuracy of timestamps
    private int snapLen; // max length of captured packets, in octets
    private int network; // data link type

    private PcapFileAccessor(File pcapFile) throws IOException, IllegalArgumentException {
        this.pcapFile = pcapFile;
        this.extendedDataInputStream =
                new ExtendedDataInputStream(
                    new BufferedInputStream(
                        new FileInputStream(this.pcapFile),
                        READ_BUFFER_SIZE));

        this.magicNumber = (long) extendedDataInputStream.readInt() & 0xFFFFFFFFL; // Read as long and 0 out MSBs to ensure unsigned

        if (magicNumber == FORWARD_MAGIC_NUMBER){
            // PCAP Headers are Big Endian ordered
            isLittleEndian = false;
            openBigEndian(extendedDataInputStream);
        } else if (magicNumber == SWAPPED_MAGIC_NUMBER){
            // PCAP Headers are Little Endian ordered
            isLittleEndian = true;
            openLittleEndian(extendedDataInputStream);
        } else {
            throw new IllegalArgumentException("Unexpected file format encountered");
        }
    }

    private void openLittleEndian(ExtendedDataInputStream eDis) throws IOException{
        this.versionMajor = eDis.readLittleEndianShort() & 0xFFFF;
        this.versionMinor = eDis.readLittleEndianShort() & 0xFFFF;
        this.thisZone = eDis.readLittleEndianInt();
        this.sigFigs = eDis.readLittleEndianInt();
        this.snapLen = eDis.readLittleEndianInt();
        this.network = eDis.readLittleEndianInt();
    }

    private void openBigEndian(ExtendedDataInputStream eDis) throws IOException{
        this.versionMajor = eDis.readShort() & 0xFFFF;
        this.versionMinor = eDis.readShort() & 0xFFFF;
        this.thisZone = eDis.readInt();
        this.sigFigs = eDis.readInt();
        this.snapLen = eDis.readInt();
        this.network = eDis.readInt();
    }

    public static PcapFileAccessor readPcapFile(String fileName) throws IOException{
        return new PcapFileAccessor(new File(fileName));
    }

    /**
     * Reads a single .pcap packet from this file
     *
     * @return Optional<PcapPacket>
     * @throws IOException
     */
    public Optional<PcapPacket> readPcapPacket() throws IOException{
        try{
            final PcapPacket pcapPacket = new PcapPacket(
                    isLittleEndian,
                    extendedDataInputStream);
            return Optional.of(pcapPacket);
        } catch (EOFException e){
            return Optional.empty();
        }
    }

    public int getDataLinkType() {
        return network;
    }

    public void close() throws IOException {
        this.extendedDataInputStream.close();
    }

    @Override
    public String toString() {
        return "PcapReader{" +
                "pcapFile=" + pcapFile +
                ", extendedDataInputStream=" + extendedDataInputStream +
                ", magicNumber=" + magicNumber +
                ", versionMajor=" + versionMajor +
                ", versionMinor=" + versionMinor +
                ", thisZone=" + thisZone +
                ", sigFigs=" + sigFigs +
                ", snapLen=" + snapLen +
                ", network=" + network +
                '}';
    }
}

