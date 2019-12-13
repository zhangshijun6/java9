package cn.xdl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DemoFileAll {

    public static void copyAllFiles(File Originalcatalogue , File Targetdirectory){
        //判断原目录文件是否存在
        if(Originalcatalogue .isDirectory()){
            //判断目标目录是否存在，若不存在则创建目标目录
            if(!Targetdirectory.exists()){
                Targetdirectory.mkdir();
            }
            //将文件夹下的原文件存入文件数组
            String[] fs=Originalcatalogue .list();
            for(String f:fs){
                //创建文件夹下的子目录
                File source=new File(Originalcatalogue ,f);
                File target=new File(Targetdirectory,f);
                //将文件进行循环下一层
                copyAllFiles(source,target);
            }
        }else{
            try {
                //声明源文件和目标文件
                FileInputStream fi=new FileInputStream(Originalcatalogue );
                FileOutputStream fo=new FileOutputStream(Targetdirectory);
                //声明通道channel(通道)
                FileChannel inChannel=fi.getChannel();
                FileChannel outChannel=fo.getChannel();
                //获取容器buffer(缓存区)
                ByteBuffer buffer=ByteBuffer.allocate(1024);
                while(true){
                    int all=inChannel.read(buffer);
                    //判断是否读完源文件
                    if(all==-1){
                        break;
                    }
                    //重新设置bufffer的 position=0 limit=position
                    buffer.flip();
                    //开始写
                    outChannel.write(buffer);
                    System.out.println("复制完成");
                    //写完重置buffer,重新设置position=0，limit=capacity
                    buffer.clear();
                }
                outChannel.close();
                inChannel.close();
                fo.close();
                fi.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args){
        copyAllFiles(new File("D:\\1"),new File("D:\\2"));
    }
}
