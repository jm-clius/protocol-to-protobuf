package za.co.jmc.util;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Extension of java.io.DataInputStream to provide
 * "Little Endian"-ordered read methods.
 */
public class ExtendedDataInputStream extends DataInputStream {
    /**
     * Creates an ExtendedDataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public ExtendedDataInputStream(InputStream in) {
        super(in);
    }

    /**
     * Reads a signed 16-bit short from this data input stream in Little
     * Endian order.
     *
     * @return the next two bytes of this input stream, interpreted as a
     *      <code>short</code>
     * @throws IOException
     */
    public final short readLittleEndianShort() throws IOException {
        final int lsByte = super.read();
        final int msByte = super.read();
        if ((lsByte | msByte) < 0)
            throw new EOFException();
        return (short)((msByte << 8) + (lsByte << 0));
    }


    /**
     * Reads a signed 32-bit integer from this data input stream in Little
     * Endian order.
     *
     * @return the next four bytes of this input stream, interpreted as an
     *      <code>int</code>.
     * @throws IOException
     */
    public final int readLittleEndianInt() throws IOException {
        final int byte1 = super.read();
        final int byte2 = super.read();
        final int byte3 = super.read();
        final int byte4 = super.read();
        if ((byte1 | byte2 | byte3 | byte4) < 0)
            throw new EOFException();
        return ((byte4 << 24) + (byte3 << 16) + (byte2 << 8) + (byte1 << 0));
    }
}
