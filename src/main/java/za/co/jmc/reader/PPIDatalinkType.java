package za.co.jmc.reader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * List of user-defined DLT protocols for Per Packet Information .pcap-file format.
 * This could be made fully configurable.
 *
 */
public enum PPIDatalinkType {
    SGS_AP (160),
    ;

    public final int value;

    private static final Map<Integer, PPIDatalinkType> valuesMap = new HashMap<>();
    static {
        Arrays.stream(values()).forEach(dataLinkType -> valuesMap.put(dataLinkType.value, dataLinkType));
    }

    PPIDatalinkType(int value){
        this.value = value;
    }

    public static PPIDatalinkType fromValue(int value){
        final PPIDatalinkType type = valuesMap.get(value);
        if (type == null) throw new IllegalArgumentException("Unknown PPI data link type: " + type);
        return type;
    }

}
