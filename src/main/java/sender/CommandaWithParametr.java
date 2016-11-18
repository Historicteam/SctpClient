package sender;

@FunctionalInterface
public interface CommandaWithParametr<T>  {
    void execute(T t);
}
