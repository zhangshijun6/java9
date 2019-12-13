package cn.xdl.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class demo1 {
    public static void  demo1(String src,String dst) throws IOException {
        //声明源文件和目标文件
        FileInputStream fi= new FileInputStream(new File(src));
        FileInputStream fo= new FileInputStream(new File(dst));
        //获得传输通道
        FileChannel inChannel=fi.getChannel();
        FileChannel outChannel=fo.getChannel();
        //获取容器buffer
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        while(true){
            //判断是否读完文件
            int e = inChannel.read(buffer);
            if(e ==-1){
                break;
            }
            //重新设置bufffer的 position=0 limit=position
            buffer.flip();
            //开始写
            outChannel.write(buffer);
            //写完重置buffer,重新设置position=0，limit=capacity
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fo.close();
        fi.close();

    }
}
