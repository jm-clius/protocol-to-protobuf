package za.co.jmc.pcap;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PcapFileAccessorTest {

    @Test
    public void readPcapFile() {
        final PcapFileAccessor pcapFileAccessor;
        try {
            pcapFileAccessor = PcapFileAccessor.readPcapFile("src/test/resources/test_sgs_ppi.pcap");
            System.out.println(pcapFileAccessor);
            
            PcapPacket packet = pcapFileAccessor.readPcapPacket().orElse(null);
            int packetCount = 0;
            while (packet != null){
                packetCount++;
                System.out.println(packetCount + ": " + packet);
                packet = pcapFileAccessor.readPcapPacket().orElse(null);
            }

            pcapFileAccessor.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}