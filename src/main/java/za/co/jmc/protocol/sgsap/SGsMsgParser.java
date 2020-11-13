package za.co.jmc.protocol.sgsap;

import za.co.jmc.util.ExtendedDataInputStream;

import java.io.IOException;

public class SGsMsgParser {

    public static SGsMsg parse(ExtendedDataInputStream extendedDataInputStream) throws IOException {
        final SGsMsgType msgType = SGsMsgType.fromValue(extendedDataInputStream.read()).orElse(SGsMsgType.UNSUPPORTED);
        switch (msgType){
            case LOCATION_UPDATE_REQUEST:
                return new LocationUpdateRequest(extendedDataInputStream);
            case UNSUPPORTED:
            default:
                throw new IllegalStateException("Unexpected value: " + msgType);
        }

    }
}
