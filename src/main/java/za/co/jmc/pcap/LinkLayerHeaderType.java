package za.co.jmc.pcap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum LinkLayerHeaderType {
    LINKTYPE_ETHERNET (1),
    LINKTYPE_PPI (192),
    ;

    public final int value;

    private static final Map<Integer, LinkLayerHeaderType> valueMap = new HashMap<Integer, LinkLayerHeaderType>();
    static{
        Arrays.stream(values()).forEach(linkLayerHeaderType ->
                valueMap.put(linkLayerHeaderType.value, linkLayerHeaderType));
    }

    LinkLayerHeaderType(int value){
        this.value = value;
    }

    /**
     * Returns the LinkLayerHeaderType for a provided value
     *
     * @param value
     * @return LinkLayerHeaderType for this value
     * @throws IllegalArgumentException
     */
    public static LinkLayerHeaderType fromValue(int value) throws IllegalArgumentException{
        final LinkLayerHeaderType llType = valueMap.get(value);
        if (llType == null) throw new IllegalArgumentException("Unknown LinkLayer ID: " + value);
        return llType;
    }
}
