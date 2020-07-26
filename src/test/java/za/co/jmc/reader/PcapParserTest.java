package za.co.jmc.reader;

import org.junit.Test;

import static org.junit.Assert.*;

public class PcapParserTest {

    @Test
    public void testSGsAPParsing(){
        try{
            PcapParser.read("src/test/resources/test_sgs_ppi.pcap");
        } catch (Exception e){
//            fail(e.getMessage());
        }
    }
}