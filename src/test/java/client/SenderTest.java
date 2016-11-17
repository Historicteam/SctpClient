package client;


import model.scparametr.ScAddress;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import sender.Sender;

@RunWith(Theories.class)
public class SenderTest {

    public Sender sender;
    @DataPoints
    public static Sender[] getSenders(){
        return new Sender[]{};
    }
    @Test
    public void testCreate(){
        Integer integer = sender.create().success(scAddress -> {}).error(code -> {}).getResult().get();
        sender.check(new ScAddress(444)).success(sctpCodeReturn -> {}).error((code)->{}).unsuccess().unfound().unavailable().getResponce();
        sender.delete(new ScAddress(444)).success((addres)->{}).error((code)->{}).unsuccess().unfound().unavailable().getResponce();
        sender.get(new ScAddress(444)).success((addres)->{
            addres.length
        }).error((code)->{}).unsuccess().unfound().unavailable().getResponce();
    }
}
