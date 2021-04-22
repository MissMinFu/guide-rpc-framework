package github.javaguide;




import java.lang.reflect.Method;

public class Loader {
    public static void main(String[] args) {


        //System.out.println(System.getProperty("sun.boot.class.path"));
        //System.out.println(System.getProperty("java.ext.dirs"));
        //System.out.println(System.getProperty("java.class.path"));

        DiskClassLoader diskClassLoader1=new DiskClassLoader("D:\\lib");

        try{
            Class c=diskClassLoader1.findClass("github.javaguide.Test");
            
            if(c!=null){
                try{
                    Object ocj=c.newInstance();
                    Method  method=c.getDeclaredMethod("say",null);
                    method.invoke(ocj,null);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
