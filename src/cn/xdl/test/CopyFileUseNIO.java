package cn.xdl.test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileUseNIO {
    private String file;

    public CopyFileUseNIO(String file) {
        super();
        this.file = file;
    }
    public void copy(String source,String target){
        try {
            //声明源文件和目标文件
            FileInputStream fi=new FileInputStream(source);
            FileOutputStream fo=new FileOutputStream(target);
            //声明通道channel(通道)
            FileChannel inChannel=fi.getChannel();
            FileChannel outChannel=fo.getChannel();
            //获取容器buffer(缓存区)
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            while(true){
                //读取源文件
                int s=inChannel.read(buffer);
                //判断是否读完源文件
                if(s ==-1) break;
                buffer.flip();//读取模式转换为写入模式
                outChannel.write(buffer);
                System.out.println("复制完成");
                buffer.clear();//清除缓存 等待下次写入
            }
            outChannel.close();
            inChannel.close();
            fo.close();
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CopyFileUseNIO cpf=new CopyFileUseNIO("D:\\1.txt");
        cpf.copy("D:\\1.txt","D:\\2.txt");
    }
}
