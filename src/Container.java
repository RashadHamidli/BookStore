public class Container<T> {
    private T Object;
//    private E Object2;

    public Container(T object) {
        this.Object = object;
    }
    public T doItAndReturn(){
        System.out.println("i did something");
        return this.Object;
    }
}
