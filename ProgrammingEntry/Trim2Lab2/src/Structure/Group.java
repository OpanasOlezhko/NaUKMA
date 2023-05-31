package Structure;

public interface Group <T>{
    T getByName(String name);
    String[] getNames();
    boolean isTaken(String name);
    boolean isTaken(String name, T t);
    String ignoreNums(String name);

}
