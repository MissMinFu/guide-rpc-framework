package github.javaguide;


import java.io.*;

public class DiskClassLoader extends ClassLoader{

    private String mLibPath;

    public DiskClassLoader(String path){
        mLibPath=path;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String fileName=getFileName(name);

        File  file=new File(mLibPath,fileName);

        try{
            FileInputStream is=new FileInputStream(file);

            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            int len=0;
            try{
                while ((len=is.read())!=-1){
                    bos.write(len);
                }
            }catch (IOException e){

            }
            byte[] data=bos.toByteArray();
            is.close();
            bos.close();
            return defineClass(name,data,0,data.length);

        }catch (IOException e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }


    private String getFileName(String name){
        int index=name.lastIndexOf('.');
        if(index==-1){
           return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }
}
