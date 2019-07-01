package xgxt.xtwh.zpgl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

import org.apache.struts.upload.FormFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zfsoft.utils.FileUploadUtil;

/**
 * 系统级使用的公用方法
 * @author hufei
 *
 */
public class FileOper {

	private static final int BUFFER_SIZE = 16 * 1024 ;

	/**
	 * 检测给定路径的文件夹是否存在，若不存在则创建。
	 * @param fileDirPath 文件夹路径
	 */
	public static void mkdir(String fileDirPath){
		File tempFilePath  = new File(fileDirPath);
		if(!tempFilePath.exists()){
			tempFilePath.mkdir();
		}
	}
	
	/**
	 * 将文件内容写入新的文件中
	 * @param file 数据源文件
	 * @param newFile 要写人的文件
	 */
	public static void writeFile(File file,File newFile){
	
		try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
                in = new BufferedInputStream( new FileInputStream(file), BUFFER_SIZE);
                out = new BufferedOutputStream( new FileOutputStream(newFile), BUFFER_SIZE);
                 byte [] buffer = new byte [BUFFER_SIZE];
                 while (in.read(buffer) > 0 )  {
                    out.write(buffer);
                } 
             } finally  {
                 if ( null != in)  {
                    in.close();
                } 
                  if ( null != out)  {
                    out.close();
                } 
            } 
         } catch (Exception e)  {
            e.printStackTrace();
        } 
	}
	
	/**
	 * 将文件内容写入新的文件中
	 * @param file 数据源文件
	 * @param newFile 要写人的文件
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void writeFile(FormFile file,String tempDir,File newFile) throws FileNotFoundException, IOException{
	
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = "" + newFile;
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead1 = 0;
		byte[] buffer1 = new byte[size];
		while ((bytesRead1 = in.read(buffer1, 0, size)) != -1) {
			out.write(buffer1, 0, bytesRead1);
		}
		out.close();
		in.close();
	}
	
	/**
	 * 获取给定文件的后缀，带点，如.xml
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getFileSuffix(String filePath){
		int begin = filePath.lastIndexOf(".");
		return filePath.substring(begin,filePath.length());
	}
	
	/**
	 * 获取给定的excel文件的列标题，标题放在list的Map中，键为“column”
	 * @param filePath 文件路径
	 * @return
	 */
	public static List<Map<String, String>> getExcelColumns(String filePath){
		List<Map<String, String>> cols = new ArrayList<Map<String, String>>();
		try {
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			
			Sheet sheet = wb.getSheet(0);
			Cell[] colRow = sheet.getRow(0);
			for(int cellnum=0;cellnum<colRow.length;cellnum++){
				HashMap<String, String> cellCol = new HashMap<String, String>();
				cellCol.put("column", colRow[cellnum].getContents());
				cols.add(cellCol);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cols;
	}
	
	/**
	 * 获取给定字符数组在给定excel文件中的列下标，组成下标数组返回
	 * @param titName 给定列数组
	 * @param filePath 文件路径
	 * @return
	 */
	public static int[] getExcelTitSuffix(String[] titName,String filePath){
		List<Map<String,String>> list = getExcelColumns(filePath); 
		int[] suffix = new int[titName.length];
		int suffixNum = 0;
		for(int i = 0; i < list.size();i++){
			Map<String,String> map = list.get(i);
			
			for(int y = 0; y < titName.length;y++){
				if(map.get("column").equalsIgnoreCase(titName[suffixNum])){
					suffix[suffixNum] = i;
					suffixNum++;
				}
			}
		}
		return suffix;
	}
	
	/**
	 * 根据给定的切分词切分字符串，并将切分后的数组根据给定的索引返回
	 * @param str 需切分字符串
	 * @param splitNam 切分词
	 * @param indexNmu 索引
	 * @return
	 */
	public static String getSplitStr(String str,String splitNam,int indexNum){
		String[] splitStr = str.split(splitNam); 
		String newStr = null;
		if(splitStr.length > 0){
			newStr = splitStr[indexNum];
		}
		return newStr;
	}
	
	/**
	 * 获取给定excel文件的给定sheet
	 * @param filePath 文件路径
	 * @param sheetNum sheet编号
	 * @return
	 */
	public static Sheet getExcelSheet(String filePath,int sheetNum){
		Sheet sheet = null;
		try {
			sheet = Workbook.getWorkbook(new File(filePath)).getSheet(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}
	
	/**
	 * 获得指定的excel表单的某行数据
	 * @param sheet excel的sheet
	 * @return
	 */
	public static String[] getOneRow(Sheet sheet,int rownum,int excelColNum){
		/*Cell[] row = new Cell[excelColNum];
		Cell[] cell = sheet.getRow(rownum); 
		for(int i = 0;i < cell.length;i++){
			row[i] = cell[i];
		}
		if(excelColNum > cell.length){
			for(int i = cell.length;i < excelColNum - cell.length;i++){
				row[i] = "";
			}
		}
		
		return row;*/
		
		Cell[] row = sheet.getRow(rownum);		
		String[] result = new String[Math.max(excelColNum, row.length)];
		int length = Math.min(excelColNum, row.length);
		for(int rnum=0;rnum<length;rnum++){
			result[rnum] = row[rnum].getContents();
		}
		for(int rowNum=0; rowNum<result.length;rowNum++){
			if(result[rowNum]==null){
				result[rowNum] = "";
			}
		}
		return result;
	}
	
	/**
	 * 将Cell[]转为String[],字符串数组比单元格长1
	 * @param cell excel的cell数组
	 * @return
	 */
	public static String[] cell2String(Cell[] cell){
		String[] str = new String[cell.length+1];
		for(int i = 0;i < cell.length;i++){
			str[i] = cell[i].getContents();
		}
		return str;
	}
	
	/**
	 * 获取一个EXCEL工作表
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static WritableWorkbook getWritableWorkbook(
			HttpServletResponse response) {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook ww = null;
		try {
			ww = Workbook.createWorkbook(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ww;
	}
	
	/**
	 * 删除指定路径下的文件,单文件删除
	 * @param filePath 文件路径
	 */
	public static void delFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * 删除某文件夹下的所有文件
	 * @param parentPath 文件夹路径
	 * @return
	 */
	public static boolean delFiles(String parentPath){
		File fileOne = new File (parentPath);
		String[] fileNames = fileOne.list();
		for(String fn:fileNames){
			File filetmp=new File(parentPath+"/"+fn);
			if(filetmp.isDirectory()){
				delFile(parentPath+"/"+fn);
			}
			try {
				filetmp.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	/**
	 * 将request中的文件内容获取到并写成实体文件，存入服务器端。存入的文件夹由程序
	 * 默认给定。使用单线程，确保生成的文件名不会重复。
	 * @param tempDir 临时文件存放的文件夹路径
	 * @param src 源文件
	 * @param fileName 源文件文件名
	 * @param useSrcName 是否使用上传文件名作为生成文件名
	 * @return 生成文件的全路径
	 */
	public synchronized static String fileUp(String tempDir,FormFile src,String fileName,boolean useSrcName){
		FileOper.mkdir(tempDir);
		String newFileName = "";
		if(!useSrcName){
			//使用当前时间作为上传文件名
			newFileName = System.currentTimeMillis() 
								+ FileOper.getFileSuffix(fileName);
		}else
			newFileName = fileName;
		
		try {
			FileOper.writeFile(src,tempDir, new File(tempDir + "/" + newFileName));
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return tempDir + "/" + newFileName;
	}
	
	/**
	 * 显示图片
	 * @param response
	 * @param in
	 * @throws IOException 
	 */
	public static void showPic(HttpServletResponse response,InputStream in) throws IOException{
		OutputStream outs = response.getOutputStream();   
		response.setContentType("image/gif;charset=UTF-8");
		BufferedInputStream bis=new BufferedInputStream(in);    //输入缓冲流
		BufferedOutputStream bos=new BufferedOutputStream(outs);   //输出缓冲流
		byte data[]=new byte[4096];    //缓冲字节数    
		int size=0;    
		size=bis.read(data);
		while (size!=-1){
			bos.write(data,0,size); size=bis.read(data);
		}   
		bis.close();   
		bos.flush();//清空输出缓冲流         
		bos.close();          
		outs.close();
	}
	
	/**
	 * 获取项目物理路径，到web-inf同级，路径最后不包含“/”
	 * @return
	 */
	public static String getFileRealBasePath(){
		String path = "";
		if(System.getProperty("os.name").equals("Linux")){
			path = FileOper.class.getResource("/").toString();
		}else{
			path = FileOper.class.getResource("/").toString().substring(5);
		}
		path = path.substring(1, path.indexOf("WEB-INF")-1); 
		path = path.replaceAll("%20", " ");
		return path;
	}
	
	/**
	 * 使用dom4j获取给定路径的xml文件的document
	 * @param xmlPath 文件路径
	 * @return
	 * @throws MalformedURLException 
	 */
	public static Document getDocument(String xmlPath) throws MalformedURLException{
		SAXReader reader = new SAXReader();
        Document d = null;
		try {
			d = reader.read(new File(xmlPath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        return d;
		
	}
	
	/**
	 * 使用xPath获取节点列表
	 * @param d document对象
	 * @param xPath 
	 * @return
	 */
	public static List<Element> getNodeList(Document d, String xPath) {
		List<Element> list =  d.selectNodes(xPath);
        return list;
    }
	
	/**
	  * getJspUrl 方法
	  * <p>方法说明: 检测个性化页面是否存在，若存在获取个性化页面，若不存在则获取通用跳转页面</p>
	  * @param request
	  * @param gxhJsp 个性化页面路径，例：/jsp/print/bdd/12317.jsp
	  * @param tyJsp 通用页面路径，例：/jsp/print/bdd/default.jsp
	  * @return String
	  * @author hhy
	  * @date 2011-6-23
	 */
	public static String getJspUrl(HttpServletRequest request,String gxhJsp,String tyJsp){
		File f = new File(request.getRealPath(gxhJsp));
		if(f.exists()){
			return gxhJsp;
		}else{
			return tyJsp;
		}
	}

	public static void main(String[] args) throws MalformedURLException{
		String path = FileOper.class.getResource("/").toString().substring(5);
		path = path.substring(1, path.indexOf("WEB-INF")-1); 
		String xmlpath = path + "/dxfs.xml";
		Document d = FileOper.getDocument(xmlpath);
		Element root = d.getRootElement();
		List<Element> list = FileOper.getNodeList(d, "//dxname");
		System.out.println(list.get(0).getText());
	}
}
