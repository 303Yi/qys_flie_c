import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadFile {
    /**
     * 模拟文件post上传
     * @param urlStr （接口地址）
     * @param formName （接口file接收名）
     * @param fileName （需要上传文件的本地路径）
     */
    public void uploadFile(String urlStr, String  formName, String fileName) {
        String baseResult = null;
        try {
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            String BOUNDARY = "========7d4a6d158c9";// 模拟数据分隔线
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// 设置为POST请求
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("connection", "Keep-Alive");// 设置请求头参数
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = conn.getOutputStream();

            File file = new File(fileName);
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            sb.append("Content-Disposition: form-data;name=\"").append(formName).append("\";filename=\"").append(fileName).append("\"").append(newLine);
            sb.append("Content-Type:application/octet-stream");
            sb.append(newLine);
            sb.append(newLine);

            out.write(sb.toString().getBytes());// 将参数头的数据写入到输出流中

            DataInputStream in = new DataInputStream(new FileInputStream(file));// 数据输入流,用于读取文件数据
            byte[] bufferOut = new byte[1024];
            int bytes = 0;

            while ((bytes = in.read(bufferOut)) != -1) {// 每次读1KB数据,并且将文件数据写入到输出流中
                out.write(bufferOut, 0, bytes);
            }

            out.write(newLine.getBytes());
            in.close();

            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
                    + boundaryPrefix + newLine).getBytes();

            out.write(end_data);
            out.flush();
            out.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            StringBuilder strs = new StringBuilder("");
            while ((line = reader.readLine()) != null) {
                strs.append(line);
            }
            baseResult = strs.toString();
        } catch (Exception e) {
            baseResult = e.getMessage();
        }
    }
}
