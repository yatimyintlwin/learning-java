public class ClassNotFoundExceptionExample {
    public static void main(String[] args) {
        try {
            Class temp = Class.forName("gfg");
        } catch (ClassNotFoundException e) {
            System.out.println("Class does not exist check the name of the class");
        }
    }
}
