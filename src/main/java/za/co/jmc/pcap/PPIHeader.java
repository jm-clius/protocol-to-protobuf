package za.co.jmc.pcap;

import za.co.jmc.util.ExtendedDataInputStream;

import java.io.IOException;

/**
 *
 * Reads a PPI Header from provided data input stream.
 * NB: this action does NOT advance cursor in the data input stream.
 *
 * typedef struct ppi_packetheader {
 *         u_int8_t pph_version; /* Version. Currently 0
 *         u_int8_t pph_flags;   /* Flags. *
 *         u_int16_t pph_len;  /* Length of entire message,
 *                              * including this header and TLV
 *                              * payload.
 *         u_int32_t pph_dlt;  /* Data Link Type of the captured
 *                              * packet data.
 * }ppi_packetheader_t;
 */
public class PPIHeader {
    private static final int MAX_PPI_HEADER_LENGTH = 65532; // See PPI Header Specification
    private final int pphVersion;   // 8-bit int
    private final int pphFlags;     // 8-bit int
    private final int pphLen;       // 16-bit int
    private final int pphDlt;       // 32-bit int

    public PPIHeader(ExtendedDataInputStream extendedDataInputStream) throws IOException {
        extendedDataInputStream.mark(MAX_PPI_HEADER_LENGTH);    // Mark current position in input stream to resume reading from same point
        pphVersion = extendedDataInputStream.read();
        pphFlags = extendedDataInputStream.read();
        pphLen = extendedDataInputStream.readLittleEndianShort();
        pphDlt = extendedDataInputStream.readLittleEndianInt();
        extendedDataInputStream.reset();                                 // Reset to mark (pre-header)
    }

    public int getPPIHeaderLength() {
        return pphLen;
    }

    public int getPPIHeaderDatalinkType() {
        return pphDlt;
    }
}
