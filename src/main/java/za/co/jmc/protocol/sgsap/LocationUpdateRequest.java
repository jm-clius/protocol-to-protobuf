package za.co.jmc.protocol.sgsap;

import za.co.jmc.protocol.sgsap.ie.IMSI;
import za.co.jmc.util.ExtendedDataInputStream;

import java.io.IOException;

public class LocationUpdateRequest extends SGsMsg {
    private final IMSI imsi;

    public LocationUpdateRequest(ExtendedDataInputStream extendedDataInputStream) throws IOException {
        super(SGsMsgType.LOCATION_UPDATE_REQUEST);

        extendedDataInputStream.read(); // IEI
        imsi = IMSI.parseLV(extendedDataInputStream);
    }
}
