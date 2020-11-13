package za.co.jmc.protocol.sgsap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SGsMsgType {
    LOCATION_UPDATE_REQUEST(0x09),
    UNSUPPORTED(0xFFFFFFFF);

    public int value;

    SGsMsgType(int value) {
        this.value = value;
    }

    private static Map<Integer, SGsMsgType> valuesMap = new HashMap<>();
    static {
        Arrays.stream(values()).forEach(sGsMsgType -> valuesMap.put(sGsMsgType.value, sGsMsgType));
    }

    public static Optional<SGsMsgType> fromValue(int value){
        final SGsMsgType type = valuesMap.get(value);
        return Optional.ofNullable(type);
    }
}
