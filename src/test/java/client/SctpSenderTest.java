package client;


import model.scparametr.ScAddress;
import model.scparametr.ScString;
import model.scparametr.scelementtype.ScConnector;
import model.scparametr.scelementtype.ScNode;
import new_client.SctpSenderCreater;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import sender.SctpSender;

import java.io.IOException;

@RunWith(Theories.class)
public class SctpSenderTest {

    public static final Logger LOG = Logger.getLogger(SctpSenderTest.class);

    SctpSender sctpSender;

    @DataPoints
    public static SctpSender[] getSenders() {
        return new SctpSender[]{SctpSenderCreater.createSender("192.168.20.128", 55770)};
    }

    @After
    public void close() throws IOException {
        LOG.info(sctpSender);
        sctpSender.close();
    }

    @Before
    public void init() {

    }

    @Theory
    public void testCreateLink(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        sctpSender.create().success((address) -> {
            LOG.info(address);
        });
    }

    @Theory
    public void testCreateNode(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        sctpSender.create(ScNode.CLASS_CONST).success((address) -> {
            LOG.info(address);
        });
    }

    @Theory
    public void testCreateConnector(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        sctpSender.create(ScConnector.ARC_ACCESS_FUZ_PERM_VAR, sctpSender.create(ScNode.CLASS_CONST).get(), sctpSender.create(ScNode.CLASS_CONST).get()).success((address) -> {
            LOG.info(address);
        });

    }

    @Theory
    public void testCheck(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        ScAddress scAddress = sctpSender.create().get();
        sctpSender.check(scAddress).success(code -> {
            LOG.info(code);
        });

    }

    @Theory
    public void testType(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        ScAddress scAddress = sctpSender.create(ScNode.MATERIAL_VAR).get();
        sctpSender.type(scAddress).success(type -> {
            LOG.info(type);
            Assert.assertEquals(ScNode.MATERIAL_VAR.get().get(), type.get());
        });
    }



    @Theory
    public void testDelete(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        ScAddress scAddress = sctpSender.create(ScNode.MATERIAL_VAR).get();
        sctpSender.delete(scAddress).success(codeReturn -> {
            LOG.info(codeReturn);
        });
        sctpSender.check(scAddress).unsuccess(()->{
            LOG.info("Элемент не найден");
        });
    }

    @Theory
    public void testContentAndLink(SctpSender sctpSender) {
        this.sctpSender = sctpSender;
        ScAddress scAddress = sctpSender.create().get();
        sctpSender.link(scAddress, new ScString("OSTIS")).success(codeReturn -> {LOG.info(codeReturn);});
        sctpSender.content(scAddress).success(scString -> LOG.info(scString));
    }


}
