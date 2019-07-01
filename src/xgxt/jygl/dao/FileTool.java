package xgxt.jygl.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileTool {
	/*作者:张建军
     *功能:判断目录是否存在,不存在则进行创建
     *参数说明:
     *   path:绝对路径
     */
 	public void mkdirs(String path) {
		if(!(new File(path).isDirectory())){
	        try{
	            new File(path).mkdirs();
	        }catch(SecurityException e){
	            System.out.println("can not make security direcoty");
	        }
	    }
	}

 	/*作者:张建军
     *功能:判断目录是否存在,不存在则进行创建
     *参数说明
     *	filepath 要得到文件名的路径;
     */
	public String GetFileName(String filepath)
	{
	    String returnstr = "*.*";
	    int length       = filepath.trim().length();
	    
	    filepath = filepath.replace('\\', '/');
	    if(length >0)
	    {
	        int i = filepath.lastIndexOf("/");
	        if (i >= 0)
	        {
	            filepath  = filepath.substring(i + 1);
	            returnstr = filepath;
	        }
	    }
	    return returnstr;
	}

	/*作者:张建军
     *功能:输出对象
     *参数说明
     *	obj 想要输出的对象;
     */
	public void print(Object obj) {
		System.out.println(obj);
	}
	
	
	public void path_delete(String path) {
		if((new File(path).isDirectory())){
		    try{
		        new File(path).delete();                    
		    }catch(SecurityException e){
		        System.out.println("can not delete security direcoty");
		    }
		   }
	}


	public void file_delete(String path) {
		if((new File(path).exists())){
		    try{
		        new File(path).delete();                    
		    }catch(SecurityException e){
		        System.out.println("can not delete security direcoty");
		    }
		   }
	}
	
	//文件下载
	public void downFile(HttpServletResponse response, String dir, String filename) throws FileNotFoundException, IOException, InterruptedException {
		byte b[]= new byte[500];

		File fileload=new File("d://file");
//		response.setContentType("application/octet-stream");
		response.setContentType("application/vnd.ms-excel");
//		response.setContentType("application/msword");
		// map.put("doc", "application/msword");
//		 map.put("xls", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment;filename=" + (filename));
		InputStream in=new FileInputStream(fileload);
		OutputStream os = null;
		while ((in.read(b))!=-1) {
		os=response.getOutputStream();
		os.write(b);
	    response.notify();
		}
	}

	public void setNullBean(HttpServletRequest request, String[] txt) {
		for (int i = 0; i < txt.length; i++)
		{
			request.setAttribute(txt[i], "");	
		}
	}

}
