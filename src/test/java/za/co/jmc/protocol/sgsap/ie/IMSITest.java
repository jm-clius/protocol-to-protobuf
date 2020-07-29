package za.co.jmc.protocol.sgsap.ie;

import org.junit.Test;
import za.co.jmc.util.ExtendedDataInputStream;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class IMSITest {

    @Test
    public void testIMSI() throws IOException {
        final HexBinaryAdapter adapter = new HexBinaryAdapter();
        final byte[] imsiBytes = adapter.unmarshal("084940199909000010");

        final IMSI imsi = IMSI.parseLV(new ExtendedDataInputStream(new ByteArrayInputStream(imsiBytes)));

        System.out.println(imsi);
    }

}