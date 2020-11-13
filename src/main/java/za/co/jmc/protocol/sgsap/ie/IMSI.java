package za.co.jmc.protocol.sgsap.ie;

import za.co.jmc.util.ExtendedDataInputStream;

import java.io.IOException;

public class IMSI {
    private final String imsiString;

    public IMSI(String imsiString){
        this.imsiString = imsiString;
    }

    public static IMSI parseLV(ExtendedDataInputStream extendedDataInputStream) throws IOException {
        final int length = extendedDataInputStream.read();
        final int firstByte = extendedDataInputStream.read();
        final int firstDigit = (firstByte & 0xF0) >> 4;
        final int parity = (firstByte & 0x08) >> 3;
        final StringBuilder sb = new StringBuilder(length/2 - 2 + parity);
        sb.append(firstDigit);
        for (int i = 0; i < length - 2; i ++){
            final int nextByte = extendedDataInputStream.read();
            sb.append(nextByte & 0x0f);
            sb.append((nextByte & 0xf0) >> 4);
        }

        final int lastByte = extendedDataInputStream.readByte();
        sb.append(lastByte & 0x0f);
        if (parity == 0x01){
            sb.append((lastByte & 0xF0) >> 4);
        }

        return new IMSI(sb.toString());
    }

    @Override
    public String toString() {
        return "IMSI{" +
                "imsiString='" + imsiString + '\'' +
                '}';
    }
}
