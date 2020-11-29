package project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class PicCrawler {

    public static void main(String args[]) {
        
    }

    /**
     * 根据路径 下载图片 然后 保存到对应的目录下
     *
     * @param urlString 下载源地址路径 http://media.expedia.com/hotels/1000000/10000/100/1/1_17_b.jpg
     * @param filename  文件名
     * @param savePath  保存路径 /jdylog/pic/JDY000001
     * @return
     * @throws Exception
     */
    public static void download(String urlString, String filename, String savePath) throws Exception {
        // 构造URL
        // System.setProperty("http.proxySet", "true");
        // System.setProperty("http.proxyHost", "192.168.2.138");
        // System.setProperty("http.proxyPort", "1081");
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求的路径
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();

        is.close();
    }
}

