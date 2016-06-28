package basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BasicBean {
   private int max;
   private String encType;
   private String path;
   private MultipartRequest multi;
   private static BasicBean instance = new BasicBean();

	public static BasicBean newInstance() {
		return instance;
	}
   public void setPath(HttpServletRequest request, String path){
      this.path=request.getServletContext().getRealPath(path);
   }
   public void setMax(int max){
      this.max=max;
   }
   public void setEncType(String type){
      this.encType=type;
   }
   public void setMulti(HttpServletRequest request){
      try {
         multi=new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public MultipartRequest getMulti() {
      return multi;
   }
   public String getUser() {
      return multi.getParameter("user");
   }
   public String getTitle() {
      return multi.getParameter("title");
   }
   public ArrayList<String> getUpFileInfo() {
	  ArrayList<String> imagelist = new ArrayList<String>();
      Enumeration enumer=multi.getFileNames();
      String result="";
      while(enumer.hasMoreElements()){ // 프로필 사진과 배경 사진의 이름을 result에 넣어서 array리스트에 담음
         String name=(String)enumer.nextElement();
         result = multi.getFilesystemName(name);
         imagelist.add(result);
      }
      return imagelist;
   }
  
}