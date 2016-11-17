package sender;

@FunctionalInterface
public interface Commanda<T>  {
    void execute(T t);
}
