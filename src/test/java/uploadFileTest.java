import org.junit.Test;



public class uploadFileTest {

    @Test
    public void uploadFile() {
        UploadFile upload = new UploadFile();
        upload.uploadFile("http://localhost:8080/files/upload","file","C:\\Users\\13488\\Desktop\\QQ图片20210113220325.png");
    }
    @Test
    public void get(){
        Download get = new Download();
        System.out.println(get.getUrl("http://localhost:8080/files/download/d1a25ddcca6043b9a4d10d6f9671ab8b"));
    }
    @Test
    public void show(){
        Download show = new Download();
        System.out.println(show.getUrl("http://localhost:8080/files/showAll"));
    }
}
