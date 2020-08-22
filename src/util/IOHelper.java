package util;


import java.io.Closeable;
import java.io.IOException;

/**
 *关闭流
 * 可变参数数组:输入的参数个数不限
 * fis,fos
 * close(fis,fos)
 * close(fis)
 * close(fis,fos,...)
 *
 * 注意点:
 * 1.可变参数数组必须定义在方法中的最后一个参数
 * 2.不传参数，java也会默认传一个长度为0的数组
 * close();closers.length == 0
 */
public class IOHelper {
    public static void close(Closeable... closers) {
        //closers 是一个数组 类型是Closeable[]
        for (Closeable closer : closers) {
            if (closer != null) {
                try {
                    closer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
