package exception;


public class SctpException extends Exception{
    public  SctpException(String s){
        super(s);
    }

    public SctpException(Exception exception) {
        super(exception);
    }
}
