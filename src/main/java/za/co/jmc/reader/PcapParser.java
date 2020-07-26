package za.co.jmc.reader;

import za.co.jmc.pcap.LinkLayerHeaderType;
import za.co.jmc.pcap.PcapFileAccessor;
import za.co.jmc.pcap.PcapPacket;
import za.co.jmc.util.ExtendedDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Reads and parses a .pcap-file according to dlt_linktype.
 */
public class PcapParser {
    /**
     * Reads and parses .pcap-file located at <code>filename</code>
     *
     * @param filename
     * @throws IOException
     */
    public static void read(String filename) throws IOException {
        // Open .pcap-file
        final PcapFileAccessor pcapFileAccessor = PcapFileAccessor.readPcapFile(filename);
        // Determine link-layer type
        final LinkLayerHeaderType linkLayerHeaderType = LinkLayerHeaderType.fromValue(pcapFileAccessor.getDataLinkType());
        // Read first pcap packet
        PcapPacket pcapPacket = pcapFileAccessor.readPcapPacket().orElse(null);

        // Parse first pcap packet (if present) and iterate over next packets until EOF
        while (pcapPacket != null){
            switch (linkLayerHeaderType){
                case LINKTYPE_PPI:
                    // Parse per-packet information data
                    PPIParser.parse(
                            new ExtendedDataInputStream(
                                    new ByteArrayInputStream(
                                            pcapPacket.getData()
                                    )
                            ));
                    break;
                case LINKTYPE_ETHERNET:
                default:
                    // TODO: Other linktypes not yet supported
                    throw new IllegalArgumentException("Unsupported LinkLayerType: " + linkLayerHeaderType.name());
            }
            pcapPacket = pcapFileAccessor.readPcapPacket().orElse(null);
        }

        pcapFileAccessor.close();
    }

    public static void usage(String error){
        System.out.println(error + "\nusage: PcapParser fileName");
    }

    public static void main(String[] args) throws IOException{
        if (args.length != 1) usage("Unexpected number of arguments.");
        read(args[0]);
    }
}
