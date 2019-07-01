package xgxt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * 鲁宁
 * 2010.5.27
 * 处理zip文件相关的类
 */
public class ZipUtils {

	/**
	 * zip文件解压
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws Exception
	 */
	public boolean unzip(String inputPath, String outputPath) throws Exception{
		File input = new File(inputPath);
		try{
            ZipInputStream in = new ZipInputStream(new FileInputStream(input));            
            ZipEntry file = in.getNextEntry();
            File newdir = new File(outputPath);
            newdir.mkdir();
            
            byte[] c = new byte[1024];
            int slen;
            while (file != null){             
             if (file.isDirectory()){
              File dirs = new File(file.getName().replace("/",""));
              dirs.mkdir();
              dirs = null;
             }
             else{
              FileOutputStream out = new FileOutputStream(outputPath+File.separator+file.getName().substring(file.getName().indexOf("/")+1));
              while((slen = in.read(c,0,c.length)) != -1)
               out.write(c,0,slen);
              out.close();
             }
             file = in.getNextEntry();
//             file = null;
            }
            in.close();
           }catch(ZipException zipe){
        	   System.out.println("不是一个ZIP文件！");
           }catch(IOException ioe){
        	   System.out.println("读取w时错误！");
           }catch(Exception i){
        	   i.printStackTrace();
              System.out.println("over");
           }

		return true;
	}
	
	
	/**
	 * 处理删除文件
	 * @param parentPath
	 * @return
	 */
	boolean deleteFile(String parentPath){
//		String parentPath = file.getParent();
		File fileOne = new File (parentPath);
		String[] fileNames = fileOne.list();
		for(String fn:fileNames){
			File filetmp=new File(parentPath+"\\"+fn);
			if(filetmp.isDirectory()){
				deleteFile(parentPath+"\\"+fn);
			}
			try {
				filetmp.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
}
