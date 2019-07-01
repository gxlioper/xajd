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
	/*����:�Ž���
     *����:�ж�Ŀ¼�Ƿ����,����������д���
     *����˵��:
     *   path:����·��
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

 	/*����:�Ž���
     *����:�ж�Ŀ¼�Ƿ����,����������д���
     *����˵��
     *	filepath Ҫ�õ��ļ�����·��;
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

	/*����:�Ž���
     *����:�������
     *����˵��
     *	obj ��Ҫ����Ķ���;
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
	
	//�ļ�����
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
