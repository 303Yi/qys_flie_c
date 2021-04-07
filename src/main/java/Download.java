import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {
    public String getUrl(String url){
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            //创建地址对象
            URL u=new URL(url);
            //创建HttpURLConnection链接对象
            HttpURLConnection huconn=(HttpURLConnection) u.openConnection();
            //连接服务器
            huconn.connect();
            // 取得输入流，并使用Reader读取，设定字符编码
           in = new BufferedReader(new InputStreamReader(huconn.getInputStream(), "UTF-8"));
            String line;
            //读取返回值，直到为空
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关闭输入流
        finally{
            try{
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
