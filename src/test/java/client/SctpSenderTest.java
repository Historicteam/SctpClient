package client;


import model.scparametr.ScAddress;
import new_client.SctpSenderCreater;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import sender.CommandaWithParametr;
import sender.SctpSender;

import java.io.IOException;

@RunWith(Theories.class)
public class SctpSenderTest {

    public SctpSender sctpSender;
    @DataPoints
    public static SctpSender[] getSenders(){
        return new SctpSender[]{};
    }

    @Test
    public void testCreate(){
        try (SctpSender ignored = SctpSenderCreater.createSender("sdf", 555667)){

        } catch (IOException e) {
            e.printStackTrace();
        }

        sctpSender.create().success(new CommandaWithParametr<ScAddress>() {
            @Override
            public void execute(ScAddress scAddress) {

            }
        }).error(code->{}).unsuccess(()->{}).getResult().get();
    }
}
