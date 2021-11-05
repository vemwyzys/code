package cn.gzho.exception;

public class Test {

    public static void main(String[] args) {
        try{
            try{
                throw new RuntimeException();
            }catch (Exception e){
                System.out.println("inner");
            }
        }catch (Exception e){
            System.out.println("outer");
        }
    }
}
