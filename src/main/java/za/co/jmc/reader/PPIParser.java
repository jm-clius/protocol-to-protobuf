package za.co.jmc.reader;

import za.co.jmc.pcap.PPIHeader;
import za.co.jmc.util.ExtendedDataInputStream;

import java.io.IOException;

/**
 * Parse data in per-packet information format.
 */
public class PPIParser {

    /**
     * Parse data in per-packet information format
     *
     * @param extendedDataInputStream wrapping data
     * @throws IOException
     */
    public static void parse(ExtendedDataInputStream extendedDataInputStream) throws IOException {
        // Read PPI header. This does not advance data input stream cursor.
        final PPIHeader ppiHeader = new PPIHeader(extendedDataInputStream);
        // Skip entire PPI header
        extendedDataInputStream.skipBytes(ppiHeader.getPPIHeaderLength());      // PPI Header does not advance cursor

        // Retrieve user-defined datalink type
        final PPIDatalinkType datalinkType = PPIDatalinkType.fromValue(ppiHeader.getPPIHeaderDatalinkType());

        switch (datalinkType){
            case SGS_AP:
                //TODO: parse SGsAP here
                break;
            default:
                // Unsupported user-defined datalink type
                throw new IllegalArgumentException("Unknown user-defined DLT: " + datalinkType);
        }
    }
}
