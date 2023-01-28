public class Main {
    public static void main(String[] args) {
        Container<User> container = new Container<>(new User());
        doSomething(container);
    }

    public static void doSomething(Container<User> container) {
        User user = (User) container.doItAndReturn();
        System.out.println("user=" + user);
    }
}